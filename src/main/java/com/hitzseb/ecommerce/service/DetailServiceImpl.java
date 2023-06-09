package com.hitzseb.ecommerce.service;

import com.hitzseb.ecommerce.model.Detail;
import com.hitzseb.ecommerce.repo.DetailRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailServiceImpl implements DetailService {
    private final DetailRepo detailRepo;

    @Override
    public void saveDetail(Detail detail) {
        detailRepo.save(detail);
    }

    @Override
    public void saveAllDetails(List<Detail> detailList) {
        detailRepo.saveAll(detailList);
    }

    @Override
    public Optional<Detail> findDetailById(Long id) {
        return detailRepo.findById(id);
    }

    @Override
    public void deleteDetailById(Long id) {
        detailRepo.deleteById(id);
    }
}
