import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.*;
import org.junit.Test;
 
public class GameTest {
 
    MineSweeperGrid game = new MineSweeperGrid();
    File fileName = new File("topTen.txt");
    String firstString;
    
    
    @Test
    public void testTopTenFile(){
      game.resetTopTen();
      
      assertNotNull("Check topTen.txt is not null", fileName);
      assertEquals("Checking default first line of topTen.txt", "1. COMP1 999", readFile());
      assertEquals("Checking there are 10 lines in topTen.txt", 10, getFileLineCount());
      
      game.insertTopTen(90, "Yordan");
      
      assertEquals("Checking first line of topTen.txt after insert", "1. Yordan 90", readFile());
      game.resetTopTen();
      assertEquals("Checking default first line of topTen.txt after a reset", "1. COMP1 999", readFile());
      
    }
    
    @Test
    public void testCheckTopTen() {
      assertTrue("Checking topTen", game.checkTopTen(90));
      assertFalse("Checking topTen for value over true threshold.", game.checkTopTen(1000));
      assertTrue("Checking topTen for lowest possible value of true threshold", game.checkTopTen(0));
      //assertFalse("This should fail", game.checkTopTen(90));  
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