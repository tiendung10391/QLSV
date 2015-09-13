package com.qlsv.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.qlsv.R;

/**
 * Created by kunph_000 on 08/09/2015.
 */
public class Fragment_lichhoc extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lich_hoc, container, false);
    }
}
