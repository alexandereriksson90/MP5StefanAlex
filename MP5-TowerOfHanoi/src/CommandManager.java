

public class CommandManager
{
	private Stack<Command> undoStack = new Stack<Command>();
	private Stack<Command> redoStack = new Stack<Command>();

	public CommandManager()
	{
	}

	public void executeCommand(Command c)
	{
		c.execute();
		undoStack.push(c);
		redoStack.clear();
		
	}

	public boolean undoAvailable()
	{
		return !undoStack.empty();
	}
	
	public boolean redoAvailable() {
        return !redoStack.empty();
    }

	public void redo()
	{
		Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
	}

	public void undo()
	{
		Command command = undoStack.pop();
		command.undo();
		redoStack.push(command);
	}
}