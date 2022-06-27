package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.DolarArsDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.DolarArsWrapperDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.DolarArsService;

@Controller
@RestController
@RequestMapping("/dolarars")
public class DolarArsApi {
    

    @Autowired
    private DolarArsService dolarArsService;


    @GetMapping(value = "/fetch")
    public DolarArsDto getCrypto(){
        String url = "https://www.dolarsi.com/api/api.php?type=valoresprincipales";

        RestTemplate restTemplate = new RestTemplate();
        
        DolarArsWrapperDto[] dolArsDtos = restTemplate.getForObject(url, DolarArsWrapperDto[].class);

        

        return dolarArsService.getBlue(dolArsDtos);
    }
}
