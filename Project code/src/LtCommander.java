
class LtCommander extends Lieutenant{
	private int head;// Stores the dept for which the Lt. Commander may be head for
	private collection RpOfficers=new collection();// Stores the list of Reporting Officers
	public LtCommander(){pips=2.5;}// Constructor that initialises pips to 2.5
	public int gethead(){return head;}// returns head
	public collection getRpOfficers(){return RpOfficers;}// returns list of reporting officer
	public void putRpOfficers(Personnel x){RpOfficers.addAtFront(x);}// adds reporting officer
	public void puthead(int x){head=x;}// put the dept for which the Lt. Commander is the head

	public void removeRpOfficer(Personnel y){RpOfficers.delete(y);}// Removes a reporting officer from the list
	public void print(){// Instance method suitable for priniting Lt. Commander
		super.print();
		if (head!=0)
			System.out.println("Status: Head of"+getdepartment(head)+" department\nList of reporting officers (ID's)");
		if (!(RpOfficers.chk())){System.out.println("None");}
		RpOfficers.printCollection();
	}
}
