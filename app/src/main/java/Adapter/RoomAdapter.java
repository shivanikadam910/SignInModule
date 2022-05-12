package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.HomeActivity;
import com.android.login.R;
import com.android.login.SQLiteActivity;
import com.zerobranch.layout.SwipeLayout;

import java.util.ArrayList;
import java.util.List;

import model.CustomerModel;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

import model.DatabaseHelper;
import model.UserModel;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private List<CustomerModel> listUsers;
    private Context context;


    public RoomAdapter(List<CustomerModel> listItems, Context context) {
        this.listUsers = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_customer, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerModel listItem = listUsers.get(position);
        holder.tvName.setText(listItem.getName());
        holder.tvAge.setText(String.valueOf(listItem.getAge()));
        holder.isActive.setText(String.valueOf(listItem.isActive()));
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvAge;
        private TextView isActive;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();
            setListeners();
        }

        private void initViews() {
            tvName = itemView.findViewById(R.id.tvName_lc);
            tvAge = itemView.findViewById(R.id.tvAge_lc);
            isActive = itemView.findViewById(R.id.tvActivity_lc);
        }

        private void setListeners() {
        }


        @Override
        public void onClick(View view) {

        }
    }
}