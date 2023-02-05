package com.updatedparceltracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parcel",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"parcelId"})})
public class Parcel {
  @Id
    @Column(name = "parcelId")
  private Integer id;
  private String senderLocation;
  private String receiverLocation;
  private String createdBy;
  private String deliveryStatus;
//  @OneToOne(mappedBy = "parcel")
//  private Tracking tracking;


//  @OneToOne(cascade = CascadeType.ALL)
//  @JoinColumn(name = "created_By",referencedColumnName = "id")
//  private User user;



}
