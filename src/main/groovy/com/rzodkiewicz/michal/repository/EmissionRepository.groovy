package com.rzodkiewicz.michal.repository

import com.rzodkiewicz.michal.domain.Emission
import com.rzodkiewicz.michal.util.enums.Sector
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EmissionRepository extends JpaRepository<Emission, Long> {

    @Query("""SELECT DISTINCT e.sector FROM Emission e ORDER BY e.sector """)
    Set<Sector> getSectors()

    @Query(""" SELECT DISTINCT e.countryName FROM Emission e ORDER BY e.countryName """)
    Set<String> getCountries()

    @Query(""" SELECT DISTINCT e.year FROM Emission e ORDER BY e.year""")
    Set<Integer> getYears()
}