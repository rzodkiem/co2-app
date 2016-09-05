package com.rzodkiewicz.michal.util

import com.rzodkiewicz.michal.enums.CountryCode
import com.rzodkiewicz.michal.enums.EmissionFilterType
import com.rzodkiewicz.michal.enums.Sector

class EmissionFilterRequest {

    Integer startDate
    Integer endDate
    List<CountryCode> countries
    List<Sector> sectors
    EmissionFilterType type

}
