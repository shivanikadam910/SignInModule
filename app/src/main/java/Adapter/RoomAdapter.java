package Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.android.login.R;

import java.util.List;

import database.AppDatabase;
import database.User;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public RoomAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();

    }

    public void setUser(User user) {
        this.userList.add(user);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RoomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_customer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.MyViewHolder holder, int position) {

        final User user = userList.get(position);
        holder.tvName.setText(user.user_name);
        holder.tvAge.setText(String.valueOf(user.age));
        holder.isActive.setText(String.valueOf(user.isActive));
        // prevent unwanted situations
        holder.checkBox2.setOnCheckedChangeListener(null);
        holder.checkBox2.setSelected(user.isSelected);

        holder.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.isSelected = isChecked;

                AppDatabase db = AppDatabase.getDbInstance(context);
                db.userDao().getAllUsers();
                System.out.println("update selected user "+ user.toString());
                db.userDao().updateSelectedUser(isChecked, user.user_id);

            }
        });
        holder.checkBox2.setChecked(user.isSelected);

    }


    @Override
    public int getItemCount() {
        return this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvAge;
        private TextView isActive;
        private CheckBox checkBox2;

        public MyViewHolder(View view) {
            super(view);
            tvName = itemView.findViewById(R.id.tvName_lc);
            tvAge = itemView.findViewById(R.id.tvAge_lc);
            isActive = itemView.findViewById(R.id.tvActivity_lc);
            checkBox2 = itemView.findViewById(R.id.checkBox2);

        }
    }
}
