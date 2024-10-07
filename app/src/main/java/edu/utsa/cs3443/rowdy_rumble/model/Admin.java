package edu.utsa.cs3443.rowdy_rumble.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Author: FGU066, Jose Perdomo
 *The Admin class is a representation of the admin of a gym who has access to all member data
 */
public class Admin extends User {
    //admin Attributes
    private Context context;

    private ArrayList <Member> memberList = new ArrayList<>();
    private DataBaseHelper helper = new DataBaseHelper(context);

    /**
     * constructor for the admin class
     * @param name
     * @param userName
     * @param passWord
     * @param memberList
     */
   public Admin(String name, String userName, String passWord, ArrayList<Member>
                memberList){
       super(name,userName,passWord);
       this.memberList=memberList;
   }

    /**
     * getter for the Member list
     * @return
     */
    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    /**
     * getter for context
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * getter fo the database helper
     * @return
     */
    public DataBaseHelper getHelper() {
        return helper;
    }

    /**
     * getter for a users password
     * @return
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     * getter for the users real name
     * @return
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * getter for the username of a user
     * @return
     */
    @Override
    public String getUserName() {
        return super.getUserName();
    }

    /**
     * setter for the username of a user
     * @param userName
     */
    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    /**
     * setter for the users password
     * @param password
     */
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    /**
     * setter for the users name
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName(name);
   }

    /**
     * setter for context
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * a setter for the data base
     * @param helper
     */
    public void setHelper(DataBaseHelper helper) {
        this.helper = helper;
    }

    /**
     * setter for the member list
     * @param memberList
     */
    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = helper.getEveryone();
    }

    /**
     * Removes a member from the list of memebers
     * @param oldMember
     */
    public void removeMember(Member oldMember){
        memberList.remove(oldMember);
    }


}
