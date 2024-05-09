package org.example.models;

public class Board {
    int len;
    int height;
    public Cell[][] grid;

    public Board(int l, int h)
    {
        len=l;
        height=h;
        grid=new Cell[l][h];
        for(int i=0;i<l;i++)
        {
            for(int j=0;j<h;j++)
            {
                grid[i][j]= new Cell();
            }
        }
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
