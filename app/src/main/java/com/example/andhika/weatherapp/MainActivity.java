package com.example.andhika.weatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

import static com.example.andhika.weatherapp.R.menu.*;

public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private ImageView iconView;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    Weather weather = new Weather();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = (TextView) findViewById(R.id.cityText);
        iconView = (ImageView) findViewById(R.id.thumbnailIcon);
        temp = (TextView) findViewById(R.id.tempText);
        description = (TextView) findViewById(R.id.cloudText);
        humidity = (TextView) findViewById(R.id.humidText);
        pressure = (TextView) findViewById(R.id.pressureText);
        wind = (TextView) findViewById(R.id.windText);
        sunrise = (TextView) findViewById(R.id.riseText);
        sunset = (TextView) findViewById(R.id.setText);
        updated = (TextView) findViewById(R.id.updateText);

        renderWeatherData("London,uk");
    }

    private class WeatherTask extends AsyncTask<String, Void, Weather>{

        @Override
        protected Weather doInBackground(String... params) {
            String data = (new WeatherHttpClient()).getWeatherData(params[0]);
            //System.out.println(data);
            weather = JSONWeatherParser.getWeather(data);
            Log.v("Data: ", weather.place.getCity());
            return null;
        }
    }
    public void renderWeatherData(String city){
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_main, menu);
        return true;
    }
}
