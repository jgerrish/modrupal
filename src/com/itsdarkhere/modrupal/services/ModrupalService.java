package com.itsdarkhere.modrupal.services;

import java.util.Vector;

import com.itsdarkhere.modrupal.ModrupalServiceClient;

/**
 * Base class for Drupal Services.  Any services should inherit from this class.
 * 
 * @author Joshua Gerrish
 */
public abstract class ModrupalService {
    public ModrupalServiceClient client;

    /**
     * Create a ModrupalService object that should connect with a given client.
     * 
     * @param client the client to use for interacting with the service.
     */
    public ModrupalService(ModrupalServiceClient client) {
        this.client = client;
    }
    
    /**
     * Call a method using a given session.
     * This calls an XML-RPC method with the current client session.
     * 
     * @param method the service method to call
     * @param params the parameters
     * @return the result as an Object
     * @throws Exception
     */
    public Object session_call(String method, Vector params) throws Exception {
        client.auth.get_hash(method);

        Vector final_params = new Vector();
        
        final_params.addElement(client.auth.hash);
        final_params.addElement(client.auth.domain);
        final_params.addElement(client.auth.timestamp);
        final_params.addElement(client.auth.nonce);
        final_params.addElement(client.auth.session);

        for(int i = 0; i < params.size(); i++) {
            final_params.addElement(params.elementAt(i));
        }
        System.out.println("final params: " + final_params);
        return client.execute(method, final_params);
    }

}
