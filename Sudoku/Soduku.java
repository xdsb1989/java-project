/*
 * Author: lingfeng zhang, e-mail lingfeng2013@my.fit.edu
 * Course: CSE 5400, Fall 2014
 * Project: project 07, Soduku
 */
import java.util.ArrayList;
import java.util.Scanner;
class mutiple_puzzle{//"one_puzzle" is one line(or one Soduku puzzle),store the numbers in the martix
	int one_puzzle[][];
}

class Possible_number{//each blank cell will have a set of number which can be the real number for the cell
	ArrayList<Integer> possible_number;
}

public class Soduku{
	static class Row_thread extends Thread{//setup the row threads,each row finish the row check
		int row;
		Possible_number [][]puzzle_matrix;
		mutiple_puzzle [] puzzle_list;
		public Row_thread(int row,Possible_number [][]puzzle_matrix,mutiple_puzzle [] puzzle_list){
			this.row = row;
			this.puzzle_list = puzzle_list;
			this.puzzle_matrix = puzzle_matrix;
		}
    	@java.lang.Override
		public void run() {
    		int exist_number[] = new int [10];//
    		int count = 0;
			for (int i = 1;i<=9;i++){//find the exist number in this row
				if (puzzle_matrix[row][i].possible_number.isEmpty())
				{
					exist_number[count] = puzzle_list[number_of_puzzle].one_puzzle[row][i];
					count++;
				}
			}
			for (int i = 1;i<=9;i++){//each row store a set of number which is the possible numbers for the current cell
				if (!puzzle_matrix[row][i].possible_number.isEmpty())
				{
					for (int j = 0; j < count; j++){
						for (int k = 0;k < puzzle_matrix[row][i].possible_number.size();k++)
						{
							if (exist_number[j] == puzzle_matrix[row][i].possible_number.get(k))
							{
								puzzle_matrix[row][i].possible_number.remove(k);
								
								if(puzzle_matrix[row][i].possible_number.size()==0)
								{
									puzzle_matrix[row][i].possible_number.add(0);
									puzzle_matrix[row][i].possible_number.add(0);
								}
								
								flag = true;
							}
						}
					}
					if (puzzle_matrix[row][i].possible_number.size() == 1){
						//after the check,if the cell only have one number left,then set the number as the value of the cell
						puzzle_list[number_of_puzzle].one_puzzle[row][i] = puzzle_matrix[row][i].possible_number.get(0);
						puzzle_matrix[row][i].possible_number.remove(0);
					}
				}
			}
		}
	}
	
	static class Col_thread extends Thread{
		int col;
		Possible_number [][]puzzle_matrix;
		mutiple_puzzle [] puzzle_list;
		public Col_thread(int col,Possible_number [][]puzzle_matrix,mutiple_puzzle [] puzzle_list){
			this.puzzle_list = puzzle_list;
			this.puzzle_matrix = puzzle_matrix;
			this.col = col;
		}
    	
		@java.lang.Override
		public void run() 
		{
    		int exist_number[] = new int [10];
    		int count = 0;
			for (int i = 1;i<=9;i++)
			{//find the exist number in this col
				if (puzzle_matrix[i][col].possible_number.isEmpty()){
					exist_number[count] = puzzle_list[number_of_puzzle].one_puzzle[i][col];
					count++;
				}
			}
			for (int i = 1;i<=9;i++)
			{//each row store a set of number which is the possible numbers for the current cell
				if (!puzzle_matrix[i][col].possible_number.isEmpty())
				{
					for (int j = 0; j < count; j++)
					{
						for (int k = 0;k < puzzle_matrix[i][col].possible_number.size();k++)
						{
							if (exist_number[j] == puzzle_matrix[i][col].possible_number.get(k))
							{
								puzzle_matrix[i][col].possible_number.remove(k);
								
								if(puzzle_matrix[i][col].possible_number.size()==0)
								{
									puzzle_matrix[i][col].possible_number.add(0);
									puzzle_matrix[i][col].possible_number.add(0);
								}
								
								flag = true;
							}
						}
					}
					if (puzzle_matrix[i][col].possible_number.size() == 1)
					{
						//after the check,if the cell only have one number left,then set the number as the value of the cell
						puzzle_list[number_of_puzzle].one_puzzle[i][col] = puzzle_matrix[i][col].possible_number.get(0);
						puzzle_matrix[i][col].possible_number.remove(0);
					}
				}
			}
		}
	}
	
