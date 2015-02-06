package com.gribchik.xo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class fragmentO extends Fragment {
	private ImageView mImageO;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	    View vO = inflater.inflate(R.layout.fragment_o, parent, false);
	    mImageO = (ImageView)vO.findViewById(R.id.ViewO);
	    return vO;
	}

}
