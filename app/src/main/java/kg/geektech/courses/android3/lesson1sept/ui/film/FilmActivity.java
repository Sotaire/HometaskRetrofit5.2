package kg.geektech.courses.android3.lesson1sept.ui.film;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import kg.geektech.courses.android3.lesson1sept.R;
import kg.geektech.courses.android3.lesson1sept.ui.main.MainActivity;

public class FilmActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
//        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
//        Fragment navhost = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//        NavController c = NavHostFragment.findNavController(navhost);
        String id = getIntent().getStringExtra(MainActivity.FILM_ID);
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.FILM_ID,id);
        navController.setGraph(R.navigation.film_graph,bundle);
    }
}