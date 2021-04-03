package me.fmoths.entity

import javax.persistence.*
import java.time.LocalDate

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성을 데이터베이스에 위임
    val id: Long?= null
    var bookName: String ?= null

    @Temporal(TemporalType.DATE)
    var created: LocalDate ?= LocalDate.now()
}