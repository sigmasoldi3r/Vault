/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault;

import com.argochamber.cipher.vault.abstractors.AbstractorFactory;

/**
 * This Factory uses the default algorithm used by The Vault System.
 * @author Pablo
 */
public class DefaultFactory extends AbstractorFactory{
    
    /**
     * Builds a factory using the default algorithm.
     * @param key 
     */
    public DefaultFactory(byte[] key){
        super( Vault.DEFAULT_CIPHER, key );
    }
    
}
