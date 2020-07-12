class TrieNode {
    /*Trie 树是一个有根的树，其结点具有以下字段：。

    最多 RR 个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
    本文中假定 RR 为 26，小写拉丁字母的数量。
    布尔字段，以指定节点是对应键的结尾还是只是键前缀

    */
    // R links to node children
    private TrieNode[] links; 

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch -'a'] != null;
    }
    public TrieNode get(char ch) {
        return links[ch -'a'];
    }
    public void put(char ch, TrieNode node) {
        links[ch -'a'] = node;
    }
    public void setEnd() {
        isEnd = true;
    }
    public boolean isEnd() {
        return isEnd;
    }
}


class Trie {
    private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            if(!node.containsKey(word.charAt(i))){
                node.put(word.charAt(i), new TrieNode());
            }
            node = node.get(word.charAt(i));
        }
        node.setEnd();
    }
    
    public TrieNode searchPrefix(String word){
        TrieNode node = root;
        for(int i = 0; i < word.length(); i++){
            if(node.containsKey(word.charAt(i))){
                node = node.get(word.charAt(i));
            }
            else{
                return null; //
            }
        }
        return node;
    }
    /** Returns if the word is in the trie. */
    /*每个键在 trie 中表示为从根到内部节点或叶的路径。我们用第一个键字符从根开始，。检查当前节点中与键字符对应的链接。*/
    public boolean search(String word) {
        TrieNode node = searchPrefix(word); //要查看返回的TrieNode是不是null，且是否已经到了end
        return node != null && node.isEnd();
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;  //只是检是不是前缀，所以不用看是否到了end
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
