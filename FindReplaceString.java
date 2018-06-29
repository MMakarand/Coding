public class FindReplaceString {

    public static  void main(String[] args)
    {
        String S ="ejvzndtzncrelhedwlwiqgdbdgctgubzczgtovufncicjlwsmfdcrqeaghuevyexqdhffikvecuazrelofjmyjjznnjdkimbklrh";// "abcd";
        int [] indexes ={25,35,60,77,69,79,15,19,58,92,27,64,4,11,51,7,72,67,30,0,74,98,17,85,48,32,38,62,43,2,9,55,87};// {0,2};
        String[] sources =
                {"gc","tov","vy","re","ikv","lo","dw","iqgdbd","ue","kimbk","tgu","qd","ndt","elhe","crq","zn","ec","ff","bz","ej","ua","rh","lw","jj","mfd","cz","ufn","ex","cjl","vz","cr","agh","znnj"};
        String[] targets = {"dxs","hn","vfc","wnr","tira","rko","oob","mlitiwj","zrj","onpp","ot","c","lm","hbsje","dgc","ruf","qi","h","vzn","ja","ow","te","km","imq","pia","ixp","xsod","m","eat","yf","lzu","dgy","dyrsc"};

//"jayflmruflzuhbsjeoobkmmlitiwjdxsotvznixpghnxsodcieatwspiadgcedgyzrjvfcmchhtiraqiowzwnrrkofjmyimqdyrscdonpplte"
        FindReplaceString obj = new FindReplaceString();
        System.out.println(obj.findReplaceString(S,indexes,sources,targets));
    }

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets)
    {

        StringBuilder sb = new StringBuilder(S);
        for(int i=0; i < indexes.length; i++)
        {
            String source = sources[i];
            String target = targets[i];

            String tString = S.substring(indexes[i]);
            if(tString.indexOf(source) == 0)
            {
                int indexInSb = -1;
                 //do{
                indexInSb = sb.indexOf(source);
                if(indexInSb != -1)
                {
                    String suffix = sb.substring(indexInSb+source.length(),sb.length());
                    sb.delete(indexInSb,sb.length());
                    //sb.replace(indexInSb,indexInSb+source.length(),target);

                    sb.append(target);
                    sb.append(suffix);
                }

                //}while(indexInSb != -1);

            }
        }
        return new String(sb);
    }

}
