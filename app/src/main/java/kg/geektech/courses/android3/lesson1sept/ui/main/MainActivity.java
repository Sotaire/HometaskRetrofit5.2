package kg.geektech.courses.android3.lesson1sept.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.courses.android3.lesson1sept.App;
import kg.geektech.courses.android3.lesson1sept.R;
import kg.geektech.courses.android3.lesson1sept.adapters.MainAdapter;
import kg.geektech.courses.android3.lesson1sept.data.models.Book;
import kg.geektech.courses.android3.lesson1sept.data.models.FilmModel;
import kg.geektech.courses.android3.lesson1sept.data.network.GhibliService;
import kg.geektech.courses.android3.lesson1sept.interfaces.OnFilmClickListener;
import kg.geektech.courses.android3.lesson1sept.ui.film.FilmActivity;

public class MainActivity
        extends AppCompatActivity
        implements MainContract.View {

    public static final String FILM_ID = "filmId";

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    List<FilmModel> filmModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.films_recycler);
        mainAdapter = new MainAdapter();
        recyclerView.setAdapter(mainAdapter);

        App.ghibliService.getFilmById("58611129-2dbc-4a81-a72f-77ddfc1b1b49",new GhibliService.GhibliFilmCallback() {
            @Override
            public void onSuccess(FilmModel filmModel) {
                Log.d("GG",filmModel.getDirector());
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });

        App.ghibliService.getFilms(new GhibliService.GhibliFilmsCallback() {
            @Override
            public void onSuccess(List<FilmModel> filmModel) {
                Log.d("GHIBLI", String.valueOf(filmModel.size()));
                filmModels = filmModel;
                mainAdapter.setFilmModels(filmModel);
            }

            @Override
            public void onFailure(Throwable ex) {
                Log.d("GHIBLI",ex.getMessage());
            }
        });

        mainAdapter.setListener(new OnFilmClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, FilmActivity.class);
                intent.putExtra(FILM_ID,filmModels.get(position).getId());
                Log.d("ID",filmModels.get(position).getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void showBook(Book book) {
    }

}