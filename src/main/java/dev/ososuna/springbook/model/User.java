package dev.ososuna.springbook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User extends AbstractModificationAttributesEntity {
  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column(name="profile_image_url")
  private String profileImageUrl;

  @Column(name="role")
  private String role;

  @Column(name="age")
  private int age;
}
