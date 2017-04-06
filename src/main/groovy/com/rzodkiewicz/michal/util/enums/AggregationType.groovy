package com.rzodkiewicz.michal.util.enums


enum AggregationType {
    COUNTRY("countryName"),
    CATEGORY("sector")

    String columnName

    AggregationType(String columnName) {
        this.columnName = columnName
    }

}