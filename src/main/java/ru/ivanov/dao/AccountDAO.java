package ru.ivanov.dao;

import org.springframework.stereotype.Component;
import ru.ivanov.models.Account;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AccountDAO {
    private List<Account> accountList;

    public AccountDAO(){
        accountList = new ArrayList<>();
        accountList.add(new Account("alex", "1234", 12000));
        accountList.add(new Account("harry", "4321", 12200));
        accountList.add(new Account("red", "1111", 32500));
    }

    public boolean validate(Account account){
        for (Account existAccount : accountList) {
            if(validateAccountField(existAccount, account)){
                return true;
            }
        }
        return false;
    }

    private static boolean validateAccountField(Account src, Account dest){
        if(src.getLogin().equals(dest.getLogin()) && src.getPin().equals(dest.getPin())){
            return true;
        }
        return false;
    }

    public HashSet<Account> toHashSet(){
        HashSet<Account> accountHashSet = new HashSet<>();
        accountHashSet.addAll(accountList);
        return accountHashSet;
    }
}
