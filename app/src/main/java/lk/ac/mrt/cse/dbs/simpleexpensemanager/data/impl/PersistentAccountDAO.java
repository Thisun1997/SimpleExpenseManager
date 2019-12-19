package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.MyDBHandler;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class PersistentAccountDAO implements AccountDAO {

    //private final Map<String, Account> accounts;
    private MyDBHandler dbhandler;

    public PersistentAccountDAO(MyDBHandler dbhandler) {
            this.dbhandler = dbhandler;
            //this.accounts = new HashMap<>();
    }

    @Override
    public List<String> getAccountNumbersList() {
        return dbhandler.getAccountNumbersList();
    }

    @Override
    public List<Account> getAccountsList() {
        return dbhandler.getAccountsList();
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return dbhandler.getAccount(accountNo);
    }

    @Override
    public void addAccount(Account account) {
        dbhandler.addAccount(account);
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        dbhandler.removeAccount(accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        dbhandler.updateBalance(accountNo,expenseType,amount);
    }
}
