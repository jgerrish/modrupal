package com.itsdarkhere.modrupal;

import java.util.Vector;

import org.kxmlrpc.XmlRpcClient;

/**
 * XML-RPC interface to the Drupal Services module
 * 
 * @author Joshua Gerrish
 */
public class ModrupalServiceClient {
    public String url = null;
    public XmlRpcClient client = null;
    public AuthInterface auth = null;

    /**
     * Create a client with the given Drupal Services URL.
     * 
     * @param url the URL of the XML-RPC Drupal Services endpoint
     */
    public ModrupalServiceClient(String url) {
        this.url = url;
        client = new XmlRpcClient(url);
    }

    /**
     * Create a client with the given Drupal services URL, API key and domain.
     * 
     * @param url the URL of the XML-RPC Drupal Services endpoint
     * @param api_key the API key for the Drupal Services module
     * @param domain the domain for the given API key
     */
    public ModrupalServiceClient(String url, String api_key, String domain) {
        this.url = url;
        client = new XmlRpcClient(url);
        this.auth = new AuthInterface(api_key, domain);
    }

    /**
     * Execute an XML-RPC method.
     * The method takes a vector of parameters.  Simply leave out any optional parameters.
     * 
     * @param method the method to execute
     * @param params a Vector of parameters to pass to the method
     * @return the result as an Object.
     * @throws Exception if there is an XML-RPC exception.
     */
    public Object execute(String method, Vector params) throws Exception {
        return client.execute(method, params);
    }

    /**
     * Set the session ID for the client
     * 
     * @param session the session id
     */
    public void setSession(String session) {
        auth.setSession(session);
    }
}
