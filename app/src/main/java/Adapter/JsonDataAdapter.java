package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.R;

import java.util.List;

import model.JsonModel;

public class JsonDataAdapter extends RecyclerView.Adapter<JsonDataAdapter.ViewHoler> {
    private List<JsonModel> jsonList;
    private Context context;

    public JsonDataAdapter(List<JsonModel> jsonList, Context context){
        this.context = context;
        this.jsonList = jsonList;
    }


    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_json,parent,false);
        return new ViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        JsonModel user = jsonList.get(position);
        holder.tvJsonId.setText(String.valueOf(user.getId()));
        holder.tvJsonName.setText(user.getName());
        holder.tvJsonEmail.setText(user.getEmail());
        holder.tvJsonGender.setText(user.getGender());
        holder.tvJsonStatus.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        return jsonList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private TextView tvJsonId, tvJsonName, tvJsonEmail, tvJsonGender, tvJsonStatus;
        public ViewHoler(View v){
            super(v);
            initViews();
        }

        private void initViews(){
            tvJsonId = itemView.findViewById(R.id.tvIdJson);
            tvJsonName = itemView.findViewById(R.id.tvNameJson);
            tvJsonEmail = itemView.findViewById(R.id.tvEmailJson);
            tvJsonGender = itemView.findViewById(R.id.tvGenderJson);
            tvJsonStatus = itemView.findViewById(R.id.tvStatusJson);
        }

    }

    public void addData(List<JsonModel> jsonList){
        this.jsonList = jsonList;
        notifyDataSetChanged();
    }
}
