package com.example.silasonyango.ewe;

import android.annotation.TargetApi;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    DatabaseHelper myDb;
    private ExplosionField mExplosionField;
    View g;
    LayoutInflater inflater3;
    public static String UserId,UserName,Addr,strStartIndex,strEndIndex;



  /* private static final String[] COUNTRIES = new String[]{"Short title", "Accounting officer", "Appeal", "Guiding principles", "Conflicts with other Acts", "Application of the Act", "PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT", "Conflicts with international agreements", "Interpretation", "Role National Treasury on public procurement and assets disposal"
            , "The Public Procurement Regulatory Authority", "Functions of Authority,Public Procurement Regulatory Board,Qualifications of members of the Board",
            "Functions of the Board", "Tenure of office", "Procedures of the Board", "Director-General of the Authority", "Term of office of Director-General",
            "Functions of the Director-General", "Restrictions on activities of Director-General", "Terms and conditions of service of Director-General and staff",
            "Vacancy of office", "Removal of Director-General", "Acting Director-General"};*/

    private static final String[] COUNTRIES = new String[]{"Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title"
            ,"Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title"
            ,"Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title","Short title"};

   // private static final String[] COUNTRIES = new String[1000];
    private Context mContext;

    public String Key,CurrentlyTyped;

    TextView tvShortTitle,tvbook;
    LayoutInflater inflater, inflaterSave,inflaterSavePage;
    AlertDialog alertDialog;
    android.app.AlertDialog alertDialog1,alertDialog4;
    AlertDialog alertDialog2;
    AutoCompleteTextView textView;
    AlertDialog alertDialog3;
    String val;
    TextView tvTopic, tvContent,tvShare,tvSave;
    private GestureDetectorCompat mDetector;
    ClipboardManager clipboard;
    Button btContinueBook, btCancelBook,btSubmitEmail;
    EditText etHeader,etEmail;

    int start=0,end=0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Key="PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT";
        mExplosionField = ExplosionField.attach2Window(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        inflaterSave = this.getLayoutInflater();
        inflater3=this.getLayoutInflater();
        inflaterSavePage = this.getLayoutInflater();
        myDb = new DatabaseHelper(this);
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        this.mContext = getBaseContext();
        inflater = this.getLayoutInflater();
        tvTopic = (TextView) findViewById(R.id.topic);
        tvContent = (TextView) findViewById(R.id.content);
        tvSave=(TextView) findViewById(R.id.save);
        tvShare=(TextView) findViewById(R.id.share);
        tvContent.setTextColor(getResources().getColor(R.color.black));
        viewSpecificData();




        //tvContent.setCustomSelectionActionModeCallback(new StyleCallback());

        val = "PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT";

        String BookMark=getIntent().getStringExtra("BookMark");

        String Topic=getIntent().getStringExtra("Topic");
        String StartIndex=getIntent().getStringExtra("StartIndex");
        String EndIndex=getIntent().getStringExtra("EndIndex");
        String Name=getIntent().getStringExtra("Name");
        String idgen= null;
        try {
            idgen = createTransactionID();
        } catch (Exception e) {
            e.printStackTrace();
        }

        idgen=idgen.substring(1,5);

        Name=Name+" - "+idgen;

        String comp="nono";

        if(BookMark.equals(comp))
        {
//Do nothing
        }else if(BookMark.equals("signin"))
        {
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();


            sendToken(refreshedToken);
        }else if(BookMark.equals("firebase"))
        {myDb.insertPaintShare(Name,Topic,StartIndex,EndIndex);
            val =Topic;
            //getValuesFromFirebase(val,StartIndex,EndIndex);

            start=Integer.parseInt(StartIndex);
            end=Integer.parseInt(EndIndex);


        }else if(BookMark.equals("sqlite"))
        {
            val =Topic;
            //getValuesFromFirebase(val,StartIndex,EndIndex);

            start=Integer.parseInt(StartIndex);
            end=Integer.parseInt(EndIndex);


        }else{
            val =BookMark;

        }

        getValues(val, tvTopic, tvContent);
        FloatingActionButton fabRight = (FloatingActionButton) findViewById(R.id.fab);
        fabRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (val == "PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT") {

                    val = "1. Short title";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "1. Short title") {
                    val = "2. Interpretation";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "2. Interpretation") {
                    val = "3. Guiding principles";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "3. Guiding principles") {
                    val = "4. Application of the Act";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "4. Application of the Act") {
                    val = "5. Conflicts with other Acts";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "5. Conflicts with other Acts") {
                    val = "6. Conflicts with international agreements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "6. Conflicts with international agreements") {
                    val = "7. Role National Treasury on public procurement and assets disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "7. Role National Treasury on public procurement and assets disposal") {
                    val = "8. The Public Procurement Regulatory Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "8. The Public Procurement Regulatory Authority") {
                    val = "9. Functions of Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "9. Functions of Authority") {
                    val = "10. Public Procurement Regulatory Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "10. Public Procurement Regulatory Board") {
                    val = "11. Qualifications of members of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "11. Qualifications of members of the Board") {
                    val = "12. Functions of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "12. Functions of the Board") {
                    val = "13. Tenure of office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "13. Tenure of office") {
                    val = "14. Procedures of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "14. Procedures of the Board") {
                    val = "15. Director-General of the Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "15. Director-General of the Authority") {
                    val = "16. Term of office of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "16. Term of office of Director-General") {
                    val = "17. Functions of the Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "17. Functions of the Director-General") {
                    val = "18. Restrictions on activities of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "18. Restrictions on activities of Director-General") {
                    val = "19. Terms and conditions of service of Director-General and staff";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "19. Terms and conditions of service of Director-General and staff") {
                    val = "20. Vacancy of office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "20. Vacancy of office") {
                    val = "21. Removal of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "21. Removal of Director-General") {
                    val = "22. Acting Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "22. Acting Director-General") {
                    val = "23. Staff of the Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "23. Staff of the Authority") {
                    val = "24. Financial arrangements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "24. Financial arrangements") {
                    val = "25. Audit";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "25. Audit") {
                    val = "26. Annual reports";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "26. Annual reports") {
                    val = "27. Establishment of the Public Procurement Administrative Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "27. Establishment of the Public Procurement Administrative Review Board") {
                    val = "28. Functions and powers of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "28. Functions and powers of the Review Board") {
                    val = "29. Composition of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "29. Composition of the Review Board") {
                    val = "30. Qualifications of members of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "30. Qualifications of members of the Review Board") {
                    val = "31. Tenure of Office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "31. Tenure of Office") {
                    val = "32. Terms and conditions of service of the Review Board members";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "32. Terms and conditions of service of the Review Board members") {
                    val = "33. Roles and Responsibilities of the County Government";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "33. Roles and Responsibilities of the County Government") {
                    val = "34. Powers to ensure compliance";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "34. Powers to ensure compliance") {
                    val = "35. Investigations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "35. Investigations") {
                    val = "36. Powers of investigators";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "36. Powers of investigators") {
                    val = "37. Report of investigation";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "37. Report of investigation") {
                    val = "38. Order by the Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "38. Order by the Director-General") {
                    val = "39. Request for a Judicial review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "39. Request for a Judicial review") {
                    val = "40. No investigation if issue before Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "40. No investigation if issue before Review Board") {
                    val = "41. Debarment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "41. Debarment") {
                    val = "42. Judicial Review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "42. Judicial Review") {
                    val = "43. Inspections, Assessments and Reviews relating to contracts, procurement and asset disposal proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "43. Inspections, Assessments and Reviews relating to contracts, procurement and asset disposal proceedings") {
                    val = "44. Responsibilities of the accounting officer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "44. Responsibilities of the accounting officer") {
                    val = "45. Corporate decisions and segregation of responsibilities";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "45. Corporate decisions and segregation of responsibilities") {
                    val = "46. Evaluation Committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "46. Evaluation Committee") {
                    val = "47. Procurement function";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "47. Procurement function") {
                    val = "48. Inspection and acceptance committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "48. Inspection and acceptance committee") {
                    val = "49. Sector-specific procuring and disposal agencies";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "49. Sector-specific procuring and disposal agencies") {
                    val = "50. Consortium buying";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "50. Consortium buying") {
                    val = "51. Procuring agents or asset disposal agents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "51. Procuring agents or asset disposal agents") {
                    val = "52. Transfer of procuring responsibility to another public entity or procuring agent";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "52. Transfer of procuring responsibility to another public entity or procuring agent") {
                    val = "53. Procurement and asset disposal planning";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "53. Procurement and asset disposal planning") {
                    val = "54. Procurement pricing and requirement not to split of contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "54. Procurement pricing and requirement not to split of contracts") {
                    val = "55. Eligibility to bid";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "55. Eligibility to bid") {
                    val = "56. Use of list of another state organ or public entity";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "56. Use of list of another state organ or public entity") {
                    val = "57. List of registered suppliers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "57. List of registered suppliers") {
                    val = "58. Standard procurement and asset disposal documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "58. Standard procurement and asset disposal documents") {
                    val = "59. Limitation on contracts with state and public officers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "59. Limitation on contracts with state and public officers") {
                    val = "60. Specific requirements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "60. Specific requirements") {
                    val = "61. Tender security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "61. Tender security") {
                    val = "62. Declaration not to engage in corruption";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "62. Declaration not to engage in corruption") {
                    val = "63. Termination or cancellation of procurement and asset disposal proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "63. Termination or cancellation of procurement and asset disposal proceedings") {
                    val = "64. Form of communications, electronic procurement and asset disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "64. Form of communications, electronic procurement and asset disposal") {
                    val = "65. Inappropriate influence on evaluations, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "65. Inappropriate influence on evaluations, etc") {
                    val = "66. Corrupt, coercive, obstructive, collusive or fraudulent practice, conflicts of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "66. Corrupt, coercive, obstructive, collusive or fraudulent practice, conflicts of interest") {
                    val = "67. Confidentiality";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "67. Confidentiality") {
                    val = "68. Procurement records";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "68. Procurement records") {
                    val = "69. Procurement approvals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "69. Procurement approvals") {
                    val = "70. Standard tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "70. Standard tender documents") {
                    val = "71. Registration of suppliers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "71. Registration of suppliers") {
                    val = "72. Responsibility for complying with Act, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "72. Responsibility for complying with Act, etc") {
                    val = "73. Initiation of procurement process";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "73. Initiation of procurement process") {
                    val = "74. Invitation to tender";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "74. Invitation to tender") {
                    val = "75. Modifications to tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "75. Modifications to tender documents") {
                    val = "76. Modification of bids";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "76. Modification of bids") {
                    val = "77. Submission and receipt of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "77. Submission and receipt of tenders") {
                    val = "78. Opening of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "78. Opening of tenders") {
                    val = "79. Responsiveness of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "79. Responsiveness of tenders") {
                    val = "80. Evaluation of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "80. Evaluation of tenders") {
                    val = "81. Clarifications";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "81. Clarifications") {
                    val = "82. No correction of errors";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "82. No correction of errors") {
                    val = "83. Post-qualification";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "83. Post-qualification") {
                    val = "84. Professional opinion";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "84. Professional opinion") {
                    val = "85. Recommendation for contract awards";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "85. Recommendation for contract awards") {
                    val = "86. Successful tender";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "86. Successful tender") {
                    val = "87. Notification of intention to enter into a contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "87. Notification of intention to enter into a contract") {
                    val = "88. Extension of tender validity period";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "88. Extension of tender validity period") {
                    val = "89. International tendering and competition";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "89. International tendering and competition") {
                    val = "90. Classified procurements and disposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "90. Classified procurements and disposals") {
                    val = "91. Choice of procurement procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "91. Choice of procurement procedure") {
                    val = "92. Methods of procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "92. Methods of procurement") {
                    val = "93. Pre-qualification";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "93. Pre-qualification") {
                    val = "94. Pre-qualification documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "94. Pre-qualification documents") {
                    val = "95. Approval of pre-qualified candidates";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "95. Approval of pre-qualified candidates") {
                    val = "96. Advertisement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "96. Advertisement") {
                    val = "97. Time for preparing tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "97. Time for preparing tenders") {
                    val = "98. Provision of tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "98. Provision of tender documents") {
                    val = "99. Two-Stage Tendering";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "99. Two-Stage Tendering") {
                    val = "100. Condition for use of Design Competitions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "100. Condition for use of Design Competitions") {
                    val = "101. Procedure for design competition";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "101. Procedure for design competition") {
                    val = "102. Restricted tendering";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "102. Restricted tendering") {
                    val = "103. When direct procurement may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "103. When direct procurement may be used") {
                    val = "104. Procedure for direct procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "104. Procedure for direct procurement") {
                    val = "105. When request for quotations may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "105. When request for quotations may be used") {
                    val = "106. Procedure for request for quotations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "106. Procedure for request for quotations") {
                    val = "107. When low-value procurement may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "107. When low-value procurement may be used") {
                    val = "108. Procedure for low-value procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "108. Procedure for low-value procurement") {
                    val = "109. Force Account";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "109. Force Account") {
                    val = "110. Reverse Auction";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "110. Reverse Auction") {
                    val = "111. Conditions for use of Reverse Auctions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "111. Conditions for use of Reverse Auctions") {
                    val = "112. Procedure for Reverse Auction";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "112. Procedure for Reverse Auction") {
                    val = "113. Successful reverse auctioneering bid";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "113. Successful reverse auctioneering bid") {
                    val = "114. Framework agreement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "114. Framework agreement") {
                    val = "114A. Specially permitted procurement procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "114A. Specially permitted procurement procedure") {
                    val = "115. Application of this Part";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "115. Application of this Part") {
                    val = "116. When request for proposals may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "116. When request for proposals may be used") {
                    val = "117. Terms of reference and invitation of tenders from shortlisted firms";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "117. Terms of reference and invitation of tenders from shortlisted firms") {
                    val = "118. Request for proposal inviting expression of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "118. Request for proposal inviting expression of interest") {
                    val = "119. Notice inviting expressions of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "119. Notice inviting expressions of interest") {
                    val = "120. Opening of proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "120. Opening of proposals") {
                    val = "121. Evaluation and shortlisting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "121. Evaluation and shortlisting") {
                    val = "122. Determination of qualified persons";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "122. Determination of qualified persons") {
                    val = "123. Request for proposals to qualified persons";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "123. Request for proposals to qualified persons") {
                    val = "124. Selection methods for requests for proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "124. Selection methods for requests for proposals") {
                    val = "125. Time for preparing proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "125. Time for preparing proposals") {
                    val = "126. Evaluation of proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "126. Evaluation of proposals") {
                    val = "127. Successful proposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "127. Successful proposal") {
                    val = "128. Negotiations with successful request for proposal tenderer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "128. Negotiations with successful request for proposal tenderer") {
                    val = "129. Contract requirements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "129. Contract requirements") {
                    val = "130. Restriction on entering into related contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "130. Restriction on entering into related contracts") {
                    val = "131. Competitive Negotiations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "131. Competitive Negotiations") {
                    val = "132. Procedure for Competitive Negotiations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "132. Procedure for Competitive Negotiations") {
                    val = "133. Successful best and final offer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "133. Successful best and final offer") {
                    val = "134. Preparation of contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "134. Preparation of contracts") {
                    val = "135. Creation of procurement contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "135. Creation of procurement contracts") {
                    val = "136. Refusal to sign contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "136. Refusal to sign contract") {
                    val = "137. Changes to contract responsibilities";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "137. Changes to contract responsibilities") {
                    val = "138. Publication of procurement contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "138. Publication of procurement contract") {
                    val = "139. Amendments or variations to contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "139. Amendments or variations to contracts") {
                    val = "140. Interest on overdue amounts and liquidated damages";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "140. Interest on overdue amounts and liquidated damages") {
                    val = "141. Framework Contracting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "141. Framework Contracting") {
                    val = "142. Performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "142. Performance Security") {
                    val = "143. Nature of performance security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "143. Nature of performance security") {
                    val = "144. Seizure of the performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "144. Seizure of the performance Security") {
                    val = "145. Recovery of the performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "145. Recovery of the performance Security") {
                    val = "146. Advance payment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "146. Advance payment") {
                    val = "147. Amount of advance payment and its security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "147. Amount of advance payment and its security") {
                    val = "148. Use of advance payment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "148. Use of advance payment") {
                    val = "149. Sub-contracting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "149. Sub-contracting") {
                    val = "150. Contract administration";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "150. Contract administration") {
                    val = "151. Complex and specialized contract implementation team";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "151. Complex and specialized contract implementation team") {
                    val = "152. Contract monitoring";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "152. Contract monitoring") {
                    val = "153. Termination of contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "153. Termination of contract") {
                    val = "154. Contract close out";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "154. Contract close out") {
                    val = "155. Requirement for preferences and reservations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "155. Requirement for preferences and reservations") {
                    val = "156. Eligibility for more than one preference scheme";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "156. Eligibility for more than one preference scheme") {
                    val = "157. Participation of candidates in preference and reservations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "157. Participation of candidates in preference and reservations") {
                    val = "158. Procurement plans and monitoring compliance";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "158. Procurement plans and monitoring compliance") {
                    val = "159. Receipt, and recording of goods, works and services";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "159. Receipt, and recording of goods, works and services") {
                    val = "160. Objectives of Inventory Control, Asset and Stores Management and Distribution";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "160. Objectives of Inventory Control, Asset and Stores Management and Distribution") {
                    val = "161. Inventory, Stores and Assets Management system";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "161. Inventory, Stores and Assets Management system") {
                    val = "162. Management of Inventory, Stores and Assets";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "162. Management of Inventory, Stores and Assets") {
                    val = "163. Disposal committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "163. Disposal committee") {
                    val = "164. Disposal procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "164. Disposal procedure") {
                    val = "165. Methods of disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "165. Methods of disposal") {
                    val = "166. Restriction on disposal to employees, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "166. Restriction on disposal to employees, etc") {
                    val = "167. Request for a review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "167. Request for a review") {
                    val = "168. Notification of review and suspension of proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "168. Notification of review and suspension of proceedings") {
                    val = "169. Rejection of requests by Review Board Secretariat";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "169. Rejection of requests by Review Board Secretariat") {
                    val = "170. Parties to review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "170. Parties to review") {
                    val = "171. Completion of review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "171. Completion of review") {
                    val = "172. Dismissal of frivolous appeals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "172. Dismissal of frivolous appeals") {
                    val = "173. Powers of Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "173. Powers of Review Board") {
                    val = "174. Right to review is additional right";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "174. Right to review is additional right") {
                    val = "175. Right to judicial review to procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "175. Right to judicial review to procurement") {
                    val = "176. Prohibitions and offences";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "176. Prohibitions and offences") {
                    val = "177. General penalty and sanctions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "177. General penalty and sanctions") {
                    val = "178. Protection from personal liability and indemnity";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "178. Protection from personal liability and indemnity") {
                    val = "179. Consultative meetings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "179. Consultative meetings") {
                    val = "180. Making of Regulations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "180. Making of Regulations") {
                    val = "181. Code of Ethics";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "181. Code of Ethics") {
                    val = "182. Repeal of No. 3 of 2005 and savings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "182. Repeal of No. 3 of 2005 and savings") {
                    val = "183. Transitional provisions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "183. Transitional provisions") {
                    val = "FIRST SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "FIRST SCHEDULE") {
                    val = "SECOND SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "SECOND SCHEDULE") {
                    val = "THIRD SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "THIRD SCHEDULE") {
                    val = "THE END";
                    getValues(val, tvTopic, tvContent);
                }








            }
        });



        FloatingActionButton fabLeft = (FloatingActionButton) findViewById(R.id.fabLeft);


        fabLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (val == "1. Short title") {
                    val = "PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "2. Interpretation") {
                    val = "1. Short title";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "3. Guiding principles") {
                    val = "2. Interpretation";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "4. Application of the Act") {
                    val = "3. Guiding principles";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "5. Conflicts with other Acts") {
                    val = "4. Application of the Act";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "6. Conflicts with international agreements") {
                    val = "5. Conflicts with other Acts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "7. Role National Treasury on public procurement and assets disposal") {
                    val = "6. Conflicts with international agreements";
                    getValues(val, tvTopic, tvContent);
                } else if (val == "8. The Public Procurement Regulatory Authority") {
                    val = "7. Role National Treasury on public procurement and assets disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "9. Functions of Authority") {
                    val = "8. The Public Procurement Regulatory Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "10. Public Procurement Regulatory Board") {
                    val = "9. Functions of Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "11. Qualifications of members of the Board") {
                    val = "10. Public Procurement Regulatory Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "12. Functions of the Board") {
                    val = "11. Qualifications of members of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "13. Tenure of office") {
                    val = "12. Functions of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "14. Procedures of the Board") {
                    val = "13. Tenure of office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "15. Director-General of the Authority") {
                    val = "14. Procedures of the Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "16. Term of office of Director-General") {
                    val = "15. Director-General of the Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "17. Functions of the Director-General") {
                    val = "16. Term of office of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "18. Restrictions on activities of Director-General") {
                    val = "17. Functions of the Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "19. Terms and conditions of service of Director-General and staff") {
                    val = "18. Restrictions on activities of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "20. Vacancy of office") {
                    val = "19. Terms and conditions of service of Director-General and staff";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "21. Removal of Director-General") {
                    val = "20. Vacancy of office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "22. Acting Director-General") {
                    val = "21. Removal of Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "23. Staff of the Authority") {
                    val = "22. Acting Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "24. Financial arrangements") {
                    val = "23. Staff of the Authority";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "25. Audit") {
                    val = "24. Financial arrangements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "26. Annual reports") {
                    val = "25. Audit";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "27. Establishment of the Public Procurement Administrative Review Board") {
                    val = "26. Annual reports";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "28. Functions and powers of the Review Board") {
                    val = "27. Establishment of the Public Procurement Administrative Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "29. Composition of the Review Board") {
                    val = "28. Functions and powers of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "30. Qualifications of members of the Review Board") {
                    val = "29. Composition of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "31. Tenure of Office") {
                    val = "30. Qualifications of members of the Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "32. Terms and conditions of service of the Review Board members") {
                    val = "31. Tenure of Office";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "33. Roles and Responsibilities of the County Government") {
                    val = "32. Terms and conditions of service of the Review Board members";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "34. Powers to ensure compliance") {
                    val = "33. Roles and Responsibilities of the County Government";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "35. Investigations") {
                    val = "34. Powers to ensure compliance";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "36. Powers of investigators") {
                    val = "35. Investigations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "37. Report of investigation") {
                    val = "36. Powers of investigators";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "38. Order by the Director-General") {
                    val = "37. Report of investigation";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "39. Request for a Judicial review") {
                    val = "38. Order by the Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "40. No investigation if issue before Review Board") {
                    val = "39. Order by the Director-General";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "41. Debarment") {
                    val = "40. No investigation if issue before Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "42. Judicial Review") {
                    val = "41. Debarment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "43. Inspections, Assessments and Reviews relating to contracts, procurement and asset disposal proceedings") {
                    val = "42. Judicial Review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "44. Responsibilities of the accounting officer") {
                    val = "43. Inspections, Assessments and Reviews relating to contracts, procurement and asset disposal proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "45. Corporate decisions and segregation of responsibilities") {
                    val = "44. Responsibilities of the accounting officer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "46. Evaluation Committee") {
                    val = "45. Corporate decisions and segregation of responsibilities";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "47. Procurement function") {
                    val = "46. Evaluation Committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "48. Inspection and acceptance committee") {
                    val = "47. Procurement function";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "49. Sector-specific procuring and disposal agencies") {
                    val = "48. Inspection and acceptance committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "50. Consortium buying") {
                    val = "49. Sector-specific procuring and disposal agencies";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "51. Procuring agents or asset disposal agents") {
                    val = "50. Consortium buying";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "52. Transfer of procuring responsibility to another public entity or procuring agent") {
                    val = "51. Procuring agents or asset disposal agents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "53. Procurement and asset disposal planning") {
                    val = "52. Transfer of procuring responsibility to another public entity or procuring agent";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "54. Procurement pricing and requirement not to split of contracts") {
                    val = "53. Procurement and asset disposal planning";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "55. Eligibility to bid") {
                    val = "54. Procurement pricing and requirement not to split of contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "56. Use of list of another state organ or public entity") {
                    val = "55. Eligibility to bid";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "57. List of registered suppliers") {
                    val = "56. Use of list of another state organ or public entity";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "58. Standard procurement and asset disposal documents") {
                    val = "57. List of registered suppliers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "59. Limitation on contracts with state and public officers") {
                    val = "58. Standard procurement and asset disposal documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "60. Specific requirements") {
                    val = "59. Limitation on contracts with state and public officers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "61. Tender security") {
                    val = "60. Specific requirements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "62. Declaration not to engage in corruption") {
                    val = "61. Tender security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "63. Termination or cancellation of procurement and asset disposal proceedings") {
                    val = "62. Declaration not to engage in corruption";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "64. Form of communications, electronic procurement and asset disposal") {
                    val = "63. Termination or cancellation of procurement and asset disposal proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "65. Inappropriate influence on evaluations, etc") {
                    val = "64. Form of communications, electronic procurement and asset disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "66. Corrupt, coercive, obstructive, collusive or fraudulent practice, conflicts of interest") {
                    val = "65. Inappropriate influence on evaluations, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "67. Confidentiality") {
                    val = "66. Corrupt, coercive, obstructive, collusive or fraudulent practice, conflicts of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "68. Procurement records") {
                    val = "67. Confidentiality";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "69. Procurement approvals") {
                    val = "68. Procurement records";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "70. Standard tender documents") {
                    val = "69. Procurement approvals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "71. Registration of suppliers") {
                    val = "70. Standard tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "72. Responsibility for complying with Act, etc") {
                    val = "71. Registration of suppliers";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "73. Initiation of procurement process") {
                    val = "72. Responsibility for complying with Act, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "74. Invitation to tender") {
                    val = "73. Initiation of procurement process";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "75. Modifications to tender documents") {
                    val = "74. Invitation to tender";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "76. Modification of bids") {
                    val = "75. Modifications to tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "77. Submission and receipt of tenders") {
                    val = "76. Modification of bids";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "78. Opening of tenders") {
                    val = "77. Submission and receipt of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "79. Responsiveness of tenders") {
                    val = "78. Opening of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "80. Evaluation of tenders") {
                    val = "79. Responsiveness of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "81. Clarifications") {
                    val = "80. Evaluation of tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "82. No correction of errors") {
                    val = "81. Clarifications";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "83. Post-qualification") {
                    val = "82. No correction of errors";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "84. Professional opinion") {
                    val = "83. Post-qualification";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "85. Recommendation for contract awards") {
                    val = "84. Professional opinion";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "86. Successful tender") {
                    val = "85. Recommendation for contract awards";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "87. Notification of intention to enter into a contract") {
                    val = "86. Successful tender";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "88. Extension of tender validity period") {
                    val = "87. Notification of intention to enter into a contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "89. International tendering and competition") {
                    val = "88. Extension of tender validity period";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "90. Classified procurements and disposals") {
                    val = "89. International tendering and competition";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "91. Choice of procurement procedure") {
                    val = "90. Classified procurements and disposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "92. Methods of procurement") {
                    val = "91. Choice of procurement procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "93. Pre-qualification") {
                    val = "92. Methods of procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "94. Pre-qualification documents") {
                    val = "93. Pre-qualification";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "95. Approval of pre-qualified candidates") {
                    val = "94. Pre-qualification documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "96. Advertisement") {
                    val = "95. Approval of pre-qualified candidates";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "97. Time for preparing tenders") {
                    val = "96. Advertisement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "98. Provision of tender documents") {
                    val = "97. Time for preparing tenders";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "99. Two-Stage Tendering") {
                    val = "98. Provision of tender documents";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "100. Condition for use of Design Competitions") {
                    val = "99. Two-Stage Tendering";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "101. Procedure for design competition") {
                    val = "100. Condition for use of Design Competitions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "102. Restricted tendering") {
                    val = "101. Procedure for design competition";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "103. When direct procurement may be used") {
                    val = "102. Restricted tendering";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "104. Procedure for direct procurement") {
                    val = "103. When direct procurement may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "105. When request for quotations may be used") {
                    val = "104. Procedure for direct procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "106. Procedure for request for quotations") {
                    val = "105. When request for quotations may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "107. When low-value procurement may be used") {
                    val = "106. Procedure for request for quotations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "108. Procedure for low-value procurement") {
                    val = "107. When low-value procurement may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "109. Force Account") {
                    val = "108. Procedure for low-value procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "110. Reverse Auction") {
                    val = "109. Force Account";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "111. Conditions for use of Reverse Auctions") {
                    val = "110. Reverse Auction";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "112. Procedure for Reverse Auction") {
                    val = "111. Conditions for use of Reverse Auctions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "113. Successful reverse auctioneering bid") {
                    val = "112. Procedure for Reverse Auction";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "114. Framework agreement") {
                    val = "113. Successful reverse auctioneering bid";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "114A. Specially permitted procurement procedure") {
                    val = "114. Framework agreement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "115. Application of this Part") {
                    val = "114A. Specially permitted procurement procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "116. When request for proposals may be used") {
                    val = "115. Application of this Part";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "117. Terms of reference and invitation of tenders from shortlisted firms") {
                    val = "116. When request for proposals may be used";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "118. Request for proposal inviting expression of interest") {
                    val = "117. Terms of reference and invitation of tenders from shortlisted firms";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "119. Notice inviting expressions of interest") {
                    val = "118. Request for proposal inviting expression of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "120. Opening of proposals") {
                    val = "119. Notice inviting expressions of interest";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "121. Evaluation and shortlisting") {
                    val = "120. Opening of proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "122. Determination of qualified persons") {
                    val = "121. Evaluation and shortlisting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "123. Request for proposals to qualified persons") {
                    val = "122. Determination of qualified persons";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "124. Selection methods for requests for proposals") {
                    val = "123. Request for proposals to qualified persons";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "125. Time for preparing proposals") {
                    val = "124. Selection methods for requests for proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "126. Evaluation of proposals") {
                    val = "125. Time for preparing proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "127. Successful proposal") {
                    val = "126. Evaluation of proposals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "128. Negotiations with successful request for proposal tenderer") {
                    val = "127. Successful proposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "129. Contract requirements") {
                    val = "128. Negotiations with successful request for proposal tenderer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "130. Restriction on entering into related contracts") {
                    val = "129. Contract requirements";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "131. Competitive Negotiations") {
                    val = "130. Restriction on entering into related contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "132. Procedure for Competitive Negotiations") {
                    val = "131. Competitive Negotiations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "133. Successful best and final offer") {
                    val = "132. Procedure for Competitive Negotiations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "134. Preparation of contracts") {
                    val = "133. Successful best and final offer";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "135. Creation of procurement contracts") {
                    val = "134. Preparation of contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "136. Refusal to sign contract") {
                    val = "135. Creation of procurement contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "137. Changes to contract responsibilities") {
                    val = "136. Refusal to sign contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "138. Publication of procurement contract") {
                    val = "137. Changes to contract responsibilities";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "139. Amendments or variations to contracts") {
                    val = "138. Publication of procurement contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "140. Interest on overdue amounts and liquidated damages") {
                    val = "139. Amendments or variations to contracts";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "141. Framework Contracting") {
                    val = "140. Interest on overdue amounts and liquidated damages";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "142. Performance Security") {
                    val = "141. Framework Contracting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "143. Nature of performance security") {
                    val = "142. Performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "144. Seizure of the performance Security") {
                    val = "143. Nature of performance security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "145. Recovery of the performance Security") {
                    val = "144. Seizure of the performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "146. Advance payment") {
                    val = "145. Recovery of the performance Security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "147. Amount of advance payment and its security") {
                    val = "146. Advance payment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "148. Use of advance payment") {
                    val = "147. Amount of advance payment and its security";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "149. Sub-contracting") {
                    val = "148. Use of advance payment";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "150. Contract administration") {
                    val = "149. Sub-contracting";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "151. Complex and specialized contract implementation team") {
                    val = "150. Contract administration";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "152. Contract monitoring") {
                    val = "151. Complex and specialized contract implementation team";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "153. Termination of contract") {
                    val = "152. Contract monitoring";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "154. Contract close out") {
                    val = "153. Termination of contract";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "155. Requirement for preferences and reservations") {
                    val = "154. Contract close out";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "156. Eligibility for more than one preference scheme") {
                    val = "155. Requirement for preferences and reservations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "157. Participation of candidates in preference and reservations") {
                    val = "156. Eligibility for more than one preference scheme";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "158. Procurement plans and monitoring compliance") {
                    val = "157. Participation of candidates in preference and reservations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "159. Receipt, and recording of goods, works and services") {
                    val = "158. Procurement plans and monitoring compliance";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "160. Objectives of Inventory Control, Asset and Stores Management and Distribution") {
                    val = "159. Receipt, and recording of goods, works and services";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "161. Inventory, Stores and Assets Management system") {
                    val = "160. Objectives of Inventory Control, Asset and Stores Management and Distribution";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "162. Management of Inventory, Stores and Assets") {
                    val = "161. Inventory, Stores and Assets Management system";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "163. Disposal committee") {
                    val = "162. Management of Inventory, Stores and Assets";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "164. Disposal procedure") {
                    val = "163. Disposal committee";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "165. Methods of disposal") {
                    val = "164. Disposal procedure";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "166. Restriction on disposal to employees, etc") {
                    val = "165. Methods of disposal";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "167. Request for a review") {
                    val = "166. Restriction on disposal to employees, etc";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "168. Notification of review and suspension of proceedings") {
                    val = "167. Request for a review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "169. Rejection of requests by Review Board Secretariat") {
                    val = "168. Notification of review and suspension of proceedings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "170. Parties to review") {
                    val = "169. Rejection of requests by Review Board Secretariat";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "171. Completion of review") {
                    val = "170. Parties to review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "172. Dismissal of frivolous appeals") {
                    val = "171. Completion of review";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "173. Powers of Review Board") {
                    val = "172. Dismissal of frivolous appeals";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "174. Right to review is additional right") {
                    val = "173. Powers of Review Board";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "175. Right to judicial review to procurement") {
                    val = "174. Right to review is additional right";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "176. Prohibitions and offences") {
                    val = "175. Right to judicial review to procurement";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "177. General penalty and sanctions") {
                    val = "176. Prohibitions and offences";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "178. Protection from personal liability and indemnity") {
                    val = "177. General penalty and sanctions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "179. Consultative meetings") {
                    val = "178. Protection from personal liability and indemnity";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "180. Making of Regulations") {
                    val = "179. Consultative meetings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "181. Code of Ethics") {
                    val = "180. Making of Regulations";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "182. Repeal of No. 3 of 2005 and savings") {
                    val = "181. Code of Ethics";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "183. Transitional provisions") {
                    val = "182. Repeal of No. 3 of 2005 and savings";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "FIRST SCHEDULE") {
                    val = "183. Transitional provisions";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "SECOND SCHEDULE") {
                    val = "FIRST SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "THIRD SCHEDULE") {
                    val = "SECOND SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }else if (val == "THE END") {
                    val = "THIRD SCHEDULE";
                    getValues(val, tvTopic, tvContent);
                }

            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowCustomEnabled(true);

        /*
        * [
        * {"topic":"foo","content":"bar"},{}
        * ]
        *

        JSONObject foo = new JSONObject("foo");
        JSONArray bar = new JSONArray("bar");
        String search = "foobar";
        for (int i = 0; i < bar.length(); i++) {
            JSONObject object = bar.getJSONObject(i);
            if(object.getString("topic").contains(search)){
                // add to search
            }
            if(object.getString("content").contains(search)){
                // add to search
            }
        }*/

        LayoutInflater inflator = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(R.layout.actionbar, null);

        getSupportActionBar().setCustomView(v);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        textView = (AutoCompleteTextView) v
                .findViewById(R.id.editText1);
        textView.setAdapter(adapter);

        final long delay = 1000; // 1 seconds after user stops typing
        final long[] last_text_edit = {0};


        final Runnable input_finish_checker = new Runnable() {
            public void run() {
                if (System.currentTimeMillis() > (last_text_edit[0] + delay - 500)) {
                    // TODO: do what you need here
                    // ............
                    // ............


                    search(CurrentlyTyped) ;
                }
            }
        };

        final Handler handler = new Handler();
       textView.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s)
            {
               Key="PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT";
                CurrentlyTyped = s.toString().trim();
                CurrentlyTyped =CurrentlyTyped.toLowerCase();

               /*  search(CurrentlyTyped) ;*/

                //avoid triggering event when text is empty
                if (s.length() > 0) {
                    last_text_edit[0] = System.currentTimeMillis();
                    handler.postDelayed(input_finish_checker, delay);
                } else {

                }
            }


            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

