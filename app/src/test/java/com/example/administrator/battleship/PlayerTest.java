package com.example.administrator.battleship;

import android.util.Log;

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
        test1.turn = true;
        test1.endTurn();
        assertEquals(test1.turn, false);

        test2.turn = true;
        test2.endTurn();
        assertEquals(test2.turn, false);
    }

    @Test
    public void testStartTurn() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        test1.turn = false;
        test1.startTurn();
        assertEquals(test1.turn, true);

        test2.turn = false;
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

        Ship testShip1 = new Ship(0, 0, 3, 0, 1, 1, 0); //a ship that is 3 long, horizontal
        Ship testShip2 = new Ship(0, 0, 3, 0, 2, 1, 0); //a ship that is 3 long, horizontal
        // float initx, float inity, int initLength, int initHeight, int initShipID, ImageView initImage, int initImageID

        assertEquals(test1.addShipToGrid(0, 0, testShip1), true); //test to see if it returns true (so it says it worked)
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                if((col==0 || col==1 || col==2) && row == 0) //the spots the ship should be in
                    assertEquals(test1.squares[row][col], 1); //check that the ship is there
                else //all other spots
                    assertEquals(test1.squares[row][col], 0); //check that the ship isnt there
            }
        }


        // Add a ship to an invalid location to return false.
        assertEquals(test1.addShipToGrid(11,11,testShip1), false);
        // Ship should go out of bounds
        assertEquals(test1.addShipToGrid(1,8,testShip1), false);

        // Test to see that nothing has changed since the first ship added
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                if((col==0 || col==1 || col==2) && row == 0) //the spots the ship should be in
                    assertEquals(test1.squares[row][col], 1); //check that the ship is there
                else //all other spots
                    assertEquals(test1.squares[row][col], 0); //check that the ship isnt there
            }
        }

        // Add a ship on top of another ship
        assertEquals(test1.addShipToGrid(3, 1, testShip2), true);
        assertEquals(test1.addShipToGrid(3, 3, testShip2), false);

    }

    @Test
    public void testDeleteShip() throws Exception {
        Player test1 = new Player("test1");
        Player test2 = new Player("test2");
        Ship testShip1 = new Ship(0, 0, 3, 0, 1, 1, 0); //a ship that is 3 long, horizontal
        Ship testShip2 = new Ship(0, 0, 3, 0, 2, 1, 0); //a ship that is 3 long, horizontal

        test1.addShipToGrid(0, 0, testShip1);
        //dont need to test if its there because we tested that earlier

        test1.deleteShip(testShip1);
        //now is it gone?
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                assertTrue(test1.squares[row][col] != testShip1.shipID); //check each spot does not equal the ships id anymore
            }
        }


        // Delete a ship that doesn't exist.
        test1.deleteShip(testShip2);

        //add two ships and delete one and make sure the other is still there.
        test1.addShipToGrid(0, 0, testShip1);
        test1.addShipToGrid(5, 5, testShip2);

        test1.deleteShip(testShip1);
        int testShip2Count = 0;
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                assertTrue(test1.squares[row][col] != testShip1.shipID); //check each spot does not equal the ship 1's id anymore
                if(test1.squares[row][col] == testShip2.shipID)
                    testShip2Count++; //if a spot is to ship 2 increase the count
            }
        }
        assertEquals(testShip2Count,3); //make sure three and only three spots are still ship2

        //do tests

        //make sure to test trying to delete ships that dont exist (should still run fine just doesnt actually do anything.)
        //delete the ship then use a double dor loop to test each spot and make sure no spot is saved as the shipID anymore.


    }

    @Test
    public void testLockIn() throws Exception{
        Player test1 = new Player("test1");
        Ship testShip1 = new Ship(0, 0, 2, 0, 1, 1, 0);
        Ship testShip2 = new Ship(0, 0, 3, 0, 2, 1, 0);
        Ship testShip3 = new Ship(0, 0, 3, 0, 3, 1, 0);
        Ship testShip4 = new Ship(0, 0, 4, 0, 4, 1, 0);
        Ship testShip5 = new Ship(0, 0, 5, 0, 5, 1, 0);
        test1.addShipToGrid(0,0,testShip1);
        test1.addShipToGrid(1,1,testShip2);
        test1.addShipToGrid(2, 2, testShip3);
        test1.addShipToGrid(3,3,testShip4);
        test1.addShipToGrid(4, 4, testShip5);

        int shipCount = 0;
        for(int row = 0; row < 10; row++)
        {
            for(int col = 0; col < 10; col++)
            {
                if(test1.squares[row][col] > 0)
                    shipCount++;
            }
        }
        assertEquals(shipCount, 17); //and check to see if there are still 17 spots of ships
    }

    @Test
    public void testAttack() throws Exception {
        Player test1 = new Player("test1");
        Ship testShip1 = new Ship(0, 0, 2, 0, 1, 1, 0);
        Ship testShip2 = new Ship(0, 0, 3, 0, 2, 1, 0);
        Ship testShip3 = new Ship(0, 0, 3, 0, 3, 1, 0);
        Ship testShip4 = new Ship(0, 0, 4, 0, 4, 1, 0);
        Ship testShip5 = new Ship(0, 0, 5, 0, 5, 1, 0);
        test1.addShipToGrid(0,0,testShip1);
        test1.addShipToGrid(1,1,testShip2);
        test1.addShipToGrid(2,2,testShip3);
        test1.addShipToGrid(3,3,testShip4);
        test1.addShipToGrid(4, 4, testShip5);

        // Attack testShip1
        assertEquals(test1.attack(0, 0), true);
        assertEquals(test1.attack(0, 1), true);

        // Attack miss
        assertEquals(test1.attack(0,2), false);

        // Attack invalid location
        assertEquals(test1.attack(5, 10), false);

        //attack a spot already called
        assertEquals(test1.attack(0, 0), false);
        assertEquals(test1.attack(0,2), false);
    }

    @Test
    public void testSetProfilePicID() throws Exception {
        Player test1 = new Player("test1");
        assertEquals(test1.getProfilePicID(), -1);//default value
        test1.setProfilePicID(1);
        assertEquals(test1.profilePicID, 1);
    }

    @Test
    public void testGetProfilePicID() throws Exception {
        Player test1 = new Player("test1");
        assertEquals(test1.getProfilePicID(), -1); //default value
        test1.setProfilePicID(1);
        assertEquals(test1.getProfilePicID(), 1);
    }

    @Test
    public void testSetColorChoiceID() throws Exception {
        Player test1 = new Player("test1");
        assertEquals(test1.getProfilePicID(), -1); //default value
        test1.setColorChoiceID(1);
        assertEquals(test1.colorChoiceID, 1);
    }

    @Test
    public void testGetColorChoiceID() throws Exception {
        Player test1 = new Player("test1");
        assertEquals(test1.getProfilePicID(), -1); //default value
        test1.setColorChoiceID(1);
        assertEquals(test1.getColorChoiceID(), 1);
    }
}