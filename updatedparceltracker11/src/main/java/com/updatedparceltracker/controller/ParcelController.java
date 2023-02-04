package com.updatedparceltracker.controller;

import com.updatedparceltracker.model.Parcel;
import com.updatedparceltracker.model.Tracking;
import com.updatedparceltracker.services.ParcelControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-user-controller")
public class ParcelController {
@Autowired
private ParcelControllerService authControllerService;
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("parcel")
  public ResponseEntity<List<Parcel>> getAllParcel(){
    return authControllerService.getAllParcel();
}

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("parcel")
  public ResponseEntity<String> addParcel(@RequestBody Parcel parcel){
    return authControllerService.addParcel(parcel);
  }

  @GetMapping("parcel/{id}")
  public  ResponseEntity<Tracking> getParcel(@PathVariable Integer id){

    return authControllerService.getParcel(id);
  }
  @PreAuthorize("hasRole('ADMIN')")
  @PutMapping("parcel/{id}")
  public ResponseEntity<String> updateParcel(@PathVariable Integer id,@RequestBody Parcel parcel){
    return  authControllerService.updateParcel(id,parcel);

  }
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("parcel/{id}")
  public ResponseEntity<String> deleteParcel(@PathVariable Integer id){
    return authControllerService.deleteParcel(id);
  }


}
