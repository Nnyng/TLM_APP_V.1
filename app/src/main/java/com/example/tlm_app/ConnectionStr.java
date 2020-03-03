package com.example.tlm_app;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionStr {

    @SuppressLint("NewApi")
    public Connection connectionclasss(String user, String password, String database, String server)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                .detectAll()
//                .penaltyLog()
//                .build();
//        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //ConnectionURL = "jdbc:jtds:sqlserver://" + server +";"+ database + ";" + user+ ";" + password + ";";
//            String dbName = "AndroidDB";
//            String serverip="10.0.2.2";
//            String serverport="1433";
//            String url = "jdbc:sqlserver://"+serverip+"\\14GRAFICALI\\MSSQLSERVER2008"+serverport+";databaseName="+dbName+"";

//            ip = "192.168.23.135\\SQLEXPRESS:1433";
//            db = "TLM_TestMobileApp";
//            DBUserNameStr = "TLMSLBASE_USER";
//            DBPasswordStr = "tlmslbaseuser";

//            ConnectionURL = "jdbc:jtds:sqlserver://" + server +";"+ database + ";user=" + user + ";password=" + password + ";";
//            connection = DriverManager.getConnection(ConnectionURL);

            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.23.135;DatabaseName=TLM_TestMobileApp;User=TLMSLBASE_USER;Password=tlmslbaseuser;");

        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
