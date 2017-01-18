
abstract class Lieutenant extends Officer{
	private int supervisor; // Stores the Supervisor ID for the objects which are instance of one of the types of Lieutenant
	public void putsupervisor(int x){supervisor=x;}// Mutates supervisor ID
	public int getsupervisor(){return supervisor;}// return supervisor ID
	public void print(){super.print();System.out.println("Suprervising Officer ID: "+supervisor);}// Instance method suitable for printing a Lieutenant
}