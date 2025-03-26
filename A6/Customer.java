public class Customer{
	private int arrivaltime=0;
	private int servicetime=0;
	private int departuretime=0;

	public Customer(int arrivaltime){
		this.arrivaltime=arrivaltime;
	}	

	public int getArrivalTime(){
		return this.arrivaltime;
	}

	public int getDepartureTime(){ 	
		return this.departuretime;
	}

	public void setDepartureTime(int departuretime){
		this.departuretime=departuretime;
	}
	public void setServiceTime(int servicetime){
		this.servicetime=servicetime;
	}
	public int getServiceTime(){
		return servicetime;
	}	
	
}
