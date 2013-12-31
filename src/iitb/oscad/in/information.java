package iitb.oscad.in;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;

public class information extends Activity{
	static String IPaddr;
	String subitem;
	TextView text_book ,text_book1,text_book2,text_book3,text_book4;
	LinearLayout LinearLayout;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			System.out.println("no info"); 

		} else {

			subitem = extras.getString("flag");
			System.out.println("info:" + subitem);

		}

		TextView title = (TextView) findViewById(R.id.title);
		text_book = (TextView) findViewById(R.id.text_book);
		text_book1 = (TextView) findViewById(R.id.text_book1);
		text_book2 = (TextView) findViewById(R.id.text_book2);
		text_book3 = (TextView) findViewById(R.id.text_book3);
		text_book4 = (TextView) findViewById(R.id.text_book4);


		LinearLayout= (LinearLayout)findViewById(R.id.linear_layout1);
		text_book.setMovementMethod(LinkMovementMethod.getInstance());
		text_book1.setMovementMethod(LinkMovementMethod.getInstance());

		if("Textbook Companion Project".equals(subitem)){
			title.setText("Textbook Companion Project");
			text_book2.setVisibility(TextView.GONE);
			text_book3.setVisibility(TextView.GONE);
			text_book4.setVisibility(TextView.GONE);
			LinearLayout.setVisibility(TextView.GONE);

			text_book.setText(Html.fromHtml(getString(R.string.text_book)));
			text_book1.setText(Html.fromHtml(getString(R.string.text_book2)));	
		}else if ("Internship".equals(subitem)) {
			title.setText("Textbook Companion Internship");
			text_book2.setVisibility(TextView.GONE);
			text_book3.setVisibility(TextView.GONE);
			text_book4.setVisibility(TextView.GONE);
			LinearLayout.setVisibility(TextView.GONE);

			text_book.setText(Html.fromHtml(getString(R.string.text_book3)));
			text_book1.setText(Html.fromHtml(getString(R.string.text_book4)));	

		}else if ("Guidelines for Coding".equals(subitem)) {
			title.setText("Guidelines for Creating project");
			text_book.setVisibility(TextView.GONE);
			text_book2.setVisibility(TextView.GONE);
			text_book3.setVisibility(TextView.GONE);
			text_book4.setVisibility(TextView.GONE);
			LinearLayout.setVisibility(TextView.GONE);

			text_book1.setText(Html.fromHtml(getString(R.string.guideline)));

		}else if ("Honorarium".equals(subitem)) {
			title.setText("Textbook Companion Honorarium Details");
			text_book.setText(Html.fromHtml(getString(R.string.Honorarium)));
			text_book1.setText(Html.fromHtml(getString(R.string.Honorarium1)));
			text_book2.setText(Html.fromHtml(getString(R.string.Honorarium2)));
			text_book3.setText(Html.fromHtml(getString(R.string.Honorarium3)));
			text_book4.setText(Html.fromHtml(getString(R.string.Honorarium4)));

		}else if ("Lab Migration Project".equals(subitem)) {
			title.setText("Lab Migration");
			text_book.setText(Html.fromHtml(getString(R.string.lab_migration1)));
			text_book1.setText(Html.fromHtml(getString(R.string.lab_migration2)));
			text_book2.setText(Html.fromHtml(getString(R.string.lab_migration3)));
			text_book2.setMovementMethod(LinkMovementMethod.getInstance());
			text_book3.setVisibility(TextView.GONE);
			text_book4.setVisibility(TextView.GONE);
			LinearLayout.setVisibility(TextView.GONE);
		}
		else if ("Lab Migration Procedure".equals(subitem)) {
			title.setText("Lab Migration Procedure");
			text_book.setText(Html.fromHtml(getString(R.string.lab_migration4)));
			text_book1.setText(Html.fromHtml(getString(R.string.lab_migration5)));
			text_book1.setMovementMethod(LinkMovementMethod.getInstance());
			text_book2.setText(Html.fromHtml(getString(R.string.lab_migration6)));
			text_book3.setText(Html.fromHtml(getString(R.string.lab_migration7)));
			text_book3.setMovementMethod(LinkMovementMethod.getInstance());
			text_book4.setText(Html.fromHtml(getString(R.string.lab_migration8)));

		}
		else if ("Lab Migration Honorarium".equals(subitem)) {
			title.setText("Lab Migration Honorarium");
			text_book.setText(Html.fromHtml(getString(R.string.lab_migration9)));
			text_book1.setText(Html.fromHtml(getString(R.string.lab_migration10)));
			text_book2.setVisibility(TextView.GONE);
			text_book3.setVisibility(TextView.GONE);
			text_book4.setVisibility(TextView.GONE);
			LinearLayout.setVisibility(TextView.GONE);
		}
		//		}else if ("Download Codes".equals(subitem)) {
		//			String url = "http://oscad.in/textbook_run";
		//			Intent i = new Intent(Intent.ACTION_VIEW);
		//			i.setData(Uri.parse(url));
		//			startActivity(i);	
		//			}



	}

}
