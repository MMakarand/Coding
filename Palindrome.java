public class Palindrome {

    public boolean isPalindrome(String s) {

        //s = s.toLowerCase();
        //s = s.replaceAll("[^A-Za-z0-9]","");

        StringBuilder sb = new StringBuilder(s);
        System.out.println(sb);
        //sb.deleteCharAt(0);
        //System.out.println(sb);
       // char[] arr = s.toCharArray();
        int len = sb.length();
        for(int i=0; i < len/2; i++)
        {
            if(sb.charAt(i) != sb.charAt(len-i-1))
                return  false;
        }
        return  true;

    }

    public static void main(String[] args)
    {
        String s = "A man, a plan, a canal: Panama";
        Palindrome p = new Palindrome();
        System.out.println();
        p.isPalindrome(s);
    }
}
