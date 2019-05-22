package smktelkom_mlg.macitourapp.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import smktelkom_mlg.macitourapp.DetailActivity;
import smktelkom_mlg.macitourapp.DialogClass;
import smktelkom_mlg.macitourapp.R;
import smktelkom_mlg.macitourapp.model.Comment;

public class ReviewFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public String wId;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private DetailActivity detailActivity;
    private DatabaseReference mRoot;
    private DatabaseReference mRootDetail;
    private DatabaseReference mRootComment;
    private RecyclerView recyclerComment;
    private String username, review, rate, date;

    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
        wId = getActivity().getIntent().getStringExtra("id");
        mRoot = FirebaseDatabase.getInstance().getReference("wisata");
        mRootDetail = mRoot.child(wId);
        mRootComment = mRootDetail.child("comment");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        detailActivity = (DetailActivity) getActivity();
        recyclerComment = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerComment.setHasFixedSize(true);
        recyclerComment.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        return view;
    }

    public void openDialog() {
        DialogClass dialog = new DialogClass();
        dialog.show(getFragmentManager(), "Review Dialog");
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Comment, ReviewFragment.CommentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Comment, ReviewFragment.CommentViewHolder>(
                Comment.class,
                R.layout.layout_comment,
                ReviewFragment.CommentViewHolder.class,
                mRootComment
        ) {

            @Override
            protected void populateViewHolder(ReviewFragment.CommentViewHolder commentViewHolder, final Comment comment, int position) {

                username = comment.getUsername();
                review = comment.getReview();
                rate = comment.getRate();
                date = comment.getDate();

                commentViewHolder.setUsername(username);
                commentViewHolder.setReview(review);
                commentViewHolder.setRate(rate);
                commentViewHolder.setDate(date);

            }
        };
        firebaseRecyclerAdapter.notifyDataSetChanged();
        recyclerComment.setAdapter(firebaseRecyclerAdapter);
    }

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
        void onFragmentInteraction(Uri uri);
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        View mView;
        DatabaseReference databaseReference;

        public CommentViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setUsername(String username) {
            TextView txtUser = (TextView) mView.findViewById(R.id.textViewUser);
            txtUser.setText(username);
        }

        public void setReview(String review) {
            TextView txtRev = (TextView) mView.findViewById(R.id.textViewReview);
            txtRev.setText(review);
        }

        public void setDate(String date) {
            TextView txtDate = (TextView) mView.findViewById(R.id.textViewDate);
            txtDate.setText(date);
        }

        public void setRate(String rate) {
            ImageView star1 = (ImageView) mView.findViewById(R.id.star1);
            ImageView star2 = (ImageView) mView.findViewById(R.id.star2);
            ImageView star3 = (ImageView) mView.findViewById(R.id.star3);
            ImageView star4 = (ImageView) mView.findViewById(R.id.star4);
            ImageView star5 = (ImageView) mView.findViewById(R.id.star5);
            int countRate = Integer.parseInt(rate);
            switch (countRate) {
                case 1:
                    star1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    star1.setVisibility(View.VISIBLE);
                    star2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    star1.setVisibility(View.VISIBLE);
                    star2.setVisibility(View.VISIBLE);
                    star3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    star1.setVisibility(View.VISIBLE);
                    star2.setVisibility(View.VISIBLE);
                    star3.setVisibility(View.VISIBLE);
                    star4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    star1.setVisibility(View.VISIBLE);
                    star2.setVisibility(View.VISIBLE);
                    star3.setVisibility(View.VISIBLE);
                    star4.setVisibility(View.VISIBLE);
                    star5.setVisibility(View.VISIBLE);
                    break;
                default:
                    System.out.print("error");
            }


        }

    }
}
