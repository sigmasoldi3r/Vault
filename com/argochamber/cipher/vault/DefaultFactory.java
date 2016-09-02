/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault;

import com.argochamber.cipher.vault.abstractors.AbstractorFactory;

/**
 * This Factory uses the default algorithm.
 * @author Pablo
 */
public class DefaultFactory extends AbstractorFactory{
    
    /**
     * Builds a factory using the default algorithm.
     * @param key 
     */
    public DefaultFactory(byte[] key){
        super(
                (raw, encoder) -> {
                    for (int i = 0; i < raw.length; i++) {
                        int b = 
                                (int) raw[i] + 
                                (int) encoder[i % encoder.length];
                        raw[i] = 
                                (byte) (b > Byte.MAX_VALUE  ?
                                (Byte.MIN_VALUE) + (b - (Byte.MAX_VALUE + 1)) :
                                b);
                    }
                    return raw;
                },
                (raw, encoder) -> {
                    for (int i = 0; i < raw.length; i++) {
                        int b = 
                                (int) raw[i] - 
                                (int) encoder[i % encoder.length];
                        raw[i] = 
                                (byte) (b < Byte.MIN_VALUE ?
                                (Byte.MAX_VALUE) + (b - (Byte.MIN_VALUE - 1)) :
                                b);
                    }
                    return raw;
                },
                key
        );
    }
    
}
