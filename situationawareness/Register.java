package ffiandroid.situationawareness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import ffiandroid.situationawareness.service.UserService;
import ffiandroid.situationawareness.service.impl.SoapUserService;

/**
 * This file is part of project: Situation Awareness
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 2/20/15. responsible for this file: GuoJunjun
 */
public class Register extends ActionBarActivity {
    private String userid, username, userpass;
    private UserService userService = new SoapUserService();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    /**
     * handle user register
     *
     * @param view
     */
    public void registerClicked(View view) {
        if (validateInput()) {
            Toast.makeText(getBaseContext(), "connecting database ... ", Toast.LENGTH_LONG).show();
            new Thread(registerThread).start();
        }
    }

    /**
     * @return true if input value is valid false otherwise
     */
    private boolean validateInput() {
        EditText editTextUsername = (EditText) findViewById(R.id.register_user_name);
        EditText editTextUserid = (EditText) findViewById(R.id.register_user_id);
        EditText editTextUserpassword = (EditText) findViewById(R.id.register_user_password);
        EditText editTextUserpasswordR = (EditText) findViewById(R.id.register_user_password_repeat);

        String userpass = editTextUserpassword.getText().toString();

        if (userpass.equals(editTextUserpasswordR.getText().toString())) {
            this.userid = editTextUserid.getText().toString();
            this.username = editTextUsername.getText().toString();
            this.userpass = editTextUserpassword.getText().toString();
            return true;
        } else {
            Toast.makeText(this, "Make user you enter the same password !", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private Runnable registerThread = new Runnable() {
        @Override
        public void run() {
            Looper.prepare();
            try {
                String message = userService.register(userid, userpass, username, "1");
                JSONObject jsonMessage = new JSONObject(message);
                if (message != null && jsonMessage.get("desc").equals("success")) {
                    Toast.makeText(getBaseContext(), "register succeed, move to Login screen.", Toast.LENGTH_SHORT).show();
                    gotoLogin();
                } else {
                    Toast.makeText(getBaseContext(), "connecting server failed, please try again ! " + jsonMessage.get("desc"),
                            Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
            }
            Looper.loop();
        }
    };

    /**
     * to to login screen
     */
    private void gotoLogin() {
        startActivity(new Intent(this, Login.class));
    }

    /**
     * go to Login screen
     *
     * @param view
     */
    public void backtoLoginClicked(View view) {
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_login:
                startActivity(new Intent(this, Login.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
