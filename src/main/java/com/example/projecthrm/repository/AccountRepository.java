package com.example.projecthrm.repository;

import com.example.projecthrm.model.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByEmail(String email);
    boolean existsAccountByEmail(String email);

    @Override
    Page<Account> findAll(Pageable pageable);
}
