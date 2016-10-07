package android.com.inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by murali101002 on 10/3/2016.
 */
public class GetJsonData extends AsyncTask<String,Void,ArrayList<Tunes>> {
    ProgressDialog progressDialog;
    GetContext activity;


    public GetJsonData(GetContext mainActivity) {
        this.activity=mainActivity;
    }

    @Override
    protected void onPostExecute(ArrayList<Tunes> tunes) {
        super.onPostExecute(tunes);
        activity.setUpData(tunes);
        progressDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity.getContect());
        progressDialog.setTitle("Loading Weather...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList doInBackground(String... params) {
        InputStream in;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                in = con.getInputStream();
                return ParseData.ParseJson.parseITunes(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static interface GetContext
    {
        public Context getContect();
        public void setUpData(ArrayList<Tunes> tunesArrayList);
    }
}
