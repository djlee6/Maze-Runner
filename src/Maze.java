import java.util.EmptyStackException;

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
    
    	String facing = "East";
    	this.mazeInfo = mazeInfo;
    	
    	if(start != null) {
        	path = new MazeRunnerStack();
        	path.push(this.start);
        	}
 
    	try {
    		while(path != null && path.peek() != finish) {
    			
    			while(facing == "East") {//I am facing East
   
    				if(path.peek().row + 1 <=  mazeInfo.length && //in Bounds
    					mazeInfo[path.peek().row][path.peek().col] == '.' || 
    				   mazeInfo[path.peek().row][path.peek().col] == 'l' ) {//I should move right
    					
    						path.push(new Position((path.peek().row + 1), path.peek().col));//Increase row
    						facing = "South";//Start checking South
    					
    				}
    				else if(path.peek().col + 1 <=  mazeInfo[0].length && //in Bounds
    						mazeInfo[path.peek().row][path.peek().col + 1] != 'L' || 
    				        mazeInfo[path.peek().row][path.peek().col + 1] != 'l') {//I should move forward
   
    						path.push(new Position((path.peek().row), path.peek().col + 1));//Increase column
    				}
    				else  {//I can't move forward or right
    					
    					facing = "North";//Start checking North
    				}
    			}
    			
    			while(facing == "South") {//I am facing South
    				if(path.peek().col - 1 >= 0 && //in Bounds
    						mazeInfo[path.peek().row][path.peek().col] == '.' || 
    	    				   mazeInfo[path.peek().row][path.peek().col] == '_') {//I should move right
    	    					
    	    						path.push(new Position((path.peek().row), path.peek().col - 1));//Decrease column
    	    						facing = "West";//Start checking West
    	    					
    	    				}
    	    				else if(path.peek().row + 1 <=  mazeInfo.length && //in bounds
    	    						mazeInfo[path.peek().row][path.peek().col] == '.' || 
    	    				        mazeInfo[path.peek().row][path.peek().col] == 'l' ) {//I should move forward
    	    					
    	    						path.push(new Position((path.peek().row + 1), path.peek().col));//Increase row
    	    				}
    	    				else  {//I can't move forward or right
    	    					
    	    					facing = "East";//Start checking East
    	    				}
    			}
    			while(facing == "West") {//I am facing West
    				if(path.peek().row - 1 >= 0 && //in Bounds
    						mazeInfo[path.peek().row - 1][path.peek().col] == 'L' || 
 	    				   mazeInfo[path.peek().row - 1][path.peek().col] == '_') {//I should move right
 	    					
 	    						path.push(new Position((path.peek().row - 1), path.peek().col));//Decrease row
 	    						facing = "North";//Start checking North
 	    					
 	    				}
 	    				else if(path.peek().col - 1 >= 0 && //in Bounds
 	    						mazeInfo[path.peek().row][path.peek().col] == '.' || 
 	    				        mazeInfo[path.peek().row][path.peek().col] == '_') {//I should move forward
 	    					
 	    						path.push(new Position((path.peek().row), path.peek().col - 1));//Decrease Column
 	    				}
 	    				else  {//I can't move forward or right
 	    					
 	    					facing = "South";//Start checking South
 	    				}
    			}
    			while(facing == "North") {//I am facing North
    				if(path.peek().row + 1 <=  mazeInfo.length && //in Bounds
    						mazeInfo[path.peek().row][path.peek().col + 1] == '.' || 
  	    				   mazeInfo[path.peek().row][path.peek().col + 1] == '_') {//I should move right
  	    					
  	    						path.push(new Position((path.peek().row), path.peek().col + 1));//Increase Column
  	    						facing = "West";//Start checking West
  	    					
  	    				}
  	    				else if(path.peek().row - 1 >= 0 && //in Bounds
  	    						mazeInfo[path.peek().row - 1][path.peek().col] == '.' || 
  	    				        mazeInfo[path.peek().row - 1][path.peek().col] == 'l') {//I should move forward
  	    					
  	    						path.push(new Position((path.peek().row - 1), path.peek().col));//Decrease Row
  	    				}
  	    				else  {//I can't move forward or right
  	    					
  	    					facing = "West";//Start checking West
  	    				}
    			}
    			}
    	}
    	
    		  catch (EmptyStackException e) {
    	            System.out.print(e.getMessage());            
    	        }
    	        catch (IllegalArgumentException e) {
    	            System.out.print(e.getMessage());
    	        }
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
