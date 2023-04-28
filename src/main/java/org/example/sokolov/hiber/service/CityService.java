package org.example.sokolov.hiber.service;


import org.example.sokolov.hiber.domain.entity.City;

import java.util.List;

public interface CityService {
    List<City> getItems(int offset, int limit);
    int getTotalCount();
    List<City> getAllCityByStep(int step);
    void testMysqlData(List<Integer> ids);
}
