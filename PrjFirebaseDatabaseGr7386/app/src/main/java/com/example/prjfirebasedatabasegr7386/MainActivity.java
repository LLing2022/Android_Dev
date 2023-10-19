package com.example.prjfirebasedatabasegr7386;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener, ChildEventListener {

    EditText edId, edName, edAge;
    Button btnAdd, btnUpdate, btnDelete, btnFind, btnFindAll;
    DatabaseReference personDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        edId=findViewById(R.id.edId);
        edName=findViewById(R.id.edName);
        edAge=findViewById(R.id.edAge);
        btnAdd=findViewById(R.id.btnAdd);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnFind=findViewById(R.id.btnFind);
        btnFindAll=findViewById(R.id.btnFindAll);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnFind.setOnClickListener(this);
        btnFindAll.setOnClickListener(this);
        personDatabase = FirebaseDatabase.getInstance().getReference("Person");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnAdd) addUpdatePerson(view, " has been added successfully.");
        if(id == R.id.btnUpdate) addUpdatePerson(view, " has been added successfully.");
        if(id == R.id.btnDelete) deletePerson(view);
        if(id == R.id.btnFind) findPerson(view);
        if(id == R.id.btnFindAll) findAll();

    } 

    private void addUpdatePerson(View view, String message) {
        try{
            String id = edId.getText().toString();
            String name = edName.getText().toString();
            String age = edAge.getText().toString();

            Person person = new Person(Integer.valueOf(id), name, Integer.valueOf(age));
            personDatabase.child(id).setValue(person);
            Snackbar.make(view, "Person with the id :" + id + message, Snackbar.LENGTH_LONG).show();
            clearWidgets();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private void clearWidgets() {
        edId.setText(null);
        edName.setText(null);
        edAge.setText(null);
        edId.requestFocus();
    }

    private void deletePerson(View view) {
        String id = edId.getText().toString();
        personDatabase.child(id).removeValue();
        Snackbar.make(view, "The person with the id " + id + " has been removed successfully.",
                Snackbar.LENGTH_LONG).show();
    }

    private void findPerson(View view) {
        String id = edId.getText().toString();
        personDatabase.child(id).addValueEventListener(this); // will trigger Method DataChange
    }

    private void findAll() {
        personDatabase.addChildEventListener(this);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists()){
            String name = snapshot.child("name").getValue().toString();
            String age = snapshot.child("age").getValue().toString();
            edName.setText(name);
            edAge.setText(age);
        }else {
            Toast.makeText(this, "No documents", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {


    }
    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        Person person = snapshot.getValue(Person.class);
        Toast.makeText(this, person.toString(), Toast.LENGTH_LONG).show();
        Log.d("Firebase", person.toString());
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }


}