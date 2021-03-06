import java.util.EmptyStackException;

public class Maze {
    private Position start; // Start position
    private Position finish; // Finish position
    private char[][] mazeInfo; //  2-dimensional array of characters that represents the maze layout
    private MazeRunnerStack path; // Final path from start to finish through the maze
    private Boolean solved; // indicates whether is maze path is solved or not
    private String facing = "East";
    private Position mouse;
    
    /*
     * Constructor creates a new instance of Maze with a given layout.
     * @param mazeInfo in a 2d char array
     */
    public Maze(char[][] mazeInfo){
    
        
        this.mazeInfo = mazeInfo;


    
        }

    /*
     * sets the start position field
     * @param row, col
     */
    public void setStart(int row, int col){
        start = new Position(row, col);
    }
    /*
     * sets the finish position field
     * @param row, col
     */
    public void setFinish(int row, int col){
        finish = new Position(row, col);
    }
    /*
     * Displays the maze. Note that the code for this method is provided in the assignment
     */
    public void displayMaze(){
        boolean[][] pathGrid = new boolean[mazeInfo.length][mazeInfo[0].length];
        String pathLine = "";
        if (path != null) {
            if (solved) {
                System.out.println("Solution is:");
                Position p;
                while (!path.peek().equals(start)) {
                    p = path.pop();
                    pathLine = " --> ["+p.row+","+p.col+"]"+pathLine;
                    pathGrid[p.row][p.col] = true;
                }
            p = path.pop();
                pathLine = "["+p.row+","+p.col+"]"+pathLine;
            }
            else
                System.out.println("No solution could be found.");
        }
        //Make the top wall
        for (int j=0;j<mazeInfo[0].length;j++) {
            System.out.print("+---");
        }
        System.out.println("+");

        //Make each row
        for (int i=0;i<mazeInfo.length;i++) {
                
            for (int j=0;j<mazeInfo[i].length;j++) {
                if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '|')
                    System.out.print("| ");
                else
                    System.out.print("  ");
                if (start.equals(i,j))
                    System.out.print("S ");
                else if (finish.equals(i,j))
                    System.out.print("F ");
                else if (pathGrid[i][j])
                    System.out.print("* ");
                else
                    System.out.print("  ");
                }
            System.out.println("|"); //Right wall always present
            //Bottom walls
            for (int j=0;j<mazeInfo[i].length;j++) {
                if (mazeInfo[i][j] == 'L' || mazeInfo[i][j] == '_')
                    System.out.print("+---");
                else
                    System.out.print("+   ");
            }
            System.out.println("+");
        }
        //Display the path line if solved
        if(path != null && solved)
            System.out.println("Path is: "+pathLine);
    }
    //Solves the maze
    public void solveMaze(){
        path = new MazeRunnerStack();
        path.push(start);
        mouse = start;
        int maxStepCount = mazeInfo.length * mazeInfo[0].length * 4;
        int steps = 0;
        
    try {
        while((steps <= maxStepCount) && path != null && !path.peek().equals(finish)) {
            steps++;
            while(facing == "East" && !path.peek().equals(finish)) {//I am facing East

                if(path.peek().col + 1 <  mazeInfo[0].length && //in Bounds
                        (mazeInfo[path.peek().row][path.peek().col + 1] == '.' ||
                        mazeInfo[path.peek().row][path.peek().col + 1] == '_')) {//I should move forward
                    
                    mouse = new Position((path.peek().row),( path.peek().col + 1));
                     
                }
                else if(path.peek().row + 1 <=  mazeInfo.length && //in Bounds
                    (mazeInfo[path.peek().row][path.peek().col] == '.' || 
                   mazeInfo[path.peek().row][path.peek().col] == '|' )) {//I should move right
                    
                        facing = "South";//Start checking South  
    
                }
                
                else  {//I can't move forward or right
                    
                    facing = "North";//Start checking North
                }
                if(!path.contains(mouse) && facing == "East") { //The path does not already contain the mouse position
                    
                    path.push(mouse);//Add the new position to the path
                    
                     
                }
                else if(path.contains(mouse) && facing == "East") {//If the path includes mouse
                    path.pop();//Pop it off
                     
                
                }
                
            }
            
            while(facing == "South" && !path.peek().equals(finish)) {//I am facing South
                if(path.peek().row + 1 <=  mazeInfo.length && //in bounds
                        mazeInfo[path.peek().row][path.peek().col] == '.' || 
                        mazeInfo[path.peek().row][path.peek().col] == '|' ) {//I should move forward
                    
                    mouse = new Position((path.peek().row + 1),( path.peek().col));
                     
                }

                else if(path.peek().col - 1 >= 0 && //in Bounds
                        (mazeInfo[path.peek().row][path.peek().col] != '_' || 
                        mazeInfo[path.peek().row][path.peek().col] != 'L')) {//I should move right
                
                                    facing = "West";//Start checking West
                                
                            }
                        else  {//I can't move forward or right
                            
                            facing = "East";//Start checking East

                        }
                if(!path.contains(mouse) && facing == "South") { //The path does not already contain the mouse position
                    
                    path.push(mouse);//Add the new position to the path
                     
                }
                else if(path.contains(mouse) && facing == "South") {//If the path includes mouse
            
                    path.pop();//Pop it off
                     
                }

            }
            while(facing == "West" && !path.peek().equals(finish)) {//I am facing West
                if(path.peek().col - 1 >= 0 && //in Bounds
                        (mazeInfo[path.peek().row][path.peek().col] == '.' || 
                        mazeInfo[path.peek().row][path.peek().col] == '_')) {//I should move forward
                    
                    mouse = new Position((path.peek().row),( path.peek().col - 1));

                }
                else if(path.peek().row - 1 >= 0 &&  //in Bounds
                    mazeInfo[path.peek().row - 1][path.peek().col] != 'L' && 
                           mazeInfo[path.peek().row - 1][path.peek().col] != '_') {//I should move right
                
                                facing = "North";//Start checking North
        
                        }               
                        else  {//I can't move forward or right
                            
                            facing = "South";//Start checking South
                             
                        }
                if(!path.contains(mouse) && facing == "West") { //The path does not already contain the mouse position
                
                    path.push(mouse);//Add the new position to the path
                     
                }
                else if(path.contains(mouse) && facing == "West") {//If the path includes mouse

                    path.pop();//Pop it off
                     
                
                }
                 
            }
            while(facing == "North" && !path.peek().equals(finish)) {//I am facing North
                if(path.peek().row + 1 <  mazeInfo.length && //in Bounds
                        (mazeInfo[path.peek().row][path.peek().col + 1] == '.' || 
                           mazeInfo[path.peek().row][path.peek().col + 1] == '_')) {//I should move right
                            
                    mouse = new Position((path.peek().row),( path.peek().col + 1));

                                facing = "East";//Start checking West
                                 
                            
                        }
                        else if(path.peek().row - 1 >= 0 && //in Bounds
                                (mazeInfo[path.peek().row - 1][path.peek().col] == '.' || 
                                mazeInfo[path.peek().row - 1][path.peek().col] == '|')) {//I should move forward
                            
                            mouse = new Position((path.peek().row - 1),( path.peek().col));
       
                        }
                        else  {//I can't move forward or right
                            
                            facing = "West";//Start checking West

                        }
                if(!path.contains(mouse) && facing == "North") { //The path does not already contain the mouse position

                    path.push(mouse);//Add the new position to the path
                     
                }
                else if(path.contains(mouse) && facing == "North") {//If the path includes mouse
            
                    path.pop();//Pop it off
                     
                }
            }
             
            }
        if(path.peek().equals(finish)) {
             
            solved = true;
        }
    }
    
          catch (EmptyStackException e) {
                System.out.print(e.getMessage());            
            }
            catch (IllegalArgumentException e) {
                System.out.print(e.getMessage());
            }
    }


public static void main(String[] args) {
      Maze maze = new Maze(new char[][]{ // Create the maze
                              {'L','.','|'},
                              {'L','_','_'}
                             });
      maze.setStart(0, 0);
      maze.setFinish(0, 2);
      maze.displayMaze();
      maze.solveMaze();
      maze.displayMaze();

}
}