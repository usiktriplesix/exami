package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.TestsViewHolder> implements Filterable {

    private List<Test> testsList;
    private List<Test> unfilteredList;

    private TextView sheetTitle;
    private TextView sheetDescription;
    private TextView sheetPreparations;
    private TextView sheetResults;
    private TextView sheetBiomaterial;
    private AppCompatButton sheetAdd;

    public MainAdapter(List<Test> testsList) {
        this.testsList = testsList;
        this.unfilteredList = new ArrayList<>(testsList);

    }

    @NonNull
    @Override
    public TestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.tests_item, parent, false);
        return new TestsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull TestsViewHolder holder, int position) {
        holder.title.setText(testsList.get(holder.getAdapterPosition()).getName());
        holder.days.setText(testsList.get(holder.getAdapterPosition()).getTime_result());
        holder.price.setText(testsList.get(holder.getAdapterPosition()).getPrice() + " ₽");
    }

    @Override
    public int getItemCount() {
        return testsList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Test> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(unfilteredList);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (Test test : unfilteredList) {
                    if (test.getCategory().toLowerCase().contains(pattern)) {
                        filteredList.add(test);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            testsList.clear();
            testsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class TestsViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView days;
        private TextView price;
        private AppCompatButton add;
        private CardView card;

        public TestsViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.test_card);
            title = itemView.findViewById(R.id.title);
            days = itemView.findViewById(R.id.days);
            price = itemView.findViewById(R.id.price);
            add = itemView.findViewById(R.id.addButton);
            add.setBackgroundDrawable(itemView.getContext().getDrawable(R.drawable.blue_button));
        }
    }

}
