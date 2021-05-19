package Maze;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {

        // out of bound of grid, return false
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
            return false;
        }

        // not the part of the path, return false
        else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
            return false;
        } 

        // equals to the exit, return true
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        }

        // if didn't find exit, set current cell to PATH
        else {
            maze.recolor(x, y, PATH);

            // if the neighbour cell is exit, return true
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) ||
            findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;
            }
            // recolor the point to TEMPORARY, return false
            else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }

    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	// out of bounds
    	if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
    		return;
    	}
       
    	// a barrier
    	else if(!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return;
    	}
    	
    	//if the cell is the exit, push it into trace and add into result
    	else if(x==maze.getNCols()-1 && y==maze.getNRows()-1) {
    		trace.push(new PairInt(x,y));
    		ArrayList<PairInt> temp = new ArrayList<>(trace);
    		result.add(temp);
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    		return;
    	}	
    		
    	//Attempt to find a path from every neighbor.
    	//Tentatively mark cell as on path.
    	else {
    		maze.recolor(x, y,PATH);
    		trace.push(new PairInt(x,y));
    		findMazePathStackBased(x+1,y,result,trace);
    		findMazePathStackBased(x-1,y,result,trace);
    		findMazePathStackBased(x,y-1,result,trace);
    		findMazePathStackBased(x,y+1,result,trace);
    		
    		//if this path's last cell is not end, pop it from this trace and recolor it to NON_BACKGROUND;
    		trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
    		return;
    	}
    }
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);
    	return result;
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	ArrayList <ArrayList <PairInt>> result = findAllMazePaths(x, y);
    	
    	if(result.size() == 0) {
    		return null;
    	}
    	else {
    		int d=0;
        	int min = result.get(0).size();
        	for(int i=1;i<result.size();i++) {       	
        		if(result.get(i).size() < min) {       			
        			d=i;
        		}
        	}
        	//System.out.println(result.get(d));
        	return result.get(d);
    	}
    	
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
