package com.updatedparceltracker.repository;

import com.updatedparceltracker.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackingRepository extends JpaRepository <Tracking,Integer>{
  Optional<Tracking> findByparcelId(Integer parcelId);

}
