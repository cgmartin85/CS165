// HashTable.java - code for hashing assignment
// Author: ?????
// Date:   ?????
// Class:  CS165
// Email:  ?????

import java.util.*;

/**
 * Implementation of a Hash Table using chaining for collision resolution.
 */
public class HashTable implements IHash {
    // Method of hashing
    /** The hash function used by this table. */
    HashFunction hasher;
    // Hash table : an ArrayList of "buckets", which store the actual strings
    /** The underlying data structure for the hash table, an ArrayList of Lists. */
    ArrayList<List<String>> hashTable;
    /**
     * Number of Elements
     */
    int numberOfElements;
    /** The size of the hash table (number of buckets). */
    int size;

    /**
     * Initializes a new instance of HashTable.
     * <p>
     * Initialize the instance variables. <br>
     * Note: when initializing the hashTable, make sure to allocate each entry in the HashTable
     *       to a new a HashBucket or null, your choice.
     * @param size   the size of the hashTable
     * @param hasher the type of hashing function
     */
    public HashTable(int size, HashFunction hasher) {
        this.hasher = hasher;
        hashTable = new ArrayList<List<String>>();
        numberOfElements = 0;
        this.size = size;
        
        for (int i = 0; i < size(); i++) {
        	hashTable.add(new LinkedList<String>());
        }
    }

    /**
     * Inserts a key into the hash table.
     *
     * @param key The key to insert.
     * @return true if the key was inserted, false if it already exists.
     */
    public boolean insert(String key) {
        int hash = hasher.hash(key) % size();
        if (!hashTable.get(hash).contains(key)) {
        	hashTable.get(hash).add(key);
        	numberOfElements++;
        	return true;
        }
        return false;
    }

    /**
     * Removes a key from the hash table.
     *
     * @param key The key to remove.
     * @return true if the key was removed, false if it was not found.
     */
    public boolean remove(String key) {
        int hash = hasher.hash(key) % size();
        if (hashTable.get(hash).contains(key)) {
            hashTable.get(hash).remove(key);
            numberOfElements--;
            return true;
        }
        return false;
    }

    /**
     * Searches for a key in the hash table.
     *
     * @param key The key to search for.
     * @return The key if found, null otherwise.
     */
    public String search(String key) {
        int hash = hasher.hash(key) % size();
        if (hashTable.get(hash).contains(key)) {
            return key;
        }
        return null;
    }

    /**
     * Returns the size of the hash table (number of buckets).
     *
     * @return The number of buckets in the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the number of elements in a specific bucket.
     *
     * @param index The index of the bucket.
     * @return The number of elements in the bucket at the given index.
     */
    public int size(int index) {
	if (index >= 0 && index < size) {
            return hashTable.get(index).size();
        }
        return 0;
    }

    // Return iterator for the entire hashTable,
    // must iterate all hashBuckets that are not empty
    /**
     * Iterator for the HashTable. Iterates over all elements in the table.
     */
    public class HashIterator implements Iterator<String> {
        // The current index into the hashTable 
        private int currentIndex;
        // The current iterator for that hashBucket
        private Iterator<String> currentIterator = null;

        /**
         * Constructs a new HashIterator.
         */
        HashIterator() {
            currentIndex = 0;
            while (currentIndex < size && hashTable.get(currentIndex).isEmpty()) {
                currentIndex++;
            }
            if (currentIndex < size) {
                currentIterator = hashTable.get(currentIndex).iterator();
            }
        }


        /**
         * Returns the next element in the iteration.
         *
         * @return The next element.
         */
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String next = currentIterator.next();
            if (!currentIterator.hasNext()) {
                currentIndex++;
                while (currentIndex < size && hashTable.get(currentIndex).isEmpty()) {
                    currentIndex++;
                }
                if (currentIndex < size) {
                    currentIterator = hashTable.get(currentIndex).iterator();
                } else {
                    currentIterator = null;
                }
            }
            return next;
        }

        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise.
         */
        public boolean hasNext() {
            return currentIterator != null && currentIterator.hasNext();
        }
    }

    // Return an iterator for the hash table
    /**
     * Returns an iterator over the elements in the hash table.
     *
     * @return An Iterator over the strings in the hash table.
     */
    public Iterator<String> iterator() {
        return new HashIterator();
    }

    /**
     * Does not use the iterator above. Iterates over one bucket.
     *
     * @param index the index of bucket to iterate over
     * @return an iterator for that bucket
     */
    public Iterator<String> iterator(int index) {
        List<String> bucket = hashTable.get(index);
        return bucket != null ? bucket.iterator() : null;
    }

    // Prints entire hash table.
    // NOTE: This method is used extensively for testing.
    /**
     * Prints the contents of the hash table to the debug output.
     */
    public void print() {
        Debug.printf("HashTable size: " + size());

        for (int index = 0; index < hashTable.size(); index++) {
            List<String> list = hashTable.get(index);
            if (list != null) {
                Debug.printf("HashTable[%d]: %d entries", index, list.size());
                for (String word : list) {
                    Debug.printf("String: %s (hash code %d)", word, hasher.hash(word));
                }
            }else {
                Debug.printf("HashTable[%d]: %d entries", index, 0);
            }
        }
    }
}
