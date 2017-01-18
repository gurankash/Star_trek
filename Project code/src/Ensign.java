
class Ensign extends Officer{
	private int mentor;// Stores the mentor ID for the Ensign 
	private int supervisor;// Stores the Supervisor ID for the Ensign
	public Ensign(){pips=1;}// Constructor that initialises pips to 1
	public void putmentor(int x){mentor=x;}// Mutator method that mutates the mentor ID for the Ensign
	public void putsupervisor(int x){supervisor=x;}// Mutator method that mutates the mentor ID for the Ensign 
	public int getsupervisor(){return supervisor;}//// Accessor method that returns the supervisor ID for the Ensign
	public int getmentor(){return mentor;}	//Accessor method that returns the mentor ID for the Ensign
	public void print()// Instance method suitable for priniting an Ensign
	{super.print();System.out.println("Mentor ID: "+mentor+"\nSuprervisor ID: "+supervisor);}
}
