
/**
 * Strie node is used to build a strie. Each node represents a character.
 * and has up to 26 child nodes.
 *
 * @author Li 7wak
 */
public class StrieNode {

    /**
     * Number of children for each node.
     */
    private static final int NUM_CHILDREN = 26;

    /**
     * Array to hold children nodes.
     */
    private StrieNode[] children = new StrieNode[NUM_CHILDREN];

    /**
     * Marks the end of a word.
     */
    private boolean endMarker;

    /**
     * Marks a character/child-node as 'removed' if this node/char denoted the
     * end of a word before and that word has been removed by a remove()
     * operation.
     */
    private boolean removed;

    /**
     * Counts how many child the node has.
     */
    private int numChild;

    /**
     * Create a new empty node.
     */
    public StrieNode() {
        removed = false;
        endMarker = false;
        numChild = 0;
    }

    /**
     * Mark the node as removed.
     */
    public void setRemoved() {
        removed = true;
    }

    /**
     * Mark as not removed.
     */
    public void unsetRemoved() {
        removed = false;
    }

    /**
     * Check if node is removed.
     *
     * @return True if removed, otherwise false
     */
    public boolean isRemoved() {
        return removed;
    }

    /**
     * Check if node has the child character.
     *
     * @param ch Character to check if it is in the node
     * @return True if character is found on this node, otherwise false
     */
    public boolean containsChar(char ch) {
        int index = ch - 'a';
        return children[index] != null && !children[index].isRemoved();
    }

    /**
     * Return the child node that holds the given character.
     *
     * @param ch Target character
     * @return Node that holds the character
     */
    public StrieNode getChild(char ch) {
        return children[ch - 'a'];
    }

    /**
     * Assign a child to this node.
     *
     * @param ch Character to add
     * @param node Node of the character
     */
    public void putChild(char ch, StrieNode node) {
        children[ch - 'a'] = node;
        numChild++;
    }

    /**
     * Return all the children of this node.
     *
     * @return List of children nodes
     */
    public StrieNode[] getAllChildren() {
        return children;
    }

    /**
     * Return the number of children of this node.
     *
     * @return Number of children
     */
    public int getNumChildren() {
        return children.length;
    }

    /**
     * Mark this node as an end marker that forms a word.
     */
    public void setEnd() {
        endMarker = true;
    }

    /**
     * Mark this node as not an end marker.
     */
    public void unsetEnd() {
        endMarker = false;
    }

    /**
     * Check if this node is an end marker.
     *
     * @return True if end marker, otherwise false
     */
    public boolean isEnd() {
        return endMarker;
    }

    /**
     * Check if this is an empty node.
     *
     * @return True if empty otherwise false
     */
    public boolean isEmptyNode() {
        return numChild == 0;
    }
}
