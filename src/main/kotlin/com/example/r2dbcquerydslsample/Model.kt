package com.example.r2dbcquerydslsample

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository : QuerydslR2dbcRepository<Sample, Long>

@Table("sample")
class Sample(
    @Id
    @Column("id")
    var id : Long? = null,
    @Column("title")
    var title: String? = null
)

@Repository
interface ChildRepository : QuerydslR2dbcRepository<Child, Long>

@Table("child")
class Child(
    @Id
    @Column("id")
    var id: Long? = null,
    @Column("sample_id")
    var sampleId: Long,
    @Column("title")
    var title: String? = null
)

