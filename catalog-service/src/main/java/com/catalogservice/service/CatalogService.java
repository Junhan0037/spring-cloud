package com.catalogservice.service;

import com.catalogservice.domain.Catalog;

public interface CatalogService {
    Iterable<Catalog> getAllCatalog();
}
