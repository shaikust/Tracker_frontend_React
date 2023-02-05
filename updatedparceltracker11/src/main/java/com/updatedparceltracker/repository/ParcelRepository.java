package com.updatedparceltracker.repository;

import com.updatedparceltracker.model.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ParcelRepository extends JpaRepository<Parcel,Integer> {


  public Optional<Parcel> findById(Integer id);
//    Boolean exitsByParcelId(Integer id);

}
