package net.ikishik.RsdnAndroid;

import java.util.Vector;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import net.ikishik.rsdnClient.*;

public class RsdnAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RsdnDbHelper dbOpenHelper = new RsdnDbHelper(RsdnAndroidActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
      
        db.close();
        
        JanusAT service = new JanusAT();
        ForumRequest creq = new ForumRequest();
        creq.setuserName("Demandred");
        creq.setpassword("kishik");
        
        
        try {
			ForumResponse resp = service.GetForumList(creq);
			Vector<JanusForumInfo> msgs = resp.getforumList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
}