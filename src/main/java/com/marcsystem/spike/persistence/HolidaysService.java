package com.marcsystem.spike.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidaysService {

    @Autowired
    private HolidaysRepository holidaysRepository;

    public List<Holidays> findAll(){
        return holidaysRepository.findAll();
    }

}
