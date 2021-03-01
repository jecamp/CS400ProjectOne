// --== CS400 File Header Information ==--
// Name: Kyle Johnson
// Email: kajohnson46@wisc.edu
// Team: Red
// Group: JG
// TA: Xinyi
// Lecturer: Gary Dahl
// Notes to Grader: NA

/**
 * Stores both values in a class
 * 
 * @author kylejohnson
 *
 * @param <KeyType>   Key type stored
 * @param <ValueType> Value type stored
 */
public class HashTableValue<KeyType, ValueType> {

  // Instance fields for two values
  private KeyType key;
  private ValueType value;

  /**
   * Creates class with both values
   * 
   * @param key   Key value to set
   * @param value Value value to set
   */
  public HashTableValue(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Returns the key
   * 
   * @return Key
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * Returns the value
   * 
   * @return Value
   */
  public ValueType getValue() {
    return value;
  }

}
