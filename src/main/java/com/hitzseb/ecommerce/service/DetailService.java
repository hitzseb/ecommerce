package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Detail;

import java.util.List;
import java.util.Optional;

public interface DetailService {
    void saveDetail(Detail detail);
    void saveAllDetails(List<Detail> detailList);
    Optional<Detail> findDetailById(Long id);
    void deleteDetailById(Long id);
}
