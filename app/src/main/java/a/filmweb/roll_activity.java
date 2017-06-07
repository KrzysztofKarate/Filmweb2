package a.filmweb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class roll_activity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = firebaseDatabase.getReference("bazaFilmow");
    private DatabaseReference secRef = firebaseDatabase.getReference("kolejka");

    ArrayList<Movie> movieAList = new ArrayList<>();
    TextView rolled_title;
    private String tytul;
    private String gatunek;
    private String aktor;
    Button startRoll_btn;
    Button rollAdd_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_activity);

        myRef.addValueEventListener(new ValueEventListener() { //dodajemy nasłuchiwacz zmian
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { // pobieramy wszystkie dane od nowa gdy zostanie wprowadzona zmiana
                movieAList.clear();  //czyścimy listę w przypadku nastąpienia zmiany w bazie by nie dublowało elementów

                for (DataSnapshot productsSnapshot : dataSnapshot.getChildren()) { //w petli pobieramy dane
                    Movie mLog = productsSnapshot.getValue(Movie.class); //tworzymi pojedyncze logi
                    movieAList.add(mLog); // dodajemy logi do listy
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        startRoll_btn = (Button) findViewById(R.id.startRoll_btn);
        rolled_title = (TextView) findViewById(R.id.rolled_title);
        startRoll_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                try{
                    int rnd = new Random().nextInt(movieAList.size());
                    rolled_title.setText(movieAList.get(rnd).getTytul());

                    tytul=movieAList.get(rnd).getTytul();
                    gatunek=movieAList.get(rnd).getGatunek();
                    aktor=movieAList.get(rnd).getGlownyAktor();
                }
                catch (Exception e){
                    rolled_title.setText("Za szybko! spróbuj jeszcze raz");
                }
            }
        });

        rollAdd_btn = (Button)findViewById(R.id.rollAdd_btn);
        rollAdd_btn.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View view){
                try {
                    Movie newMovie = new Movie(tytul, gatunek, aktor); //nowy obiekt event log
                    secRef.child(newMovie.getTytul()).setValue(newMovie);  //do katalogi referencyjnego wrzucamy nasze informacje
                }
                catch (Exception e) {
                    rolled_title.setText("najpierw wybierz film!");
                }
            }
        });
    }
}
