package com.message.viewing.member

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = ["com.message.viewing.member"],
        entityManagerFactoryRef = "memberEntityManagerFactory",
        transactionManagerRef = "memberTransactionManager"
)
class MemberDatabaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.member")
    fun getMemberDataSourceProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @Qualifier("memberDataSource")
    @ConfigurationProperties("spring.datasource.member.hikari")
    fun getMemberDataSource(): HikariDataSource {
        return getMemberDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource::class.java)
                .build()
    }

    @Bean
    @Primary
    @Qualifier("memberEntityManagerFactory")
    fun memberEntityManagerFactory(
            builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean {
        return builder
                .dataSource(getMemberDataSource())
                .packages("com.message.viewing.member")
                .persistenceUnit("memberEntityManager")
                .build()
    }

    @Bean
    @Primary
    @Qualifier("memberTransactionManager")
    fun memberTransactionManager(
            @Qualifier("memberEntityManagerFactory") memberEntityManagerFactory: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
        return JpaTransactionManager(memberEntityManagerFactory.getObject()!!)
    }
}