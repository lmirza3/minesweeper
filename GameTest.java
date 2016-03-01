import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.*;
import org.junit.Test;
//import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
 
public class GameTest {
 
    MineSweeperGrid game = new MineSweeperGrid();
    File fileName = new File("topTen.txt");
    String firstString;
    
    Icon button = new ImageIcon("CS342 Project 2 Minesweeper Images/button_normal.gif");
    MineSweeperGrid.MSButton msButton = game.new MSButton(0,0,"",button);
    boolean buttonMineStatus;
    
    
    @Test
    public void testTopTenFile(){
      game.resetTopTen();
      //TEST 1
      assertNotNull("Check topTen.txt is not null", fileName);
      //TEST 2
      assertEquals("Checking default first line of topTen.txt", "1. COMP1 999", readFile());
      //TEST 3
      assertEquals("Checking there are 10 lines in topTen.txt", 10, getFileLineCount());
      
      game.insertTopTen(90, "Yordan");
      //TEST 4
      assertEquals("Checking first line of topTen.txt after insert", "1. Yordan 90", readFile());
      game.resetTopTen();
      //TEST 5
      assertEquals("Checking default first line of topTen.txt after a reset", "1. COMP1 999", readFile());
      
    }
    
    @Test
    public void testCheckTopTen() {
      //TEST 6
      assertTrue("Checking topTen", game.checkTopTen(90));
      //TEST 7
      assertFalse("Checking topTen for value over true threshold.", game.checkTopTen(1000));
      //TEST 8
      assertTrue("Checking topTen for lowest possible value of true threshold", game.checkTopTen(0));
      //assertFalse("This should fail", game.checkTopTen(90));  
    }
    
    @Test
    public void testInitialMineStatus() {
      buttonMineStatus = msButton.mineStatus;
      //initial mine status should be false
      //TEST 9
      assertFalse("Checking initial mine status",buttonMineStatus);
      //toggle mine status
      msButton.toggleMine(true);
      //TEST 10
      assertTrue("Checking toggled mine status",msButton.mineStatus);
      //msButton should be a mine right now
      //TEST 11
      assertTrue("Checking if mine status is true",msButton.isMine());
      msButton.setState("normal");
      //this should cause the state to be false
      //TEST 12
      assertEquals("Checking if state is normal","normal",msButton.state);
      //TEST 13
      assertEquals("Checking if row is correct",0,msButton.getRow());
      //TEST 14
      assertEquals("Checking if column is correct",0,msButton.getCol());
      
      msButton = game.new MSButton(5,5,"",button);
      //TEST 15
      assertEquals("Checking if new button with changed column is correct",5,msButton.getCol());
      
    }
    
    public String readFile(){
      try{
        Scanner input = new Scanner(fileName);
      
        int i = 1;
        while(input.hasNextLine())
        {
          if(i == 1){
            firstString = input.nextLine();
            ++i;
          }
          else{
            String progress = input.nextLine();
            ++i;
          }
        }
        input.close();
      }
      catch(Exception ex){
        System.out.println("File not Found");
      }
      
      return firstString;
    }
    
    public int getFileLineCount(){
      int i = 1;
      
      try{
        Scanner input = new Scanner(fileName);
      
        i = 1;
        while(input.hasNextLine())
        {
          if(i == 1){
            firstString = input.nextLine();
            ++i;
          }
          else{
            String progress = input.nextLine();
            ++i;
          }
        }
        input.close();
      }
      catch(Exception ex){
        System.out.println("File not Found");
      }
      
      return i-1; //This should be 10
    }
}