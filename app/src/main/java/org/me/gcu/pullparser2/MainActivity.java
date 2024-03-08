package org.me.gcu.pullparser2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    private LinkedList<Thing> thingList;
    // XML that requires to be parsed
    // Note this is not the best place to define string
    // String defined here for testing purposes
    Thing aThing;
    private String thingString = "<ThingCollection> " +
            "<Thing> " +
            "<Bolt>M8 x 100</Bolt>	" +
            "<Nut>M8 Hex</Nut>	" +
            "<Washer>M8 Penny Washers</Washer>	" +
            "</Thing>" +
            "<Thing> " +
            "<Bolt>M8 x 150</Bolt>	" +
            "<Nut>M8 Hex</Nut>	" +
            "<Washer>M8 Penny Washers</Washer>	" +
            "</Thing>" +
            "<Thing> " +
            "<Bolt>M6 x 100</Bolt>	" +
            "<Nut>68 Hex</Nut>	" +
            "<Washer>M6 Penny Washers</Washer>	" +
            "	</Thing>" +
            "</ThingCollection>";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thingList = new LinkedList<Thing>();
        //Make call to parsing code
        //Note this is not the best location
        parseData(thingString);

    } // End of onCreate


    private void parseData(String dataToParse)
    {

        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( dataToParse ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType == XmlPullParser.START_TAG) // Found a start tag
                {   // Check which start Tag we have as we'd do different things
                    if (xpp.getName().equalsIgnoreCase("Thing"))
                    {
                        aThing = new Thing();
                        Log.d("MyTag","New Thing found!");
                    }
                    else if (xpp.getName().equalsIgnoreCase("bolt"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.d("MyTag","Bolt is " + temp);
                        aThing.setBolt(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("Nut"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.d("MyTag","Nut is " + temp);
                        aThing.setNut(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("Washer"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        // Do something with text
                        Log.d("MyTag","Washer is " + temp);
                        aThing.setWasher(temp);
                    }
                }
                else if(eventType == XmlPullParser.END_TAG) // Found an end tag
                {
                    if (xpp.getName().equalsIgnoreCase("Thing"))
                    {
                        thingList.add(aThing);
                        Log.d("MyTag","Thing parsing completed!");

                    }
                }
                eventType = xpp.next(); // Get the next event  before looping again
            } // End of while
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.d("MyTag","End of document reached");
    } // End of parseData

} //End of class