package com.tat.bookshelf.repos;

import com.tat.bookshelf.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepo extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByReadAlready(boolean read);
    List<Book> findByPrintYearAfter(int year);
    List<Book> findByPrintYearBefore(int year);
}
