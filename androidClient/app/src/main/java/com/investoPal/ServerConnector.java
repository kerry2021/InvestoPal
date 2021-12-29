package com.investoPal;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerConnector extends AsyncTask<String, Void, Void>
//This is the client side interface used to communicate with server
{
    private static String IP_ADDR = "66.159.126.79";
    private static int PORT_NUM = 50000;

    private String userID;
    private NetworkUser user;
    private Socket socket;

    ServerConnector(NetworkUser user, String userID){
        this.user = user;
        this.userID = userID;
    }

    @Override
    protected Void doInBackground(String... voids){
        //Initialize a connection and send over the userID

        try {
            socket = new Socket(IP_ADDR, PORT_NUM);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            out.println(userID);

            //This operation is expected to block until receiving a response from server, then it notifies user
            String response = in.readLine();
            user.OnMessageReceived(response);

            in.close();
            out.close();
        }
        catch( Exception e ){
            Log.e("connection", "connecting failure", e);
        }

        return null;
    }



}