public class WordDictionary {
    class TrieNode{

        public TrieNode[] child;
        public boolean isEnd;

        public TrieNode()
        {
            child = new TrieNode[26];
            isEnd = false;
        }
    };
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {

        root =  new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {

        TrieNode temp  = root;

        for(int i=0; i < word.length(); i++)
        {
            if(temp.child[word.charAt(i) - 'a'] == null)
            {
                temp.child[word.charAt(i) - 'a'] = new TrieNode();
            }
            temp = temp.child[word.charAt(i) - 'a'];
        }
        temp.isEnd = true;

    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {


        //Recursive version
        return matchR(word.toCharArray(), 0, root);

        //Interative Version
        /*
         TrieNode temp  = root;
         boolean res = false;
        for(int i=0; i < word.length(); i++)
        {
            if(word.charAt(i) == '.')
            {
                //use backtracking
                String tStr = word.substring(i+1, word.length());
                for(int ii=0; ii < 26; ii++)
                {
                    res = res || searchR(temp.child[ii], tStr);
                    if(res)
                        break;
                }
                return res;
            }
            else if(temp.child[word.charAt(i) - 'a'] == null)
            {
                return false;
            }
            temp = temp.child[word.charAt(i) - 'a'];
        }
        if(temp.isEnd == true)
            return true;

        return false;
        */

    }

    /*public boolean searchR(TrieNode temp, String word)
    {
        boolean res = false;
        if(temp == null)
            return res;

        if(word.length() == 1 && word.charAt(0)=='.')
        {
            // TrieNode r = temp;
            for(int j=0; j<26;j++)
            {
                if(temp.child[j] != null && temp.child[j].isEnd == true)
                    return true;
            }
            return false;
        }
        for(int i=0; i < word.length(); i++)
        {
            if(word.charAt(i) == '.')
            {
                String tStr = word.substring(i+1, word.length());
                for(int ii=0; ii < 26; ii++)
                {
                    res = res || searchR(temp.child[ii], tStr);
                }
                return res;
            }
            else if(temp.child[word.charAt(i) - 'a'] == null)
            {
                return false;
            }
            temp = temp.child[word.charAt(i) - 'a'];
        }
        if(temp.isEnd == true)
            return true;

        return false;

    }*/

    public boolean matchR(char[] word, int index, TrieNode root)
    {
        if(root == null || index >= word.length)
            return false;

        char ch = word[index];

        if(index == word.length - 1)
        {
            if(word[index] != '.' )
            {
                if(root.child[(ch - 'a')].isEnd)
                    return true;

                return false;
            }
            else{
                for(int i=0; i < 26; i++)
                {
                    if(root.child[i] != null && root.child[i].isEnd )
                        return  true;

                }
                return false;
            }
        }

        if(word[index] != '.')
        {
            return ((root.child[ (ch - 'a')] != null) && matchR(word, index+1, root.child[ (ch - 'a')]));
        }
        else{

            for(int i=0; i<26; i++)
            {
                if(matchR(word, index+1, root.child[i]))
                    return true;
            }
            return false;
        }
    }
    public static void main(String[] args)
    {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        obj.addWord("bad");
        boolean res = obj.search("b.");
        System.out.println(res);

        //res = obj.search("b..");
        //System.out.println(res);
    }
}
