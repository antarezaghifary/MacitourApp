package smktelkom_mlg.macitourapp.layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import smktelkom_mlg.macitourapp.DetailActivity;
import smktelkom_mlg.macitourapp.R;


public class VisitFragment extends Fragment implements OnMapReadyCallback {
    String name, koordinat, alamat;
    DetailActivity detailActivity;
    private GoogleMap mGoogleMap;
    private MapView mapView;
    private View mView;
    private Button mDirect;
    private Double latitude, longtitude;
    private DetailFragment.OnFragmentInteractionListener mListener;
    public VisitFragment() {
        // Required empty public constructor
    }


    public static VisitFragment newInstance(String param1, String param2) {
        VisitFragment fragment = new VisitFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        detailActivity = (DetailActivity) getActivity();
        koordinat = detailActivity.lokasi;
        name = detailActivity.nama;
        alamat = detailActivity.alamat;
        String[] pisah = koordinat.split(",");
        latitude = Double.parseDouble(pisah[0]);
        longtitude = Double.parseDouble(pisah[1]);
        mView = inflater.inflate(R.layout.fragment_visit, container, false);
        mDirect = (Button) mView.findViewById(R.id.btnDirection);
        mDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + latitude + "," + longtitude + " (" + name + ")"));
                startActivity(intent);
            }
        });
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) mView.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longtitude)).title(name).snippet(alamat));
        CameraPosition cam = CameraPosition.builder().target(new LatLng(latitude, longtitude)).zoom(16).bearing(0).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cam));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailFragment.OnFragmentInteractionListener) {
            mListener = (DetailFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
