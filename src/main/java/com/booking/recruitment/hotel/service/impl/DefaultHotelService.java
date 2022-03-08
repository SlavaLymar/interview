package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  public static final int THREE_CLOSEST_HOTELS = 3;
  private final HotelRepository hotelRepository;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  @Override
  public Optional<Hotel> getById(String id){
    return this.hotelRepository.findById(Long.parseLong(id));
  }

  @Override
  public void deleteById(String id){
    this.hotelRepository.deleteById(Long.valueOf(id));
  }

  @Override
  public List<Hotel> searchClosest(String cityId) {
    List<Hotel> hotels = this.getHotelsByCity(Long.valueOf(cityId));

    hotels.sort((h1, h2) -> {
      double centerH1Distance = this.distance(
              new double[]{h1.getCity().getCityCentreLongitude(), h1.getCity().getCityCentreLatitude()},
              new double[]{h1.getLongitude(), h1.getLatitude()});
      double centerH2Distance = this.distance(
              new double[]{h2.getCity().getCityCentreLongitude(), h2.getCity().getCityCentreLatitude()},
              new double[]{h2.getLongitude(), h2.getLatitude()});

      if (centerH1Distance == centerH2Distance) return 0;
      return centerH1Distance < centerH2Distance ? -1 : 1;
    });

    List<Hotel> closestHotels = new ArrayList<>();
    for (int i = 0; i < THREE_CLOSEST_HOTELS && i < hotels.size(); i++) {
      closestHotels.add(hotels.get(i));
    }
    return closestHotels;
  }

  private double distance(double[] p1, double[] p2) {
    return Math.sqrt(Math.pow(p2[0] - p1[0], 2) + Math.pow(p2[1] - p1[1], 2));
  }
}
