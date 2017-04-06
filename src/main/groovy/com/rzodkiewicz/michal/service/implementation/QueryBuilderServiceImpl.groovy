package com.rzodkiewicz.michal.service.implementation

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.dto.EmissionChartDto
import com.rzodkiewicz.michal.dto.FilterDto
import com.rzodkiewicz.michal.service.QueryBuilderService
import com.rzodkiewicz.michal.util.enums.AggregationType
import com.rzodkiewicz.michal.util.enums.FilterType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityManager
import javax.persistence.Query
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Service
class QueryBuilderServiceImpl implements QueryBuilderService {

    private final EntityManager entityManager;


    @Autowired
    public QueryBuilderServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    Query emissionQuery(FilterDto request) {
        CriteriaBuilder criteriaBuilder = entityManager.criteriaBuilder
        CriteriaQuery<Emission> query = criteriaBuilder.createQuery(Emission.class)
        Root<Emission> emissionRoot = query.from(Emission.class)
        Predicate where = criteriaBuilder.conjunction()
        query.select(emissionRoot)
        query.where(appendFilters(where, request, criteriaBuilder, emissionRoot))
        entityManager.createQuery(query)
    }

    private boolean filterSelected(FilterDto request) {
        return request.startDate || request.endDate || request.sectors || request.countries
    }

    private boolean filterSelected(FilterDto request, FilterType property) {
        switch (property) {
            case FilterType.YEAR:
                return request.startDate && request.endDate
            case FilterType.SECTOR:
                return request.sectors
            case FilterType.COUNTRY:
                return request.countries
        }
    }

    private Predicate appendFilters(Predicate where, FilterDto request, CriteriaBuilder criteriaBuilder,
                                    Root emissionRoot) {
        if (filterSelected(request, FilterType.YEAR)) {
            where = appendYearFilter(request, criteriaBuilder, emissionRoot, where);
        }

        if (filterSelected(request, FilterType.SECTOR)) {
            where = appendSectorFilter(request, criteriaBuilder, emissionRoot, where);
        }

        if (filterSelected(request, FilterType.COUNTRY)) {
            where = appendCountryFilter(request, criteriaBuilder, emissionRoot, where);
        }

        where

    }


    private Predicate appendYearFilter(FilterDto filter, CriteriaBuilder criteriaBuilder, Root emissionRoot, Predicate where) {

        where = criteriaBuilder.and(where, criteriaBuilder.greaterThan(emissionRoot.get("year"), filter.startDate))
        where = criteriaBuilder.and(where, criteriaBuilder.lessThan(emissionRoot.get("year"), filter.endDate))

        where
    }

    private Predicate appendCountryFilter(FilterDto filter, CriteriaBuilder criteriaBuilder, Root emissionRoot, Predicate where) {
        where = criteriaBuilder.and(where, criteriaBuilder.in(emissionRoot.get("countryName"), filter.countries))
        where
    }

    private Predicate appendSectorFilter(FilterDto filter, CriteriaBuilder criteriaBuilder, Root emissionRoot, Predicate where) {
        where = criteriaBuilder.and(where, criteriaBuilder.in(emissionRoot.get("sector"), filter.sectors))
        where
    }

    Query emissionChartQuery(FilterDto request, AggregationType aggregation) {
        CriteriaBuilder criteriaBuilder = entityManager.criteriaBuilder
        CriteriaQuery<EmissionChartDto> query = criteriaBuilder.createQuery(Emission.class)
        Root<Emission> emissionRoot = query.from(Emission.class)
        Predicate where = criteriaBuilder.conjunction()
        appendChartAggregation(query, emissionRoot, criteriaBuilder, aggregation)
        query.where(appendFilters(where, request, criteriaBuilder, emissionRoot))
        entityManager.createQuery(query)

    }

    private void appendChartAggregation(CriteriaQuery query, Root emissionRoot, CriteriaBuilder criteriaBuilder,
                                        AggregationType aggregation) {
        query.select(criteriaBuilder.construct(EmissionChartDto.class,
                emissionRoot.get(aggregation.columnName),
                criteriaBuilder.sum(emissionRoot.get("emission")))
        )
        query.groupBy(emissionRoot.get(aggregation.columnName))
    }


}
