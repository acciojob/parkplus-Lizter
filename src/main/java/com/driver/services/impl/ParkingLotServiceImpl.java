package com.driver.services.impl;


import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.model.ParkingLot;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.*;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;

    @Autowired
    SpotRepository spotRepository1;

    @Override
    public ParkingLot addParkingLot(String name, String address) {
//        ParkingLot parkingLot=new ParkingLot();
//        parkingLot.setName(name);
//        parkingLot.setAddress(address);
//        parkingLotRepository1.save(parkingLot);
//        return parkingLot;
        ParkingLot parkingLot=new ParkingLot(name,address);
        parkingLotRepository1.save(parkingLot);
        return parkingLot;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
//        Spot spot=new Spot();
//        spot.setPricePerHour(pricePerHour);
//        if(numberOfWheels>4){
//            spot.setSpotType(SpotType.OTHERS);
//        }
//        else if(numberOfWheels>2){
//            spot.setSpotType(SpotType.FOUR_WHEELER);
//        }
//        else spot.setSpotType(SpotType.TWO_WHEELER);
//
//        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
//        spot.setParkingLot(parkingLot);
//
//        //Making mapping bidirectional
//        parkingLot.getSpotList().add(spot);
//
//        parkingLotRepository1.save(parkingLot);
//
//
//        return spot;

        Spot spot=new Spot();
        ParkingLot parkingLot=parkingLotRepository1.findById(parkingLotId).get();
        spot.setPricePerHour(pricePerHour);
        spot.setParkingLot(parkingLot);

        spot.setOccupied(false);

        if(numberOfWheels<=2)
            spot.setSpotType(SpotType.TWO_WHEELER);
        else if(numberOfWheels<=4)
            spot.setSpotType(SpotType.FOUR_WHEELER);
        else
            spot.setSpotType(SpotType.OTHERS);

        parkingLot.getSpotList().add(spot);
        parkingLotRepository1.save(parkingLot);
        return spot;

    }


    @Override
    public void deleteSpot(int spotId) {
//        Spot spot = spotRepository1.findById(spotId).get();
//        spotRepository1.delete(spot);
        spotRepository1.deleteById(spotId);
    }


    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {

//        Spot spot = spotRepository1.findById(spotId).get();
//        spot.setPricePerHour(pricePerHour);
//
//
//        //setting parking lot
//        ParkingLot parkingLot= parkingLotRepository1.findById(parkingLotId).get();
//        spot.setParkingLot(parkingLot);
//
//        return spot;
        ParkingLot parkingLot= parkingLotRepository1.findById(parkingLotId).get();
        List<Spot> spots = parkingLot.getSpotList();
        Spot updatedSpot = null;

        for(Spot spot:spots){
            if(spot.getId()==spotId){
                updatedSpot=spot;
            }
        }
        updatedSpot.setPricePerHour(pricePerHour);
        updatedSpot.setParkingLot(parkingLot);

        spotRepository1.save(updatedSpot);
        return updatedSpot;

    }


    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);

    }
}
