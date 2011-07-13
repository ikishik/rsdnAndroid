package net.ikishik.RsdnAndroid;

import java.util.Vector;


import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
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
        
       /* JanusAT service = new JanusAT();
        ForumRequest creq = new ForumRequest();
        creq.setuserName("Demandred");
        creq.setpassword("kishik");
        
        
        try {
			ForumResponse resp = service.GetForumList(creq);
			
			Vector<JanusForumGroupInfo> forumGroups = resp.getgroupList();
			Vector<JanusForumInfo> forums = resp.getforumList();
			
			for(JanusForumGroupInfo fg : forumGroups)
			{
				 ContentValues values = new ContentValues();
		         
				 values.put(ForumGroups.SERVERID, fg.getforumGroupId());
				 values.put(ForumGroups.FORUMGROUPNAME, fg.getforumGroupName());
				 values.put(ForumGroups.SORTORDER, fg.getsortOrder());
				
		         
		         getContentResolver().insert(ForumGroups.CONTENT_URI, values);
			}
			
			for(JanusForumInfo fi : forums)
			{
				 ContentValues values = new ContentValues();
		         
				 values.put(Forums.SERVERID, fi.getforumId());
				 values.put(Forums.FORUMGROUPID, fi.getforumGroupId());
				 values.put(Forums.FORUMNAME, fi.getforumName());
				 values.put(Forums.INTOP, fi.getinTop());
				 values.put(Forums.RATED, fi.getrated());
				 values.put(Forums.RATELIMIT, fi.getrateLimit());
				 values.put(Forums.SHORTFORUMNAME, fi.getshortForumName());
		         
		         getContentResolver().insert(Forums.CONTENT_URI, values);
			}
			
		}	
		catch (Exception e) {
			e.printStackTrace();
		}
		*/
        
        Cursor cursor = managedQuery(ForumGroups.CONTENT_URI, PROJECTION, null, null,
        		ForumGroups.DEFAULT_SORT_ORDER);
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.activity_list_item , cursor,
                new String[] { ForumGroups.FORUMGROUPNAME }, new int[] { android.R.id.text1 });
        setListAdapter(adapter);
        
       
        
        
    }
}