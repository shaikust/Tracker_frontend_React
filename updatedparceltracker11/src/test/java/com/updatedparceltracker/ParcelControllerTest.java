package com.updatedparceltracker;

import com.updatedparceltracker.model.Parcel;
import com.updatedparceltracker.model.User;
import com.updatedparceltracker.repository.ParcelRepository;
import com.updatedparceltracker.services.ParcelControllerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ParcelControllerTest.class})
public class ParcelControllerTest {
  @Mock
  private ParcelRepository parcelRepository;
  @InjectMocks
  private ParcelControllerService controllerService;

  public List<Parcel> parcels=new ArrayList<>();
  public Set<User> users;


//  @Test
//  public void test_addParcel() {
//    Parcel parcel = new Parcel(1, "klm", "ekm", "pkd", "notdeliverd", (User) users);
//    when(parcelRepository.save(parcel)).thenReturn(null);
//    assertEquals(ResponseEntity.ok(String.format("[" + parcel.getTrackingId() + "]" + "parcel added successfully")), controllerService.addParcel(parcel));
//
//
//  }
//  @Test
//  public void test_parcel_list(){
//    parcels.add(new Parcel(1,"klm","tvm","klm","no", (User) users));
//    when(parcelRepository.findAll()).thenReturn(parcels);
//    assertEquals(parcels,controllerService.getAllParcel().getBody());
//
//
//  }
//  @Test
//  public void test_delete_parcel(){
//    Optional<Parcel> parcel=Optional.of(new Parcel(1,"klm","tvm","klm","no", (User) users));
//    when(parcelRepository.findById(parcel.get().getTrackingId())).thenReturn(parcel);
//    doNothing().when(parcelRepository).delete(parcel.get());
//    assertEquals(ResponseEntity.ok(String.format("deleted successfully")),controllerService.deleteParcel(parcel.get().getTrackingId()));
//
//  }
//
//
//
//  @Test
//  public void test_update(){
//    Optional<Parcel> parcel=Optional.of(new Parcel(1,"klm","tvm","klm","no", (User) users));
//    Parcel parcel1 = new Parcel(1, "klm", "ekm", "pkd", "notdeliverd", (User) users);
//    when(parcelRepository.findById(parcel.get().getTrackingId())).thenReturn(parcel);
//    when(parcelRepository.save(parcel1)).thenReturn(null);
//    assertEquals(ResponseEntity.ok(String.format("updated successfully")),controllerService.updateParcel(parcel.get().getTrackingId(),parcel1));
//  }

}
