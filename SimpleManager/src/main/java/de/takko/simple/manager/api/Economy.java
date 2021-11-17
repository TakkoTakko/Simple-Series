package de.takko.simple.manager.api;

import java.util.UUID;

public interface Economy {

    String DEFAULT_NAMESPACE = "default";

    String getName();

    /**
     * This method returns the number of digits keeps
     * or -1 if no rounding occurs.
     *
     * @return number of digits after the decimal point kept
     */
    int fractionalDigits();

    /**
     * Format amount into a human-readable String This provides translation into
     * economy specific formatting to improve consistency between plugins.
     *
     * @param amount to format
     * @return Human-readable string describing amount
     */
    String format(double amount);

    /**
     * Returns the name of the currency in plural form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (plural)
     */
    String currencyNamePlural();


    /**
     * Returns the name of the currency in singular form.
     * If the economy being used does not support currency names then an empty string will be returned.
     *
     * @return name of the currency (singular)
     */
    String currencyNameSingular();

    /**
     * Deposits the amount to the database
     *
     * @param namespace the namespace
     * @param id        the id of the user
     * @param amount    the amount which will be deposited to the database
     * @return the new amount in database
     */
    int deposit(String namespace, UUID id, double amount);

    /**
     * Deposits the amount to the database with the default namespace
     *
     * @param id     the id of the user
     * @param amount the amount which will be deposited to the database
     * @return the new amount in the database
     */
    default int deposit(UUID id, double amount) {
        return deposit(DEFAULT_NAMESPACE, id, amount);
    }

    /**
     * Withdraws the amount from the database
     *
     * @param namespace the namespace
     * @param id        the id of the user
     * @param amount    the amount which will be withdrawn from the database
     * @return the new amount in the database
     */
    int withdraw(String namespace, UUID id, double amount);

    /**
     * Withdraws the amount from the database with the default namespace
     *
     * @param id     the id of the user
     * @param amount the amount which will be withdrawn from the database
     * @return the new amount in the database
     */
    default int withdraw(UUID id, double amount) {
        return withdraw(DEFAULT_NAMESPACE, id, amount);
    }

    /**
     * Gets the balance from the database
     *
     * @param namespace the namespace where the amount is saved
     * @param id        the id of the user
     * @return the current balance in namespace in database
     */
    int getBalance(String namespace, UUID id);

    /**
     * Gets the balance from the database
     *
     * @param id the id of the user
     * @return the current balance in namespace in database
     */
    default int getBalance(UUID id) {
        return getBalance(DEFAULT_NAMESPACE, id);
    }

}
