package io.github.augustorsn.back_end_baba.service;

import org.springframework.stereotype.Service;
import io.github.augustorsn.back_end_baba.repository.MeuRepository;

@Service
public class MeuService {

    public MeuRepository repository;
    
    public MeuService(MeuRepository repository){
        this.repository = repository;
    }


    
}
