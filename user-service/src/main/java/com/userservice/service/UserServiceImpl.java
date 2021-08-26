package com.userservice.service;

import com.userservice.domain.User;
import com.userservice.dto.UserDto;
import com.userservice.repository.UserRepository;
import com.userservice.vo.ResponseOrders;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final Environment env;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User user = modelMapper.map(userDto, User.class);
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        UserDto userDto = modelMapper.map(user, UserDto.class);

        String orderUrl = String.format(env.getProperty("order_service.url"), userId);
        ResponseEntity<List<ResponseOrders>> orderListResponse = restTemplate.exchange(orderUrl, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        List<ResponseOrders> orderList = orderListResponse.getBody();
        userDto.setOrders(orderList);

        return userDto;
    }

    @Override
    public Iterable<User> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailsByEmail(String userName) {
        User user = userRepository.findByEmail(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }

        return modelMapper.map(user, UserDto.class);
    }

}
