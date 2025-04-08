package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class ListTypes<T> {
  /*
  ListTypes covers:
  - Adding to list
  - Checking for no entries
  - Getting an entry
  - Getting size
  - Searching by id
  */

  protected ArrayList<T> list;

  protected abstract ArrayList<Integer> getMatchingEntries(String keyword);

  public ListTypes() {
    this.list = new ArrayList<>();
  }

  public void addToList(T obj) {
    this.list.add(obj);
  }

  public boolean containsNoEntries() {
    return this.list.isEmpty();
  }

  public T getEntry(int i) {
    return this.list.get(i);
  }

  public int getSize() {
    return this.list.size();
  }
}
