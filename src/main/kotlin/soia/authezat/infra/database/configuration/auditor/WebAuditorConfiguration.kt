package soia.authezat.infra.database.configuration.auditor

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebAuditorConfiguration(
    private val auditWebInterceptor: AuditWebInterceptor,
    private val auditArgumentResolver: AuditArgumentResolver,
) :
    WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(auditWebInterceptor).addPathPatterns("/**")
        super.addInterceptors(registry)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(auditArgumentResolver)
        super.addArgumentResolvers(resolvers)
    }

}