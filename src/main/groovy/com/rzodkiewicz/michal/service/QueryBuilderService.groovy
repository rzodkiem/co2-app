package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.dto.FilterDto

import javax.persistence.Query

interface QueryBuilderService {

    Query emissionQuery(FilterDto request)

    Query emissionChartQuery(FilterDto request)

}