package com.userservice.dto;

import com.userservice.vo.ResponseOrders;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    private String userId;
    private LocalDateTime createdAt;

    private String encryptedPwd;
    private List<ResponseOrders> orders;
}
