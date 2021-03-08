package com.marcsystem.spike.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaysHybridService {

    @Autowired
    private HolidaysHybridRepository repository;

    public List<HolidaysHybrid> findAll(){
        return repository.findAll();
    }

}
