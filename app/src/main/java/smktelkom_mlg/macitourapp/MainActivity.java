package smktelkom_mlg.macitourapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import smktelkom_mlg.macitourapp.model.Wisata;

public class MainActivity extends AppCompatActivity {
    public static final String WISATA = "wisata";
    DatabaseHelper dbhelper = new DatabaseHelper(this);
    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    private String nama, deskripsi, foto, username, nama_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = FirebaseDatabase.getInstance().getReference("wisata");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mDatabase.keepSynced(true);
        username = getIntent().getStringExtra("username");
        nama_user = dbhelper.NamaUser(username);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Wisata, WisataViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Wisata, WisataViewHolder>(
                Wisata.class,
                R.layout.item_list,
                WisataViewHolder.class,
                mDatabase
        ) {

            @Override
            protected void populateViewHolder(WisataViewHolder wisataViewHolder, final Wisata wisata, int position) {
                final String wisataID = getRef(position).getKey();

                nama = wisata.getNama();
                deskripsi = wisata.getDeskripsi();
                foto = wisata.getFoto();


                wisataViewHolder.setNama(nama);
                wisataViewHolder.setDeskripsi(deskripsi);
                wisataViewHolder.setFoto(getApplicationContext(), foto);

                wisataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, DetailActivity.class);
                        //Toast.makeText(MainActivity.this,wisataID,Toast.LENGTH_SHORT).show();
                        i.putExtra("id", wisataID);
                        i.putExtra("nama", nama_user);
                        startActivity(i);
                    }
                });
            }
        };
        firebaseRecyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mQuery = newText.toLowerCase();
                doFilter(mQuery);
                return true;
            }
        });
        return true;*/
        return super.onCreateOptionsMenu(menu);
    }


    public static class WisataViewHolder extends RecyclerView.ViewHolder {
        View mView;
        DatabaseReference databaseReference;

        public WisataViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setNama(String nama) {
            TextView txtNama = (TextView) mView.findViewById(R.id.textViewJudul);
            txtNama.setText(nama);
        }

        public void setDeskripsi(String deskripsi) {
            TextView txtDesk = (TextView) mView.findViewById(R.id.textViewDeskripsi);
            txtDesk.setText(deskripsi);
        }

        public void setFoto(Context ctx, String foto) {
            ImageView imgView = (ImageView) mView.findViewById(R.id.imageView);
            Picasso.with(ctx).load(foto).into(imgView);

        }
    }

   /* private void doFilter(String query) {
        if (!isFiltered) {
            mListAll.clear();
            mListAll.addAll(mList);
            isFiltered = true;
        }
        mList.clear();
        if (query == null || query.isEmpty()) {
            mList.addAll(mListAll);
            isFiltered = false;
        } else {
            mListMapFilter.clear();
            for (int i = 0; i < mListAll.size(); i++) {
                Wisata wisata = mListAll.get(i);
                if (wisata.judul.toLowerCase().contains(query) ||
                        wisata.deskripsi.toLowerCase().contains(query) ||
                        wisata.lokasi.toLowerCase().contains(query)) {
                    mList.add(wisata);
                    mListMapFilter.add(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }*/
}
