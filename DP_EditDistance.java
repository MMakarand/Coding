public class DP_EditDistance {
    //this array stores count of number remove/replace/insert operations needed to convert word1 substring into word2 substring starting fom index
    int count[][];

    public static void main(String[] args)
    {
        DP_EditDistance obj = new DP_EditDistance();
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(obj.minDistance(word1,word2));
    }
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        //count array dimension needs 1 more than length to handle cases where one string has crossed its length
        count = new int[len1+1][len2+1];


        for(int i=0; i<=len1; i++)
        {
            for(int j=0; j<=len2; j++)
            {
                count[i][j] = -1;
            }
        }
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        return getDistance(arr1, 0, arr2,0);

    }

    private int getDistance(char[] arr1, int i, char[] arr2, int j) {
       //if distance is already calculated for substring starting at i for word1 and substring starting at j for word2, return
        if(count[i][j] != -1)
            return count[i][j];

        //if both strings length are already crossed
        if(i >= arr1.length && j >= arr2.length) {
            count[i][j] = 0;
            return 0;
        }
        //if str1 is completely traverssed but str2 still remains
        else if(i >= arr1.length)
        {
            count[i][j] = arr2.length-j;
            return  count[i][j];
        }
        //other way
        else if(j >= arr2.length)
        {
            count[i][j] = arr1.length-i;
            return  count[i][j];
        }

        int remove=0, replace=0, insert=0;
        if(arr1[i] == arr2[j]) {
            count[i][j] = getDistance(arr1, i + 1, arr2, j + 1);
            return count[i][j];
        }

        //if we remove character from position i from string 1
        remove = 1+ getDistance(arr1,i+1,arr2,j);

        //if we replace character at position i in string 1 with character at position j in string 2
        replace = 1+getDistance(arr1, i + 1, arr2, j + 1);

        //if we add character at position j in string 2 at the beginning of string 1
        insert = 1+getDistance(arr1, i , arr2, j + 1);
        count[i][j] = Math.min(Math.min(remove,replace),insert);
         return count[i][j];
    }
}