	static class Box_thread extends Thread{
		//for each box,I set a central coordinate to scan all the cells in the box
		int centra_row;
		int centra_col;
		int array[] = {0,22,25,28,52,55,58,82,85,88};
		Possible_number [][]puzzle_matrix;
		mutiple_puzzle [] puzzle_list;
		public Box_thread(int num,Possible_number [][]puzzle_matrix,mutiple_puzzle [] puzzle_list){
			centra_row = array[num]/10;
			centra_col = array[num]%10;
			this.puzzle_list = puzzle_list;
			this.puzzle_matrix = puzzle_matrix;
		}
		
		@java.lang.Override
		public void run() {
			int exist_number[] = new int [10];//using the "exist_number" to store the numbers which is current exited in the box
    		int count = 0;
    		for (int i = centra_row-1;i <= centra_row+1;i++)
    		{
    			for (int j = centra_col-1;j <= centra_col+1;j++)
    			{
    				if (puzzle_matrix[i][j].possible_number.isEmpty())
    				{
    					exist_number[count] = puzzle_list[number_of_puzzle].one_puzzle[i][j];
    					count++;
    				}
    			}
    		}
    		for (int i = centra_row-1;i <= centra_row+1;i++)
    		{
    			for (int j = centra_col-1;j <= centra_col+1;j++)
    			{
    				if (!puzzle_matrix[i][j].possible_number.isEmpty())
    				{
    					for (int k = 0; k < count; k++)
    					{
    						//each row store a set of number which is the possible numbers for the current cell
    						for (int n = 0;n < puzzle_matrix[i][j].possible_number.size();n++){
    							if (exist_number[k] == puzzle_matrix[i][j].possible_number.get(n))
    							{
    								puzzle_matrix[i][j].possible_number.remove(n);
    								
    								if(puzzle_matrix[i][j].possible_number.size()==0)
    								{
    									puzzle_matrix[i][j].possible_number.add(0);
    									puzzle_matrix[i][j].possible_number.add(0);
    								}
    								
    								flag = true;
    							}
    						}
    					}
    					if (puzzle_matrix[i][j].possible_number.size() == 1)
    					{
    						//after the check,if the cell only have one number left,then set the number as the value of the cell
    						puzzle_list[number_of_puzzle].one_puzzle[i][j] = puzzle_matrix[i][j].possible_number.get(0);
    						puzzle_matrix[i][j].possible_number.remove(0);
    					}
    				}
    				
    			}
    		}
		}
	}
	
    
    static int lines = 0;//"lines" indicates how many puzzles in the standard input stream
	static int number_of_puzzle = 0;
	static boolean flag = true;
	
    public static void main(String []args) throws InterruptedException{
		char [][] puzzles = new char[20][];
		readlines(puzzles);
		Possible_number puzzle_matrix[][] = new Possible_number[10][10];
		mutiple_puzzle [] puzzle_list = new mutiple_puzzle [lines];
		
		for (int i = 0;i<lines;i++){
			puzzle_list[i] = new mutiple_puzzle();
			puzzle_list[i].one_puzzle = new int [10][10];
		}
		convert_char_to_int(puzzle_list,puzzles);
		
		
		for (;number_of_puzzle<lines;number_of_puzzle++){//loop for each puzzles
			
			for (int i = 0;i<=9;i++){
				for (int j = 0;j<=9;j++){
					puzzle_matrix[i][j] = new Possible_number();
					puzzle_matrix[i][j].possible_number = new ArrayList<Integer>();
					if (puzzle_list[number_of_puzzle].one_puzzle[i][j] == 0){
						for (int k = 0;k<9;k++)
							puzzle_matrix[i][j].possible_number.add(k+1);
					}
				}
			}
			Back_track(puzzle_matrix,puzzle_list);//using back track and Concurrent way to solve problem 
			System.out.println();
		}
		
	}
	
	private static int check_blank(Possible_number[][] puzzle_matrix,
			mutiple_puzzle[] puzzle_list) 
	{
		//check the current board if it is done
		for (int i=1;i<=9;i++)
		{
			for (int j=1;j<=9;j++)
			{
				if (!puzzle_matrix[i][j].possible_number.isEmpty())
				{
					if (puzzle_matrix[i][j].possible_number.get(0) == 0)
					{
						return 2;
					}
					
					return 1;
				}
			}
		}
		return 0;
	}
	
