package a.filmweb;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class add_movie extends AppCompatActivity {

    private EditText edit_tytul;
    private EditText edit_gatunek;
    private EditText edit_aktor;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = firebaseDatabase.getReference("bazaFilmow"); // deklaracja folderu logi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

         edit_tytul = (EditText) findViewById(R.id.edit_tytul);  // deklaracja pól tekstowych
         edit_gatunek= (EditText) findViewById(R.id.edit_gatunek);
         edit_aktor = (EditText) findViewById(R.id.edit_aktor);

        //poprzez add button dodajemy aktywność finansową
        Button addMovie_btn = (Button) findViewById(R.id.addMovie_btn);
        addMovie_btn.setOnClickListener(new View.OnClickListener(){

                @Override public void onClick(View view){
                    // zczytywanie wartosci do nowego obiektu
                    String tytul = edit_tytul.getText().toString();
                    String gatunek = edit_gatunek.getText().toString();
                    String aktor  = edit_aktor.getText().toString(); // pobieramy date z kalendarza

                    Movie mLog = new Movie(tytul, gatunek, aktor); //nowy obiekt event log
                    mRef.child(mLog.getTytul()).setValue(mLog);  //do katalogi referencyjnego wrzucamy nasze informacje

                    edit_tytul.setText("");
                    edit_gatunek.setText("");
                    edit_aktor.setText("");
                }
            });
        }
}
