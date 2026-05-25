package soia.authezat.infra.database.configuration.auditor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import soia.authezat.infra.database.configuration.auditor.annotations.AuditAccessedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditDeletedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditModifiedBy
import kotlin.reflect.KClass

@Aspect
@Component
class AuditWebInterceptor : HandlerInterceptor {
    companion object {
        private val AUDIT_ANNOTATION_CLASSES: Set<KClass<out Annotation>> = setOf(
            AuditModifiedBy::class,
            AuditCreatedBy::class,
            AuditAccessedBy::class,
            AuditDeletedBy::class
        )
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) return true

        for (annotationClass: KClass<out Annotation> in AUDIT_ANNOTATION_CLASSES) {
            val annotation: Annotation = handler.getMethodAnnotation(annotationClass.java) ?: continue
            val headerName: String = getAnnotationProperty(annotation, "headerName") as String
            val required: Boolean = getAnnotationProperty(annotation, "required") as Boolean
            this.setAuditor(headerName, required)
        }
        return true
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        exception: Exception?,
    ) {
        AuditorContextHolder.clear()
    }

    private fun getAnnotationProperty(annotation: Annotation, propertyName: String): Any {
        return annotation.annotationClass.java.getMethod(propertyName).invoke(annotation)
            ?: ""
    }

    private fun setAuditor(headerName: String, required: Boolean) {
        require(headerName.isNotBlank()) { "headerName cannot be blank" }

        val request: HttpServletRequest = getServletRequest()
        val createdBy = request.getAuditedBy(headerName = headerName, required = required)
        AuditorContextHolder.setAuditor(auditor = createdBy)
    }

    private fun HttpServletRequest.getAuditedBy(headerName: String, required: Boolean): String =
        this.getHeader(headerName)
            .let {
                if (it.isNullOrBlank()) {
                    require(required.not()) { "auditor required" }
                    return ""
                }
                return it
            }

    private fun getServletRequest(): HttpServletRequest =
        (RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes)?.request
            ?: throw IllegalStateException("not found servlet request")

}