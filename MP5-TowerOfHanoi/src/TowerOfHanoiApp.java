import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TowerOfHanoiApp extends JFrame
{
	private static final long serialVersionUID = 1L;
	private HanoiModel hanoiModel = new HanoiModel();
	private RodPanel rodPanelA = new RodPanel(hanoiModel.getIterableRod(0));
	private RodPanel rodPanelB = new RodPanel(hanoiModel.getIterableRod(1));
	private RodPanel rodPanelC = new RodPanel(hanoiModel.getIterableRod(2));
	private RodManager mediator = new RodManager(hanoiModel);
	private RodPanel rodPanelManual1 = new RodPanel(hanoiModel.getIterableRod(0), mediator);
	private RodPanel rodPanelManual2 = new RodPanel(hanoiModel.getIterableRod(1), mediator);
	private RodPanel rodPanelManual3 = new RodPanel(hanoiModel.getIterableRod(2), mediator);
	private HanoiSolver hanoiSolver;
	private Container c;
	private JPanel centerP;

	public TowerOfHanoiApp()
	{
		super("Tower of Hanoi");
		hanoiModel.addObserver(0, rodPanelA);
		hanoiModel.addObserver(1, rodPanelB);
		hanoiModel.addObserver(2, rodPanelC);
		
		setJMenuBar(makeMenuBar());
		centerP = makeCenterPanel();
		JPanel southP = makeSouthPanel();
		c = getContentPane();
		c.setBackground(Color.black);
		c.setLayout(new BorderLayout());
		c.add(centerP, BorderLayout.CENTER);
		c.add(southP, BorderLayout.SOUTH);
		hanoiSolver = new HanoiSolverRecursiveImpl(hanoiModel);
		hanoiModel.reset();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void stop()
	{
		hanoiSolver.halt();
	}

	private int inputIntegerDialog(String message)
	{
		int result = 0;
		String string = JOptionPane.showInputDialog(this, message);
		try
		{
			result = Integer.parseInt(string);
		} catch (Exception e)
		{
		}
		return result;
	}

	private void changeNumberOfRings()
	{
		int disks = inputIntegerDialog("<html>Current number of disks = " + hanoiModel.getNumberOfDisks()
				+ ".<br>Enter new number of disks:</html>");
		if (disks > 0)
		{
			hanoiSolver.halt();
			hanoiModel.setNumberOfDisks(disks);
		}
	}

	private void changeSpeed()
	{
		int delayTime = inputIntegerDialog(
				"<html>Current delay = " + hanoiSolver.getDelayTime() + " ms.<br>Enter new delay:</html>");
		if (delayTime >= 0)
		{
			hanoiSolver.setDelayTime(delayTime);
		}
	}

	private void start()
	{
		
		c.remove(centerP);
		centerP = makeCenterPanel();
		c.add(centerP, BorderLayout.CENTER);
		setVisible(true);
		repaint();
		
		new Thread()
		{
			public void run()
			{
				try
				{
					hanoiSolver.solve();
				} catch (Exception e)
				{
					hanoiSolver.halt();
					hanoiModel.reset();
				}
				java.awt.Toolkit.getDefaultToolkit().beep();
			}
		}.start();
	}

	private void startManual()
	{
		
		c.remove(centerP);
		centerP = makeManualCenterPanel();
		c.add(centerP, BorderLayout.CENTER);
		setVisible(true);
		repaint();
		
		new Thread()
		{
			public void run()
			{
				try
				{
					
				} catch (Exception e)
				{
					hanoiModel.reset();
				}
				java.awt.Toolkit.getDefaultToolkit().beep();
			}
		}.start();
	}

	private void quit()
	{
		System.exit(0);
	}

	private JMenuBar makeMenuBar()
	{
		JMenuItem ringsMI = new JMenuItem("Set rings");
		JMenuItem speedMI = new JMenuItem("Set speed");
		JMenu menu = new JMenu("Menu");
		menu.add(ringsMI);
		menu.add(speedMI);
		JMenuBar bar = new JMenuBar();
		bar.add(menu);
		ringsMI.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				changeNumberOfRings();
			}
		});
		speedMI.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				changeSpeed();
			}
		});
		return bar;
	}

	private JPanel makeCenterPanel()
	{
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(3, 7));
		centerP.setBackground(Color.black);
		for (int i = 0; i < 7; i++)
			centerP.add(new JLabel(""));
		centerP.add(new JLabel(""));
		centerP.add(rodPanelA);
		centerP.add(new JLabel(""));
		centerP.add(rodPanelB);
		centerP.add(new JLabel(""));
		centerP.add(rodPanelC);
		centerP.add(new JLabel(""));
		for (int i = 0; i < 7; i++)
			centerP.add(new JLabel(""));
		return centerP;
	}
	
	private JPanel makeManualCenterPanel()
	{
		JPanel centerP = new JPanel();
		centerP.setLayout(new GridLayout(3, 7));
		centerP.setBackground(Color.black);
		for (int i = 0; i < 7; i++)
			centerP.add(new JLabel(""));
		centerP.add(new JLabel(""));
		centerP.add(rodPanelManual1);
		centerP.add(new JLabel(""));
		centerP.add(rodPanelManual2);
		centerP.add(new JLabel(""));
		centerP.add(rodPanelManual3);
		centerP.add(new JLabel(""));
		for (int i = 0; i < 7; i++)
			centerP.add(new JLabel(""));
		return centerP;
	}

	private JPanel makeSouthPanel()
	{
		JButton startManual = new JButton("Manual-Solve");
		JButton startB = new JButton("Auto-Solve");
		JButton stopB = new JButton("Stop");
		JButton quitB = new JButton("Quit");
		startManual.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				startManual();
			}
		});
		startB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				start();
			}
		});
		stopB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				stop();
			}
		});
		quitB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				quit();
			}
		});
		JPanel southP = new JPanel();
		southP.add(startManual);
		southP.add(startB);
		southP.add(stopB);
		southP.add(quitB);
		return southP;
	}

	public static void main(String args[])
	{
		new TowerOfHanoiApp();
	}
}
