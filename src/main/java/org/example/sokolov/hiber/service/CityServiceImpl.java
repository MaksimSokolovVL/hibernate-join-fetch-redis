package org.example.sokolov.hiber.service;

import org.example.sokolov.hiber.domain.entity.City;
import org.example.sokolov.hiber.domain.entity.Country;
import org.example.sokolov.hiber.domain.entity.CountryLanguage;
import org.example.sokolov.hiber.repository.CityRepository;
import org.example.sokolov.hiber.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CityServiceImpl implements CityService {

    private final CountryRepository countryRepo;
    private final CityRepository cityRepo;

    public CityServiceImpl(CityRepository cityRepo, CountryRepository countryRepo) {
        this.cityRepo = cityRepo;
        this.countryRepo = countryRepo;
    }


    @Override
    @Transactional(readOnly = true)
    public List<City> getItems(int offset, int limit) {
        return cityRepo.getItems(offset, limit);
    }

    @Override
    @Transactional(readOnly = true)
    public int getTotalCount() {
        return cityRepo.getTotalCount();
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> getAllCityByStep(int step) {

        List<Country> countryRepoAll = countryRepo.getAll();
        int totalCount = getTotalCount();
        List<City> result = new ArrayList<>();
        int offset = 0;
        while (offset < totalCount) {
            List<City> tempList = cityRepo.getItems(offset, step);
            result.addAll(tempList);
            offset += step;
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public void testMysqlData(List<Integer> ids) {
        for (Integer id : ids) {
            City city = cityRepo.getById(id);
            Set<CountryLanguage> languages = city.getCountryId().getLanguages();
        }
    }
}
