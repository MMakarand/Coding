public class ReverseString2 {

    public String reverseStr(String s, int k) {

        int i=0;
        int len = s.length();

        while(i+(2*k) <= len)
        {
            String str = reverseString1(s.substring(i,i+k))+s.substring(i+k, i+2*k);
           // s.replace(s.substring(i,i+2*k), str);
            s = s.substring(0,i) + str + s.substring(i+ 2*k, len);
            i += 2*k;
        }

        if(len - i < k ) {

            String str =  reverseString1(s.substring(i, len));
            s = s.substring(0, i) + str;

        }
        if((len - i) < (2 * k) && (len - i) >= k) {
            String str =reverseString1(s.substring(i, i + k));
            s = s.substring(0, i) + str + s.substring(i+k, len);
        }
        return s;
    }
    public String reverseString1(String s) {
        int len = s.length();

        if(len==0 || len==1)
            return s;

        int mid = len/2;
        char[] tArr = s.toCharArray();
        for(int i=0; i<mid;i++)
        {
            swap(tArr,i, len-i-1 );
        }
        return new String(tArr);
    }
    private void swap(char[] tArr, int i, int j) {
        char ch = tArr[i];
        tArr[i] = tArr[j];
        tArr[j] = ch;
    }

    public static void main(String[] args)
    {
        ReverseString2 rv2 = new ReverseString2();

        String s = "abcd";
        int k = 2;
        System.out.println(rv2.reverseStr(s,k));
    }
}
