import java.util.Observer;

public class HanoiModel
  {
  private DiskStack[] rods          = { new DiskStack(), new DiskStack(), new DiskStack() };
  private int         numberOfDisks = 5;

  public void addObserver(int rodIndex, Observer observer)
    {
    getRod(rodIndex).addObserver(observer);
    }

  public void move(int fromRod, int toRod)
    {
    getRod(toRod).push(getRod(fromRod).pop());
    }

  public void setNumberOfDisks(int numberOfDisks)
    {
    this.numberOfDisks = numberOfDisks;
    reset();
    }

  public int getNumberOfDisks()
    {
    return numberOfDisks;
    }

  public int getNumberOfDisksOnRod(int index)
    {
    return getRod(index).size();
    }

  public void reset()
    {
    for (DiskStack stack : rods)
      stack.clear();
    Disk.reset();
    for (int n = 0; n < numberOfDisks; n++)
      getRod(0).push(new Disk());
    }
  public int getRod(Iterable<Disk> rod)
  {
	  int index = 0;
	  for(int i = 0; i < 3; i++)
	  {
		 if(rods[i].equals(rod))
			 index = i;
	  }
	
	  return index;
  }

  public Iterable<Disk> getIterableRod(int index)
    {
    return getRod(index);
    }

  private DiskStack getRod(int index)
    {
    return rods[index % rods.length];
    }
  
  
  }
