package Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.login.R;
import com.android.login.ShowSeriesActivity;

import java.math.BigInteger;
import java.util.List;

import database.User;
import model.SeriesNumberModel;
import model.UserModel;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    private List<SeriesNumberModel> listNumbers;
    private List<SeriesNumberModel> listSeekValues;
    private Context context;

    public SeriesAdapter(List<SeriesNumberModel> listNumbers, List<SeriesNumberModel> listSeekValues, Context context) {
        this.listNumbers = listNumbers;
        this.listSeekValues = listSeekValues;
        this.context = context;
    }

    @NonNull
    @Override
    public SeriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_number, parent, false);
        return new SeriesAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull SeriesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SeriesNumberModel seriesNumber = listNumbers.get(position);
        SeriesNumberModel seriesSeekValue = listSeekValues.get(position);

        holder.tvSeriesNumber.setText(String.valueOf(seriesNumber.getNumber()));
        holder.tvSerialNumber.setText("#: " + String.valueOf(position + 1));
        holder.seekBar.setOnSeekBarChangeListener(null);
        holder.seekBar.setProgress(seriesSeekValue.getSeekbarValue());

        if (position > 0) {
            SeriesNumberModel seriesPrevNumber = listNumbers.get(position - 1);
            SeriesNumberModel seriesPrevSeekValue = listSeekValues.get(position - 1);
            holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int pval = 0;
                int newNum;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    pval = progress;
                    newNum = ShowSeriesActivity.MAX;
                    int setProg = pval * newNum / 100;

                    System.out.println("onStopTracking : " + setProg);
                    System.out.println("newNum" + newNum);

                    seriesNumber.setNumber(new BigInteger(String.valueOf(setProg)));
                    seriesSeekValue.setSeekbarValue(pval);
                    seriesPrevNumber.setNumber(new BigInteger(String.valueOf(setProg)));
                    seriesPrevSeekValue.setSeekbarValue(pval);

                    notifyItemChanged(position);
                    notifyItemChanged(position - 1);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //write custom code to on start progress
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    notifyItemChanged(position);
                    notifyItemChanged(position - 1);
                }
            });

            holder.seekBar.setProgress(seriesSeekValue.getSeekbarValue());
            holder.tvSeriesNumber.setText(String.valueOf(seriesNumber.getNumber()));
        } else if (position == 0) {
            holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int pval = 0;
                int newNum;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    pval = progress;
                    newNum = ShowSeriesActivity.MAX;
                    int setProg = pval * newNum / 100;

                    System.out.println("onStopTracking : " + setProg);
                    System.out.println("newNum" + newNum);

                    seriesNumber.setNumber(new BigInteger(String.valueOf(setProg)));
                    seriesSeekValue.setSeekbarValue(pval);
                    notifyItemChanged(position);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    //write custom code to on start progress
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            holder.seekBar.setProgress(seriesSeekValue.getSeekbarValue());
            holder.tvSeriesNumber.setText(String.valueOf(seriesNumber.getNumber()));
        }
    }

    @Override
    public int getItemCount() {
        return listNumbers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvSeriesNumber, tvSerialNumber;
        private SeekBar seekBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            tvSeriesNumber = itemView.findViewById(R.id.tvSeriesNumber);
            tvSerialNumber = itemView.findViewById(R.id.tvserialNumber);
            seekBar = itemView.findViewById(R.id.seekBar);
        }
    }
}
