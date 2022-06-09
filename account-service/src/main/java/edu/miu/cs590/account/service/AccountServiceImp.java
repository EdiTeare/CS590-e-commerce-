package edu.miu.cs590.account.service;

import edu.miu.cs590.account.domain.Account;
import edu.miu.cs590.account.domain.PaymentMethod;
import edu.miu.cs590.account.repository.AccountRepository;
import edu.miu.cs590.account.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        // if the Account already exists do not take it!
        Optional<Account> optionalAccount = accountRepository.getAccountById(account.getId());
        if(optionalAccount.isEmpty())
            return accountRepository.save(account);
        return null;
    }

    @Override
    public PaymentMethod addPayment(PaymentMethod paymentMethod, Long id){
        Account account = accountRepository.getAccountById(id).orElse(new Account());
        account.addPaymentMethod(paymentMethod);
        return paymentMethodRepository.save(paymentMethod);
    }


    @Override
    public Account updateAccount(Long id, Account accountBody) {
        Optional<Account> accountOptional= accountRepository.findById(id);
        if(accountOptional.isPresent()){
            accountBody.setId(id);
         return accountRepository.save(accountBody);
        }
        return accountRepository.save(accountBody);
    }
    @Override public boolean removeAccount(Long accountId) {
        Optional<Account> accountOptional =accountRepository.findById(accountId);
        if(accountOptional.isPresent()){
           accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }


}
