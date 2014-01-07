package iitb.oscad.in;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	static List<String> groupList;
	static List<String> childList;
	static Map<String, List<String>> tutorials;
	static ExpandableListView expListView;
	static AlertDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
		.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 9;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			case 3:
				return getString(R.string.title_section4).toUpperCase(l);
			case 4:
				return getString(R.string.title_section5).toUpperCase(l);
			case 5:
				return getString(R.string.title_section6).toUpperCase(l);
			case 6:
				return getString(R.string.title_section7).toUpperCase(l);
			case 7:
				return getString(R.string.title_section8).toUpperCase(l);
			case 8:
				return getString(R.string.title_section9).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		Context thisContext;

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = null;
			int arg_no = getArguments().getInt(ARG_SECTION_NUMBER);
			switch (arg_no) {
			case 1:

				rootView = inflater.inflate(R.layout.home,container, false);
				TextView homecontentTextView = (TextView) rootView.findViewById(R.id.intro);
				homecontentTextView.setMovementMethod(LinkMovementMethod.getInstance());
				SpannableString s = new SpannableString(Html.fromHtml(getString(R.string.intro)));
				Linkify.addLinks(s, Linkify.WEB_URLS);
				homecontentTextView.setText(s);

				homecontentTextView = (TextView) rootView.findViewById(R.id.features);
				homecontentTextView.setText(Html.fromHtml(getString(R.string.features)));

				homecontentTextView = (TextView) rootView.findViewById(R.id.books);
				homecontentTextView.setMovementMethod(LinkMovementMethod.getInstance());
				homecontentTextView.setText(Html.fromHtml(getString(R.string.books)));

				break;
			case 2:
				rootView = inflater.inflate(R.layout.downloads,	container, false);
				/***
				 * Download oscad installer tar file for Linux
				 */
				TextView linux1 = (TextView)rootView.findViewById(R.id.down_linux);
				linux1.setMovementMethod(LinkMovementMethod.getInstance());
				String link_linux = "<a href='http://www.oscad.in/download/OSCAD_installer.tar.gz'>Oscad Installer - Linux</a>&nbsp;&nbsp;(6.2 MB)";
				linux1.setText(Html.fromHtml(link_linux));

				/***
				 * Download oscad installer zip file for Windows
				 */
				TextView windows = (TextView)rootView.findViewById(R.id.down_oscad_IW);
				windows.setMovementMethod(LinkMovementMethod.getInstance());
				String text = "<a href='http://www.oscad.in/download/Oscad-windows-installer.zip'>Oscad Installer - Windows</a>&nbsp;&nbsp;(150 MB)";
				windows.setText(Html.fromHtml(text));

				/***
				 * Download oscad installation instrucion PDF file.
				 */
				TextView windowsInsta = (TextView)rootView.findViewById(R.id.down_oscad_II);
				windowsInsta.setMovementMethod(LinkMovementMethod.getInstance());
				String text1 = "<a href='http://www.oscad.in/resource/instruction-sheet/Oscad-Installation-Windows.pdf'>Oscad Installation Instructions for Windows</a>&nbsp;&nbsp;(pdf)";
				windowsInsta.setText(Html.fromHtml(text1));

				/***
				 * Download oscad e-book pdf.
				 */
				Button EBook = (Button)rootView.findViewById(R.id.down_Ebook);
				EBook.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/book/oscad.pdf")));
					}
				});
				/***
				 * Download oscad example .
				 */
				TextView tv = (TextView)rootView.findViewById(R.id.tvTitle);
				tv.setText(Html.fromHtml(getString(R.string.space)));
				Button osExample = (Button)rootView.findViewById(R.id.down_OE);
				osExample.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/Examples.tar.gz")));
					}
				});


				/***
				 * Download oscad errata pdf .
				 */
				Button errata = (Button)rootView.findViewById(R.id.down_EOB);
				errata.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/book/errata.pdf")));
					}
				});

				/***
				 * Download Oscad Companion CD .
				 */
				Button linux = (Button)rootView.findViewById(R.id.down_OCCD);
				linux.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/download/Oscad.zip")));
					}
				});

				/***
				 * It will intent to micro book page 
				 */
				TextView micro = (TextView)rootView.findViewById(R.id.down_micro);
				micro.setMovementMethod(LinkMovementMethod.getInstance());
				String link_micro = "<a href='http://www.flipkart.com/microelectronic-circuits-theory-applications-with-cd-5/p/itmczytdpnuhxm6q'>Microelectronic Circuits by Sedra and Smith.</a>";
				micro.setText(Html.fromHtml(link_micro));
				break;
			case 3:

				//download oscad installation scripts
				rootView = inflater.inflate(R.layout.spoken_tutorial,container, false);
				Button windowInstuction = (Button)rootView.findViewById(R.id.windowInstruction);
				//for windows
				windowInstuction.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/instruction-sheet/Oscad-Installation-Windows.pdf")));
					}
				});

				//for linux
				Button linuxSheet = (Button)rootView.findViewById(R.id.linuxSheet);
				linuxSheet.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/instruction-sheet/Oscad-Instruction-Sheet-Linux.pdf")));
					}
				});

				//windows sheets
				Button windowSheet = (Button)rootView.findViewById(R.id.windowSheet);
				windowSheet.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.oscad.in/resource/instruction-sheet/Oscad-Instruction-Sheet-Windows.pdf")));
					}
				});

				//expandable list activity to view or download spoken tutorials
				information.display_exp_list = false;
				String[] foss_list = new String[]{"Oscad","KiCad","Ngspice"};
				createGroupList(foss_list);

				createCollection();

				expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
				final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
						getActivity(), groupList, tutorials);
				expListView.setAdapter(expListAdapter);
				break;
			case 4:
				rootView = inflater.inflate(R.layout.contact,container, false);

				String[] contact_to = new String[]{"Textbook Companion : <a href='mailto:textbook-companion@oscad.in'>textbook-companion@oscad.in</a> ",
						"Lab migration : <a href='mailto:lab-migration@oscad.in'>lab-migration@oscad.in</a>",
						"SELF workshops : <a href='mailto:SELF-workshop@oscad.in'>SELF-workshop@oscad.in</a>",
						"Oscad development and enhancing its capabilities : <a href='mailto:Oscad-dev@oscad.in'>Oscad-dev@oscad.in</a>",
				"Feedback on Oscad book : <a href='mailto:Oscad-textbook@oscad.in'>Oscad-textbook@oscad.in</a>",
				"General Queries : <a href='mailto:queries@oscad.in'>queries@oscad.in</a>"};
				int[] ids = new int[]{R.id.contact1,R.id.contact2,R.id.contact3,R.id.contact4,R.id.contact5,R.id.contact6};

				for (int i = 0; i < contact_to.length; i++) {
					TextView contentTextView = (TextView) rootView.findViewById(ids[i]);
					contentTextView.setVisibility(View.VISIBLE);
					contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
					contentTextView.setText(Html.fromHtml(contact_to[i]));
				}
				break;
			case 5:
				rootView = inflater.inflate(R.layout.faqs,container, false);

				int list[]=new int[]{R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView6,R.id.textView7,R.id.textView8};

				int list1[]=new int[]{R.string.FAQs,R.string.FAQs2,R.string.FAQs3,R.string.FAQs4,R.string.FAQs5,R.string.FAQs6};

				for (int i = 0; i < list.length; i++) {
					TextView contentTextView = (TextView) rootView.findViewById(list[i]);
					contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
					contentTextView.setBackgroundColor(Color.parseColor("#15060b"));
					contentTextView.setTextColor(Color.parseColor("#d0ad85"));
					contentTextView.setText(Html.fromHtml(getString(list1[i])));

				}

				int list2[]=new int[]{R.id.textView1,R.id.textView5};

				for (int i = 0; i < list2.length; i++) {
					TextView contentTextView1 = (TextView) rootView.findViewById(list2[i]);
					contentTextView1.setBackgroundColor(Color.parseColor("#302b31"));
					contentTextView1.setTextColor(Color.parseColor("#d0ad85"));
				}
				break;
			case 6:
				rootView = inflater.inflate(R.layout.contact,container, false);

				String[] weblinks = new String[]{"http://fossee.in/",
						"http://scilab.in/",
						"http://python.fossee.in/",
						"http://cfd.fossee.in/",
				"http://spoken-tutorial.org/"};
				int[] id = new int[]{R.id.contact1,R.id.contact2,R.id.contact3,R.id.contact4,R.id.contact5};

				for (int i = 0; i < weblinks.length; i++) {
					TextView contentTextView = (TextView) rootView.findViewById(id[i]);
					contentTextView.setMovementMethod(LinkMovementMethod.getInstance());
					SpannableString sContact = new SpannableString(Html.fromHtml(weblinks[i]));
					Linkify.addLinks(sContact, Linkify.WEB_URLS);
					contentTextView.setText(sContact);
				}
				break;
			
			case 7:
				rootView = inflater.inflate(R.layout.list,container, false);

				String[] from = new String[] {"names_"};
				int[] to = new int[] {R.id.tvname};

				final List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();

				final String names[] = new String[]{"Textbook Companion Project","Internship","Guidelines for Coding","Honorarium","Textbook Companion FAQ's","Completed Books","Download Codes"};

				for (int i = 0; i < names.length; i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("names_",names[i]);
					fillMaps.add( map);

				}

				SimpleAdapter adapter;
				adapter = new SimpleAdapter(getActivity().getApplicationContext(), fillMaps,
						R.layout.text_book_companion, from, to);

				final ListView lv = (ListView) rootView.findViewById( R.id.listView1 );
				lv.setAdapter(adapter);		

				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView a, View v, int position, long id) {

						if(position==4){
							LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
							View layout = inflater.inflate(R.layout.contact, null);
							//Building DatepPcker dialog
							AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
							builder.setView(layout);
							TextView title = (TextView)layout.findViewById(R.id.option_title);
							title.setVisibility(View.VISIBLE);
							
							final String[] categories = new String[]{"Queries regarding Textbook companion",
									"Queries regarding book proposal & coding",
									"Queries regarding Internship forms and honorarium"};
							int[] text_id = new int[]{R.id.contact1,R.id.contact2,R.id.contact3};

							TextView contact4 = (TextView)layout.findViewById(R.id.contact4);
							contact4.setVisibility(View.GONE);
							contact4 = (TextView)layout.findViewById(R.id.contact5);
							contact4.setVisibility(View.GONE);
							LinearLayout ll = (LinearLayout)layout.findViewById(R.id.linear3);
							ll.setVisibility(View.GONE);
							for (int i = 0; i < categories.length; i++) {
								TextView contentTextView = (TextView) layout.findViewById(text_id[i]);
								contentTextView.setText(categories[i]);
								final String option = categories[i];
								contentTextView.setOnClickListener(new OnClickListener() {
									
									@Override
									public void onClick(View v) {
										dialog.dismiss();
										//Toast.makeText(getActivity(), option, Toast.LENGTH_LONG).show();
										Intent intent= new Intent(getActivity(), information.class);
										intent.putExtra("flag","Textbook Companion FAQ's");
										intent.putExtra("option",option);
										startActivity(intent);
										
									}
								});
							}
							
							dialog=builder.create();
				    		dialog.show();
						}else if(position==6){
							String url = "http://oscad.in/textbook_run";
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse(url));
							startActivity(i);	
						}else {
							Intent intent= new Intent(getActivity(), information.class);
							intent.putExtra("flag",names[position]);
							startActivity(intent);
						}
					}
				});
				break;
			case 8:
				//lab migration project
				rootView = inflater.inflate(R.layout.list,container, false);

				String[] title = new String[] {"names_"};
				int[] tv_ids = new int[] {R.id.tvname};

				final List<HashMap<String, String>> fillMaps1 = new ArrayList<HashMap<String, String>>();

				final String headings[] = new String[]{"Lab Migration Project","Lab Migration Procedure","Lab Migration Honorarium"};

				for (int i = 0; i < headings.length; i++) {
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("names_",headings[i]);
					fillMaps1.add( map);

				}

				SimpleAdapter adapter1;
				adapter1 = new SimpleAdapter(getActivity().getApplicationContext(), fillMaps1,
						R.layout.text_book_companion, title, tv_ids);

				final ListView lv1 = (ListView) rootView.findViewById( R.id.listView1 );
				lv1.setAdapter(adapter1);		

				TextView tvname = (TextView)rootView.findViewById(R.id.textView5);
				tvname.setText("Lab Migration");
				
				lv1.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView a, View v, int position, long id) {

						Intent intent= new Intent(getActivity(), information.class);
						intent.putExtra("flag",headings[position]);
						startActivity(intent);
					}
				});
				break;
			case 9:
				rootView = inflater.inflate(R.layout.list,container, false);
				
				 String[] from_names = new String[] {"names_"};
				 int[] to_id = new int[] {R.id.tvworkshops};
	      
			     final List<HashMap<String, String>> fillMaps2 = new ArrayList<HashMap<String, String>>();
			     
				final String workshops[] = new String[]{"Past Remote Workshops","Upcoming Remote Workshops","Workshop Details"};
				 for (int i = 0; i < workshops.length; i++) {
				     HashMap<String, String> map = new HashMap<String, String>();
		        	 map.put("names_",workshops[i]);
			         fillMaps2.add( map);

		        }

		        SimpleAdapter workshop_adapter;
		        adapter = new SimpleAdapter(getActivity().getApplicationContext(), fillMaps2,
	 				R.layout.workshops, from_names, to_id);
			
		        final ListView listv = (ListView) rootView.findViewById( R.id.listView1 );
		        final TextView textview = (TextView) rootView.findViewById( R.id.textView5);
		        textview.setText("Workshops");
		        listv.setAdapter(adapter);	
		        listv.setOnItemClickListener(new OnItemClickListener() {
		        	
					@Override
					public void onItemClick(AdapterView a, View v, int position, long id) {
						
						if(position==0){
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://oscad.in/completed_workshops_list")));
						}
						else if(position==1){
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://oscad.in/upcoming_workshops")));
						}else{
							startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://oscad.in/view_completed_workshop")));
						}
					}
				});
			default:
				break;
			}

			return rootView;
		}


		public static void createGroupList(String[] list) {
			groupList = new ArrayList<String>();
			for (int i = 0; i < list.length; i++) {
				groupList.add(list[i]);
			}
		}

		public static void createCollection() {
			// preparing FOSS tutorials(child)
			String[] oscadTutorials = { "1. Introduction to Oscad", "2. Schematic Creation and Simulation using Oscad",
			"3. Designing PCB using Oscad" };
			String[] kicadTutorials = { "1. Designing circuit schematic in KiCad", 
					"2. Electric rule checking and Netlist generation", 
					"3. Mapping components in KiCad",
			"4. Designing printed circuit board in KiCad"};
			String[] ngspiceTutorials = { "1. Operating point analysis in NGspice", "2. DC Sweep Analysis"};

			tutorials = new LinkedHashMap<String, List<String>>();

			for (String FOSS : groupList) {
				if (FOSS.equals("Oscad")) {
					loadChild(oscadTutorials);
				}
				else if (FOSS.equals("KiCad"))
					loadChild(kicadTutorials);
				else
					loadChild(ngspiceTutorials);

				tutorials.put(FOSS, childList);
			}
		}

		public static void loadChild(String[] fossTutorials) {
			childList = new ArrayList<String>();
			for (String model : fossTutorials)
				childList.add(model);
		}

		public static void createQuestionCollection(String[] array) {
			tutorials = new LinkedHashMap<String, List<String>>();

			for (int i = 0; i < groupList.size(); i++) {
				childList = new ArrayList<String>();
				childList.add(Html.fromHtml(array[i]).toString());
				tutorials.put(groupList.get(i), childList);
			}
		}

	}

	@Override
	public void onBackPressed() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
		.setCancelable(false)
		.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				finish();
				android.os.Process
				.killProcess(android.os.Process.myPid());

			}
		})
		.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}
}
