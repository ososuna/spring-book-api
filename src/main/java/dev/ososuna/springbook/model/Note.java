package dev.ososuna.springbook.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="note")
public class Note extends AbstractNamedEntity {

  @Column
  private String description;
  
  @Column
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name="book_id")
  private Book book;

}
