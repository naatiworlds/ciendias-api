package es.natiworlds.cienpaginas.config

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {

	override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
		val response = res as HttpServletResponse
		val request = req as HttpServletRequest

		response.setHeader("Access-Control-Allow-Origin", "https://ciendias-web.onrender.com")
		response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS")
		response.setHeader("Access-Control-Allow-Headers", "*")
		response.setHeader("Access-Control-Allow-Credentials", "true")
		response.setHeader("Access-Control-Max-Age", "180")

		if ("OPTIONS".equals(request.method, ignoreCase = true)) {
			response.status = HttpServletResponse.SC_OK
		} else {
			chain.doFilter(req, res)
		}
	}

	override fun init(filterConfig: FilterConfig?) {}

	override fun destroy() {}
}