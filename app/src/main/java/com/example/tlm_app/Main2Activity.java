package com.example.tlm_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private DatabaseReference firebaseReference;
    private EditText edName;
    private Spinner spinGroup;
    private Button btnAdd;
    private ListView listView;
    private List<UserDao> users;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initInstances();
        initFirebase();
        showData();
    }

    private void initFirebase() {
        firebaseReference = FirebaseDatabase.getInstance().getReference();

    }

    private void initInstances() {
        edName = (EditText) findViewById(R.id.edName);
        spinGroup = (Spinner) findViewById(R.id.spinGroup);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==btnAdd){
                    addUser();
                }
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserDao dao = users.get(i);
                showDialog(dao);
                return false;
            }
        });

        users = new ArrayList<>();

    }


    private void addUser() {
        String name = edName.getText().toString();
        String group = spinGroup.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {
            String id = firebaseReference.child("users").push().getKey();

            UserDao user = new UserDao();
            user.setId(id);
            user.setName(name);
            user.setGroup(group);

            firebaseReference.child("users").child(id).setValue(user);

            Toast.makeText(this, "User added", Toast.LENGTH_LONG).show();
            edName.setText("");
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
    private void showData() {
        Query query = firebaseReference.child("users");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserDao dao = postSnapshot.getValue(UserDao.class);
                    users.add(dao);
                }
                adapter = new UserAdapter(users);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private boolean updateUser(String id, String name, String group) {
        //updating
        UserDao dao = new UserDao();
        dao.setId(id);
        dao.setName(name);
        dao.setGroup(group);

        firebaseReference.child("users").child(id).setValue(dao);
        Toast.makeText(this, "User Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteUser(String id) {
        //removing
        firebaseReference.child("users").child(id).removeValue();

        Toast.makeText(this, "User Deleted", Toast.LENGTH_LONG).show();

        return true;
    }


    private int getIndex(Spinner spinner, String myString) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void showDialog(final UserDao dao) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.user_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edName = (EditText) dialogView.findViewById(R.id.edName);
        final Spinner spinnerGroup = (Spinner) dialogView.findViewById(R.id.spinnerGroup);
        final Button btnUpdate = (Button) dialogView.findViewById(R.id.btnUpdate);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.btnDelete);

        dialogBuilder.setTitle(dao.getName());
        edName.setText(dao.getName());
        spinnerGroup.setSelection(getIndex(spinnerGroup, dao.getGroup()));
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString().trim();
                String genre = spinnerGroup.getSelectedItem().toString();
                if (!TextUtils.isEmpty(name)) {
                    updateUser(dao.getId(), name, genre);
                    alertDialog.dismiss();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser(dao.getId());
                alertDialog.dismiss();
            }
        });

    }


}