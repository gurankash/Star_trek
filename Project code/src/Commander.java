
class Commander extends Officer{
	private int DeptHead;// Stores the dept for which the Commander may be head for
	private  collection proteges=new collection();//Stores the list of proteges
	private collection LCdeptheads=new collection();//Stores the list of reporting LC Department heads
	public Commander(){pips=3;}// Intialises pips to 3
	public collection getproteges(){return proteges;}// returns the list of proteges.
	public void putdepthead(int x){DeptHead=x;}// Mutates the DepthHead
	public int getdepthead(){return DeptHead;}// returns DeptHead
	public boolean isLCdeptheadsEmpty(){return LCdeptheads.chk();}// Checks if LCdeptheads is empty
	public void putprotege(Personnel x){ proteges.addAtFront(x);}// adds a protege
	public void putproteges(collection x){ proteges=x;}// mutates the entire list of proteges
	public void putLCdeptheads(Personnel x){ LCdeptheads.add(x);}// adds a LCdepthead to the list
	public void putLcdeptheads2(collection x){ LCdeptheads=x;}// mutates the entire LCdepthead list
	public collection getLCdeptheads(){ return LCdeptheads;}// return LCdeptheads list
	public void removeproteges(Personnel x){proteges.delete(x);}// removes  a protege
	public void removeLCdeptheads(Personnel x){LCdeptheads.delete(x);}// removes a LCdepthead from the list
	public void print(){// Instance method suitable for printing a Commander
		super.print();
		if (DeptHead!=0){System.out.println("Additional Status: Head of "+getdepartment(DeptHead)+" department");}
		System.out.println("\nList of Ensign proteges (ID's)");
		if (!(proteges.chk())){System.out.println("None");}
		proteges.printCollection();
		System.out.println("List of Lieutenant commander (ID's): ");
		if (!(LCdeptheads.chk())){System.out.println("None");}
		LCdeptheads.printCollection();
	}

}