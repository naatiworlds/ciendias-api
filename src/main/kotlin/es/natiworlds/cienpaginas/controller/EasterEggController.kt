package es.natiworlds.cienpaginas.controller

import es.natiworlds.cienpaginas.model.EasterEgg
import es.natiworlds.cienpaginas.service.EasterEggService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:8081"]) // Permitir el origen del frontend
@RequestMapping("/api/easteregg")
class EasterEggController @Autowired constructor(
	private val easterEggService: EasterEggService
) {
	@GetMapping
	fun getAllEasterEggs(): ResponseEntity<List<EasterEgg>> {
		val easterEggs = easterEggService.getAllEasterEggs()
		return ResponseEntity.ok(easterEggs)
	}

	@PostMapping("/load")
	fun loadEasterEgg(@RequestBody easterEgg: EasterEgg): ResponseEntity<EasterEgg> {
		val savedEasterEgg = easterEggService.saveEasterEgg(easterEgg)
		return ResponseEntity.ok(savedEasterEgg)
	}

	@PostMapping("/validate")
	fun validateEasterEgg(
		@RequestParam word: String,
		@RequestParam easterEggNumber: Int
	): ResponseEntity<String> {
		val isValid = easterEggService.validateEasterEgg(word, easterEggNumber)
		return if (isValid) {
			ResponseEntity.ok("¡WOW! Has encontrado el easter egg número $easterEggNumber.")
		} else {
			ResponseEntity.badRequest().body("No has encontrado ningún easter egg, lo siento.")
		}
	}

	@DeleteMapping("/remove/{id}")
	fun removeEasterEgg(@PathVariable id: Long): ResponseEntity<String> {
		return try {
			easterEggService.deleteEasterEggById(id)
			ResponseEntity("Easter egg eliminado correctamente.", HttpStatus.OK)
		} catch (e: Exception) {
			ResponseEntity("Error al eliminar el easter egg: ${e.message}", HttpStatus.INTERNAL_SERVER_ERROR)
		}
	}
}
