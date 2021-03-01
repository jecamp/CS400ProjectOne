import java.util.NoSuchElementException;
import java.util.LinkedList;

// --== CS400 File Header Information ==--
// Name: Kyle Johnson
// Email: kajohnson46@wisc.edu
// Team: Red
// Group: JG
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: NA

/**
 * Implements HashTable
 * 
 * @author kylejohnson
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // Linked list of class HashTable Values, and capacity and size
  private LinkedList<HashTableValue<KeyType, ValueType>>[] hashTableArray;
  private int capacity;
  private int currentSize;

  /**
   * Creates HashTable with specified capacity
   * 
   * @param capacity
   */
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    // Initializes variables
    hashTableArray = new LinkedList[capacity];
    this.capacity = capacity;
    currentSize = 0;
  }

  /**
   * Creates HashTable with default capacity 10
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() {
    // Initializes variables
    hashTableArray = new LinkedList[10];
    this.capacity = 10;
    currentSize = 0;
  }

  /**
   * Puts data into HashTable
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (key == null) {
      return false;
    }
    // Sets value to insert and finds index
    HashTableValue<KeyType, ValueType> newValue =
        new HashTableValue<KeyType, ValueType>(key, value);
    int indexToInsert = Math.abs(key.hashCode()) % capacity;
    // Empty linked list means first insert
    if (hashTableArray[indexToInsert] == null) {
      hashTableArray[indexToInsert] = new LinkedList<HashTableValue<KeyType, ValueType>>();
    }
    // Checks for duplicates
    for (int i = 0; i < hashTableArray[indexToInsert].size(); i++) {
      if (hashTableArray[indexToInsert].get(i).getKey().equals(key)) {
        return false;
      }
    }
    // Adds to front and updates size
    hashTableArray[indexToInsert].addFirst(newValue);
    currentSize++;
    // Checks the load factor
    if (((double) currentSize / capacity) >= 0.85) {
      resize();
    }
    return true;
  }


  /**
   * Resizes array when certain load is reached
   */
  private void resize() {
    @SuppressWarnings("unchecked")
    // Creates a new list with double the capacity
    LinkedList<HashTableValue<KeyType, ValueType>>[] newHashTableArray =
        new LinkedList[capacity * 2];
    // Adds every value from old array to new based on new hash
    for (int i = 0; i < capacity; i++) {
      if (hashTableArray[i] != null) {
        for (int j = 0; j < hashTableArray[i].size(); j++) {
          int indexToInsert =
              Math.abs(hashTableArray[i].get(j).getKey().hashCode()) % (capacity * 2);
          if (newHashTableArray[indexToInsert] == null) {
            newHashTableArray[indexToInsert] = new LinkedList<HashTableValue<KeyType, ValueType>>();
          }
          newHashTableArray[indexToInsert].addFirst(hashTableArray[i].get(j));
        }
      }
    }
    // Updates pointer and size
    capacity = capacity * 2;
    hashTableArray = newHashTableArray;
  }

  /**
   * Gets specified value from HashTable based on key
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (key == null) {
      throw new NoSuchElementException("Invalid Entry");
    }
    int indexToCheck = Math.abs(key.hashCode()) % capacity;
    if (hashTableArray[indexToCheck] == null) {
      throw new NoSuchElementException("Key " + key + " Was not in HashTable");
    }
    // Checks through linked list of the index based on the key
    for (int i = 0; i < hashTableArray[indexToCheck].size(); i++) {
      if (hashTableArray[indexToCheck].get(i).getKey().equals(key)) {
        return hashTableArray[indexToCheck].get(i).getValue();
      }
    }
    throw new NoSuchElementException("Key " + key + " Was not in HashTable");
  }

  /**
   * Returns size of HashTable
   */
  @Override
  public int size() {
    return currentSize;
  }

  /**
   * Returns total capacity
   */
  public int capacity() {
    return capacity;
  }
  
  /**
   * Return true if specified key is in HashTable
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    }
    int indexToCheck = Math.abs(key.hashCode()) % capacity;
    if (hashTableArray[indexToCheck] == null) {
      return false;
    }
    // Checks if every in the linked list of the index based on the key
    for (int i = 0; i < hashTableArray[indexToCheck].size(); i++) {
      if (hashTableArray[indexToCheck].get(i).getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns value of key if successfully removed
   */
  @Override
  public ValueType remove(KeyType key) {
    if (key == null) {
      return null;
    }
    int indexToCheck = Math.abs(key.hashCode()) % capacity;
    if (hashTableArray[indexToCheck] == null) {
      return null;
    }
    // If it is linked list of the key, remove and update size
    for (int i = 0; i < hashTableArray[indexToCheck].size(); i++) {
      if (hashTableArray[indexToCheck].get(i).getKey().equals(key)) {
        ValueType removed = hashTableArray[indexToCheck].get(i).getValue();
        hashTableArray[indexToCheck].remove(i);
        currentSize--;
        return removed;
      }
    }
    return null;
  }

  /**
   * Clears the HashTable
   */
  @Override
  public void clear() {
    for (int i = 0; i < hashTableArray.length; i++) {
      hashTableArray[i] = null;
    }
    currentSize = 0;
  }
  

}
