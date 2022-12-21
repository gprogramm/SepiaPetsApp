package com.android.sepiapetsapp.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.android.sepiapetsapp.R;
import com.android.sepiapetsapp.databinding.ItemListPetsBinding;
import com.android.sepiapetsapp.dtos.PetsDto;
import com.android.sepiapetsapp.interfaces.PetsCallback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.MyLeadsItemHolder> {

    private List<PetsDto.PetsList> petsLists;
    private final PetsCallback callback;

    public PetsAdapter(PetsCallback callback) {
        this.callback = callback;
    }

    public void setList(List<PetsDto.PetsList> petsLists) {
        this.petsLists = petsLists;
    }

    @Override
    public MyLeadsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemListPetsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_pets, parent, false);
        return new MyLeadsItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyLeadsItemHolder holder, int position) {
        holder.bindData(petsLists.get(position), position);
    }

    @Override
    public int getItemCount() {
        return petsLists.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(CircleImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext()).load(url).placeholder(R.drawable.ic_loading).into(view);
        }
    }

    public class MyLeadsItemHolder extends RecyclerView.ViewHolder {

        ItemListPetsBinding binding;

        public MyLeadsItemHolder(ItemListPetsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(final PetsDto.PetsList data, final int position) {

            // Bind object with xml
            binding.setData(data);

            // Handle event when click on tile
            binding.getRoot().findViewById(R.id.cv_main).setOnClickListener(view -> {
                callback.onItemClick(data, position);
            });
        }
    }
}

