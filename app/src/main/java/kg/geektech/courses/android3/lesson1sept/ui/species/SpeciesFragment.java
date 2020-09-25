package kg.geektech.courses.android3.lesson1sept.ui.species;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kg.geektech.courses.android3.lesson1sept.App;
import kg.geektech.courses.android3.lesson1sept.R;
import kg.geektech.courses.android3.lesson1sept.adapters.SpeciesAdapter;
import kg.geektech.courses.android3.lesson1sept.data.models.SpeciesModel;
import kg.geektech.courses.android3.lesson1sept.data.network.GhibliService;
import kg.geektech.courses.android3.lesson1sept.ui.filmF.FilmFragment;

public class SpeciesFragment extends Fragment {

    RecyclerView recyclerView;
    SpeciesAdapter speciesAdapter;
    ArrayList<String> speciesModels = new ArrayList<>();
    ArrayList<SpeciesModel> models = new ArrayList<>();

    public SpeciesFragment() {
        // Required empty public constructor
    }

    public static SpeciesFragment newInstance(String param1, String param2) {
        SpeciesFragment fragment = new SpeciesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            speciesModels= getArguments().getStringArrayList(FilmFragment.SPECIES);
            Log.d("kk", String.valueOf(getArguments().getStringArrayList(FilmFragment.SPECIES).size()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_species, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.species_recycler);
        speciesAdapter = new SpeciesAdapter();
        recyclerView.setAdapter(speciesAdapter);

        for (int i = 0; i < speciesModels.size(); i++) {
            Log.d("kk","l");
        App.ghibliService.getSpecies(speciesModels.get(i), new GhibliService.GhibliSpeciesCallBack() {
            @Override
            public void onSuccess(SpeciesModel speciesModel) {
                if (speciesModel != null){
                Log.d("kk",speciesModel.getName());
                    speciesAdapter.setSpeciesModels(speciesModel);
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                Log.d("Fail",ex.getMessage());
            }
        });
        }

    }
}