package com.rzodkiewicz.michal.service.implementation

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.enums.EmissionFilterType
import com.rzodkiewicz.michal.enums.Sector
import com.rzodkiewicz.michal.repository.EmissionRepository
import com.rzodkiewicz.michal.service.EmissionService
import com.rzodkiewicz.michal.util.EmissionFilterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmissionServiceImpl implements EmissionService {

    private final EmissionRepository emissionRepository

    @Autowired
    EmissionServiceImpl(EmissionRepository emissionRepository){
        this.emissionRepository = emissionRepository
    }

    @Override
    Set<Emission> fetchFilteredEmission(EmissionFilterRequest request) {
        Set<Emission> response = []
        switch(request.type){
            case EmissionFilterType.COUNTRY:
                response = emissionRepository.findAllByCountries(request.countries.collect{it.toString()})
                break
            case EmissionFilterType.SECTOR:
                response = emissionRepository.findAllBySectors(request.sectors.collect{it.toString()})
                break
            case EmissionFilterType.TIME:
                response = emissionRepository.findAllByDateRange(request.startDate, request.endDate)
                break
            case EmissionFilterType.COUNTRY_SECTOR:
                response = emissionRepository.findAllBySectorsAndCountries(request.sectors.collect{it.toString()}, request.countries.collect{it.toString()})
                break
            case EmissionFilterType.COUNTRY_TIME:
                response = emissionRepository.findAllByCountriesAndDateRange(request.countries.collect{it.toString()}, request.startDate, request.endDate)
                break
            case EmissionFilterType.SECTOR_TIME:
                response = emissionRepository.findAllBySectorsAndDateRange(request.sectors.collect{it.toString()}, request.startDate, request.endDate)
                break
            case EmissionFilterType.COUNTRY_SECTOR_TIME:
                response = emissionRepository.findAllByCountriesAndSectorsAndDateRange(request.countries.collect{it.toString()}, request.sectors.collect{it.toString()},
                                                                                        request.startDate, request.endDate)
                break
        }

        response
    }

    @Override
    Set<Sector> getSectors() {
        emissionRepository.getSectors()
    }

    @Override
    Set<String> getCountries() {
        emissionRepository.getCountries()
    }
}
