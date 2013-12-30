package iitb.oscad.in;

import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class completed_books extends Activity{
	String subitem;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			System.out.println("no info"); 

		} else {

		subitem = extras.getString("flag");
			System.out.println("info:" + subitem);

		}
		
//		
//		if("Download Codes".equals(subitem)){
//	
//			int[] ids1 = new int[]{R.id.contact2,R.id.contact3,R.id.contact4,R.id.contact5};
//			int[] ids2 = new int[]{R.id.linear2,R.id.linear3,R.id.linear4};
//
//			
//			for (int i = 0; i < ids1.length; i++) {
//				TextView contentTextView1 = (TextView)findViewById(ids1[i]);
//				LinearLayout ln=(LinearLayout)findViewById(ids2[i]);
//				contentTextView1.setVisibility(TextView.GONE);
//				ln.setVisibility(LinearLayout.GONE);
//			}
////			Toast.makeText(getApplicationContext(), "hiiii", Toast.LENGTH_SHORT).show();
//			TextView contentTextView = (TextView)findViewById(R.id.contact1);
//			contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
//			SpannableString s = new SpannableString(Html.fromHtml(contact_to[i]));
//			Linkify.addLinks(s, Linkify.WEB_URLS);
//			contentTextView.setText(s);
//			
//			
//		}else {
			int[] ids1 = new int[]{R.id.contact3,R.id.contact4,R.id.contact5};
			int[] ids2 = new int[]{R.id.linear2,R.id.linear3,R.id.linear4};


			for (int i = 0; i < ids1.length; i++) {
				TextView contentTextView1 = (TextView)findViewById(ids1[i]);
				LinearLayout ln=(LinearLayout)findViewById(ids2[i]);
				contentTextView1.setVisibility(TextView.GONE);
				ln.setVisibility(LinearLayout.GONE);
			}
			

			String[] contact_to = new String[]{"Electronic Devices And Circuit Theory 10 Edition  by Louis Nashelsky, Robert L. Boylestad, Pearson, 2009 : http://www.oscad.in/textbook_run/10",
					"Microelectronic Circuits : Theory And Applications by Adel S. Sedra, Kenneth C. Smith, Oxford University Press, 2009 : http://www.oscad.in/textbook_run/19"};
			int[] ids = new int[]{R.id.contact1,R.id.contact2};
			
			for (int i = 0; i < contact_to.length; i++) {
				TextView contentTextView = (TextView)findViewById(ids[i]);
				contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
				SpannableString s = new SpannableString(Html.fromHtml(contact_to[i]));
				Linkify.addLinks(s, Linkify.WEB_URLS);
				contentTextView.setText(s);
			}
			
		}
		
		
		
//		}

}
