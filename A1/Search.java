import java.lang.*;
import java.io.*;

public class Search{
	public static void main(String[] args) throws FileNotFoundException,IOException{

		String mystring=null;
		int oldindex=0;
		int index=0;
		int diff=0;
		int count=0;

		BufferedReader in = new BufferedReader(new FileReader(new File(args[1])));

		mystring=in.readLine();

		while(index!=-1){
			oldindex=index;
			index=mystring.indexOf(args[0],index+1);
			count++;
			diff=index-oldindex;
			if(index==-1) break;
			System.out.println(count+" "+diff);
		}

		/*System.out.println();	
		System.out.println("The desired word appears for: ");
		System.out.print(count--);
		System.out.println( " times");*/

	}
}
