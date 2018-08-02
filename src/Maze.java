
public class Maze {
    private Position start; // Start position
    private Position finish; // Finish position
    private char[][] mazeInfo; //  2-dimensional array of characters that represents the maze layout
    private MazeRunnerStack path; // Final path from start to finish through the maze
    private Boolean solved; // indicates whether is maze path is solved or not
    
    /*
     * Constructor creates a new instance of Maze with a given layout.
     * @param mazeInfo in a 2d char array
     */
    public Maze(char[][] mazeInfo){
        for(int i = 0; i < mazeInfo.length; i++) {
            for(int j = 0; j < mazeInfo[i].length; j++) {
                
            }
        }
    } 
    /*
     * sets the start position field
     * @param row, col
     */
    public void setStart(int row, int col){
        
    }
    /*
     * sets the finish position field
     * @param row, col
     */
    public void setFinish(int row, int col){
        
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
        
    }
}