//You need to remove this to run only once
                handler.removeCallbacks(input_finish_checker);

            }
        });

        TextView tvSearch = (TextView) v.findViewById(R.id.dot);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        tvSearch.setTypeface(font);
        tvSave.setTypeface(font);
        tvShare.setTypeface(font);

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentlyTyped =CurrentlyTyped.toLowerCase();

                search(CurrentlyTyped);
                //controlKey();

            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);

                tvSave.startAnimation(animShake);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        savePageHeader(val);

                    }
                }, 800);



            }
        });

        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);

                tvShare.startAnimation(animShake);
                mExplosionField.explode(view);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent b =new Intent(MainActivity.this,Home.class);
                        startActivity(b);

                    }
                }, 800);


            }
        });

        tvTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {



                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);
                final Animation earthquake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
                tvTopic.startAnimation(earthquake);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tvTopic.startAnimation(animShake);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mExplosionField.explode(view);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        reset(view);

                                    }
                                }, 800);

                            }
                        }, 800);


                    }
                }, 800);



            }
        });

        tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {


                mExplosionField.explode(view);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset(view);

                    }
                }, 800);


            }
        });


        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.nav_header_main, null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        tvContent.setCustomSelectionActionModeCallback(new StyleCallback());




       // tvTopic.setCustomSelectionActionModeCallback(new StyleCallback());


    }

    private void reset(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                reset(parent.getChildAt(i));
            }
        } else {
            root.setScaleX(1);
            root.setScaleY(1);
            root.setAlpha(1);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        val = parent.getItemAtPosition(position).toString();



        if(val=="PART I – PRELIMINARY")
        {Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART II – BODIES INVOLVED IN THE REGULATION OF PUBLIC PROCUREMENT AND ASSET DISPOSAL")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART III – COUNTY GONERNMENT RESPONSIBILITIES WITH RESPECT TO PUBLIC PROCUREMENT AND ASSET DISPOSAL")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART IV — POWERS TO ENSURE COMPLIANCE")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART V — INTERNAL ORGANISATION OF PROCURING ENTITIES")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART VI —GENERAL PROCUREMENT PRINCIPLES")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART VII — BASIC PROCUREMENT RULES")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART VIII — CLASSIFIED PROCUREMENT METHODS AND PROCEDURES")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART IX — METHODS OF PROCUREMENT")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART X— PROCUREMENT OF CONSULTANCY SERVICES")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XI—PROCUREMENT CONTRACTS")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XII — PREFERENCES AND RESERVATION IN PROCUREMENT")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XIII — INVENTORY CONTROL, ASSET AND STORES MANAGEMENT AND DISTRIBUTION")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XIV — DISPOSAL OF ASSETS")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XV — ADMINISTRATIVE REVIEW OF PROCUREMENT AND DISPOSAL PROCEEDINGS")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XVI — OFFENCES AND SANCTIONS")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XVII —POLICY OPERATIONALIZATION AND REVIEW")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else if(val=="PART XVIII — REPEAL, TRANSITION AND SAVINGS PROVISIONS")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }else if(val=="FIRST  SCHEDULE")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }else if(val=="SECOND  SCHEDULE")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }else if(val=="THIRD  SCHEDULE")
        {//Toast.makeText(parent.getContext(), "Select a topic", Toast.LENGTH_LONG).show();
        }
        else{
            //alertDialog.cancel();
            getcValues3(val, tvTopic, tvContent);
           // alertDialog.cancel();
            }

        // Showing selected spinner item



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                    }
                    result = true;
                } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                }
                result = true;

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }


        public void onSwipeRight() {
            Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();

        }

        public void onSwipeLeft() {

            Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();

        }

        public void onSwipeTop() {
            Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
        }

        public void onSwipeBottom() {
            Toast.makeText(MainActivity.this, "Bottom", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent i= new Intent(MainActivity.this,Home.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            View p = inflater.inflate(R.layout.table_of_contents, null);
            popPage(p);
        }  else if (id == R.id.nav_share) {
           // getAll();
        } else if (id == R.id.nav_send) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  void getValues(String val, final TextView topic, final TextView content) {
        StringBuffer buffer = new StringBuffer();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                // Log.d("Details-->", jo_inside.getString("name"));

                if (jo_inside.getString("topic").equals(val)) {
                    final String strTopic = jo_inside.getString("topic");
                    final String strContent = jo_inside.getString("content");







                    tvTopic.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    tvTopic.setText(strTopic);
                                    tvTopic.setAlpha(1);
                                }
                            });


                    tvContent.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                    SpannableString   str = new SpannableString(strContent);

                    str.setSpan(new BackgroundColorSpan(Color.rgb(255,182,193)),  start, end, 0);

                    tvContent.setText(str);

                    tvContent.setAlpha(1);
                                }
                            });

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // popPage(p);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        // builder.setView(R.layout.activity_main);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popPage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog = builder.create();
        alertDialog.show();



        //INITIALIZNIMG TABLE OF CONTENTS*******************************************************************************************


        Spinner gender_spinner = (Spinner) v.findViewById(R.id.gender_spinner);

        // Spinner click listener
        gender_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> gender = new ArrayList<String>();
        gender.add("PART I – PRELIMINARY");
        gender.add("1. Short title");
        gender.add("2. Interpretation");
        gender.add("3. Guiding principles");
        gender.add("4. Application of the Act");

        gender.add("5. Conflicts with other Acts");
        gender.add("6. Conflicts with international agreements");



        // Creating adapter for spinner
        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);

        // Drop down layout style - list view with radio button
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        gender_spinner.setAdapter(genderAdapter);

