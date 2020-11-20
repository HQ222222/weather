package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;


import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edit=findViewById(R.id.editText);
        String inputText=load();

        Button saveData=findViewById(R.id.save_data);
        saveData.setOnClickListener(v ->{
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putInt("age",22);
                editor.putBoolean("married",false);
                editor.apply();
            }
        );



        Button restoreData=findViewById(R.id.restore_data);
        restoreData
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences pref=getSharedPreferences("data",MODE_PRIVATE);
                        String name=pref.getString("name","");
                        int age =pref.getInt("age",0);
                        boolean married=pref.getBoolean("married",false);
                        Log.d("ma2","name is "+ name);
                        Log.d("ma2","age is "+age);
                        Log.d("ma2","married  state is "+married);

                    }
                });


     /*   Stetho.initializeWithDefaults(this);
        Log.d("msg", "我是一条信息");
        LitePal.initialize(this);
        Button btn1 = findViewById(R.id.create_database);
        btn1.setOnClickListener(this);
        Button btn2 = findViewById(R.id.add_data);
        btn2.setOnClickListener(this);
        Button btn3 = findViewById(R.id.query_data);
        btn3.setOnClickListener(this);
        Button btn4 = findViewById(R.id.delete_data);
        btn4.setOnClickListener(this);
        Button btn5 = findViewById(R.id.update_data);
        btn5.setOnClickListener(this);*/

    }



    public String load(){
        FileInputStream in =null;
        BufferedReader render=null;
        StringBuilder content =new StringBuilder();
        try {
            in=openFileInput("data");
            render =new BufferedReader(new InputStreamReader(in));
            String line="";
            while((line=render.readLine())!=null){
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(render !=null){
                try {
                    render.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText=edit.getText().toString();
        save(inputText);
    }

    private void save(String inputText) {

        FileOutputStream out =null;
        BufferedWriter writer =null;
        try {


            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer =new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(writer !=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /*  @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.create_database:
                Connector.getDatabase();
                break;
            case R.id.add_data:
                Book book=new Book();
                book.setName("hq");
                book.setAuthor("hqhq");
                book.setPages(454);
                book.setPrice(16.94);
                book.setPress("Unknow");
                book.save();
                break;
            case R.id.delete_data:
                DataSupport.deleteAll(Book.class,"price<?","15");
                break;
            case R.id.update_data:
                Book book2 =new Book();
                book2.setPrice(14.95);
                book2.setPress("Anchor");
                book2.updateAll("name=? and author=?","the lost Symbol","Dan Brown");
                break;
            case R.id.query_data:
                List<Book> books=DataSupport.findAll(Book.class);
                for (Book book4: books){
                    Log.d("MainActivity","book name is "+book4.getName());
                    Log.d("MainActivity","book author is "+book4.getName());
                    Log.d("MainActivity","book pages is "+book4.getPages());
                    Log.d("MainActivity","book price is "+book4.getPrice());
                    Log.d("MainActivity","book press is "+book4.getPress());

                }
                break;


    }*/
  }
