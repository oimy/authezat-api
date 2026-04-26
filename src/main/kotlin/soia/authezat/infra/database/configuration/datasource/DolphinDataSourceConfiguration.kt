package soia.authezat.infra.database.configuration.datasource

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import soia.authezat.infra.database.dolphin.DolphinAccess

@Configuration
@EnableJpaRepositories(basePackageClasses = [DolphinAccess::class])
class DolphinDataSourceConfiguration