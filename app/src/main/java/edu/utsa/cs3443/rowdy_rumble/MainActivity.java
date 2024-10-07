package edu.utsa.cs3443.rowdy_rumble;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.utsa.cs3443.rowdy_rumble.model.DataBaseHelper;
import edu.utsa.cs3443.rowdy_rumble.model.Member;

/**
 * The MainActivity class represents the main entry point of the Rowdy Rumble application.
 * It handles user authentication and navigation to different activities based on user input.
 */
public class MainActivity extends AppCompatActivity {
    /** The key used for passing intent data. */
    public static String intentkey;
    /**
     * Retrieves the intent key.
     * @return The intent key.
     */
    public static String decodeIntent() {return intentkey;}
    /**
     * Launches the StatsDisplayActivity with the provided username.
     * @param usernameNameField The username.
     * @param PasswordField The password.
     */

    public void launchActivity(String usernameNameField, String PasswordField){
        Intent i = new Intent(this, StatsDisplayActivity.class);
        i.putExtra(intentkey,String.valueOf(usernameNameField));
        startActivity(i);
    }
    /**
     * Launches the RegisterActivity.
     */
    public void launchActivity2(){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }
    /**
     * Launches the AdminActivity.
     */

    public void luanchadmin(){
        Intent i = new Intent(this, AdminActivity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context = getApplicationContext();
        DataBaseHelper dbhelper = new DataBaseHelper(context);
        // create new db helper
        // when User enters a username pass username and if username is == username in db then it returns
        // newMember.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userNameField = (EditText)findViewById(R.id.usernameField);
        EditText PasswordField = (EditText)findViewById(R.id.passwordField);
        //member Member;

        Button SignInBut = (Button)findViewById(R.id.button2);
        SignInBut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = userNameField.getText().toString();
                String password = PasswordField.getText().toString();

                if (username.equals("Admin") && password.equals("password")) {
                    luanchadmin();
                } else {
                    try {
                        Member Member = dbhelper.getOne(username);
                        if (Member.getPassword().equals(password)){
                            launchActivity(username, password);
                        } else {
                            Toast.makeText(context, "Incorrect Password", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IllegalArgumentException exception) {
                        Toast.makeText(context, "Username not found", Toast.LENGTH_SHORT).show();

                    }

                    //do logic in model file
                }
            }
        });
        Button RegisterBut = (Button)findViewById(R.id.button3);
    RegisterBut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchActivity2();
        }
    });


    }
}