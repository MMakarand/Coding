import java.io.IOException;
import java.util.ArrayList;

import static sun.font.AttributeValues.getMask;

public class MinimumUniqueWordSequenceBitwise {
    ArrayList<Integer> ditctionary = new ArrayList<Integer>();
    int n = 0;
    int bn = 0;
    int b = 1;
    int smallesMask = 0;
    int maskLength = Integer.MAX_VALUE;


//it gives count of all ones + count of group of zeroes (each group is counted as 1)
    private  int abbrLen(int mask) {
        int count = 0;
        for (int b = 1; b < bn;) {
            if ((mask & b) == 0)
                for (; b < bn && (mask & b) == 0; b <<= 1);
            else b <<= 1;
            count ++;
        }
        return count;
    }

    //it gives just count of ones
    private  int abbrLen2(int mask) {
        int count = 0;
        while(mask != 0)
        {
            count++;
            mask = mask & (mask-1);
        }
        return count;
    }

    private void getMask(int mask, int b) {
        int len = abbrLen2(mask);
        if (len >= maskLength)
            return;

        boolean isMask = true;

        for (int i : ditctionary) {
            if ((mask & i) == 0) {
                isMask = false;
                break;
            }
        }

        if (isMask) {
            maskLength = len;
            smallesMask = mask;
        } else {

            for (int bit = b; bit < bn; bit <<= 1) {
                getMask(mask + bit, bit);
            }
        }

    }

    private String minAbbreviation(String word, String[] dict) {
        n = word.length();
        bn = 1 << n;

        int num = 0;

        for (String str : dict) {
            num = 0;
            if (str.length() != n)
                continue;

            for (int i = n - 1; i >= 0; i--) {
                if (word.charAt(i) != str.charAt(i)) {
                    num += (1 << (n - 1 - i));
                }
            }

            ditctionary.add(num);

        }

        /*
        for(int i : ditctionary)
        {
            System.out.println(Integer.toBinaryString(i));
        }*/

            //find smallest mask which gives abbreviation and does not match with any word in dictionary
            //get mask
            getMask(smallesMask, b);


            System.out.println(smallesMask);

            //reconstruct word abbreviation which matches least with dictionary word abbreviations
            String res = "";
            //reconstruct small
            // es abbreviation
// Reconstruct abbreviation from bit sequence
            for (int i = n - 1, pre = i; i >= 0; --i, smallesMask >>= 1) {
                if ((smallesMask & 1) != 0) {
                    if (pre - i > 0) res = Integer.toString(pre - i) + res;
                    pre = i - 1;
                    res = word.charAt(i) + res;
                } else if (i == 0) res = Integer.toString(pre - i + 1) + res;
            }
            return res;

        }

    public static void main(String[] args) throws IOException {
        MinimumUniqueWordSequenceBitwise obj = new MinimumUniqueWordSequenceBitwise();
        String word = "apple";
        String[] dict = {"blade"};//,"lyftcode","leetcold","litecode","lietcode","leetccod","lyftcold"};

        System.out.println(obj.minAbbreviation(word, dict));
    }
    }




