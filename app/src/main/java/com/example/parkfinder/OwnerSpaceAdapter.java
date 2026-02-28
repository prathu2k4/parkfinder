package com.example.parkfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OwnerSpaceAdapter extends RecyclerView.Adapter<OwnerSpaceAdapter.ViewHolder> {

    private List<OwnerSpace> spaceList;

    public OwnerSpaceAdapter(List<OwnerSpace> spaceList) {
        this.spaceList = spaceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_owner_space, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OwnerSpace space = spaceList.get(position);
        holder.tvName.setText(space.name);
        holder.tvAddress.setText(space.address);
        holder.tvActive.setText(space.active);
        holder.tvSlots.setText(space.slots);
        holder.tvRevenue.setText("₹" + space.revenue);
    }

    @Override
    public int getItemCount() {
        return spaceList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvActive, tvSlots, tvRevenue;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvOwnerSpaceName);
            tvAddress = itemView.findViewById(R.id.tvOwnerSpaceAddress);
            tvActive = itemView.findViewById(R.id.tvOwnerSpaceActive);
            tvSlots = itemView.findViewById(R.id.tvOwnerSpaceSlots);
            tvRevenue = itemView.findViewById(R.id.tvOwnerSpaceRevenue);
        }
    }
}