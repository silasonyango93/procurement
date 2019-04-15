package com.ppada.silasonyango.procurement;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by hp on 3/2/2016.
 */
public class Landing extends AppCompatActivity {
    FragmentTransaction transaction;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    TextView header_user, header_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing);
        transaction = getSupportFragmentManager().beginTransaction();
        setupDrawer();
        transaction.add(R.id.landing_frame, new HomeSignedIn()).commit();
    }

    private void setupDrawer() {
        View v = LayoutInflater.from(this).inflate(R.layout.drawer_header, null);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.landing_navView);
        mNavigationView.addHeaderView(v);
        header_user = (TextView) v.findViewById(R.id.header_text);
        header_email = (TextView) v.findViewById(R.id.header_email);
        header_user.setText("Antony Owaga");
        header_email.setText("yitchhouse@inc.io");
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                transaction = getSupportFragmentManager()
                        .beginTransaction();
                switch (item.getItemId()){
                    case R.id.drawer_home:
                        mDrawerLayout.closeDrawers();
                        transaction.replace(R.id.landing_frame, new HomeSignedIn()).commit();
                        break;
                    case R.id.drawer_myCompany:
                        Company frag = new Company();
                        mDrawerLayout.closeDrawers();
                        transaction.replace(R.id.landing_frame, frag).commit();
                        break;
                }
                return false;
            }
        });
    }
    public void opendrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

}
