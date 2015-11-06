package com.example.administrator.battleship;

import android.widget.ImageView;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by rothschi18 on 11/5/2015.
 */
public class PlayerTest {

    Player test1 = new Player("test1");
    Player test2 = new Player("test2");

    @org.junit.Test
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
        test1.addShipToGrid(5,5,1,2,true);
    }

    @Test
    public void testDeleteShip() throws Exception {

    }



    @Test
    public void testAttack() throws Exception {

    }
}