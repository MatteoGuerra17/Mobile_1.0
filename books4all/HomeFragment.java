package com.example.books4all;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.books4all.RecyclerView.CardAdapter;
import com.example.books4all.RecyclerView.OnItemListener;
import com.example.books4all.ViewModel.Less100ViewModel;
import com.example.books4all.ViewModel.ListViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemListener {

    private static boolean LESS100 = false;
    private static boolean MORE100 = false;
    private static boolean LIST = true;
    private static boolean GIALLO = false;
    private static boolean NATURE = false;
    private static boolean FANTASY = false;
    private static boolean ITA = false;
    private static boolean UK = false;
    private static boolean USA = false;

    private CardAdapter adapter;
    private RecyclerView recyclerView;
    private ListViewModel listViewModel;
    private Less100ViewModel less100ViewModel;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;

    TextInputEditText nationBook;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    private MenuItem gps;

    private boolean requestingLocationUpdates = false;

    private FilterFragment filterFragment = (FilterFragment) getParentFragment();



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(requestingLocationUpdates && getActivity() != null){
            startLocationUpdate(getActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void startLocationUpdate(Activity activity) {
        final String PERMISSION_REQUESTED = Manifest.permission.ACCESS_FINE_LOCATION;

        if (ActivityCompat.
                checkSelfPermission(activity, PERMISSION_REQUESTED) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(activity, PERMISSION_REQUESTED)) {
            showDialog(activity);
        } else {
            requestPermissionLauncher.launch(PERMISSION_REQUESTED);
        }

    }

    private void initializeLocation(Activity activity) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        locationRequest = LocationRequest.create();

        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location location = locationResult.getLastLocation();
                String text = location.getLatitude() + ", " + location.getLongitude();
                //nationBook.setText(text);

                requestingLocationUpdates = false;
                stopLocationUpdates();
            }
        };
    }

    private void showDialog(Activity activity){
        new AlertDialog.Builder(activity)
                .setMessage("permission denied but needed for gps functionality")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null) {
            initializeLocation(activity);
            Utilities.setUpToolBar((AppCompatActivity) activity, getString(R.string.Home));
            setRecyclerView(getActivity());

            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddFragment(), AddFragment.class.getSimpleName());
                }
            });

            listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
            System.out.println("3");
            if(LIST) {
                System.out.println("2");
                listViewModel.getCardItems().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                        System.out.println("observe carditems");
                    }
                });
            }else if(LESS100) {
                //listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
                listViewModel.getCardItemsLess100().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                        System.out.println("observe carditemless100");
                    }
                });
            }else if(MORE100) {
                //listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
                listViewModel.getCardItemsMore100().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }else if(GIALLO){
                listViewModel.getCardItemsGiallo().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }else if(NATURE){
                listViewModel.getCardItemsNature().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }else if(FANTASY){
                listViewModel.getCardItemsFantasy().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }else if(ITA){
                listViewModel.getCardItemsIta().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            } else if(UK){
                listViewModel.getCardItemsUk().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }else if(USA){
                listViewModel.getCardItemsUsa().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItems) {
                        adapter.setData(cardItems);
                    }
                });
            }

            requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if (result){
                        startLocationUpdate(activity);
                        Log.d("LAB_ADDFRAGMENT", "PERMISSION GRANTED");
                    } else {
                        showDialog(activity);
                        Log.d("LAB_ADDFRAGMENT", "PERMISSION NOT GRANTED");
                    }
                }
            });
            /*if(filterFragment.getChipLess100().isEnabled()) {
                listViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListViewModel.class);
                listViewModel.getCardItemsLess100().observe((LifecycleOwner) activity, new Observer<List<CardItem>>() {
                    @Override
                    public void onChanged(List<CardItem> cardItemsLess100) {
                        adapter.setData(cardItemsLess100);
                    }
                });
            }*/
        } else {
            Log.e("Home Fragment", "Activity is null");
        }
    }

    private void setRecyclerView(final Activity activity) {
        recyclerView = activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        /*
        List<CardItem> list = new ArrayList<>();
        list.add(new CardItem("ic_baseline_android_24", "book1", "desc"));
        list.add(new CardItem("ic_baseline_android_24", "book2", "desc2"));
         */

        final OnItemListener listener = this;
        adapter = new CardAdapter(listener, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int pos) {
        Activity activity = getActivity();
        if (activity != null) {
            Utilities.insertFragment((AppCompatActivity) activity, new DetailsFragment(), DetailsFragment.class.getSimpleName());
            listViewModel.setItemSelected(adapter.getItemSelected(pos));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem search = menu.findItem(R.id.app_bar_search);
        //gps = menu.findItem(R.id.app_bar_gps);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        Activity activity = getActivity();
        MenuItem filter = menu.findItem(R.id.app_bar_filter);
        filter.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Utilities.insertFragment((AppCompatActivity) activity, new FilterFragment(), FilterFragment.class.getSimpleName());//------------------------------
                return true;
            }
        });
    }

    public void setPageFalse(){
        LESS100 = false;
        MORE100 = false;
        LIST = false;
    }

    public void setTypeFalse(){
        GIALLO = false;
        NATURE = false;
        FANTASY = false;
    }

    public void setLinguaFalse(){
        ITA = false;
        UK = false;
        USA = false;
    }

    public void setLess100(boolean less100) {
        LESS100 = less100;
    }

    public boolean getLess100() {
        return LESS100;
    }

    public boolean isMore100() {
        return MORE100;
    }

    public void setMore100(boolean more100) {
        MORE100 = more100;
    }

    public boolean isLIST() {
        return LIST;
    }

    public void setLIST(boolean list) {
        LIST = list;
    }

    public boolean isGIALLO() {
        return GIALLO;
    }

    public void setGIALLO(boolean giallo) {
        GIALLO = giallo;
    }

    public boolean isNATURE() {
        return NATURE;
    }

    public void setNATURE(boolean nature) {
        NATURE = nature;
    }

    public boolean isFANTASY() {
        return FANTASY;
    }

    public void setFANTASY(boolean fantasy) {
        FANTASY = fantasy;
    }

    public boolean isITA() {
        return ITA;
    }

    public void setITA(boolean ita) {
        ITA = ita;
    }

    public boolean isUK() {
        return UK;
    }

    public void setUK(boolean uk) {
        UK = uk;
    }

    public boolean isUSA() {
        return USA;
    }

    public void setUSA(boolean usa) {
        USA = usa;
    }
}