package org.example.sokolov.hiber.repository;

import org.example.sokolov.hiber.domain.entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityRepoImpl implements CityRepository {
    private final SessionFactory sessionFactory;

    public CityRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<City> getItems(int offset, int limit) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery(
                "select c from City c", City.class
        );
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public int getTotalCount() {
        Query<Long> query = sessionFactory.getCurrentSession().createQuery(
                "select count(c) from City c", Long.class
        );
        return Math.toIntExact(query.uniqueResult());
    }

    public City getById(Integer id) {
        Query<City> query = sessionFactory.getCurrentSession().createQuery(
                "select c from City c join fetch c.countryId where c.id = :ID", City.class
        );
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
}
