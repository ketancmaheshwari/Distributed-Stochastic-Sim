public class TheoreticalDES{
	public static void main(String[] args){
		double lambda=Double.parseDouble(args[0]);
		double mu=Double.parseDouble(args[1]);
		double W=lambda/(mu*(mu-lambda));
		double L=(lambda*lambda)/(mu*(mu-lambda));
		double Q=lambda/(mu-lambda);
		double T=1/(mu-lambda);	
		System.out.println("Expected waiting time: "+W);
		System.out.println("Expected queue length: "+L);
		System.out.println("Expected number of units in the system: "+Q);
		System.out.println("Expected throughput time of a unit: "+T);
	}
}
