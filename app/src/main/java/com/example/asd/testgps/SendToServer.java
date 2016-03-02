package com.example.asd.testgps;

import android.os.AsyncTask;
import android.util.Log;
import com.example.asd.testgps.objects.ChildLocationUpdaterObject;
import com.example.asd.testgps.objects.LocationObject;
import com.example.asd.testgps.providers.ServerConnection;
/**
 * Created by 123123123 on 20/04/2015.
 */

    public class SendToServer extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... arg0) {
            try {
                String longi = arg0[0];
                String lati = arg0[1];
                Log.d("Longitude:", longi);
                Log.d("Latitude:", lati);

                ChildLocationUpdaterObject Ch=new ChildLocationUpdaterObject();
                LocationObject childR=new LocationObject();
                Ch.setId(StaticDataContainer.CHILD_ID);
                String msgFromServer = "";
                childR.setLatitude(Double.parseDouble(lati));
                childR.setLongitude(Double.parseDouble(longi));
                Ch.setLocationObject(childR);
                ServerConnection serverConnection = ServerConnection.getServerConnection(Ch);
                serverConnection.start();
                while (msgFromServer.equals("")) {
                    msgFromServer = serverConnection.getMsgFromServer();
                }
            }catch(Exception e){};

            return "call";
        }
    }

