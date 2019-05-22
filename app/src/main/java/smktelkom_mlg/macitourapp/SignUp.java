package smktelkom_mlg.macitourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUp extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    private EditText name,username,pass,confpass,email;
    private String strName,strUser,strPass,strEmail,strConf;
    private Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.etName);
        username = (EditText) findViewById(R.id.etUser);
        pass = (EditText) findViewById(R.id.etPass);
        email = (EditText) findViewById(R.id.etEmail);
        confpass = (EditText) findViewById(R.id.etConfPass);
        signup = (Button) findViewById(R.id.btnSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strName = name.getText().toString();
                strEmail = email.getText().toString();
                strUser = username.getText().toString();
                strPass = pass.getText().toString();
                strConf = confpass.getText().toString();
                if(strName.isEmpty()){
                    name.setError("Nama Harus Diisi");
                }else if(strEmail.isEmpty()){
                    email.setError("Email Harus Diisi");
                }else if(strUser.isEmpty()){
                    username.setError("Username Harus Diisi");
                }else if(strPass.isEmpty()){
                    pass.setError("Password Harus Diisi");
                }else if(strConf.isEmpty()){
                    confpass.setError("Konfirmasi Password Harus Diisi");
                }else if(!strPass.equals(strConf)){
                    toast();
                }else{
                    // SAVE DATABASE
                    DataUser data = new DataUser();
                    data.setName(strName);
                    data.setEmail(strEmail);
                    data.setUsername(strUser);
                    data.setPassword(strPass);
                    helper.insertUser(data);
                    Intent i = new Intent(SignUp.this, Login.class);
                    startActivity(i);
                    toastSukses();
                    finish();
                }
            }
        });
    }
    public void toast(){
        Toast.makeText(this, "Password Tidak Cocok", Toast.LENGTH_SHORT).show();
    }

    public void toastSukses() {
        Toast.makeText(this, "Sign Up Sukses !", Toast.LENGTH_SHORT).show();
    }
}
