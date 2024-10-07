package edu.utsa.cs3443.rowdy_rumble.model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * @author Otto Zoesch-Weigel
 * Database tools so that other models can interface with the database.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String MEMBER_TABLE = "member_table";
    public static final String MEMBER_NAME = "member_name";
    public static final String MEMBER_WEIGHT = "member_weight";
    public static final String MEMBER_USERNAME = "member_username";

    public static final String MEMBER_PASSWORD = "member_password";
    public static final String MEMBER_AMWINS = "member_amwins";
    public static final String MEMBER_PROWINDS = "member_prowinds";
    public static final String MEMBER_TOTALMATCHES = "member_total";
    public static final String TIMESTAMP = "timestamp";
    public static final String MEMBER_CHECKINS = "member_checkins";
    public static final String MEMBER_AGE = "member_age";
    public static final String MEMBER_CONTACT = "member_contact";
    public static final String MEMBER_DOB = "member_dob";
    public static final String MEMBER_EMERGENCY_CONTACT = "member_emergency_contact";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "member.db", null, 1);
    }

    /**
     * Called the first time the database is accessed. Should be code in here to create a database
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + MEMBER_TABLE + " (" + MEMBER_USERNAME + " TEXT PRIMARY KEY" +
                ", " + MEMBER_NAME + " TEXT, " + MEMBER_WEIGHT + " TEXT, " + MEMBER_PASSWORD + " TEXT," +
                " " + MEMBER_AMWINS + " TEXT, " + MEMBER_PROWINDS + " TEXT, " + MEMBER_TOTALMATCHES + " TEXT, " +
                MEMBER_CHECKINS + " TEXT, " + MEMBER_AGE + " TEXT, " + MEMBER_DOB + " TEXT, " + MEMBER_EMERGENCY_CONTACT + " TEXT, " +
                MEMBER_CONTACT + " TEXT, " + TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(createTableStatement);
    }

    /**
     * Called if the database version number changes. It prevents previous apps from breaking when you change the database design.
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + MEMBER_TABLE);
        onCreate(db);
    }

    /**
     * adds one member to the database
     * @param Member   the member you would like to add
     * @return boolean whether or not the addition was succesful
     */
    public boolean addOne(Member Member) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MEMBER_NAME, Member.getName());
        cv.put(MEMBER_WEIGHT, Member.getWeight());
        cv.put(MEMBER_PASSWORD, Member.getPassword());
        cv.put(MEMBER_USERNAME, Member.getUserName());
        cv.put(MEMBER_CHECKINS, Member.getCheckIns());
        cv.put(MEMBER_AMWINS, Member.getAmWins());
        cv.put(MEMBER_PROWINDS, Member.getProWins());
        cv.put(MEMBER_TOTALMATCHES, Member.getTotal());
        cv.put(MEMBER_AGE, Member.getAge());
        cv.put(MEMBER_DOB, Member.getDob());
        cv.put(MEMBER_EMERGENCY_CONTACT, Member.getEmergencyContact());
        cv.put(MEMBER_CONTACT, Member.getMemberContact());

        long insert = db.insert(MEMBER_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return false;
        }

    }

    /**
     * deletes a single member
     * @param Member   Member you wish to delete
     * @return boolean Whether on not deletion was succesful
     */
    public boolean deleteOne(Member Member) {

        SQLiteDatabase db = this.getWritableDatabase();
            String queryString = "DELETE FROM " + MEMBER_TABLE + " WHERE " + MEMBER_USERNAME + " = " + "'" + Member.getUserName() + "'";

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * get all the members in the database
     * @return returnList arrayList of all the members in the database
     */
    public ArrayList<Member> getEveryone() {
        ArrayList<Member> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + MEMBER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {

                String username = cursor.getString(0);
                String name = cursor.getString(1);
                String weight = cursor.getString(2);
                String password = cursor.getString(3);
                String amwins = cursor.getString(4);
                String prowins = cursor.getString(5);
                String total = cursor.getString(6);
                String checkins = cursor.getString(7);
                String age = cursor.getString(8);
                String dob = cursor.getString(9);
                String emergency_contact = cursor.getString(10);
                String contact = cursor.getString(11);

                Member newMember = new Member(name, username, password, amwins, prowins, total, checkins, weight, age, dob, emergency_contact, contact);
                returnList.add(newMember);

            } while (cursor.moveToNext());
        } else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    /**
     * Get member by username
     * @throws IllegalArgumentException Thrown when no member goes by the username.
     *                                  I recommend this is caught to determine if
     *                                  a member exists with the given username.
     * @param username                  Username for the member that is being gotten.
     * @return member                   Member that goes by the given username
     */
    public Member getOne(String username){
        Member newMember;

        String queryString = "SELECT * FROM " + MEMBER_TABLE + " WHERE " + MEMBER_USERNAME + " = " + "'" + username + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            String usernameFromDB = cursor.getString(0);
            String name = cursor.getString(1);
            String weight = cursor.getString(2);
            String password = cursor.getString(3);
            String amwins = cursor.getString(4);
            String prowins = cursor.getString(5);
            String total = cursor.getString(6);
            String checkins = cursor.getString(7);
            String age = cursor.getString(8);
            String dob = cursor.getString(9);
            String emergency_contact = cursor.getString(10);
            String contact = cursor.getString(11);

            newMember = new Member(name, usernameFromDB, password, amwins, prowins, total, checkins, weight, age, dob, emergency_contact, contact);
        } else {
            throw new IllegalArgumentException("Not a member: " + username);
        }

        cursor.close();
        db.close();
        return newMember;
    }

    /**
     * Updates one member in the database
     * @param Member
     */
    public void updateOne(Member Member){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MEMBER_NAME, Member.getName());
        cv.put(MEMBER_WEIGHT, Member.getWeight());
        cv.put(MEMBER_PASSWORD, Member.getPassword());
        cv.put(MEMBER_USERNAME, Member.getUserName());
        cv.put(MEMBER_CHECKINS, Member.getCheckIns());
        cv.put(MEMBER_AMWINS, Member.getAmWins());
        cv.put(MEMBER_PROWINDS, Member.getProWins());
        cv.put(MEMBER_TOTALMATCHES, Member.getTotal());
        cv.put(MEMBER_AGE, Member.getAge());
        cv.put(MEMBER_DOB, Member.getDob());
        cv.put(MEMBER_EMERGENCY_CONTACT, Member.getEmergencyContact());
        cv.put(MEMBER_CONTACT, Member.getMemberContact());

        String clause = MEMBER_USERNAME + " = '" + Member.getUserName() + "'";

        long insert = db.update(MEMBER_TABLE, cv, clause, null);


//        String queryString = "UPDATE " + MEMBER_TABLE + " SET " +
//                MEMBER_AGE + " = '" + Member.getAge() + "', " +
//                MEMBER_AMWINS + " = '" + Member.getAmWins() + "', " +
//                MEMBER_NAME + " = '" + Member.getName() + "', " +
//                MEMBER_DOB + " = '" + Member.getDob() + "', " +
//                MEMBER_PASSWORD + " = '" + Member.getPassword() + "', " +
//                MEMBER_EMERGENCY_CONTACT + " = '" + Member.getEmergencyContact() + "', " +
//                MEMBER_CONTACT + " = '" + Member.getMemberContact() + "', " +
//                MEMBER_PROWINDS + " = '" + Member.getProWins() + "', " +
//                MEMBER_WEIGHT + " = '" + Member.getWeight() + "', " +
//                MEMBER_CHECKINS + " = '" + Member.getCheckIns() + "', " +
//                MEMBER_TOTALMATCHES + " = '" + Member.getTotal() + "'" +
//                " WHERE " + MEMBER_USERNAME + " = '" + Member.getUserName() + "', " ;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(queryString, null);
//
//        cursor.close();
//        db.close();
    }
}