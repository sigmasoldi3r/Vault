/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault.abstractors;

import com.argochamber.cipher.vault.raster.Cipher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * @author Pablo
 */
public class Abstractor extends File {
    
    /**
     * This will tell the cipher how to encrypt/decrypt.
     */
    protected final byte[] encoder;
    
    /**
     * The cipher used to encode resources.
     */
    private final Cipher cipher;
    
    /**
     * This is a reference to the parent's factory
     * (The one that has created it).
     * <p>
     *  If null that means that this abstractor was not factory-assembled.
     * </p>
     */
    private AbstractorFactory factory;
    

    /**
     * This, should not be used to instantiate an object, instead you should
     * use a factory.
     * @param cipher
     * @see com.argochamber.cipher.vault.abstractors.AbstractorFactory
     * @param path
     * @param encoder 
     */
    public Abstractor(String path, Cipher cipher, byte[] encoder) {
        super(path);
        this.factory = null;
        this.encoder = encoder;
        this.cipher = cipher;
    }
    
    /**
     * Ciphers and deciphers the abstract charset.
     * @param enc
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     */
    public byte[] getEncode(boolean enc) throws 
            IOException,
            FileNotFoundException
    {
        if (enc){
            return cipher.encode(Files.readAllBytes(this.toPath()), encoder);
        } else {
            return cipher.decode(Files.readAllBytes(this.toPath()), encoder);
        }
    }
    
    
    @Override
    public Abstractor[] listFiles() {
        File[] files = super.listFiles();
        Abstractor[] absList = new Abstractor[files.length];
        Arrays.asList(files).stream()
                .map((file) -> factory.buildNew(file.getPath()))
                .collect(Collectors.toList())
                .toArray(absList);
        return absList;
    }

    /**
     * The factory that created this object, if created that way.
     * <p>
     * If the object was not created in a factory, that will be null.
     * </p>
     * @return 
     */
    public AbstractorFactory getFactory() {
        return factory;
    }

    public void setFactory(AbstractorFactory factory) {
        this.factory = factory;
    }

    public byte[] getEncoder() {
        return encoder;
    }

    public Cipher getCipher() {
        return cipher;
    }   
    
    
}
