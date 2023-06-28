package com.mdbytes.app.rest.service;

import com.mdbytes.app.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreService {
    private static StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        StoreService.storeRepository = storeRepository;
    }

    static StoreRepository storeRep() {
        return storeRepository;
    }
}
