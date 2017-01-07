package com.rzodkiewicz.michal.dto

import com.rzodkiewicz.michal.util.enums.CountryCode
import com.rzodkiewicz.michal.util.enums.EmissionFilterType
import com.rzodkiewicz.michal.util.enums.Sector

class EmissionFilterRequest {

    Integer startDate
    Integer endDate
    List<CountryCode> countries
    List<Sector> sectors
    EmissionFilterType type

}
