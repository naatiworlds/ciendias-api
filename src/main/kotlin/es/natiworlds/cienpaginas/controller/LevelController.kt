package es.natiworlds.cienpaginas.controller

import es.natiworlds.cienpaginas.model.EasterEgg
import es.natiworlds.cienpaginas.model.Level
import es.natiworlds.cienpaginas.service.LevelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])
@RequestMapping("/api/levels")
class LevelController(
	private val levelService: LevelService
) {
	@GetMapping
	@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])
	fun getAllEasterEggs(): ResponseEntity<List<Level>> {
		val levels = levelService.getAllLevels()
		return ResponseEntity.ok(levels)
	}

	@PostMapping("/validate")
	@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])

	fun validateLevel(
		@RequestParam word: String,
		@RequestParam levelNumber: Int
	): ResponseEntity<String> {
		val isValid = levelService.validateLevel(word, levelNumber)
		return if (isValid) {
			levelService.unlockNextLevel(levelNumber)
			ResponseEntity.ok("¡Correcto! Nivel ${levelNumber + 1} desbloqueado.")
		} else {
			ResponseEntity.badRequest().body("Palabra incorrecta, intenta de nuevo.")
		}
	}
	@PostMapping("/load")
	@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])

	fun addLevel(@RequestBody level: Level): ResponseEntity<Level> {
		val savedLevel = levelService.saveLevel(level)
		return ResponseEntity.ok(savedLevel)
	}

	@GetMapping("/{levelNumber}")
	@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])

	fun getLevel(@PathVariable levelNumber: Int): ResponseEntity<Level> {
		val level = levelService.getLevel(levelNumber)
		return if (level != null) {
			ResponseEntity.ok(level)
		} else {
			ResponseEntity.notFound().build()
		}
	}
	@DeleteMapping("/remove/{id}")
	@CrossOrigin(origins = ["https://ciendias-web.onrender.com"])

	fun removeEasterEgg(@PathVariable id: Long): ResponseEntity<String> {
		return try {
			levelService.deleteEasterEggById(id)
			ResponseEntity("El nivel ${id} ha sido eliminado correctamente.", HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error al eliminar el nivel: ${e.message}", HttpStatus
				.INTERNAL_SERVER_ERROR)
		}
	}
}
