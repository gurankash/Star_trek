
public class Personnel {

	private String fname;//Stores First name of the personnel
	private String lname;//Stores last name of the personnel
	private double age;//Stores age
	private int SID;// Stores SID
	private double height; //Stores height
	private double yrservice;// Stores years in service
	private int ID;// Stores ID
	public void putfname(String x){fname=x;}// Sets the value first name
	public void putlname(String x){lname=x;}// Sets the value of last name
	public void putage(double x){age=x;}//Sets the value of age
	public void putSID(int x){SID=x;}// sets value of species ID
	public void putheight(double x){height=x;}// Sets value of height
	public void putyrservice(double x){yrservice=x;}// sets the value for years in service
	public void putID(int x){ID=x;}// sets value of ID
	public String getfname(){return fname;}// returns first name
	public String getlname(){return lname;}// returns last name
	public double getage(){return age;}// return age 
	public int getSID(){return SID;}// returns Species ID
	public double getheight(){return height;}// returns height
	public double getyrservice(){return yrservice;}// returns years in service
	public int getID(){return ID;}// returns ID
	// Prints Personnel
	public void print(){System.out.println("First name: "+fname+"\nLast name: "+lname+"\nAge: "+age+" years");
	main.printSID(SID);
	System.out.println("Height: "+" feet\nYears in service: "+yrservice+" years\nID: "+ID);
	}

}
