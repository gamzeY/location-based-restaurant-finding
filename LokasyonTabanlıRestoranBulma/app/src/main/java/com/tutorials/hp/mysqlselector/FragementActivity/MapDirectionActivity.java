package com.tutorials.hp.mysqlselector.FragementActivity;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.tutorials.hp.mysqlselector.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MapDirectionActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {

    private GoogleMap mMap;
    private GoogleMap mMap1;
    private GoogleMap mMap2;
    private GoogleMap mMap3;
    private GoogleMap mMap4;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directionmaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map12);
        mapFragment.getMapAsync(this);

        btnFindPath = (Button) findViewById(R.id.btnFindPath);
        etOrigin = (EditText) findViewById(R.id.etOrigin);
        etDestination = (EditText) findViewById(R.id.etDestination);
        btnFindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }
    private void sendRequest() {
        String origin = etOrigin.getText().toString();
        String destination = etDestination.getText().toString();
        if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap1 = googleMap;
        mMap2 = googleMap;
        mMap3 = googleMap;
        mMap4 = googleMap;
        LatLng hcmus = new LatLng(39.763767, 33.913061);
        LatLng hcmus1 = new LatLng(41.225546, 32.664944);
        LatLng hcmus2 = new LatLng(41.220345, 32.660734);
        LatLng hcmus3 = new LatLng(41.218497, 32.659769);
        LatLng hcmus4 = new LatLng(41.216527, 32.659823);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus1, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus2, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus3, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus4, 15));
        originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Türkiye")
                .position(hcmus)));
        destinationMarkers.add(mMap1.addMarker(new MarkerOptions()
                .title("Passaport Pizza")
                .position(hcmus1)));
        destinationMarkers.add(mMap1.addMarker(new MarkerOptions()
                .title("Dominos Pizza")
                .position(hcmus2)));
        destinationMarkers.add(mMap1.addMarker(new MarkerOptions()
                .title("PizzaBulls")
                .position(hcmus3)));
        destinationMarkers.add(mMap1.addMarker(new MarkerOptions()
                .title("100.yil Doner")
                .position(hcmus4)));


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Lütfen Bekleyin....",
                "Mesafe Ölçülüyor..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }
      @Override
      public void onDirectionFinderSuccess(List<Route> routes) {
          progressDialog.dismiss();
          polylinePaths = new ArrayList<>();
          originMarkers = new ArrayList<>();
          destinationMarkers = new ArrayList<>();

          for (Route route : routes) {
              mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 15));
              ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
              ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

              originMarkers.add(mMap.addMarker(new MarkerOptions()
                      .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                      .title(route.startAddress)
                      .position(route.startLocation)));
              destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                      .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                      .title(route.endAddress)
                      .position(route.endLocation)));

              PolylineOptions polylineOptions = new PolylineOptions().
                      geodesic(true).
                      color(Color.BLUE).
                      width(10);

              for (int i = 0; i < route.points.size(); i++)
                  polylineOptions.add(route.points.get(i));

              polylinePaths.add(mMap.addPolyline(polylineOptions));
          }
      }
}
