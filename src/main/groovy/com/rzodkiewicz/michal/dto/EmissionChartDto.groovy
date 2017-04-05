package com.rzodkiewicz.michal.dto

class EmissionChartDto {

    String label
    double emission

    public EmissionChartDto(String label, double emission) {
        this.label = label
        this.emission = emission
    }
}
