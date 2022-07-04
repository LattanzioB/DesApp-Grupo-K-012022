package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.CryptoDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.CryptoService;

@Controller
@RestController
@RequestMapping("/binance")
public class BinanceApi {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping(value = "/fetch")
    public CryptoDto getCrypto(String crypto){
        String url =  cryptoService.getUrlGetName() + crypto;

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto cryptodto = restTemplate.getForObject(url, CryptoDto.class);

        cryptoService.save(cryptodto);

        return cryptodto;
    }

    @GetMapping(value = "/fetchAll")
    public ResponseEntity<CryptoDto[]> getCryptos(){
        return new ResponseEntity<CryptoDto[]>(cryptoService.updateCryptos(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllCryptos")
    public ResponseEntity<ArrayList<CryptoDto>> getAllCryptos(){
        return new ResponseEntity<ArrayList<CryptoDto>>(cryptoService.getCryptos(), HttpStatus.OK);
    }

}


    



