package edu.miu.cs590.account.controller;

import com.sun.istack.NotNull;
import edu.miu.cs590.account.domain.*;
import edu.miu.cs590.account.dto.AccountDTO;
import edu.miu.cs590.account.service.AccountService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    //    @Autowired
    @Autowired
    private AccountService accountService;


    // Get all accounts
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Account> accounts = accountService.findAll();
        return ResponseEntity.ok(accounts);
    }

    //Get account by id
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Account> account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        Account newAccount = accountService.createAccount(account);
        if (newAccount == null)
            return new ResponseEntity<>("Already Exists", HttpStatus.OK);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/bank-account")
    public ResponseEntity<?> addBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        PaymentMethod paymentMethod =  accountService.addPayment(bankAccount, id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/credit-card")
    public ResponseEntity<?> addCreditCard(@PathVariable Long id, @RequestBody CreditCard creditCard) {
        PaymentMethod paymentMethod =  accountService.addPayment(creditCard, id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/paypal")
    public ResponseEntity<?> addPayPal(@PathVariable Long id, @RequestBody PayPal paypal) {
        PaymentMethod paymentMethod =  accountService.addPayment(paypal, id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Account accountBody){
        Account account = accountService.updateAccount(id, accountBody);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping(path = "/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        if (!accountService.removeAccount(accountId)) {
            return new ResponseEntity<>("No Account Found!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }
}