package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.DolarArsDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.DolarArsWrapperDto;

@Service
public class DolarArsService {



    public DolarArsDto getBlue(DolarArsWrapperDto[] dolArsDtos) {
        DolarArsDto dolarblue = null;
        for (DolarArsWrapperDto dolarArsWrapperDto : dolArsDtos) {
            if (dolarArsWrapperDto.getCasa().getNombre().equals("Dolar Blue")){
                dolarblue = dolarArsWrapperDto.getCasa();
            }
        }
    
        return dolarblue;
    }
    
}
