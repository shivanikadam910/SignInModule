package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.HomeActivity;
import com.android.login.R;

import java.util.ArrayList;
import java.util.List;

import model.UserModel;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvFirstName;
        private TextView tvLastName;
        private ImageButton ibUpdate;
        private ImageButton ibDelete;
        private ViewGroup viewGroup;
        private View dialogView;
        private Button btnCancel, btnUpdate, btnDelete;
        private EditText etUpdateFName, etUpdateLName;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();
            setListeners();
        }
        private void initViews() {
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            ibUpdate = itemView.findViewById(R.id.ibUpdate);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            viewGroup = itemView.findViewById(android.R.id.content);
        }

        private void setListeners() {
            ibUpdate.setOnClickListener(this);
            ibDelete.setOnClickListener(this);
        }

        private void setUpdateDialog(int adapterIndex, View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_update, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            btnCancel = alertDialog.findViewById(R.id.btnCancel);
            btnUpdate = alertDialog.findViewById(R.id.btnUpdate);
            etUpdateFName = alertDialog.findViewById(R.id.etUpdateFName);
            etUpdateLName = alertDialog.findViewById(R.id.etUpdateLName);

            etUpdateFName.setText(tvFirstName.getText().toString());
            etUpdateLName.setText(tvLastName.getText().toString());
            btnCancel.setOnClickListener(v -> {
                alertDialog.dismiss();
            });
            btnUpdate.setOnClickListener(v -> {
                UserModel model = listUsers.get(adapterIndex);
                model.setfName(etUpdateFName.getText().toString());
                model.setlName(etUpdateLName.getText().toString());
                System.out.print(adapterIndex);
                listUsers.set(adapterIndex, model);
                notifyItemChanged(adapterIndex);
                alertDialog.dismiss();
            });
        }

        private void setDeleteDialog(int adapterIndex, View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_delete, viewGroup, false);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            btnCancel = alertDialog.findViewById(R.id.btnCancel);
            btnDelete = alertDialog.findViewById(R.id.btnDelete);
            alertDialog.show();
            btnCancel = alertDialog.findViewById(R.id.btnCancel);
            btnDelete = alertDialog.findViewById(R.id.btnDelete);
            btnCancel.setOnClickListener(v -> {
                alertDialog.dismiss();
            });
            btnDelete.setOnClickListener(v -> {
                listUsers.remove(adapterIndex);
                notifyItemRemoved(adapterIndex);
                alertDialog.dismiss();
            });
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ibUpdate:
                    System.out.println(getAdapterPosition() + "**");
                    setUpdateDialog(getAdapterPosition(), view);
                    break;
                case R.id.ibDelete:
                    System.out.println(getAdapterPosition() + "**");
                    setDeleteDialog(getAdapterPosition(), view);
                    break;
            }
        }
    }
}