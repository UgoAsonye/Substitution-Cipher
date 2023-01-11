import java.util.ArrayList;
import java.util.Random;

// transmission.txt holds the plaintext, hiddemesasage.txt is where the encrypted version should be generated.
//hiddenmessage has one charactrer already. This is so it could be uploaded to the moodle.
public class SubstitutionCipher extends Cipher {
    // make Random, and three array lists.
    private Random rangen;
    private ArrayList<Character> Normal = new ArrayList <> ();
    private ArrayList<Character> Abnormal = new ArrayList <> ();
    private ArrayList<Character> Normal2 = new ArrayList <> ();

    public SubstitutionCipher (long key) {
        super(key);
        rangen = new Random(key);
        for (int i = 0; i <= 255; i++) {
            //This loop is for Normal and Normal2 establishing that their bounds are from 0-255 and that i is incremented through the indeces
            Normal.add((char) i);
            Normal2.add((char) i);
        }
        for (int i = 0; i <= 255; i++) {
            //this loop is for Normal2's function. When a value is assigned to an index of Normal2, Abnormal also gets an index and said index is removed from Normal2
            //this way, Normal2 keeps track of which indeces have a value assigned to them.
            int ind = rangen.nextInt(Normal2.size());
            Abnormal.add(Normal2.get(ind));
            Normal2.remove(ind);
        }
    }
        //encrypting: estalish ind of char within normal, replace it with Abnormal.get(ind) for each char in message

    public ArrayList<Character> encrypt (ArrayList<Character> cleartext) {
            ArrayList<Character> ciphertext = Cipher.createList();
            for (int i = 0; i < cleartext.size(); i += 1) {
                //for loop adapted from Caesarcipher but working for Substitution
                char clearchar = cleartext.get(i);
                int cl = (int) clearchar;
                char cipherchar = Abnormal.get(cl);
                ciphertext.add(cipherchar);
            }
            return ciphertext;
        }
        //decrypting: establish ind of char within Abnormal, replace with Normal.get(ind) For each char in message
     public ArrayList<Character> decrypt (ArrayList<Character> ciphertext) {
         ArrayList<Character> cleartext = Cipher.createList();
        for (int i = 0; i < ciphertext.size(); i += 1) {
            //for loop adapted from CaesarCipher, some error here stops a message from being decrypted and instead returns the encrypted value
            char cipherchar = ciphertext.get(i);
            int ct = (int) cipherchar;
            char clearchar = (char) Abnormal.indexOf((char) cipherchar);
            cleartext.add(clearchar);
        }
        return cleartext;
    }

}