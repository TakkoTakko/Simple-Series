package de.takko.simple.money.util;

import de.takko.simple.manager.api.Economy;
import de.takko.simple.manager.base.util.MySQL;
import de.takko.simple.money.MoneyModule;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;

public class SQLMoneyService implements Economy {

    private static final NumberFormat FORMAT = new DecimalFormat("#.00");
    private final MoneyModule module;
    private final MySQL mySQL;

    public SQLMoneyService(MoneyModule module, MySQL mySQL) {
        this.module = module;
        this.mySQL = mySQL;
    }

    @Override
    public String getName() {
        return "Money";
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double amount) {
        return FORMAT.format(amount);
    }

    @Override
    public String currencyNamePlural() {
        return "";
    }

    @Override
    public String currencyNameSingular() {
        return "";
    }

    @Override
    public double deposit(String namespace, UUID id, double amount) {
        return 0;
    }

    @Override
    public double withdraw(String namespace, UUID id, double amount) {
        return 0;
    }

    @Override
    public double getBalance(String namespace, UUID id) {
        return 0;
    }
}
