package kg.geektech.courses.android3.lesson1sept.ui.filmF;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.courses.android3.lesson1sept.App;
import kg.geektech.courses.android3.lesson1sept.R;
import kg.geektech.courses.android3.lesson1sept.data.models.FilmModel;
import kg.geektech.courses.android3.lesson1sept.data.network.GhibliService;
import kg.geektech.courses.android3.lesson1sept.ui.main.MainActivity;

public class FilmFragment extends Fragment {

    String id;
    TextView title;
    TextView director;
    TextView producer;
    TextView description;
    TextView releaseDate;
    Button species;

    public static final String SPECIES = "species";

    ArrayList<String> speciesList = new ArrayList<>();

    private static final String TITLE = "Title: ";
    private static final String DIRECTOR = "Director: ";
    private static final String PRODUCER = "Producer: ";
    private static final String DESCRIPTION = "Description: ";
    private static final String RELEASE_DATE = "Release date: ";

    public FilmFragment() {
        // Required empty public constructor
    }

    public static FilmFragment newInstance(String param1, String param2) {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(MainActivity.FILM_ID);
            Log.d("FilmFragment",id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.title_film_tv);
        director = view.findViewById(R.id.director_film_tv);
        producer = view.findViewById(R.id.producer_film_tv);
        description = view.findViewById(R.id.description_film_tv);
        releaseDate = view.findViewById(R.id.release_date_film_tv);
        species = view.findViewById(R.id.species_film_btn);

        App.ghibliService.getFilmById(id, new GhibliService.GhibliFilmCallback() {
            @Override
            public void onSuccess(FilmModel filmModel) {
                if (filmModel != null){
                    title.setText(TITLE + filmModel.getTitle());
                    description.setText(DESCRIPTION + filmModel.getDescription());
                    producer.setText(PRODUCER + filmModel.getProducer());
                    director.setText(DIRECTOR + filmModel.getDirector());
                    releaseDate.setText(RELEASE_DATE + filmModel.getReleaseDate());
                    speciesList = (ArrayList<String>) filmModel.getSpecies();
                }
            }
            @Override
            public void onFailure(Throwable ex) {}
        });

        species.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList(SPECIES,speciesList);
            Log.d("Fail", String.valueOf(speciesList.size()));
            Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.action_filmFragment_to_speciesFragment,bundle);
        });
    }
}