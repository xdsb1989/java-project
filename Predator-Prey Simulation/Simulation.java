/*
 * Author: lingfeng zhang, e-mail lingfeng2013@my.fit.edu
 * Course: CSE 5400, Fall 2014
 * Project: project 05, Predator-Prey Simulation
 */
import java.util.Random;
public class Simulation {
	
	final static Random RNG = new Random (Long.getLong ("seed", System.nanoTime()));
    static Living gen () {
    	final double x = RNG.nextDouble();
    	if (x < 0.1)      
        	return new Fox();
        else if (x < 0.3) 
        	return new Rabbit();
        else if (x < 0.6) 
        	return new Grass();
        else              
        	return new Empty();
    }
    
    public static void main(String arg []){
        final int size = Integer.parseInt(arg[0]);
        final int cycles = Integer.parseInt(arg[1]);
        
        Living [][] world = new Living[size][size];
        
        for (int row=0;row<size;row++){
        	for (int col=0;col<size;col++){
        		if (row == 0 || col == 0 || row == size-1 || col == size-1){//this is the edge,edge is always empty
        			world[row][col] = new Empty();
        		}
        		else{
        			world[row][col] = gen();//the world will be generated by randomly
        		}
        	}
        }
        System.out.println("Cycle = 0");//print the initial world
        for (int row=0;row<size;row++){
        	for (int col=0;col<size;col++){
        		System.out.print(world[row][col].toChar());
        	}
        	System.out.println();
        }
        System.out.println();
        
        for (int count=1;count<=cycles;count++){//print the rest of the cycles
        	System.out.println("Cycle = "+count);
        	Living [][] new_world = new Living[size][size];
        	
        	for (int row=0;row<size;row++){
            	for (int col=0;col<size;col++){
            		if (row == 0 || col == 0 || row == size-1 || col == size-1){//this is the edge,edge is always empty
            			new_world[row][col] = world[row][col];
            		}
            		else{
            			Neighbors n = new Neighbors();
            			check_neighbors(size,row,col,world,n);//check_neighbor is the function to store the neighbors
	            		if (world[row][col].toChar() == 'X'){
	            			Fox f = new Fox();
	            			new_world[row][col] = f.next(n);
	            		}
	            		else if (world[row][col].toChar() == 'R'){
	            			Rabbit r = new Rabbit();
	            			new_world[row][col] = r.next(n);
	            		}
                        else if (world[row][col].toChar() == 'G'){
	            			Grass g = new Grass();
	            			new_world[row][col] = g.next(n);
	            		}
                        else if (world[row][col].toChar() == '.'){
	            			Empty e = new Empty();
	            			new_world[row][col] = e.next(n);
	            		}
            		}
            	}
        	}
        	world = new_world;//asign the new_world to the "world", because "world" will be used in the next cycle
        	for (int row=0;row<size;row++){//print the world which just be generated.
            	for (int col=0;col<size;col++){
            		System.out.print(world[row][col].toChar());
            	}
            	System.out.println();
            }
        	System.out.println();
        }
    }

	private static void check_neighbors(int size,int row,int col, Living[][] world, Neighbors n) {
		//there are 8 neighbors for each space,I just one by one to store all the neighbors
		for (int i=0;i<=7;i++){
			if (i==0){
				if (row-1!=0 && col-1!=0){
						n.array[i] = world[row-1][col-1].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==1){
				if (row-1!=0){
					n.array[i] = world[row-1][col].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==2){
				if (row-1!=0 && col+1<size-1){
					n.array[i] = world[row-1][col+1].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==3){
				if (col-1!=0){
					n.array[i] = world[row][col-1].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==4){
				if (col+1<size-1){
					n.array[i] = world[row][col+1].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==5){
				if (row+1<size-1 && col-1!=0){
					n.array[i] = world[row+1][col-1].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==6){
				if (row+1<size-1){
					n.array[i] = world[row+1][col].toChar();
				}
				else
					n.array[i] = ' ';
			}
			if (i==7){
				if (row+1<size-1 && col+1<size-1){
					n.array[i] = world[row+1][col+1].toChar();
				}
				else
					n.array[i] = ' ';
			}	
		}
	}
}
