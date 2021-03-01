package ru.ivanov.classes.Terminal;

public class AccountIsLockedException extends Exception{

    @Override
    public String getMessage(){
        return "Your account is blocked! It continue about ";
    }
}
