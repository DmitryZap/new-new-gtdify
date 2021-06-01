package ru.techpark.new_new_gtdify.main.project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.techpark.new_new_gtdify.R;
import ru.techpark.new_new_gtdify.model.Card;


public class ProjectCardRecyclerViewAdapter extends RecyclerView.Adapter<ProjectCardRecyclerViewAdapter.ViewHolder>{
    private List<Card> mValues = new ArrayList<>();

    public void setValues(List<Card> mValues) {
        this.mValues = mValues;
        notifyDataSetChanged();
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
        Card mItem = mValues.get(position);
        holder.title.setText(mItem.getName());
        // TODO: добавить кнопки для действий над карточкой
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Card mItem;
        public TextView title;
//        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            title = (TextView) itemView.findViewById(R.id.card_title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + title.getText() + "'";
        }
    }

}
