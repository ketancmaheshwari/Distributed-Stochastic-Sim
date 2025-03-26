import java.lang.*;
import java.util.Random;
//generate a random number U
public class PoissonRandomUsingLog{
	public static void main(String[] args){
		int lambda=1000;
		int myi=0;
		double FI=0.0;	
		Random rn = new Random();
		double U=0.0;
		for(int trial=0;trial<100;trial++){
			 myi=lambda;
			 FI=0.0;
			 U=rn.nextDouble();
			for(int i=0;i < lambda;i++){
				FI+=Math.exp(F(i,lambda));
			}
			if(U<=FI){
				while(U<=FI){
					myi--;
					FI=FI-Math.exp(F(myi,lambda));
				}//end of while
			}else{
				while(U>FI){
					myi++;
					FI=FI+Math.exp(F(myi,lambda));
				}//end of while
			}//end of ifelse
			System.out.println(myi);
		}//end of trial loop
	}//end of main

	public static double F(int I,int lambda){
		if(I==0) return (-lambda);
		return (Math.log(lambda)-Math.log(I)+F(I-1,lambda));
	} 
}

