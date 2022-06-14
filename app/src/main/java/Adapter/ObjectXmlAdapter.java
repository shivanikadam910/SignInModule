package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.R;

import java.util.List;

import model.JsonModel;
import model.ObjectXmlModel;

public class ObjectXmlAdapter extends RecyclerView.Adapter<ObjectXmlAdapter.ViewHolder> {

    private List<ObjectXmlModel> listXml;
    private Context context;

    public ObjectXmlAdapter(List<ObjectXmlModel> listXml, Context context) {
        this.listXml = listXml;
        this.context = context;
    }

    @NonNull
    @Override
    public ObjectXmlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_xml_object, parent, false);
        return new ObjectXmlAdapter.ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ObjectXmlAdapter.ViewHolder holder, int position) {

        ObjectXmlModel objModel = listXml.get(position);
        System.out.println("position : " + position);
        String type = objModel.getType();
        int height = objModel.getHeight();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.ll_object.getLayoutParams();
        params.height = height;
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        holder.ll_object.setLayoutParams(params);

        LinearLayout.LayoutParams parameters = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        parameters.setMargins(20,20,20,20);

        if (type.equals("TextView")) {
            System.out.println("TextView : " + type);
            holder.textView = new TextView(context);
            holder.textView.setLayoutParams(parameters);
            holder.textView.setGravity(Gravity.CENTER);
            holder.textView.setBackgroundColor(R.color.black);
            holder.textView.setText("Text View");
            holder.ll_object.addView(holder.textView);

        } else if (type.equals("EditText")) {
            System.out.println("EditText : " + type);
            holder.editText = new EditText(context);
            holder.editText.setLayoutParams(parameters);
            holder.editText.setGravity(Gravity.CENTER);
            holder.editText.setText("edit text");
            holder.ll_object.addView(holder.editText);

        } else if (type.equals("Button")) {
            System.out.println("Button : " + type);
            holder.button = new Button(context);
            holder.button.setLayoutParams(parameters);
            holder.button.setGravity(Gravity.CENTER);
            holder.ll_object.addView(holder.button);

        } else if (type.equals("SeekBar")) {
            System.out.println("Seekbar : " + type);
            holder.seekBar = new SeekBar(context);
            holder.seekBar.setLayoutParams(parameters);
            holder.seekBar.setForegroundGravity(Gravity.CENTER);
            holder.ll_object.addView(holder.seekBar);
        }
        else if(type.equals("Switch")){
            System.out.println("Switch : " + type);
            holder.swtch = new Switch(context);
            holder.swtch.setLayoutParams(parameters);
            holder.swtch.setGravity(Gravity.CENTER);
            holder.ll_object.addView(holder.swtch);
        }

    }

    @Override
    public int getItemCount() {
        return listXml.size();
    }

    public void addObject(List<ObjectXmlModel> xmlList) {
        this.listXml = xmlList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View v) {
            super(v);
            initViews();
//            getType();
        }

        private TextView textView;
        private Button button;
        private EditText editText;
        private SeekBar seekBar;
        private Switch swtch;
        private LinearLayout ll_object;

        private void initViews() {
            ll_object = itemView.findViewById(R.id.ll_object);
        }

        private void getType() {
            System.out.println("adapterposition : " + getAdapterPosition());
            ObjectXmlModel objModel = listXml.get(getAdapterPosition());
            String type = objModel.getType();
            int height = objModel.getHeight();


            if (type.equals("TextView")) {
                textView = new TextView(context);
            } else if (type.equals("EditText")) {
                editText = new EditText(context);
            } else if (type.equals("Button")) {
                button = new Button(context);
            } else if (type.equals("SeekBar")) {
                seekBar = new SeekBar(context);
            }

        }

    }

}
