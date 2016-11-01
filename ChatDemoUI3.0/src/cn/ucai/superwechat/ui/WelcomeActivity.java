package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.ucai.superwechat.R;

public class WelcomeActivity extends BaseActivity {
    Button btnLogin,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        setListener();
    }

    private void setListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnLogin= (Button) findViewById(R.id.btn_welcome_login);
        btnSignUp= (Button) findViewById(R.id.btn_welcome_signUp);
    }
}
