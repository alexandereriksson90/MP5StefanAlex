import java.awt.*;

class Disk
  {
  private Color      color;
  private int        number;
  private static int numberOfDisks = 0;

  public Disk()
    {
    number = ++numberOfDisks;
    switch (number % 10)
      {
      case 0:
        color = Color.gray;
        break;
      case 1:
        color = Color.red;
        break;
      case 2:
        color = Color.orange;
        break;
      case 3:
        color = Color.yellow;
        break;
      case 4:
        color = Color.black;
        break;
      case 5:
        color = Color.white;
        break;
      case 6:
        color = Color.green;
        break;
      case 7:
        color = Color.magenta;
        break;
      case 8:
        color = Color.blue;
        break;
      case 9:
        color = Color.cyan;
        break;
      }
    }

  public Color getColor()
    {
    return color;
    }

  public int getNumber()
    {
    return number;
    }

  public static int getNumberOfDisks()
    {
    return numberOfDisks;
    }

  public String toString()
    {
    return "" + number;
    }

  public static void reset()
    {
    numberOfDisks = 0;
    }
  }
