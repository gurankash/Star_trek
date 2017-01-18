
class Starship{
	// Feilds of the class
	private String registrynum;
	private int FleetID;
	private double Ptropedoes;
	private double Fshields;
	private double Ashields;
	private int Sclass;
	private Captain capt;
	private static Executive Exofficer;
	private collection allpersonnel=new collection();
	public void putregistrynum(String x){registrynum=x;}// mutates registry number
	public String getregistrynum(){return registrynum;}// returns regesitry num
	public void putFleetID(int x){FleetID=x;}//Mutator 
	public int getFleetID(){return FleetID;}//Accessor
	public void putAshilds(double x){Ashields=x;}//Mutator 
	public double getAshilds(){return Ashields;}//Accessor
	public void putPtropedoes(double x){Ptropedoes=x;}//Mutator 
	public void putFshields(double x){Fshields=x;}//Mutator 
	public double getFshields(){return Fshields;}//Accessor
	public double getPtropedoes(){return Ptropedoes;}//Accessor
	public int getSclass(){return Sclass;}//Accessor
	public void putSclass(int x){Sclass=x;}//Mutator 
	public collection getallpersonnel(){return allpersonnel;}//Accessor
	// prints Starship
	public void print(){System.out.println("\t\t\t\t\tShip Info\nResgistry Number: "+registrynum+"\nFleet ID: "+FleetID+"\nPhton tropedoes: "+Ptropedoes+"\nForward Shields(%): "+Fshields+"\nAfter Shields (%): "+Ashields);
	getmaxcapacity(0);
	System.out.println("Captain ID: "+capt.getID());
	if (Exofficer==null){
		System.out.println("Executive Officer not yet created");
	}
	else {System.out.println("Executive Officer ID: "+Exofficer.getID());}
	}

	public void add(Personnel x){allpersonnel.add(x);}// adds Perssonel
	public void putcapt(Captain x){capt=x;}// adds captain
	public void putExc(Personnel x){Exofficer=(Executive)x;}//adds Executive
	public static Executive getExo(){return Exofficer;}// returns Executive
	public static void removeExo(){ Exofficer=null;}// removes Executive
	public int getmaxcapacity(int p){// returns max capacity 
		if (Sclass==1){if (p!=1){System.out.println("Ship Class: Sovereign \nComplement: "+2000+"\nTropedo banks: "+775);}return 2000;}
		else if(Sclass==2){if (p!=1){System.out.println("Ship Class: Galaxy class\nComplement: "+1750+"\nTropedo banks: "+400);}return 1750;}
		else if(Sclass==3){if (p!=1){System.out.println("Ship Class: Ambassador class \nComplement: "+1350+"\nTropedo banks: "+350);}return 1350;}
		else if(Sclass==4){if (p!=1){System.out.println("Ship Class: Excelsior class \nComplement: "+850+"\nTropedo banks: "+200);}return 850;}
		else if(Sclass==5){if (p!=1){System.out.println("Ship Class: Voyager class \nComplement: "+850+"\nTropedo banks: "+375);}return 850;}
		else {if (p!=1){System.out.println("Ship Class: Defiant class\nComplement: "+100+"\nTropedo banks: "+550);}return 100;}
	}
	public void putclass(int x){Sclass=x;}// mutates Ship class
	public int findlist(double x){return allpersonnel.findlist(x);}// return the number of officers depending on pips
	public collection printlist(double x){return allpersonnel.printlist(x);}}// returns a linked list of desired Personnel

