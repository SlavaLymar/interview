package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Hotel> getById(@PathVariable String id) {
    Optional<Hotel> hotel = this.hotelService.getById(id);
    return hotel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable String id) {
    Optional<Hotel> hotel = this.hotelService.getById(id);
    if (hotel.isPresent()) {
      this.hotelService.deleteById(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }



}
