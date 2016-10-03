package demo.api.v1;

import demo.account.Account;
import demo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceV1 {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceV1(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getUserAccounts(String username) {
        List<Account> accounts = null;
        accounts = accountRepository.findAccountsByUserId(username);

        // Mask credit card numbers
        if (accounts != null) {
            accounts.forEach(acct -> acct.getCreditCards()
                    .forEach(card ->
                            card.setNumber(card.getNumber()
                                    .replaceAll("([\\d]{4})(?!$)", "****-"))));
        }

        return accounts;
    }
}
