package android.aleks.com.marvelapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Aleksandar on 5.1.2018 г..
 */

public class Utils {

    public static final String md5( final String s ) throws NoSuchAlgorithmException {

        final String MD5 = "MD5";
        // Create MD5 Hash

        final MessageDigest digest = java.security.MessageDigest.getInstance( MD5 );

        digest.update( s.getBytes() );

        final byte messageDigest[] = digest.digest();

        // Create Hex String

        StringBuilder hexString = new StringBuilder();

        for ( byte aMessageDigest : messageDigest ) {

            String h = Integer.toHexString( 0xFF & aMessageDigest );

            while ( h.length() < 2 ) {

                h = "0" + h;
            }

            hexString.append( h );

        }

        return hexString.toString();
    }
}
