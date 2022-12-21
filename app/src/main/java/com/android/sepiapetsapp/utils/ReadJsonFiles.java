package com.android.sepiapetsapp.utils;

import android.content.Context;

import com.android.sepiapetsapp.dtos.ConfigDto;
import com.android.sepiapetsapp.dtos.PetsDto;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Make a singleton class to read the json files placed in an assets.
 */
public class ReadJsonFiles {

    private static ReadJsonFiles readAssetsJson;

    public static ReadJsonFiles getInstance() {
        if (readAssetsJson == null) {
            readAssetsJson = new ReadJsonFiles();
        }

        return readAssetsJson;
    }

    /**
     * Read the pets list from pets_list json file
     *
     * @param context
     * @return
     */
    public ArrayList<PetsDto.PetsList> fetchPetsList(Context context) {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("pets_list.json"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        PetsDto petsDto = new Gson().fromJson(reader, PetsDto.class);

        return petsDto.getPets();
    }

    /**
     * Read the settings from config json file
     *
     * @param context
     * @return
     */
    public ConfigDto fetchSettings(Context context) {

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open("config.json"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConfigDto configDto = new Gson().fromJson(reader, ConfigDto.class);

        return configDto;
    }
}
