package com.gribchik.xo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class fragmentX extends Fragment {
	private ImageView mImageX;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
	    View vX = inflater.inflate(R.layout.fragment_x, parent, false);
	    mImageX = (ImageView)vX.findViewById(R.id.ViewX);
	    return vX;
	}

}