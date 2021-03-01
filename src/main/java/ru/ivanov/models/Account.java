package ru.ivanov.models;

public class Account {
    private String login;
    private String pin;
    private int cash;

    public Account(){}

    public Account (String login, String pin){
        this.setLogin(login);
        this.setPin(pin);
    }

    public Account (String login, String pin, int cash){
        this.setLogin(login);
        this.setPin(pin);
        this.setCash(cash);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}
