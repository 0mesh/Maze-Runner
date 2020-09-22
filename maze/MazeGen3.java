package maze;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MazeGen3 {

    public static final int WTOP = 1;
    public static final int WRGT = 2;
    public static final int WBOT = 4;
    public static final int WLFT = 8;

    int cells[][];
    Point current_cell;
    ArrayList<Point> inlist;
    ArrayList<Point> outlist;
    ArrayList<Point> frontlist;
    Random gen = new Random();
    int gridw, gridh, cellsize;

    public MazeGen3(int width,int height){
        gridw =width;
        gridh =height;
        cells =new int[gridw][gridh];
        int full = WTOP | WBOT | WLFT | WRGT;
        for (int x = 0; x < gridw; x++)
            for (int y = 0; y < gridh; y++)
                cells[x][y] = full;
        /* Then, mark the borders
         */
        int left = WLFT << 4;
        int right = WRGT << 4;
        for (int y = 0; y < gridh; y++)
        {
            cells[0][y] |= left;
            cells[gridw-1][y] |= right;
        }
        int top = WTOP << 4;
        int bottom = WBOT << 4;
        for (int x = 0; x < gridw; x++)
        {
            cells[x][0] |= top;
            cells[x][gridh-1] |= bottom;
        }
        mazegenerator();
    }
    public void mazegenerator()
    {
        int dir, x, y;

        /*
         * Implement Prim's algorithm to build maze
         */
        outlist = new ArrayList<Point>(gridw*gridh);
        inlist = new ArrayList<Point>();
        frontlist = new ArrayList<Point>();
        for (x = 0; x < gridw; x++)
            for (y = 0; y < gridh; y++)
                outlist.add(new Point(x,y));
        current_cell = rndElement(outlist);
        inlist.add(current_cell);
        moveNbrs(current_cell);

        while ((!frontlist.isEmpty()))
        {
            current_cell = rndElement(frontlist);
            inlist.add(current_cell);
            moveNbrs(current_cell);
            dir = findInNbr(current_cell);
            removeWall(current_cell, dir);
        }
        current_cell = null;

    }
    int findInNbr(Point p)
    {
        /* Return a random direction in which the point p has
         * a neighbor which is in inlist.
         */
        int d = gen.nextInt(4);
        int k = 0;
        while (k < 4)
        {
            switch(d)
            {
                case 0:	/* Top nbr? */
                    if ((cells[p.x][p.y] & (WTOP<<4)) != 0) break;
                    if (inlist.indexOf(new Point(p.x,p.y-1)) >= 0)
                        return WTOP;
                    break;
                case 1: /* Right nbr? */
                    if ((cells[p.x][p.y] & (WRGT<<4)) != 0) break;
                    if (inlist.indexOf(new Point(p.x+1,p.y)) >= 0)
                        return WRGT;
                    break;
                case 2: /* Bottom nbr? */
                    if ((cells[p.x][p.y] & (WBOT<<4)) != 0) break;
                    if (inlist.indexOf(new Point(p.x,p.y+1)) >= 0)
                        return WBOT;
                    break;
                case 3: /* Left nbr? */
                    if ((cells[p.x][p.y] & (WLFT<<4)) != 0) break;
                    if (inlist.indexOf(new Point(p.x-1,p.y)) >= 0)
                        return WLFT;
                    break;
            }
            d = (d+1) % 4;
            k++;
        }
        return 0; // This shouldn't ever happen
    }
    void moveNbrs(Point p)
    {
        Point s;

        /*
         * Move any neighbors of p which are in outlist from
         * outlist to frontlist.
         */
        if ((cells[p.x][p.y] & (WTOP<<4)) == 0)
        {
            s = new Point(p.x, p.y-1);
            movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & (WRGT<<4)) == 0)
        {
            s = new Point(p.x+1, p.y);
            movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & (WBOT<<4)) == 0)
        {
            s = new Point(p.x, p.y+1);
            movePoint(s, outlist, frontlist);
        }
        if ((cells[p.x][p.y] & (WLFT<<4)) == 0)
        {
            s = new Point(p.x-1, p.y);
            movePoint(s, outlist, frontlist);
        }
    }
    void movePoint(Point p, ArrayList<Point> v, ArrayList<Point> w)
    {
        /*
         * If p is element of v, move it to w
         */
        int i = v.indexOf(p);
        if (i >= 0)
        {
            v.remove(i);
            w.add(p);
        }
    }
    void removeWall(Point p, int d)
    {
        /* Exclusive or bit with cell to drop wall
         */
        cells[p.x][p.y] ^= d;
        /*
         * And drop neighboring wall as well
         */
        switch(d)
        {
            case WTOP: cells[p.x][p.y-1] ^= WBOT;
                break;
            case WRGT: cells[p.x+1][p.y] ^= WLFT;
                break;
            case WBOT: cells[p.x][p.y+1] ^= WTOP;
                break;
            case WLFT: cells[p.x-1][p.y] ^= WRGT;
                break;
        }
    }

    // Utility routines

    <E> E rndElement(java.util.List<E> v)
    {
        int i = gen.nextInt(v.size());
        E s = v.get(i);
        v.remove(i);
        return s;
    }

    public void printmatrix(){
        for(int  i =0;i<gridw;i++){
            for(int j =0;j<gridh;j++){
                int val =cells[i][j];
                if((val& WTOP)!=0){
                    System.out.print("  ");
                }
                if((val& WTOP)!=0){
                    System.out.print("  ");
                }
                if((val& WTOP)!=0){
                    System.out.print("  ");
                }
                if((val& WTOP)!=0){
                    System.out.print("  ");
                }
                else{
                    System.out.println("\u2588\u2588");
                }
            }
//            System.out.println();
        }
    }





}
