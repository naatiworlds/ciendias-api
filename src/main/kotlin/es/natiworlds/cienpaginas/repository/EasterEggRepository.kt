package es.natiworlds.cienpaginas.repository

import es.natiworlds.cienpaginas.model.EasterEgg
import es.natiworlds.cienpaginas.model.Level
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EasterEggRepository: JpaRepository<EasterEgg, Long> {
    fun findByEasterEggNumber(easterEggNumber: Int): EasterEgg?
    /*@Query("SELECT e.easterEggNumber FROM EasterEgg e")
    fun findAllEasterEggNumbers(): List<Int>*/
}