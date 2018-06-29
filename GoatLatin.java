public class GoatLatin {

    public static void main(String[] args)
    {
        GoatLatin gc = new GoatLatin();
        String ip = "The quick brown fox jumped over the lazy dog";
        System.out.println(gc.toGoatLatin(ip));
    }
    public String toGoatLatin(String S) {
        String[] arr = S.split(" ");
        String op = "";

        for(int i=0; i<arr.length; i++)
        {
            arr[i] = convertToGoatLatin(arr[i], i+1);
            op += arr[i];
        }
        return op;
    }


    public String convertToGoatLatin(String str, int index)
    {
        String op = null;

        char [] aArray = new char[index];
        for(int i=0; i < index; i++)
        {
            aArray[i] = 'a';
        }

        if(str.charAt(0) == 'a' || str.charAt(0) == 'e' || str.charAt(0) == 'i' || str.charAt(0) == 'o' || str.charAt(0) == 'u'
                || str.charAt(0) == 'A' || str.charAt(0) == 'E' || str.charAt(0) == 'I' || str.charAt(0) == 'O' || str.charAt(0) == 'U' )
        {
            str += "ma" + new String(aArray)+ " ";
            System.out.println(str);
            return str;
        }
        else{
                char ch = str.charAt(0);
                str = str.substring(1,str.length()) + ch+ "ma" +  new String(aArray) + " ";
                System.out.println(str);
                return str;
            }
    }

}
