package cz.cvut.kbss.wpa.provider;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.stereotype.Component;

@Component("hashProvider")
public class SHA1Provider implements HashProvider{
    
    public String computeHash(String s) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(s.getBytes("UTF-8"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        byte[] hash = md.digest();

        // add extra zero to the first byte of the array to avoid the problem with negative values (in BigInteger byte array interpretation)
        byte[] t = new byte[hash.length + 1];
        t[0] = 0;
        System.arraycopy(hash, 0, t, 1, hash.length);
        String res = "0000000000000000000000000000000000000000" + new BigInteger(t).toString(16); // pad with zeros from left
        return res.substring(res.length() - 40, res.length()); // cut to 40 chars from right
    }
}
