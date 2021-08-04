package com.catalogservice.repository;

import com.catalogservice.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    Catalog findByProductId(String productId);
}
