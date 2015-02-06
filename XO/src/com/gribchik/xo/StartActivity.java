package com.gribchik.xo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class StartActivity extends Activity {
	
	private ImageButton mButtonPlayer1;
	private ImageButton mButtonPlayer2;
	private ImageButton mButtonExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		mButtonPlayer1=(ImageButton)findViewById(R.id.ButtonPlayer1);
		mButtonPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OnePlayer = new Intent(StartActivity.this, Player1Activity.class);
                startActivity(OnePlayer);
            }
        });
		
		
		
		mButtonPlayer2=(ImageButton)findViewById(R.id.ButtonPlayer2);
		mButtonPlayer2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent TwoPlayer= new Intent(StartActivity.this, Player2Activity.class);
				startActivity(TwoPlayer);
			}
		});
		
		mButtonExit=(ImageButton)findViewById(R.id.ButtonExit3);
		mButtonExit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder Dialog = new AlertDialog.Builder(StartActivity.this);
				Dialog.setMessage("Вы действительно хотите выйти?")
						.setCancelable(false)
						.setNegativeButton("Нет",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										dialog.cancel();
									}
								})
				.setPositiveButton("Да",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});
				AlertDialog alert = Dialog.create();
				alert.show();
			}
		});

	}

}
