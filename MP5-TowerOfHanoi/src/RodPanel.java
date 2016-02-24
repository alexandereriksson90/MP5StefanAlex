import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ConcurrentModificationException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

class RodPanel extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;
	private Iterable<Disk> rod;
	private Color rodColor = Color.yellow;
	private boolean selected = false;
	

	public RodPanel(Iterable<Disk> rod)
	{
		super(true);
		setBackground(Color.black);
		this.rod = rod;
	}
	
	public RodPanel(Iterable<Disk> rod, RodManager mediator)
	{
		super(true);
		setBackground(Color.black);
		this.rod = rod;
		addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				// prata med en mediator?
				if(!selected)
				{
					selected = true;
					rodColor = Color.red;		
				}
					
				else
				{
					selected = false;
					rodColor = Color.yellow;								
				}					
				repaint();
			}


		});
	}
	

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int h = getHeight();
		int w = getWidth();
		int u = w / 15;
		int disks = Disk.getNumberOfDisks();
		int ru = w / (disks + 1);
		int midX = w / 2;
		g.setColor(rodColor);
		g.fillRect(midX - (u / 2), 0, u, getHeight());
		int n = 0;
		try
		{
			for (Disk disk : rod)
			{
				int rh = h / (disks + 1);
				g.setColor(disk.getColor());
				int rw = w - (disk.getNumber() - 1) * ru;
				g.fillRoundRect((w - rw) / 2, h - (n + 1) * rh, rw, rh - 1, rh - 1, rh - 1);
				g.setColor(Color.white);
				g.drawRoundRect((w - rw) / 2, h - (n + 1) * rh, rw, rh - 1, rh - 1, rh - 1);
				n++;
			}
		} catch (ConcurrentModificationException cme)
		{
			// the model has changed during rendering
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		repaint();
	}
}
