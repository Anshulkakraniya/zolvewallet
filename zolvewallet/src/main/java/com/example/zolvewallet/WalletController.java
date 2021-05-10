package com.example.zolvewallet;

import com.example.zolvewallet.WalletEntity;
import com.example.zolvewallet.WalletValidationService;
import com.example.zolvewallet.exceptions.WalletException;
import com.example.zolvewallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.apache.logging.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

@RestController
@RequestMapping("/wallet")
public class WalletController {
//	static Logger log = Logger.getLogger(WalletController.class.getName());
//	static final Logger log = LogManager.getLogger(WalletController.class.getName());
	Logger logger = LoggerFactory.getLogger(WalletController.class);
//	 static Log log2 = LogFactory.getLog(WalletController.class.getName());
	 
    @Autowired
    private WalletService walletService;
    @Autowired
    private WalletValidationService validationService;
//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
//    	log.info("Fetched details for ", id);
        return new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody WalletEntity wallet, BindingResult result){
    		logger.warn("Created Wallet Succesfully for "+ wallet.getPhoneNumber());
            ResponseEntity errors = validationService.validate(result);
            if(errors != null) return errors;
            WalletEntity walletSaved = walletService.Create(wallet);
            return new ResponseEntity<>("Created Wallet Succesfully" , HttpStatus.CREATED);
    }

    @PutMapping("{id}/debit")
    public ResponseEntity<?> debit(@PathVariable String id, @RequestParam("debitAmount") Long debitAmount/*, BindingResult result*/){
//        ResponseEntity errors = validationService.validate(result);
//        if(errors != null) return errors;
//        wallet.setId(id);
    	try {
    		WalletEntity walletSaved = walletService.DebitWallet(id, debitAmount);
            logger.warn("Debited Amount "+ debitAmount + " from " + walletSaved.getPhoneNumber() +", current balance is " + walletSaved.getCurrentBalance());
            return new ResponseEntity<WalletEntity>(walletSaved, HttpStatus.OK);
    	}
        catch (Exception e){
        	logger.warn(e.getMessage());
        }
    	return new ResponseEntity<String>("Please enter a valid amount, balance should be greater than 200", HttpStatus.OK);
    }
    
    @PutMapping("{id}/credit")
    public ResponseEntity<?> credit(@PathVariable String id, @RequestParam("creditAmount") Long creditAmount/*, BindingResult result*/){
//        ResponseEntity errors = validationService.validate(result);
//        if(errors != null) return errors;
//        wallet.setId(id);
        WalletEntity walletSaved = walletService.CreditWallet(id, creditAmount);
        logger.warn("Credited Amount "+ creditAmount + " into " + walletSaved.getPhoneNumber() +", current balance is " + walletSaved.getCurrentBalance());

        return new ResponseEntity<WalletEntity>(walletSaved, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable String id){
        walletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}