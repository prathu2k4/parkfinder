package com.example.parkfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ParkingSpotAdapter extends RecyclerView.Adapter<ParkingSpotAdapter.ViewHolder> {

    private List<ParkingSpot> spotList;

    public ParkingSpotAdapter(List<ParkingSpot> spotList) {
        this.spotList = spotList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parking_spot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParkingSpot spot = spotList.get(position);
        holder.tvName.setText(spot.name);
        holder.tvDistance.setText("📍 " + spot.distance);
        holder.tvTimings.setText("🕒 " + spot.timings);
        holder.tvPrice.setText("₹" + spot.price);
        holder.tvAvailability.setText("🚗 " + spot.availability);
    }

    @Override
    public int getItemCount() {
        return spotList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDistance, tvTimings, tvPrice, tvAvailability;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvSpotName);
            tvDistance = itemView.findViewById(R.id.tvSpotDistance);
            tvTimings = itemView.findViewById(R.id.tvSpotTimings);
            tvPrice = itemView.findViewById(R.id.tvSpotPrice);
            tvAvailability = itemView.findViewById(R.id.tvSpotAvailability);
        }
    }
}