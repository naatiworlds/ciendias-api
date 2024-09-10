package es.natiworlds.cienpaginas.config

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestInterceptor : HandlerInterceptor {

	private val logger = LoggerFactory.getLogger(RequestInterceptor::class.java)

	override fun preHandle(
		request: jakarta.servlet.http.HttpServletRequest,
		response: jakarta.servlet.http.HttpServletResponse,
		handler: Any
	): Boolean
	{
		logger.info("Incoming request: ${request.method} ${request.requestURI}")
		logger.info("Headers: ${request.headerNames.toList().associateWith { request.getHeaders(it).toList() }}")
		return true
	}
}