package com.example.r2dbcquerydslsample.config

import com.querydsl.sql.MySQLTemplates
import com.querydsl.sql.SQLTemplates
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun datasource(properties: DataSourceProperties): DataSource {
        val builder = DataSourceBuilder.create()
        builder.driverClassName(properties.driverClassName)
        builder.url(properties.url)
        builder.username(properties.username)
        builder.password(properties.password)
        builder.type(SimpleDriverDataSource::class.java)
        return builder.build()
    }

    @Bean
    fun sqlTemplates(): SQLTemplates {
        return MySQLTemplates()
    }
}