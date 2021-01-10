package com.example.allinone.RecyclerViews.HEighthRecView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allinone.R;

import java.util.ArrayList;

public class Trips_Adapter extends RecyclerView.Adapter<Trips_Adapter.Holder> {
    ArrayList<AssignFragData> subtask_lists;
    Context context;
    LayoutInflater inflate;

    public Trips_Adapter(ArrayList<AssignFragData> active_alerts_lists, Context activity) {
        this.subtask_lists = active_alerts_lists;
        this.context = activity;
        this.inflate = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Trips_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.trips_adapter_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Trips_Adapter.Holder holder, int i) {
        final AssignFragData subtask_list = subtask_lists.get(i);
        holder.order_id_txt.setText(subtask_list.getSubtaskname());
        holder.lane_txt.setText(subtask_list.getRoute() + "-" + subtask_list.getDestination());
        holder.xpcn.setText(subtask_list.getXpcn());
        holder.xpts.setText(subtask_list.getXpts());
        holder.ffv.setText(subtask_list.getFfv());
//        String time1= apiUtils.TimeCalc(subtask_list.getDep());
//        String date1= apiUtils.DateCalc(subtask_list.getDep());
//        holder.dpt.setText(date1+" | "+time1);
        holder.dpt.setText(subtask_list.getDep());
//        String time2= apiUtils.TimeCalc(subtask_list.getAt());
//        String date2= apiUtils.DateCalc(subtask_list.getAt());
//        holder.at.setText(date2+" | "+time2);
        holder.at.setText(subtask_list.getAt());
//        String time3= apiUtils.TimeCalc(subtask_list.getDt());
//        String date3= apiUtils.DateCalc(subtask_list.getDt());
//        holder.dt.setText(date3+" | "+time3);
        holder.dt.setText(subtask_list.getDt());
        if (subtask_list.getTouch().equals("null")) {
            holder.toch.setText(" -");
        } else {
            holder.toch.setText(subtask_list.getTouch());
        }
        holder.status.setText(subtask_list.getStatus());
        holder.vno.setText(subtask_list.getVehicle_no());
        holder.tt.setText(subtask_list.getTt());
        holder.timetaken.setText(subtask_list.getTimeTaken());
        holder.haltinghours.setText(subtask_list.getHt());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, Trip_History.class);
//                i.putExtra("Departed_date", subtask_list.getDep());
//                i.putExtra("Destination_arrived_date", subtask_list.getDt());
//                i.putExtra("Origin_route", subtask_list.getRoute());
//                i.putExtra("destination_route", subtask_list.getDestination());
//                i.putExtra("trip_id",subtask_list.getTripId());
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return subtask_lists.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView order_id_txt, xpcn, xpts, ffv, vno, toch, dpt, at, dt, lane_txt, status, tt, timetaken, haltinghours;
        LinearLayout lenear_layout;
        Button trip;

        public Holder(@NonNull View itemView) {
            super(itemView);
            lenear_layout = itemView.findViewById(R.id.lenear_layout);

            order_id_txt = itemView.findViewById(R.id.oid);
            xpcn = itemView.findViewById(R.id.xpcn);
            xpts = itemView.findViewById(R.id.xpts);
            ffv = itemView.findViewById(R.id.ffv_name);
            vno = itemView.findViewById(R.id.vn);
            lane_txt = itemView.findViewById(R.id.lane_txt);
            toch = itemView.findViewById(R.id.to);
            dpt = itemView.findViewById(R.id.dept);
            at = itemView.findViewById(R.id.arrival);
            dt = itemView.findViewById(R.id.del);
            status = itemView.findViewById(R.id.status_txt);
            tt = itemView.findViewById(R.id.tt);
            timetaken = itemView.findViewById(R.id.timetaken);
            haltinghours = itemView.findViewById(R.id.haltinghours);
        }
    }
}
