import static org.junit.Assert.*;
 
import org.junit.Test;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
 
public class GameTest {
 
    MineSweeperGrid game = new MineSweeperGrid();
    main main = new main();
    Icon smiley = new ImageIcon("CS342 Project 2 Minesweeper Images/smile_button.gif");
    JButton testSmileyButton; 
    MineSweeperGrid.MSButton msButton = game.new MSButton(0,0,"",smiley);
    boolean buttonMineStatus;
    int x;
    
 
    @Test
    public void testCheckTopTen() {
       assertTrue("Checking topTen", game.checkTopTen(90));
       assertFalse("Checking topTen", game.checkTopTen(1000));
        //assertFalse("This should fail", game.checkTopTen(90));  
    }
    
    @Test
    public void testInitialMineStatus() {
      buttonMineStatus = msButton.mineStatus;
      assertFalse("Checking initial mine status",buttonMineStatus);
    }
    
    @Test
    public void testToggledMineStatus() {
      buttonMineStatus = false;
      msButton.toggleMine(buttonMineStatus);
      assertFalse("Checking toggled mine status",buttonMineStatus);
    }
}