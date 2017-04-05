package com.rzodkiewicz.michal.service.implementation

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.dto.EmissionChartDto
import com.rzodkiewicz.michal.dto.FilterDto
import com.rzodkiewicz.michal.repository.EmissionRepository
import com.rzodkiewicz.michal.service.EmissionService
import com.rzodkiewicz.michal.service.QueryBuilderService
import com.rzodkiewicz.michal.util.enums.Sector
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityManager

@Service
class EmissionServiceImpl implements EmissionService {

    private final EmissionRepository emissionRepository
    private final QueryBuilderService queryBuilderService
    private final EntityManager entityManager

    @Autowired
    EmissionServiceImpl(EmissionRepository emissionRepository, QueryBuilderService queryBuilderService,
                        EntityManager entityManager) {
        this.emissionRepository = emissionRepository
        this.queryBuilderService = queryBuilderService
        this.entityManager = entityManager
    }

    @Override
    Set<Emission> fetchFilteredEmission(FilterDto request) {
        def query = queryBuilderService.emissionQuery(request)
        List<Emission> list = query.resultList
        List<EmissionChartDto> charts = queryBuilderService.emissionChartQuery(request).getResultList()
        list
    }

    @Override
    Set<Sector> getSectors() {
        emissionRepository.getSectors()
    }

    @Override
    Set<String> getCountries() {
        emissionRepository.getCountries()
    }

    @Override
    Set<Integer> getYears(){
        emissionRepository.getYears()
    }
}
