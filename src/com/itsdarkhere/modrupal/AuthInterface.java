package com.itsdarkhere.modrupal;

import java.util.Random;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

/**
 * The AuthInterface class provides an interface to generate API hashes and hold
 * authentication information.
 * 
 * @author Joshua Gerrish
 */
public class AuthInterface {
    public String api_key;
    public String domain;

    public String method;
    public String hash;
    public String nonce;
    public String timestamp;
    public String session;

    /**
     * Get the current time in milliseconds since the Epoch
     * 
     * @return the current time in milliseconds since Epoch
     */
    public long getTime() {
        return System.currentTimeMillis();
    }
    
    /**
     * Returns a random string
     * 
     * @return a random string
     */
    public String getRandomString() {
        Random r = new Random();

        String token = Long.toString(Math.abs(r.nextLong()), 36);
        return token;
    }

    /**
     * Constructs an AuthInterface object with the given API key and domain
     * 
     * @param api The Drupal Services API key
     * @param domain The domain for the API key
     */
    public AuthInterface(String api, String domain) {
        this.api_key = api;
        this.domain = domain;
    }
    
    /**
     * Generates an authentication string for hashing
     * 
     * @return the authentication string
     */
    public String getMessage() {
        StringBuffer buf = new StringBuffer();
        buf.append(this.timestamp);
        buf.append(";");
        buf.append(this.domain);
        buf.append(";");
        buf.append(this.nonce);
        buf.append(";");
        buf.append(this.method);

        return buf.toString();
    }

    /**
     * Generates a HMAC SHA256 hash for the given method using
     * the current authentication data.
     * 
     * @param method the method to call
     * @return the hashed authentication string
     */
    public String get_hash(String method) {
        this.timestamp = Long.toString(getTime());
        this.nonce = getRandomString();
        this.method = method;
        
        HMac hmac = new HMac(new SHA256Digest());
        byte[] resBuf = new byte[hmac.getMacSize()];
        
        //byte[] keyBytes = Hex.decode(api_key);
        byte[] keyBytes = api_key.getBytes();
        hmac.init(new KeyParameter(keyBytes));

        String msg = getMessage();
        byte[] m = msg.getBytes();
        hmac.update(m, 0, m.length);

        hmac.doFinal(resBuf, 0);
        this.hash = new String(Hex.encode(resBuf));
        //this.hash = new String(resBuf);
        return this.hash;
    }

    /**
     * Sets the session id for this authentication object
     * 
     * @param session the session id
     */
    public void setSession(String session) {
        this.session = session;
    }
}
