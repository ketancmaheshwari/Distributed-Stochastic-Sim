/*
Author: Ketan C. Maheshwari
Purpose: To compute the integral of 1/(x^^3+1) over (0,10) using importance sampling method.
Date: 07/10/2005	
*/
import java.lang.*;
import java.util.*;

public class ImportanceSampling{
	public static void main(String args[]){
		Random generate = new Random();
		int trials=100000;
		int experiments=1000;
		double[] integral=new double[experiments];
		double C1=1/10d;
		double[] observation=new double[trials];
		double U=0d;
 		for(int exp=0;exp<experiments;exp++){
			for(int i=0;i<trials;i++){
				U=10*generate.nextDouble();
				observation[i]=1/((U*U*U+1)*C1);
			}
			integral[exp]=MonteCarloIntegration.mean(observation);
//			System.out.println(integral[exp]);
		}
		System.out.println("Integral: "+MonteCarloIntegration.mean(integral));
		System.out.println("Standard Deviation: "+MonteCarloIntegration.stddev(integral));
		System.out.println("Error:"+MonteCarloIntegration.stddev(integral)/Math.sqrt(trials));
	}
}	
