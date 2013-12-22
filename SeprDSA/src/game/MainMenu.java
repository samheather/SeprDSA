package game;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
       
        createGUI();
    }

    private void createGUI() {
        
        //used to place buttons relative positions
        int widthMainMenu=1064;
        int heightMainMenu=600;
        
        int heightButton = 40;
        int widthButton = 100;

    //create panel to place main menu buttons on
       JPanel mainMenuPanel = new JPanel();
       getContentPane().add(mainMenuPanel);
       

       //disables layout manager so buttons/panels can be placed using coordinates
       mainMenuPanel.setLayout(null);

       //create "Start" button at coordinates (x, y, width, height)
       JButton startGame = new JButton("Start");
       
       startGame.setBounds((widthMainMenu/3)-(widthButton/3), (heightMainMenu/2)-4*(heightButton/2), widthButton, heightButton);
       
       //defines what the "Start" button will do when pressed
       startGame.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               //NEEDS CODE TO RUN GAME
          }
       });
       
       JButton help = new JButton("Help");
       help.setBounds((widthMainMenu/3)-(widthButton/3), (heightMainMenu/2)-(heightButton/2), widthButton, heightButton);

       help.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               //NEEDS TO SHOW HELP PAGE
          }
       });

       JButton exitGame = new JButton("Exit");
       exitGame.setBounds((widthMainMenu/3)-(widthButton/3), (heightMainMenu/2)+2*(heightButton/2), widthButton, heightButton);

       exitGame.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent event) {
               //Closes the game
               System.exit(0);
          }
       });

       //add the "Start" button to the panel
       mainMenuPanel.add(startGame);
       mainMenuPanel.add(help);
       mainMenuPanel.add(exitGame);

       //sets frame variables
       setTitle("Air Traffic Control Game: Main Menu");
       setSize(widthMainMenu, heightMainMenu);
       setResizable(false);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setBackground(Color.GREEN);
       
       
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenu menu = new MainMenu();
                menu.setVisible(true);
            }
        });
    }
}
