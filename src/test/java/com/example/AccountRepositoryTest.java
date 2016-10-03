package com.example;

import demo.account.Account;
import demo.account.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository repository;

    @Test
    public void testExample() throws Exception {
        this.entityManager.persist(new Account("user", "0"));
        List<Account> account = this.repository.findAccountsByUserId("user");
        assertThat(account).size().isEqualTo(1);
        Account actual = account.get(0);
        assertThat(actual.getAccountNumber()).isEqualTo("0");
        assertThat(actual.getUserId()).isEqualTo("user");
    }

}
