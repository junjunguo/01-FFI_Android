package ffiandroid.situationawareness;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This Status Class is part of project: Situation Awareness
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 2/20/2015.
 * <p/>
 * responsible for this file: GuoJunjun
 */
public class Status extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_app_settings:
                startActivity(new Intent(this, AppSettings.class));
                return true;
            case R.id.menu_item_report:
                startActivity(new Intent(this, Report.class));
                return true;
            case R.id.menu_item_map_view:
                startActivity(new Intent(this, MapActivity.class));
                return true;
            case R.id.menu_item_report_view:
                startActivity(new Intent(this, ReportView.class));
                return true;
            case R.id.menu_item_logout:
                startActivity(new Intent(this, Login.class));
                return true;
            case R.id.menu_item_location_view:
                startActivity(new Intent(this, LocationView.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
