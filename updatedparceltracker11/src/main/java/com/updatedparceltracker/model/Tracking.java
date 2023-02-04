package com.updatedparceltracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tracking")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Tracking {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  private String currentLocation;
  @OneToOne
  @JoinColumn(name = "parcel_id")
  private Parcel parcel;
}
