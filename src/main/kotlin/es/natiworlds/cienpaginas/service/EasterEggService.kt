package es.natiworlds.cienpaginas.service

import es.natiworlds.cienpaginas.model.EasterEgg
import es.natiworlds.cienpaginas.repository.EasterEggRepository
import org.springframework.stereotype.Service

@Service
class EasterEggService(
    private val easterEggRepository: EasterEggRepository
) {
    fun getAllEasterEggs(): MutableList<EasterEgg>
    {
        return easterEggRepository.findAll()
    }
    fun getEasterEgg(easterEggNumber: Int): EasterEgg? {
        return easterEggRepository.findByEasterEggNumber(easterEggNumber)
    }
    fun saveEasterEgg(easterEgg: EasterEgg) :EasterEgg{
        return easterEggRepository.save(easterEgg)
    }
    fun validateEasterEgg(word: String, easterEggNumber: Int): Boolean {
        val easterEgg = getEasterEgg(easterEggNumber)
        return easterEgg?.easterEggWorld?.equals(word, ignoreCase = true) == true
    }
    fun unlockNextEasterEgg(currentEasterEggNumber: Int) {
        val easterEggNumber = currentEasterEggNumber
        val nextLevel = getEasterEgg(easterEggNumber)
        nextLevel?.let {
            it.isComplete = true
            easterEggRepository.save(it)
        }
    }
    fun deleteEasterEggById(id: Long){
        return easterEggRepository.deleteById(id)
    }
}
