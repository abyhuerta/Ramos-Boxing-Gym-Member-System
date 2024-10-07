package edu.utsa.cs3443.rowdy_rumble;

import static edu.utsa.cs3443.rowdy_rumble.model.Register.*;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.rowdy_rumble.model.DataBaseHelper;
import edu.utsa.cs3443.rowdy_rumble.model.Member;


/**
 * @author Otto Zoesch-Weigel
 * Activity for user registration.
 * Takes in the user information from the textboxes and validates it.
 * If valid, it put's it in the database as a new user.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button submit = (Button)findViewById(R.id.registerButton);

        EditText userNameInput = (EditText)findViewById(R.id.UsernameTextBox);
        EditText fullNameInput = (EditText)findViewById(R.id.FillNameTextBox);
        EditText DOBInput = (EditText)findViewById(R.id.DOBTextBox);
        EditText phoneNumberInput = (EditText)findViewById(R.id.PhoneNumberTextBox);
        EditText passwordInput = (EditText)findViewById(R.id.PasswordTextBox);
        EditText weightInput = (EditText)findViewById(R.id.WeightTextBox);
        EditText emergencyPhoneNumberInput = (EditText)findViewById(R.id.editTextText2);
        Context context = getApplicationContext();
        DataBaseHelper dbhelper = new DataBaseHelper(context);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userNameInput.getText().toString();
                String fullName = fullNameInput.getText().toString();
                String DOB = DOBInput.getText().toString();
                String phoneNumber = phoneNumberInput.getText().toString();
                String password = passwordInput.getText().toString();
                String weight = weightInput.getText().toString();
                String emergencyPhone = emergencyPhoneNumberInput.getText().toString();


                if (validatePhoneNumber(phoneNumber)) {
                    Toast.makeText(context, "invalid personal phone number", Toast.LENGTH_SHORT).show();
                } else if (validatePhoneNumber(emergencyPhone)) {
                    Toast.makeText(context, "invalid emergency phone number", Toast.LENGTH_SHORT).show();
                } else if (validateDate(DOB)) {
                    Toast.makeText(context, "invalid date of birth", Toast.LENGTH_SHORT).show();
//                } else if (validateNumber(age)) {
//                    Toast.makeText(context, "invalid age", Toast.LENGTH_SHORT).show();
                } else if (validateFloat(weight)) {
                    Toast.makeText(context, "invalid weight", Toast.LENGTH_SHORT).show();
                } else {
                    String age = Integer.toString(calculateAge(DOB));
                    Member Member = new Member(fullName, username, password, "0", "0", "0", "0", weight, age, DOB, emergencyPhone, phoneNumber);

                    try {
                        dbhelper.getOne(username);
                        Toast.makeText(context, "User Already Exists", Toast.LENGTH_SHORT).show();
                    } catch (IllegalArgumentException e) {
                        dbhelper.addOne(Member);
                        launchActivity();
                    }
                }
            }
        });
    }

    /**
     * Launches the main activity.
     */
    private void launchActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}