package es.natiworlds.cienpaginas.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig {

	@Bean
	fun corsConfigurer() = object : WebMvcConfigurer {
		override fun addCorsMappings(registry: CorsRegistry) {
			registry.addMapping("/**")
				.allowedOrigins("https://ciendias-web.onrender.com") // Permite solicitudes desde este origen
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
				.allowedHeaders("*") // Permite todos los headers
				.allowCredentials(true) // Permite enviar cookies en solicitudes cross-origin
		}
	}
}