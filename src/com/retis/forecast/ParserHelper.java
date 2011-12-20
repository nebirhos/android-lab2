package com.retis.forecast;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;

import android.util.Log;


class ParserHelper {

    private final String URL = "http://www.google.com/ig/api?weather=%s&hl=en&oe=utf-8";
    private final String TAG = "ParserHelper";
    private File CachePath;
    private String xml;

    public ParserHelper(File CachePath) {
    	this.CachePath=CachePath;
        try {
            getHttp( "Pisa" );
            parseHttp();
        }
        catch ( Exception e ) {
            Log.e( TAG, "error: " + e );
        }
    }
    
    

    public void getHttp( String city ) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet method = new HttpGet(String.format(URL, city) );

        String result; InputStream in = null;
        try {
            HttpResponse response = client.execute(method);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != HttpStatus.SC_OK)
                throw new Exception("HTTP GET failed: " + status);

            HttpEntity entity = response.getEntity();
            if (entity == null) {
                Log.e(TAG, "No contents in responce..");
                xml = "";
            }

            result= "";
            in = entity.getContent();
            byte[] b = new byte[2048]; int read;
            while ((read = in.read(b)) >= 0)
                result += new String(b, 0, read);
            
            xml=result;

        } catch (Exception e) {
            Log.e(TAG, "Exception while retriving: "+e);
            // return "";          // FIXME
            xml = "<?xml version=\"1.0\"?><xml_api_reply version=\"1\"><weather module_id=\"0\" tab_id=\"0\" mobile_row=\"0\" mobile_zipped=\"1\" row=\"0\" section=\"0\" ><forecast_information><city data=\"Pisa, Tuscany\"/><postal_code data=\"Pisa\"/><latitude_e6 data=\"\"/><longitude_e6 data=\"\"/><forecast_date data=\"2011-12-19\"/><current_date_time data=\"1970-01-01 00:00:00 +0000\"/><unit_system data=\"SI\"/></forecast_information><current_conditions><condition data=\"Sereno\"/><temp_f data=\"28\"/><temp_c data=\"-2\"/><humidity data=\"Umidità: 86%\"/><icon data=\"/ig/images/weather/sunny.gif\"/><wind_condition data=\"Vento: E a 6 km/h\"/></current_conditions><forecast_conditions><day_of_week data=\"lun\"/><low data=\"3\"/><high data=\"11\"/><icon data=\"/ig/images/weather/sunny.gif\"/><condition data=\"Sereno\"/></forecast_conditions><forecast_conditions><day_of_week data=\"mar\"/><low data=\"-1\"/><high data=\"8\"/><icon data=\"/ig/images/weather/mostly_sunny.gif\"/><condition data=\"Per lo più soleggiato\"/></forecast_conditions><forecast_conditions><day_of_week data=\"mer\"/><low data=\"3\"/><high data=\"10\"/><icon data=\"/ig/images/weather/mostly_sunny.gif\"/><condition data=\"Per lo più soleggiato\"/></forecast_conditions><forecast_conditions><day_of_week data=\"gio\"/><low data=\"4\"/><high data=\"9\"/><icon data=\"/ig/images/weather/sunny.gif\"/><condition data=\"Sereno\"/></forecast_conditions></weather></xml_api_reply>";
        } finally {
            if (in != null) in.close();
        }
    }


    public void parseHttp() {
        try {
     		FileOutputStream out = new FileOutputStream(CachePath); 
    		
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput( new StringReader ( xml ) );
            int eventType = xpp.getEventType(); // START_DOCUMENT

            // Find <current_conditions> start
            xpp.next();
            while ( xpp.next() != XmlPullParser.START_TAG ||
                    xpp.getName().compareToIgnoreCase( "current_conditions" ) != 0 ) { }
            // Read <current_conditions> content
            xpp.next();
            while ( xpp.next() != XmlPullParser.END_TAG ||
                    xpp.getName().compareToIgnoreCase( "current_conditions" ) != 0 ) {
                if ( xpp.getAttributeCount() == 1 ) {
                	/*Writes on cache*/
                	String str = xpp.getName() + '\n' + xpp.getAttributeValue(0);
                	out.write( str.getBytes());
                	//Log.d( xpp.getName(), xpp.getAttributeValue(0) );
                }
            }
        }
        catch ( XmlPullParserException e ) {
            Log.e(TAG, "Exception while parsing: "+e);
        }
        catch ( IOException e ) {
            Log.e(TAG, "IOException: "+e);
        }
    } 

}
