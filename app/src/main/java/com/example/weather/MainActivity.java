package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("msg", "我是一条信息");
        LitePal.initialize(this);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });

        Button addData= findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setName("hq");
                book.setAuthor("hqhq");
                book.setPages(454);
                book.setPrice(180.94);
                book.setPress("Unknow");
                book.save();
            }
        });


        Button updateData=findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book =new Book();/*
                book.setName("the lost");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.09);
                book.setPress("Unknow");
                book.save();
                book.setPrice(10.99);
                book.save();*/
                book.setPrice(14.95);//修改后的内容
                book.setPress("Anchor");
                book.updateAll("name= ?","hq");//条件

            }
        });



        Button deleteData=findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price< ?","50");//条件


            }
        });



        Button queryButton =findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=LitePal.findAll(Book.class);
                for (Book book: books){
                    Log.d("MainActivity","book name is "+book.getName());
                    Log.d("MainActivity","book author is "+book.getName());
                    Log.d("MainActivity","book pages is "+book.getPages());
                    Log.d("MainActivity","book price is "+book.getPrice());
                    Log.d("MainActivity","book press is "+book.getPress());

                }
            }
        });

    }
}
