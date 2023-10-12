import java.util.LinkedList;

//design of HashTable
// KeyValuePair represents a key-value pair to store in the hash table.
class KeyValuePair<K, V> {
    K key;
    V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class SimpleHashTable<K, V> {
    private int size; // The size of the hash table, i.e., the number of buckets.
    private LinkedList<KeyValuePair<K, V>>[] table; // An array of linked lists to store key-value pairs.

    // Constructor to initialize the hash table with a specified size.
    public SimpleHashTable(int size) {
        this.size = size;
        table = new LinkedList[size];
    }

    // Private helper method to calculate the index (bucket) for a given key.
    private int hashFunction(K key) {
        // Implement your custom hash function here.
        // Return an integer index within the range [0, size - 1].
        // Example: return key.hashCode() % size;
        return 0;
    }

    // Insert a key-value pair into the hash table.
    public void put(K key, V value) {
        int index = hashFunction(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        // Check if the key already exists in the bucket.
        // If the key exists, update its associated value.
        // If the key doesn't exist in the bucket, add a new key-value pair.
    }

    // Retrieve the value associated with a given key.
    // Returns null if the key is not found in the hash table.
    public V get(K key) {
        int index = hashFunction(key);

        if (table[index] != null) {
            // Search for the key in the linked list at the computed index.
            // If found, return the associated value.
        }

        // Key not found in the hash table.
        return null;
    }

    // Remove a key-value pair from the hash table.
    public void remove(K key) {
        int index = hashFunction(key);

        if (table[index] != null) {
            // Search for the key in the linked list at the computed index.
            // If found, remove the key-value pair.
        }
    }

    // Display the content of the hash table for debugging purposes.
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.print("Index " + i + ": ");
            if (table[i] != null) {
                // Iterate through the linked list at the current index.
                // Print the key-value pairs.
            }
            System.out.println();
        }
    }
}
