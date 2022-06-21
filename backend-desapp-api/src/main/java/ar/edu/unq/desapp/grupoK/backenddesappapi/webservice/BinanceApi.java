package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.CryptoDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.CryptosDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.CryptoService;

@RestController
@RequestMapping("/binance")
public class BinanceApi {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping(value = "/fetch")
    public CryptoDto getCrypto(String crypto){
        String url = "https://api.binance.us/api/v3/ticker/price?symbol=" + crypto;

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto cryptodto = restTemplate.getForObject(url, CryptoDto.class);

        cryptoService.save(cryptodto);

        return cryptodto;
    }

    @PostMapping(value = "/fetchAll")
    public ResponseEntity<ArrayList<CryptoDto>> getCryptos(@RequestBody CryptosDto cryptos){
        String url = "https://api.binance.us/api/v3/ticker/price";

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto[] cryptodtos = restTemplate.getForObject(url, CryptoDto[].class);
          
        return new ResponseEntity<ArrayList<CryptoDto>>(cryptoService.saveAll(cryptodtos), HttpStatus.OK);
    }

}


    



