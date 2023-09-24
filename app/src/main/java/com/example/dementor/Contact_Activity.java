package com.example.dementor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class Contact_Activity extends AppCompatActivity {

    private Button btn;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        btn = findViewById(R.id.callBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:112"));
                startActivity(intent);
            }
        });
    }

    /*
    edit = findViewById(R.id.editCallButton);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(Contact_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.dialog);

                Button close,save;
                close = dialog.findViewById(R.id.dialogCancel);
                save = dialog.findViewById(R.id.dialogSave);
                EditText  number = dialog.findViewById(R.id.dialogEditText);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String numberText = number.getText().toString();
                        if(numberText.length() == 10){
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("firstNumber",numberText);
                            editor.apply();
                            setCallingInformation();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(Contact_Activity.this, "Enter valid number!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });




        callerInfo = findViewById(R.id.callText);


        setCallingInformation();


        contacts = new HashMap<>();
        send = new ArrayList<>();

        adapter = new Contact_Adapter(this, send, new MyOCListener() {
            @Override
            public void onItemClicked(int position) {
                deleteItemFromDatabase(position);
            }
        });

        recyclerView = findViewById(R.id.contacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();

        contact = findViewById(R.id.contactGet);
        addContact = findViewById(R.id.addContact);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createContact(contact.getText().toString());
            }
        });
    }

    private void createContact(String contactString) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new LinkedHashSet<>());
        oldNumbers.add(contactString);
        editor.remove("enumbers");
        editor.putStringSet("enumbers",oldNumbers);
        editor.apply();

        contact.setText("");
        editor.apply();
        getData();

    }


    private void setCallingInformation(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String firstNumber = sharedPreferences.getString("firstNumber","null");

        if (firstNumber.isEmpty()||firstNumber.equalsIgnoreCase("null")){
            callerInfo.setText("Please add number.");
        }else {
            callerInfo.setText(firstNumber);
        }

    }




    private void deleteItemFromDatabase(int position) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new LinkedHashSet<>());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("enumbers");
        oldNumbers.remove(send.get(position));
        editor.putStringSet("enumbers",oldNumbers);
        editor.apply();
        getData();
    }



    private void getData() {
        send.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new LinkedHashSet<>());
        send.addAll(oldNumbers);
        adapter.notifyDataSetChanged();
    }
     */
}


