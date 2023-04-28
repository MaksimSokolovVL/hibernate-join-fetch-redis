package org.example.sokolov.hiber.repository;


import org.example.sokolov.hiber.domain.entity.City;

import java.util.List;

public interface CityRepository {
    List<City> getItems(int offset, int limit);
    int getTotalCount();
    City getById(Integer id);
}
