package com.example.dell.liziping20170124;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZhuCeActivity extends AppCompatActivity {

    private EditText mTel;
    private EditText mPwd;
    /**
     * 注册
     */
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        initView();
    }

    private void initView() {
        mTel = (EditText) findViewById(R.id.tel);
        mPwd = (EditText) findViewById(R.id.pwd);
        mBtn = (Button) findViewById(R.id.btn);
    }

    public void zhuCe(View view){
        String tel = mTel.getText().toString();
        String pwd = mPwd.getText().toString();
        if(TextUtils.isEmpty(tel)||tel.length()==0){
            Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String s="^1[24578]\\d{9}$";
        if(tel.matches(s)){

        }else {
            Toast.makeText(this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)||pwd.length()==0){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(pwd.length()>=6)){
            Toast.makeText(this, "密码不正确", Toast.LENGTH_SHORT).show();
            return;
        }

        //实例化OkhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //通过Formbody的builder对象设置请求的参数
        FormBody.Builder builder = new FormBody.Builder()
                .add("mobile",tel)
                .add("password",pwd);
        Request request = new Request.Builder().post(builder.build()).url("http://120.27.23.105/user/login").build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String jsonStr = response.body().string();
                    Log.d("----------",""+jsonStr);
                    startActivity(new Intent(ZhuCeActivity.this,MainActivity.class));
                }
            }
        });
    }
}
