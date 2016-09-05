package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.enums.CountryCode
import com.rzodkiewicz.michal.enums.Sector
import com.rzodkiewicz.michal.util.EmissionFilterRequest
import org.springframework.stereotype.Service

@Service
interface EmissionService {

    Set<Emission> fetchFilteredEmission(EmissionFilterRequest request)
    Set<Sector> getSectors()
    Set<String> getCountries()

}