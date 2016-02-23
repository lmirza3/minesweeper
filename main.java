import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class main implements ActionListener{
  
  public static void main (String args[])
  {
    new main();
  }
  
  public main()
  {
    MineSweeperGrid game = new MineSweeperGrid();
    JFrame f = new JFrame();
    JMenuBar menuBar = new JMenuBar();
    
    JMenu menu = new JMenu("Game");
    menu.setMnemonic(KeyEvent.VK_A);
    menuBar.add(menu);
    
    
    JMenuItem reset = new JMenuItem("Reset", KeyEvent.VK_T);
    reset.setMnemonic(KeyEvent.VK_T); //used constructor instead
    reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    reset.addActionListener(this);
    
    JMenuItem topTen = new JMenuItem("Top Ten", KeyEvent.VK_T);
    topTen.setMnemonic(KeyEvent.VK_T); //used constructor instead
    topTen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    topTen.addActionListener(this);
    
    JMenuItem exit = new JMenuItem("Exit", KeyEvent.VK_T);
    exit.setMnemonic(KeyEvent.VK_T); //used constructor instead
    exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
    exit.addActionListener(this);
    
    menu.add(reset);
    menu.add(topTen);
    menu.add(exit);
    
    
    
    f.setJMenuBar(menuBar);
    f.setLayout(new FlowLayout());
    f.getContentPane().add(game);
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );     
    f.pack();
    f.setSize(275,350);
    f.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    JMenuItem source = (JMenuItem)(e.getSource());
    /*if(source == topTen){
      System.out.println("hi");
      //perform action when textYes clicked
    }
    if(e.getSource() == reset){
      //perform action when textNo clicked
    }*/
  }
}

