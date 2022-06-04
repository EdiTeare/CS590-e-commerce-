package edu.miu.cs590.account.service;

import edu.miu.cs590.account.domain.Account;
import edu.miu.cs590.account.domain.PaymentMethod;
import edu.miu.cs590.account.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface AccountService {

   Account createAccount(Account account);

   Account updateAccount(Long AccountId, Account account);

   boolean removeAccount(Long accountId);
   List<Account> findAll();

   Optional<Account> findById(Long id);

   PaymentMethod addPayment(PaymentMethod paymentMethod, Long id);






}
