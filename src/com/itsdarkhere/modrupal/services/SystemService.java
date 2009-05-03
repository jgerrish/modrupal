/**
 * 
 */
package com.itsdarkhere.modrupal.services;

import java.util.Hashtable;
import java.util.Vector;

import com.itsdarkhere.modrupal.ModrupalServiceClient;

/**
 * System service module provides an interface to the system.service Drupal
 * Service module.
 * 
 * @author Joshua Gerrish
 */
public class SystemService extends ModrupalService {
    /**
     * Create a SystemService object with the given client.
     * 
     * @param client the client
     */
    public SystemService(ModrupalServiceClient client) {
        super(client);
    }

    /**
     * Call the system.connect method
     * 
     * @return the return result
     */
    public Hashtable connect() {
        Vector params = new Vector();
        Hashtable res = null;
        try {
            res = (Hashtable)client.execute("system.connect", params);
        } catch (Exception e) {
            System.out.println(e);
        }

        return res;
    }

}
