package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "projectDB.db";

    public static final String TABLE_NAME_ACCOUNT = "Account_table";
    public static final String COLUMN_A1 = "accountNo";
    public static final String COLUMN_A2 = "bankName";
    public static final String COLUMN_A3 = "accountHolderName";
    public static final String COLUMN_A4 = "balance";

    public static final String TABLE_NAME_TRANSACTION = "Transaction_table";
    public static final String COLUMN_T1 = "accountNo";
    public static final String COLUMN_T2 = "type";
    public static final String COLUMN_T3 = "amount";
    public static final String COLUMN_T4 = "date";

    //initialize the database
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + TABLE_NAME_ACCOUNT + "(" + COLUMN_A1 +
                "TEXT PRIMARYKEY," + COLUMN_A2 + "TEXT," + COLUMN_A3 + "TEXT," + COLUMN_A4 + "FLOAT )";
        db.execSQL(CREATE_TABLE_ACCOUNT);

        String CREATE_TABLE_TRANSACTION = "CREATE TABLE " + TABLE_NAME_TRANSACTION + "(" + COLUMN_T1 +
                "TEXT PRIMARYKEY," + COLUMN_T2 + "TEXT," + COLUMN_T3 + "FLOAT," + COLUMN_T4 + "DATE )";
        db.execSQL(CREATE_TABLE_TRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}

    public void addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_A1, account.getAccountNo());
        contentValues.put(COLUMN_A2, account.getBankName());
        contentValues.put(COLUMN_A3, account.getAccountHolderName());
        contentValues.put(COLUMN_A4, account.getBalance());
        db.insert(TABLE_NAME_ACCOUNT, null, contentValues);
    }

    public ArrayList<String> getAccountNumbersList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> accountNo_list = new ArrayList<String>();
        Cursor res = db.rawQuery( "select "+COLUMN_A1+" from "+TABLE_NAME_ACCOUNT, null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            accountNo_list.add(res.getString(0));
            res.moveToNext();
        }
        return accountNo_list;
        //return new ArrayList<>(accounts.keySet());
    }

    public ArrayList<Account> getAccountsList() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Account> account_list = new ArrayList<Account>();
        Cursor res = db.rawQuery( "select * from "+ TABLE_NAME_ACCOUNT , null );
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            String account_no = res.getString(0);
            String bankName = res.getString(1);
            String accountHolderName = res.getString(2);
            Double balance = res.getDouble(3);
            Account account = new Account(account_no,bankName,accountHolderName,balance);
            account_list.add(account);
            res.moveToNext();
        }
        return account_list;
    }

    public Account getAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] account_No = {accountNo} ;
        Cursor res = db.rawQuery( "select * from "+ TABLE_NAME_ACCOUNT +" WHERE "+ COLUMN_A1 +" = ?", account_No );

        if (res.moveToFirst()){
            String account_no = res.getString(0);
            String bankName = res.getString(1);
            String accountHolderName = res.getString(2);
            Double balance = res.getDouble(3);
            Account account = new Account(account_no,bankName,accountHolderName,balance);
            return account;
        }
        else {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
    }

    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);
        // specific implementation based on the transaction type

        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
            }
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_A4, account.getBalance());
        db.update(TABLE_NAME_ACCOUNT, contentValues, COLUMN_A1 + " = ? ", new String[] {account.getAccountNo()});
    }

    public void removeAccount(String accountNo) throws InvalidAccountException {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] account_No = {accountNo};
        Cursor res = db.rawQuery( "select * from "+ TABLE_NAME_ACCOUNT +" WHERE "+ COLUMN_A1 +" = ?", account_No );

        if(res.moveToFirst()){
            db.delete(TABLE_NAME_ACCOUNT, COLUMN_A1+" = ? ", account_No);
        }
        else{
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }

    }

//    public String loadHandler() {}
//    public void addHandler(Student student) {}
//    public Student findHandler(String studentname) {}
//    public boolean deleteHandler(int ID) {}
//    public boolean updateHandler(int ID, String name) {}
}