package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.dto.FilterDto
import com.rzodkiewicz.michal.util.enums.AggregationType

import javax.persistence.Query

interface QueryBuilderService {

    Query emissionQuery(FilterDto request)

    Query emissionChartQuery(FilterDto request, AggregationType aggregation)

}