//*******************************************************************************************************************
        Spinner part_two_spinner = (Spinner) v.findViewById(R.id.part_two_spinner);

        // Spinner click listener
        part_two_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_two = new ArrayList<String>();
        part_two.add("PART II – BODIES INVOLVED IN THE REGULATION OF PUBLIC PROCUREMENT AND ASSET DISPOSAL");
        part_two.add("7. Role National Treasury on public procurement and assets disposal");
        part_two.add("8. The Public Procurement Regulatory Authority");

        part_two.add("9. Functions of Authority");
        part_two.add("10. Public Procurement Regulatory Board");
        part_two.add("11. Qualifications of members of the Board");
        part_two.add("12. Functions of the Board");
        part_two.add("13. Tenure of office");
        part_two.add("14. Procedures of the Board");
        part_two.add("15. Director-General of the Authority");
        part_two.add("16. Term of office of Director-General");
        part_two.add("17. Functions of the Director-General");
        part_two.add("18. Restrictions on activities of Director-General");
        part_two.add("19. Terms and conditions of service of Director-General and staff");
        part_two.add("20. Vacancy of office");
        part_two.add("21. Removal of Director-General");
        part_two.add("22. Acting Director-General");
        part_two.add("23. Staff of the Authority");
        part_two.add("24. Financial arrangements");
        part_two.add("25. Audit");
        part_two.add("26. Annual reports");
        part_two.add("27. Establishment of the Public Procurement Administrative Review Board");
        part_two.add("28. Functions and powers of the Review Board");
        part_two.add("29. Composition of the Review Board");
        part_two.add("30. Qualifications of members of the Review Board");
        part_two.add("31. Tenure of Office");
        part_two.add("32. Terms and conditions of service of the Review Board members");





        // Creating adapter for spinner
        ArrayAdapter<String> part_twoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_two);

        // Drop down layout style - list view with radio button
        part_twoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_two_spinner.setAdapter(part_twoAdapter);

