package org.example.sokolov.hiber.service;

import org.example.sokolov.hiber.domain.entity.Country;
import org.example.sokolov.hiber.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepo;

    public CountryServiceImpl(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        return countryRepo.getAll();
    }
}
