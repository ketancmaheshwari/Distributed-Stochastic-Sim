/*
Date: 22.10.2005
Author: Ketan C Maheshwari
Purpose: This program simulates a queuing system behaving in a discrete manner observing poisson arrival rate...
*/

import java.util.*;

public class DES{

	public static void main(String[] args) throws Exception{
		double W=0d;//average waiting time
		double L=0d;//average queue length
		double Q=0d;//average number of customers in the system
		double T=0d;//average throughput time

		int experiments=1000;	
		// Variables
		Customer temp=new Customer(0);

		int arrivaltimer=0;
		int servicetimer=0;

		double lambda=Double.parseDouble(args[0]);//Customer arrival rate
		double mu=Double.parseDouble(args[1]);//Service time
		int O=Integer.parseInt(args[2]);//Observation time units
		int cust_arrival_interval=0;
		int cust_service_interval=0;

		Q ourqueue=new Q();

		System.out.println("Descrete Event Simulation");
		System.out.println("Lambda= "+lambda+" mu= "+mu+" Observation interval O = "+O);
		
		for(int exp=0;exp<experiments;exp++){
			cust_arrival_interval=poisson(lambda);
			cust_service_interval=0;
		
			//Start observation
			for(int virtual_time=0;virtual_time<O;virtual_time++){
				arrivaltimer++;

				if(arrivaltimer==cust_arrival_interval){
					arrivaltimer=0;
					ourqueue.insert(new Customer(virtual_time));
					cust_arrival_interval=poisson(lambda);
				}//end if

				//simulate service and departure 
				if(servicetimer==cust_service_interval){
					servicetimer=0;
					if(!ourqueue.isEmpty()){
						temp=(Customer)ourqueue.remove();
						temp.setServiceTime(cust_service_interval);
						W+=virtual_time-(temp.getArrivalTime()+temp.getServiceTime());
						T+=virtual_time-temp.getArrivalTime();
					}
					cust_service_interval=poisson(mu);//for next customer
				}//end if
				servicetimer++;

				L+=ourqueue.getCurrentSize();
				Q+=ourqueue.getCurrentSize()+1;
			
			}//end for

			//if still some customers are left in the queue
			while(!ourqueue.isEmpty()){
				temp=(Customer)ourqueue.remove();
				W+=O-temp.getArrivalTime();
			}
		}
		System.out.println("Average waiting time: " + W/(O*experiments));
		System.out.println("Average queue length:"+ L/(O*experiments));	
		System.out.println("Average number of Customers in the System:"+Q/(O*experiments));
		System.out.println("Average throughput time:"+T/(O*experiments));
	}//end main

	public static int poisson(double lambda){
	int i=0;
		double p=0d;
		double F=0d;
		p=Math.exp(-lambda);
		F=p;
		Random generate=new Random(System.currentTimeMillis());
		while(true){
			if(generate.nextDouble()<F)return i;
			p=lambda*p/(i+1);
			F+=p;
			i++;
		}//end while
	}//end poisson
}//end class DES
