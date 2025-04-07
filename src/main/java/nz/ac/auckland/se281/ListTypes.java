package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class ListTypes<Type> {
  /*
  ListTypes covers:
  - Adding to list
  - Checking for no entries
  - Getting an entry
  - Getting size
  - Searching by id
  */

  protected ArrayList<Type> list;

  protected abstract void printEntries(ArrayList<Integer> indexes);

  public ListTypes() {
    this.list = new ArrayList<>();
  }

  public void addToList(Type obj) {
    this.list.add(obj);
  }

  public boolean containsNoEntries() {
    return this.list.isEmpty();
  }

  public Type getEntry(int i) {
    return this.list.get(i);
  }

  public int getSize() {
    return this.list.size();
  }
}
