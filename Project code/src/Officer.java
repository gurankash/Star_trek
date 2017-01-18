
public class Officer extends Personnel{

	protected int department;// Stores department for an officer
	protected int ShID;// Stores the Ship ID for the Officer
	protected double pips;// Stores pips for the Officer
	public double getpips(){return pips;}// Accessor methods that returns number of pips for the officer
	public int getShID(){return ShID;}// Accessor method that returns the ship ID for the officer
	public int getdepartment(){return department;}// Accessor method that returns the department for the officer
	public void putpips(double x){pips=x;}// Mutator methods that mutates the number of pips for the officer
	public void putShID(int x){ShID=x;}// Mutator methods that mutates the Ship ID for the officer
	public void putdepartment(int x){department=x;}// Mutator methods that mutates the department for the officer
	public void print(){super.print();// Instance method suitable for printing an officer
	System.out.print("Department: "+getdepartment(department)+"\nShip ID: "+ShID+"\nPips: "+pips);}
	public static String getdepartment(int x){// Static method that returns the string value for the department
		String y=null;
		switch(x){
		case 1: y=("Command");break;
		case 2:y=("Medical");break;
		case 3:y=("Sciences"); break; 
		case 4:y=("Security");break; 
		case 5:y=("Engineering");break;
		}
		return y;
	}

}
