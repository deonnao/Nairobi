import java.util.Hashtable;
import java.util.LinkedList;


class Entry<K, V> {
	   K key;
	   V value;
	   Entry<K, V> next;

	    public Entry(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }
	}
class MyHashTable<K, V> {
	   private Entry<K, V>[] table;
	   private int capacity;

	   public MyHashTable(int capacity) {
	       this.capacity = capacity;
	       table = new Entry[capacity];
	    }
	   public boolean containsKey(K input) {
		   int index = calculateIndex(input);
		   Entry<K,V> current = table[index];
		   while(current != null) {
			   if (current.key == input){
				   return true;
			   }
			   current = current.next;
		   }
		   return false;
	   }
	public boolean changeValue(K key, V value) {
		   int index = calculateIndex(key);
		   Entry<K,V> current = table[index];
		   while(current != null) {
			   if (current.key == key){
				   current.value = value;
				   //Return true if changed
				   return true;
			   }
			   current = current.next;
		   }
		   //Return false if not found/changed
		   return false;
		   
	   }
	   public void put(K key, V value) {
	       int index = calculateIndex(key);

	       Entry<K, V> newEntry = new Entry<>(key, value);

	       if (table[index] == null) {
	            // If the list is empty, set the new entry as the head.
	           table[index] = newEntry;
	       } else {
	            // Otherwise, insert the new entry at the front of the list.
	           newEntry.next = table[index];
	           table[index] = newEntry;
	       }
	    }
	   public V getValue(K key) {
		   Entry<K,V>[] tab = table;
		   int hash = key.hashCode();
		   int index = calculateIndex(key); 
		   for (Entry<K,V> e = tab[index]; e != null; e = e.next ) {
			   if((e.key.hashCode() == hash) && e.key.equals(key) ) {
				   return (V) e.value;
			   }
		   }
		   return null;
	   }

	   
	    private int calculateIndex(K key) {
	        // Calculate the index using a hash function.
	        int hashCode = key.hashCode();
	        return (hashCode & 0x7FFFFFFF) % capacity;
	    }
}
public class Hasher {
	public static void main(String[] args) {
		
	}
}
