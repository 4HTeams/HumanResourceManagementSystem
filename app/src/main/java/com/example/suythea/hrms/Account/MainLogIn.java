package com.example.suythea.hrms.Account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.suythea.hrms.Interfaces.MySupporter_Interface;
import com.example.suythea.hrms.MainActivity;
import com.example.suythea.hrms.R;
import com.example.suythea.hrms.Setting.MainSetting;
import com.example.suythea.hrms.Interfaces.Setting_Interface;
import com.example.suythea.hrms.Supporting_Files.MySqlite;
import com.example.suythea.hrms.Supporting_Files.MySupporter;
import com.example.suythea.hrms.Supporting_Files.MyVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainLogIn extends AppCompatActivity implements MySupporter_Interface {

    Toolbar toolbar;
    Button btnLogIn;
    EditText etUsername,etPassword;
    Setting_Interface setting_interface;
    TextView txtSignUp;
    public static MainLogIn context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_log_in);

        context = this;
        MySupporter.runFirstDefault(this);

        toolbar = (Toolbar) findViewById(R.id.toolBarNoSearch);
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        etPassword = (EditText) findViewById(R.id.etLogInPassword) ;
        etUsername = (EditText) findViewById(R.id.etLogInUsername) ;
        txtSignUp = (TextView)findViewById(R.id.tvSignUp);

        toolbar.setTitle("LogIn");

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(toolbar.getWindowToken(), 0);
                dataVolley();
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp); // your drawable
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainCreateUser.class);
                intent.putExtra("fromLogIn", "1");
                startActivity(intent);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        MySupporter.runFirstDefault(this);
    }

    public static void closeMe(){
        context.finish();
    }

    public MainLogIn(){
        this.setting_interface = MainSetting.context;
    }

    private void dataVolley(){

        Map<String, String> params = new HashMap<>();
        params.put("appToken","ThEa331RA369RiTH383thY925");
        params.put("username",etUsername.getText().toString());
        params.put("password",etPassword.getText().toString());

        String result = MySupporter.verifyControls(params);

        if (!result.equals("OK")){
            Snackbar.make(toolbar, result, Snackbar.LENGTH_LONG).show();
            return;
        }

        MySupporter.Volley("http://bongNU.myreading.xyz/bongnu/account/login.php", params, this);
        MySupporter.showLoading("Please wait.....");
    }

    @Override
    public void onHttpFinished(String response) {

    }

    @Override
    public void onHttpError(String message) {

    }

    @Override
    public void onVolleyFinished(String response) {
        try {
            JSONArray arrayDB = new JSONArray(URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"),"UTF-8"));

            JSONObject jsonObj = arrayDB.getJSONObject(0);

            if (jsonObj.getString("status").equals("Success")){
                MySqlite sqlite = new MySqlite(getBaseContext());
                sqlite.insertJsonDB(MySqlite.fields.get(0), response);

                if (jsonObj.getString("type").equals("1")){
                    setting_interface.changeToFragment("SEEKER_PROFILE");
                }
                else if (jsonObj.getString("type").equals("2")){
                    setting_interface.changeToFragment("COMPANY_PROFILE");
                }

                finish();
            }else{
                Snackbar.make(btnLogIn,jsonObj.getString("message"),Snackbar.LENGTH_LONG).show();
            }

        } catch(JSONException e){e.printStackTrace();} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        MySupporter.hideLoading();
    }

    @Override
    public void onVolleyError(String message) {

        MySupporter.hideLoading();
        Snackbar.make(toolbar, MySupporter.checkError(), Snackbar.LENGTH_LONG).show();

    }
}
