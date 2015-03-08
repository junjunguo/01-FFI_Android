package ffiandroid.situationawareness;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This AppSettings Class is part of project: Situation Awareness
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 2/20/2015.
 * <p/>
 * responsible for this file: GuoJunjun & Simen
 */
public class AppSettings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_report:
                finish();
                startActivity(new Intent(this, Report.class));
                return true;
            case R.id.menu_item_status:
                finish();
                startActivity(new Intent(this, Status.class));
                return true;
            case R.id.menu_item_map_view:
                finish();
                startActivity(new Intent(this, MapActivity.class));
                return true;
            case R.id.menu_item_report_view:
                finish();
                startActivity(new Intent(this, ReportView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
