package com.rzodkiewicz.michal.repository

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.util.enums.Sector
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface EmissionRepository extends JpaRepository<Emission, Long> {

    @Query("""SELECT DISTINCT e.sector FROM Emission e ORDER BY e.sector """)
    Set<Sector> getSectors()

    @Query(""" SELECT DISTINCT e.countryName FROM Emission e ORDER BY e.countryName """)
    Set<String> getCountries()

    @Query(""" SELECT e FROM Emission e WHERE e.countryCode in (:codes) ORDER BY e.year""")
    Set<Emission> findAllByCountries(@Param('codes') Collection<String> codes)

    @Query(""" SELECT e FROM Emission e WHERE e.sector IN (:sectors) ORDER BY e.year""")
    Set<Emission> findAllBySectors(@Param('sectors') Collection<String> sectors)

    @Query(""" SELECT e FROM Emission e WHERE e.year BETWEEN (:startDate) AND (:endDate) ORDER BY e.year """)
    Set<Emission> findAllByDateRange(@Param('startDate') Integer startDate, @Param('endDate') Integer endDate)

    @Query(""" SELECT e FROM Emission e WHERE e.sector IN (:sectors) AND e.countryCode in (:codes) ORDER BY e.year """)
    Set<Emission> findAllBySectorsAndCountries(@Param('sectors') Collection<String> sectors, @Param('codes') Collection<String> codes)

    @Query(""" SELECT e FROM Emission e WHERE e.countryCode in (:codes) AND e.year BETWEEN (:startDate) AND (:endDate) ORDER BY e.year""")
    Set<Emission> findAllByCountriesAndDateRange(@Param('codes')Collection<String> codes, @Param('startDate') Integer startDate, @Param('endDate') Integer endDate)

    @Query(""" SELECT e FROM Emission e WHERE e.sector IN (:sectors) AND e.year BETWEEN (:startDate) AND (:endDate) ORDER BY e.year""")
    Set<Emission> findAllBySectorsAndDateRange(@Param('sectors') Collection<String> sectors, @Param('startDate') Integer startDate, @Param('endDate') Integer endDate)

    @Query(""" SELECT e FROM Emission e WHERE e.countryCode IN (:codes) AND e.sector IN (:sectors) AND e.year BETWEEN (:startDate) AND (:endDate) ORDER BY e.year""")
    Set<Emission> findAllByCountriesAndSectorsAndDateRange(@Param('codes') Collection<String> codes, @Param('sectors') Collection<String> sectors,
                                                           @Param('startDate') Integer startDate, @Param('endDate') Integer endDate)
}