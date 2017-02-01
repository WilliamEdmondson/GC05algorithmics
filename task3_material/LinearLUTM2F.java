/**
 * Implementation of LUT using unordered linear search.
 *
 * This class is an implementation of the look-up table abstract data
 * type that uses a sequence array as the underlying data
 * structure. The capacity of the LUT is thus limited. The elements of
 * the look-up table are stored unordered and linear search is used
 * for retrieval.
 */
public class LinearLUTM2F<E> implements LUT<E> {

  /**
   * The member class Key is used for the indexing keys of the LUT. It
   * is a String with basic comparative methods added.
   */
  protected class Key {

    public Key(String s) {
      kString = s;
    }

    public boolean equals(Key k) {
      return kString.equals(k.toString());
    }

    public boolean lessThan(Key k) {
      return kString.compareTo(k.toString()) < 0;
    }

    public boolean greaterThan(Key k) {
      return kString.compareTo(k.toString()) > 0;
    }

    public String toString() {
      return kString;
    }

    private String kString;
  }

  /**
   * The member class Entry encapsulates an entry of the LUT and contains
   * a {key, value} pair.
   */
  protected class Entry {

    public Entry(Key k, E v) {
      key = k;
      value = v;
    }

    protected Key key;
    protected E value;
  }

  // Single private data member - the LUT is stored in a sequence.
  protected SequenceList<Entry> seq;

  /**
   * Default constructor creates a default size sequence to store the LUT.
   */
  public LinearLUTM2F() {
    //changed to sequence list
    //seq = new SequenceArray<Entry>();
    seq = new SequenceList<Entry>();
  }

  /*
   * Does not make sence to construct list
   *  of specified size. 
  public LinearLUT(int size) {
    seq = new SequenceArray<Entry>(size);
  }
  */

  /**
   * Inserts a new key-value pair into the look-up table.
   */
  public void insert(String key, E value)
  throws LUTKeyException {
    // Just insert new entries at the front 
    Entry newEntry = new Entry(new Key(key), value);
      seq.insertFirst(newEntry);
  }

  /**
   * Removes the key-value pair with the specified key from the look-up
   * table.
   */
  public void remove(String key)
  throws LUTKeyException {
    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) {
      try {
        seq.delete(index);
      } catch (SequenceException e) {
        throw new AssertionError("This should not happen." + e);
      }
    } else {
      throw new LUTKeyException();
    }
  }

  /**
   * Retrieves the key-value pair with the specified key from the look-up
   * table.
   */
  public E retrieve(String key)
  throws LUTKeyException {

    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) {
      try {
        Entry searchEntry = seq.element(index);
        E r = searchEntry.value;
        //move to front
        seq.delete(index);
        seq.insertFirst(searchEntry);
        return r;

      } catch (SequenceException e) {
        throw new AssertionError("This should not happen." + e);
      }
    } else {
      throw new LUTKeyException();
    }
  }

  /**
   * Updates the key-value pair with the specified key with the new
   * specified value.
   */
  public void update(String key, E value)
  throws LUTKeyException {
    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) {
      try {
        Entry searchEntry = seq.element(index);
        searchEntry.value = value;
        //move to front
        seq.delete(index);
        seq.insertFirst(searchEntry);
      } catch (SequenceException e) {
        throw new AssertionError("This should not happen." + e);
      }
    } else {
      throw new LUTKeyException();
    }
  }

  /**
   * Returns a string listing all the key-entry pairs in the LUT
   */
  public String toString() {
    String output = "";
    for (int i=0; i < seq.size(); i++) {
      try {
        Entry tableEntry = seq.element(i);
        output += tableEntry.key.toString() + ":" +
                  tableEntry.value.toString() + ", ";
      } catch (SequenceException e) {
        throw new AssertionError("This should not happen." + e);
      }
    }
    return output;
  }

  /**
   * Returns the index of the sequence holding the entry with the
   * specified key. If no match is found an index of -1 is returned.
   */
  protected int findPosition(Key k) {
    return linearSearch(k);
  }

  /**
   * Performs linear search on the sequence holding the LUT and returns
   * the index of the entry containing the specifed key. If no match is
   * found an index of -1 is returned.
   */
  protected int linearSearch(Key k) {
    for (int i=0; i < seq.size(); i++) {
      if (k.equals(keyAt(i))) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the key at the specified position in the sequence.
   */
  protected Key keyAt(int index) {
    try {
      Entry entryAt = seq.element(index);
      return entryAt.key;
    } catch (SequenceException e) {
      throw new AssertionError("This should not happen." + e);
    }
  }
}
