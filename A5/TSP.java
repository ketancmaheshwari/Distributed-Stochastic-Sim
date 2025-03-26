/*
Author: Ketan C. Maheshwari
Purpose:To implement Traveling salesman problem for 16 cities using simulated annealing method.
Date:16/10/2005
*/

import java.util.*;

public class TSP{
	static City[] cities=new City[16]; //Array of cities to hold the data
	
	public static void main(String[] args){

		double oldtotaldistance=0d;//The distance before a small perturbation
		double newtotaldistance=0d;//The distance after small perturbation

 		int percent_counter=0;//To check how many results have been accepted

		Random generate=new Random();//Random number generator

		City[] old_route=new City[16];
		City[] new_route=new City[16];

		double C=1700d;//The temperature

		double U=0d;//Random Number

		int trials=1000;

		int experiments=1000;
		boolean perturbation_needed=true;

		// Data for the cities here 
		cities[0] = new City(1,"Ithaca",38.24,20.42,'N','E');
		cities[1] = new City(2,"Troy",39.57,26.15,'N','E');
		cities[2] = new City(3,"Cicones",40.56,25.32,'N','E');
		cities[3] = new City(4,"Malea",36.26,23.12,'N','E');
		cities[4] = new City(5,"Lotus_eaters",33.48,10.54,'N','E');
		cities[5] = new City(6,"Eavignana",37.56,12.19,'N','E');
		cities[6] = new City(7,"Aeolia_island",38.42,13.11,'N','E');
		cities[7] = new City(8,"Ithaca2",37.52,20.44,'N','E');
		cities[8] = new City(9,"Laestrygonians",41.23,9.10,'N','E');
		cities[9] = new City(10,"Circeo",41.17,13.05,'N','E');
		cities[10] = new City(11,"Hades",36.08,-5.21,'N','W');
		cities[11] = new City(12,"Sirens",38.47,15.1313,'N','E');
		cities[12] = new City(13,"Scylla_and_Charibdis",38.15,15.35,'N','E');
		cities[13] = new City(14,"Cyclops",37.51,15.17,'N','E');
		cities[14] = new City(15,"Birzebbugia",35.49,14.32,'N','E');
		cities[15] = new City(16,"Calypso",39.36,19.56,'N','E');


		//To generate a random combination of cities
		old_route=generateRandomCombination(cities);

		for(int j=0;j<experiments;j++){
			for(int k=0;k<trials;k++){	
				oldtotaldistance=getTotalDistance(old_route);
				//First perturbance
				if (perturbation_needed==true){
					new_route=swapRandomly(old_route);
					perturbation_needed=false;
				}
				newtotaldistance=getTotalDistance(new_route);//Check for the new total distance
				if(newtotaldistance<oldtotaldistance){
					percent_counter++;
					for(int l=0;l<16;l++)old_route[l]=new_route[l];//accept
					new_route=null;
					new_route=swapRandomly(old_route);
					
				}else{
					if(generate.nextDouble()<Math.exp(-(newtotaldistance-oldtotaldistance)/C)){
						percent_counter++;
						for(int l=0;l<16;l++)old_route[l]=new_route[l];//accept	
						new_route=swapRandomly(old_route);
					}
				}//end of if
			}//end of for loop
			//Alter the Temperature slowly
			C+=0.99999;
		}//end of outer for loop

		System.out.println("Acceptance Probability:"+(double)(percent_counter*100)/(trials*experiments)+" %.");
		System.out.println("The route: ");
		for(int i=0;i<16;i++) System.out.print(new_route[i].number+" ");
		System.out.println(" ");
		System.out.println("The total distance: "+getTotalDistance(new_route));
	}//end main

	// This method returns the total distance for a round trip through a given combinations of cities and back home
	public static double getTotalDistance(City[] combination){
		double dist=0;
		for(int i=0;i<combination.length-1;i++){
			dist+=distance(combination[i],combination[i+1]);
		}
		dist+=distance(combination[combination.length-1],combination[0]);
		return dist;
	}


	//This method gives the distance between two given cities
	public static double distance(City c1,City c2){
		return 6400*Math.acos(Math.sin((Math.PI/180)*c1.longitude)*Math.sin((Math.PI/180)*c2.longitude)+Math.cos((Math.PI/180)*c1.longitude)*Math.cos((Math.PI/180)*c2.longitude)*Math.cos(((Math.PI/180)*c1.latitude)-((Math.PI/180)*c2.latitude)));

	}//end method distance

	
	//This method returns a random vector of a given size without duplication with all members from 0 to <size> 
	public static int[] generateRandomVector(int size){
		Random generator=new Random();
		boolean dupflag=false;
		int[] randomvector=new int[16];
	        for(int i=0;i<16;i++){
			randomvector[i]=generator.nextInt(16);
			dupflag=true;
			while(dupflag==true){
				dupflag = false;
				for(int j=0;j<i;j++){
					if(randomvector[j]==randomvector[i]){
						dupflag=true;
						randomvector[i]=generator.nextInt(16);
						break;
					}//end if
				}//end inner for
			}//end while
		}//end outer for
		return randomvector;
	}//end method generateRandomVector

	
	//This method returns a random combination of cities
	public static City[] generateRandomCombination(City[] cities){
		int[] order=generateRandomVector(16);
		City[] temp=new City[16];	
		for(int i=0;i<16;i++)
			temp[order[i]]=cities[i];
		return temp;
	}//end method generateRandomCombination


	//This method returns the list of cities with  randomly swapping one pair of cities()
        public static City[] swapRandomly(City[] arg_cities){
                Random generate=new Random();
		City temp;
		City[] local_use = new City[16];	
		City[] ret=new City[16];
		int a=generate.nextInt(16);
		int b=generate.nextInt(16);
		while(b==a){//To avoid duplication
			b=generate.nextInt(16);
		}
		for(int i=0;i<arg_cities.length;i++)local_use[i]=arg_cities[i];
            
               //swap 
               	temp=local_use[a];
		local_use[a]=local_use[b];
		local_use[b]=temp;
		
		for(int m=0;m<16;m++)ret[m]=local_use[m];	      

                return ret;
        }//end method swapRandomly
}//end class
