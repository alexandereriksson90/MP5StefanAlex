import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

class Stack<TYPE> extends Observable implements Iterable<TYPE>
  {
  private List<TYPE> list = new LinkedList<TYPE>();

  public boolean empty()
    {
    return list.isEmpty();
    }

  public int size()
    {
    return list.size();
    }

  public void push(TYPE o)
    {
    list.add(o);
    handleChange();
    }

  public TYPE pop()
    {
    TYPE result = null;
    if (!empty())
      result = list.remove(list.size() - 1);
    handleChange();
    return result;
    }

  public TYPE top()
    {
    TYPE result = null;
    if (!empty())
      result = list.get(0);
    return result;
    }

  public void clear()
    {
    list.clear();
    handleChange();
    }

  public Iterator<TYPE> iterator()
    {
    return list.iterator();
    }

  public String toString()
    {
    return list.toString();
    }

  private void handleChange()
    {
    this.setChanged();
    this.notifyObservers();
    }
  }
