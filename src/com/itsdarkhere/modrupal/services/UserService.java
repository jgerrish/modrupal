/**
 * 
 */
package com.itsdarkhere.modrupal.services;

import java.util.Hashtable;
import java.util.Vector;

import com.itsdarkhere.modrupal.ModrupalServiceClient;

/**
 * Provides an interface to the user service module.
 * 
 * @author Joshua Gerrish
 */
public class UserService extends ModrupalService {

    /**
     * Create a UserService object
     * 
     * @param client the client to use for connecting to the server.
     */
    public UserService(ModrupalServiceClient client) {
        super(client);
    }

    /**
     * Call the login method.
     * 
     * @param username the username to login
     * @param password the password for the user
     * @return the results of the login, including a new session id.
     */
    public Hashtable login(String username, String password) {
        Vector params = new Vector();

        params.addElement(username);
        params.addElement(password);

        Hashtable res = null;
        try {
            res = (Hashtable)session_call("user.login", params);
        } catch (Exception e) {
            // The kxml-rpc library doesn't like null value nodes.  It will
            // throw an exception if the server returns a result with null values.
            // This package includes a patched version of the kxml-rpc library.
            System.out.println(e);
        }

        return res;
    }
    
}
