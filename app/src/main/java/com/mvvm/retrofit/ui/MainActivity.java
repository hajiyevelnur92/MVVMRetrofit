package com.mvvm.retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import com.mvvm.retrofit.R;
import com.mvvm.retrofit.adapters.CockTailAdapter;

import com.mvvm.retrofit.model.Drink;
import com.mvvm.retrofit.viewmodels.CockTailViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Drink> articleArrayList = new ArrayList<>();
    private CockTailAdapter cockTailAdapter;
    private RecyclerView recyclerView;
    private CockTailViewModel cockTailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvNews);

        cockTailViewModel = ViewModelProviders.of(this).get(CockTailViewModel.class);
        cockTailViewModel.init();
        cockTailViewModel.getCockTailRepository().observe(this, newsResponse -> {
            List<Drink> newsArticles = newsResponse.getDrinks();
            articleArrayList.addAll(newsArticles);
            cockTailAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (cockTailAdapter == null) {
            cockTailAdapter = new CockTailAdapter(MainActivity.this, articleArrayList) {
                @Override
                public void onItemClick(Drink drink) {
                    Log.d("Item clicked !", "id " + drink.getIdDrink());
                }
            };
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));

            recyclerView.setAdapter(cockTailAdapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        } else {
            cockTailAdapter.notifyDataSetChanged();
        }
    }
}
