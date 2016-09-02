/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.abstractors;

import com.argochamber.cipher.vault.raster.Encoder;

/**
 * This factory creates Abstractors using the given ciphers.
 * <hr>
 * <strong>BEWARE!</strong> The cipher must match the decipher (in algorithm).
 * @author Pablo
 */
public class AbstractorFactory {
    
    /**
     * The cipher used to encode resources.
     */
    private final Encoder cipher;
    
    /**
     * This should implement the inverse operation.
     */
    private final Encoder decipher;
    
    /**
     * This is the ciphering key.
     */
    private final byte[] encoder;

    /**
     * Creates a new factory using the given ciphers.
     * @param cipher
     * @param decipher 
     * @param encoder 
     */
    public AbstractorFactory(Encoder cipher, Encoder decipher, byte[] encoder) {
        this.cipher = cipher;
        this.decipher = decipher;
        this.encoder = encoder;
    }
    
    /**
     * Gets the algorithm class of ciphering.
     * @return 
     */
    public Encoder getCipher(){
        return this.cipher;
    }
    
    /**
     * Gets the algorithm class of ciphering.
     * @return 
     */
    public Encoder getDecipher(){
        return this.decipher;
    }
    
    /**
     * build a new abstractor, setup the factory first.
     * @param path
     * @return 
     */
    public Abstractor buildNew(String path){
        Abstractor p = new Abstractor(path, encoder);
        p.setCipher(cipher);
        p.setDecipher(decipher);
        p.setFactory(this);
        return p;
    }
    
    
}
