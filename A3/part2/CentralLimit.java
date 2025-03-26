import java.util.*;
import java.lang.*;
import java.io.*;

public class CentralLimit{
	public static void main(String[] args) throws IOException{ 
		File datafile=new File("datafile.dat");
                PrintWriter out = new PrintWriter(new FileWriter(datafile));

		Random generator = new Random();
		double diffsquare=0.0;
		double controller=0.0;
		double xi=0.0;
		int N=100;
		int max_trials=10000;
		double internal_sum=0.0;
		double zeta=0.0;
		double tot=0.0;
		double mean=0.0;
		double variance=0.0; 
		int[] bin=new int[20];

		for(int trial=0;trial<max_trials;trial++){
			internal_sum=0.0;
			for(int i=0;i<N;i++){
				controller=generator.nextDouble();
				xi = generator.nextDouble();
				if(controller<0.5) xi=-xi;
				internal_sum+=xi;		
			}//end of generator for loop
			zeta=internal_sum/N;
			tot+=zeta;	
			if(zeta>=-1.0 && zeta<=-0.9) bin[0]++;
			else if(zeta>-0.9 && zeta<=-0.8) bin[1]++;
			else if(zeta>-0.8 && zeta<=-0.7) bin[2]++;
			else if(zeta>-0.7 && zeta<=-0.6) bin[3]++;
			else if(zeta>-0.6 && zeta<=-0.5) bin[4]++;
			else if(zeta>-0.5 && zeta<=-0.4) bin[5]++;
			else if(zeta>-0.4 && zeta<=-0.3) bin[6]++;
			else if(zeta>-0.3 && zeta<=-0.2) bin[7]++;
			else if(zeta>-0.2 && zeta<=-0.1) bin[8]++;
			else if(zeta>-0.1 && zeta<=0.0) bin[9]++;
			else if(zeta>0.0 && zeta<=0.1) bin[10]++;
			else if(zeta>0.1 && zeta<=0.2) bin[11]++;
			else if(zeta>0.2 && zeta<=0.3) bin[12]++;
			else if(zeta>0.3 && zeta<=0.4) bin[13]++;
			else if(zeta>0.4 && zeta<=0.5) bin[14]++;
			else if(zeta>0.5 && zeta<=0.6) bin[15]++;
			else if(zeta>0.6 && zeta<=0.7) bin[16]++;
			else if(zeta>0.7 && zeta<=0.8) bin[17]++;
			else if(zeta>0.8 && zeta<=0.9) bin[18]++;
			else if(zeta>0.9 && zeta<=1.0) bin[19]++;

			out.println(zeta);

		}//end of trial loop
		mean=tot/max_trials;
		out.close();
		for(int i=0;i<20;i++){
			System.out.println(bin[i]);
			
		}
		BufferedReader br = new BufferedReader(new FileReader(datafile));
                for(int j=0;j<max_trials;j++){
                        double val=Double.parseDouble(br.readLine());
                        diffsquare+=(val-mean)*(val-mean);
                }
                br.close();
                variance=diffsquare/max_trials;

		System.out.println("Mean:"+ mean);
		System.out.println("Variance:"+ variance);

	}//end main
		
} 
