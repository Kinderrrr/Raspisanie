package Adapters;

import java.util.ArrayList;
import java.util.regex.Pattern;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raspviewproj.AlarmActivity;
import com.example.raspviewproj.AlarmSave;
import com.example.raspviewproj.MainActivity;
import com.example.raspviewproj.NotificationReciever;
import com.example.raspviewproj.R;

public class ListViewAlarmAdapter extends ArrayAdapter<String> {
	private final Context context;
	private ArrayList<String> listAll = new ArrayList<String>();
	private ArrayList<String> listCity = new ArrayList<String>();
	private ArrayList<String> listTimeLeave = new ArrayList<String>();
	private ArrayList<String> listTimeAlarm = new ArrayList<String>();

	public ListViewAlarmAdapter(Context context, ArrayList<String> listAll) {
		super(context, R.layout.list_item_alarm, listAll);
		this.context = context;
		this.listAll = listAll;

		for (int i = 0; i < listAll.size(); i++) {

			String[] parts = listAll.get(i).split(Pattern.quote("+"));
			listCity.add(parts[1]);
			listTimeLeave.add(parts[2]);
			listTimeAlarm.add(parts[3]);
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.list_item_alarm, parent, false);
		final int i = position;
		if ((position % 2) == 0) {
			RelativeLayout Line = (RelativeLayout) view.findViewById(R.id.LinearLayoutAlarmItem);
			Line.setBackgroundResource(R.color.light_blue);
		}
		TextView textViewCity = (TextView) view.findViewById(R.id.TextViewCity);
		TextView textViewTimeLeave = (TextView) view.findViewById(R.id.TextViewTimeLeave);
		TextView textViewTimeAlarm = (TextView) view.findViewById(R.id.TextViewTimeAlarm);
		Button button = (Button) view.findViewById(R.id.ButtonView);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Удаление");
				alertDialogBuilder.setMessage("Удалить напоминание?");
				alertDialogBuilder.setPositiveButton("Да",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {

								AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
								am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

								Uri myUri = Uri.parse(AlarmSave.get(context).get_key().get(i));
								AlarmSave.get(context).delete(i);

								Intent intent = new Intent(MainActivity.getAppContext(),NotificationReciever.class);
								intent.setData(myUri);

								PendingIntent sender = PendingIntent.getBroadcast(MainActivity.getAppContext(),0, intent, 0);

								am.cancel(sender);
								((AlarmActivity) context).onCreate(null);
							
							}
						});

				alertDialogBuilder.setNegativeButton("Нет",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int id) {

							}

						});

				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();

			}
		});

		textViewCity.setText(listCity.get(position));
		textViewTimeLeave.setText("Отправление в: "+ listTimeLeave.get(position));
		textViewTimeAlarm.setText("Напомнит: " + listTimeAlarm.get(position));

		return view;

	}

}
