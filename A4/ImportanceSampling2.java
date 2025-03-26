/*
Author: Ketan C. Maheshwari
Purpose: To compute the integral of 1/(x^^3+1) over (0,10) using importance sampling method.
Date: 07/10/2005	
*/
import java.lang.*;
import java.util.*;

public class ImportanceSampling2{
	public static void main(String args[]){
		Random generate = new Random();
		int trials=100000;
		int experiments=1000;
		double[] integral=new double[experiments];
		double C2=1/(1-Math.exp(-10));
		double[] observation=new double[trials];
		double U=0d;

 		for(int exp=0;exp<experiments;exp++){
			for(int i=0;i<trials;i++){
				U=-Math.log(1-generate.nextDouble()/C2);
				observation[i]=(1/(U*U*U+1)) / (C2*Math.exp(-U));
			}
			integral[exp]=MonteCarloIntegration.mean(observation);
		}
		System.out.println("Integral:"+MonteCarloIntegration.mean(integral));
		System.out.println("Standard Deviation:"+MonteCarloIntegration.stddev(integral));
		System.out.println("Error:"+MonteCarloIntegration.stddev(integral)/Math.sqrt(trials));
	}
}	
