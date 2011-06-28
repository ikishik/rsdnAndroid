package net.ikishik.RsdnAndroid;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class RsdnAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RsdnDbHelper dbOpenHelper = new RsdnDbHelper(RsdnAndroidActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
      
        db.close();

    }
}