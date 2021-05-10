package com.example.zolvewallet;

import com.example.zolvewallet.WalletEntity;
import com.example.zolvewallet.exceptions.WalletException;
import com.example.zolvewallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    public List<WalletEntity> getAll(){
        return walletRepository.findAll();
    }
    
    @Transactional(isolation=Isolation.REPEATABLE_READ)
    public WalletEntity getById(String id){
        Optional<WalletEntity> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            return wallet.get();
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public WalletEntity Create(WalletEntity wallet){
    	Optional<WalletEntity> checkWallet = walletRepository.findById(wallet.getPhoneNumber());
    	if(checkWallet.isPresent()){
    		throw new WalletException("Wallet already exists");
    	}
    	else {
    		walletRepository.save(wallet);
    		return wallet;
    	}
  }
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public WalletEntity DebitWallet(String id, Long debitAmount){
        Optional<WalletEntity> wallet = walletRepository.findById(id);
        WalletEntity retrievedWallet;
        if(wallet.isPresent()){
        	retrievedWallet = wallet.get();
        	if (retrievedWallet.getCurrentBalance()==0) {
        		throw new WalletException("Current Balance is Nil!");
        	}
        	else if (retrievedWallet.getCurrentBalance() - debitAmount < 200) {
        		throw new WalletException("Minimum Balance of 200 should be maintained!");
        	}
        	else {
        		retrievedWallet.setCurrentBalance(retrievedWallet.getCurrentBalance() - debitAmount);
        	}
            walletRepository.save(retrievedWallet);	
        }
        else {
        	throw new WalletException("Wallet with "+id+" does not exists!");
        }

        return retrievedWallet;
    }
    
    public WalletEntity CreditWallet(String id, Long creditAmount ){
        Optional<WalletEntity> wallet = walletRepository.findById(id);
        WalletEntity retrievedWallet;
        if(wallet.isPresent()){
        	retrievedWallet = wallet.get();
        	retrievedWallet.setCurrentBalance(retrievedWallet.getCurrentBalance()+creditAmount);
        	walletRepository.save(retrievedWallet);
        }
        else {
        	throw new WalletException("Wallet with "+id+" does not exists!");
        }
        return retrievedWallet;
  }
    
    public boolean delete(String id){
        Optional<WalletEntity> wallet = walletRepository.findById(id);
        if(wallet.isPresent()){
            walletRepository.delete(wallet.get());
            return true;
        }
        throw new WalletException("Wallet with "+id+" does not exists!");
    }
}