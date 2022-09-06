package com.example.books4all;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.books4all.RecyclerView.CardAdapter;
import com.example.books4all.RecyclerView.OnItemListener;
import com.example.books4all.ViewModel.AddViewModel;
import com.example.books4all.ViewModel.Less100ViewModel;
import com.example.books4all.ViewModel.ListViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputLayout;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment {

    //private List<CardItem> cardItemList;

    private AddViewModel addViewModel;
    private ArrayList<CardItem> listLess100;
    private ArrayList<CardItem> listMore100;
    private CardAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filter, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = getActivity();
        if (activity != null) {
            Utilities.setUpToolBar((AppCompatActivity) activity, getString(R.string.filter));

            ListViewModel listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
            HomeFragment homeFragment = new HomeFragment();

            //final OnItemListener listener = (OnItemListener) this;
            //adapter = new CardAdapter(addViewModel.getAllBook(), activity);

            Chip chipLess100 = view.findViewById(R.id.chipLess100);
            chipLess100.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setLess100(true);
                    //System.out.println(""+homeFragment.getLess100());
                    //listViewModel.getCardItemsLess100();

                    //listViewModel.getCardItemsLess100();
                }
            });

            Chip chipMore100 = view.findViewById(R.id.chipMore100);
            chipMore100.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setMore100(true);
                    listViewModel.getCardItemsMore100();
                }
            });

            Chip all = view.findViewById(R.id.all);
            all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setLIST(true);
                    listViewModel.getCardItems();
                }
            });

            Chip chipCrime = view.findViewById(R.id.chipCrime);
            chipCrime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setGIALLO(true);
                    listViewModel.getCardItemsGiallo();
                }
            });

            Chip chipNature = view.findViewById(R.id.chipNature);
            chipNature.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setNATURE(true);
                    listViewModel.getCardItemsNature();
                }
            });

            Chip chipFantasy = view.findViewById(R.id.chipFantasy);
            chipFantasy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setFANTASY(true);
                    listViewModel.getCardItemsFantasy();
                }
            });

            Chip chipIta = view.findViewById(R.id.chipIta);
            chipIta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setITA(true);
                    System.out.println(""+homeFragment.isITA());
                    System.out.println(""+homeFragment.isLIST());
                    listViewModel.getCardItemsIta();
                }
            });

            Chip chipUk = view.findViewById(R.id.chipUK);
            chipUk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setUK(true);
                    listViewModel.getCardItemsUk();
                }
            });

            Chip chipUsa = view.findViewById(R.id.chipUSA);
            chipUsa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    homeFragment.setPageFalse();
                    homeFragment.setTypeFalse();
                    homeFragment.setLinguaFalse();

                    homeFragment.setUSA(true);
                    listViewModel.getCardItemsUsa();
                }
            });

            FilterActivity filterActivity = new FilterActivity();

            Button btnApply = view.findViewById(R.id.buttonApplica);
            btnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((AppCompatActivity)activity).getSupportFragmentManager().popBackStack();
                    //Utilities.insertFragment((AppCompatActivity) activity, new HomeFragment(), HomeFragment.class.getSimpleName());

                }
            });


            //view.findViewById();
            //TextInputLayout textInputLayout = view.findViewById(R.id.book_textinput);
            //EditText editText = textInputLayout.getEditText();
            //TextView textView = view.findViewById(R.id.description_textinput);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.top_app_bar, menu);

        menu.findItem(R.id.app_bar_search).setVisible(false);
        menu.findItem(R.id.app_bar_filter).setVisible(false);
        //menu.findItem(R.id.app_bar_gps).setVisible(false);
    }

    public ArrayList<CardItem> getListLess100() {
        return listLess100;
    }
}
