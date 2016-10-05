package android.com.inclass07;

import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by murali101002 on 10/3/2016.
 */
public class ParseData {
    static public class ParseJson extends DefaultHandler{
        ArrayList<Tunes> tunesArrayList;
        Tunes tunes;
        StringBuilder xmlInnerText;
        int smallFlag = 0, largeFlag = 0, flag = 0;
        public ArrayList<Tunes> getTunesArrayList() {
            return tunesArrayList;
        }

        static ArrayList<Tunes> parseITunes(InputStream jsonData) throws IOException, SAXException {
            ParseJson feedSAXParser = new ParseJson();
            InputSource source = new InputSource(jsonData);
            source.setEncoding("UTF-8");
            Log.i("TAG", "response" + source);
//            inputSource.setCharacterStream(new StringReader(response));
//            xr.parse(inputSource);
            //jsonData = jsonData.toString().replaceAll("&","&amp;")
            Xml.parse(jsonData, Xml.Encoding.UTF_8, feedSAXParser);
            return feedSAXParser.getTunesArrayList();

        }


        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if(localName.equals("entry")){
                tunesArrayList.add(tunes);
            }

            if(flag == 0) {

                switch (localName) {

                    case "title":
                        String title;
                        title = xmlInnerText.toString().trim();
                        tunes.setTitle(title);
                        break;
                    case "summary":
                        String summary;
                        summary = xmlInnerText.toString().trim();
                        tunes.setSummary(summary);
                        break;
                }
            }
                switch (qName) {
                    case "im:image":
                        if (smallFlag == 1) {
                            String smallImg;
                            smallImg = xmlInnerText.toString().trim();
                            tunes.setSmallImgUrl(smallImg);
                            smallFlag = 0;
                        }
                        if (largeFlag == 1) {
                            String largeImg;
                            largeImg = xmlInnerText.toString().trim();
                            tunes.setLargeImgUrl(largeImg);
                            largeFlag = 0;
                        }
                        break;
                }

            xmlInnerText.setLength(0);

        }


        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            tunesArrayList = new ArrayList<>();
            xmlInnerText = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("feed")) {
                flag = 1;
            }
            if (localName.equals("entry")) {
                tunes = new Tunes();
                flag = 0;
            }
            if (qName.equals("im:image")) {
                if (attributes.getValue("height").equals("55"))
                    smallFlag = 1;
                if (attributes.getValue("height").equals("170"))
                    largeFlag = 1;
            }
            if (qName.equals("im:releaseDate")){
                tunes.setRelDate(attributes.getValue("label"));
            }
        }
    }
}
