package android.com.inclass07;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by murali101002 on 10/3/2016.
 */
public class TunesArrayAdapter extends ArrayAdapter<Tunes> {
    Context mContext;
    int mResource;
    List<Tunes> mData;
    public TunesArrayAdapter(Context context, int resource, List<Tunes> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mData = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        Tunes tune = mData.get(position);
        TextView title = (TextView)convertView.findViewById(R.id.showTitle);
        ImageView smallImage = (ImageView) convertView.findViewById(R.id.imageSmall);
        title.setText(tune.getTitle());
        Picasso.with(mContext).load(tune.getSmallImgUrl()).into(smallImage);
        if(position<MainActivity.count){
            convertView.setBackgroundColor(Color.GREEN);
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        return convertView;
    }
}
