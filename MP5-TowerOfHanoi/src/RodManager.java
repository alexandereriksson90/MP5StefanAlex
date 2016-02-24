import java.awt.event.MouseAdapter;

public class RodManager
{
	HanoiModel model;
	
	public RodManager(HanoiModel model)
	{
		this.model = model;
	}
	
	public void move(int fromRod, int toRod)
	{
		model.move(fromRod, toRod);
	}
	
	public void reset()
	{
		model.reset();
	}
}
