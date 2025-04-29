public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; // default number of chains
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        this.chainArray = new HashNode[M];
        this.size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % M);
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        if (node == null) {
            chainArray[index] = new HashNode<>(key, value);
            size++; // Увеличиваем size только при добавлении нового узла
        } else {
            HashNode<K, V> prev = null;
            while (node != null && !node.key.equals(key)) {
                prev = node;
                node = node.next;
            }
            if (node != null && node.key.equals(key)) {
                node.value = value; // Обновляем значение, size не меняем
            } else {
                prev.next = new HashNode<>(key, value);
                size++; // Увеличиваем size только при добавлении нового узла
            }
        }
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];

        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> node = chainArray[index];
        HashNode<K, V> prev = null;

        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                if (node.value.equals(value)) {
                    return node.key;
                }
                node = node.next;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public int[] getBucketSizes() {
        int[] sizes = new int[M];
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> node = chainArray[i];
            while (node != null) {
                count++;
                node = node.next;
            }
            sizes[i] = count;
        }
        return sizes;
    }
}