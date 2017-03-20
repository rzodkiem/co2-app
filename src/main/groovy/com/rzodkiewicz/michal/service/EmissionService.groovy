package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.util.enums.Sector
import com.rzodkiewicz.michal.dto.EmissionFilterRequest
import org.springframework.stereotype.Service

@Service
interface EmissionService {

    Set<Emission> fetchFilteredEmission(EmissionFilterRequest request)
    Set<Sector> getSectors()
    Set<String> getCountries()
    Set<Integer> getYears()

}