//**********************************************************************************************************************

        Spinner part_three_spinner = (Spinner) v.findViewById(R.id.part_three_spinner);

        // Spinner click listener
        part_three_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_three = new ArrayList<String>();
        part_three.add("PART III – COUNTY GOVERNMENT RESPONSIBILITIES WITH RESPECT TO PUBLIC PROCUREMENT AND ASSET DISPOSAL");
        part_three.add("33. Roles and Responsibilities of the County Government");




        // Creating adapter for spinner
        ArrayAdapter<String> part_threeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_three);

        // Drop down layout style - list view with radio button
        part_threeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_three_spinner.setAdapter(part_threeAdapter);


        //***************************************************************************************************************

        Spinner part_four_spinner = (Spinner) v.findViewById(R.id.part_four_spinner);

        // Spinner click listener
        part_four_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_four = new ArrayList<String>();
        part_four.add("PART IV — POWERS TO ENSURE COMPLIANCE");

        part_four.add("34. Powers to ensure compliance");
        part_four.add("35. Investigations");
        part_four.add("36. Powers of investigators");
        part_four.add("37. Report of investigation");
        part_four.add("38. Order by the Director-General");
        part_four.add("39. Request for a Judicial review");
        part_four.add("40. No investigation if issue before Review Board");
        part_four.add("41. Debarment");
        part_four.add("42. Judicial Review");
        part_four.add("43. Inspections, Assessments and Reviews relating to contracts, procurement and asset disposal proceedings");





        // Creating adapter for spinner
        ArrayAdapter<String> part_fourAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_four);

        // Drop down layout style - list view with radio button
        part_fourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_four_spinner.setAdapter(part_fourAdapter);

        //***********************************************************************************************************

        Spinner part_five_spinner = (Spinner) v.findViewById(R.id.part_five_spinner);

        // Spinner click listener
        part_five_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_five = new ArrayList<String>();
        part_five.add("PART V — INTERNAL ORGANISATION OF PROCURING ENTITIES");

        part_five.add("44. Responsibilities of the accounting officer");
        part_five.add("45. Corporate decisions and segregation of responsibilities");
        part_five.add("46. Evaluation Committee");
        part_five.add("47. Procurement function");
        part_five.add("48. Inspection and acceptance committee");
        part_five.add("49. Sector-specific procuring and disposal agencies");
        part_five.add("50. Consortium buying");
        part_five.add("51. Procuring agents or asset disposal agents");
        part_five.add("52. Transfer of procuring responsibility to another public entity or procuring agent");






        // Creating adapter for spinner
        ArrayAdapter<String> part_fiveAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_five);

        // Drop down layout style - list view with radio button
        part_fiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_five_spinner.setAdapter(part_fiveAdapter);

        //*************************************************************************************************************


        Spinner part_six_spinner = (Spinner) v.findViewById(R.id.part_six_spinner);

        // Spinner click listener
        part_six_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_six = new ArrayList<String>();
        part_six.add("PART VI —GENERAL PROCUREMENT PRINCIPLES");

        part_six.add("53. Procurement and asset disposal planning");
        part_six.add("54. Procurement pricing and requirement not to split of contracts");
        part_six.add("55. Eligibility to bid");
        part_six.add("56. Use of list of another state organ or public entity");
        part_six.add("57. List of registered suppliers");
        part_six.add("58. Standard procurement and asset disposal documents");
        part_six.add("59. Limitation on contracts with state and public officers");
        part_six.add("60. Specific requirements");
        part_six.add("61. Tender security");
        part_six.add("62. Declaration not to engage in corruption");
        part_six.add("63. Termination or cancellation of procurement and asset disposal proceedings");
        part_six.add("64. Form of communications, electronic procurement and asset disposal");
        part_six.add("65. Inappropriate influence on evaluations");
        part_six.add("66. Corrupt, coercive, obstructive, collusive or fraudulent practice, conflicts of interest");
        part_six.add("67. Confidentiality");
        part_six.add("68. Procurement records");
        part_six.add("69. Procurement approvals");






        // Creating adapter for spinner
        ArrayAdapter<String> part_sixAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_six);

        // Drop down layout style - list view with radio button
        part_sixAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_six_spinner.setAdapter(part_sixAdapter);


        //*****************************************************************************************************************


        Spinner part_seven_spinner = (Spinner) v.findViewById(R.id.part_seven_spinner);

        // Spinner click listener
        part_seven_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_seven = new ArrayList<String>();
        part_seven.add("PART VII — BASIC PROCUREMENT RULES");

        part_seven.add("70. Standard tender documents");
        part_seven.add("71. Registration of suppliers");
        part_seven.add("72. Responsibility for complying with Act, etc");
        part_seven.add("73. Initiation of procurement process");
        part_seven.add("74. Invitation to tender");
        part_seven.add("75. Modifications to tender documents");
        part_seven.add("76. Modification of bids");
        part_seven.add("77. Submission and receipt of tenders");
        part_seven.add("78. Opening of tenders");
        part_seven.add("79. Responsiveness of tenders");
        part_seven.add("80. Evaluation of tenders");
        part_seven.add("81. Clarifications");
        part_seven.add("82. No correction of errors");
        part_seven.add("83. Post-qualification");
        part_seven.add("84. Professional opinion");
        part_seven.add("85. Recommendation for contract awards");
        part_seven.add("86. Successful tender");
        part_seven.add("87. Notification of intention to enter into a contract");
        part_seven.add("88. Extension of tender validity period");
        part_seven.add("89. International tendering and competition");


        // Creating adapter for spinner
        ArrayAdapter<String> part_sevenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_seven);

        // Drop down layout style - list view with radio button
        part_sevenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_seven_spinner.setAdapter(part_sevenAdapter);



        //********************************************************************************************************



        Spinner part_eight_spinner = (Spinner) v.findViewById(R.id.part_eight_spinner);

        // Spinner click listener
        part_eight_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_eight = new ArrayList<String>();
        part_eight.add("PART VIII — CLASSIFIED PROCUREMENT METHODS AND PROCEDURES");

        part_eight.add("90. Classified procurements and disposals");



        // Creating adapter for spinner
        ArrayAdapter<String> part_eightAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_eight);

        // Drop down layout style - list view with radio button
        part_eightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_eight_spinner.setAdapter(part_eightAdapter);

        //****************************************************************************************************


        Spinner part_nine_spinner = (Spinner) v.findViewById(R.id.part_nine_spinner);

        // Spinner click listener
        part_nine_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_nine = new ArrayList<String>();
        part_nine.add("PART IX — METHODS OF PROCUREMENT");

        part_nine.add("91. Choice of procurement procedure");
        part_nine.add("92. Methods of procurement");
        part_nine.add("93. Pre-qualification");
        part_nine.add("94. Pre-qualification documents");
        part_nine.add("95. Approval of pre-qualified candidates");
        part_nine.add("96. Advertisement");
        part_nine.add("97. Time for preparing tenders");
        part_nine.add("98. Provision of tender documents");
        part_nine.add("99. Two-Stage Tendering");
        part_nine.add("100. Condition for use of Design Competitions");
        part_nine.add("101. Procedure for design competition");
        part_nine.add("102. Restricted tendering");
        part_nine.add("103. When direct procurement may be used");
        part_nine.add("104. Procedure for direct procurement");
        part_nine.add("105. When request for quotations may be used");
        part_nine.add("106. Procedure for request for quotations");
        part_nine.add("107. When low-value procurement may be used");
        part_nine.add("108. Procedure for low-value procurement");
        part_nine.add("109. Force Account");
        part_nine.add("110. Reverse Auction");
        part_nine.add("111. Conditions for use of Reverse Auctions");
        part_nine.add("112. Procedure for Reverse Auction");
        part_nine.add("113. Successful reverse auctioneering bid");
        part_nine.add("114. Framework agreement");
        part_nine.add("114A. Specially permitted procurement procedure");




        // Creating adapter for spinner
        ArrayAdapter<String> part_nineAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_nine);

        // Drop down layout style - list view with radio button
        part_nineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_nine_spinner.setAdapter(part_nineAdapter);

        //*********************************************************************************************

        Spinner part_ten_spinner = (Spinner) v.findViewById(R.id.part_ten_spinner);

        // Spinner click listener
        part_ten_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_ten = new ArrayList<String>();
        part_ten.add("PART X— PROCUREMENT OF CONSULTANCY SERVICES");

        part_ten.add("115. Application of this Part");
        part_ten.add("116. When request for proposals may be used");
        part_ten.add("117. Terms of reference and invitation of tenders from shortlisted firms");
        part_ten.add("118. Request for proposal inviting expression of interest");
        part_ten.add("119. Notice inviting expressions of interest");
        part_ten.add("120. Opening of proposals");
        part_ten.add("121. Evaluation and shortlisting");
        part_ten.add("122. Determination of qualified persons");
        part_ten.add("123. Request for proposals to qualified persons");
        part_ten.add("124. Selection methods for requests for proposals");
        part_ten.add("125. Time for preparing proposals");
        part_ten.add("126. Evaluation of proposals");
        part_ten.add("127. Successful proposal");
        part_ten.add("128. Negotiations with successful request for proposal tenderer");
        part_ten.add("129. Contract requirements");
        part_ten.add("130. Restriction on entering into related contracts");
        part_ten.add("131. Competitive Negotiations");
        part_ten.add("132. Procedure for Competitive Negotiations");
        part_ten.add("133. Successful best and final offer");




        // Creating adapter for spinner
        ArrayAdapter<String> part_tenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_ten);

        // Drop down layout style - list view with radio button
        part_tenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_ten_spinner.setAdapter(part_tenAdapter);


        //********************************************************************************************************


        Spinner part_eleven_spinner = (Spinner) v.findViewById(R.id.part_eleven_spinner);

        // Spinner click listener
        part_eleven_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_eleven = new ArrayList<String>();
        part_eleven.add("PART XI—PROCUREMENT CONTRACTS");

        part_eleven.add("134. Preparation of contracts");
        part_eleven.add("135. Creation of procurement contracts");
        part_eleven.add("136. Refusal to sign contract");
        part_eleven.add("137. Changes to contract responsibilities");
        part_eleven.add("138. Publication of procurement contract");
        part_eleven.add("139. Amendments or variations to contracts");
        part_eleven.add("140. Interest on overdue amounts and liquidated damages");
        part_eleven.add("141. Framework Contracting");
        part_eleven.add("142. Performance Security");
        part_eleven.add("143. Nature of performance security");
        part_eleven.add("144. Seizure of the performance Security");
        part_eleven.add("145. Recovery of the performance Security");
        part_eleven.add("146. Advance payment");
        part_eleven.add("147. Amount of advance payment and its security");
        part_eleven.add("148. Use of advance payment");
        part_eleven.add("149. Sub-contracting");
        part_eleven.add("150. Contract administration");
        part_eleven.add("151. Complex and specialized contract implementation team");
        part_eleven.add("152. Contract monitoring");
        part_eleven.add("153. Termination of contract");
        part_eleven.add("154. Contract close out");




        // Creating adapter for spinner
        ArrayAdapter<String> part_elevenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_eleven);

        // Drop down layout style - list view with radio button
        part_elevenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_eleven_spinner.setAdapter(part_elevenAdapter);


        //***************************************************************************************************

        Spinner part_twelve_spinner = (Spinner) v.findViewById(R.id.part_twelve_spinner);

        // Spinner click listener
        part_twelve_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_twelve = new ArrayList<String>();
        part_twelve.add("PART XII — PREFERENCES AND RESERVATION IN PROCUREMENT");

        part_twelve.add("155. Requirement for preferences and reservations");
        part_twelve.add("156. Eligibility for more than one preference scheme");
        part_twelve.add("157. Participation of candidates in preference and reservations");
        part_twelve.add("158. Procurement plans and monitoring compliance");


        // Creating adapter for spinner
        ArrayAdapter<String> part_twelveAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_twelve);

        // Drop down layout style - list view with radio button
        part_twelveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_twelve_spinner.setAdapter(part_twelveAdapter);

        //**************************************************************************************************

        Spinner part_thirteen_spinner = (Spinner) v.findViewById(R.id.part_thirteen_spinner);

        // Spinner click listener
        part_thirteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_thirteen = new ArrayList<String>();
        part_thirteen.add("PART XIII — INVENTORY CONTROL, ASSET AND STORES MANAGEMENT AND DISTRIBUTION");

        part_thirteen.add("159. Receipt, and recording of goods, works and services");
        part_thirteen.add("160. Objectives of Inventory Control, Asset and Stores Management and Distribution");
        part_thirteen.add("161. Inventory, Stores and Assets Management system");
        part_thirteen.add("162. Management of Inventory, Stores and Assets");



        // Creating adapter for spinner
        ArrayAdapter<String> part_thirteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_thirteen);

        // Drop down layout style - list view with radio button
        part_thirteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_thirteen_spinner.setAdapter(part_thirteenAdapter);

        //*****************************************************************************************************

        Spinner part_fourteen_spinner = (Spinner) v.findViewById(R.id.part_fourteen_spinner);

        // Spinner click listener
        part_fourteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_fourteen = new ArrayList<String>();
        part_fourteen.add("PART XIV — DISPOSAL OF ASSETS");

        part_fourteen.add("163. Disposal committee");
        part_fourteen.add("164. Disposal procedure");
        part_fourteen.add("165. Methods of disposal");
        part_fourteen.add("166. Restriction on disposal to employees, etc");




        // Creating adapter for spinner
        ArrayAdapter<String> part_fourteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_fourteen);

        // Drop down layout style - list view with radio button
        part_fourteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_fourteen_spinner.setAdapter(part_fourteenAdapter);

        //***********************************************************************************************

        Spinner part_fiveteen_spinner = (Spinner) v.findViewById(R.id.part_fiveteen_spinner);

        // Spinner click listener
        part_fiveteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_fiveteen = new ArrayList<String>();
        part_fiveteen.add("PART XV — ADMINISTRATIVE REVIEW OF PROCUREMENT AND DISPOSAL PROCEEDINGS");

        part_fiveteen.add("167. Request for a review");
        part_fiveteen.add("168. Notification of review and suspension of proceedings");
        part_fiveteen.add("169. Rejection of requests by Review Board Secretariat");
        part_fiveteen.add("170. Parties to review");
        part_fiveteen.add("171. Completion of review");
        part_fiveteen.add("172. Dismissal of frivolous appeals");
        part_fiveteen.add("173. Powers of Review Board");
        part_fiveteen.add("174. Right to review is additional right");
        part_fiveteen.add("175. Right to judicial review to procurement");



        // Creating adapter for spinner
        ArrayAdapter<String> part_fiveteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_fiveteen);

        // Drop down layout style - list view with radio button
        part_fiveteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_fiveteen_spinner.setAdapter(part_fiveteenAdapter);

        //**************************************************************************************

        Spinner part_sixteen_spinner = (Spinner) v.findViewById(R.id.part_sixteen_spinner);

        // Spinner click listener
        part_sixteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_sixteen = new ArrayList<String>();
        part_sixteen.add("PART XVI — OFFENCES AND SANCTIONS");

        part_sixteen.add("176. Prohibitions and offences");
        part_sixteen.add("177. General penalty and sanctions");
        part_sixteen.add("178. Protection from personal liability and indemnity");




        // Creating adapter for spinner
        ArrayAdapter<String> part_sixteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_sixteen);

        // Drop down layout style - list view with radio button
        part_sixteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_sixteen_spinner.setAdapter(part_sixteenAdapter);

        //**************************************************************************************************

        Spinner part_seventeen_spinner = (Spinner) v.findViewById(R.id.part_seventeen_spinner);

        // Spinner click listener
        part_seventeen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_seventeen = new ArrayList<String>();
        part_seventeen.add("PART XVII —POLICY OPERATIONALIZATION AND REVIEW");

        part_seventeen.add("179. Consultative meetings");
        part_seventeen.add("180. Making of Regulations");
        part_seventeen.add("181. Code of Ethics");





        // Creating adapter for spinner
        ArrayAdapter<String> part_seventeenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_seventeen);

        // Drop down layout style - list view with radio button
        part_seventeenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_seventeen_spinner.setAdapter(part_seventeenAdapter);

        //**********************************************************************************************

        Spinner part_eighteen_spinner = (Spinner) v.findViewById(R.id.part_eighteen_spinner);

        // Spinner click listener
        part_eighteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_eighteen = new ArrayList<String>();
        part_eighteen.add("PART XVIII — REPEAL, TRANSITION AND SAVINGS PROVISIONS");

        part_eighteen.add("182. Repeal of No. 3 of 2005 and savings");
        part_eighteen.add("183. Transitional provisions");






        // Creating adapter for spinner
        ArrayAdapter<String> part_eighteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_eighteen);

        // Drop down layout style - list view with radio button
        part_eighteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_eighteen_spinner.setAdapter(part_eighteenAdapter);


        //*****************************************************************************************************

        Spinner part_nineteen_spinner = (Spinner) v.findViewById(R.id.part_nineteen_spinner);

        // Spinner click listener
        part_nineteen_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_nineteen = new ArrayList<String>();
        part_nineteen.add("FIRST  SCHEDULE");

        part_nineteen.add("FIRST SCHEDULE");







        // Creating adapter for spinner
        ArrayAdapter<String> part_nineteenAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_nineteen);

        // Drop down layout style - list view with radio button
        part_nineteenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_nineteen_spinner.setAdapter(part_nineteenAdapter);

        //*****************************************************************************************************************************************

        Spinner part_twenty_spinner = (Spinner) v.findViewById(R.id.part_twenty_spinner);

        // Spinner click listener
        part_twenty_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_twenty = new ArrayList<String>();
        part_twenty.add("SECOND  SCHEDULE");

        part_twenty.add("SECOND SCHEDULE");







        // Creating adapter for spinner
        ArrayAdapter<String> part_twentyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_twenty);

        // Drop down layout style - list view with radio button
        part_twentyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_twenty_spinner.setAdapter(part_twentyAdapter);

        //*********************************************************************************************************************

        Spinner part_thirty_spinner = (Spinner) v.findViewById(R.id.part_thirty_spinner);

        // Spinner click listener
        part_thirty_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        final List<String> part_thirty = new ArrayList<String>();
        part_thirty.add("THIRD  SCHEDULE");

        part_thirty.add("THIRD SCHEDULE");







        // Creating adapter for spinner
        ArrayAdapter<String> part_thirtyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, part_thirty);

        // Drop down layout style - list view with radio button
        part_thirtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        part_thirty_spinner.setAdapter(part_thirtyAdapter);




        //*****************************************************************************************************************************

    }





    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void addSQLite(String strHeader,String strContent) {
        String three = strContent;
        String one=strHeader;

        if(three.equals(""))
        {
            Toast.makeText(getApplicationContext(),
                    "Kindly select the text again", Toast.LENGTH_SHORT).show();
        }
        else {


        boolean isInserted = myDb.insertData(one, three);

        if (isInserted == true)
            Toast.makeText(MainActivity.this, "Annotation Succesfully added", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Naaaah! you failed", Toast.LENGTH_LONG).show();}

    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void savePop(View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);


        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog1 = builder.create();
        alertDialog1.show();


    }

    public void getAll()
    {
        Cursor res = myDb.getAllSecondTable();

        if (res.getCount() == 0) {
            //Show message
            showMessage("Error", "No data found Silas");
            return;
        }
        String lastval = null;
        
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            buffer.append("ID : " + res.getString(0) + "\n");
            buffer.append("HEADER : " + res.getString(1) + "\n\n");


        }


        //Show all data


        showMessage("Data", buffer.toString());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void savePageHeader(String strHeader) {



        boolean isInserted = myDb.insertSecondTable(strHeader);

        if (isInserted == true)
            Toast.makeText(MainActivity.this, "Page has been saved succesfully.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Naaaah! you failed", Toast.LENGTH_LONG).show();
       // getAll();
    }


    public void share(String message)
    {

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(share, "Share"));
    }


    class StyleCallback implements android.view.ActionMode.Callback {
        public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
            Log.d("Custom", "onCreateActionMode");
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.style, menu);
            menu.removeItem(android.R.id.selectAll);
            return true;
        }

        public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
            return false;
        }

        public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
            Log.d("Custom", String.format("onActionItemClicked item=%s/%d", item.toString(), item.getItemId()));
            CharacterStyle cs;


            int start = tvContent.getSelectionStart();
            int end = tvContent.getSelectionEnd();
            SpannableStringBuilder ssb = new SpannableStringBuilder(tvContent.getText());

            switch (item.getItemId()) {

                case R.id.paint:
                   // cs = new StyleSpan(Typeface.BOLD);
                   // ssb.setSpan(cs, start, end, 1);
                    ssb.setSpan(new BackgroundColorSpan(Color.BLUE), start, end, 0);
                    tvContent.setText(ssb);
                    return true;

                case R.id.bold:
                    cs = new StyleSpan(Typeface.BOLD);
                    ssb.setSpan(cs, start, end, 1);
                    tvContent.setText(ssb);
                    return true;

                case R.id.italic:
                    cs = new StyleSpan(Typeface.ITALIC);
                    ssb.setSpan(cs, start, end, 1);
                    tvContent.setText(ssb);
                    return true;

                case R.id.underline:
                    cs = new UnderlineSpan();
                    ssb.setSpan(cs, start, end, 1);
                    tvContent.setText(ssb);
                    return true;

                case R.id.annotate:
                    final String selectedText;
                    String content=tvContent.getText().toString();

                    selectedText=content.substring(start,end);

                    View v = inflaterSave.inflate(R.layout.save_paragraph_pop, null);
                    etHeader = (EditText) v.findViewById(R.id.et_header_name);
                    btContinueBook = (Button) v.findViewById(R.id.bt_submit_save_paragraph);

                    btContinueBook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            String header = etHeader.getText().toString().trim();

                            if(header.equals(""))
                            {
                                Toast.makeText(getApplicationContext(),
                                        "You have not selected a title for your annotation", Toast.LENGTH_SHORT).show();
                            }else {alertDialog1.cancel();
                                addSQLite(header,selectedText);}




                        }
                    });


                    savePop(v);



                    return true;

                case R.id.share:

                    final String selectedText2;
                    String content2=tvContent.getText().toString();

                    selectedText2=content2.substring(start,end);

                    String three = selectedText2;
                    if(three.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),
                                "Kindly select the text again", Toast.LENGTH_SHORT).show();
                    }
                    else {share(three);}

                    return true;

                case R.id.paintshare:

                    boolean net= isNetworkConnected();
                    if(net!=true)
                    {showMessage("This feature requires internet connection.", "Check your internet connectivity and try again.");}
                    else{

                    boolean loginCheck;

                    loginCheck= signupCofirmation();

                    if(loginCheck==true)
                    {



                    final String startIndex,endIndex;
                    ssb.setSpan(new BackgroundColorSpan(Color.BLUE), start, end, 0);
                    tvContent.setText(ssb);
                    startIndex=String.valueOf(start);
                    endIndex=String.valueOf(end);


                    View b = inflaterSave.inflate(R.layout.submit_email, null);
                    etEmail = (EditText) b.findViewById(R.id.et_header_name);
                    btSubmitEmail = (Button) b.findViewById(R.id.bt_submit_save_paragraph);

                    btSubmitEmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

String strEmail=etEmail.getText().toString();
                            String strTopic=tvTopic.getText().toString();

if(strEmail.equals("")){
    Toast.makeText(getBaseContext(), "Kindly input the recepient's email address", Toast.LENGTH_SHORT).show();
}else{

    alertDialog4.cancel();
    paintShare(strEmail,strTopic,startIndex,endIndex);}

                        }
                    });


                    paintSharePop(b);
