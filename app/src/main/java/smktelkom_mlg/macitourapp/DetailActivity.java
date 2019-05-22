package smktelkom_mlg.macitourapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import smktelkom_mlg.macitourapp.layout.DetailFragment;
import smktelkom_mlg.macitourapp.layout.ReviewFragment;
import smktelkom_mlg.macitourapp.layout.VisitFragment;


public class DetailActivity extends AppCompatActivity implements DetailFragment.OnFragmentInteractionListener,
        ReviewFragment.OnFragmentInteractionListener, VisitFragment.OnFragmentInteractionListener, DialogClass.DialogClassListener {
    public String id = "";
    public String nama, deskripsi, lokasi, fasilitas, info_tiket, info_trans, lokal, alamat, nama_user;
    public Map<String, String> map;
    public TextView tvTitle, tvLokasi;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private DatabaseReference mDataDetail;
    private DatabaseReference mData;
    private DatabaseReference mDataComment;
    private DatabaseReference mDetailComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        id = getIntent().getStringExtra("id");
        nama_user = getIntent().getStringExtra("nama");
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mData = FirebaseDatabase.getInstance().getReference("wisata");
        mDataDetail = mData.child(id);
        mDataComment = mDataDetail.child("comment");
        mDetailComment = mDataComment.push();
        mDataDetail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                map = (Map<String, String>) dataSnapshot.getValue();
                nama = map.get("nama");
                alamat = map.get("alamat");
                String foto = map.get("foto");
                deskripsi = map.get("deskripsi");
                fasilitas = map.get("fasilitas");
                info_tiket = map.get("info_tiket");
                info_trans = map.get("info_trans");
                lokal = map.get("kearifan");
                lokasi = map.get("koordinat");
                ImageView ivFoto = (ImageView) findViewById(R.id.imageFoto);
                Picasso.with(getApplication()).load(foto).into(ivFoto);
                tvTitle = (TextView) findViewById(R.id.place_title);
                tvTitle.setText(nama);
                tvLokasi = (TextView) findViewById(R.id.place_location);
                tvLokasi.setText(alamat);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void sendReview(String comment, String rate) {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        mDetailComment.child("username").setValue(nama_user);
        mDetailComment.child("review").setValue(comment);
        mDetailComment.child("rate").setValue(rate);
        mDetailComment.child("date").setValue(date);
        Toast.makeText(DetailActivity.this, "Success add Review", Toast.LENGTH_SHORT).show();
    }

   /* @Override
    public void sendReview(String comment, String rate) {
        mComment = comment;
        mRate = rate;
    }*/

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0) {
                return new DetailFragment();
            } else if (position == 1) {
                return new ReviewFragment();
            } else if (position == 2) {
                return new VisitFragment();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:

                    return "Details";
                case 1:
                    return "Reviews";
                case 2:
                    return "Visit";
            }
            return null;
        }
    }
}
