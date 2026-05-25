package soia.authezat.infra.database.configuration.auditor

import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import soia.authezat.infra.database.configuration.auditor.annotations.Audited

@Component
class AuditArgumentResolver : HandlerMethodArgumentResolver {
    companion object {
        private const val UNKNOWN = "unknown"
    }

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(Audited::class.java)
                && parameter.parameterType == String::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): String {
        return AuditorContextHolder.getAuditor() ?: UNKNOWN;
    }

}