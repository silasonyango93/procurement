package com.ppada.silasonyango.procurement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hp on 2/20/2016.
 */
public class Company extends Fragment {
    View v;
    private Toolbar toolbar;

    public Company() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.company, container, false);
        setToolbar();
        return v;
    }

    private void setToolbar() {
        toolbar = (Toolbar) v.findViewById(R.id.company_toolbar);
        toolbar.setTitle(R.string.company);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Landing) getActivity()).opendrawer();
            }
        });
    }

}
