/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.abstractors;

import com.argochamber.cipher.vault.raster.Encoder;

/**
 *
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
     * 
     * @param cipher
     * @param decipher 
     */
    public AbstractorFactory(Encoder cipher, Encoder decipher, byte[] encoder) {
        this.cipher = cipher;
        this.decipher = decipher;
        this.encoder = encoder;
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
