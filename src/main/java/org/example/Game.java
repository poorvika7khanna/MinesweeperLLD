package org.example;

import org.example.models.Board;
import org.example.models.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game {
    Board gameBoard;
    int safeCells;
    Scanner sc=new Scanner(System.in);
    Set<Pair<Integer,Integer>> seen;

    public Game()
    {
        seen=new HashSet<>();
        initializeGame();
    }

    void initializeGame()
    {
        System.out.println("Enter the dimensions: ");
        String[] inp=sc.next().split(",");
        int len=Integer.parseInt(inp[0]);
        int height=Integer.parseInt(inp[1]);
        gameBoard=new Board(len,height);
        constructBoard();
    }
    void constructBoard()
    {
        Random random=new Random();
        char[] values={'0','0','*'};
        for(int l=0;l<gameBoard.getLen();l++)
        {
            for(int h=0;h<gameBoard.getHeight();h++)
            {
                gameBoard.grid[l][h].setVal(values[random.nextInt(3)]);
            }
        }
        for(int l=0;l<gameBoard.getLen();l++)
        {
            for(int h=0;h<gameBoard.getHeight();h++)
            {
                if(gameBoard.grid[l][h].getVal()=='*')
                {
                    char val;
                    if(l>0)
                    {
                        val=gameBoard.grid[l-1][h].getVal();
                        if(val!='*')
                        {
                            gameBoard.grid[l-1][h].setVal((char)(val+1));
                        }
                        if(h>0)
                        {
                            val=gameBoard.grid[l-1][h-1].getVal();
                            if(val!='*')
                            {
                                gameBoard.grid[l-1][h-1].setVal((char)(val+1));
                            }
                        }
                        if(h<gameBoard.getHeight()-1)
                        {
                            val=gameBoard.grid[l-1][h+1].getVal();
                            if(val!='*')
                            {
                                gameBoard.grid[l-1][h+1].setVal((char)(val+1));
                            }
                        }
                    }
                    if(h>0)
                    {
                        val=gameBoard.grid[l][h-1].getVal();
                        if(val!='*')
                        {
                            gameBoard.grid[l][h-1].setVal((char)(val+1));
                        }
                    }
                    if(h<gameBoard.getHeight()-1)
                    {
                        val=gameBoard.grid[l][h+1].getVal();
                        if(val!='*')
                        {
                            gameBoard.grid[l][h+1].setVal((char)(val+1));
                        }
                    }
                    if(l<gameBoard.getLen()-1)
                    {
                        val=gameBoard.grid[l+1][h].getVal();
                        if(val!='*')
                        {
                            gameBoard.grid[l+1][h].setVal((char)(val+1));
                        }
                        if(h>0)
                        {
                            val=gameBoard.grid[l+1][h-1].getVal();
                            if(val!='*')
                            {
                                gameBoard.grid[l+1][h-1].setVal((char)(val+1));
                            }
                        }
                        if(h<gameBoard.getHeight()-1)
                        {
                            val=gameBoard.grid[l+1][h+1].getVal();
                            if(val!='*')
                            {
                                gameBoard.grid[l+1][h+1].setVal((char)(val+1));
                            }
                        }
                    }
                }
                else
                {
                    safeCells++;
                }
            }
        }
    }

    void startGame()
    {
        while(true)
        {
            System.out.println("Enter the cell indices(1-based) or Enter q/Q to quit: ");
            String inpStr=sc.next().toLowerCase();
            if(inpStr.equals("q"))
            {
                System.out.println("YOU QUIT!");
            }
            String[] inp = inpStr.split(",");
            int x = Integer.parseInt(inp[0]);
            int y = Integer.parseInt(inp[1]);
            if (gameBoard.grid[x-1][y-1].getVal() == '*') {
                displayBoard(true);
                System.out.println("YOU LOST!");
                return;
            }
            seen.add(new Pair<>(x - 1, y - 1));
            safeCells--;
            if (safeCells == 0) {
                displayBoard(true);
                System.out.println("YOU WON!");
                return;
            }
            displayBoard(false);
        }
    }

    void displayBoard(boolean allCells)
    {
        for(int l=0;l<gameBoard.getLen();l++)
        {
            for (int h = 0; h < gameBoard.getHeight(); h++)
            {
                if(allCells || seen.contains(new Pair<>(l,h)))
                    System.out.print(gameBoard.grid[l][h].getVal());
                else
                {
                    System.out.print(" ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
