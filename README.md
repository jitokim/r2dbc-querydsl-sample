# R2dbc Querydsl Sample


### Guides

This sample was written for MySQL Database.
If you want to use another database, then rewrite [DataSourceConfig](/src/main/kotlin/com/example/r2dbcquerydslsample/config/DataSourceConfig.kt)

DataSource and SQLTemplates beans must be registered to create a query clause

DatabaseClient bean must be registered to connect to the database,
it's used by SimpleQuerydslR2dbcFragment

Copy dependencies for r2dbc querydsl in
[build.gradle](build.gradle.kts)
```
implementation("com.infobip:infobip-spring-data-jdbc-annotation-processor:6.2.1")
implementation("com.infobip:infobip-spring-data-r2dbc-querydsl-boot-starter:6.2.1")  {
    exclude(group = "com.querydsl", module="querydsl-core")
    exclude(group = "com.querydsl", module="querydsl-apt")
}
implementation("com.querydsl:querydsl-core:5.0.0")
implementation("com.querydsl:querydsl-apt:5.0.0")
kapt("com.querydsl:querydsl-core:5.0.0")
kapt("com.querydsl:querydsl-apt:5.0.0")

```


### Reference Documentation



* [Infobip Spring Data Querydsl](https://github.com/infobip/infobip-spring-data-querydsl)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/2.6.3/reference/html/spring-boot-features.html#boot-features-r2dbc)
* [Querydsl](https://github.com/querydsl/querydsl)
