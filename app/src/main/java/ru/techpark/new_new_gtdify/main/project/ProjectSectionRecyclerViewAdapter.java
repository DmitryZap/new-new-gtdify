package ru.techpark.new_new_gtdify.main.project;

import android.content.Context;
import android.graphics.Paint;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.model.Section;


public class ProjectSectionRecyclerViewAdapter extends RecyclerView.Adapter<ProjectSectionRecyclerViewAdapter.ViewHolder>{
    private List<Section> mValues = new ArrayList<>();

    public void setValues(List<Section> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);

        return new ViewHolder(view);
    }

    public void RefreshData(List<Section> items) {
        mValues = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectSectionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.name.setText(holder.mItem.getName());

    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Section mItem;
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            name = (TextView) itemView.findViewById(R.id.section_name);
        }
    }

}
