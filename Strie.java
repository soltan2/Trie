package trie;

import java.util.ArrayList;

/**
 * A strie is a tree that stores word such that each letters are re-used. This
 * makes this tree a compacted dictionary of words.
 *
 * @author Hazina drapeau safi 
 */
public class Strie {

    /**
     * Starting node.
     */
    private StrieNode root;

    /**
     * Counts the number of words stored.
     */
    private int size;

    /**
     * Create a new tree.
     */
    public Strie() {
        root = new StrieNode();
        size = 0;
    }

    /**
     * Check if the tree is empty.
     *
     * @param myStrie Tree to check if empty.
     * @return True of tree is empty, otherwise false.
     */
    public static boolean isEmptyStrie(Strie myStrie) {
        return myStrie.size == 0;
    }

    /**
     * Add a new word into the tree.
     *
     * @param word Word to add
     */
    public void insert(String word) {
        // Validate the word
        word = word.toLowerCase();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch < 'a' || ch > 'z') {
                throw new RuntimeException("Invalid character entered. Characters must be between 'a' and 'z'");
            }
        }

        // Insert the word
        StrieNode current = root;

        // Put all characters
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!current.containsChar(ch)) {
                current.putChild(ch, new StrieNode());
            }

            current = current.getChild(ch);
        }

        if (!current.isEnd()) {
            size++;
        }

        current.unsetRemoved();
        current.setEnd();
    }

    /**
     * Check if the word is in the tree.
     *
     * @param word Word to check if in tree.
     * @return True if it is, otherwise false.
     */
    public boolean contains(String word) {
        word = word.toLowerCase();

        StrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch < 'a' || ch > 'z' || !current.containsChar(ch)) {
                return false;
            }

            current = current.getChild(ch);
        }

        return current.isEnd();
    }

    /**
     * Remove a word from the tree.
     *
     * @param word Word to remove
     * @return True if word has been removed, otherwise false
     */
    public boolean remove(String word) {
        word = word.toLowerCase();

        StrieNode current = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (ch < 'a' || ch > 'z' || !current.containsChar(ch)) {
                return false;
            }

            current = current.getChild(ch);
        }

        if (!current.isEnd() || current.isRemoved()) {
            return false;
        }

        current.setRemoved();
        current.unsetEnd();
        size--;

        return true;
    }

    /**
     * Perform a breadth first search traversal of the tree.
     *
     * @param myStrie Tree to be perform breadth first search
     * @return Breath first search traversal
     */
    public static String levelOrderTraversal(Strie myStrie) {
        // Lets build our own queue of characters
        String result = "";
        ArrayList<StrieNode> queue = new ArrayList<>();
        queue.add(myStrie.root);

        while (!queue.isEmpty()) {
            StrieNode node = queue.remove(0);
            StrieNode[] children = node.getAllChildren();

            for (int i = 0; i < children.length; i++) {
                StrieNode childNode = children[i];

                if (childNode != null) {
                    queue.add(childNode);
                    result += ((char) (i + 'a')) + " ";
                }
            }
        }

        return result;
    }

    /**
     * Find all words in the tree.
     *
     * @param myStrie Tree to be traversed.
     * @return List of words.
     */
    public static String[] getStrieWords(Strie myStrie) {
        ArrayList<String> words = new ArrayList<>();

        getStrieWords(myStrie.root, "", words);

        String[] wordsArr = new String[words.size()];
        wordsArr = words.toArray(wordsArr);
        return wordsArr;
    }

    /**
     * Recursively traverse the tree using depth first search to get all tree
     * words.
     *
     * @param current Current node being traversed
     * @param word Currently built word (incomplete)
     * @param words List of words found.
     */
    private static void getStrieWords(StrieNode current, String word, ArrayList<String> words) {
        StrieNode[] children = current.getAllChildren();

        for (int i = 0; i < children.length; i++) {
            StrieNode child = children[i];

            if (child != null) {
                if (child.isEnd()) {
                    words.add(word + ((char) (i + 'a')));
                }

                getStrieWords(child, word + ((char) (i + 'a')), words);
            }
        }
    }

    /**
     * Returns from myStrie all words with prefix query.
     *
     * @param myStrie Tree to find all suffixes
     * @param query The query to find all suffixes
     * @return All suffixes
     */
    public static String[] getAllSuffixes(Strie myStrie, String query) {
        // 
        // query must contain characters between 'a' and 'z'
        // otherwise, throw RuntimeException with message "Invalid character entered. Characters must be between 'a' and 'z' " 
        // if no word is found in myStrie for the given prefix query, throw RuntimeException with message "No suffixes found with the given prefix"
        // example: Your Strie stores these words: tea, ted, teen, teeth, team, med
        //           query: tee
        //           returns: teen, teeth
        // returns words in alphabetical order

        return null;
    }

    /**
     * Given query, returns the longest prefix stored in myStrie.
     *
     * @param myStrie Tree to find longest prefix
     * @param query Query
     * @return Longest prefix
     */
    public static String getLongestPrefix(Strie myStrie, String query) {
        // Given query, returns the longest prefix stored in myStrie 
        // If no prefix can be found, throw RuntimeException with message "No prefix found!"
        // query must contain characters between 'a' and 'z'
        // otherwise, throw RuntimeException with message "Invalid character entered. Characters must be between 'a' and 'z' " 
        // O(n), where n is the number of characters in query
        // Example: Your Strie stores these words: ban, band, banned, banana, can 
        //          query: bandana
        //          returns: band

        return null;
    }

    /**
     * Returns the closest match(es) found in myStrie for the given query.
     *
     * @param myStrie Tree to get closest match
     * @param query Query
     * @return Closest matches
     */
    public static String[] getClosestMatch(Strie myStrie, String query) {
        // Returns the closest match(es) found in myStrie for the given query 
        // query must contain characters between 'a' and 'z'
        // otherwise, throw RuntimeException with message "Invalid character entered. Characters must be between 'a' and 'z' " 
        // closest match rules: return the word(s) with the smallest distance between word_in_myStrie and query
        // 1. length of query == length of word_in_myStrie
        //    distance = charcter mismatches between query and word_in_myStrie at each position (distance(barn, bird) = 2)
        // 2. length of query != length of word_in_myStrie
        //    find substrings by moving a sliding window of length = smaller(query, word_in_myStrie) 
        //    distance_substring = absolute_length_difference(word_in_myStrie, query) + mismatch of characters at each position of substring and query
        //    distance = smallest distance_substring
        //    example: query: bann, word_in_myStrie = banned. absolute_length_difference(word_in_myStrie, query) = 2
        //             substrings: bann, anne, nned
        //             distance_substring(bann, bann) = 2, distance_substring(bann, anne) = 5, distance_substring(bann, nned) = 6
        //             distance(bann, banned) = 2
        // return the words with minimum distance
        // if the minumim cost of the closest match >= length of query, throw RuntimeException with message "Can't suggest a word! No close word found!"
        // Example: your Strie stores these words: barn, ban, banana, banned, bird
        //          query: bann 
        //          returns: ban, barn 
        // returns words in alphabetical order

        return null;
    }

    // --------------------------------------------------------
    // example testing code... edit this as much as you want!
    // --------------------------------------------------------
    /**
     * Entry point of testing.
     *
     * @param args unused arguments
     */
    public static void main(String[] args) {
        Strie myStrie = new Strie();

        if (isEmptyStrie(myStrie) && myStrie.root.isEmptyNode()) {
            System.out.println("Yay 1");
        }

        myStrie.insert("a");
        StrieNode[] children = myStrie.root.getAllChildren();
        if (!isEmptyStrie(myStrie) && children[0].isEmptyNode() && children[0].isEnd() && myStrie.root.containsChar('a')) {
            System.out.println("Yay 2");
        }

        myStrie.insert("bat");
        myStrie.insert("bar");
        myStrie.insert("barn");
        myStrie.insert("cat");

        if (myStrie.contains("cat")) {
            System.out.println("Yay 3");
        }

        String res = Strie.levelOrderTraversal(myStrie).trim();
        String actualOut = "a b c a a r t t n";
        if (res.equals(actualOut)) {
            System.out.println("Yay 4");
        }

        String[] yourWords = Strie.getStrieWords(myStrie);
        String[] allWords = {"a", "bar", "barn", "bat", "cat"};
        int allMatches = 1;
        for (int i = 0; i < yourWords.length; i++) {
            if (!yourWords[i].equals(allWords[i])) {
                allMatches = 0;
            }
        }
        if (allMatches == 1) {
            System.out.println("Yay 5");
        }

        if (myStrie.remove("cat") && !myStrie.contains("cat")) {
            System.out.println("Yay 6");
        }

    }

}
