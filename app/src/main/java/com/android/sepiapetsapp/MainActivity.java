package com.android.sepiapetsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.sepiapetsapp.dtos.PetsDto;
import com.android.sepiapetsapp.utils.ReadJsonFiles;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<PetsDto.PetsList> petsLists = ReadJsonFiles.getInstance().fetchPetsList(this);
        System.out.println("---petsLists--->"+petsLists.size());
    }
}