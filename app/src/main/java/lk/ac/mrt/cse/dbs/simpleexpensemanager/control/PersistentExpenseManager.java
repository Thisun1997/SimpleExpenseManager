package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.os.PersistableBundle;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

public class PersistentExpenseManager extends ExpenseManager{
    private Context context;
    public PersistentExpenseManager(Context context) {
        setup();
        this.context = context;
    }

    @Override
    public void setup() {
        /*** Begin generating dummy data for In-Memory implementation ***/
        //System.out.println("jj");
        MyDBHandler mydbhandler = new MyDBHandler(context);
//        PersistableBundle PersistentTransactionDAO = new PersistentTransactionDAO(mydbhandler);
//        setTransactionsDAO(inMemoryTransactionDAO);

        AccountDAO persistentAccountDAO = new PersistentAccountDAO(mydbhandler);
        setAccountsDAO(persistentAccountDAO);


        Account dummyAcct1 = new Account("12345A", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
        getAccountsDAO().addAccount(dummyAcct1);
        getAccountsDAO().addAccount(dummyAcct2);
        /*** End ***/
    }
}
