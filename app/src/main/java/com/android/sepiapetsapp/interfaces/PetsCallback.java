package com.android.sepiapetsapp.interfaces;

import com.android.sepiapetsapp.dtos.PetsDto;

public interface PetsCallback {

    void onItemClick(PetsDto.PetsList pet, int position);

}
