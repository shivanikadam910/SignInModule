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


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {

    private final DatabaseHelper helper;
    private List<CustomerModel> listUsers;
    private Context context;


    public CustomerAdapter(List<CustomerModel> listItems, Context context) {
        this.listUsers = listItems;
        this.context = context;
        helper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipe_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerModel listItem = listUsers.get(position);
        listItem.getName();
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
        private SwipeLayout swipeLayout;
        private ImageView ivSwipeDelete, ivSwipeUpdate;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();
            setListeners();
        }

        private void initViews() {
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            isActive = itemView.findViewById(R.id.tvActivity);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            ivSwipeUpdate = itemView.findViewById(R.id.ivSwipeUpdate);
            ivSwipeDelete = itemView.findViewById(R.id.ivSwipeDelete);

        }

        private void setListeners() {
            ivSwipeUpdate.setOnClickListener(this);
            ivSwipeDelete.setOnClickListener(this);
            swipeLayout.setOnActionsListener(new SwipeLayout.SwipeActionsListener() {
                @Override
                public void onOpen(int direction, boolean isContinuous) {
                    if (direction == SwipeLayout.LEFT && isContinuous) {
                        if (getAdapterPosition() != NO_POSITION) {
                            Log.d("CustomerAdapter", "onOpen");
//                            remove(itemView.getContext(), getAdapterPosition());
                        }
                    }
                }

                @Override
                public void onClose() {

                }
            });


        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ivSwipeUpdate:
                    System.out.println("Update (***) ");

//                    System.out.println(getAdapterPosition() + "**");
//                    setUpdateDialog(getAdapterPosition(), view);
                    break;
                case R.id.ivSwipeDelete:
                    System.out.println("Delete ( *** ) ");
//                    setDeleteDialog(getAdapterPosition(), view);
                    System.out.println("Set listener on delete");
                    CustomerModel clickCustomer = listUsers.get(getAdapterPosition());

                    helper.deleteOne(clickCustomer.getId());
//                swipeLayout.close();
                    notifyItemRemoved(getAdapterPosition());
                    break;
            }
        }
    }
}