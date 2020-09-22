package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {
    public int[][] grid;
    Scanner inputScanner = new Scanner(System.in);
    public Menu() {}
     public void menurun(){

        boolean flag = true;
        System.out.println("=== Menu ===");
        System.out.println("1." + "\t" + "Generate a new maze");
        System.out.println("2." + "\t" + "Load a maze");
        System.out.println("0." + "\t" + "Exit");
//         System.out.println(">");

        int i =Integer.parseInt(inputScanner.nextLine());
        if(i==1){
            mazegeneration();
        }
        else if(i ==2){
//            System.out.println("Enter file name along with the path");
//            System.out.println(">");
//            String path ="/home/omesh/Codes/Java/Intellij/Maze\\ Runner/Maze\\ Runner/task/";
            String  path =inputScanner.nextLine();
            loadmaze(path);
        }
        else if(i==0) {
            System.out.println("Bye!");
            flag=false;
        }
        else{
            flag =false;
        }
        while (flag) {

            System.out.println("=== Menu ===");
            System.out.println("1." + "\t" + "Generate a new maze");
            System.out.println("2." + "\t" + "Load a maze");
            System.out.println("3." + "\t" + "Save the maze");
            System.out.println("4." + "\t" + "Display the maze");
            System.out.println("5." + "\t" + "Find the escape");
            System.out.println("0." + "\t" + "Exit");

            int input = Integer.parseInt(inputScanner.nextLine());
            switch (input) {
                case 1: {
                    mazegeneration();
                    break;
                }
                case 2:{
//                    System.out.println("Enter file name along with the path");
//                    String path ="/home/omesh/Codes/Java/Intellij/Maze Runner/Maze Runner/task/";
                   String  path =inputScanner.nextLine();
//                    path =inputScanner.nextLine();
                    loadmaze(path);

                    break;
                }
                case 3:{
                    savemaze();
                    break;
                }
                case 4:{
                    printmaze(grid);
                    break;
                }
                case 5:{
                    solveMaze();
                    break;
                }
                case 0:{
                    System.out.println("Bye!");
                    flag=false;
                    break;
                }
                default:{
                    flag =false;
                    break;
                }
            }
        }
    }
    public void mazegeneration(){

        System.out.println("Please, enter the size of a maze" );
        int height = Integer.parseInt(inputScanner.nextLine());
        Maze maze = new Maze(height, height, false);
        grid =new int[height][height];
        grid =  maze.generateMaze();
        printmaze(grid);
    }
    public  void printmaze(int[][] grid){
        String wallChar = "\u2588\u2588";
        String passChar = "  ";

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] == 1 ? wallChar : passChar);
            }
            System.out.print("\n");
        }
    }
    public  void loadmaze(String path){
//        System.out.println(">");
//        String path = inputScanner.nextLine();
        String g = "D:\\Codes\\Java\\Maze_Runner\\Maze_Runner\\task\\test_maze.txt";
        File file = new File(g);


        try(Scanner sc=new Scanner(file)){
            String s ="";
            while(sc.hasNext()){
                s+=sc.nextLine();
//                System.out.println(s);
            }
//            System.out.println(s);
            int n =(int ) Math.sqrt(s.length());
//            System.out.println(n);
//            int [][] grid = new int[n][n];
            grid =new int[n][n];
           int str_ptr =0;
            for(int  i = 0;i<n;i++){
                for(int j =0;j<n;j++){
                    grid[i][j] =s.charAt(str_ptr)-'0';
                    str_ptr=str_ptr+1;
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("The file  " + path + "  does not exist");

        }


    }
    public void savemaze() {
        String path ="";
         path = inputScanner.nextLine();
        File file = new File(path);
        try(PrintWriter printWriter = new PrintWriter(file)) {
            for(int i=0;i<grid.length;i++){
                for(int j =0;j<grid.length;j++){
                    printWriter.print(grid[i][j]);
                }
                printWriter.println();
            }

        }catch(java.io.FileNotFoundException ex) {
            System.out.println("Cannot save the file" );
        }
         catch(java.lang.NullPointerException e){
             System.out.println("empty file does not exist");
            }

        }
        public boolean  solveMazeHelper(int[][] v,int i,int j){
            // Index out of bounds
        if(i < 0 || j < 0 || i >= v.length || j >= v[0].length || v[i][j] <= 0) {
                return false;
        }
        v[i][j]=0;
        if(grid[i][j]==1){
            return false;
        }
            if(i==grid.length-1||
                 solveMazeHelper(v,i-1,j)||
                    solveMazeHelper(v,i+1,j)||
                    solveMazeHelper(v,i,j-1)||
                    solveMazeHelper(v,i,j+1)
            ){
                v[i][j]=-1;
            }
            return v[i][j]==-1;

        }
        public void solveMaze(){
        int src =-1;
        int dest =-1;
        int [][] v =new int[grid.length][grid[0].length];
//            int [][] m =new int[grid.length][grid[0].length];
        for(int i =0 ;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){

                v[i][j] =1;
            }
        }
        boolean ans = hasPath(v);
            String wallChar = "\u2588\u2588";

            String passChar = "  ";
            String pathChar = "//";
        for(int i=0;i<v.length;i++){
            for(int j=0;j<v[0].length;j++){
                if(v[i][j]==-1){

                System.out.print(pathChar);
                }
                else {
                    System.out.print(grid[i][j] == 1 ? wallChar : passChar);
                }
            }
            System.out.print("\n");
        }
        }
        public boolean hasPath(int[][] v){
        for(int j =0;j<v[0].length;j++){
            if(solveMazeHelper(v,0,j)){
                return true;
            }
        }return false;

        }
    }

//    /home/omesh/Codes/Java/Intellij/savedmazes/maze.txt
//  /home/omesh/Codes/Java/Intellij/Maze Runner/Maze Runner/task

