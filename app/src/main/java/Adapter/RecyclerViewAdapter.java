package Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.R;

import java.util.ArrayList;

import model.TourModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<TourModel> courseDataArrayList;
    private Context mcontext;

    public RecyclerViewAdapter(ArrayList<TourModel> recyclerDataArrayList, Context mcontext) {
        this.courseDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        TourModel recyclerData = courseDataArrayList.get(position);
        holder.tvPlaceName.setText(recyclerData.getTitle());
        holder.ivPlaceImage.setImageResource(recyclerData.getImgid());
    }

    @Override
    public int getItemCount() {
        return courseDataArrayList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPlaceName;
        private ImageView ivPlaceImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPlaceName = itemView.findViewById(R.id.tvTourName);
            ivPlaceImage = itemView.findViewById(R.id.ivTourPlace);
        }
    }
}
