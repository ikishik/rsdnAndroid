package net.ikishik.RsdnAndroid;



import android.app.ListActivity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import net.ikishik.RsdnAndroid.RsdnAndroidDBStatic.ForumGroups;
import net.ikishik.RsdnAndroid.RsdnAndroidDBStatic.Forums;
import net.ikishik.rsdnClient.*;

public class RsdnAndroidActivity extends ListActivity {
	
	 private static final String[] PROJECTION = new String[] {
		 ForumGroups._ID, // 0
		 ForumGroups.FORUMGROUPNAME, // 1
	 };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        RsdnDbHelper dbOpenHelper = new RsdnDbHelper(RsdnAndroidActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
       
      
        db.close();
        
        //Intent intent = getIntent();
        //if (intent.getData() == null) {
            //intent.setData(Forums.CONTENT_URI);
        //}
        
        //Uri mUri = getIntent().getData();
                
        Cursor cursor = managedQuery(ForumGroups.CONTENT_URI, PROJECTION, null, null,
        		ForumGroups.DEFAULT_SORT_ORDER);
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.activity_list_item , cursor,
                new String[] { ForumGroups.FORUMGROUPNAME }, new int[] { android.R.id.text1 });
        setListAdapter(adapter);
        
       
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	
    	menu.add(0,1,0, R.string.synchronize_menu);
    	menu.add(0,2,0, R.string.preferences_menu);
    	
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	
    		if(item.getItemId() == 1)
    		{
    			try {
    				
    				Synchroner.syncForumsAndGrousp(getContentResolver());
    				
    				//Synchroner.syncNewUsers(getContentResolver());
    				
    				//Synchroner.syncNewData(getContentResolver());
    				
    	    	    
    	        }	
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    	        return true;
    		}
    		if(item.getItemId() == 2)
    		{
    			try {
    				
    				Intent intent = new Intent();
    				intent.setClass(this, PreferencesActivity.class);
    				
    				startActivity(intent);
    	        }	
    			catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    	        return true;
    		}
    	
    	
    	return super.onOptionsItemSelected(item);
    }

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Uri uri = ContentUris.withAppendedId(Forums.CONTENT_URI, id);
		
		Intent frmInt = new Intent(Intent.ACTION_VIEW, uri);
		//frmInt.putExtra("group_id", id);
		
		startActivity(frmInt);
		
		// Get the item that was clicked
		//Object o = this.getListAdapter().getItem(position);
		//String keyword = o.toString();
		
	}


}