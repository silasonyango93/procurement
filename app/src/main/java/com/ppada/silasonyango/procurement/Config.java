package com.ppada.silasonyango.procurement;

public class  Config {
    public static String ip = "http://ppra.go.ke";
    public static String login_url =ip + "/app/api/pages/login.php";
    public static String signup_url = ip + "/app/api/pages/register.php";
    public static String Prof_Pic_Url= ip + "/blood/getProfPic.php";
    public static final String change_prof_pic_url= ip + "/blood/ProfPicUpdate.php";
    public static final String prep_prof_pic_url= ip + "/blood/prepareProfilePic.php";
    public static final String update_profile_url= ip + "/blood/updateProfile.php";
    public static final String get_large_image_url= ip + "/blood/getProfile.php";
    public static final String book_url= ip + "/blood/bookAppointment.php";
    public static final String get_donors_url= ip + "/blood/getDonors.php";
    public static final String tag_url= ip + "/blood/tagPost.php";
    public static final String get_large_image= ip + "/blood/getSpecificDonorDetails.php";
    public static final String get_my_tags= ip + "/blood/getMyTags.php";
    public static final String get_blood_group= ip + "/blood/getBloodGroup.php";
    public static final String get_my_appointments= ip + "/blood/getMyAppointments.php";
    public static final String get_feeds= ip + "/app/api/pages/grtFeeds.php";
    public static final String get_specific_feed= ip + "/app/api/pages/getSpecificFeed.php";
    public static final String send_token= ip + "/app/api/pages/sendToken.php";
    public static final String update_token= ip + "/app/api/pages/updateToken.php";
    public static final String paint_share= ip + "/app/api/pages/paintShare.php";

}
