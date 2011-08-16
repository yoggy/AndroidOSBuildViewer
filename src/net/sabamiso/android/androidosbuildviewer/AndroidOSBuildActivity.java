package net.sabamiso.android.androidosbuildviewer;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

public class AndroidOSBuildActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView listview = (ListView)findViewById(R.id.listView1);

        ArrayList<Item> list = new ArrayList<Item>();
        
        Field [] fs = Build.class.getDeclaredFields();
        for (int i = 0; i < fs.length; ++i) {
        	Field f = fs[i];
        	String key = f.getName();
        	String value;
			try {
				value = (String)f.get(null);
	            list.add(new Item(key, value));
			} catch (Exception e) {
			}
        }
        
        ItemAdapter adapter = new ItemAdapter(this, list);
        listview.setAdapter(adapter);
    }

    @Override
    public void onPause() {
		super.onPause();
		System.exit(0);
	}
}