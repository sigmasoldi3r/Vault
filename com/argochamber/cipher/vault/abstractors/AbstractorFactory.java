/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.abstractors;

import com.argochamber.cipher.vault.raster.Cipher;
import com.argochamber.cipher.vault.raster.Encoder;

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
     * Creates a new factory using the given ciphers.
     * @deprecated Use the Cipher.
     * @param encoder
     * @param decoder 
     * @param key
     */
    public AbstractorFactory(Encoder encoder, Encoder decoder, byte[] key) {
        this.cipher = new Cipher(encoder, decoder);
        this.encoder = key;
    }

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
     * Gets the algorithm class of ciphering.
     * @deprecated  not needed
     * @see com.argochamber.cipher.vault.raster.Cipher
     * @return 
     */
    public Encoder getCipher(){
        return this.cipher.getEncoder();
    }
    
    /**
     * Gets the algorithm class of ciphering.
     * @deprecated 
     * @see com.argochamber.cipher.vault.raster.Cipher
     * @return 
     */
    public Encoder getDecipher(){
        return this.cipher.getDecoder();
    }
    
    /**
     * build a new abstractor, setup the factory first.
     * @param path
     * @return 
     */
    public Abstractor buildNew(String path){
        Abstractor p = new Abstractor(path, encoder);
        p.setCipher(cipher.getEncoder());
        p.setDecipher(cipher.getDecoder());
        p.setFactory(this);
        return p;
    }
    
    
}
