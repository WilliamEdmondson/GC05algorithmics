public interface Sequence<E> {
  /**
   * Adds a new item at a specified position in the sequence.
   */
  public void insert(E o, int index)
  throws SequenceException;

  /**
   * Adds a new item at the start of the sequence.
   */
  public void insertFirst(E o)
  throws SequenceException;

  /**
   * Adds a new item at the end of the sequence.
   */
  public void insertLast(E o)
  throws SequenceException;

  /**
   * Removes the item at the specified position in the sequence.
   */
  public void delete(int index)
  throws SequenceException;

  /**
   * Removes the item at the start of the sequence.
   */
  public void deleteFirst()
  throws SequenceException;

  /**
   * Removes the item at the end of the sequence.
   */
  public void deleteLast()
  throws SequenceException;

  /**
   * Returns the item at the specified position in the sequence.
   */
  public E element(int index)
  throws SequenceException;

  /**
   * Returns the item at the start of the sequence.
   */
  public E first()
  throws SequenceException;

  /**
   * Returns the item at the end of the sequence.
   */
  public E last()
  throws SequenceException;

  /**
   * Returns the number of items in the sequence.
   */
  public int size();

  /**
   * Tests whether there are any items in the sequence.
   */
  public boolean empty();

  /**
   * Empties the sequence.
   */
  public void clear();
}
