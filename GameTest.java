import static org.junit.Assert.*;
 
import org.junit.Test;
 
public class GameTest {
 
    MineSweeperGrid game = new MineSweeperGrid();
 
    @Test
    public void testCheckTopTen() {
        assertTrue("Checking topTen", game.checkTopTen(90));
        assertFalse("Checking topTen", game.checkTopTen(1000));
        assertFalse("This should fail", game.checkTopTen(90));  
    }
}