package com.example.silasonyango.ewe;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ExplosionField mExplosionField;
    View root;
    final Handler handler = new Handler();
    TextView tvBook,tvAnnotations,tvReport,tvBookMarks,tvNotices,tvNews,tvHelp,tvWebsite,tvRegister;
    CardView CardSignUp,CardAct,CardNotes,CardBookMarks,CardNews,CardWebsite,White,WhiteBottom;
    DatabaseHelper myDb;
    AlertDialog alertDialog,alertDialog2;
    LayoutInflater inflater,inflater2,inflater3;
    Context mContext;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       mExplosionField = ExplosionField.attach2Window(this);
        //addListener(findViewById(R.id.root));

        root=findViewById(R.id.root);
        mHandler= new Handler();
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext=getBaseContext();
        myDb = new DatabaseHelper(this);
        inflater=this.getLayoutInflater();
        inflater2=this.getLayoutInflater();
        inflater3=this.getLayoutInflater();
       toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        tvBook=(TextView) findViewById(R.id.book);
        tvAnnotations=(TextView) findViewById(R.id.annotations);
        tvReport=(TextView) findViewById(R.id.report);
        tvBookMarks=(TextView) findViewById(R.id.bookmarks);
        tvNotices=(TextView) findViewById(R.id.notices);
        tvNews=(TextView) findViewById(R.id.news);
        tvHelp=(TextView) findViewById(R.id.help);
        tvWebsite=(TextView) findViewById(R.id.website);
        tvRegister=(TextView) findViewById(R.id.signup);


        CardSignUp=(CardView) findViewById(R.id.signupcard);
        CardWebsite=(CardView) findViewById(R.id.websitecard);
        CardAct=(CardView) findViewById(R.id.bookcard);
        CardNotes=(CardView) findViewById(R.id.bookmarkcard);
        CardBookMarks=(CardView) findViewById(R.id.newscard);
        CardNews=(CardView) findViewById(R.id.nana);
        White=(CardView) findViewById(R.id.whitetop);
        WhiteBottom=(CardView) findViewById(R.id.pupucard);
        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");

        tvBook.setTypeface(font);
        tvAnnotations.setTypeface(font);
        tvReport.setTypeface(font);
        tvBookMarks.setTypeface(font);
        tvNotices.setTypeface(font);
        tvNews.setTypeface(font);
        tvHelp.setTypeface(font);
        tvWebsite.setTypeface(font);
        tvRegister.setTypeface(font);

        tvNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(WhiteBottom);
                mExplosionField.explode(White);

                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);

                CardWebsite.startAnimation(animShake);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ppoa.go.ke"));
                        startActivity(browserIntent);
                    }
                }, 800);



            }
        });



        tvBook.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                mExplosionField.explode(WhiteBottom);
                mExplosionField.explode(White);

                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);

                CardAct.startAnimation(animShake);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String pop="nono";
                        Intent i= new Intent(Home.this,MainActivity.class);
                        i.putExtra("BookMark",pop);
                        startActivity(i);
                    }
                }, 600);

            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(final View view) {

               final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);




                tvRegister.startAnimation(animShake);

               /* boolean net= isNetworkConnected();
                if(net!=true)
                {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
                else{Intent i= new Intent(Home.this,SignUp.class);

                    startActivity(i);}*/
            }
        });



        tvAnnotations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);
                mExplosionField.explode(WhiteBottom);
                mExplosionField.explode(White);
                CardNotes.startAnimation(animShake);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View p = inflater.inflate(R.layout.annotation_list, null);
                        popPage(p,mContext);
                    }
                }, 800);


            }
        });

        tvBookMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(WhiteBottom);
                mExplosionField.explode(White);
                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);

                CardBookMarks.startAnimation(animShake);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View g = inflater3.inflate(R.layout.bookmark_list, null);
                        popBookMark(g,mContext);
                    }
                }, 800);




            }
        });
        tvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(WhiteBottom);
                mExplosionField.explode(White);
                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake_card);
                CardNews.startAnimation(animShake);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean net= isNetworkConnected();
                        if(net!=true)
                        {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
                        else{Intent i= new Intent(Home.this,SearchResults.class);

                            startActivity(i);}
                    }
                }, 800);



            }
        });

        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
                tvReport.startAnimation(animShake);



            }
        });
        tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,TermsAndConditionsActivity.class);
                startActivity(i);




            }
        });
        tvWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation animShake = AnimationUtils.loadAnimation(getBaseContext(), R.anim.shake);
                tvWebsite.startAnimation(animShake);





            }
        });
        White.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(view);
                mExplosionField.explode(WhiteBottom);


            }
        });
        WhiteBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplosionField.explode(view);
                mExplosionField.explode(White);



            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

  /*  private void addListener(View root) {
        if (root instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) root;
            for (int i = 0; i < parent.getChildCount(); i++) {
                addListener(parent.getChildAt(i));
            }
        } else {
            root.setClickable(true);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExplosionField.explode(v);
                    v.setOnClickListener(null);
                }
            });
        }
    }
*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
        }else if(item.getItemId() == R.id.action_reset) {
           /* View root = findViewById(R.id.root);
            reset(root);
            addListener(root);
            mExplosionField.clear();*/
            return true;
        }

        return super.onOptionsItemSelected(item);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            View p = inflater.inflate(R.layout.annotation_list, null);
            popPage2(p,mContext);
        } else if (id == R.id.nav_gallery) {
            View g = inflater3.inflate(R.layout.bookmark_list, null);
            popBookMark2(g,mContext);
        }  else if (id == R.id.nav_login) {


            boolean net= isNetworkConnected();
            if(net!=true)
            {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
            else{Intent c=new Intent(Home.this,SignIn.class);
                startActivity(c);}
        } else if (id == R.id.nav_register) {


            boolean net= isNetworkConnected();
            if(net!=true)
            {showMessage("No internet connectivity", "Check your internet connectivity and try again.");}
            else{Intent c=new Intent(Home.this,SignUp.class);
                startActivity(c);}
        }else if (id == R.id.nav_paintshare) {
            View p = inflater.inflate(R.layout.annotation_list, null);
            popPaintShares(p,mContext);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getAll(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();
        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            //Show message
            showMessage("No annotations Available", "You currently have no annotations saved");
            return;
        }
        String lastval = null;
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            list.add(res.getString(1));

        }

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
               String val= ((TextView) view).getText().toString();
                View p = inflater2.inflate(R.layout.paragraph, null);

                popParagraph( p,val);

            }

        });
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popPage(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getAll(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popParagraph(View v,String val) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        viewSpecificData(v,val);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog = builder.create();
        alertDialog.show();





    }

    public void viewSpecificData(View v,String val) {
        TextView tvTopic=(TextView) v.findViewById(R.id.topic);
        TextView tvContent=(TextView) v.findViewById(R.id.content);

                        Cursor res = myDb.getSpecificData(val);

                        if (res.getCount() == 0) {
                            //Show message
                            showMessage("No annotations Available", "You currently have no annotations saved");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            tvTopic.setText(res.getString(1));
                            tvContent.setText(res.getString(2));
                        }




    }


    public void getBookMarks(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();
        Cursor res = myDb.getAllSecondTable();

        if (res.getCount() == 0) {
            //Show message
            showMessage("No Bookmarks available", "You currently have no Bookmarks");
            return;
        }
        String lastval = null;
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            list.add(res.getString(1));

        }

        final StableArrayAdapter adapter = new StableArrayAdapter(c,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Intent intent =new Intent(Home.this,MainActivity.class);
                intent.putExtra("BookMark",((TextView) view).getText().toString().trim());
                intent.putExtra("Topic","");
                intent.putExtra("StartIndex","");
                intent.putExtra("EndIndex","");
                intent.putExtra("Name","");
                startActivity(intent);

            }

        });
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popBookMark(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getBookMarks(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popPage2(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getAll2(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }

    public void getAll2(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<Annotations> list = new ArrayList<>();
        Cursor res = myDb.getAllData();

        if (res.getCount() == 0) {
            //Show message
            showMessage("No annotations Available", "You currently have no annotations saved");
            return;
        }
        String lastval = null;
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            list.add(new Annotations(R.drawable.delete,res.getString(1)));

        }

        MyListAdapter2 adapter = new MyListAdapter2(this, R.layout.my_custom_list, list);

        //attaching adapter to the listview
        listview.setAdapter(adapter);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popBookMark2(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getBookMarks2(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }

    public void getBookMarks2(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<Annotations> list = new ArrayList<>();
        Cursor res = myDb.getAllSecondTable();

        if (res.getCount() == 0) {
            //Show message
            showMessage("No Bookmarks available", "You currently have no Bookmarks");
            return;
        }
        String lastval = null;
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {
            list.add(new Annotations(R.drawable.delete,res.getString(1)));
           // list.add(res.getString(1));

        }

        MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, list);

        //attaching adapter to the listview
        listview.setAdapter(adapter);



      /*  listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);

                String val= ((TextView) view).getText().toString().trim();

                myDb.deleteBookMarks(val);
                alertDialog2.cancel();
                Toast.makeText(getApplicationContext(),
                        "BookMark Succesfully deleted", Toast.LENGTH_SHORT).show();
            }

        });*/
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void popPaintShares(View v,Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        getPaintShares(v,c);

        builder.setView(v);

        builder.setCancelable(true);
        // builder.setTitle("Submit verification code");

        //editText.setText("test label");
        alertDialog2 = builder.create();
        alertDialog2.show();






    }

    public void getPaintShares(final View v, Context c)
    {
        final ListView listview = (ListView) v.findViewById(R.id.listview);
        final ArrayList<Annotations> list = new ArrayList<>();
        Cursor res = myDb.getAllPaintShare();

        if (res.getCount() == 0) {
            //Show message
            showMessage("No PaintShares Available", "You currently have no PaintShares saved");
            return;
        }
        String lastval = null;
        StringBuffer buffer = new StringBuffer();


        while (res.moveToNext()) {

            list.add(new Annotations(R.drawable.delete,res.getString(1)));

        }

        MyListAdapter9 adapter = new MyListAdapter9(this, R.layout.my_custom_list, list);

        //attaching adapter to the listview
        listview.setAdapter(adapter);
    }



}
