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
import ru.techpark.new_new_gtdify.model.Card;


public class ProjectCardRecyclerViewAdapter extends RecyclerView.Adapter<ProjectCardRecyclerViewAdapter.ViewHolder>{
    private List<Card> mValues = new ArrayList<>();
    private CheckBoxClickListener mCheckBoxClickListener;

    public void setValues(List<Card> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
    }

    public ProjectCardRecyclerViewAdapter(CheckBoxClickListener checkBoxClickListener) {
        mCheckBoxClickListener = checkBoxClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);

        return new ViewHolder(view);
    }

    public void RefreshData(List<Card> items) {
        mValues = items;
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectCardRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.title.setText(holder.mItem.getName());

        setupCardCheckboxState(holder);

        holder.checkBox.setOnClickListener(v -> {
            setupCardCheckboxState(holder);

            holder.mItem.setComplete(!holder.mItem.isComplete());
            mCheckBoxClickListener.onCheckClick(holder.mItem);
        });


        // TODO: добавить кнопки для действий над карточкой
    }


    private void setupCardCheckboxState (@NonNull ProjectCardRecyclerViewAdapter.ViewHolder holder) {
        holder.checkBox.setChecked(holder.mItem.isComplete());
        if(holder.checkBox.isChecked()) {
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Card mItem;
        public TextView title;
        public CheckBox checkBox;
//        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = (TextView) itemView.findViewById(R.id.card_title);
            checkBox = (CheckBox) itemView.findViewById(R.id.card_checkbox);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + checkBox.getText() + "'";
        }
    }

}
