package android.com.inclass07;

import android.widget.ImageView;

/**
 * Created by murali101002 on 10/3/2016.
 */
public class ImageData {
    public String url;
    public ImageView view;

    public ImageData(String url, ImageView view) {
        this.url = url;
        this.view = view;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageView getView() {
        return view;
    }

    public void setView(ImageView view) {
        this.view = view;
    }
}
