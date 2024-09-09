package es.natiworlds.cienpaginas.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

	override fun addCorsMappings(registry: CorsRegistry) {
		registry.addMapping("/**")  // Permite todas las rutas
			.allowedOrigins("https://cienpaginas.netlify.app")  // Permitir solo tu frontend
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
			.allowedHeaders("*")  // Permitir todos los encabezados
			.allowCredentials(true)  // Permitir cookies o autenticación basada en sesión
	}
}
