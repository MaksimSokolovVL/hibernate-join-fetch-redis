package org.example.sokolov.hiber.repository;


import org.example.sokolov.hiber.domain.entity.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> getAll();
}
