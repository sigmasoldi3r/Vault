/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.argochamber.cipher.vault;

import com.argochamber.cipher.vault.abstractors.Abstractor;
import com.argochamber.cipher.vault.abstractors.AbstractorFactory;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Pablo
 */
public class Vault {

    /**
     * This is the default working vault dir.
     */
    public static final String VAULT_FOLDER = "vault",
            VAULT_KEY = "key",
            ACTION_DECODE = "d",
            ACTION_ENCODE = "e";

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.print("Please, enter the key: ");
        String key = input.nextLine();

        //We need a factory
        AbstractorFactory factory = new AbstractorFactory(
                //ENCODE
                (raw, encoder) -> {
                    for (int i = 0; i < raw.length; i++) {
                        int b = (int) raw[i] + (int) encoder[i % encoder.length];
                        raw[i] = (byte) (b > Byte.MAX_VALUE
                                ? (Byte.MIN_VALUE) + (b - (Byte.MAX_VALUE + 1)) : b);
                    }
                    return raw;
                },
                //DECODE
                (raw, encoder) -> {
                    for (int i = 0; i < raw.length; i++) {
                        int b = (int) raw[i] - (int) encoder[i % encoder.length];
                        raw[i] = (byte) (b < Byte.MIN_VALUE
                                ? (Byte.MAX_VALUE) + (b - (Byte.MIN_VALUE - 1)) : b);
                    }
                    return raw;
                },
                //SECRET KEY
                key.getBytes());

        //Here comes the greasy stuff
        //The abstractor is the root folder.
        Abstractor root = factory.buildNew(VAULT_FOLDER);
        if (!root.exists()) {
            System.err.println("Could not find the vault folder.");
            System.exit(-1);
        }

        //Remember, the more you encode, the more you will have to decode.
        System.out.println("Enter the action (d/e):");
        boolean shouldEncode;
        {
            boolean c;
            String act;
            do {
                act = input.nextLine();
                shouldEncode = act.equalsIgnoreCase(ACTION_ENCODE);
                c = act.equalsIgnoreCase(ACTION_DECODE) | shouldEncode;
                if (!c) {
                    System.out.println("Please, enter d or e");
                }
            } while (!c);
        }

        //Here comes the really greasy stuff.
        if (shouldEncode) {
            deepEncode(root);
        } else {
            deepDecode(root);
        }

    }

    /**
     * Recursive deep scan. Encodes! haha lol :U
     *
     * @param root
     */
    static void deepEncode(Abstractor root) {
        if (root.isDirectory()) {
            Arrays.asList(root.listFiles()).stream()
                    .forEach(Vault::deepEncode);
        } else {
            try {
                byte[] input = root.getEncode(true);
                try (FileOutputStream fout = new FileOutputStream(root)) {
                    fout.write(input);
                }
                byte[] beacon = root.getCipher().encode(root.getName().getBytes(), root.getEncoder());
                String newName = getHex(beacon);
                root.renameTo(new java.io.File(root.getParentFile().getAbsolutePath()+"/"+newName));
            } catch (IOException ex) {
                Logger.getLogger(Vault.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Recursive deep scan. Decodes! haha lol :U
     *
     * @param root
     */
    static void deepDecode(Abstractor root) {
        if (root.isDirectory()) {
            Arrays.asList(root.listFiles()).stream()
                    .forEach(Vault::deepDecode);
        } else {
            try {
                byte[] input = root.getEncode(false);
                try (FileOutputStream fout = new FileOutputStream(root)) {
                    fout.write(input);
                }
                byte[] beacon = root.getDecipher().encode(getPure(root.getName().toCharArray()), root.getEncoder());
                String newName = new String(beacon);
                root.renameTo(new java.io.File(root.getParentFile().getAbsolutePath()+"/"+newName));
            } catch (IOException ex) {
                Logger.getLogger(Vault.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Convert a byte array to a string of hex.
     * @param data
     * @return 
     */
    public static String getHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%03d", (b+(Byte.MAX_VALUE+1))));
        }
        return sb.toString();
    }
    
    /**
     * Gets the pure byte array meaning of that hex string.
     * @param data
     * @return 
     */
    public static byte[] getPure(char[] data){
        byte[] bRaw = new byte[data.length/3];
        for (int i = 0; i < data.length; i += 3){
            String hex = new String(new char[]{data[i], data[i+1], data[i+2]});
            bRaw[i/3] = (byte)(Integer.parseInt(hex)+(Byte.MIN_VALUE));
        }
        return bRaw;
    }

}
