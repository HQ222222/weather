package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        btn5.setOnClickListener(this);

    }

    @Override
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
        }

    }
}
