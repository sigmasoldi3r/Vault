/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.raster;

/**
 * This will hold two encoders, that will encode and decode the data.
 * The Abstractor Factory will not hold anymore the ciper and decipher.
 * @author Pablo
 */
public class Cipher {
    
    /**
     * Those are the ones who will do the job for you.
     */
    private final Encoder encoder, decoder;
    
    public Cipher(Encoder encoder, Encoder decoder){
        this.encoder = encoder;
        this.decoder = decoder;
    }
    
    /**
     * This encodes data.
     * @param data
     * @param meta
     * @return 
     */
    public byte[] encode(byte[] data, byte[] meta){
        return this.encoder.encode(data, meta);
    }
    
    /**
     * This decodes the data.
     * @param data
     * @param meta
     * @return 
     */
    public byte[] decode(byte[] data, byte[] meta){
        return this.decoder.encode(data, meta);
    }
    
}
