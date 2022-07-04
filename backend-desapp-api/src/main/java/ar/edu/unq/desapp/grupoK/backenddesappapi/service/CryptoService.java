package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.CryptoDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.CryptoRepository;

@Service
public class CryptoService {
    private static final Logger LOGGER = LogManager.getLogger(CryptoService.class);
    
    private List<String> cryptosToGet = Arrays.asList("ALICEUSDT","MATICUSDT","AXSUSDT","AAVEUSDT","ATOMUSDT","NEOUSDT","DOTUSDT","CAKEUSDT","ADAUSDT","TRXUSDT","AUDIOUSDT","BTCUSDT","ETHUSDT","BNBUSDT");
    
    private String urlGetName = "https://api.binance.us/api/v3/ticker/price?symbol=";
    private String urlGetAll = "https://api.binance.us/api/v3/ticker/price";


    
    public String getUrlGetName() {
        return urlGetName;
    }

    public String getUrlGetAll() {
        return urlGetAll;
    }

    @Autowired
    private CryptoRepository cryptoRepository;


    @Transactional
    public ArrayList<CryptoDto> getCryptos(){
        var cryptos = cryptoRepository.findAll();
        ArrayList<CryptoDto> finalCryptos = new ArrayList<CryptoDto>();

        for (Crypto crypto : cryptos) {
            CryptoDto newCryptoDto = new CryptoDto();
            newCryptoDto.setSymbol(crypto.getName());
            newCryptoDto.setPrice(crypto.getQuote());
            newCryptoDto.setFetchQuoteTime(crypto.getQuoteFetchTime());
            finalCryptos.add(newCryptoDto);
        }
        return finalCryptos;
    }

    @Transactional
    public Optional<Crypto> getCrypto(Integer id){
        return cryptoRepository.findById(id);
    }

    @Transactional
    public Crypto save(CryptoDto newCryptodto) {
        //VALIDACION
        Crypto newcrypto = new Crypto(newCryptodto.getSymbol(), newCryptodto.getPrice());
        return cryptoRepository.save(newcrypto);
    }

    @Transactional
    @PostConstruct
    public ArrayList<CryptoDto> saveAll() {
        LOGGER.info("******fetchAll Post Initiatize******");
        ArrayList<CryptoDto> finalCryptos = new ArrayList<CryptoDto>();

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto[] cryptodtos = restTemplate.getForObject(this.getUrlGetAll(), CryptoDto[].class);

        for (CryptoDto cryptoDto : cryptodtos) {
            if(cryptosToGet.contains(cryptoDto.getSymbol())){
                finalCryptos.add(cryptoDto);
            }
        }

        for (CryptoDto cryptoDto2 : finalCryptos) {
            Crypto newcrypto = new Crypto(cryptoDto2.getSymbol(), cryptoDto2.getPrice(), new Date());

            cryptoRepository.save(newcrypto);
        }
        return finalCryptos;
    }

    @Transactional
    @Scheduled(cron = "1 * * * * ?")
    public CryptoDto[] updateCryptos() {
        LOGGER.info("******updateCryptos Scheduled******");

        RestTemplate restTemplate = new RestTemplate();
        
        CryptoDto[] cryptodtos = restTemplate.getForObject(this.getUrlGetAll(), CryptoDto[].class);

        Iterable<Crypto> repositoryCryptos = cryptoRepository.findAll();

        

        for (Crypto crypto : repositoryCryptos) {
            for(CryptoDto cryptodto : cryptodtos){
                if(crypto.getName().equals(cryptodto.getSymbol()) ){
                    crypto.setQuote(cryptodto.getPrice());
                    crypto.setQuoteFetchTime(new Date());
                }
            }
        }

        cryptoRepository.saveAll(repositoryCryptos);

        return cryptodtos;
    }

    @Transactional
    public Optional<Crypto> getCryptoByName(String cryptoName) {
        Iterable<Crypto> cryptos = cryptoRepository.findAll();
        Optional<Crypto> cryptores = Optional.empty(); 
        for (Crypto crypto : cryptos) {
            if (crypto.getName().equals(cryptoName)){
                cryptores = Optional.of(crypto);
            }
        }
        return cryptores;
    }
}
