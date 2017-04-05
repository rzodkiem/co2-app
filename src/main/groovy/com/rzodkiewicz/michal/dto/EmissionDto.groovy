package com.rzodkiewicz.michal.dto

import com.rzodkiewicz.michal.domain.Emission

class EmissionDto {

    Set<Emission> tableData = new ArrayList<>()
    Set<EmissionChartDto> countryChartData = new ArrayList<>()
    Set<EmissionChartDto> categoryChartDto = new ArrayList<>()
}
