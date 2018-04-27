public class ReverseVowelsString {
    public String reverseVowels(String s) {

        int i = 0, j = s.length()-1;
        char[] tArr = s.toCharArray();
        while(i < j)
        {
            while(!isVowel(tArr[i]))
                i++;
            while(!isVowel(tArr[j]))
                j--;

            if(i < j && tArr[i] != tArr[j])
            {
                swap(tArr,i++,j--);
            }
            else{
                i++;j--;
                }
        }
        return new String(tArr);
    }
    private void swap(char[] tArr, int i, int j) {
        char ch = tArr[i];
        tArr[i] = tArr[j];
        tArr[j] = ch;
    }

    private boolean isVowel(char c) {
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        ReverseVowelsString rv = new ReverseVowelsString();
      //  String s = "hello";
        //System.out.println(rv.reverseVowels(s));

        String s = "leetcode";
        System.out.println(rv.reverseVowels(s));
    }
}
