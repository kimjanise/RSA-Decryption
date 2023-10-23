package RSA;

import java.math.BigInteger;
import java.util.ArrayList;
public class RSA {
    
    public static ArrayList<Integer> extEucAlg(int a, int b){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(a == 0){
            ret.add(a);
            ret.add(1);
            ret.add(0);
            return ret;
        }
        ret = extEucAlg(b,a%b);
        ArrayList<Integer> finRet = new ArrayList<Integer>();
        finRet.add(ret.get(0));
        finRet.add(ret.get(2));
        finRet.add(ret.get(1)-((a/b)*ret.get(2)));
        return finRet;
    }

    public static char baseToChar(int x){
        char ret = ' ';
        if (x != 0){
            ret = (char)(64 + x);
        }
        return ret;
    }
    
    public static String toString(int x){
        String ret = "";
        while(x > 0){
            int temp = x%27;
            String newChar = String.valueOf(baseToChar(temp));
            ret = newChar.concat(ret);
            x = x/27;
        }
        return ret;
    }

    public static String decrypt(String pStr, String qStr, String eStr, String cStr) { 
        int p = Integer.parseInt(pStr);
        int q = Integer.parseInt(qStr);
        int e = Integer.parseInt(eStr);
        int c = Integer.parseInt(cStr);

        int n = p * q;
        int totFn = (p-1) * (q-1);
        ArrayList<Integer> eea = extEucAlg(totFn, e);
        int eInv = eea.get(1);
        int decryptedMsg = 1;
        while (eInv > 0){
            if(eInv%2 == 1) {
            	decryptedMsg = (decryptedMsg * c)%n;
                eInv--;
            }
            else {
                c = (c*c)%n;
                eInv = eInv/2;
            }
        }
        return toString(decryptedMsg);
    }
}


