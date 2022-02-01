package com.ayizor.friendlyeats.activitys;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayizor.friendlyeats.R;
import com.ayizor.friendlyeats.adapters.HomeAdapter;
import com.ayizor.friendlyeats.models.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Restaurant> arrayList;
    private HomeAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private DatabaseReference reference;
    private NestedScrollView scrollView;
    private int count = 0;
    private LinearLayout layout;
    Boolean loading = true;
    private GridLayoutManager layoutManagerV;
    //
    private RecyclerView mRV;
    private int mTotalItemCount = 0;
    private int mLastVisibleItemPosition;
    private boolean mIsLoading = false;
    private int mPostsPerPage = 3;
    private Boolean isScrolling = false;
    private int currentScientists, totalScientists, scrolledOutScientists;
    private Boolean reachedEnd = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();

    }

    private void inits() {
        layoutManagerV = new GridLayoutManager(this, 1);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.home_recyceler);
        arrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference();
        progressBar.setVisibility(View.GONE);
        getProductsDataFromBase();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //check for scroll state
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentScientists = layoutManagerV.getChildCount();
                totalScientists = layoutManagerV.getItemCount();
                scrolledOutScientists = ((GridLayoutManager) recyclerView.getLayoutManager()).
                        findFirstVisibleItemPosition();

                if (isScrolling && (currentScientists + scrolledOutScientists ==
                        totalScientists)) {
                    isScrolling = false;

                    if (dy > 0) {
                        // Scrolling up
                        if (!reachedEnd) {
                            getProductsDataFromBase();
                            progressBar.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(MainActivity.this, "No More Item Found", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        // Scrolling down
                    }
                }


            }
        });
    }

    private void getProductsDataFromBase() {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(layoutManagerV);
        } else {
            layoutManagerV = new GridLayoutManager(this, 3);
            recyclerView.setLayoutManager(layoutManagerV);

        }


        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        arrayList = new ArrayList<>();
        adapter = new HomeAdapter(arrayList, this);
        arrayList.clear();
        recyclerView.setAdapter(adapter);

        reference.child("Restaurants").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);

                    arrayList.add(restaurant);
                }
                if (arrayList.size() > 0) {
                    //         progressBar.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                loading = false;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}
