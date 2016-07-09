/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.raster;

/**
 *
 * @author Pablo
 */
public interface Encoder {
    
    /**
     * This must be overriden by encoder and decoders.
     * @param input
     * @param encoder
     * @return 
     */
    public abstract byte[] encode(byte[] input, byte[] encoder);
    
}
