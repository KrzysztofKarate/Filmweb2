package a.filmweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pick_activity extends AppCompatActivity {
    ListView logList;
    FirebaseDatabase database = FirebaseDatabase.getInstance(); //deklarujemy firebase
    DatabaseReference myRef = database.getReference("bazaFilmow");
    ArrayList<Movie> movieArrayList = new ArrayList<>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_activity);
        logList = (ListView) findViewById(R.id.log_list); //deklaracja Listy wyświetlania

        myRef.addValueEventListener(new ValueEventListener() { //dodajemy nasłuchiwacz zmian
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // pobieramy wszystkie dane od nowa gdy zostanie wprowadzona zmiana
                movieArrayList.clear();  //czyścimy listę w przypadku nastąpienia zmiany w bazie by nie dublowało elementów

                for (DataSnapshot productsSnapshot : dataSnapshot.getChildren()){ //w petli pobieramy dane
                    Movie mLog = productsSnapshot.getValue(Movie.class); //tworzymi pojedyncze logi
                    movieArrayList.add(mLog); // dodajemy logi do listy
                }

                // ponownie generuje listView
                adapter = new PickAdapter(pick_activity.this, movieArrayList);
                logList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }
}
