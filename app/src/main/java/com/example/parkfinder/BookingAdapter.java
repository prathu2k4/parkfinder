package com.example.parkfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    private List<Booking> bookingList;

    public BookingAdapter(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    // This method allows us to swap between Active and Past lists
    public void updateData(List<Booking> newBookings) {
        this.bookingList = newBookings;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Make sure you have created item_booking.xml in your res/layout folder!
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        holder.tvName.setText(booking.name);
        holder.tvStatus.setText(booking.status);
        holder.tvAddress.setText(booking.address);
        holder.tvDateTime.setText(booking.dateTime);
        holder.tvPrice.setText("₹" + booking.price);
        holder.tvAction.setText(booking.actionText);

        // Change status color based on active/past
        if(booking.status.equals("Active")) {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.green_main));
        } else {
            holder.tvStatus.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.light_grey));
        }
    }

    @Override
    public int getItemCount() {
        return bookingList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStatus, tvAddress, tvDateTime, tvPrice, tvAction;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvBookingName);
            tvStatus = itemView.findViewById(R.id.tvBookingStatus);
            tvAddress = itemView.findViewById(R.id.tvBookingAddress);
            tvDateTime = itemView.findViewById(R.id.tvBookingDateTime);
            tvPrice = itemView.findViewById(R.id.tvBookingPrice);
            tvAction = itemView.findViewById(R.id.tvBookingAction);
        }
    }
}