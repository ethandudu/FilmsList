package fr.ethanduault.tp3;

import static android.app.ProgressDialog.show;
import static android.app.usage.UsageEvents.Event.NONE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<String> filmList = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView filmList = (ListView) findViewById(R.id.filmList);
        filmList.setOnItemClickListener(this::onItemClick);

        registerForContextMenu(filmList);
    }


    public void addToList(View v){
        EditText filmNameEditText = (EditText) findViewById(R.id.filmNameEditText);
        String filmName = filmNameEditText.getText().toString();
        ListView filmList = (ListView) findViewById(R.id.filmList);


        if (filmName.length() == 0) {
            Toast.makeText(this, "Merci d'entrer le nom du film", Toast.LENGTH_SHORT).show();
        } else {
            this.filmList.add(filmName);
            filmNameEditText.setText("");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.filmList);
            filmList.setAdapter(adapter);
            Toast.makeText(this, "Film ajouté", Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemClick (AdapterView<?> p, View v, int pos, long id) {
        Intent intent = new Intent(this, FilmActivity.class);
        intent.putExtra("filmName", filmList.get(pos));
        startActivity(intent);
    }

    public void onCreateContextMenu(ContextMenu m, View v, ContextMenu.ContextMenuInfo i){
        super.onCreateContextMenu(m, v, i);
        m.setHeaderTitle("Options");
        m.add(NONE, v.getId(), 1, "Modifier");
        m.add(NONE, v.getId(), 2, "Supprimer");
    }

    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Modifier"){
            String filmName = filmList.get(info.position);
            EditText filmNameEditText = (EditText) findViewById(R.id.filmNameEditText);
            filmNameEditText.setText(filmName);
            filmList.remove(info.position);
            adapter.notifyDataSetChanged();
        } else if (item.getTitle() == "Supprimer"){
            filmList.remove(info.position);
            adapter.notifyDataSetChanged();
        } else {
            return false;
        }
        return false;
    }
}
