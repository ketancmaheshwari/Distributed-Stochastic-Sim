/* 
Author:  Ketan C Maheshwari
Purpose: This program attempts to find out the approximated value of integral of exp(-x) over the continuous interval (0,1) using The Monte Carlo Method.
This is solution to Question A	of the fourth assignment
Date: 04/10/2005 
*/

import java.lang.*;
import java.util.*;
import java.io.*;

public class MonteCarloIntegration{
	public static void main(String[] args) throws IOException{
		
		
		PrintWriter out=new PrintWriter(new FileWriter(new File("montecarloresults.txt")));

		int n=Integer.parseInt(args[0]);
		double[] observation=new double[n];
		double U=0d;
		double sum_of_square=0d;
		double theoretical_integral=0d;
		double estimated_error=0d;
		double computed_error=0d;
		int subset=20;
		double[] temp=new double[n/subset];
		double[] array_of_mean=new double[subset];
		
		System.out.println("*********MC Integration********");
		System.out.println("The output is available on montecarloresults.txt file.");

		Random generate=new Random();
		
		for(int i=0;i<n;i++){
			U=generate.nextDouble();
			observation[i]=Math.exp(-U);
		}
		theoretical_integral=1-Math.exp(-1);
		estimated_error=Math.abs(mean(observation)-theoretical_integral);
		computed_error=stddev(observation)/Math.sqrt(n);
		out.println("Number of trials: "+n);
		out.println("The value of integral(theta) is: "+mean(observation));
		out.println("The standard deviation is: "+stddev(observation));
		out.println("Theoretical value of integral is: "+theoretical_integral);
		out.println("The estimated value of error is: "+estimated_error);
		out.println("The computed value of error using \"std_dev/sqrt(n)\" is: "+computed_error);
		//divide the observations into 20 groups
		out.println("***Mean of 20 subsets***");
		for(int l=0;l<subset;l++){
			for(int m=0;m<n/subset;m++){
				temp[m]=observation[subset*l+m];
			}
			array_of_mean[l]=mean(temp);
			out.println("subset "+l+" "+ stddev(temp));
		}
		double stddev_of_subsets=stddev(array_of_mean);	
		out.println("Observed Standard Deviation of 20 subsets is: "+stddev_of_subsets);
		out.println("The estimated error for 20 subsets is: "+stddev_of_subsets/Math.sqrt(subset));
		out.close();
	}

	public static double mean(double[] array){
		double sum=0d;
		for(int i=0;i<array.length;i++)	
			sum+=array[i];
		return sum/array.length;
	}

	public static double stddev(double[] array){
		double current_mean=mean(array);
		double sum_of_square=0d;
		for(int i=0;i<array.length;i++)
			sum_of_square+=(array[i]-current_mean)*(array[i]-current_mean);
		return sum_of_square/array.length;
	}
}
