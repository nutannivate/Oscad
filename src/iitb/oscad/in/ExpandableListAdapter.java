package iitb.oscad.in;

	import java.util.List;
	import java.util.Map;
	import android.app.Activity;
	import android.app.AlertDialog;
	import android.content.Context;
	import android.content.DialogInterface;
	import android.graphics.Typeface;
	import android.util.Log;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.view.ViewGroup;
	import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
	import android.widget.ImageView;
import android.widget.TextView;
	 
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
	 
	        Button delete = (Button) convertView.findViewById(R.id.download);
	        delete.setOnClickListener(new OnClickListener() {
	 
	            public void onClick(View v) {
	                AlertDialog.Builder builder = new AlertDialog.Builder(context);
	                builder.setMessage("Do you want to remove?");
	                builder.setCancelable(false);
	                builder.setPositiveButton("Yes",
	                        new DialogInterface.OnClickListener() {
	                            public void onClick(DialogInterface dialog, int id) {
	                                List<String> child =
	                                    tutorials.get(foss.get(groupPosition));
	                                child.remove(childPosition);
	                                notifyDataSetChanged();
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
