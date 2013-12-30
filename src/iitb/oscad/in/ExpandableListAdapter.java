package iitb.oscad.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.animation.AnimatorSet.Builder;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity context;
	private Map<String, List<String>> tutorials;
	private List<String> foss;

	public ExpandableListAdapter(Activity context, List<String> foss,
			Map<String, List<String>> tutorials) {
		this.context = context;
		this.tutorials = tutorials;
		this.foss = foss;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return tutorials.get(foss.get(groupPosition)).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final String FOSS = (String) getChild(groupPosition, childPosition);
		LayoutInflater inflater = context.getLayoutInflater();

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_item, null);
		}

		TextView item = (TextView) convertView.findViewById(R.id.tutorial);
		final PackageManager pm = context.getPackageManager();
		
		Button download = (Button) convertView.findViewById(R.id.download);
		download.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Do you want to download this video?");
				builder.setCancelable(false);
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
							String url = getVideoLink(groupPosition, childPosition);
							Intent intent_browser = new Intent(Intent.ACTION_VIEW);
							intent_browser.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
							intent_browser.setData(Uri.parse(url));
							context.startActivity(intent_browser);
//							List<String> child = tutorials.get(foss.get(groupPosition));
//	                        child.remove(childPosition);
//	                        notifyDataSetChanged();
					}

					
				});
				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});

		Button view = (Button) convertView.findViewById(R.id.view);
		view.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setMessage("Do you want to watch online video?");
				builder.setCancelable(false);
				builder.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						String url = getVideoLink(groupPosition, childPosition);
						try {
							pm.getPackageInfo("org.mozilla.firefox", PackageManager.GET_ACTIVITIES);
							Intent intent_browser = new Intent(Intent.ACTION_VIEW);
							intent_browser.setClassName("org.mozilla.firefox", "org.mozilla.firefox.App");
							intent_browser.setData(Uri.parse(url));
							context.startActivity(intent_browser);
						} catch (NameNotFoundException e) {
							AlertDialog.Builder builder = new AlertDialog.Builder(context);
							builder.setMessage(
									"Firefox not installed,redirecting to play store!")
									.setCancelable(true)
									.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
										
										@Override
										public void onClick(DialogInterface dialog, int which) {
											//do nothing
										}
									})
									.setPositiveButton("Ok",
											new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,
												int id) {
											final String appName = "org.mozilla.firefox";
											try {
												context.startActivity(new Intent(
														Intent.ACTION_VIEW,
														Uri.parse("market://details?id="
																+ appName)));
											} catch (android.content.ActivityNotFoundException anfe) {
												context.startActivity(new Intent(
														Intent.ACTION_VIEW,
														Uri.parse("http://play.google.com/store/apps/details?id="
																+ appName)));
											}
										}
									});

							AlertDialog alert = builder.create();
							alert.show();
						}
					}
				});
				builder.setNegativeButton("No",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
			}
		});
		
		
		item.setText(FOSS);
		return convertView;
	}

	private String getVideoLink(int groupPosition, int childPosition) {
		String oscad = "http://oscad.net/resource/tutorials/";
		String kicad = "http://video.spoken-tutorial.org/st_videos/KiCad/";
		String ngspice = "http://video.spoken-tutorial.org/st_videos/Ngspice/";
		
		String[][] video_links = new String[][]{{"oscad-introduction.ogv", 
				"oscad-schematic-creation.ogv",
				"oscad-designing-pcb.ogv"},
				{"C2/Designing-circuit-schematic-in-KiCad/Designing-circuit-schematic-in-KiCad-English.ogv",
					"C2/Electric-rule-checking-and-Netlist-generation/Electric-rule-checking-and-Netlist-generation-English.ogv",
					"C2/Mapping-components-in-KiCad/Mapping-components-in-KiCad-English.ogv",
					"C2/Designing-printed-circuit-board-in-KiCad/Designing-printed-circuit-board-in-KiCad-English.ogv"},
					{"C2/Operating-point-analysis-in-NGspice/Operating-point-analysis-in-NGspice-English.ogv",
						"C2/DC-Sweep-Analysis/DC-Sweep-Analysis-English.ogv"}};
			
			String url = "";
			for (int i = 0; i < video_links.length; i++) {
				for (int j = 0; j < video_links[i].length; j++) {
					if(groupPosition == i && childPosition == j){
						if (groupPosition == 0) {
							url = oscad +video_links[i][j];
						}else if(groupPosition == 1){
							url = kicad +video_links[i][j];
						}else{
							url = ngspice +video_links[i][j];
						}
					}
				}
			}
			
			return url;
	}
	
	public int getChildrenCount(int groupPosition) {
		return tutorials.get(foss.get(groupPosition)).size();
	}

	public Object getGroup(int groupPosition) {
		return foss.get(groupPosition);
	}

	public int getGroupCount() {
		return foss.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String FOSSName = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.group_item,
					null);
		}
		TextView item = (TextView) convertView.findViewById(R.id.tvFOSS);
		item.setTypeface(null, Typeface.BOLD);
		item.setText(FOSSName);
		return convertView;
	}

	public boolean hasStableIds() {
		return true;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
