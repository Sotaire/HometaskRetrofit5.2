package kg.geektech.courses.android3.lesson1sept;

import android.app.Application;

import kg.geektech.courses.android3.lesson1sept.data.local.PreferenceUtils;
import kg.geektech.courses.android3.lesson1sept.data.network.GhibliService;

public class App extends Application {

    public static GhibliService ghibliService;

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(this);
        ghibliService = new GhibliService();
    }
}
