package smktelkom_mlg.macitourapp.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import smktelkom_mlg.macitourapp.DetailActivity;
import smktelkom_mlg.macitourapp.R;


public class DetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public DetailActivity detail;
    private String deskripsi, fasilitas, info_tiket, info_trans, kearifan, alamat;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_detail, container, false);

        detail = (DetailActivity) getActivity();
        deskripsi = detail.deskripsi;
        fasilitas = detail.fasilitas;
        info_tiket = detail.info_tiket;
        info_trans = detail.info_trans;
        kearifan = detail.lokal;
        alamat = detail.alamat;
        TextView tvDeskripsi = (TextView) view.findViewById(R.id.txtDesc);
        tvDeskripsi.setText(deskripsi);
        TextView tvFasilitas = (TextView) view.findViewById(R.id.txtFasilitas);
        tvFasilitas.setText(fasilitas);
        TextView tvTiket = (TextView) view.findViewById(R.id.txtTicket);
        tvTiket.setText(info_tiket);
        TextView tvTrans = (TextView) view.findViewById(R.id.txtTrans);
        tvTrans.setText(info_trans);
        TextView tvLokal = (TextView) view.findViewById(R.id.txtLokal);
        tvLokal.setText(kearifan);
        TextView tvAlamat = (TextView) view.findViewById(R.id.txtLokasi);
        tvAlamat.setText(alamat);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
