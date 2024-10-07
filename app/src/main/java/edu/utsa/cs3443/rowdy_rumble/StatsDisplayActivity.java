package edu.utsa.cs3443.rowdy_rumble;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import edu.utsa.cs3443.rowdy_rumble.model.DataBaseHelper;
import edu.utsa.cs3443.rowdy_rumble.model.Member;
/**
 * @author ghy212 Aby Huerta
 * Activity for displaying statistics of a specific member.
 * This activity retrieves member information from a database
 * and displays it in various TextViews. It also allows the user
 * to start a workout session and update check-ins.
 */
public class StatsDisplayActivity extends AppCompatActivity {

    private String username;
    private static final String intentkey = "username";
    /**
     * Called when the activity is first created. This method initializes the layout,
     * retrieves member information from the intent, and sets up TextViews to display the
     * member's statistics.
     *
     * @param savedInstanceState The saved instance state from a previous instance, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stats_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //need to make a method to send me information about the logged in User
        //get username string from main activity (PLACEHOLDER for now)
        username = getIntent().getStringExtra(MainActivity.decodeIntent());


        Context context = getApplicationContext();
        Member loggedInmember = getmemberDB(username, context);


        // get Textviews
        TextView user = (TextView)findViewById(R.id.welcomeuser);
        TextView proWins = (TextView)findViewById(R.id.prowins);
        TextView amWins = (TextView)findViewById(R.id.amwins);
        TextView weight = (TextView)findViewById(R.id.weightclass);
        TextView age = (TextView)findViewById(R.id.age);
        TextView attendance = (TextView)findViewById(R.id.attendance);
        TextView datebirth = (TextView)findViewById(R.id.DOB);
        TextView emergencynum = (TextView)findViewById(R.id.Emergencycontact);
        TextView membernum = (TextView)findViewById(R.id.BoxerNumber);
        //get button
        Button startworkoutbtn = (Button)findViewById(R.id.startWorkout);

        //update text views with member data
        String[] tokens = loggedInmember.getName().split(" ");
        user.append(tokens[0]);
        proWins.append(" " + loggedInmember.getProWins());
        amWins.append(" " + loggedInmember.getAmWins());
        weight.append(" " + loggedInmember.getWeight());
        age.append(" " + loggedInmember.getAge());
        attendance.append(" " + loggedInmember.getCheckIns());
        datebirth.append(" " + loggedInmember.getDob());
        emergencynum.append(" " + loggedInmember.getEmergencyContact());
        membernum.append(" " + loggedInmember.getMemberContact());
            startworkoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Enjoy your workout", Toast.LENGTH_LONG).show();
                    updateCheckins(loggedInmember, context);
                    attendance.setText(R.string.boxer_attendance);
                    attendance.append(" " + loggedInmember.getCheckIns());
                    logout();


                }
            });
    }
    /**
     * Updates the check-ins count for a given member in the database.
     *
     * @param currMember The current member whose check-ins are to be updated.
     * @param context The context used to interact with the database.
     */
    public void updateCheckins(Member currMember, Context context){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        int currentCheckins = Integer.parseInt(currMember.getCheckIns());
        currentCheckins++;
        currMember.setCheckIns(String.valueOf(currentCheckins));
        dataBaseHelper.updateOne(currMember);
    }
    /**
     * Logs the user out by redirecting to the MainActivity.
     */
    public void logout(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    /**
     * Retrieves a Member object from the database using the given username.
     *
     * @param userName The username to look up.
     * @param context The context used to interact with the database.
     * @return The Member object corresponding to the given username.
     */
    public Member getmemberDB(String userName, Context context){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
            return dataBaseHelper.getOne(userName);
    }
}