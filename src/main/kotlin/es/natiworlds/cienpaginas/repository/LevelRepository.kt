package es.natiworlds.cienpaginas.repository

import es.natiworlds.cienpaginas.model.Level
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LevelRepository : JpaRepository<Level, Long>
{
	fun findByLevelNumber(levelNumber: Int): Level?
}
