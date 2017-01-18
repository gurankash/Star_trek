import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


//Linked List class for weapons
class List1 {
	private Weapon head;

	//reads weapons from file and substrings attributes according to breaks inserted in file
	public void read() throws IOException{
		try	{
			String x;		
			FileReader fr = new FileReader("J:\\weapon.txt");
			BufferedReader br = new BufferedReader(fr);
			int fir, sec;
			while ( (x = br.readLine() ) != null ) {
				fir = 0;
				sec = x.indexOf('|');								
				addatEnd(Integer.parseInt(x.substring(0,fir=sec)), x.substring(fir+1,sec=x.indexOf('|', fir+1)), x.substring(sec+1,fir=x.indexOf('|', sec+1)), Double.parseDouble(x.substring(fir+1,sec=x.indexOf('|', fir+1))), Integer.parseInt(x.substring(sec+1)) );
			}	
			br.close();
		}
		catch (IOException e){}
		catch (NumberFormatException e){}		
	}

	//writes weapons to file and writes all attributes with breaks in between
	public void write() throws IOException{
		try {
			int intunlock;
			FileWriter fw = new FileWriter("J:\\weapon.txt");
			PrintWriter pw = new PrintWriter (fw);
			for (Weapon temp = head; temp != null; temp = temp.link) {
				if (temp.unlocked == true ) {
					intunlock = 1;
				}
				else {
					intunlock = 0;
				}
				pw.println(temp.num+"|"+temp.name+"|"+temp.info+"|"+temp.range+"|"+intunlock);
			}
			pw.close();     		
		}
		catch (IOException e){}
	}

	//to see if weapon ID is already in use
	//returns true if a match is found, otherwise returns false
	public boolean checkID(int wunl) {
		boolean chk = false;
		for (Weapon temp = head; temp != null; temp = temp.link) {
			if (temp.num == wunl || wunl == 0) { 
				chk = true;
			}
		}
		return chk;	
	}

	//adds a node of Weapon at the end, with info as parameters
	public void addatEnd(int n, String nam, String inf, double ran, int intunlock) {
		boolean change = true;
		boolean unlock;
		if (intunlock == 1) {
			unlock = true;
		}
		else {
			unlock = false;
		}
		if (head == null){
			head = new Weapon(n, nam, inf, ran, unlock, null);
		}
		else {
			for (Weapon temp = head; change == true; temp = temp.link) {
				if (temp.link == null) {
					temp.link = new Weapon(n, nam, inf, ran, unlock, null);
					change = false;
				}
			}
		}
	}

	//prints all weapon name and ID only
	public void printMenu() {
		for (Weapon temp = head; temp != null; temp = temp.link) {
			System.out.println(temp.num + " - " + temp.name);
		}
	}

	//prints all weapons with their info
	public void printAll() {
		for (Weapon temp = head; temp != null; temp = temp.link) {
			System.out.println("Name: " + temp.name);
			System.out.println("Description: " + temp.info);
			System.out.println("Range: " + temp.range + " Au");
			System.out.println();
		}
	}

	//prints weapon info corresponding to the weapon ID send in as parameter
	public void printNum(int x, int [] weaponlock) {
		int t = 1;
		boolean y=true;
		Weapon print= head;;
		Weapon temp = head;
		System.out.println(weaponlock[0]);
		for (temp = head; temp != null && y; temp = temp.link, t++) {
			for (int i=0;i<weaponlock.length && weaponlock[i]!=0&&y;i++){
				if (weaponlock[i]==temp.num && temp.num==x ){
					y=false;
					print=temp;
				}
			}
		}
		if (!y) {
			System.out.println("Name: " + print.name + " (Weapon IG: " + print.num + ")");
			System.out.println("Description: " + print.info);
			System.out.println("Range: " + print.range + " Au");
		}
		else {
			System.out.println("Must Unlock the Weapon using Explore Option in order to View Full Info.");
		}		
	}



	//receives a parameter of the weapon ID, and unlocks the weapon, prints the weapon name unlocked with it's ID
	public void unlocked (int x) {
		int t = 1;
		Weapon temp = head;
		for (temp = head; temp != null && t != x+1; temp = temp.link, t++) {
			if (t == x) {
				temp.unlocked = true;
				System.out.println("Weapon Unlocked: " + temp.name + " (Weapon ID: " + temp.num + ")");
			}			
		}
	}

	//defines Weapon node with 5 attributes(fields) assigned to each weapon with 1 link
	class Weapon {
		private int num;
		private String name;
		private String info;
		private double range;
		private boolean unlocked;
		private Weapon link;

		//constructor initialises all fields to it's corresponding parameters
		public Weapon(int n, String nam, String inf, double ran, boolean unlock, Weapon s) {
			num = n;
			name = nam;
			info = inf;
			range = ran;
			unlocked = unlock;
			link = s;
		}
		public void putname (String x){name=x;}
		public void putnum (int x){num=x;}
		public void putinfo(String x){info=x;}
		public void putrange (double x){range=x;}
		public void putunlocked (boolean x){unlocked=x;}
	}

}
