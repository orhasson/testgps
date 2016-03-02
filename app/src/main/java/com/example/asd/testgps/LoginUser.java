package com.example.asd.testgps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asd.testgps.providers.ServerConnection;

import java.io.IOException;

public class LoginUser extends Activity {

    Button loginBtn;
    Button DeleteSrvBtn;
    EditText ChildId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginBtn = (Button) findViewById(R.id.btnShowLocation);
        ChildId = (EditText) findViewById(R.id.test1);
        DeleteSrvBtn = (Button) findViewById(R.id.btndeleteservice);
    }

    public void onLoginButtonClick(View view) {
        String msgFromServer = "";
        try {
            ServerConnection serverConnection = ServerConnection.getServerConnection(ChildId.getText().toString());
            serverConnection.start();

            while (msgFromServer.equals("")) {
                Thread.sleep(100);
                msgFromServer = serverConnection.getMsgFromServer();
            }

            if (msgFromServer.toLowerCase().equals("ok")) {

                StartMyServiceAtBootReceiver a=new StartMyServiceAtBootReceiver();
                Intent intent =  new Intent(LoginUser.this, GPSTracker.class);
                a.onReceive(this,intent);
                StaticDataContainer.CHILD_ID= ChildId.getText().toString();
                this.startService(intent);
            }
            else if(msgFromServer.toLowerCase().equals("not")) {

            }
        }catch (Exception e){
            System.out.println();
        }
    }

    public void onDeleteServiceBtn(View view){
        Intent srv=new Intent(LoginUser.this, GPSTracker.class);
        stopService(srv);
    }
}

