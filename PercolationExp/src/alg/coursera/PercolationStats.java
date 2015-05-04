package alg.coursera;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
	private double[] fractions;
	private int T;
	private int N;
	public PercolationStats(int N, int T) {
		this.T = T;
		this.N = N;
		fractions = new double[T];
		for (int t = 0; t < T; t++){
			int openCount = 0;
			Percolation percolation = new Percolation(N);
			while( !percolation.percolates() ){
				int x = StdRandom.uniform(1, N+1);
				int y = StdRandom.uniform(1, N+1);
				if(percolation.isOpen(x, y)) {
					continue;
				}
				else {
					percolation.open(x, y);
					openCount++;
				}
			}
			fractions[t] = (double)openCount / (N*N);
		}
	}
	public double mean() {                     // sample mean of percolation threshold
		double sum = 0;
		for(int i = 0; i < T; i++)
			sum += fractions[i];
		return sum/T;
	}
	public double stddev() {                    // sample standard deviation of percolation threshold
		double sum = 0;
		double mean = mean();
		for(int i = 0; i < T; i++)
			sum += Math.pow((fractions[i] - mean), 2);
		return Math.sqrt(sum / (T - 1));
	}
	public double confidenceLo() {              // low  endpoint of 95% confidence interval
		return mean() - 1.96*stddev()/Math.sqrt(T);
	}
	public double confidenceHi() {             // high endpoint of 95% confidence interval
		return mean() + 1.96*stddev()/Math.sqrt(T);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats percolationStats = new PercolationStats(2, 100000);
		System.out.print("mean\t= ");
		System.out.print(percolationStats.mean());
		System.out.println();
		System.out.print("stddev\t= ");
		System.out.print(percolationStats.stddev());
		System.out.println();
		System.out.print("95% confidence interval\t= ");
		System.out.print(percolationStats.confidenceLo());
		System.out.print(" ");
		System.out.print(percolationStats.confidenceHi());
		
	}

}
