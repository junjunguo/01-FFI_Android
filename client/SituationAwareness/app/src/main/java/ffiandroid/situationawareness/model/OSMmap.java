package ffiandroid.situationawareness.model;

import android.content.Context;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ffiandroid.situationawareness.model.localdb.DAOlocation;
import ffiandroid.situationawareness.model.localdb.DAOphoto;
import ffiandroid.situationawareness.model.localdb.DAOtextReport;

/**
 * This OSMmap File is part of project: Situation Awareness
 * <p/>
 * Created by GuoJunjun <junjunguo.com> on 3/8/2015.
 * <p/>
 * Responsible for this file: GuoJunjun
 */
public class OSMmap {
    /**
     * @param context
     * @return a markers Overlay Item Array with my coworkers location
     */
    public ArrayList<OverlayItem> getCoworkerMarkersOverlay(Context context) {
        ArrayList<OverlayItem> markersOverlayItemArray = new ArrayList();
        DAOlocation daOlocation = new DAOlocation(context);
        List<LocationReport> locationReports = daOlocation.getLatestCoWorkerLocations(UserInfo.getUserID());
        for (LocationReport lr : locationReports) {
            markersOverlayItemArray.add(new OverlayItem(lr.getUserid(), lr.getUserid(),
                    new GeoPoint(lr.getLatitude(), lr.getLongitude())));
            System.out.println("id: " + lr.getUserid() + ".  Lat:  " + lr.getLatitude() + "  Long:  " + lr.getLongitude());
        }
        System.out.println( "size " + locationReports.size());
        daOlocation.close();
        return markersOverlayItemArray;
    }


    /*
    // Created by Torgrim for testing purpose
    // return all coworkers location reports in the server database.
     */

    public ArrayList<LocationReport> getAllCoworkersLocationReports(Context context)
    {
        DAOlocation daoLocation = new DAOlocation(context);
        ArrayList<LocationReport> locationReports = (ArrayList)daoLocation.getCoWorkerLocations(UserInfo.getUserID());
        daoLocation.close();
        return locationReports;

    }

    // Created by Torgrim
    // gets all coworkersTextReports
    public List<TextReport> getAllCoworkersTextReports(Context context)
    {
        DAOtextReport doaTextReport = new DAOtextReport(context);
        List<TextReport> textReports =  doaTextReport.getAllTextReports();
        doaTextReport.close();
        return textReports;

    }


    public List<PhotoReport> getAllCoworkersPhotoReports(Context context)
    {
        DAOphoto daoPhoto = new DAOphoto(context);
        List<PhotoReport> photoReports = daoPhoto.getCoWorkerPhotos(UserInfo.getUserID());
        daoPhoto.close();
        return photoReports;
    }

}
