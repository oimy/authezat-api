package soia.authezat.infra.database.configuration.auditor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
class JpaAuditorConfiguration {

    @Bean
    fun auditorAware(): AuditorAware<String> {
        return AuditorAware {
            val auditor: String? = AuditorContextHolder.getAuditor()
            Optional.ofNullable(auditor)
        }
    }

}