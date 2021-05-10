package com.example.zolvewallet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WalletEntity {
   
	@Id
    @Size(min = 10, max = 10, message = "Enter a 10 digit number")
    private String phoneNumber;
   
//    private Integer priority; //1=High; 2=Medium; 3=Low
//	@Version
    private Double currentBalance = (double) 0;
//    @PrePersist

   
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	
//	@PrePersist
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
}