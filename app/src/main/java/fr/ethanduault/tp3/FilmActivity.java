package fr.ethanduault.tp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

public class FilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        Hashtable<String, String> filmList = new Hashtable<String, String>();
        filmList.put("starwars", "Film de science-fiction écris par George Lucas");
        filmList.put("lordofthering", "Film de fantasy écris par J.R.R. Tolkien");

        Hashtable<String, String> CoverList = new Hashtable<String, String>();
        CoverList.put("starwars", "starwars");
        CoverList.put("lordofthering", "lordofthering");

        Intent i;
        i = getIntent();
        String filmName = i.getStringExtra("filmName");

        TextView filmNameTextView = (TextView) findViewById(R.id.nameFilmLabel);
        filmNameTextView.setText(filmName);

        filmName = filmName.replaceAll("\\s+", "");
        filmName = filmName.toLowerCase();
        System.out.println(filmName);

        TextView filmdescTextView = (TextView) findViewById(R.id.descFilmLabel);
        filmdescTextView.setText(filmList.get(filmName));

        ImageView filmImageView = (ImageView) findViewById(R.id.filmImageView);
        //check if the image exists in the hashtable
        if (CoverList.get(filmName) == null) {
            filmImageView.setImageResource(getResources().getIdentifier("notfound", "drawable", getPackageName()));
        } else {
            filmImageView.setImageResource(getResources().getIdentifier(CoverList.get(filmName), "drawable", getPackageName()));
        }
    }
}