package com.citronix.citronix.repository.Impl;

import com.citronix.citronix.model.Farm;
import com.citronix.citronix.repository.FarmRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class FarmRepositoryImpl implements FarmRepositoryCustom {

    @PersistenceContext
    EntityManager em;
    @Override
    public List<Farm> findAllFarmsBySurface(double surface) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);
        Root<Farm> farm = cq.from(Farm.class);
        cq.select(farm);
        cq.where(cb.greaterThanOrEqualTo(farm.get("surfaceArea"), surface));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public Farm findFarmByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);
        Root<Farm> farm = cq.from(Farm.class);
        cq.select(farm);
        cq.where(cb.equal(farm.get("name"), name));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public List<Farm> findFarmsByNameAndLocation(String name, String location) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);
        Root<Farm> farm = cq.from(Farm.class);
        cq.select(farm);
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.equal(farm.get("name"), name));
        predicates.add(cb.like(farm.get("location"), "%" + location + "%"));
        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        return em.createQuery(cq).getResultList();
    }
}
