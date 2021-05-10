package com.example.zolvewallet;

import com.example.zolvewallet.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, String> {
    List<WalletEntity> findAll();
}