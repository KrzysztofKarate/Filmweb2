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

public class QueAdapter  extends ArrayAdapter<Movie> {

    Activity context;
    List<Movie> mLogs;
    private String tytul;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference secRef = firebaseDatabase.getReference("kolejka");
    /*
    adapter generuje liste rzeczy ktore maja sie znajdowac w ListView. Tutaj generujemy liste
    wszystkich aktywnosci
    */

    QueAdapter(Activity context, List<Movie> mLogs){
        super(context, R.layout.table_row1, mLogs);

        this.context = context;
        this.mLogs = mLogs;
    }

    //deklaruje z klasy abstrakcyjnej klase statyczna
    private static class ViewHolder {
        private TextView logTytul;
        private TextView logGatunek;
        private TextView logAktor;
        private Button remove_btn;
    }


    //generuje mi kazdy wiersz w listView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.table_row1, null, true);

        QueAdapter.ViewHolder holder = new QueAdapter.ViewHolder();

        holder.logTytul = (TextView) view.findViewById(R.id.text_tytulQue);
        holder.logGatunek = (TextView) view.findViewById(R.id.text_gatunekQue);
        holder.logAktor = (TextView) view.findViewById(R.id.text_aktorQue);
        holder.remove_btn = (Button) view.findViewById(R.id.remove_btn);
        ///////////////////////////////////////////////////////////////////////////////
        tytul = holder.logTytul.getText().toString();
        holder.remove_btn = (Button) view.findViewById(R.id.remove_btn);

        holder.remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    secRef.child(tytul).removeValue();
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
