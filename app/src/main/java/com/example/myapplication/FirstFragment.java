package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    private RecyclerView testsView;
    private RecyclerView.LayoutManager verticalLayoutManager;

    private MainAdapter mainAdapter;
    private List<Test> tests;

    private AppCompatButton[] filters;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        testsView = requireView().findViewById(R.id.tests_view);

        verticalLayoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,  false);
        testsView.setLayoutManager(verticalLayoutManager);

        initTestsView();
        setupFilters();

    }

    private void initTestsView() {
        tests = new ArrayList<>();
        tests.add(new Test(0, "ПЦР-тест на определение РНК коронавируса стандартный", "", "1800", "Covid", "2 дня", "", "" ));
        tests.add(new Test(0, "Клинический анализ крови с лейкоцитарной формулировкой ", "", "690", "Популярные", "1 дня", "", "" ));
        tests.add(new Test(0, "Биохимический анализ крови", "", "2440", "", "2 дня", "Комплексные", "" ));
        tests.add(new Test(0, "ПЦР-тест на определения РНК коронавируса полный", "", "4900", "Covid", "3 дня", "", "" ));

        mainAdapter = new MainAdapter(tests);

        testsView.setAdapter(mainAdapter);

    }

    public FirstFragment() {
    }

    private void setupFilters() {
        filters = new AppCompatButton[4];
        boolean[] state = new boolean[4];
        for (int i = 0; i < filters.length; i++) {
            state[i] = false;
            filters[i] = requireView().findViewById(getResources().getIdentifier("filter" + (i + 1), "id", getContext().getPackageName()));
            filters[i].setTextColor(getContext().getColor(R.color.gray));
            filters[i].setBackgroundDrawable(getContext().getDrawable(R.drawable.unavailable_button));
            final int[] n = {i};
            filters[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < filters.length; i++) {
                        if (i == n[0] && !state[i]) {
                            state[i] = true;
                            filters[i].setTextColor(getContext().getColor(R.color.white));
                            filters[i].setBackgroundDrawable(getContext().getDrawable(R.drawable.blue_button));
                            changeFilter(i);
                        } else if (i == n[0] && state[i]) {
                            filters[i].setTextColor(getContext().getColor(R.color.gray));
                            filters[i].setBackgroundDrawable(getContext().getDrawable(R.drawable.unavailable_button));
                            changeFilter(4);
                            state[i] = false;
                        } else {
                            filters[i].setTextColor(getContext().getColor(R.color.gray));
                            filters[i].setBackgroundDrawable(getContext().getDrawable(R.drawable.unavailable_button));
                            state[i] = false;
                        }
                    }
                }
            });
        }
    }

    private void changeFilter(int index) {
        if (mainAdapter != null) {
            String filter;
            switch (index) {
                case 0:
                    filter = "Популярные";
                    break;
                case 1:
                    filter = "COVID";
                    break;
                case 2:
                    filter = "Онкогенетические";
                    break;
                case 3:
                    filter = "Комплесные";
                    break;
                default:
                    filter = null;
                    break;
            }
            mainAdapter.getFilter().filter(filter);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
}