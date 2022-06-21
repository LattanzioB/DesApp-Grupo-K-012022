package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.util.ArrayList;
import java.util.Date;

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

<<<<<<< HEAD
        cryptoService.save(cryptodto);
=======
        
        Date date = new Date();
        date.toString();

        Crypto newcrypto = new Crypto(cryptodto.getSymbol(), cryptodto.getPrice(),date.toString());

        cryptoService.save(newcrypto);
>>>>>>> e75a1e64ff647a24017e8559f565fa222f8b9631

        return cryptodto;
    }

    @PostMapping(value = "/fetchAll")
    public ResponseEntity<ArrayList<CryptoDto>> getCryptos(@RequestBody CryptosDto cryptos){
        String url = "https://api.binance.us/api/v3/ticker/price";

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto[] cryptodtos = restTemplate.getForObject(url, CryptoDto[].class);

        ArrayList<CryptoDto> finalCryptos = new ArrayList<CryptoDto>();

        for (CryptoDto cryptoDto : cryptodtos) {
            if(cryptos.containsCrypto(cryptoDto.getSymbol())){
                finalCryptos.add(cryptoDto);
            }
        }

        for (CryptoDto cryptoDto2 : finalCryptos) {
            Crypto newcrypto = new Crypto(cryptoDto2.getSymbol(), cryptoDto2.getPrice());

            cryptoService.save(newcrypto);
        }
            

        
        return new ResponseEntity<ArrayList<CryptoDto>>(finalCryptos, HttpStatus.OK);
    }

}


    



