/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.abstractors;

import com.argochamber.cipher.vault.raster.Cipher;

/**
 * This factory creates Abstractors using the given ciphers.
 * <hr>
 * <strong>BEWARE!</strong> The cipher must match the decipher (in algorithm).
 * @author Pablo
 */
public class AbstractorFactory {
    
    /**
     * This now takes care of all.
     */
    private final Cipher cipher;
    
    /**
     * This is the ciphering key.
     */
    private final byte[] encoder;

    /**
     * Creates a new factory.
     * @param cipher
     * @param encoder 
     */
    public AbstractorFactory(Cipher cipher, byte[] encoder) {
        this.encoder = encoder;
        this.cipher = cipher;
    }
    
    /**
     * Geter for the current cipher.
     * @return 
     */
    public Cipher getCipher(){
        return this.cipher;
    }
    
    /**
     * build a new abstractor, setup the factory first.
     * @param path
     * @return 
     */
    public Abstractor buildNew(String path){
        Abstractor p = new Abstractor(path,cipher, encoder);
        p.setFactory(this);
        return p;
    }
    
    
}
