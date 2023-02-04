package com.updatedparceltracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "user",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String email;
  private String name;
  private Integer phone;
  private String password;
  @ManyToOne
  @JoinColumn(name = "role_id")
  private Roles roles;

//  @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//  private List<Parcel> parcels;

}
