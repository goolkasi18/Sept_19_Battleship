package com.example.administrator.battleship;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by goolkasi18 on 11/8/2015.
 */
public class PlayerTest extends TestCase {
    Player test1 = new Player("test1");
    Player test2 = new Player("test2");
    Ship ship = new Ship(); //default ship which is the 5 long ship horizontal

    @Test
    public void testPlayer() throws  Exception {
        Player t1 = new Player();
        Player t2 = new Player("jim");
        int x, y;
        for(x=0; x<10; x++)
            for(y=0; y<10; y++) {
                assertEquals(t1.squares[x][y], 0);
                assertEquals(t2.squares[x][y], 0);
            }
        assertEquals(t1.playerName, "default");
        assertEquals(t2.playerName, "jim");
        assertEquals(t1.turn, false);
        assertEquals(t2.turn, false);
    }

    @Test
    public void testInitSquares() throws Exception {
        test1.initSquares();
        test2.initSquares();

        int x, y;
        for(x=0; x<10; x++)
            for(y=0; y<10; y++) {
                assertEquals(test1.squares[x][y], 0);
                assertEquals(test2.squares[x][y], 0);
            }
    }

    @Test
    public void testEndTurn() throws Exception {
        test1.endTurn();
        assertEquals(test1.turn, false);

        test2.endTurn();
        assertEquals(test2.turn, false);
    }

    @Test
    public void testStartTurn() throws Exception {
        test1.startTurn();
        assertEquals(test1.turn, true);

        test2.startTurn();
        assertEquals(test2.turn, true);
    }

    @Test
    public void testGetTurn() throws Exception {
        test1.turn = true;
        assertEquals(test1.getTurn(), true);
        test1.turn = false;
        assertEquals(test1.getTurn(), false);

        test2.turn = true;
        assertEquals(test2.getTurn(), true);
        test2.turn = false;
        assertEquals(test2.getTurn(), false);
    }

    @Test
    public void testSetPlayerName() throws Exception {
        test1.setPlayerName("newtest1");
        assertEquals(test1.playerName, "newtest1");

        test2.setPlayerName("newtest2");
        assertEquals(test2.playerName, "newtest2");
    }

    @Test
    public void testGetPlayerName() throws Exception {
        test1.playerName = "getnametest1";
        assertEquals(test1.getPlayerName(), "getnametest1");

        test2.playerName = "getnametest2";
        assertEquals(test2.getPlayerName(), "getnametest2");
    }

    @Test
    public void testAddShipToGrid() throws Exception {
        test1.addShipToGrid(0,0,ship);

        //do tests

        //have to make sure tests cover a good range of where they can place the ship and if the ship is our of bounds
        //not  just positive tests

        //this is really our largest and most complicated test by far so make sure to try your best to cover all aspects of it


    }

    @Test
    public void testDeleteShip() throws Exception {
        test1.addShipToGrid(0,0,ship);
        test1.deleteShip(ship);

        //do tests

        //make sure to test trying to delete ships that dont exist (should still run fine just doesnt actually do much.
        //delete the ship then use a double dor loop to test each spot and make sure no spot is saved as the shipID anymore.


    }

    @Test
    public void testAttack() throws Exception {
        test1.attack(0,0);

        //do tests

        test1.attack(5,5);

        //do tests

        test1.attack(10,10); //out of bounds, technically not possible how we made the game so might need to take out. but if possible good to test i guess

        //do tests assertEquals(test1.attack(10,10), false);
    }

    @Test
    public void testSetProfilePicID() throws Exception {
        //this should be easy
    }

    @Test
    public void testGetProfilePicID() throws Exception {
        //this should be easy
    }

    @Test
    public void testSetColorChoiceID() throws Exception {
        //this should be easy
    }

    @Test
    public void testGetColorChoiceID() throws Exception {
        //this should be easy
    }
}