class StreamChecker {
    class TrieNode {
        boolean isRoot;
        TrieNode[] children = new TrieNode[26];
    }
    
    TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();
    
    public StreamChecker(String[] words) {
        createTrie(words);
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        for (int i = sb.length()-1; i>=0 && node != null; i--) {
            char c = sb.charAt(i);
            node = node.children[c - 'a'];
            if (node != null && node.isRoot) {
                return true;
            }
        }
        
        return false;
    }
    
    public void createTrie(String[] words) {
        for (String s: words) {
            TrieNode node = root;
            int len = s.length();
            
            for (int i = len-1; i >= 0; i--) {
                char c = s.charAt(i);
                if (node.children[c - 'a'] == null)
                    node.children[c-'a'] = new TrieNode();
                
                node = node.children[c-'a'];
            }
            
            node.isRoot = true;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */