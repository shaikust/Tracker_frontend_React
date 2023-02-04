package com.updatedparceltracker.repository;

import com.updatedparceltracker.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelRepository extends JpaRepository<Parcel,Integer> {
  public List<Parcel> findByTrackingId(Integer trackingId);

}
