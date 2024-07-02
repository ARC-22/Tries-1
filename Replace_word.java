// Time Complexity : O(k*l) + O(m*n) // dictionaty words + avg len, sentance words + avg len 
// Space Complexity : O(K*l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    class TrieNode{
        boolean isEnd;
        TrieNode [] children;
        public TrieNode(){
            isEnd = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;

    private void insert(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        if(sentence == null || sentence.length() == 0){
            return sentence;
        }
        root = new TrieNode();
        
        //Store each word of dictionary in trie
        for(String word: dictionary){
            insert(word);
        }

        String[] strArray = sentence.split(" ");
        StringBuilder ans = new StringBuilder();

        //Iterate over each word of sentence
        for(int i = 0; i< strArray.length; i++){
            String word = strArray[i];
            TrieNode curr = root;
            StringBuilder sb = new StringBuilder();
            if(i!=0){
                ans.append(" ");
            }
            for (int j =0; j<word.length(); j++){
                char c = word.charAt(j);   
                if(curr.children[c - 'a'] == null || curr.isEnd == true){
                    break;
                }
                sb.append(c);
                curr = curr.children[c - 'a'];
            }

            if(curr.isEnd){
                ans.append(sb);
            }
            else{
                ans.append(word);
            }
        }
        return ans.toString();   
    }
}