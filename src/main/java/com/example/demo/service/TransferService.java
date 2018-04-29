package com.example.demo.service;

import com.example.demo.model.Transfer;
import com.example.demo.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TransferService {

    final TransferRepository transferRepository;

    @Autowired

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public Iterable<Transfer> findTransfers(){
        return transferRepository.findAll();
    }

    public void saveTransfer(Transfer transfer){
        transferRepository.save(transfer);
    }
}
