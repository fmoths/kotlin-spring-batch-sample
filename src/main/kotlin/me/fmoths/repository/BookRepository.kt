package me.fmoths.repository

import me.fmoths.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface BookRepository : JpaRepository<Book, Long>