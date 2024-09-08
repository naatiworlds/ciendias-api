package es.natiworlds.cienpaginas.service

import es.natiworlds.cienpaginas.model.EasterEgg
import es.natiworlds.cienpaginas.model.Level
import es.natiworlds.cienpaginas.repository.LevelRepository
import org.springframework.stereotype.Service

@Service
class LevelService(
	private val levelRepository: LevelRepository
) {

	fun getAllLevels(): MutableList<Level>
	{
		return levelRepository.findAll()
	}
	fun getLevel(levelNumber: Int): Level? {
		return levelRepository.findByLevelNumber(levelNumber)
	}

	fun validateLevel(word: String, levelNumber: Int): Boolean {
		val level = getLevel(levelNumber)
		return level?.correctWord?.equals(word, ignoreCase = true) == true
	}
	fun saveLevel(level: Level): Level {
		return levelRepository.save(level)
	}

	fun unlockNextLevel(currentLevelNumber: Int) {
		val nextLevelNumber = currentLevelNumber
		val nextLevel = getLevel(nextLevelNumber)
		nextLevel?.let {
			it.isCompleted = true
			levelRepository.save(it)
		}
	}
	fun deleteEasterEggById(id: Long){
		levelRepository.deleteById(id)
	}
}
