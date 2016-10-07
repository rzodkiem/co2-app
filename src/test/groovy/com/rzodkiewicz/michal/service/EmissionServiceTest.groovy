package com.rzodkiewicz.michal.service

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.enums.EmissionFilterType
import com.rzodkiewicz.michal.util.EmissionFilterRequest
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Assert

import javax.persistence.EntityManager

@RunWith(SpringRunner)
@SpringBootTest
class EmissionServiceTest {

    /*
    Records numbers for each filter with values same as in test counted with direct query on the emission table
     */
    private final int COUNTRY_RECORDS_COUNT = 25256
    private final int SECTOR_RECORDS_COUNT = 3280
    private final int TIME_RECORDS_COUNT = 96075
    private final int COUNTRY_SECTOR_RECORDS_COUNT = 184
    private final int COUNTRY_TIME_RECORDS_COUNT = 5490
    private final int SECTORS_TIME_RECORDS_COUNT = 700

    @Autowired
    private EmissionService emissionService

    @Autowired
    EntityManager entityManager


    List<String> countries = ['AT', 'BE']
    List<String> sectors = ['1. Energy']
    Integer startDate = 1995
    Integer endDate = 1999
    Set<Emission> response = []

    EmissionFilterRequest request = new EmissionFilterRequest(countries: countries, sectors: sectors, startDate: startDate, endDate: endDate)

    @Test
    void testCanGetByCountry(){
        request.type = EmissionFilterType.COUNTRY
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == COUNTRY_RECORDS_COUNT)
    }

    @Test
    void testCanGetBySectors(){
        request.type = EmissionFilterType.SECTOR
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == SECTOR_RECORDS_COUNT)
    }

    @Test
    void testCanGetByDateRange(){
        request.type = EmissionFilterType.TIME
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == TIME_RECORDS_COUNT)
    }

    @Test
    void testCanGetByCountriesAndSectors(){
        entityManager.
        request.type= EmissionFilterType.COUNTRY_SECTOR
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == COUNTRY_SECTOR_RECORDS_COUNT)
    }

    @Test
    void testCanGetByCountriesAndDateRange(){
        request.type = EmissionFilterType.COUNTRY_TIME
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == COUNTRY_TIME_RECORDS_COUNT)
    }

    @Test
    void canGetBySectorsAndDateRange(){
        request.type = EmissionFilterType.SECTOR_TIME
        response = emissionService.fetchFilteredEmission(request)
        Assert.isTrue(response.size() == SECTORS_TIME_RECORDS_COUNT)
    }
}
