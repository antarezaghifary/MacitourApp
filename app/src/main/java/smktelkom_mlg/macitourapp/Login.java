package smktelkom_mlg.macitourapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText username,password;
    String strUser,strPass;
    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.etUser);
        password = (EditText) findViewById(R.id.etPass);
        login = (Button) findViewById(R.id.btnLogin);
        signup = (Button) findViewById(R.id.btnSignup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUser  = username.getText().toString();
                strPass = password.getText().toString();
                if(strUser.isEmpty()){
                    username.setError("Username Harus Diisi");
                }else if(strPass.isEmpty()){
                    password.setError("Password Harus Diisi");
                }else{
                    // VALIDASI
                   // String KeyPass = helper.LoginPass(str);
                    String KeyUser = helper.LoginUser(strUser);

                    if(strPass.equals(KeyUser)) {

                        Intent i = new Intent(Login.this, MainActivity.class);
                        i.putExtra("username", strUser);
                        startActivity(i);
                        finish();
                    }else{
                        toastGagal();
                    }
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void toastGagal() {
        Toast.makeText(this, "Username Atau Password Salah",Toast.LENGTH_SHORT).show();
    }
    private void toastSukses() {
        Toast.makeText(this, "Login Sukses",Toast.LENGTH_SHORT).show();
    }
}
