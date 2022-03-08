package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/search")
public class SearchController {

  private final HotelService hotelService;

  @Autowired
  public SearchController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping("/{cityId}")
  public ResponseEntity<List<Hotel>> searchClosest(@PathVariable String cityId, @RequestParam Map<String, Object> param) {
    List<Hotel> hotels = this.hotelService.searchClosest(cityId);
    if (hotels.size() != 0) return ResponseEntity.ok(hotels);
    else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
