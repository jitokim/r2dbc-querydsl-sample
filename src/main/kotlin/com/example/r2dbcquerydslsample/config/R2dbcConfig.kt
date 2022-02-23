package com.example.r2dbcquerydslsample.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.Option
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
class R2dbcConfig(
    private val r2dbcProperties: R2dbcProperties
) : AbstractR2dbcConfiguration() {

    override fun connectionFactory(): ConnectionFactory {
        return r2dbcProperties.let {
            ConnectionFactoryOptions.parse(it.url).mutate()
                .let { builder ->
                    builder.option(ConnectionFactoryOptions.USER, it.username)
                    builder.option(ConnectionFactoryOptions.PASSWORD, it.password)
                    builder.option(ConnectionFactoryOptions.DATABASE, it.name)
                    it.properties.forEach { (k, v) ->
                        builder.option(Option.valueOf(k), v)
                    }
                    ConnectionFactories.get(builder.build())
                }
        }
    }

    @Bean
    fun transactionManager(): R2dbcTransactionManager {
        return R2dbcTransactionManager(connectionFactory())
    }

    @Bean
    fun initializer(): ConnectionFactoryInitializer {
        return ConnectionFactoryInitializer().apply {
            setConnectionFactory(connectionFactory())
        }
    }

    @Bean
    override fun databaseClient(): DatabaseClient {
        return DatabaseClient.create(connectionFactory())
    }


}