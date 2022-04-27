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

import model.UserModel;


public  class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private List<UserModel> listUsers;
    private Context context;

    public CustomAdapter(List<UserModel> listItems, Context context) {
        this.listUsers = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
       return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
       UserModel listItem = listUsers.get(position);
        holder.tvFirstName.setText(listItem.getfName());
        holder.tvLastName.setText(listItem.getlName());
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvFirstName;
        public  TextView tvLastName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
        }
    }
}