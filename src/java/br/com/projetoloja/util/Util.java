/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projetoloja.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristiano
 */
public class Util {
    
    public static String Sha01(String txt)
        {
            String varsha1="";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(txt.getBytes("UTF-8"));
            varsha1 = String.format("%040x",new BigInteger(1, digest.digest()));
           
        } catch (NoSuchAlgorithmException ex) {
            varsha1 ="";
            ex.printStackTrace();
        } catch (UnsupportedEncodingException ex) {
            varsha1 ="";
            ex.printStackTrace();
        }
        
           return varsha1; 
        }
}
