package dev.ososuna.mylibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ososuna.mylibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByIdAndActiveTrue(Long id);
  List<Book> findAllByActiveTrue();
}
