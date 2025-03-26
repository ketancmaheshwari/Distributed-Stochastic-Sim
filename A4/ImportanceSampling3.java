/*
Author: Ketan C. Maheshwari
Purpose: To compute the integral of 1/(x^2+cos^x) over (0,pi) using importance sampling method.
Date: 07/10/2005	
*/
import java.lang.*;
import java.util.*;

public class ImportanceSampling3{
	public static void main(String args[]){
		Random generate = new Random();
		double lambda=-1.0d;
		
		int trials=1000;
		int experiments=100;
		double[] integral=new double[experiments];
		double C=1/(1-Math.exp(-Math.PI*lambda));
		double[] observation=new double[trials];
		double U=0d;
		while(lambda<=1.0){
 			for(int exp=0;exp<experiments;exp++){
				for(int i=0;i<trials;i++){
					U=-Math.log(1-generate.nextDouble()/C)/lambda;
					observation[i]=1/(U*U+(Math.cos(U)*Math.cos(U)))*(1/(C*lambda*Math.exp(-U*lambda)));
				}
				integral[exp]=MonteCarloIntegration.mean(observation);
			}
		//	System.out.println(lambda+"	"+MonteCarloIntegration.mean(integral));
			System.out.println(lambda+"	 "+MonteCarloIntegration.stddev(integral));
			lambda+=0.1;
		}
	}
}	
