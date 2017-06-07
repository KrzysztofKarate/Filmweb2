package a.filmweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button pick_btn = (Button) findViewById(R.id.pick_btn);
        pick_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, pick_activity.class);
                startActivity(intent);
            }
        });

        //przejscie przyciskiem activity_log button do ekranu z logami
        Button roll_btn = (Button) findViewById(R.id.roll_btn);
        roll_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, roll_activity.class);
                startActivity(intent);
            }
        });

        Button que_btn = (Button) findViewById(R.id.que_btn);
        que_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, que_activity.class);
                startActivity(intent);
            }
        });
        Button add_btn = (Button) findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view){
                context = getApplicationContext();
                Intent intent = new Intent(context, add_movie.class);
                startActivity(intent);
            }
        });
    }
}
