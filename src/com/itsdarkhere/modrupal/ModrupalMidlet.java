package com.itsdarkhere.modrupal;

import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.itsdarkhere.modrupal.services.SystemService;
import com.itsdarkhere.modrupal.services.TaxonomyService;
import com.itsdarkhere.modrupal.services.UserService;

/**
 * Simple MIDlet to show off the Modrupal Drupal Services Java toolkit
 * 
 * @author Joshua Gerrish
 */
public class ModrupalMidlet extends MIDlet {
    protected void startApp() throws MIDletStateChangeException {
        //String url = getAppProperty("url");
        //String api_key = getAppProperty("api_key");
        //String domain = getAppProperty("domain");
        //String username = getAppProperty("username");
        //String password = getAppProperty("password");
        String url = new String("INSERT_DRUPAL_SERVICES_URL_HERE");
        String api_key = new String("INSERT_API_KEY_HERE");
        String domain = new String("INSERT_DOMAIN_HERE");
        String username = new String("INSERT_USERNAME_HERE");
        String password = new String("INSERT_PASSWORD_HERE");

        ModrupalServiceClient client =
            new ModrupalServiceClient(url, api_key, domain);

        SystemService system = new SystemService(client);
        Hashtable res = system.connect();
        UserService user = new UserService(client);
        String sessid = (String)res.get("sessid");
        client.setSession(sessid);
        Hashtable session = null;

        if (sessid == null)
            System.out.println("Error getting session id");
        else
            session = user.login(username, password);
        client.setSession((String)session.get("sessid"));

        System.out.println("user.login: " + session);

        TaxonomyService taxonomy = new TaxonomyService(client);
        Vector nodes = null;

        Vector terms = new Vector();
        terms.addElement(new Integer(1));

        Vector fields = new Vector();
        fields.addElement(new String("title"));
        fields.addElement(new String("body"));

        nodes = taxonomy.selectNodes(terms, fields);
        System.out.println("nodes: " + nodes);
    }

    protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
    }

    protected void pauseApp() {
    }

}
