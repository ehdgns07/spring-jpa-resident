package com.nhnacademy.jpa.service.resident;

import com.nhnacademy.jpa.domain.ResidentRegisterDto;
import com.nhnacademy.jpa.entity.resident.Resident;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class ResidentServiceImpl implements ResidentService{

    ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }
    @Override
    public Resident createResident(Resident resident){ return residentRepository.save(resident); }

    @Override
    public Integer modifyResident(ResidentRegisterDto residentRegisterDto, Integer serialNo){
        // residentRepository.findById(serialNo)
        //                       .ifPresent(value -> {
        //                           value.set
        //                           residentRepository.save(value)
        //                       });
        return residentRepository.updateResident(residentRegisterDto.getName(),
            residentRegisterDto.getGender(), residentRegisterDto.getBirthDate(),
            residentRegisterDto.getBaseAddress(), serialNo);
    }

    @Override
    public Resident getResident(Integer serialNo) {
        Optional<Resident> residentOptional = residentRepository.findById(serialNo);
        if(residentOptional.isPresent()) {
            return residentOptional.get();
        }
        return null;
    }

}
