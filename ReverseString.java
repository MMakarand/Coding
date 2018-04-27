public class ReverseString {

    public String reverseString(String s) {
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

  /*  public  static void main(String[] args)
    {
        String s = "";
        ReverseString rs = new ReverseString();
        System.out.println(rs.reverseString(s));

        s = "a";
        System.out.println(rs.reverseString(s));

        s = "ab";
        System.out.println(rs.reverseString(s));

        s = "abc";
        System.out.println(rs.reverseString(s));

        s = "abcd";
        System.out.println(rs.reverseString(s));

        s = "1234";
        System.out.println(rs.reverseString(s));
    }*/
}
