package edu.utsa.cs3443.rowdy_rumble.model;
/**
 * @author ghy212 Aby Huerta
 * Represents a Member, which extends the User class and adds additional information
 * specific to a club or organization, such as match records, personal data, and contact information.
 */

public class Member extends User {
    private String amWins;
    private String proWins;
    private String totalMatches;
    private String checkIns;
    private String weight;
    private String age;
    private String dob;
    private String emergencyContact;
    private String memberContact;

    /**
     * Constructs a new Member with the given attributes.
     *
     * @param name The name of the member.
     * @param userName The username for the member.
     * @param passWord The password for the member.
     * @param amwins The number of amateur wins for the member.
     * @param prowins The number of professional wins for the member.
     * @param total The total number of matches for the member.
     * @param checkIns The number of check-ins for the member.
     * @param weight The weight of the member.
     * @param age The age of the member.
     * @param dob The date of birth of the member.
     * @param emergencyContact The emergency contact number for the member.
     * @param userContact The primary contact number for the member.
     */
    public Member(String name, String userName, String passWord, String amwins, String prowins, String total, String checkIns, String weight, String age, String dob, String emergencyContact, String userContact){
        super(name,userName,passWord);
        this.amWins = amwins;
        this.proWins = prowins;
        this.totalMatches = total;
        this.checkIns = checkIns;
        this.weight = weight;
        this.age=age;
        this.dob = dob;
        this.emergencyContact = emergencyContact;
        this.memberContact = userContact;

    }

    //default values for testing.
//    public member(){
//        this.name = "User";
//        this.userName = "default_userName";
//        this.passWord = "default_password";
//        this.amWins = "5";
//        this.proWins = "5";
//        this.totalMatches = "15";
//        this.checkIns = "101";
//        this.weight = "143.7";
//        this.age= "20";
//        this.dob = "01/01/9999";
//        this.emergencyContact = "(210)-999-1100";
//        this.memberContact = "(210)-999-1100";
//    }
    /**
     * Gets the number of amateur wins for the member.
     *
     * @return The number of amateur wins.
     */
    public String getAmWins() {
        return amWins;
    }
    /**
     * Gets the number of professional wins for the member.
     *
     * @return The number of professional wins.
     */
    public String getProWins() {
        return proWins;
    }
    /**
     * Gets the total number of matches for the member.
     *
     * @return The total number of matches.
     */
    public String getTotal() {
        return totalMatches;
    }
    /**
     * Gets the number of check-ins for the member.
     *
     * @return The number of check-ins.
     */
    public String getCheckIns() {
        return checkIns;
    }
    /**
     * Gets the weight of the member.
     *
     * @return The weight of the member.
     */
    public String getWeight() {
        return weight;
    }
    /**
     * Gets the age of the member.
     *
     * @return The age of the member.
     */
    public String getAge() {
        return age;
    }
    /**
     * Gets the date of birth of the member.
     *
     * @return The date of birth of the member.
     */
    public String getDob() {
        return dob;
    }
    /**
     * Gets the emergency contact number for the member.
     *
     * @return The emergency contact number.
     */
    public String getEmergencyContact() {
        return emergencyContact;
    }
    /**
     * Gets the primary contact number for the member.
     *
     * @return The primary contact number.
     */
    public String getMemberContact() {
        return memberContact;
    }
    /**
     * Sets the number of amateur wins for the member.
     *
     * @param wins The new number of amateur wins.
     */
    public void setAmWins(String wins) {
        this.amWins = wins;
    }
    /**
     * Sets the number of professional wins for the member.
     *
     * @param proWins The new number of professional wins.
     */
    public void setProWins(String proWins) {
        this.proWins = proWins;
    }
    /**
     * Sets the number of check-ins for the member.
     *
     * @param checkIns The new number of check-ins.
     */
    public void setCheckIns(String checkIns) {
        this.checkIns = checkIns;
    }
    /**
     * Sets the weight of the member.
     *
     * @param weight The new weight.
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }
    /**
     * Sets the age of the member.
     *
     * @param age The new age.
     */
    public void setAge(String age) {
        this.age = age;
    }
    /**
     * Sets the date of birth for the member.
     *
     * @param dob The new date of birth.
     */
    public void setDob(String dob) {
        this.dob = dob;
    }
    /**
     * Sets the emergency contact number for the member.
     *
     * @param emergencyContact The new emergency contact number.
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
    /**
     * Sets the primary contact number for the member.
     *
     * @param memberContact The new primary contact number.
     */
    public void setMemberContact(String memberContact) {
        this.memberContact = memberContact;
    }
    /**
     * Sets the total number of matches for the member.
     *
     * @param total The new total number of matches.
     */
    public void setTotal(String total) {
        this.totalMatches = total;
    }
}
