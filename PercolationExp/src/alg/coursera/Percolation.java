/**
 * This is my impletation of Percolation exercise
 * @author hehe
 */
package alg.coursera;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private WeightedQuickUnionUF wquf; //to do union find on grid
	private WeightedQuickUnionUF wquf2; //this one has no virtural bottom, to handle backwash
	private int[] grid;
	private int N;
	public Percolation(int N) { //N-by-N grid
		this.N = N;
		wquf = new WeightedQuickUnionUF(N*N+2); //there are to virtual site
		wquf2 = new WeightedQuickUnionUF(N*N+1); // no virtual bottom
		//on the top which locates [N*N] and bottom which is [N*N+1]
		grid = new int[N*N];    //is an array in reality
	}
	/**
	 * @param i 2D x in XY-coordinate 
	 */
	private int xyTo1D(int i, int j){
		return (i-1)*N + (j-1); // handle 2d coordinate
	}
	
	public boolean isOpen(int i, int j) { // is site (row i, column j) open?
	    return grid[xyTo1D(i, j)] == 1;
	}
	
	public void open(int i, int j) {        // open site (row i, column j) if it is not open already
		if(isOpen(i, j) == false){
			grid[xyTo1D(i, j)] = 1; //set to 1
			//is this open site on top row?
			if(i == 1){
				wquf.union(xyTo1D(i, j), N*N);
				wquf2.union(xyTo1D(i, j), N*N);
			}
			else if(i == N){ //is this open site on bottom row?
				wquf.union(xyTo1D(i, j), N*N+1);
			}
			//connect to adjacent open site,left up right down
			if(i > 1){
				if(isOpen(i-1, j)){
					wquf.union(xyTo1D(i, j), xyTo1D(i-1, j));
					wquf2.union(xyTo1D(i, j), xyTo1D(i-1, j));
				}				
			}
			if(j > 1){
				if(isOpen(i, j-1)){
					wquf.union(xyTo1D(i, j), xyTo1D(i, j-1));
					wquf2.union(xyTo1D(i, j), xyTo1D(i, j-1));
				}				
			}
			if(i < N){
				if(isOpen(i+1, j)){
					wquf.union(xyTo1D(i, j), xyTo1D(i+1, j));
					wquf2.union(xyTo1D(i, j), xyTo1D(i+1, j));
				}				
			}
			if(j < N){
				if(isOpen(i, j+1)){
					wquf.union(xyTo1D(i, j), xyTo1D(i, j+1));
					wquf2.union(xyTo1D(i, j), xyTo1D(i, j+1));
				}				
			}
		}
			
	}
	public boolean isFull(int i, int j){
		if(wquf2.connected(xyTo1D(i, j), N*N)) //is connected to virtual top? 
			return true;
		return false;
	}
	public boolean percolates() {             // does the system percolate?
		if(N == 1)
			return true;
		else
			return wquf.connected(N*N, N*N+1);
	}
	public void printGrid(){
		for(int i=1; i<=N; i++){
			for(int j=1; j<=N; j++){
				System.out.print(grid[xyTo1D(i, j)]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public void printXY1D(int i, int j){
		System.out.println(xyTo1D(i, j));
	}
	
	public static void main(String[] args){
		Percolation percolation = new Percolation(1);
//		percolation.open(1, 5);
//		percolation.open(1, 4);
//		percolation.open(2, 4);
//		percolation.open(3, 4);
//		percolation.open(4, 4);
//		percolation.open(4, 2);
//		percolation.open(4, 3);
//		percolation.open(5, 3);
		//percolation.open(5, 5);
		percolation.printGrid();	
		System.out.println(percolation.wquf.count());
		System.out.println(percolation.percolates());
	}
}
