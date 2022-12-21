package com.android.sepiapetsapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.sepiapetsapp.R;
import com.android.sepiapetsapp.adapters.PetsAdapter;
import com.android.sepiapetsapp.dtos.PetsDto;
import com.android.sepiapetsapp.interfaces.PetsCallback;
import com.android.sepiapetsapp.utils.ReadJsonFiles;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PetsCallback {

    private PetsAdapter adapter;
    private RecyclerView recyclerView;
    private TextView txtNoRecFound;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        fetchData();
    }

    /**
     * Initialize the components used in xml
     */
    private void initComponents() {
        setUpRecyclerView();

        // Initialize other components
        txtNoRecFound = (TextView) findViewById(R.id.txt_no_rec_found);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    /**
     * Initialize recycle view
     */
    private void setUpRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);

        // Init/SetUp layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Bind adapter with recycle view
        adapter = new PetsAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Refresh the data in adapter list
     *
     * @param petsLists
     */
    private void refreshAdapter(ArrayList<PetsDto.PetsList> petsLists) {
        if (adapter != null) {
            adapter.setList(petsLists);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Fetch data from json file and update the adapter
     */
    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<PetsDto.PetsList> petsLists = ReadJsonFiles.getInstance().fetchPetsList(this);
        refreshAdapter(petsLists);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(PetsDto.PetsList pet, int position) {
        Intent intent = new Intent(this, PetsDetailsActivity.class);
        intent.putExtra("content_url", pet.getContent_url());
        startActivity(intent);
    }
}