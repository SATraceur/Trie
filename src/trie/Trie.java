package trie;
/*
    Recursive word search method partly obtained from, 
    https://github.com/bburns/code-lemmas/blob/master/src/main/java/lemmas/ds/Trie.java
*/
import java.util.*;

public class Trie{

    private TrieNode root = new TrieNode();

    public TrieNode insert(String str, TrieData data) {  
        char arr[] = str.toCharArray();                // Split string into array of characters
        TrieNode node = root;
        
        for (int i = 0; i < str.length(); i++){        // for each character in the current word...
            if(node.getChild(arr[i]) == null) {        // if the current node does not contain the appropriate children
                node.addChild(arr[i], new TrieNode()); // add the current character of string as a child
                node.addChildKey(arr[i]);              // store the keys of all children in parent node
            } 
            node = node.getChild(arr[i]);              // navigate to the child 
        }   
        node.addData(data);                            // add the word related data to the terminal node
        node.declareTerminal();                        // set the last inserted node as the terminal node
        return node;
    }
    
    public TrieNode getNode(String str) {
        char arr[] = str.toCharArray();         
        TrieNode node = root;
        
        for (int i = 0; i < str.length(); i++){                // for each character in the string...
            if(node.getChild(arr[i]) == null) { return null; } // if the current node does not contain the appropriate children, return null
            else { node = node.getChild(arr[i]); }             // else the appropriate child exists so navigate to the child             
        }             
        return node;
    }

    public TrieNode get(String str) {        
        TrieNode node = getNode(str);
        return (node != null && node.isTerminal() ? node : null);             // if final node was found and it's terminal, return node
    }                                                                         // else return null         
    
    public List<String> getAlphabeticalListWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        List<Character> keys = new ArrayList<>();  
        TrieNode N = getNode(prefix);                                         // Move to node at end of prefix
        
        if(N != null) {
            if(N.isTerminal()){ words.add(prefix); }                          // If the prefix is a word, add it to list                                               
            for (int i = N.childNumber() - 1; i >= 0; i--) {                  // For each child of the current node
                keys.addAll(N.getChildKeys());                                // Retreive list of keys for all children of current node
                String updatedPrefix = prefix.concat(keys.get(i).toString()); // Append current key(letter) to current prefix and store as updatedPrefix
                words.addAll(getAlphabeticalListWithPrefix(updatedPrefix));   // Recursivly traverse tree storing complete words in list
            }
        }
        Collections.sort(words);
        return words;
    }
    
    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
     * occurring word represented in the trie (according to the dictionary file)
     * that begins with the provided prefix.
     *
     * @param prefix The prefix to search for
     * @return The most frequent word that starts with prefix
     */
    public String getMostFrequentWordWithPrefix(String prefix) {
        return prefix + "bogus";
    }

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Reads in a dictionary from file 
     * and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie readInDictionary(String fileName) {
        return null;
    }
}
