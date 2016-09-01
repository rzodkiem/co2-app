package com.rzodkiewicz.michal.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id

    String countryCode

    String countryName

    String sector

    int year

    double emission

}
