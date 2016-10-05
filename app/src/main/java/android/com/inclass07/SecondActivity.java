package android.com.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    TextView description;
    TextView date;
    TextView title;
    ImageView imageView;
    Tunes tunes = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        description = (TextView) findViewById(R.id.textViewDescription);
        date = (TextView) findViewById(R.id.textViewDate);
        title = (TextView) findViewById(R.id.textViewTitle);
        imageView = (ImageView) findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.get(MainActivity.FEEDS_KEY) != null) {
                tunes = (Tunes) extras.get(MainActivity.FEEDS_KEY);
            }
        }

        description.setText(tunes.getSummary());
        title.setText(tunes.getTitle());
        Date date1 = new Date(tunes.getRelDate());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        String time1 = sdf.format(date1);
        date.setText("Updated On: "+time1);
        Picasso.with(this).load(tunes.getLargeImgUrl()).into(imageView);
    }
}
