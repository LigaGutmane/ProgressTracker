package com.example.progresstracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EmployeeRepository employeeRepository;
    TextView dataList;
    TextView dataListCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeRepository = new EmployeeRepository(MainActivity.this);
        Button delete = findViewById(R.id.delete_data);
        Button insert = findViewById(R.id.insert_data);
        Button update = findViewById(R.id.update_data);
        Button reload = findViewById(R.id.refresh_data);
        dataList = findViewById(R.id.all_data_list);
        dataListCount = findViewById(R.id.data_list_count);


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });

    }


    private void refreshData() {
        dataListCount.setText("ALL DATA COUNT : " + employeeRepository.getTotalCount());

        List<Employee> employeeList = employeeRepository.getAllEmployees();
        dataList.setText("");
        for (Employee employee : employeeList) {
            dataList.append("ID : " + employee.getId() + " | Name : " + employee.getName() + " | Berries : " + employee.getBerryType() + " | Amount : " + employee.getBerryAmount() + " | Date : " + employee.getDate() + "\n\n");
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.delete_dialog, null);
        alertDialog.setView(view);
        final EditText idInput = view.findViewById(R.id.id_input);
        Button deleteButton = view.findViewById(R.id.delete_btn);
        AlertDialog dialog = alertDialog.show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeRepository.deleteEmployee(idInput.getText().toString());
                dialog.dismiss();
                refreshData();
            }
        });
    }


    private void showInputDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.insert_dialog, null);
        final EditText name = view.findViewById(R.id.name);
        final EditText berryType = view.findViewById(R.id.berryType);
        final EditText berryAmount = view.findViewById(R.id.berryAmount);
        final EditText date = view.findViewById(R.id.date);
        Button insertButton = view.findViewById(R.id.insert_btn);
        alertDialog.setView(view);

        AlertDialog dialog = alertDialog.show();
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employee.setName(name.getText().toString());
                employee.setBerryType(berryType.getText().toString());
                employee.setBerryAmount(berryAmount.getText().toString());
                employee.setDate(date.getText().toString());
                employeeRepository.addEmployee(employee);
                dialog.dismiss();
                refreshData();
            }
        });

    }

    private void showUpdateIdDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.update_id_dialog, null);
        alertDialog.setView(view);
        final EditText idInput = view.findViewById(R.id.id_input);
        Button fetchButton = view.findViewById(R.id.update_id_btn);
        AlertDialog dialog = alertDialog.show();
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(idInput.getText().toString());
                dialog.dismiss();
                refreshData();
            }
        });
    }


    private void showDataDialog(String id) {
        Employee employee = employeeRepository.getEmployee(Integer.parseInt(id));
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.update_dialog, null);
        final EditText name = view.findViewById(R.id.name);
        final EditText berryType = view.findViewById(R.id.berryType);
        final EditText berryAmount = view.findViewById(R.id.berryAmount);
        final EditText date = view.findViewById(R.id.date);
        Button updateButton = view.findViewById(R.id.update_btn);
        alertDialog.setView(view);

        name.setText(employee.getName());
        berryType.setText(employee.getBerryType());
        berryAmount.setText(employee.getBerryAmount());
        date.setText(employee.getDate());

        AlertDialog dialog = alertDialog.show();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Employee employee = new Employee();
                employee.setName(name.getText().toString());
                employee.setId(id);
                employee.setBerryType(berryType.getText().toString());
                employee.setBerryAmount(berryAmount.getText().toString());
                employee.setDate(date.getText().toString());
                employeeRepository.updateEmployee(employee);
                dialog.dismiss();
                refreshData();
            }
        });

    }

}