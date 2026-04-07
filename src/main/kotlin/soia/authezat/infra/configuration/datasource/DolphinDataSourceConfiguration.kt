package soia.authezat.infra.configuration.datasource

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["soia.authezat.infra.database.dolphin"])
class DolphinDataSourceConfiguration