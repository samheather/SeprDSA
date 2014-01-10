package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import game.Leaderboard;
import java.awt.Canvas;

public class MainMenu extends JFrame {

	public Canvas canvas = null;
	
	public MainMenu() {

		
		
		canvas = new Canvas();
        //canvas.setFocusTraversalKeysEnabled(false);
		canvas.setIgnoreRepaint(true);
        canvas.setSize(200, 200);
        setSize(1024, 640);
        setVisible(true);
        /*
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                newCanvasSize.set(canvas.getSize());
            }
        });
        */

        getContentPane().add(canvas, BorderLayout.CENTER);
        createGUI();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("icon16.png")));

        // on Windows we need to transfer focus to the Canvas
        // otherwise keyboard input does not work when using alt-tab
        /*
        if(LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_WINDOWS) {
            addWindowFocusListener(new WindowAdapter() {
                @Override
                public void windowGainedFocus(WindowEvent e) {
                    canvas.requestFocusInWindow();
                }
            });
        }
        
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeRequested = true;
            }
        });
        */
	}

	private void createGUI() {

		// used to place buttons relative positions
		int widthMainMenu = 1024;
		int heightMainMenu = 640;

		int heightButton = 40;
		int widthButton = 100;

		// create panel to place main menu buttons on
		JPanel mainMenuPanel = new JPanel();
		getContentPane().add(mainMenuPanel);

		// disables layout manager so buttons/panels can be placed using
		// coordinates
		mainMenuPanel.setLayout(null);

		// create "Start" button at coordinates (x, y, width, height)
		JButton startGame = new JButton("Start");

		startGame.setBounds((widthMainMenu / 3) - (widthButton / 3),
				(heightMainMenu / 2) - 4 * (heightButton / 2), widthButton,
				heightButton);

		// defines what the "Start" button will do when pressed
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//mainMenuPanel.setBounds(0, 0, 0, 0);
			}
		});

		JButton help = new JButton("Help");
		help.setBounds((widthMainMenu / 3) - (widthButton / 3),
				(heightMainMenu / 2) - (heightButton / 2), widthButton,
				heightButton);

		help.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// NEEDS TO SHOW HELP PAGE
			}
		});

		JButton exitGame = new JButton("Exit");
		exitGame.setBounds((widthMainMenu / 3) - (widthButton / 3),
				(heightMainMenu / 2) + 2 * (heightButton / 2), widthButton,
				heightButton);

		exitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// Closes the game
				System.exit(0);
			}
		});

		// add the "Start" button to the panel
		mainMenuPanel.add(startGame);
		mainMenuPanel.add(help);
		mainMenuPanel.add(exitGame);

		// display leaderboard
		JLabel leaderboardLabel = new JLabel("Leaderboard");
		JLabel nameLabel = new JLabel("Name");
		JLabel scoreLabel = new JLabel("Score");

		// place leaderboard label 2/3 across, 1/10 down the screen
		leaderboardLabel.setBounds((2 * widthMainMenu / 3),
				(heightMainMenu / 10), 75, 25);
		nameLabel
				.setBounds((3 * widthMainMenu / 5), heightMainMenu / 5, 75, 25);
		scoreLabel.setBounds((4 * widthMainMenu / 5), heightMainMenu / 5, 75,
				25);

		mainMenuPanel.add(leaderboardLabel);
		mainMenuPanel.add(nameLabel);
		mainMenuPanel.add(scoreLabel);

		// create an instance of Leaderboard class so data can be passed between
		// MainMenu class and Leaderboard class
		Leaderboard leaderboardInstance = new Leaderboard();
		int numberOfEntries = leaderboardInstance.leaderboardEntries.length;

		// create JLabels that will display the leaderboard name and scores
		for (int i = 0; i < numberOfEntries; i++) {
			JLabel nameOfScorer = new JLabel(
					leaderboardInstance.leaderboardEntries[i].getName());

			// turns the double type to a string via (+"")
			JLabel score = new JLabel(
					leaderboardInstance.leaderboardEntries[i].getScore() + "");

			nameOfScorer.setBounds((3 * widthMainMenu / 5),
					((heightMainMenu / 5) + 50 * (i + 1)), 75, 25);
			score.setBounds((4 * widthMainMenu / 5),
					((heightMainMenu / 5) + 50 * (i + 1)), 75, 25);

			mainMenuPanel.add(nameOfScorer);
			mainMenuPanel.add(score);
		}

		// sets frame variables
		setTitle("Air Traffic Control Game: Main Menu");
		//setResizable(false);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//mainMenuPanel.setBackground(Color.green);
		//mainMenuPanel.setOpaque(false);
		//mainMenuPanel.setVisible(false);

		// set the background for the leaderboard
		JPanel leaderboardBackground = new JPanel();
		leaderboardBackground.setLayout(null);
		leaderboardBackground.setBounds((2 * widthMainMenu / 3) - 100,
				(heightMainMenu / 10) - 25, 300, 500);
		mainMenuPanel.add(leaderboardBackground);
		leaderboardBackground.setBackground(Color.gray);

	}
/*
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainMenu menu = new MainMenu();
				menu.setVisible(true);
			}
		});
	}
	*/
}
