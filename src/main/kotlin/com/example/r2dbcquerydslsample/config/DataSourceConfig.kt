package com.example.r2dbcquerydslsample.config

import com.querydsl.sql.MySQLTemplates
import com.querydsl.sql.SQLTemplates
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    @Bean
    fun datasource(r2dbcProperties: R2dbcProperties): DataSource {
        val builder = DataSourceBuilder.create()
        builder.driverClassName(r2dbcProperties.properties.getOrDefault("driver-class-name", "com.mysql.cj.jdbc.Driver"))
        builder.url(r2dbcProperties.url)
        builder.username(r2dbcProperties.username)
        builder.password(r2dbcProperties.password)
        builder.type(SimpleDriverDataSource::class.java)
        return builder.build()
    }

    @Bean
    fun sqlTemplates(): SQLTemplates {
        return MySQLTemplates()
    }
}