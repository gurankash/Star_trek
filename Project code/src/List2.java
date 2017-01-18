import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


//Linked List class for planets
class List2 {
	private Planet head;

	//reads planets from file and substrings attributes according to breaks inserted in file
	public void read() throws IOException{
		try	{
			String x;		
			FileReader fr2 = new FileReader("J:\\planet.txt");
			BufferedReader br2 = new BufferedReader(fr2);
			int fir2, sec2;
			while ( (x = br2.readLine() ) != null) {
				fir2 = 0;
				sec2 = x.indexOf('|');
				addatEnd(Integer.parseInt(x.substring(0,fir2=sec2)), x.substring(fir2+1,sec2=x.indexOf("|",fir2+1)), x.substring(sec2+1,fir2=x.indexOf("|",sec2+1)), Double.parseDouble(x.substring(fir2+1,sec2=x.indexOf("|",fir2+1))), x.substring(sec2+1,fir2=x.indexOf("|",sec2+1)), x.substring(fir2+1,sec2=x.indexOf("|",fir2+1)), Double.parseDouble(x.substring(sec2+1,fir2=x.indexOf("|",sec2+1))), Integer.parseInt(x.substring(fir2+1)) );
			}
			br2.close();
		}
		catch (IOException e){}
		catch (NumberFormatException e){}		
	}

	//writes planets to file and writes all attributes with breaks in between
	public void write() throws IOException{
		try {
			FileWriter fw2 = new FileWriter("J:\\planet.txt");
			PrintWriter pw2 = new PrintWriter (fw2);
			for (Planet temp = head; temp != null; temp = temp.link) {
				pw2.println(temp.num+"|"+temp.name+"|"+temp.atmosphere+"|"+temp.gravity+"|"+temp.lifeforms+"|"+temp.landcomp+"|"+temp.distance+"|"+temp.wunlock);
			}
			pw2.close();     		
		}
		catch (IOException e){}
	}

	//writes planets to file and skips those to be deleted(x)
	public void delete(int x) throws IOException{
		try {
			FileWriter fw = new FileWriter("J:\\planet.txt");
			PrintWriter pw = new PrintWriter (fw);
			for (Planet temp = head; temp != null; temp = temp.link) {
				if (x != temp.num) {
					pw.println(temp.num+"|"+temp.name+"|"+temp.atmosphere+"|"+temp.gravity+"|"+temp.lifeforms+"|"+temp.landcomp+"|"+temp.distance+"|"+temp.wunlock);
				}
			}
			pw.close();     		
		}
		catch (IOException e){}
	}

	//checks to see how many planets are associated with weapon ID(d)
	//returns false if only 1 planet, other returns true
	public boolean doubleID (int d) {
		int counter = 0;
		int weaponid = 0;
		for (Planet temp = head; temp != null; temp = temp.link) {			
			if (temp.num == d) {
				weaponid = temp.wunlock;
			}
		}
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.wunlock == weaponid ) {
				counter++;
			}
		}
		if (counter == 1) {
			return false;
		}
		else {
			return true;
		}		
	}

	//returns the weapon ID associated with the planet ID(c)
	//0 represents no weapons
	public int getweapon(int c) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == c) {
				return temp.wunlock;
			}
		}
		return 0;
	}
	//accessor method for planet name
	public String getname (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.name;
			}
		}
		return "";	
	}
	//accessor method for planet atmosphere
	public String getatmos (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.atmosphere;
			}
		}
		return "";
	}
	//accessor method for planet gravity
	public double getgrav (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.gravity;
			}
		}
		return 0;		
	}
	//accessor method for planet life forms
	public String getlife (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.lifeforms;
			}
		}
		return "";	
	}
	//accessor method for planet land composition
	public String getland (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.landcomp;
			}
		}
		return "";
	}
	//accessor method for distance away from earth
	public double getdist (int nm) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == nm) { 
				return temp.distance;
			}
		}
		return 0;		
	}	


	//to see if planet ID is already in use
	//returns true if a match is found, otherwise returns false if match is found with new ID(x) or not found
	public boolean checkID(int idnum,int x) {
		boolean chk = false;
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == idnum) { 
				chk = true;
				if (temp.num==x){
					chk=false;
				}
			}
		}
		return chk;	
	}

	public void editPlanet(int x, int nm, String n, String at, double grav, String life, String land, double dis, int wunl) {
		for (Planet temp = head; temp != null; temp = temp.link) {
			if (temp.num == x) { 
				temp.num=nm; temp.name=n; temp.atmosphere=at; temp.gravity=grav;
				temp.lifeforms=life; temp.landcomp=land; temp.distance=dis; temp.wunlock=wunl;
			}
		}
	}

	//adds a node of Planet at the end, with info as parameters
	public void addatEnd(int nm, String n, String at, double grav, String life, String land, double dis, int wunl) {
		boolean change = true;
		if (head == null){
			head = new Planet(nm, n, at, grav, life, land, dis, wunl, null);
		}
		else {
			for (Planet temp = head; change == true; temp = temp.link) {
				if (temp.link == null) {
					temp.link = new Planet(nm, n, at, grav, life, land, dis, wunl, null);
					change = false;
				}
			}
		}
	}

	//prints all planet name and ID only
	public void printMenu() {
		for (Planet temp = head; temp != null; temp = temp.link) {
			System.out.println(temp.num + " - " + temp.name);
		}
	}

	//prints all planets with their info
	public void printAll() {
		for (Planet temp = head; temp != null; temp = temp.link) {
			System.out.println("Name: " + temp.name + " (Planet ID: " + temp.num + ")");
			System.out.println("Gravity: " + temp.gravity + " m/s^2");
			System.out.println("Life Forms: " + temp.lifeforms);
			System.out.println("Land Composition: " + temp.landcomp);
			System.out.println("Distance from Earth: " + temp.distance + " light years");
			System.out.println("Weapon Unlocked " + temp.wunlock);	
			System.out.println();
		}
	}

	//prints planet info corresponding to the planet ID send in as parameter
	public int printNum(int x) {
		int number = 0;
		for (Planet temp = head; temp != null; temp = temp.link) {

			if (temp.num == x) {
				number = temp.wunlock;
				System.out.println("Name: " + temp.name + " (Planet ID: " + temp.num + ")");
				System.out.println("Atmosphere: " + temp.atmosphere);
				System.out.println("Gravity: " + temp.gravity + " m/s^2");
				System.out.println("Life Forms: " + temp.lifeforms);
				System.out.println("Land Composition: " + temp.landcomp);
				System.out.println("Distance from Earth: " + temp.distance + " light years");
				System.out.println("Weapon Unlocked " + temp.wunlock);
				System.out.println();
			}
		}
		return number;
	}

	//defines Planet node with 8 attributes(fields) assigned to each planet with 1 link
	class Planet {
		private int num;
		private String name;
		private String atmosphere;
		private double gravity;
		private String lifeforms;
		private String landcomp;
		private double distance;
		private int wunlock;
		private Planet link;

		//constructor initialises all fields to it's corresponding parameters
		public Planet (int nm, String n, String at, double grav, String life, String land, double dis, int wunl, Planet s) {
			num = nm;
			name = n;
			atmosphere = at;
			gravity = grav;
			lifeforms = life;
			landcomp = land;
			distance = dis;
			wunlock = wunl;
			link = s;
		}

	}
}
