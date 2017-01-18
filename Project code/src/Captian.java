
class Captain extends Executive{
	private collection commanders=new collection();// Stores list of Commanders (linked list)
	public Captain(){pips=4;}// Constructor- intialises pips to 4
	public void putcommanders(Personnel x){commanders.add(x);}// adds a commander to list
	public boolean  iscommandersEmpty(){return commanders.chk();}// check to see if commanders list is empty 
	public collection getcommanders(){return commanders;}// returns the list of commander
	public void removecommanders(Personnel x){commanders.delete(x);}// removes a commander from the list
	public void print(){//instance method that prints Captain 
		super.print();
		System.out.println("\nCommanders under supervision (ID's)");
		if (!(commanders.chk())){System.out.println("None");}
		commanders.printCollection();
		System.out.println("XO officer under supervision");
		if (!(commanders.chk())){System.out.println("None");}Starship.getExo();}
}