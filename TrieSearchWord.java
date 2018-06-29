public class TrieSearchWord {




        class TrieNode{

            public TrieNode[] child;
            public boolean isEnd;

            public TrieNode()
            {
                child = new TrieNode[26];
                isEnd = false;
            }
        };


        class Trie
        {
            TrieNode root;
            public Trie()
            {
                root =  new TrieNode();
            }

            public void insert(String word)
            {
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

            public boolean search(String word)
            {
                TrieNode temp  = root;

                for(int i=0; i < word.length(); i++)
                {
                    if(temp.child[word.charAt(i) - 'a'] == null)
                    {
                        return false;
                    }
                    temp = temp.child[word.charAt(i) - 'a'];
                }
                if(temp.isEnd == true)
                    return true;

                return false;
            }

        };


        public String longestWord(String[] words) {
            Trie t = new Trie();
            for(int i=0; i < words.length; i++)
            {
                t.insert(words[i]);
            }

            String max = "";
            for(int i=0; i < words.length; i++)
            {
                String curr = words[i];
                int start = 0, end = start+1;
                while(end <= curr.length())
                {
                    if(!t.search(curr.substring(start,end)))
                        break;

                    end++;
                }
                if(end == curr.length()+1)
                {
                    if(end > max.length())
                        max = curr;
                }
            }
            return max;
        }

        public static void main(String[] args)
        {
            TrieSearchWord obj = new TrieSearchWord();
            String []words = {"w","wo","wor"}; //{"w","wo","wor","worl","world"};
            String op = obj.longestWord(words);
            System.out.println(op);
        }
    }

