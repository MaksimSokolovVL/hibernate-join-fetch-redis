package org.example.sokolov.hiber.repository;


import org.example.sokolov.hiber.domain.entity.Country;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CountryRepoImpl implements CountryRepository {

    private final SessionFactory sessionFactory;

    public CountryRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Country> getAll() {
        Query<Country> query = sessionFactory.getCurrentSession()
                .createQuery(
                        "select c from Country c join fetch c.languages", Country.class
                );
        return query.list();
    }
}
