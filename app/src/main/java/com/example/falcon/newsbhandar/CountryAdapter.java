package com.example.falcon.newsbhandar;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.countryHolder> {
    private int lastSelectedPosition = -1;
    String url1 = "https://www.countryflags.io/";

    countryNameAndCodes details;

    public CountryAdapter() {
        this.details = new countryNameAndCodes();
        details.enter();

    }


    @NonNull
    @Override
    public countryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.country_list_template, viewGroup, false);
        return new countryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull countryHolder viewHolder, int i) {

        String countryName = details.cNames[i];
        String img = details.data.get(countryName);
        String url = url1 + img + "/shiny/32.png";
        Log.v("plsssss", url);
        viewHolder.coName.setText(countryName);
        viewHolder.coName.setChecked(lastSelectedPosition == i);
        if (lastSelectedPosition == i) {
            MainActivity.editor.clear();
            MainActivity.editor.commit();
            MainActivity.editor.putString("country", details.cNames[i]);
            MainActivity.editor.commit();
        }
        Glide.with(viewHolder.coImg.getContext()).load(url).into(viewHolder.coImg);

    }

    @Override
    public int getItemCount() {
        return details.data.size();
    }

    public class countryHolder extends RecyclerView.ViewHolder {
        public RadioButton coName;
        public ImageView coImg;

        public countryHolder(@NonNull View itemView) {
            super(itemView);
            coName = (RadioButton) itemView.findViewById(R.id.coName);
            coImg = (ImageView) itemView.findViewById(R.id.coImg);
            coName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();


                }
            });
        }
    }
}
