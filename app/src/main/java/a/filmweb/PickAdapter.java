package a.filmweb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Aleksandra on 2017-06-07.
 */

public class PickAdapter extends ArrayAdapter<Movie> {

    Activity context;
    List<Movie> mLogs;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();  ///TEST!!!!!!!!!
    DatabaseReference secRef = firebaseDatabase.getReference("kolejka"); ///TEST!!!!!!!!!
    String tytul;
    String gatunek;
    String aktor;
    /*
    adapter generuje liste rzeczy ktore maja sie znajdowac w ListView. Tutaj generujemy liste
    wszystkich aktywnosci
    */

    PickAdapter(Activity context, List<Movie> mLogs){
        super(context, R.layout.table_row, mLogs);

        this.context = context;
        this.mLogs = mLogs;
    }

    //deklaruje z klasy abstrakcyjnej klase statyczna
    private static class ViewHolder {
        private TextView logTytul;
        private TextView logGatunek;
        private TextView logAktor;
        private Button addFromList_btn; ///TEST!!!!!!!!!
    }


    //generuje mi kazdy wiersz w listView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.table_row, null, true);

        ViewHolder holder = new ViewHolder();

        holder.logTytul = (TextView) view.findViewById(R.id.text_tytul);
        holder.logGatunek = (TextView) view.findViewById(R.id.text_gatunek);
        holder.logAktor = (TextView) view.findViewById(R.id.text_aktor);
        ///////////////////////////////////////////////////////////////////////////////
        tytul = holder.logTytul.getText().toString();
        gatunek = holder.logGatunek.getText().toString();
        aktor = holder.logAktor.getText().toString();

        holder.addFromList_btn = (Button) view.findViewById(R.id.addFromList_btn);
        holder.addFromList_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    try {
                        Movie newMovie = new Movie(tytul, gatunek, aktor); //nowy obiekt event log
                        secRef.child(newMovie.getTytul()).setValue(newMovie);  //do katalogi referencyjnego wrzucamy nasze informacje
                    }
                    catch (Exception e) {
                    }
                }
         });
        ///////////////////////////////////////////////////////////////////////////////
        Movie mLog = mLogs.get(position);

        holder.logTytul.setText(mLog.getTytul());
        holder.logGatunek.setText(mLog.getGlownyAktor());
        holder.logAktor.setText(mLog.getGatunek());

        return view;
    }

}