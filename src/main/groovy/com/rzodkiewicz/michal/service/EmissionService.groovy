package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.dto.EmissionDto
import com.rzodkiewicz.michal.dto.FilterDto
import com.rzodkiewicz.michal.util.enums.Sector
import org.springframework.stereotype.Service

@Service
interface EmissionService {

    EmissionDto fetchFilteredEmission(FilterDto request)
    Set<Sector> getSectors()
    Set<String> getCountries()
    Set<Integer> getYears()

}