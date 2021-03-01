package ru.ivanov.classes.Terminal;

import org.springframework.stereotype.Component;
import ru.ivanov.models.Account;

import java.util.*;

@Component
public class TerminalServer {
    private HashSet<Account> listAccount;
    private HashMap<String, String> relationLoginPIN;
    private HashMap<String, Integer> relationLoginCash;
    private HashMap<String, Account> relationLoginAccount;
    private HashMap<Account, Account> relationAccountAccountServer;

    public TerminalServer(HashSet<Account> listAccount) {
        this.listAccount = listAccount;
        this.relationLoginPIN = new HashMap<>();
        this.relationLoginCash = new HashMap<>();
        this.relationLoginAccount = new HashMap<>();
        this.relationAccountAccountServer = new HashMap<>();
        for (Account account: listAccount) {
            relationLoginPIN.put(account.getLogin(), account.getPin());
            relationLoginCash.put(account.getLogin(), account.getCash());
            relationLoginAccount.put(account.getLogin(), account);
            relationAccountAccountServer.put(new Account(account.getLogin(), account.getPin()), account);
        }
    }

    public boolean validRelationLoginPIN(String login, String pin, int attempt)
            throws AccountIsLockedException{
        if(attempt == 3){
            throw new AccountIsLockedException();
        }
        return relationLoginPIN.get(login).equals(pin);
    }

    public Account getAccountServer( Account account ){
        return relationAccountAccountServer.get(account);
    }

    public void putMoney(String login, int value){
        int newCash = relationLoginCash.get(login) + value;
        relationLoginCash.put(login, newCash);
        Account account = relationLoginAccount.get(login);
        account.setCash(newCash);
    }

    public Integer takeMoney(String login, int value){
        int newCash = relationLoginCash.get(login) - value;
        if(newCash < 0){
            return 0;
        }
        relationLoginCash.put(login, newCash);
        Account account = relationLoginAccount.get(login);
        account.setCash(newCash);
        return value;
    }

    public Integer getStatement(String login){
        Account account = relationLoginAccount.get(login);
        return account.getCash();
    }
}
