package android.com.inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GetJsonData.GetContext{
    public static final String FEEDS_KEY = "key";
    ListView listView;
    EditText search;
    String searchWord;
    Tunes tune = null;
    Button goButton, clearButton;
    static int count = 0;
    ArrayList<Tunes> tunesList = new ArrayList<>();
    ArrayList<Tunes> searchList = new ArrayList<>();
    private int flag = 0;
    //ProgressDialog progressDialog;
    ArrayList<Tunes> receivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml";
        goButton = (Button) findViewById(R.id.go);
        search = (EditText) findViewById(R.id.editBox);

        goButton.setOnClickListener(this);
        clearButton = (Button) findViewById(R.id.clear);
        clearButton.setOnClickListener(this);
        receivedData = new ArrayList<Tunes>();
        new GetJsonData(this).execute(url);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go:
                flag = 0;
                count = 0;
                searchWord = search.getText().toString();
                if (!(searchWord.trim().equals(""))) {
                    for (int j = 0; j < tunesList.size(); j++) {
                        tune = tunesList.get(j);
                        if (tune.getTitle().toUpperCase().contains(searchWord.toUpperCase())) {
                            tunesList.remove(tune);
                            tunesList.add(0, tune);
                            count++;
                            flag = 1;
                        }

                    }
                    if (flag == 0) {
                        Toast.makeText(this, "Your keyword does not match with the list", Toast.LENGTH_SHORT).show();
                    }
                    setNewAdapter(tunesList);

                } else {
                    Toast.makeText(this, "Enter a valid String", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.clear:
                search.setText("");
                count = 0;
                Collections.sort(searchList);
                setNewAdapter(searchList);
                break;
        }

    }

    private void setNewAdapter(final ArrayList<Tunes> tunesList) {
        TunesArrayAdapter adapter1 = new TunesArrayAdapter(this, R.layout.custom_layout, tunesList);
        listView.setAdapter(adapter1);
        adapter1.setNotifyOnChange(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(FEEDS_KEY, tunesList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public Context getContect() {
        return this;
    }

    @Override
    public void setUpData(ArrayList<Tunes> tunesArrayList) {

//        searchList = (ArrayList<Tunes>) tunesArrayList.clone();
        tunesList = (ArrayList<Tunes>) tunesArrayList.clone();
        Collections.sort(tunesList);
        searchList = tunesList;

        listView = (ListView) findViewById(R.id.listView);
        setNewAdapter(tunesList);
    }
}
