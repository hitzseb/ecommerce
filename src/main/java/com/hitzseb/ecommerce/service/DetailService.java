package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Detail;
import com.hitzseb.ecommerce.repo.DetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepo detailRepo;

    public void saveDetail(Detail detail) {
        detailRepo.save(detail);
    }

    public void saveAllDetails(List<Detail> detailList) {
        detailRepo.saveAll(detailList);
    }

    public Optional<Detail> findDetailById(Long id) {
        return detailRepo.findById(id);
    }

    public void deleteDetailById(Long id) {
        detailRepo.deleteById(id);
    }
}
