/**
 * 
 */
package com.itsdarkhere.modrupal.services;

import java.util.Vector;

import com.itsdarkhere.modrupal.ModrupalServiceClient;

/**
 * The TaxonmyService class provides an interface to the taxonmy service module.
 * 
 * @author Joshua Gerrish
 */
public class TaxonomyService extends ModrupalService {

    /**
     * Create a new TaxonmyService object with the given client.
     * 
     * @param client the client to connect to the server with.
     */
    public TaxonomyService(ModrupalServiceClient client) {
        super(client);
    }

    /**
     * Call the selectNodes method.
     * This method returns a list of nodes that match a set of taxonomy terms.
     * 
     * @param terms a Vector of taxonomy terms to retrieve.
     * @param fields a Vector of node fields to return.
     * @return a Vector of node fields that match the given terms.
     */
    public Vector selectNodes(Vector terms, Vector fields) {
        Vector params = new Vector();

        params.addElement(terms);
        params.addElement(fields);
        params.addElement(new String("or"));
        params.addElement(new String("all"));
        params.addElement(new Boolean(false));
        params.addElement(new String("n.sticky DESC, n.created DESC"));

        Vector res = null;

        try {
            res = (Vector)session_call("taxonomy.selectNodes", params);
        } catch (Exception e) {
            System.out.println(e);
        }

        return res; 
    }
}
