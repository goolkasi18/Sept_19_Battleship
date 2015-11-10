package com.example.administrator.battleship;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Created by goolkasi18 on 11/8/2015.
 */
public class PlayerTest extends TestCase {
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
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
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
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        test1.endTurn();
        assertEquals(test1.turn, false);

        test2.endTurn();
        assertEquals(test2.turn, false);
    }

    @Test
    public void testStartTurn() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        test1.startTurn();
        assertEquals(test1.turn, true);

        test2.startTurn();
        assertEquals(test2.turn, true);
    }

    @Test
    public void testGetTurn() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");

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
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");

        test1.setPlayerName("newtest1");
        assertEquals(test1.playerName, "newtest1");

        test2.setPlayerName("newtest2");
        assertEquals(test2.playerName, "newtest2");
    }

    @Test
    public void testGetPlayerName() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");

        test1.playerName = "getnametest1";
        assertEquals(test1.getPlayerName(), "getnametest1");

        test2.playerName = "getnametest2";
        assertEquals(test2.getPlayerName(), "getnametest2");
    }

    @Test
    public void testAddShipToGrid() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");

        Ship testShip1 = new Ship(0, 0, 3, 1, 1, null, 1);
        Ship testShip2 = new Ship(0, 0, 3, 1, 2, null, 1);
        // float initx, float inity, int initLength, int initHeight, int initShipID, ImageView initImage, int initImageID

        assertEquals(test1.addShipToGrid(0, 0, testShip1), true);
        assertEquals(test1.squares[0][0], 1);
        assertEquals(test1.squares[0][1], 1);
        assertEquals(test1.squares[0][2], 1);

        // Delete the shipId from its location in the grid.
        for(int col = 0; col < testShip1.length; col++)
        {
            test1.squares[0][col] = 0;
        }

        // Test to see if the ship was not added in a wrong position.
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                assertEquals(test1.squares[row][col], 0);
            }
        }

        // Add a ship to an invalid location.
        assertEquals(test1.addShipToGrid(11,11,testShip1), false);
        // Ship should go out of bounds
        assertEquals(test1.addShipToGrid(9,1,testShip1), false);

        // Test to see if the ship was not added in a wrong position.
        for(int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                assertEquals(test1.squares[row][col], 0);
            }
        }

        // Add a ship on top of another ship
        assertEquals(test1.addShipToGrid(3, 1, testShip2), true);
        assertEquals(test1.addShipToGrid(1, 1, testShip2), false);


        // Should not work

        //do tests

        //have to make sure tests cover a good range of where they can place the ship and if the ship is our of bounds
        //not  just positive tests

        //this is really our largest and most complicated test by far so make sure to try your best to cover all aspects of it

    }

    @Test
    public void testDeleteShip() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        Ship testShip1 = new Ship(0, 0, 3, 1, 1, null, 1);
        Ship testShip2 = new Ship(0, 0, 3, 1, 2, null, 1);

        test1.addShipToGrid(0,0,testShip1);
        test1.deleteShip(testShip1);
        assertEquals(test1.squares[0][0], 0);
        assertEquals(test1.squares[0][1], 0);
        assertEquals(test1.squares[0][2], 0);

        // Test to see if ships were not deleted
        for(int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                assertEquals(test1.squares[row][col], 0);
            }
        }

        // Delete a ship that doesn't exist.
        test1.deleteShip(testShip2);
        // Test to see if ships were not deleted
        for(int row = 0; row < 10; row++)
        {
            for (int col = 0; col < 10; col++)
            {
                assertEquals(test1.squares[row][col], 0);
            }
        }

        //do tests

        //be weary, the ships added above will still be added on the board so trying to add a ship if it overlapps the ships added above
        //you will run into some form of error(probably)

        //make sure to test trying to delete ships that dont exist (should still run fine just doesnt actually do much.
        //delete the ship then use a double dor loop to test each spot and make sure no spot is saved as the shipID anymore.


    }

    @Test
    public void testAttack() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        Ship testShip1 = new Ship(0, 0, 3, 1, 1, null, 1);
        Ship testShip2 = new Ship(0, 0, 3, 1, 2, null, 1);
        Ship testShip3 = new Ship(0, 0, 3, 1, 2, null, 1);
        test1.addShipToGrid(0,0, testShip1);
        test1.addShipToGrid(2,2, testShip2);

        // Attack testShip1
        assertEquals(test1.attack(0, 0), true);

        // Attack miss
        assertEquals(test1.attack(5,5), false);

        // Attack invalid location
        assertEquals(test1.attack(5, 10), false);

    }

    @Test
    public void testSetProfilePicID() throws Exception {
        Player test1 = new Player("test1");
        test1.setProfilePicID(1);
        assertEquals(test1.profilePicID, 1);
    }

    @Test
    public void testGetProfilePicID() throws Exception {
        Player test1 = new Player("test1");
        test1.setProfilePicID(1);
        assertEquals(test1.getProfilePicID(), 1);
    }

    @Test
    public void testSetColorChoiceID() throws Exception {
        Player test1 = new Player("test1");
        test1.setColorChoiceID(1);
        assertEquals(test1.colorChoiceID, 1);
    }

    @Test
    public void testGetColorChoiceID() throws Exception {
        Player test1 = new Player("test1");
        test1.setColorChoiceID(1);
        assertEquals(test1.getColorChoiceID(), 1);
    }
}