public class Expectation{
	public static void main(String[] args){
	//find out the expectation value of occurence of a three character word on a text file with n characters
		long n=2523851;
		float exp=0;
		exp=(float)n*1/(26*26*26);
		System.out.println(exp);
	}
}