	@SuppressWarnings("unused")
	private static boolean Back_track(Possible_number[][] puzzle_matrix,
			mutiple_puzzle[] puzzle_list) throws InterruptedException
	{
		while (flag)
		{
			flag = false;
			for (int row = 1;row<=9;row++){//set up 9 threads to solve each row
				Row_thread r_t = new Row_thread(row,puzzle_matrix,puzzle_list);
				new Thread(r_t).start();
			}
			Thread.sleep(300);
			for (int col = 1;col<=9;col++){//set up 9 threads to solve each col
				Col_thread c_t = new Col_thread(col,puzzle_matrix,puzzle_list);
				new Thread(c_t).start();
			}
			Thread.sleep(300);
			for (int num=1;num<=9;num++){//set up 9 threads to solve each box
				Box_thread b_t = new Box_thread(num,puzzle_matrix,puzzle_list);
				new Thread(b_t).start();
			}
			Thread.sleep(300);
		}
		if(check_blank(puzzle_matrix,puzzle_list) == 0)
		{
			Print_soduku(puzzle_list);
			return true;
		}
		
		if(check_blank(puzzle_matrix,puzzle_list) == 2)
		{
			return false;
		}

		int MinVal = 10;
		int x=0;
		int y=0;
		for (int i=1;i<=9;i++)
		{//back track to solve the problem,if the current soduku can not be done by the Concurrent method
			for (int j=1;j<=9;j++)
			{
				if (!puzzle_matrix[i][j].possible_number.isEmpty())
				{
					if(puzzle_matrix[i][j].possible_number.size()<MinVal)
					{
						x = i;
						y = j;
						MinVal = puzzle_matrix[i][j].possible_number.size();
					}
				}
			}
		}
	
		int temp[] = new int [puzzle_matrix[x][y].possible_number.size()];
		
		for (int k=0;k<puzzle_matrix[x][y].possible_number.size();k++)
		{
			temp[k] = puzzle_matrix[x][y].possible_number.get(k);
		}
	
		for (int n=0;n<temp.length;n++)
		{
			Possible_number copy_puzzle_matrix[][] = new Possible_number[10][10];
			for (int a=0;a<=9;a++)
			{
				for (int b=0;b<=9;b++)
				{
					copy_puzzle_matrix[a][b] = new Possible_number();
					copy_puzzle_matrix[a][b].possible_number = new ArrayList<Integer>();
					for(int c = 0; c<puzzle_matrix[a][b].possible_number.size();c=c+1)
					{
						copy_puzzle_matrix[a][b].possible_number.add(puzzle_matrix[a][b].possible_number.get(c));
					}
				}
			}
		
			mutiple_puzzle  copy_puzzle_list [] = new mutiple_puzzle[lines];
			
			for (int a=0;a<lines;a++)
			{
				copy_puzzle_list[a] = new mutiple_puzzle();
				copy_puzzle_list[a].one_puzzle = new int [10][10];
				for (int b=0;b<=9;b++)
				{
					for (int c=0;c<=9;c++)
					{
						copy_puzzle_list[a].one_puzzle[b][c] = puzzle_list[a].one_puzzle[b][c];
					}
				}
			}
			
			copy_puzzle_matrix[x][y].possible_number.clear();
			copy_puzzle_list[number_of_puzzle].one_puzzle[x][y] = temp[n];
			
			if(Back_track(copy_puzzle_matrix,copy_puzzle_list))
			{
				return true;
			}
		}
		return false;
	}
	
	private static void Print_soduku(mutiple_puzzle []puzzle_list) {//print the soduku list
			for (int k = 1;k<=9;k++){
				for (int n = 1;n<=9;n++){
					System.out.print(puzzle_list[number_of_puzzle].one_puzzle[k][n]);
				}
			}	
	}

	private static void convert_char_to_int(mutiple_puzzle[] puzzle_list,
			char[][] puzzles) {//convert the char to number
		
		for (int i = 0;i<lines;i++){
			for (int k = 1;k<=9;k++){
				for (int n = 1;n<=9;n++){
					if (puzzles[i][(k-1)*9+n-1] != '.')
						puzzle_list[i].one_puzzle[k][n] = puzzles[i][(k-1)*9+n-1] - 48;
					else
						puzzle_list[i].one_puzzle[k][n] = 0;
				}
			}
		}
		
	}

	private static void readlines(char[][] puzzles) {//get the input stream,store the input in char array
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()){
			String puzzle = in.nextLine();
			puzzles[lines] = puzzle.toCharArray();
			lines++;	
		}
	}
}
