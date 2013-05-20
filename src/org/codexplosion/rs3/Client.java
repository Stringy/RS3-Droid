package org.codexplosion.rs3;

import android.util.Log;
import org.codexplosion.rs3.model.User;

/**
 * Created by giles on 20/05/13.
 *
 * Client for connecting to the codexplosion server for retrieval of
 * user data and feeds/items
 */
public class Client {

    private static String SERVER_ADDRESS = "127.0.0.1";
    private static int PORT = 1337;

    //singleton pattern
    private static Client clientInstance = new Client();

    private Client() {

    }

    public static Client getInstance() {
        return clientInstance;
    }

    /*
        Login connects to the server and logs in, retrieving cookie and user
        cookie is stored internally to this class
     */
    public User login(String user, String pass) {
        Log.d("CLIENT DEBUG", "username: " + user + " pass: " + pass);
        return null;
    }
}
