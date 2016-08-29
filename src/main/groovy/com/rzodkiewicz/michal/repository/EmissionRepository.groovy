package com.rzodkiewicz.michal.repository

import com.rzodkiewicz.michal.domain.Emission
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmissionRepository extends JpaRepository<Emission, Long> {

}