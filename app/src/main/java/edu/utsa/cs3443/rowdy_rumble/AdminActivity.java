package edu.utsa.cs3443.rowdy_rumble;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.utsa.cs3443.rowdy_rumble.model.DataBaseHelper;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.*;

import edu.utsa.cs3443.rowdy_rumble.model.Member;
import edu.utsa.cs3443.rowdy_rumble.model.Admin;

/**
 * Author: FGU066, Jose Perdomo
 * this is a controller class for our activity model class which will allow the admin to be able to interact with all memebers and their stats
 */
public class AdminActivity extends AppCompatActivity {
// attributes for the adminActivitu class
    private int position = 0;
    private ArrayList<Member> memberList = new ArrayList<>();
    private TextView dynamicName;
    private EditText dynamicAttendance;
    private EditText dynamicRecord;
    private EditText dynamicProRec;
    private EditText dynamicUser;
    private EditText dynamicDOB;
    private EditText dynamicEContact;
    private EditText dynamicUserContact;
    private EditText dynamicWeight;
    private EditText dynamicAmRec;
    private HashMap<EditText, String> editTextMap = new HashMap<>();

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);
        //declare/initialize all Var
        Context context = getApplicationContext();
        DataBaseHelper helper = new DataBaseHelper(context);
        memberList = helper.getEveryone();
        Admin adminCam = new Admin("Admin", "onlyAdm1n", "password", memberList);
        SearchView searchView;
        searchView = findViewById(R.id.SearchView);
        searchView.clearFocus();
        Button HomeButton = findViewById(R.id.HomeButton);
        Button updateButton = findViewById(R.id.updateButton);
        Button DeleteButton = findViewById(R.id.deleteButton);
        dynamicName = findViewById(R.id.boxerNameDynamic);
        dynamicAttendance = findViewById(R.id.attendanceDynamic);
        dynamicRecord = findViewById(R.id.dynamicRecord);
        dynamicProRec = findViewById(R.id.proRecordDynamic);
        dynamicUser = findViewById(R.id.BoxerUserNameDynamic);
        dynamicDOB = findViewById(R.id.DOBDynamic);
        dynamicEContact = findViewById(R.id.EmergencyContactDynamic);
        dynamicUserContact = findViewById(R.id.BoxerContactDynamic);
        dynamicWeight = findViewById(R.id.BoxerWeightDynamic);
        dynamicAmRec = findViewById(R.id.amRecordDynamic);
        //clears the sample Text
        clearTextViews();
        //app Logic
        adminCam.setHelper(helper);
        adminCam.setMemberList(memberList);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //search bar logic
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * This method will search through the filtered list and return a member once the user submits there search
             * and save the data the admin edits
             * @param query the query text that is to be submitted
             * @return
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                saveEditText(dynamicRecord, position, "Total");
                saveEditText(dynamicProRec, position, "ProWins");
                saveEditText(dynamicAmRec, position, "AmWins");
                saveEditText(dynamicWeight, position, "Weight");
                saveEditText(dynamicEContact, position, "EmergencyContact");
                saveEditText(dynamicUserContact, position, "MemberContact");
                return true;
            }
            /**
             * this method simply saves the content a admin might change but the search method is not used in this onQueryTextChange
             * @param newText the new content of the query text field.
             *
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        /**
         * Update button to commit changes of Admin into the DB
         * params updateOne(member member) memberlist.get(position) returns the member that was searced
         *
         */


        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberList.get(position).setTotal(editTextMap.get(dynamicRecord));
                memberList.get(position).setAmWins(editTextMap.get(dynamicAmRec));
                memberList.get(position).setProWins(editTextMap.get(dynamicProRec));
                memberList.get(position).setMemberContact(editTextMap.get(dynamicUserContact));
                memberList.get(position).setEmergencyContact(editTextMap.get(dynamicEContact));
                memberList.get(position).setWeight(editTextMap.get(dynamicWeight));
                helper.updateOne(memberList.get(position));
                Toast.makeText(context,"User Updated!",Toast.LENGTH_LONG).show();
            }
        });
        /**
         * this button logic is for when the admin needs to remove someone from the list this will remove them from our list and our
         * database
         */
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextViews();
                editTextMap.put(dynamicWeight,"");
                editTextMap.put(dynamicRecord,"");
                editTextMap.put(dynamicEContact,"");
                editTextMap.put(dynamicUserContact,"");
                editTextMap.put(dynamicAmRec,"");
                editTextMap.put(dynamicProRec,"");
                helper.deleteOne(memberList.get(position));
                adminCam.removeMember(memberList.get(position));
            }
        });
        /**
         * This button simply logs out the admin
         */
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Method to creates a filtered list for the admin to search through
     * @param text
     */
    private void filterList(String text) {
        boolean memberFound = false;
        for (Member boxer : memberList) {
            if (boxer.getUserName() != null) {
                if (boxer.getUserName().contains(text)) {
                    populateMemberInfo(boxer);
                    memberFound = true;
                    position = memberList.indexOf(boxer);
                    break;
                }
            }
        }
        if (!memberFound) {
            Toast.makeText(this, "No boxer found", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Method made to save the changes made in each EditText using their setters
     * @param dynamicText
     * @param i
     * @param propertyName
     */
    private void saveEditText(EditText dynamicText, int i, String propertyName) {
        dynamicText.addTextChangedListener(new TextWatcher() {
            //UNUSED
            long startTimeMillis; // Track start time for timing user changes
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            startTimeMillis=System.currentTimeMillis();
            }

            //UNUSED
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            /**
             * the method used to save the text for each stat of a member
             *
             * @param s
             */
            @Override
            public void afterTextChanged(Editable s) {
                String userInput = s.toString();
                if (!memberList.isEmpty()) {
                    long endTimeMillis = System.currentTimeMillis();
                    long editTime = endTimeMillis - startTimeMillis;
                    if(editTime>=3){
                        editTextMap.put(dynamicText,userInput);
                    }
                }
            }
        });
    }

    /**
     * Dynamically populates member info from list
     * @param boxer
     */
    private void populateMemberInfo(Member boxer) {
        String weight;
        if(boxer.getWeight().contains("lb")){
             weight = (boxer.getWeight());
        }else {
             weight = (boxer.getWeight()) + " lb";
        }
        String attendance = (boxer.getCheckIns()) + " Days";

        TextView dynamicName = findViewById(R.id.boxerNameDynamic);
        dynamicName.setText(boxer.getName());

        EditText dynamicRecord = findViewById(R.id.dynamicRecord);
        dynamicRecord.setText(boxer.getTotal());

        EditText proRecordDynamic = findViewById(R.id.proRecordDynamic);
        proRecordDynamic.setText(boxer.getProWins());

        EditText dynamicAmRecord = findViewById(R.id.amRecordDynamic);
        dynamicAmRecord.setText(boxer.getAmWins());

        EditText dynamicWeight = findViewById(R.id.BoxerWeightDynamic);
        dynamicWeight.setText(weight);

        EditText dynamicAttendance = findViewById(R.id.attendanceDynamic);
        dynamicAttendance.setText(attendance);

        EditText dynamicDOB = findViewById(R.id.DOBDynamic);
        dynamicDOB.setText(boxer.getDob());

        EditText dynamicEcontact = findViewById(R.id.EmergencyContactDynamic);
        dynamicEcontact.setText(boxer.getEmergencyContact());

        EditText dynamicNumber = findViewById(R.id.BoxerContactDynamic);
        dynamicNumber.setText(boxer.getMemberContact());

        EditText dynamicUserName = findViewById(R.id.BoxerUserNameDynamic);
        dynamicUserName.setText(boxer.getUserName());

        editTextMap.put(dynamicRecord,dynamicRecord.getText().toString());
        editTextMap.put(dynamicProRec,dynamicProRec.getText().toString());
        editTextMap.put(dynamicAmRecord,dynamicAmRecord.getText().toString());
        editTextMap.put(dynamicUserContact,dynamicUserContact.getText().toString());
        editTextMap.put(dynamicEcontact,dynamicEcontact.getText().toString());
        editTextMap.put(dynamicWeight,dynamicWeight.getText().toString());
    }
    public void clearTextViews(){
        dynamicName.setText("");
        dynamicAttendance.setText("");
        dynamicRecord.setText("");
        dynamicProRec.setText("");
        dynamicAmRec.setText("");
        dynamicUser.setText("");
        dynamicDOB.setText("");
        dynamicEContact.setText("");
        dynamicUserContact.setText("");
        dynamicWeight.setText("");
    }
}
