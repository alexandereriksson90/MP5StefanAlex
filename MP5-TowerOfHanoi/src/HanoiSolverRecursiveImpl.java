class HanoiSolverRecursiveImpl implements HanoiSolver
  {
  private HanoiModel hanoiModel;
  private int        moves     = 0;
  private boolean    halt      = false;
  private int        delayTime = 500;

  public HanoiSolverRecursiveImpl(HanoiModel hanoiModel)
    {
    this.hanoiModel = hanoiModel;
    }

  public synchronized long solve() throws Exception
    {
    int from = maxIndex(0, maxIndex(1, 2));
    int to = minIndex(0, minIndex(1, 2));
    int n = hanoiModel.getNumberOfDisksOnRod(from);
    moves = 0;
    halt = false;
    move(from, to, n);
    return moves;
    }

  public void halt()
    {
    halt = true;
    }

  public int getDelayTime()
    {
    return delayTime;
    }

  public void setDelayTime(int delayTime)
    {
    this.delayTime = delayTime;
    }

  private void move(int a, int b, int n) throws Exception
    {
    if (n > 1)
      {
      move(a, other(a, b), n - 1);
      move(a, b, 1);
      move(other(a, b), b, n - 1);
      }
    else if (n == 1)
      moveOne(a, b);
    }

  private void moveOne(int a, int b) throws Exception
    {
    if (halt)
      throw new Exception("Terminated");
    if (hanoiModel.getNumberOfDisksOnRod(a) > 0)
      {
      simulateTime();
      hanoiModel.move(a, b);
      moves++;
      }
    }

  private int other(int a, int b)
    {
    return (~(a + b)) & 0x03;
    }

  private int maxIndex(int a, int b)
    {
    return (hanoiModel.getNumberOfDisksOnRod(a) >= hanoiModel.getNumberOfDisksOnRod(b)) ? a : b;
    }

  private int minIndex(int a, int b)
    {
    return (hanoiModel.getNumberOfDisksOnRod(a) <= hanoiModel.getNumberOfDisksOnRod(b)) ? a : b;
    }

  private void simulateTime()
    {
    try
      {
      Thread.sleep(delayTime);
      }
    catch (Exception e)
      {
      }
    }
  }
