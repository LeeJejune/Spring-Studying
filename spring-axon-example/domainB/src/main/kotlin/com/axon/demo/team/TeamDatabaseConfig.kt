package com.message.viewing.team

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = ["com.message.viewing.team"],
        entityManagerFactoryRef = "teamEntityManagerFactory",
        transactionManagerRef = "teamTransactionManager"
)
class TeamDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.team")
    fun getTeamDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Qualifier("teamDataSource")
    @ConfigurationProperties("spring.datasource.team.hikari")
    fun getTeamDataSource(): HikariDataSource {
        return getTeamDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource::class.java)
                .build()
    }

    @Bean
    @Qualifier("teamEntityManagerFactory")
    fun teamEntityManagerFactory(
            builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(getTeamDataSource())
                .packages("com.message.viewing.team")
                .persistenceUnit("teamEntityManager")
                .build()
    }

    @Bean
    @Qualifier("teamTransactionManager")
    fun teamTransactionManager(
            @Qualifier("teamEntityManagerFactory") teamEntityManagerFactory: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
        return JpaTransactionManager(teamEntityManagerFactory.getObject()!!)
    }
}