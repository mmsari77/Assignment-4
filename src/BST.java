
import java.util.*;

public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }


    public static class Entry<K1, V1> {
        private K1 key;
        private V1 value;

        public Entry(K1 key, V1 value) {
            this.key = key;
            this.value = value;
        }

        public K1 getKey() {
            return key;
        }

        public V1 getValue() {
            return value;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
        size++;
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
            size--; // since we are updating, not adding a new node
        }
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.val;
    }

    private Node get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node;
    }

    public void delete(K key) {
        if (get(key) != null) {
            root = delete(root, key);
            size--;
        }
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;

            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public Iterable<Entry<K, V>> iterator() {
        List<Entry<K, V>> entries = new ArrayList<>();
        inOrder(root, entries);
        return entries;
    }

    private void inOrder(Node node, List<Entry<K, V>> entries) {
        if (node == null) return;
        inOrder(node.left, entries);
        entries.add(new Entry<>(node.key, node.val));
        inOrder(node.right, entries);
    }
}