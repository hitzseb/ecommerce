package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Detail;
import com.hitzseb.ecommerce.repo.DetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepo detailRepo;

    public void saveAllDetails(List<Detail> detailList) {
        detailRepo.saveAll(detailList);
    }

}
