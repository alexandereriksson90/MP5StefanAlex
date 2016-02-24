
public class RodManager 
{
	private HanoiModel model;
	private int selectedRod;
	private boolean isSelected = false;
	private CommandManager commander;

	public RodManager(HanoiModel model)
	{
		this.model = model;
		this.commander = new CommandManager();
	}

	public void reset()
	{
		model.reset();
	}

	public void gotSelected(Iterable<Disk> rod)
	{
		if (isSelected)
		{
			commander.executeCommand(new MoveCommand(selectedRod, model.getRod(rod)));
			isSelected = false;
		} else
		{
			selectedRod = model.getRod(rod);
			isSelected = true;
		}

	}
	public void redo()
	{
		if(commander.isRedoAvailable())
			commander.redo();
	}
	public void undo()
	{
		if(commander.isUndoAvailable())
			commander.undo();
	}

	public boolean hasSelected()
	{
		return isSelected;
	}

	private class MoveCommand implements Command
	{
		private int fromRod, toRod;
		public MoveCommand(int fromRod, int toRod)
		{
			this.fromRod = fromRod;
			this.toRod = toRod;
		}
		public void execute()
		{
			model.move(fromRod, toRod);
		}

		public void undo()
		{
			model.move(toRod, fromRod);
		}

	}

}
