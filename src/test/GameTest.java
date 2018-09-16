package test;

import org.junit.jupiter.api.Test;
import main.Game;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void test_Game_Object_Creation(){
        assertTrue(new Game() != null);
    }
}