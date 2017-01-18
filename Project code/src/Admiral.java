
class Admiral extends Officer{
	private int []captains=new int[10];// stores list of Captain ID's
	private int FleetID;// Stores the Fleet ID for the Admiral
	public void putFltID(int x){FleetID=x;}// mutates FleetID
	public int getFltID(){return FleetID;} // returns FleetID
	public void putcaptains(int x){part1.addintarray(captains,x);}// adds a capatin in the list
	public int [] getcaptains(){return captains;}// returns list of captains
	public void print(){// prints Admiral
		System.out.println("Name: "+getfname()+" "+getlname());
		main.printSID(getSID());
		System.out.println("Age: "+getage()+"\nheight: "+getheight()+"\nYears in service: "+getyrservice()+"\nID: "+getID()+"\nDepartment: "+getdepartment(getdepartment())+"\nFleet ID: "+FleetID+"\nCaptains Serving");
		if (captains[0]==0){System.out.println("None");}
		for (int i=0;i<captains.length&&captains[i]!=0;i++){
			System.out.println(i+1+": ID-"+captains[i]);
		}	
	}
}