package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
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
//    public String loadHandler() {}
//    public void addHandler(Student student) {}
//    public Student findHandler(String studentname) {}
//    public boolean deleteHandler(int ID) {}
//    public boolean updateHandler(int ID, String name) {}
}