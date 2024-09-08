package es.natiworlds.cienpaginas.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Level(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long = 0,
	val levelNumber: Int = 0,
	val correctWord: String = "",
	val reward: String = "",
	var isCompleted: Boolean = false
)

