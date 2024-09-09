package es.natiworlds.cienpaginas.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class EasterEgg (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val easterEggNumber: Int = 0,
    val easterEggWorld: String = "",
    val reward: String = "",
    var isComplete: Boolean = false
)