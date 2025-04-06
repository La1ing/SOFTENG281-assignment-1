package nz.ac.auckland.se281;

import java.util.ArrayList;

public abstract class ListTypes<Type> {
  protected ArrayList<Type> list;

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
