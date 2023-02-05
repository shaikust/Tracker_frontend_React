package com.updatedparceltracker;

import com.updatedparceltracker.model.Parcel;
import com.updatedparceltracker.model.Tracking;
import com.updatedparceltracker.model.User;
import com.updatedparceltracker.repository.ParcelRepository;
import com.updatedparceltracker.repository.TrackingRepository;
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
  @Mock
  private TrackingRepository trackingRepository;
  @InjectMocks
  private ParcelControllerService controllerService;

  public List<Parcel> parcels=new ArrayList<>();
  public Set<Tracking> trackings;


  @Test
  public void test_addParcel() {
    Parcel parcel = new Parcel(121, "klm", "ekm", "pkd", "notdeliverd");
    Tracking tracking=new Tracking(1,"ekm",parcel);
    when(parcelRepository.save(parcel)).thenReturn(null);
    when(trackingRepository.save(tracking)).thenReturn(null);

    assertEquals(ResponseEntity.ok(String.format("[" + parcel.getId() + "]" + "parcel added successfully")), controllerService.addParcel(parcel));


  }
  @Test
  public void test_parcel_list(){
    parcels.add(new Parcel(121,"klm","tvm","klm","no"));
    when(parcelRepository.findAll()).thenReturn(parcels);
    assertEquals(parcels,controllerService.getAllParcel().getBody());


  }
  @Test
  public void test_delete_parcel(){
    Optional<Parcel> parcel=Optional.of(new Parcel(1,"klm","tvm","klm","no"));
    Optional<Tracking> tracking=Optional.of(new Tracking(1,"ekm",parcel.get()));
    when(parcelRepository.findById(parcel.get().getId())).thenReturn(parcel);
    when(trackingRepository.findByparcelId(parcel.get().getId())).thenReturn(tracking);
    doNothing().when(parcelRepository).delete(parcel.get());
    doNothing().when(trackingRepository).delete(tracking.get());
    assertEquals(ResponseEntity.ok(String.format("parcel "+"["+parcel.get().getId()+"]" +"deleted successfully")),controllerService.deleteParcel(parcel.get().getId()));

  }
//
//
//
  @Test
  public void test_update(){
    Optional<Parcel> parcel=Optional.of(new Parcel(121,"klm","tvm","klm","no"));
    Parcel parcel1 = new Parcel(121, "klm", "ekm", "pkd", "notdeliverd");
    Optional<Tracking> tracking=Optional.of(new Tracking(1,"ekm",parcel1));
    Tracking tracking1=new Tracking(1,"ekm",parcel1);
    when(trackingRepository.findByparcelId(parcel.get().getId())).thenReturn(tracking);
    when(trackingRepository.save(tracking1)).thenReturn(null);
    when(parcelRepository.findById(parcel.get().getId())).thenReturn(parcel);
    when(parcelRepository.save(parcel1)).thenReturn(null);

    assertEquals(ResponseEntity.ok(String.format("parcel "+"["+parcel.get().getId()+"]" +"updated successfully")),controllerService.updateParcel(parcel.get().getId(),parcel1));
  }

}
