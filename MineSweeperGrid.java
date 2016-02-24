/**********************************************************************************************************************
 * CS342 Project 2 Minesweeper
 * Instructor: Patrick Troy
 * 
 * Authors: Yordan Machin and Lubna Mirza
 * 
 * This file contains Jpanel for the actual MineSweeper game and all its logic.
 * 
 *********************************************************************************************************************/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class MineSweeperGrid extends JPanel {
  private MSButton buttons[][];
  private Container container;
  private GridLayout grid;
  private int numCleared;
  private int markedM;
  private File fileName = new File("topTen.txt");  //This file must be in project folder for topTen functionality to work.
  private String[] topTen = new String[11];
  /***************************************Button Icons************************************/
  private Icon normal_button = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_normal.gif" );
  private Icon pressed_button = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_pressed.gif" );
  private Icon myMine_button = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_bomb_pressed.gif" );
  private Icon question_button = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_question.gif" );
  private Icon mineBlown_button = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_bomb_blown.gif" );
  private Icon button1 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_1.gif" );
  private Icon button2 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_2.gif" );
  private Icon button3 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_3.gif" );
  private Icon button4 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_4.gif" );
  private Icon button5 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_5.gif" );
  private Icon button6 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_6.gif" );
  private Icon button7 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_7.gif" );
  private Icon button8 = new ImageIcon( "CS342 Project 2 Minesweeper Images/button_8.gif" );
  /***************************************************************************************/
  public int mineFlag;
  public int secondsElapsed;
  public int gameCompletedFlag;
 /***********************************Game Grid Constructor********************************/
  public MineSweeperGrid(){
    
    this.setBackground(Color.RED);
    this.setPreferredSize(new Dimension(200,200));
    grid = new GridLayout(10,10,1,1);
    this.setLayout(grid);
    
    buttons = new MSButton[10][10];
    MouseClickHandler mouse = new MouseClickHandler();
    this.numCleared = 0;
    this.markedM = 0;
    fileName = new File("topTen.txt");
    topTen = new String[11];
    mineFlag = 10;
    secondsElapsed = 0;
    gameCompletedFlag = 0;
    
    try
    {
      Scanner input = new Scanner(fileName);
      
      int i = 1;
      while(input.hasNextLine())
      {
        topTen[i] = input.nextLine();
        ++i;
      }
      input.close();
    }
    catch(Exception ex)
    {
      JOptionPane.showMessageDialog (null, "File topTen.txt could not be read.");
    }
    
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 10; ++j)
      {   
        buttons[i][j] = new MSButton(i, j, "normal", normal_button);
        buttons[i][j].setSize(16,16);
        buttons[i][j].addMouseListener(mouse);
        this.add(buttons[i][j]);
      }
    }
    
    int randRow;
    int randCol;
    for(int i = 0; i < 10; ++i)
    {
      do
      {
        randRow = (int) (Math.random() * 9);
        randCol = (int) (Math.random() * 9);
      }while(buttons[randRow][randCol].isMine());
      
      buttons[randRow][randCol].toggleMine(true); 
    }    
  }
  /***************************************************************************************/
  
  /*****************************Check If Score is In topTen*******************************/
  public boolean checkTopTen(int secondsElapsed)
  {
    String[][] tmp = new String[11][];
    for(int i = 1; i < 11; ++i)
    {
      tmp[i] = topTen[i].split("\\s");
    }
    
    if(secondsElapsed < Integer.parseInt(tmp[10][2]))
    {
      return true;
    } 
    else
    {
      return false;
    }
  }
  /***************************************************************************************/
  
  /*****************************Insert into topTen*******************************/
  public void insertTopTen(int secondsElapsed, String user)
  {
    String[][] tmp = new String[11][];
    for(int i = 1; i < 11; ++i)
    {
      tmp[i] = topTen[i].split("\\s");
    }
    
    for(int i = 1; i < 11; ++i)
    {
      if((i == 10) && (Integer.parseInt(tmp[i][2]) > secondsElapsed))
      {
        topTen[i] = i + ". " + user + " " + secondsElapsed;
        break;
      }
      if(Integer.parseInt(tmp[i][2]) > secondsElapsed)
      {
        for(int j = 10; j > i; --j)
        {
          topTen[j] = j + ". " + tmp[j][1] + " " + tmp[j][2]/*topTen[j-1]*/;
        }
        topTen[i] = i + ". " + user + " " + secondsElapsed;
        break;
      }
    }
    
    try
    {
      FileWriter writer = new FileWriter(fileName);
      BufferedWriter bufWriter = new BufferedWriter(writer);
      
      for(int i = 1; i < 11; ++i)
      {
        bufWriter.write(topTen[i]);
        bufWriter.newLine();
      }
      
      bufWriter.close();
      
    }
    catch(IOException ex)
    {
      JOptionPane.showMessageDialog(null, "Error writing to file: topTen.txt");
    }
    
  }
  /***************************************************************************************/
  
  /************************************Reset topTen***************************************/
  public void resetTopTen()
  {
    try
    {
      FileWriter writer = new FileWriter(fileName);
      BufferedWriter bufWriter = new BufferedWriter(writer);
      
      for(int i = 1; i < 11; ++i)
      {
        String temp = (i + ". " + "COMP" + i + " 999");
        bufWriter.write(temp);
        bufWriter.newLine();
      }
      
      bufWriter.close();
      
    }
    catch(IOException ex)
    {
      JOptionPane.showMessageDialog(null, "Error writing to file: topTen.txt");
    }
  }
  /***************************************************************************************/
  
  
  /***********************************Grid Resetter***************************************/
  public void resetGrid()
  {
    for(int i = 0; i < 10; ++i)
    {
      for(int j = 0; j < 10; ++j)
      {   
        buttons[i][j].setEnabled(true);
        buttons[i][j].setState("normal");
        buttons[i][j].toggleMine(false);
        buttons[i][j].setIcon(normal_button);
      }
    }
    
    int randRow;
    int randCol;
    for(int i = 0; i < 10; ++i)
    {
      do
      {
        randRow = (int) (Math.random() * 9);
        randCol = (int) (Math.random() * 9);
      }while(buttons[randRow][randCol].isMine());
      
      buttons[randRow][randCol].toggleMine(true); 
    }
    
    numCleared = 0;
    
    try
    {
      Scanner input = new Scanner(fileName);
      
      int i = 1;
      while(input.hasNextLine())
      {
        topTen[i] = input.nextLine();
        ++i;
      }
      input.close();
    }
    catch(Exception ex)
    {
      JOptionPane.showMessageDialog (null, "File topTen.txt could not be read.");
    }
  }  
  /***************************************************************************************/
  
  /*************************************Calculate Adjacent Mines**************************/
  public int calcAdjMines(MSButton curr)
  {
    int currRow = curr.getRow();
    int currCol = curr.getCol();
    int adjMines = 0;
    
    //NW-Button
    if((currRow > 0) && (currCol > 0))
    {
      if(buttons[currRow-1][currCol-1].isMine())
        ++adjMines;
    }
    //N-Button
    if(currRow > 0)
    {
      if(buttons[currRow-1][currCol].isMine())
        ++adjMines;
    }
    //NE-Button
    if((currRow > 0) && (currCol < 9))
    {
      if(buttons[currRow-1][currCol+1].isMine())
        ++adjMines;
    }
    //W-Button
    if(currCol > 0)
    {
      if(buttons[currRow][currCol-1].isMine())
        ++adjMines;
    }
    //SW-Button
    if((currRow < 9) && (currCol > 0))
    {
      if(buttons[currRow+1][currCol-1].isMine())
        ++adjMines;
    }
    //S-Button
    if(currRow < 9)
    {
      if(buttons[currRow+1][currCol].isMine())
        ++adjMines;
    }
    //SE-Button
    if((currRow < 9) && (currCol < 9))
    {
      if(buttons[currRow+1][currCol+1].isMine())
        ++adjMines;
    }
    //E-Button
    if(currCol < 9)
    {
      if(buttons[currRow][currCol+1].isMine())
        ++adjMines;
    }
    
    return adjMines;
  }  
  /***************************************************************************************/
  
  /**************************************Click Handler************************************/
  private class MouseClickHandler extends MouseAdapter
  { 
    /**********************************Left Click Helper**********************************/
    public void performClick(int currRow, int currCol)
    {
      /*
       s = "Left Mouse Clicked " + 
       (buttons[currRow][currCol].getCol() + (buttons[currRow][currCol].getRow()*10)) +
       "\nState: " + buttons[currRow][currCol].getState() +
       "\nMine status = " + buttons[currRow][currCol].isMine();
       */
      
      //Check if button is not marked as possible mine or question mark.
      if(buttons[currRow][currCol].getState().equals("normal"))
      {
        //Check if button is a mine.
        if(buttons[currRow][currCol].isMine())
        {
          buttons[currRow][currCol].setState("blown");
          buttons[currRow][currCol].setIcon(mineBlown_button);
          JOptionPane.showMessageDialog (null, "You tripped a mine!!! Game Over....");
          
          //Mark all mines
          for(int i = 0; i < 10; ++i)
          {
            for(int j = 0; j < 10; ++j)
            {   
              if(buttons[i][j].isMine())
              {
                buttons[i][j].setState("myMine");
                buttons[i][j].setIcon(myMine_button);
                
                ++markedM;
              }
            }
          }
          
          //Set buttons to unclickable
          for(int i = 0; i < 10; ++i)
          {  
            for(int j = 0; j < 10; ++j)
            {   
              buttons[i][j].setEnabled(false);
              buttons[i][j].setState("gameOver");
            }
          }
        }
        else
        {
            int numAdjMines = calcAdjMines(buttons[currRow][currCol]);
            
            switch(numAdjMines)
            {
              case 0: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(pressed_button);
              ++numCleared;
              //NW-Button
              if((currRow > 0) && (currCol > 0))
              {
                //buttons[currRow-1][currCol-1].mousePressed();
                performClick(currRow-1, currCol-1);
              }
              //N-Button
              if(currRow > 0)
              {
                //buttons[currRow-1][currCol].mousePressed();
                performClick(currRow-1, currCol);
              }
              //NE-Button
              if((currRow > 0) && (currCol < 9))
              {
                //buttons[currRow-1][currCol+1].mousePressed();
                performClick(currRow-1, currCol+1);
              }
              //W-Button
              if(currCol > 0)
              {
                //buttons[currRow][currCol-1].doClick();
                performClick(currRow, currCol-1);
              }
              //SW-Button
              if((currRow < 9) && (currCol > 0))
              {
                //buttons[currRow+1][currCol-1].doClick();
                performClick(currRow+1, currCol-1);
              }
              //S-Button
              if(currRow < 9)
              {
                //buttons[currRow+1][currCol].doClick();
                performClick(currRow+1, currCol);
              }
              //SE-Button
              if((currRow < 9) && (currCol < 9))
              {
                //buttons[currRow+1][currCol+1].doClick();
                performClick(currRow+1, currCol+1);
              }
              //E-Button
              if(currCol < 9)
              {
                //buttons[currRow][currCol+1].doClick();
                performClick(currRow, currCol+1);
              }
              break;
              case 1: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button1);
              ++numCleared;
              break;
              case 2: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button2);
              ++numCleared;
              break;
              case 3: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button3);
              ++numCleared;
              break;
              case 4: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button4);
              ++numCleared;
              break;
              case 5: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button5);
              ++numCleared;
              break;
              case 6: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button6);
              ++numCleared;
              break;
              case 7: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button7);
              ++numCleared;
              break;
              case 8: buttons[currRow][currCol].setState("pushed");
              buttons[currRow][currCol].setIcon(button8);
              ++numCleared;
              break;
            }  
            
            //JOptionPane.showMessageDialog (null, "Number of Adjacent Mines = " + numAdjMines);
        }
      }
    }
    /***************************************************************************************/
    
    public void mouseClicked (MouseEvent e)
    { 
      String s = "";
      MSButton tmp = (MSButton) e.getSource();
      int currRow = tmp.getRow();
      int currCol = tmp.getCol();
      /********************************Left Click Handler***********************************/
      if (SwingUtilities.isLeftMouseButton(e))
      {
        performClick(currRow, currCol);
        
        //All buttons cleared
        if(numCleared >= 90)
        { 
          //Mark all mines
          for(int i = 0; i < 10; ++i)
          {
            for(int j = 0; j < 10; ++j)
            {   
              if(buttons[i][j].isMine())
              {
                buttons[i][j].setState("myMine");
                buttons[i][j].setIcon(myMine_button);
                ++markedM;
              }
            }
          }
             
          //Set buttons to unclickable
          for(int i = 0; i < 10; ++i)
          {  
            for(int j = 0; j < 10; ++j)
            {   
              buttons[i][j].setEnabled(false);
              buttons[i][j].setState("gameOver");
            }
          }
          gameCompletedFlag = 1;
          JOptionPane.showMessageDialog (null, "You cleared the board!!! Good Job!!");
          if(checkTopTen(numCleared))
          {
            String tmpUser = JOptionPane.showInputDialog("You made the Top Ten!! Enter your Name: ");
            String[] currUser = tmpUser.split("\\s");
            
            insertTopTen(numCleared, currUser[0]);  
          }
          
          resetGrid();
        }
      }/***********************************Right Click Handler******************************/  
      else if (SwingUtilities.isRightMouseButton(e))
      {
        mineFlag--;
        
        //s = "Right Mouse Click";
        if(buttons[currRow][currCol].getState().equals("normal"))
        {
          buttons[currRow][currCol].setState("myMine");
          buttons[currRow][currCol].setIcon(myMine_button);
          ++markedM;
        }
        else if(buttons[currRow][currCol].getState().equals("myMine"))
        {
          --markedM;
          buttons[currRow][currCol].setState("question");
          buttons[currRow][currCol].setIcon(question_button);
        }
        else 
        {
          buttons[currRow][currCol].setState("normal");
          buttons[currRow][currCol].setIcon(normal_button);
        }
      }
      //else if (SwingUtilities.isMiddleMouseButton(e))
        //s = "Middle Mouse Click";   

      //s = s + "\nSHIFT_MASK: " + e.isShiftDown();
      //s = s + "\nCTRL_MASK: " + e.isControlDown();
      //s = s + "\nMETA_MASK: " + e.isMetaDown();
      //s = s + "\nALT_MASK: " + e.isAltDown();
      //JOptionPane.showMessageDialog (null, s);
    }
  }
  /***************************************************************************************/
  
  /**************************************MSButton Class***********************************/
  private class MSButton extends JButton 
  {
    private int row;
    private int col;
    private String state;
    private Icon icon;
    private boolean mineStatus;
  
    public MSButton(int r, int c, String s, Icon i)
    {
      super("", i);
      this.state = s;
      this.row = r;
      this.col = c;
      this.mineStatus = false;
    }
    
    public void toggleMine(boolean state)
    {
      this.mineStatus = state;
    } 
    
    public boolean isMine()
    {
      return this.mineStatus;
    }  
    
    public void setState(String s)
    {
      this.state = s;
    }  
  
    public String getState()
    {
      return this.state;
    }
  
    public int getRow()
    {
      return this.row;
    }  
    public int getCol()
    {
      return this.col;
    }  
  
  }
  /***************************************************************************************/
   
}
