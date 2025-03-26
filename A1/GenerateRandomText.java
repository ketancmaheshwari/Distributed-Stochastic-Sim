import java.lang.*;
import java.io.*;
//import java.Math.*;

public class GenerateRandomText{
	static java.util.Random random = new java.util.Random(System.currentTimeMillis());
	public static void main(String[] args) throws IOException{
		String filename = "Random.txt";
		File outputfile=new File(filename);
		FileWriter out = new FileWriter(outputfile);
		for(long i=0;i<2523851;i++){
			char ch=(char)rand(65,90);
			out.write(ch);
		}
		out.close();
	}

	public static int rand(int lo, int hi){
		int n = hi - lo + 1;
		int i = random.nextInt() % n;
		if (i < 0)
			i = -i;
		return lo + i;
	}
}
