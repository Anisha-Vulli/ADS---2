/**
 * Class for trie st.
 */
class TrieST {
    /**
     * extended ASCII.
     */
    private static final int R = 26;
    /**
     * root of trie.
     */
    private Node root;
    /**
     * number of keys in trie.
     */
    private int N;

    // R-way trie node

    /**
     * Class for node.
     */
    private static class Node {
        private Node[] next = new Node[R];
        private boolean isString;
    }

    /**
     * Initializes an empty set of strings.
     */
    protected TrieST() {
    }

    /**
     * Does the set contain the given key?
     * @param key the key
     * @return <tt>true</tt> if the set contains
     * <tt>key</tt> and
     *     <tt>false</tt> otherwise
     * @throws NullPointerException if
     * <tt>key</tt> is <tt>null</tt>
     * 
     *
     * Time complexity is O(1)
     */
    public boolean contains(final String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return false;
        }
        return x.isString;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param x [description]
     * @param key [description]
     * @param d [description]
     * @return [description]
     */
    private Node get(final Node x, final String key, final int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = Character.toUpperCase(key.charAt(d));
        return get(x.next[c - 'A'], key, d + 1);
    }

    /**
     * Adds the key to the set if it is not
     * already present.
     * @param key the key to add
     * @throws NullPointerException if
     * <tt>key</tt> is <tt>null</tt>
     * 
     *
     * Time complexity is O(N)
     */
    public void add(final String key) {
        root = add(root, key, 0);
    }
    /**
    * Time complexity is O(1)
    **/
    private Node add(final Node x, final String key, final int d) {
        Node y = x;
        if (y == null) {
            y = new Node();
        }
        if (d == key.length()) {
            if (!y.isString) {
                N++;
            }
            y.isString = true;
        } else {
            char c = Character.toUpperCase(key.charAt(d));
            y.next[c - 'A'] = add(y.next[c - 'A'], key, d + 1);
        }
        return y;
    }

    /**
     * Returns the number of strings in the set.
     * @return the number of strings in the set
     * Time complexity is O(1)
     */
    public int size() {
        return N;
    }

    /**
     * Is the set empty?
     * @return <tt>true</tt> if the set is empty,
     * and <tt>false</tt> otherwise
     * Time complexity is O(1)
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all of the keys in the set, as an iterator.
     * To iterate over all of the keys in a set
     * named <tt>set</tt>, use the
     * foreach notation: <tt>for (Key key : set)</tt>.
     * @return an iterator to all of the keys in the set
     * Time complexity is O(1)
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * Returns all of the keys in the set that start
     * with <tt>prefix</tt>.
     * @param prefix the prefix
     * @return all of the keys in the set that start
     * with <tt>prefix</tt>,
     *     as an iterable
     *     Time complexity is O(N)
     */
    public Iterable<String> keysWithPrefix(final String prefix) {
        Queue<String> results = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }
    /**
    * Time Complexity is O(N)
    **/
    private void collect(final Node x, final StringBuilder prefix,
        final Queue<String> results) {
        if (x == null) {
            return;
        }
        if (x.isString) {
            results.enqueue(prefix.toString());
        }
        for (char c = 'A'; c < 'A' + R; c++) {
            prefix.append(c);
            collect(x.next[c - 'A'], prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns all of the keys in the set that match <tt>pattern</tt>,
     * where . symbol is treated as a wildcard character.
     * @param pattern the pattern
     * @return all of the keys in the set that match <tt>pattern</tt>,
     *     as an iterable, where . is treated as a wildcard character.
     *     Time complexity is O(N)
     */
    public Iterable<String> keysThatMatch(final String pattern) {
        Queue<String> results = new Queue<String>();
        StringBuilder prefix = new StringBuilder();
        collect(root, prefix, pattern, results);
        return results;
    }
    /**
    * Time complexity is O(1)
    **/
    private void collect(final Node x, final StringBuilder prefix,
                         final String pattern,
                         final Queue<String> results) {
        if (x == null) {
            return;
        }
        int d = prefix.length();
        if (d == pattern.length() && x.isString) {
            results.enqueue(prefix.toString());
        }
        if (d == pattern.length()) {
            return;
        }
        char c = Character.toUpperCase(pattern.charAt(d));
        if (c == '.') {
            for (char ch = 'A'; ch < 'A' + R; ch++) {
                prefix.append(ch);
                collect(x.next[ch - 'A'], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c - 'A'], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    /**
     * Returns the string in the set that is the longest
     * prefix of <tt>query</tt>,
     * or <tt>null</tt>, if no such string.
     * @param query the query string
     * @throws NullPointerException if <tt>query</tt> is
     * <tt>null</tt>
     * @return the string in the set that is the longest
     * prefix of <tt>query</tt>,
     *     or <tt>null</tt> if no such string
     *     Time complexity is O(1)
     */
    public String longestPrefixOf(final String query) {
        String str = query;
        int length = longestPrefixOf(root, str, 0, -1);
        if (length == -1) {
            return null;
        }
        return str.substring(0, length);
    }

    /**
     * returns the length of the longest string key in the subtrie.
     * rooted at x that is a prefix of the query string,.
     * assuming the first d character match and we have already
     * found a prefix match of length length
     *
     * Time Complexity is O(N).
     * @param      x       { parameter_description }
     * @param      query   The query
     * @param      d       { parameter_description }
     * @param      length  The length
     *
     * @return     { description_of_the_return_value }
     */
    private int longestPrefixOf(final Node x, final String query,
        final int d, final int length) {

        Node y = x;
        String str = query;
        int a = d;
        int len = length;
        if (y == null) {
            return len;
        }
        if (y.isString) {
            len = a;
        }
        if (a == str.length()) {
            return length;
        }
        char c = Character.toUpperCase(str.charAt(a));
        return longestPrefixOf(y.next[c - 'A'], str, a + 1, len);
    }

    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws NullPointerException if <tt>key</tt>
     * is <tt>null</tt>
     * Time complexity is O(N)
     */
    public void delete(final String key) {
        String str = key;
        root = delete(root, str, 0);
    }
    /**
    * Time complexity is O(N)
    **/
    private Node delete(final Node x, final String key, final int d) {
        Node y = x;
        String str = key;
        int a = d;
        if (y == null) {
            return null;
        }
        if (a == key.length()) {
            if (y.isString) {
                N--;
            }
            y.isString = false;
        } else {
            char c = str.charAt(a);
            y.next[c - 'A'] = delete(y.next[c - 'A'], str, a + 1);
        }

        // remove subtrie rooted at x if it is completely empty
        if (y.isString) {
            return x;
        }
        for (int c = 'A'; c < 'A' + R; c++) {
            if (y.next[c - 'A'] != null) {
                return y;
            }
        }
        return null;
    }
    /**
     *  Time complexity is O(1)
    **/
    public boolean hasPrefix(final String query) {
        Node x = get(root, query, 0);
        return x != null;
    }
}