package com.example.r2dbcquerydslsample.config

import com.infobip.spring.data.common.QuerydslSqlQueryConfiguration
import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepositoryFactoryBean
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.repository.Repository
import java.io.Serializable


open class CustomQuerydslR2dbcRepositoryFactoryBean<T : Repository<S, ID>?, S, ID : Serializable?> constructor(
        repositoryInterface: Class<out T>?
) : QuerydslR2dbcRepositoryFactoryBean<T, S, ID>(repositoryInterface!!)


@Configuration
@AutoConfigureAfter(DataSourceConfig::class)
@Import(QuerydslSqlQueryConfiguration::class)
@EnableR2dbcRepositories(
        basePackages = ["com.example.r2dbcquerydslsample"],
        repositoryFactoryBeanClass = CustomQuerydslR2dbcRepositoryFactoryBean::class
)
class QuerydslConfig