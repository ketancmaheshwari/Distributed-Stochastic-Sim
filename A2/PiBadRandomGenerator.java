import java.lang.*;
import java.util.*;
import java.math.*;

public class PiBadRandomGenerator{
        static long s=37;
	public static void main(String[] args){

		double x;
		double y;
		double r;

		int hit;
		int miss;

		double pi;

		int binA; // less_than_or_equal_to_19;
		int binB; //between_19_and_23;
		int binC; //greater_than_or_equal_to_24;


		double expected_binA=32.31;
		double expected_binB=37.32;
		double expected_binC=30.37;	


		for(int chi_determination=0;chi_determination<1000;chi_determination++){
			
			double chi_square=0.0;
			double subexpA=0.0;
			double subexpB=0.0;
			double subexpC=0.0;

			binA=0;		//less_than_or_equal_to_19=0;
			binB=0;		//between_19_and_23=0;
			binC=0;		//greater_than_or_equal_to_24=0;

			for(int experiment=0;experiment<100;experiment++){
				hit=0;
				miss=0;
				r=0;
	
				

				for(int i=0;i<100;i++){
					x=dshort_period_rng();
					y=dshort_period_rng();
			
					r=Math.sqrt(x*x + y*y);

					if (r > 1.0){
						miss++;
					}else{
						hit++;
					}	//end if
				
					
				}//end of iteration loop
				
				if(miss<=19){
					binA++;			//less_than_or_equal_to_19
				}else if(miss>19 && miss<24){
					binB++;			//between_19_and_23
				}else if(miss>=24){
					binC++;			//greater_than_or_equal_to_24
				}//end if
				pi=(double)4*hit/(hit+miss);
				

				
				//System.out.println(experiment+"  "+binA+"  "+binB+"  "+binC);

			}//end experiment loop 
			subexpA += (binA - expected_binA)*(binA - expected_binA)/expected_binA;
			subexpB += (binB - expected_binB)*(binB - expected_binB)/expected_binB;
			subexpC += (binC - expected_binC)*(binC - expected_binC)/expected_binC;
			chi_square = subexpA + subexpB + subexpC;
			System.out.println(chi_determination+"  "+chi_square);

		}//end chi_determination loop
	}//end main
        static long short_period_rng() {
                s = (s * 257 + 13);
                s &= 0xffff;
                return s;
        }
        static double dshort_period_rng() {
                return (0.5 + short_period_rng()) / 65536.0;
        }

}//end class

