package com.example.silasonyango.ewe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 2/18/2016.
 */
public class HomeSignedIn extends Fragment {
    View v;
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public HomeSignedIn() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.home_signedin, container, false);
        setToolbar();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void setToolbar() {
        toolbar = (Toolbar) v.findViewById(R.id.home_toolbar);
        toolbar.setTitle("Home");
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Landing) getActivity()).opendrawer();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.signed_in_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_landing_settings) {
//            Intent in = new Intent(getBaseContext(), Settings.class);
//            startActivity(in);
            return true;
        }
        if (id == R.id.menu_landing_logout) {
            LogoutDialog alert = new LogoutDialog();
            alert.show(getChildFragmentManager(), "Logout Account");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
