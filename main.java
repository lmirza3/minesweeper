import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.*;

public class main implements ActionListener{
  
  MineSweeperGrid game = new MineSweeperGrid();
  Globals globals = new Globals();
  JButton smileyButton;
  JMenuItem topTen;
  JMenuItem reset;
  JMenuItem exit;
  
  JMenuItem help;
  JMenuItem about;
  
  JTextField timeDisplay;
  JLabel timeLabel;
  JFrame f;
 
  
  
  public static void main (String args[])
  {
    new main();
  }
  
  public main()
  {
    //overarching frame
    f = new JFrame();
    
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    timeDisplay = new JTextField();
    timeDisplay.setPreferredSize(new Dimension(40, 25));
    timeLabel = new JLabel();
    f.getContentPane().add(timeLabel, BorderLayout.NORTH);
    
    javax.swing.Timer t = new javax.swing.Timer(1000, new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              f.repaint();
          }
       });
    
    
    t.start();
    panel2.add(timeLabel);
    
    f.getContentPane().add(panel2,BorderLayout.NORTH);
    //panel for the number of mines left to be found
    
    Icon smiley = new ImageIcon("C:/Users/Lubna/Dropbox/School/CS 342/mineSweeper/smile_button.gif");
    smileyButton = new JButton(smiley); // Declare and allocate a Button instance called btnColor
    smileyButton.setPreferredSize(new Dimension(24, 24));
    smileyButton.addActionListener(this);
    f.add(smileyButton);                       // "this" Container adds the Button

    JLabel label = new JLabel("Test text");//initialize the label
    
    f.getContentPane().add(panel1,BorderLayout.NORTH);
    JMenuBar menuBar = new JMenuBar();
    
    JMenu gameMenu = new JMenu("Game");
    gameMenu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(gameMenu);
    
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(helpMenu);
    
    //adding menu items for Game
    reset = new JMenuItem("Reset", KeyEvent.VK_T);
    reset.setMnemonic(KeyEvent.VK_T); //used constructor instead
    reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    reset.addActionListener(this);
    
    topTen = new JMenuItem("Top Ten", KeyEvent.VK_T);
    topTen.setMnemonic(KeyEvent.VK_T); //used constructor instead
    topTen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    topTen.addActionListener(this);
    
    exit = new JMenuItem("Exit", KeyEvent.VK_T);
    exit.setMnemonic(KeyEvent.VK_T); //used constructor instead
    exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    exit.addActionListener(this);
    
    //adding menu items for help
    help = new JMenuItem("Help", KeyEvent.VK_T);
    help.setMnemonic(KeyEvent.VK_T); //used constructor instead
    help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    help.addActionListener(this);
    
    about = new JMenuItem("About", KeyEvent.VK_T);
    about.setMnemonic(KeyEvent.VK_T); //used constructor instead
    about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    about.addActionListener(this);
    
    gameMenu.add(reset);
    gameMenu.add(topTen);
    gameMenu.add(exit);
    
    helpMenu.add(help);
    helpMenu.add(about);
    
    f.setJMenuBar(menuBar);
    f.setLayout(new FlowLayout());
    f.getContentPane().add(game);
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
    f.pack();
    f.setSize(275,350);
    f.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    //TOP TEN
    if(e.getSource() == topTen){
      System.out.println("hi");
      //perform action when textYes clicked
    }
    //ABOUT
    if(e.getSource() == about){
      System.out.println("Development Team:");
      System.out.println("Lubna Mirza - lmirza3");
      System.out.println("Yordan Machin - ymachi2");
      System.out.println("CS 342 MineSweeper Assignment");
    }
    //RESET
    if (e.getSource() == reset || e.getSource() == smileyButton)
    {
      game.resetGrid();
    }
    //HELP
    if (e.getSource() == help)
    {
      System.out.println("The rules in Minesweeper are simple:");
      System.out.println("- Uncover a mine, and the game ends.");
      System.out.println("- Uncover an empty square, and you keep playing.");
      System.out.println("- Uncover a number, and it tells you how many mines lay hidden in the eight surrounding squares");
    }
    //EXIT
    if (e.getSource() == exit)
    {
      System.exit(0);
    }
    
  }
}

