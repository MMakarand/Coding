public class BackspaceStringCompare {

        public boolean backspaceCompare(String S, String T) {
            boolean result= false;

            while(S.contains("#"))
            {
                int start =  S.indexOf('#');
                if(start == 0)
                    S = S.substring(start+1);
                else
                    S = S.substring(0, start-1)+ S.substring(start+1);
            }
            while(T.contains("#"))
            {
                int start =  T.indexOf('#');
                if(start == 0)
                    T = T.substring(start+1);
                else
                    T = T.substring(0, start-1)+ T.substring(start+1);
            }


            return result = S.equals(T);
        }

    public static void main(String[] args)
    {
        BackspaceStringCompare obj = new BackspaceStringCompare();

        String s1 = "ab#c";
        String s2 = "ad#c";
        System.out.println(obj.backspaceCompare(s1,s2));
    }

}
