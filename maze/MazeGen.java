package maze;
import java.util.*;
import java.lang.*;

public class MazeGen {
    Random random = new Random();
    private int blocked =0;
    private int passage =1;
    private  int width ;
    private  int height ;
    private int[][] grid;
   public int Startx;
    public int Starty;
    private int Endx;
    private int Endy;
    private LinkedList<int[]> neighbours ;
    public MazeGen(int width,int height){
        this.width =width;
        this.height = height;
        grid = new int[width][height];
        neighbours=new LinkedList<int[]>();
        setStart();
        mazegenerator();
    }
    //width decrease West=0 {4}//width increase East =width-1{2}
    //height decrease North ==row ==0{1} //height increase south==row ==height-1{3}

    private int getdirectionforStartandEndPos(){
        int dir = random.nextInt(5)+1;
        return dir;
    }

    private void setStart(){
        int dir = random.nextInt(2);
        int ele =0;
        if(dir ==0 ){
             ele = random.nextInt(width);
             Startx = 0;
             Starty = ele;
        }
        if(dir ==1){
            ele =random.nextInt(height);
            Starty =0;
            Startx =ele;
        }
//        grid[Startx][Starty]=1;
        neighbours.add(new int[]{Startx,Starty,Startx,Starty});
    }




    private void setEnd(){
        int dir = random.nextInt(2)+2;
        int ele =0;
        if(dir ==3 ){
            ele = random.nextInt(width);
            Endx = height-1;
            Endy = ele;
        }
        if(dir ==2){
            ele =random.nextInt(height);
            Endy =width-1;
            Endx =ele;
        }
    }
    public void mazegenerator(){
        while(!neighbours.isEmpty()){
            int [] n = neighbours.remove(random.nextInt(neighbours.size()));
            int x =n[2];
            int y =n[3];
            if(grid[x][y]==0){
                grid[n[0]][n[1]]=1;
                grid[x][y]=1;
                if ( x >= 2 && grid[x-2][y] == 0 )
                    neighbours.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && grid[x][y-2] == 0 )
                    neighbours.add( new int[]{x,y-1,x,y-2} );
                if ( x < width-2 && grid[x+2][y] == 0 )
                    neighbours.add( new int[]{x+1,y,x+2,y} );
                if ( y < height-2 && grid[x][y+2] == 0 )
                    neighbours.add( new int[]{x,y+1,x,y+2} );
            }
        }
    }
    public void printgrid(){
        for(int i =0;i<width;i++){
            for(int  j =0;j<height;j++){
                if(grid[i][j]==1){
                    System.out.print("\u2588\u2588");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

}
