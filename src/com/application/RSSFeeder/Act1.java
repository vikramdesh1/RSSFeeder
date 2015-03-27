package com.application.RSSFeeder;

import android.app.AlertDialog;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter; 
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
 

public class Act1 extends ListActivity {
	int focusflag=0,serviceflag=1,firstlaunch=0;
		SharedPreferences prefs;
		SharedPreferences.Editor editor;
		
		
		
		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.icon_menu, menu);
	    

	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.item1:
	    	switch(focusflag)
	    	{
	    	case 0:Toast.makeText(getApplicationContext(), "Opening default feed : News", Toast.LENGTH_SHORT).show();
	    	case 1:FeedParserFactory.feedUrl=prefs.getString("newsurl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	case 2:FeedParserFactory.feedUrl=prefs.getString("sportsurl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	case 3:FeedParserFactory.feedUrl=prefs.getString("entertainmenturl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	case 4:FeedParserFactory.feedUrl=prefs.getString("travelurl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	case 5:FeedParserFactory.feedUrl=prefs.getString("educationurl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	case 6:FeedParserFactory.feedUrl=prefs.getString("cricketurl", "");
	    	startActivity(new Intent(Act1.this,FeedDisplay.class));break;
	    	}
	    	 return true;
	    case R.id.item2:
	    	switch(focusflag)
	    	{
	    	case 0:Toast.makeText(getApplicationContext(), "Opening default feed settings : News", Toast.LENGTH_SHORT).show();
	    	case 1:startActivity(new Intent(Act1.this,NewsSettings.class));break;
	    	case 2:startActivity(new Intent(Act1.this,SportsSettings.class));break;
	    	case 3:startActivity(new Intent(Act1.this,EntertainmentSettings.class));break;
	    	case 4:startActivity(new Intent(Act1.this,TravelSettings.class));break;
	    	case 5:startActivity(new Intent(Act1.this,EducationSettings.class));break;
	    	case 6:startActivity(new Intent(Act1.this,CricketSettings.class));break;
	    	}
	        return true;
	    case R.id.item3:
	    	switch(serviceflag)
	    	{
	    	case 1:	stopService(new Intent(Act1.this,RSSFeederService.class));serviceflag=0;break;
	    	case 0: startService(new Intent(Act1.this,RSSFeederService.class));serviceflag=1;break;
	    	}
	    case R.id.item4:
	    {
	    	startActivity(new Intent(Act1.this,AboutPage.class));break;
	    }
	    default:
	        return super.onOptionsItemSelected(item);
	        }
		return true;
	    
	}
	
		
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, choices));
	  prefs=getSharedPreferences("rssprefs",0);
	  if(firstlaunch==0)
	  {
		editor=prefs.edit();		
		editor.putString("newsurl","http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&output=rss");
		editor.putString("cricketurl","http://www.espncricinfo.com/rss/content/story/feeds/0.xml");
		editor.putString("educationurl","http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&topic=b&output=rss");
		editor.putString("entertainmenturl","http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&topic=w&output=rss");
		editor.putString("sportsurl","http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&topic=s&output=rss");
		editor.putString("travelurl","http://news.google.co.in/news?pz=1&cf=all&ned=in&hl=en&topic=w&output=rss");
		editor.commit();
		firstlaunch=1;
	  }
	  else
	  {
		  
	  }
	  ListView lv = getListView();
	  lv.setTextFilterEnabled(true);
		  lv.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	AlertDialog.Builder builder = new AlertDialog.Builder(Act1.this);

		    	if(((TextView)view).getText()=="News")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("News Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("newsurl", "");
		    	                startActivity(new Intent(Act1.this,FeedDisplay.class));
		    	           }
		    	       })
		    	       .setNegativeButton("News Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	                startActivity(new Intent(Act1.this,NewsSettings.class));
		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	if(((TextView)view).getText()=="Sports")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("Sports Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("sportsurl", "nothing");
		    	        	   startActivity(new Intent(Act1.this,FeedDisplay.class));		    	           }
		    	       })
		    	       .setNegativeButton("Sports Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   startActivity(new Intent(Act1.this,SportsSettings.class));		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	if(((TextView)view).getText()=="Entertainment")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("Entertainment Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("entertainmenturl", "");
		    	        	   startActivity(new Intent(Act1.this,FeedDisplay.class));		    	           }
		    	       })
		    	       .setNegativeButton("Entertainment Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   startActivity(new Intent(Act1.this,EntertainmentSettings.class));		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	
		    	if(((TextView)view).getText()=="Travel")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("Travel Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("travelurl", "");
		    	        	   startActivity(new Intent(Act1.this,FeedDisplay.class));		    	           }
		    	       })
		    	       .setNegativeButton("Travel Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   startActivity(new Intent(Act1.this,TravelSettings.class));		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	
		    	if(((TextView)view).getText()=="Education")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("Education Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("educationurl", "");
		    	        	   startActivity(new Intent(Act1.this,FeedDisplay.class));		    	           }
		    	       })
		    	       .setNegativeButton("Education Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   startActivity(new Intent(Act1.this,EducationSettings.class));		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	
		    	
		    	if(((TextView)view).getText()=="Cricket")
		    	{
		    	builder.setMessage("Where would you like to go?")
		    	       .setCancelable(true)
		    	       .setPositiveButton("Cricket Display", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   FeedParserFactory.feedUrl=prefs.getString("cricketurl", "");
		    	        	   startActivity(new Intent(Act1.this,FeedDisplay.class));		    	           }
		    	       })
		    	       .setNegativeButton("Cricket Settings", new DialogInterface.OnClickListener() {
		    	           public void onClick(DialogInterface dialog, int id) {
		    	        	   startActivity(new Intent(Act1.this,CricketSettings.class));		    	           }
		    	      
		    	       });
		    	AlertDialog alert = builder.create();
		    	alert.show();
		    }
		    	
		    }
		    
		    
	  });
		  
		  lv.setOnItemSelectedListener(new OnItemSelectedListener() {
			    public void onItemSelected(AdapterView<?> parent, View view,
			        int position, long id) {
			    	
			    	if(((TextView)view).getText()=="News")
			    	{
			    	focusflag=1;
			    }
			    	if(((TextView)view).getText()=="Sports")
			    	{
				    	focusflag=2;

			    }
			    	if(((TextView)view).getText()=="Entertainment")
			    	{
				    	focusflag=3;

			    }
			    	
			    	if(((TextView)view).getText()=="Travel")
			    	{
				    	focusflag=4;

			    }
			    	
			    	if(((TextView)view).getText()=="Education")
			    	{
				    	focusflag=5;

			    }
			    	
			    	
			    	if(((TextView)view).getText()=="Cricket")
			    	{
				    	focusflag=6;

			    }
			    	
			    }

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
			
				}
			    
			    
		  });
			   
	}
	static final String[] choices = new String[] {
	    "News","Sports","Entertainment","Travel","Education","Cricket"
	  };
	

}
