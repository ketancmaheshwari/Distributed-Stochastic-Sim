import java.util.Random;
import java.io.*;

public class PoissonRandomVariable {

	public static void main(String[] args) throws IOException {
		File datafile=new File("rawdata.dat");
		PrintWriter out = new PrintWriter(new FileWriter(datafile)); 
		double diffsquare=0.0;
		int[] bin=new int[100];
		double expectation=0.0;
		int sum=0;	
		int min=1000;//an arbitrary large value
		int max=0;
		int range=0;
		for(int trial=0;trial<100000;trial++){
			//generate a random number U

			int i=0;
			double p=0.0;
			double lambda=Double.parseDouble(args[0]);
			double F=0.0;
			double X=0.0;
			Random rn = new Random();
			double U=rn.nextDouble();
			
			// set i=0, p=e**-lambda,F=p
			p=Math.exp(-lambda);
			F=p;

			//if U<F, set X=i stop
			//p=lambda*p/(i+1),F=F+p,i=i+1
			//go to step 3

			while(true){
				if(U < F){
					X=i;
					break;
				}//end if
				p=lambda*p/(i+1);
				F+=p;
				i++;
			}//end while
			if(i<min) min=i;
			if(i>max) max=i;
			range=max-min;
			if(range>100)max=min+100;
			bin[i-min]++;
			sum+=i;
			out.println(i);
		}//end outer for
		out.flush();
		out.close();
		expectation=(double)sum/100000;
		BufferedReader br = new BufferedReader(new FileReader(datafile));
		for(int j=0;j<100000;j++){
			int val=Integer.parseInt(br.readLine());
			diffsquare+=(val-expectation)*(val-expectation);
		}
		br.close();
		double variance=diffsquare/100000;
	//	System.out.println(min+" "+max);
		for(int j=0;j<range;j++)
			System.out.println((j+min)+"   "+bin[j]);
		System.out.println("Expecatation: "+ expectation);
		System.out.println("Variance: "+ variance);
		
	}//end main
}//end class
