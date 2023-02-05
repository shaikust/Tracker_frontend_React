package com.updatedparceltracker.services;

import com.updatedparceltracker.exception.ResourceNotFoundException;
import com.updatedparceltracker.model.Parcel;
import com.updatedparceltracker.model.Tracking;
import com.updatedparceltracker.repository.ParcelRepository;
import com.updatedparceltracker.repository.TrackingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ParcelControllerService {
  final Logger logger= LoggerFactory.getLogger(ParcelControllerService.class);
@Autowired
  private  ParcelRepository parcelRepository;
@Autowired
private  TrackingRepository trackingRepository;
public ResponseEntity<List<Parcel>> getAllParcel() {
  try {
    return  ResponseEntity.ok(parcelRepository.findAll());

  }catch (Exception e){
    return (ResponseEntity<List<Parcel>>) ResponseEntity.badRequest();
  }
}
  public ResponseEntity<String> addParcel(Parcel parcel) {
//      Optional<Parcel> existingParcel = parcelRepository.findById(parcel.getId());
//      if (existingParcel != null) {
//          return new ResponseEntity<String>("Parcel with ID [" + parcel.getId() + "] already exists", HttpStatus.BAD_REQUEST);
//      }
//    try {
//      Parcel parcel1=new Parcel();
//      parcel1.setId(parcel.getId());
//      parcel1.setReceiverLocation(parcel.getSenderLocation());
//      parcel1.setSenderLocation(parcel.getSenderLocation());
//      parcel1.setDeliveryStatus(parcel.getDeliveryStatus());
//      parcel1.setCreatedBy(parcel.getCreatedBy());
//      parcelRepository.save(parcel1);
//      Tracking tracking=new Tracking();
//      tracking.setParcel(parcel);
//      tracking.setCurrentLocation(parcel.getSenderLocation());
//      trackingRepository.save(tracking);
//      logger.info("new parcel added");
//      return new ResponseEntity<String>("["+parcel.getId()+"]"+"parcel added successfully",HttpStatus.OK);
//    }catch (Exception e){
//
//      return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
//    }
//    if(parcelRepository.exitsById(parcel.getId())){
//      logger.error("{} parcelId is already taken",parcel.getId());
//      return new ResponseEntity<>(parcel.getId()+" "+"parcelId  is already taken", HttpStatus.BAD_REQUEST);
//    }
    parcelRepository.save(parcel);
    Tracking tracking=new Tracking();
    tracking.setParcel(parcel);
    tracking.setCurrentLocation(parcel.getSenderLocation());
    trackingRepository.save(tracking);
    logger.info("new parcel added");
    return new ResponseEntity<String>("["+parcel.getId()+"]"+"parcel added successfully",HttpStatus.OK);


  }
  public  ResponseEntity<Tracking> getParcel(Integer trackingId) {
    logger.info("get parcel with given id");

//      return new ResponseEntity<Parcel>(this.parcelRepository.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","id",trackingId)),HttpStatus.FOUND);
    Tracking tracking=this.trackingRepository.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","id",trackingId));
   return ResponseEntity.ok(tracking);
  }
public ResponseEntity<String> updateParcel(Integer trackingId, Parcel parcel) {
  try {

    Tracking tracking1=trackingRepository.findByparcelId(trackingId).orElseThrow(()->new UsernameNotFoundException("Parcel "+parcel.toString()+"with trackingId "+trackingId+"not found"));
    tracking1.setCurrentLocation(parcel.getSenderLocation());
    trackingRepository.save(tracking1);
    Parcel parcel1=parcelRepository.findById(trackingId).orElseThrow(()->new UsernameNotFoundException("Parcel "+parcel.toString()+"with trackingId "+trackingId+"not found"));
    parcel1.setDeliveryStatus(parcel.getDeliveryStatus());
    parcelRepository.save(parcel1);
    logger.info("parcel updated");
    return new ResponseEntity<String>("parcel "+"["+trackingId+"]"+"updated successfully", HttpStatus.OK);
  }catch (Exception e){
    return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);

  }
}
  public ResponseEntity<String> deleteParcel(Integer trackingId) {
    try {
      Tracking tracking=trackingRepository.findByparcelId(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","trackingId",trackingId));
      trackingRepository.delete(tracking);
      Parcel parcel=parcelRepository.findById(trackingId).orElseThrow(()->new ResourceNotFoundException("parcel","trackingId",trackingId));
      parcelRepository.delete(parcel);
      logger.info("parcel deleted with given id");
    return new ResponseEntity<>("parcel "+"["+trackingId+"]"+"deleted successfully",HttpStatus.OK);

    }catch (Exception e){
      return  new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);

    }
  }


}
