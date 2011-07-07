package net.ikishik.RsdnAndroid;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

//import net.ikishik.rsdnClient.*;

public class RsdnAndroidActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RsdnDbHelper dbOpenHelper = new RsdnDbHelper(RsdnAndroidActivity.this);
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
      
        db.close();
        
        //JanusAT service = new JanusAT();
        //ChangeRequest creq = new ChangeRequest();
        //creq.setuserName("Demandred");
        //creq.setpassword("");
        
        //try {
		//	service.GetNewData(creq);
		//} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
        
        
    }
}