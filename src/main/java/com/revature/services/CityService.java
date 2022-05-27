package com.revature.services;

import com.revature.models.City;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CityService {
    private com.revature.repository.CityRepo cr;


    @Autowired
    public CityService(com.revature.repository.CityRepo cr) {
        this.cr = cr;
    }
    public City createCity(City c) {

        return cr.save(c);
    }

    public City findCurrentCityById(int id) {
        return cr.findById(id).get();
    }
}