//paintShare(tvTopic,startIndex,endIndex);
                    return true;}}
            }
            return false;
        }

        public void onDestroyActionMode(android.view.ActionMode mode) {
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void poplist(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getlist(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog3 = builder.create();
        alertDialog3.show();






    }

    public void getlist(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();





        list.add("Add notes");
        list.add("Share");



        final StableArrayAdapter adapter = new StableArrayAdapter(c,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
               /* Toast.makeText(getApplicationContext(),
                        ((TextView) view).getText(), Toast.LENGTH_SHORT).show();*/
                String vava= ((TextView) view).getText().toString();


                if (vava=="Add notes")
                {



                } else{

                }



            }

        });
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }


    public void search(String s)
    {  g = inflater3.inflate(R.layout.search, null);
        final ListView listview = (ListView) g.findViewById(R.id.listview);
        final ArrayList<Searches> list = new ArrayList<>();
        String val = s;

        JSONObject obj = null;
        try {
            obj = new JSONObject(loadJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray m_jArry = null;
        try {
            m_jArry = obj.getJSONArray("PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String search = val;
        for (int i = 0; i < m_jArry.length(); i++) {
            JSONObject object = null;
            try {
                object = m_jArry.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if(object.getString("topic").toLowerCase().contains(search)){

                    list.add(new Searches(R.drawable.baba,object.getString("topic")));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                if(object.getString("content").toLowerCase().contains(search)){
                    // add to search
                    //Toast.makeText(getBaseContext(), "c", Toast.LENGTH_LONG).show();
                    list.add(new Searches(R.drawable.baba,object.getString("topic")));
                    //getcValues3(Key, tvTopic, tvContent);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        MySearchAdapter adapter = new MySearchAdapter(this, R.layout.my_custom_list, list);

        //attaching adapter to the listview
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
               // final String item = (String) parent.getItemAtPosition(position);

               // String val= ((TextView) view).getText().toString().trim();
                String val= list.get(position).getName();
                alertDialog2.cancel();
               getvvvv(val);
            }

        });
        if (list.isEmpty()) {
            showMessage("Word not found","No such word exists in this book");
        }else{popBookMark2(g);}



    }



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void getcValues3(String val, final TextView topic, final TextView content) {
        StringBuffer buffer = new StringBuffer();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                // Log.d("Details-->", jo_inside.getString("name"));

                if (jo_inside.getString("topic").equals(val)) {
                    final String strTopic = jo_inside.getString("topic");
                    final String strContent = jo_inside.getString("content");


                    tvTopic.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    topic.setText(strTopic);
                                    tvTopic.setAlpha(1);
                                }
                            });

                    tvContent.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {



                                    tvContent.setText(strContent);

                                    tvContent.setAlpha(1);


                                }
                            });
                    alertDialog.cancel();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // popPage(p);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  void getvvvv(String val) {
        StringBuffer buffer = new StringBuffer();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("PUBLIC PROCUREMENT AND ASSET DISPOSAL ACT");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;


            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                // Log.d("Details-->", jo_inside.getString("name"));

                if (jo_inside.getString("topic").equals(val)) {
                    final String strTopic = jo_inside.getString("topic");
                    final String strContent = jo_inside.getString("content");
                    Pattern word = Pattern.compile(CurrentlyTyped.toLowerCase());
                    Matcher match = word.matcher(strContent.toLowerCase());
                    SpannableString str;

                    while (match.find()) {

                        Toast.makeText(getBaseContext(),
                                "Found  index "+ match.start() +" - "+ (match.end()-1), Toast.LENGTH_SHORT).show();
                        //Spannable WordtoSpan = new SpannableString(text);
                       str = new SpannableString(strContent);
                        //WordtoSpan.setSpan(new ForegroundColorSpan(Color.GREEN), match.start(), (match.end()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        //textView.setText(WordtoSpan);
                        str.setSpan(new BackgroundColorSpan(Color.YELLOW),  match.start(), match.end(), 0);

                        //textView.setText(str);
                        tvContent.setText(str);

                        tvContent.setAlpha(1);
                    }



                    tvTopic.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    tvTopic.setText(strTopic);
                                    tvTopic.setAlpha(1);
                                }
                            });

                   /* tvContent.animate().setDuration(1000).alpha(0)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {



                                    tvContent.setText(str);

                                    tvContent.setAlpha(1);


                                }
                            });*/

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // popPage(p);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popBookMark2(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Search suggestions");
        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }

    public void viewSpecificData() {

        Cursor res = myDb.getAllCredentials();

        if (res.getCount() == 0) {
            //Show message
           // showMessage("Error", "No data found Silas");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append("dbID : " + res.getString(0) + "\n");
            buffer.append("id : " + res.getString(1) + "\n");
            buffer.append("Name : " + res.getString(2) + "\n");
            buffer.append("Email : " + res.getString(3) + "\n");
            buffer.append("Key : " + res.getString(4) + "\n");
            // buffer.append("Address : " + res.getString(5) + "\n\n");

            UserId=res.getString(1);
            UserName=res.getString(2);
            Addr=res.getString(3);

            //Addrr.setText(Addr);
        }

        //Show all data


        //showMessage("Data", buffer.toString());

        //Addrr.setText(Addr);

    }

    private void sendToken(final String refreshedToken){
        //Showing a progress dialog while our app fetches the data from url


        //Creating a json array request to get the json from our api

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.update_token, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                Log.d("responce", s);

                //Displaying our grid

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ggg", volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("token_value",refreshedToken);
                stringMap.put("UserId",UserId);

                //Log.d("pupu", "pupu:"+ProductId);

                return stringMap;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void paintSharePop(View v) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);


        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog4 = builder.create();
        alertDialog4.show();


    }

    private void paintShare(final String email,final String topic,final String startindex,final String endindex){
        //Showing a progress dialog while our app fetches the data from url


        //Creating a json array request to get the json from our api

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Config.paint_share, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


               Log.d("responcemm", response);
                JSONObject jObj = null;

                try {
                    jObj = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                boolean error = false;
                try {
                    error = jObj.getBoolean("error");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (error) {//When response returns error
                    String errorMessage = null;
                    try {
                        errorMessage = jObj.getString("error_msg");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(getBaseContext(), errorMessage, Toast.LENGTH_SHORT).show();

                    showMessage("Paint-share failed",errorMessage);

                }else {Toast.makeText(getBaseContext(), "Paint-share was successful", Toast.LENGTH_SHORT).show();}

                //Displaying our grid

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("ggg", volleyError.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> stringMap = new HashMap<>();
                stringMap.put("email",email);
                stringMap.put("topic",topic);
                stringMap.put("startindex",startindex);
                stringMap.put("endindex",endindex);
                stringMap.put("UserId",UserId);

                Log.d("pupu", "pupu:"+startindex);

                return stringMap;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    

    public boolean signupCofirmation() {

        Cursor res = myDb.getAllCredentials();

        if (res.getCount() > 0) {
            //Show message
           // showMessage("Kindly sign up/Login", "You have to first sign up or log in to acces this feature.");

            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("dbID : " + res.getString(0) + "\n");
                buffer.append("id : " + res.getString(1) + "\n");
                buffer.append("Name : " + res.getString(2) + "\n");
                buffer.append("Email : " + res.getString(3) + "\n");
                buffer.append("Key : " + res.getString(4) + "\n");
                // buffer.append("Address : " + res.getString(5) + "\n\n");

                UserId = res.getString(1);
                UserName = res.getString(2);
                Addr = res.getString(3);

                Toast.makeText(getBaseContext(),
                        "You are logged in as " + UserName, Toast.LENGTH_SHORT).show();

                //Addrr.setText(Addr);
            }

            //Show all data


            //showMessage("Data", buffer.toString());

            //Addrr.setText(Addr);
            return true;
        }else


            showMessage("Kindly sign up/Login", "You have to first sign up or log in to acces this feature.");


        return false;
    }

    public void getPaintShare()
    {
        Cursor res = myDb.getAllPaintShare();

        if (res.getCount() == 0) {
            //Show message
            showMessage("Error", "No data found Silas");
            return;
        }
        String lastval = null;

        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            buffer.append("NAME : " + res.getString(1) + "\n");
            buffer.append("TOPIC : " + res.getString(2) + "\n");
            buffer.append("START : " + res.getString(3) + "\n");
            buffer.append("END : " + res.getString(4) + "\n\n");



        }


        //Show all data


        showMessage("Data", buffer.toString());
    }

    public String createTransactionID() throws Exception{
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
