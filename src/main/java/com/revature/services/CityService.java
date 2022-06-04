package com.revature.services;

import com.revature.models.City;
import com.revature.models.User;
import com.revature.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityService {
    private CityRepo cr;


    @Autowired
    public CityService(CityRepo cr) {
        this.cr = cr;
    }
    public City createCity(City c) {

        City temp =  findCurrentCityByName(c.getCity());
        City hold = cr.findByPosition(c.getPosition());

        if(temp != null || hold != null){
            return null;
        }else{
            return cr.save(c);
        }

    }

    public City findCurrentCityById(int id) {
        return cr.findById(id).get();
    }

    public City findCurrentCityByName(String city) {
        return cr.findByCity(city);
    }

    public List<City> getAllCities(){
        return cr.findAll();
    }
}
