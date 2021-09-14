package com.example.progresstracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "progressTrackerDatabase";
    private static final String TABLE_NAME = "employees";
    private static final String ID = "id";
    private static final String name = "name";
    private static final String berryType = "berryType";
    private static final String berryAmount = "berryAmount";
    private static final String date = "date";


    public EmployeeRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String tableQuery = "CREATE TABLE if not EXISTS " + TABLE_NAME +
                "(" +
                ID + " INTEGER PRIMARY KEY," +
                name + " TEXT ," +
                berryType + " TEXT ," +
                berryAmount + " TEXT ," +
                date + " TEXT " +
                ")";
        database.execSQL(tableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(name, employee.getName());
        contentValues.put(berryType, employee.getBerryType());
        contentValues.put(berryAmount, employee.getBerryAmount());
        contentValues.put(date, employee.getDate());
        database.insert(TABLE_NAME, null, contentValues);
        database.close();
    }

    public Employee getEmployee(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[]{ID, name, berryType, berryAmount, date}, ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Employee employee = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        database.close();
        return employee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        String query = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                employeeList.add(employee);
            }
            while (cursor.moveToNext());
        }
        database.close();
        return employeeList;
    }

    public int updateEmployee(Employee employee) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(name, employee.getName());
        contentValues.put(berryType, employee.getBerryType());
        contentValues.put(berryAmount, employee.getBerryAmount());
        contentValues.put(date, employee.getDate());
        return database.update(TABLE_NAME, contentValues, ID + "=?", new String[]{String.valueOf(employee.getId())});
    }

    public void deleteEmployee(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, ID + "=?", new String[]{id});
        database.close();
    }

    public int getTotalCount(){
        String query = "SELECT * from " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        return cursor.getCount();

    }
}
