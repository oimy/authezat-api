package soia.authezat.infra.database.configuration.auditor

import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import soia.authezat.infra.database.configuration.auditor.annotations.AuditAccessedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditCreatedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditDeletedBy
import soia.authezat.infra.database.configuration.auditor.annotations.AuditModifiedBy
import java.util.*

@Aspect
@Component
class AuditorAspect {

    @Around("@annotation(auditAnnotation)")
    fun handledControllerAuditCreatedBy(joinPoint: ProceedingJoinPoint, auditAnnotation: AuditCreatedBy) =
        setAuditor(joinPoint = joinPoint, headerName = auditAnnotation.headerName, required = auditAnnotation.required)

    @Around("@annotation(auditAnnotation)")
    fun handledControllerAuditCreatedBy(joinPoint: ProceedingJoinPoint, auditAnnotation: AuditModifiedBy) =
        setAuditor(joinPoint = joinPoint, headerName = auditAnnotation.headerName, required = auditAnnotation.required)

    @Around("@annotation(auditAnnotation)")
    fun handledControllerAuditCreatedBy(joinPoint: ProceedingJoinPoint, auditAnnotation: AuditAccessedBy) =
        setAuditor(joinPoint = joinPoint, headerName = auditAnnotation.headerName, required = auditAnnotation.required)

    @Around("@annotation(auditAnnotation)")
    fun handledControllerAuditCreatedBy(joinPoint: ProceedingJoinPoint, auditAnnotation: AuditDeletedBy) =
        setAuditor(joinPoint = joinPoint, headerName = auditAnnotation.headerName, required = auditAnnotation.required)

    private fun setAuditor(joinPoint: ProceedingJoinPoint, headerName: String, required: Boolean): Any? {
        require(headerName.isNotBlank()) { "headerName cannot be blank" }

        val request: HttpServletRequest = getServletRequest()
        val auditedBy: String = request.getAuditedBy(headerName = headerName, required = required)
        val auditedUuid: UUID = UUID.fromString(auditedBy)
        try {
            AuditorContextHolder.setAuditor(auditor = auditedUuid)
            return joinPoint.proceed()
        } finally {
            AuditorContextHolder.clear()
        }
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