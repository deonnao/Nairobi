package myCompiler;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.ArrayList;


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
	public ArrayList<K> keySet() {
		Entry<K,V>[] tab = table;
		ArrayList<K> keys = new ArrayList<>();
		int i = capacity -1;
		while (i > -1) {
			for (Entry<K,V> e = tab[i]; e != null; e = e.next ) {
				if (e != null){
					keys.add(e.key);
				}
			}
			i--;
		}

		return keys;
	}
	public ArrayList<V> valueSet() {
		Entry<K,V>[] tab = table;
		ArrayList<V> values = new ArrayList<>();
		int i = capacity -1;
		while (i > -1) {
			for (Entry<K,V> e = tab[i]; e != null; e = e.next ) {
				//if (e.key != null){
					values.add(e.value);
				//}
			}
			i--;
		}

		return values;
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
	   public boolean remove(K key) {
		   int index = calculateIndex(key);
		   int hash = key.hashCode();
		   Entry<K,V>[] tab = table;
		   for (Entry<K,V> e = tab[index]; e != null; e = e.next ) {
			   if((e.key.hashCode() == hash) && e.key.equals(key) ) {
				   e.value = null;
				   e.key = null;
				   //true for removed
				   return true;
			   }
		   }
		   //False if fails to remove
		   return false;
		   
		   
		   
	   }

	   
	    private int calculateIndex(K key) {
	        // Calculate the index using a hash function.
	        int hashCode = key.hashCode();
	        return (hashCode & 0x7FFFFFFF) % capacity;
	    }
}
public class Hasher {
	public static void main(String[] args) {
		MyHashTable<String,String> hash = new MyHashTable<>(10); //Add a capacity
		hash.put("greeting","String");
		hash.put("12","int");
		hash.put("10.12","float");
		hash.put("abc","char");
		hash.put("true","boolean");
		
		System.out.print(hash.keySet());
		System.out.print(hash.valueSet());
		System.out.print(hash.changeValue("greeting","char"));
		System.out.print(hash.getValue("greeting"));
		System.out.print(hash.containsKey("12"));
				
		
	}
}
