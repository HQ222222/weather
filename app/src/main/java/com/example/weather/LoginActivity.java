package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit=findViewById(R.id.account);
        passwordEdit=findViewById(R.id.password);
        login=findViewById(R.id.login);
        rememberPass=findViewById(R.id.remember_pass);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Boolean isRemember=pref.getBoolean("remember_password",false);
        if (isRemember){
            String account =pref.getString("account","");
            String password =pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String account =accountEdit.getText().toString();
                String password=passwordEdit.getText().toString();
                if (account.equals("admin")&&password.equals("123456")){
                    editor=pref.edit();
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
            }else{
            editor.clear();
        }
        editor.apply();
        Intent intent =new Intent(LoginActivity.this,   MainActivity.class);
        startActivity(intent);
        finish();
    }else{
            Toast.makeText(LoginActivity.this, "account is error", Toast.LENGTH_SHORT).show();
        }
    }
});

    }
}
