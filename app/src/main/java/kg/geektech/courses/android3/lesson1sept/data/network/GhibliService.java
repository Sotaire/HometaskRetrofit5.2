package kg.geektech.courses.android3.lesson1sept.data.network;

import android.util.Log;

import java.util.List;

import kg.geektech.courses.android3.lesson1sept.data.models.FilmModel;
import kg.geektech.courses.android3.lesson1sept.data.models.SpeciesModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public class GhibliService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://ghibliapi.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    GhibliApi service = retrofit.create(GhibliApi.class);

    public void getFilmById(String id, GhibliFilmCallback callback) {
        Call<FilmModel> call = service.getFilmById(id);
        call.enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                    Log.d("tag", response.body().getTitle());
                }
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {
                callback.onFailure(t);
                Log.d("tag", "Failure");
            }
        });
    }

    public void getFilms(GhibliFilmsCallback ghibliFilmCallback){
        Call<List<FilmModel>> listCall = service.getFilms();
        listCall.enqueue(new Callback<List<FilmModel>>() {
            @Override
            public void onResponse(Call<List<FilmModel>> call, Response<List<FilmModel>> response) {
                ghibliFilmCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<FilmModel>> call, Throwable t) {
                ghibliFilmCallback.onFailure(t);
            }
        });
    }

    public void getSpecies(String speciesId,GhibliSpeciesCallBack ghibliSpeciesCallBack){
        Call<SpeciesModel> modelCall = service.getSpecies(speciesId);
        modelCall.enqueue(new Callback<SpeciesModel>() {
            @Override
            public void onResponse(Call<SpeciesModel> call, Response<SpeciesModel> response) {
                if (response.isSuccessful()){
                    ghibliSpeciesCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<SpeciesModel> call, Throwable t) {
                Log.d("Fail",t.getMessage());
                t.printStackTrace();
                ghibliSpeciesCallBack.onFailure(t);
            }
        });
    }

    public interface GhibliFilmCallback {
        void onSuccess(FilmModel filmModel);

        void onFailure(Throwable ex);
    }

    public interface GhibliSpeciesCallBack{
        void onSuccess(SpeciesModel speciesModel);

        void onFailure(Throwable ex);
    }

    public interface GhibliFilmsCallback{
        void onSuccess(List<FilmModel> filmModel);

        void onFailure(Throwable ex);
    }

    public interface GhibliApi {
        @GET("films/{filmId}")
        Call<FilmModel> getFilmById(
                @Path("filmId") String filmId
        );
        @GET("films")
        Call<List<FilmModel>> getFilms();
//
        @GET
        Call<SpeciesModel> getSpecies(@Url String url);

//        @GET("{url}")
//        Call<SpeciesModel> getSpecies(@Path(value = "url",encoded = true)String url);

    }

}
