import java.io.*;
import java.util.*;
import java.lang.String;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class main {
	public static void removeEnsign(Ensign x,Starship z){// removes Ensign
		Commander a3=(Commander)(z.getallpersonnel().findID(x.getmentor()));
		LtCommander a2=(LtCommander)z.getallpersonnel().findID(x.getsupervisor());
		a2.removeRpOfficer(x);
		a3.removeproteges(x);
		z.getallpersonnel().delete(x);
	}
	public static void Ltadditions(collection LcDeptheads, Personnel a1, Starship z){// Condition for A LC dept heads that must be met
		Scanner in=new Scanner(System.in);
		int temp;
		LtCommander a2;
		System.out.println("Following are ID's of the available Lt. Commanders for supervising. Enter the desiered ID number");
		LcDeptheads.printCollection();
		temp=chkID(in.nextInt(),LcDeptheads);
		System.out.println("Error here");
		a2=(LtCommander)LcDeptheads.findID(temp);
		((Lieutenant)a1).putsupervisor(temp);
		a2.putRpOfficers(a1);
		z.add(a1);
	}
	public static void removeLieutant1(Lieutenant x,Starship z){// removes Lieutant1
		LtCommander a2=(LtCommander)z.getallpersonnel().findID(x.getsupervisor());
		a2.removeRpOfficer(x);
		z.getallpersonnel().delete(x);
	}
	public static void removeCaptain(Personnel y,Starship z){// removes captain
		System.out.println("A new captain must be created to replace the previous captain. Enter the Following attributes");
		((Officer)y).putdepartment(prompt(y,z));
		System.out.println("Responsibilites of the previous Commander Automatically transfered");

	}
	public static void removeLieutant2(LtCommander x,Captain z1, int [] dept,collection LcDeptheads, Starship z){// removes Lieutant2
		Scanner in=new Scanner(System.in);
		collection temp=new collection();
		LtCommander temp1;

		int y;
		Commander a2=(Commander)z.getallpersonnel().findID(x.getsupervisor());
		System.out.println("Would you like to create a new Personnel to take over the responsibilites of Lt. Commander or shift the responisbility to one of the following Lt. Commanders");
		temp=z.getallpersonnel().option(2.5,x.getID());
		System.out.println("Enter the ID number or 1 to create a new personnel");
		while ((y=in.nextInt())!=1&&!(temp.IDchk(y))){
			System.out.println("Invalid input. Enter again(ID may be entered before or may not be six digits)");
			y=in.nextInt();
		}
		if (y==1){
			createLtCommander(z1, dept,LcDeptheads,x.getRpOfficers(),z,z1.getShID());
		}
		else{
			temp1=(LtCommander)z.getallpersonnel().findID(y);
			temp1.getRpOfficers().transfer(x.getRpOfficers());
		}
		LcDeptheads.delete(x);
		z.getallpersonnel().delete(x);
		a2.removeLCdeptheads(x);
	}
	public static void removeCommander(Commander x,Captain z1, Starship z,int []dept){// removes Commander
		Scanner in=new Scanner(System.in);
		int [] tempstore;
		collection temp=new collection();
		Commander temp1=new Commander();
		int y;
		System.out.println("Would you like to create a new Personnel to take over the responsibilites of Commander or shift the responisbility to one of the following Commanders");
		temp=z1.getcommanders().option(3,x.getID());
		System.out.println("Enter the ID number or 1 to create a new personnel");
		while ((y=in.nextInt())!=1&&!(temp.IDchk(y))){
			System.out.println("Invalid input. Enter again");
			y=in.nextInt();
		}
		deletedept(x.getdepthead(),dept);
		if (y==1){
			createCommander(z1,z,dept,x.getproteges(),x.getLCdeptheads(),z1.getShID());
		}
		else{
			temp1=(Commander)z.getallpersonnel().findID(y);
			temp1.getproteges().transfer(x.getproteges());
			temp1.getLCdeptheads().transfer(x.getLCdeptheads());
		}
		z.getallpersonnel().delete(x);
		z1.removecommanders(x);
	}
	// Conditions associated to a Lt. Commander that must be met
	public static void LtCommanderadditions(Captain z1, LtCommander a1, collection put,Starship z,int []dept, collection LcDeptheads,boolean chk){
		Scanner in=new Scanner(System.in);
		Commander a2;
		int temp=0;
		collection options=z1.getcommanders();
		if (options!=null){
			if (chk){
				temp=prompt(a1,z);
				((Officer)a1).putdepartment(temp);
			}
			System.out.println("Following are ID's of the available Commanding officers. Enter the desiered ID number");
			options.printCollection();
			temp=chkID(in.nextInt(),options);				
			((LtCommander)a1).putsupervisor(temp);
			a2=(Commander)options.findID(temp);
			System.out.println("Enter 1 to make the Lieutenant head of the department or any other num(int) to decline");
			if (in.nextInt()==1||put!=null){
				if (put!=null){System.out.println("The Lieutenant Commander must be a dept. head to relieve the previous Lt.Commander");}
				System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
				temp=in.nextInt();

				while (temp<1||temp>5||deptchk(temp,dept)){
					System.out.print("Invalid input or A head for the seleted dept. may already exist. Enter again");temp=in.nextInt();
				}
				((LtCommander)a1).puthead(temp); 
				insertdept(temp,dept);
				LcDeptheads.add(a1);
			}
			else{((LtCommander)a1).puthead(0);}

			z.getallpersonnel().add(a1);
		}
		else{System.out.println("You must create a commander to act as the supervisor before creating a Lieutenant Commander ");}
	}
	// creates Commander
	public static void createLtCommander( Captain z1, int [] dept, collection put,collection LcDeptheads, Starship z,int file ){		
		LtCommander a1=new LtCommander();
		LtCommanderadditions(z1,a1,put,z,dept,LcDeptheads,true);
	}
	// Conditions associated with a Ensign that must be met
	public static void Ensignadditions(collection LcDeptheads,Personnel a1, Starship z,Captain z1){
		Scanner in=new Scanner(System.in);
		collection options=z1.getcommanders();
		int temp,temp2;
		LtCommander a2;
		Commander a3;
		System.out.println("Following are ID's of the available Lt. Commanders for supervising. Enter the desiered ID number");
		LcDeptheads.printCollection();
		temp=chkID(in.nextInt(),LcDeptheads);
		a2=(LtCommander)LcDeptheads.findID(temp);
		System.out.println("Following are ID's of the available Commanding officers. Enter the desiered ID number");
		options.printCollection();
		temp2=chkID(in.nextInt(),options);
		a3=(Commander)options.findID(temp2);
		((Officer)a1).putpips(1);
		((Ensign)a1).putsupervisor(temp);
		((Ensign)a1).putmentor(temp2);
		a3.putprotege(a1);
		a2.putRpOfficers(a1);
		z.add(a1);
	}
	// removes Ensign
	public static void removeEnsign(Starship z,Ensign y){
		Commander tempc;
		LtCommander templ;
		tempc=((Commander)z.getallpersonnel().findID(((Ensign) y).getmentor()));
		templ=((LtCommander)z.getallpersonnel().findID(((Ensign) y).getsupervisor()));
		tempc.removeproteges(y);
		z.getallpersonnel().delete(y);
	}
	public static void removeExecutive(Starship z,Executive y){// removes Executive
		z.removeExo();
		z.getallpersonnel().delete(y);
	}

	// Contains the conditions associated to Commander that must be  met 
	public static void Commanderadditions(Commander a1,int[]dept){
		Scanner in=new Scanner(System.in);
		int temp;
		System.out.println("Enter 1 to make the commander dept head or any other num to decline");
		temp=in.nextInt();
		if (temp!=1){((Commander)a1).putdepthead(0);}
		else if (temp==1&& ((Officer)a1).getdepartment()!=2){
			System.out.println("The Department must be set to Medical in order to make the commander dept. head.\nEnter 1 to change department or any other num to indicate cancel dept. head request");
			if (in.nextInt()==1){
				((Officer)a1).putdepartment(2);
				System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
				temp=in.nextInt();
				while (temp<1||temp>5||deptchk(temp,dept)){
					System.out.print("Invalid input or A head for the seleted dept. may already exist. Enter again");temp=in.nextInt();
				}
				((Commander)a1).putdepthead(temp);
				insertdept(temp,dept);
			}
			else {((Commander)a1).putdepthead(0);}
		}
		else{System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
		temp=in.nextInt();
		while (temp<1||temp>5||deptchk(temp,dept)){
			System.out.print("Invalid input or A head for the seleted dept. may already exist. Enter again");temp=in.nextInt();
		}
		(a1).putdepthead(temp);
		insertdept(temp,dept);}
	}

	// Create a commander 
	public static void createCommander(Captain z1, Starship z,int [] dept,collection p, collection Ldepth, int file){		
		int temp;
		Scanner in=new Scanner(System.in);

		Commander a1=new Commander();
		if (Math.ceil(z.getmaxcapacity(1)/500.0)<=z1.getcommanders().findlist(3)){
			System.out.println("In Relation to maximum ship capacity you can only have "+Math.ceil(z.getmaxcapacity(1)/500.0)+" commanders");
		}
		else {
			temp=prompt(a1,z);
			((Officer)a1).putdepartment(temp);
			Commanderadditions(a1,dept);
			if (p!=null)
				a1.putproteges(p);
			if (Ldepth!=null)
				a1.putLcdeptheads2(Ldepth);
			z.add(a1);z1.putcommanders(a1);}

	}
	// copies the fields from one personnel to another
	public static void copyall(Personnel x, Personnel y){
		y.putfname(x.getfname());
		y.putlname(x.getlname());
		y.putage(x.getage());
		y.putSID(x.getSID());
		y.putheight(x.getheight());
		y.putyrservice(x.getyrservice());
		y.putID(x.getID());
		if (y instanceof Officer){
			((Officer)y).putShID(((Officer)x).getShID());
			((Officer)y).putdepartment(((Officer)x).getdepartment());}

	}

	// Contains conditions that must be met when promoting or deleting a Personnel of a given type
	public static void conditions(Personnel temp4,Starship z, Captain z1,int[]dept,collection LcDeptheads){
		if (temp4 instanceof Captain){removeCaptain(((Captain)temp4),z);}
		else if (temp4 instanceof Commander){removeCommander(((Commander)temp4),z1,z,dept);}
		else if(temp4 instanceof LtCommander){removeLieutant2(((LtCommander)temp4),z1,dept,LcDeptheads,z);}
		else if (temp4 instanceof Ensign){removeEnsign(z,((Ensign)temp4));}
		else if (temp4 instanceof Executive){removeExecutive(z,((Executive)temp4));}
		else {removeLieutant1(((Lieutenant)temp4),z);};
	}


	// Method checks if the ID exists
	public static int chkID(int y,collection x){
		Scanner in=new Scanner(System.in);
		while (!(x.IDchk(y))){
			System.out.println("Invalid input. Enter again(ID does not match the any given ID's)");
			y=in.nextInt();
		}	
		return y;
	}

	//  Checks to see if the ID enter is valid 
	public static int IDmatchchk(int y,collection x){
		Scanner in=new Scanner(System.in);
		while (y/100000<1||y/100000>=10||(x.IDchk(y))){
			System.out.println("Invalid input. Enter again(ID may be entered before or may not be six digits)");
			y=in.nextInt();
		}	
		return y;
	}

	// Deletes a department for the list department in the main program
	public static void deletedept(int x, int[]y){
		for (int i=0;i<y.length;i++){if (y[i]==x){y[i]=0; }}
	}
	// Inserts a department in the dept list
	public static void insertdept(int x, int[] y){
		int count=0;
		for (int i=0;i<y.length&&count==0;i++){
			if (y[i]==0){y[i]=x; count++;}
		}
	}
	public static boolean deptchk(int x, int[]y){// chks to see if the given department has already been claimed
		boolean z=false;
		for (int i=0;i<y.length;i++){
			if (y[i]==x){z=true;}
		}
		return z;
	}
	public static void printSID(int x){// Prints the Species ID in string depending on integer sent
		switch (x){
		case (1):{System.out.println("Species: Human");}break;
		case (2):{System.out.println("Species: Andorian");}break;
		case (3):{System.out.println("Species: Bajoran");}break;
		case (4):{System.out.println("Species: Banzite");}break;
		case (5):{System.out.println("Species: Betazoid");}break;
		case (6):{System.out.println("Species: Bolain");}break;
		case (7):{System.out.println("Species: Saurian");}break;
		case (8):{System.out.println("Species: Trill");}break;
		case (9):{System.out.println("Species: Vulcan");}break;
		}
	}
	public static int SIDchk(){// Ensures that the user enter a valid ID
		Scanner in=new Scanner(System.in);
		int x;
		do {
			System.out.println("Following are the Species ID available\nHuman- ID:01\t\tAndorian- ID: 02\t\tBajoran- ID: 03\nBanzite- ID: 04\t\tBetazoid- ID: 05\t\tBolain- ID: 06\nSaurian- ID: 07\t\tTrill- ID: 08\t\t\tVulcan- ID:09\n Enter Species ID of Choice");
			x=in.nextInt();
		}while (x<1||x>9);
		return x;	
	}
	public static int prompt(Personnel x, Starship y){// Does the general prompting (for  Personnel fields)
		Scanner in=new Scanner(System.in);
		int z=0;
		int i=5;
		if (x instanceof Commander){i=2;}
		if (x instanceof Executive){i=1;}
		if (x instanceof Executive){i=1;}
		System.out.print("Enter first name: ");x.putfname(in.next());
		System.out.print("Enter last name: ");x.putlname(in.next());
		System.out.print("Enter age(years): ");x.putage(in.nextDouble());
		x.putSID(SIDchk());
		System.out.print("Enter Height (feet): ");x.putheight(in.nextDouble());
		System.out.print("Enter years in service: ");x.putyrservice(in.nextDouble());
		System.out.print("Enter ID(6 digits- integer value)" );
		x.putID(IDmatchchk(in.nextInt(),y.getallpersonnel()));
		if (x instanceof Officer){
			System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
			z=in.nextInt();
			while (z<1||z>i){
				System.out.print("Invalid input. Enter again (Captain, Commander and Executive officer can only belong to certain Deparments");z=in.nextInt();
			}}
		return z;
	}
	public static void continuation(double x, Starship z){// Gives the user option to select from different ship
		Scanner in=new Scanner(System.in);
		int temp;
		z.printlist(x);
		System.out.println("Enter choice"); 
		temp=chkID(in.nextInt(),z.getallpersonnel());
		z.getallpersonnel().findID(temp).print();
	}
	public static String[] addpassword(String x, String[] y){// adds a password and can be used for other general strings as well (automatically increases String size) 
		boolean added=true;
		for (int i=0;added;i++){
			if (y[i]==null){y[i]=x;added=false;}
			if(i+1==y.length){
				String [] temp=new String[y.length+10];
				for (int z=0;z<y.length;z++){
					temp[z]=y[z];
				}
				y=temp;
			}
		}
		return y;
	}
	public static void addintarray(int[] list,int x){// Adds a element to array and automatically increases its size 
		boolean added=true;
		for (int i=0;added;i++){
			if (i+1==list.length){
				int [] shiplist1=new int[list.length+10];
				for (int z=0;z<list.length;z++){
					shiplist1[z]=list[z];
				}
				list=shiplist1;
			}
			if (list[i]==0){list[i]=x;added=false;}
		}
	}
	public static boolean chkweapon(int x,int[]y){ // Checks to see if any Weapon ID matches the sent parameter
		boolean send=false;
		for (int i=0;i<y.length;i++){
			if (y[i]==x){
				send=true;
			}
		}
		return send;
	}
	public static int transfergeneral(String y, Personnel x,int chk){// Transer general Personnel class feilds on one personnel to another
		int chk2=0;
		x.putfname(y.substring (chk+1,chk2=y.indexOf("|",chk+1)));
		x.putlname(y.substring (chk2+1,chk=y.indexOf("|",chk2+1)));
		x.putage(Double.parseDouble(y.substring (chk+1,chk2=y.indexOf("|",chk+1))));
		x.putSID(Integer.parseInt(y.substring (chk2+1,chk=y.indexOf("|",chk2+1))));
		x.putheight(Double.parseDouble(y.substring (chk+1,chk2=y.indexOf("|",chk+1))));
		x.putyrservice(Double.parseDouble(y.substring (chk2+1,chk=y.indexOf("|",chk2+1))));
		System.out.println(Integer.parseInt(y.substring (chk+1,chk2=y.indexOf("|",chk+1))));
		x.putID(Integer.parseInt(y.substring (chk+1,chk2=y.indexOf("|",chk+1))));	
		return chk2;
	}
	public static void readfilepassnfleet(String []passwords, Starfleet v, collectionfleet allfleet){// reads password and fleet from a file
		try	{
			String read;
			String y;
			int chk=0;
			int chk2=0;
			int count=0;
			while (count!=2){
				if (count==0){read="J:\\passwords.txt";}
				else{read="J:\\allfleet.txt";}
				FileReader fr=new FileReader(read);
				BufferedReader br = new BufferedReader(fr);
				while ((y = br.readLine()) != null) {
					v=new Starfleet();
					if (count==0){passwords=addpassword(y,passwords);}
					else {
						chk=0;chk2=0;	
						v.putFltID(Integer.parseInt(y.substring (chk,chk2=y.indexOf("|",chk+1))));
						while(!(read=(y.substring(chk2+1,chk=y.indexOf("|",chk2+1)))).equals("\\\\")){
							v.putShip(Integer.parseInt(read));chk2=chk;
						}
						allfleet.addAtFront(v);
					}}
				br.close();
				count++;
				if (count!=0){
					v=new Starfleet();

				}}}
		catch (IOException e){}
		catch (NumberFormatException e){}
	}
	// Reads all the attributes from a file 
	public static Captain readship(Starship z,Captain z1 ,collection LCdeptheads,int[] dept,int[] weaponlock,String file){
		try
		{   int count=0;
		int chk=0;
		int chk2=0;
		double chk3;
		String read = null;
		int deptcount=0;
		String x;		
		Personnel p;
		FileReader fr=new FileReader("J:\\"+file+".txt");
		BufferedReader br = new BufferedReader(fr);
		while ((x = br.readLine()) != null) {			
			chk=0;chk2=0;//reads data into x and then compares x  to null
			if (count==0){
				z.putregistrynum(x.substring(chk,chk2=x.indexOf("|",chk)));
				z.putFleetID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
				z.putPtropedoes(Double.parseDouble(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
				z.putAshilds(Double.parseDouble(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
				z.putFshields(Double.parseDouble(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
				z.putclass(Integer.parseInt(x.substring(chk2+1)));
			}
			else if(x.equals("\\\\")){
				x=br.readLine();
				count=0;
				while (!(read=x.substring(chk,chk2=x.indexOf("|",chk+1))).equals("\\\\")){
					addintarray(weaponlock,Integer.parseInt(read));
					chk=chk2+1;
				}
			}
			else{
				read=x.substring(chk,chk2=x.indexOf("|",chk));
				chk3=Double.parseDouble(read);
				if (chk3==0){
					p=new Personnel();
					transfergeneral(x,p,chk2);
					z.add(p);
				}
				else if(chk3==1.0){
					p=new Ensign();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
					((Ensign)p).putmentor(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Ensign)p).putsupervisor(Integer.parseInt(x.substring(chk2+1)));
					z.add(p);
				}
				else if(chk3==1.5){
					p=new LtJuniorgrade();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
					((Lieutenant)p).putsupervisor(Integer.parseInt(x.substring(chk+1)));
					z.getallpersonnel().addAtFront(p);
				}
				else if(chk3==2.0){
					p=new LtFullgrade();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
					((Lieutenant)p).putsupervisor(Integer.parseInt(x.substring(chk+1)));
					z.getallpersonnel().addAtFront(p);
				}
				else if(chk3==2.5){
					p=new LtCommander();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
					((Lieutenant)p).putsupervisor(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((LtCommander)p).puthead(Integer.parseInt(x.substring(chk2+1)));
					if (((LtCommander)p).gethead()!=0){
						LCdeptheads.add(p);
						dept[deptcount]=((LtCommander)p).gethead();
						deptcount++;
					}
					z.getallpersonnel().addAtFront(p);
				}
				else if(chk3==3.0){
					p=new Commander();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					System.out.println(p.getID());
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
					((Commander) p).putdepthead(Integer.parseInt(x.substring(chk+1))); 
					((Officer)p).putShID(Integer.parseInt(file));
					z.getallpersonnel().addAtFront(p);
				}
				else if(chk3==3.1){
					p=new Executive();
					((Officer)p).putpips(3);
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putShID(Integer.parseInt(file));
					z.getallpersonnel().addAtFront(p);
					z.putExc(p);
				}
				else if(chk3==4.0){
					p=new Captain();
					((Officer)p).putpips(Double.parseDouble(read));
					chk=transfergeneral(x,p,chk2);
					((Officer)p).putdepartment(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
					((Officer)p).putShID(Integer.parseInt(x.substring(chk2+1)));
					z1=(Captain)p;
				}
			}
			count++;
		}
		br.close();

		}
		catch (IOException e){}
		catch (NumberFormatException e){}
		return z1;
	}
	//  Checks if the passwords match
	public static boolean passwordchk(String x, String[] y){
		boolean send=true;
		for (int i=0;i<y.length&&y[i]!=null;i++){
			if (y[i].equals(x)){send=false;}
		}
		return send;
	}
	public static boolean datechk(String y){// Chks if date is valid
		boolean send=false;
		if (y.length()!=6){System.out.println("Invalid date");send=true;}
		else{
			int z=Integer.parseInt(y.substring(0,2));
			if (z<1||z>31){System.out.println("Invalid date");send=true;}
			z=Integer.parseInt(y.substring(2,4));
			if (z<1||z>12){System.out.println("Invalid month");send=true;}
			z=Integer.parseInt(y.substring(4,6));
			if (z<0){System.out.println("Invalid year");}}
		return send;
	}
	public static void main(String[] args)throws IOException {
		Scanner in=new Scanner(System.in);
		Personnel a1;
		String file="";
		Starfleet v=new Starfleet();// makes a variabale v that refers to an object of type Starfleet
		Starship z=new Starship();//makes a variabale z that refers to an object of type Star ship
		Captain z1=new Captain();// makes a variabale z1 that refers to an object of type Starfleet
		String [] passwords=new String[100];// makes an array that will store all passwords
		int [] weaponlock=new int[10];// Array will store that weapons that unlocked
		collectionfleet allfleet=new collectionfleet();
		collection Admirals=new collection();
		Admirals.readAdmirals();
		readfilepassnfleet(passwords,v,allfleet);		
		collection LcDeptheads=new collection();
		List1 myweapon = new List1();
		List2 myplanet = new List2();
		int [] dept=new int[5];
		int x;
		int helper=0;
		String temp1;
		int temp=0;
		String tempstore;
		do{
			if (temp!=0){System.out.println("Invalid Input. Enter again");}
			else{
				System.out.println("\t\t\t\t\t    Star Trek Online\n\t\t\t\t\tExplore, Discover &Engage\n1: Create New Ship\n2: Login");
			}
			temp=in.nextInt();  
		}while (temp<1||temp>2);
		if (temp==1){
			System.out.println("Following are the Available Fleets.");
			allfleet.printlist();
			System.out.print("\nEnter the Starfleet to connect to:  ");
			helper=in.nextInt();
			while (!(allfleet.chkfleet(helper))){
				System.out.println("Invalid input. Enter again"); 
				helper=in.nextInt();
			} 
			z.putFleetID(helper);  
			do{
				System.out.println("Choose a prefix for ship registry num.\nEnter <1> for NCC and <2> for NX");
				temp=in.nextInt();
			}while(temp<1||temp>2);
			if (temp==1){tempstore="NCC";}
			else{tempstore="NX";}
			temp=0;
			do {
				if (temp!=0){System.out.println("Invalid Input. Enter Again.");}	  
				System.out.print("Enter ID:");
				x=in.nextInt();
				file=x+"";
				temp++;
			}while(allfleet.shipchk(x));
			allfleet.getStarfleet(helper).putShip(x);
			tempstore=tempstore+x;
			System.out.println("Enter 1 to Add a sufix or any other number(int) to skip");
			if (in.nextInt()==1){
				do {
					System.out.println("Add sufix staring with A");
					temp1=in.next();
				}while (temp1.charAt(0)!='A');
				tempstore=tempstore+temp1;
			}
			z.putregistrynum(tempstore);
			do{
				System.out.println("Enter ships class\n1: Sovereigh\t2: Galaxy Class\t3: Ambassador class\t4: Excelsior class\t5: Voyager Class\t6: Defiant class\nIndicate choice number(1-6)");
				x=in.nextInt();
			}while(x<1||x>6);
			z.putSclass(x);
			System.out.println("Enter number of Photon tropedoes)");
			z.putPtropedoes(in.nextDouble());
			System.out.println("Enter Forward sheilds percentage");
			z.putFshields(in.nextDouble());
			System.out.println("Enter After Sheilds percenteage");
			z.putAshilds(in.nextDouble());
			//break;
			System.out.println("Welcome to Ship: "+z.getregistrynum());
			System.out.println("You UserID for login is the same as your ship ID: "+file);
			x=0;
			do{
				if (x!=0){ System.out.println("Invalid Input");}	  
				System.out.println("Enter password: ");
				if (x==0)
					in.nextLine();
				temp1=in.nextLine();
				System.out.println("Confirm password: ");
				tempstore=in.nextLine();
				x++;
			}while(!(temp1.equals(tempstore)));
			passwords=addpassword((file+"|"+temp1),passwords);
			System.out.println("You are the Captain of the Ship. Enter the Following attributes");
			z1.putdepartment(prompt(z1,z));
			((Admiral)Admirals.findFltID((helper))).putcaptains(z1.getID());
			FileWriter fw = new FileWriter("J:\\"+file+"newsarchive.txt");

		}
		else{//Line
			x=0;
			do{
				if (x!=0){System.out.println("Invalid input. Enter again");}
				System.out.println("Enter UserID: ");
				if (x==0){in.nextLine();}
				file=in.nextLine();
				temp1=file;
				System.out.println("Enter password");
				temp1=temp1+"|"+in.nextLine();
				x++;
			}while(passwordchk(temp1,passwords));
			z1=readship(z,z1,LcDeptheads,dept,weaponlock,file);
		}
		z1.putShID(Integer.parseInt(file));
		System.out.println(weaponlock[0]);
		z.add(z1);
		z.putcapt(z1);
		z.getallpersonnel().connections(z1);
		if (!(allfleet.chkfleet(Integer.parseInt(file)))){
			x=0;
			myweapon.read();
			myplanet.read();
			while (x!=7){
				System.out.println("1: Organize Ship\t2: Ships logs\t3: Weapons\t4: Explore\t5: News Archive\t6: Forum\t7: Exit\nEnter choice num");
				x=in.nextInt();
				switch (x){
				case(1):{
					do{
						System.out.println("1: Create Personnel\t2: Edit Info\t3: Delte Personnel\t4: Promote\t5: Find/Search\t6: View Ship Info\t7: Return\nEnter Choice");
						x=in.nextInt();
						switch(x){

						case (1):{
							do{
								System.out.println("1: Executive Officer\t2: Commander\t3 :Liutenant Commander\t4: Lieutant Full Grade\t5: Lieutant Jr.Grade\t6: Ensign\t7: Personnel\t8: Back\nEnter Choice");
								x=in.nextInt();
								switch(x){
								case(1):{
									a1=new Executive();
									temp=prompt(a1,z);
									((Officer)a1).putdepartment(temp);
									((Officer)a1).putpips(3);
									((Officer)a1).putShID (Integer.parseInt(file));
									z.add(a1);		
									z.putExc(a1);
								}break;
								case (2):{
									Commander ignore=new Commander();
									createCommander(z1,z,dept,null,null,Integer.parseInt(file));

								}break;
								case (3):{
									createLtCommander(z1, dept,null,LcDeptheads,z,Integer.parseInt(file));
								}break;
								case (4):{
									if(z.findlist(2.5)!=0){	
										a1=new LtFullgrade();
										temp=prompt(a1,z);
										((Officer)a1).putdepartment(temp);
										((Officer)a1).putShID (Integer.parseInt(file));
										Ltadditions(LcDeptheads,a1,z);
									}
									else {System.out.println("You must create a Lieutenant commander to act as the supervisor before creating a Lieutenant Full grade");}
								}break;
								case (5):{
									if(z.findlist(2.5)!=0){
										a1=new LtJuniorgrade();
										temp=prompt(a1,z);
										((Officer)a1).putdepartment(temp);
										((Officer)a1).putShID (Integer.parseInt(file));
										Ltadditions(LcDeptheads,a1,z);
									}
									else {System.out.println("You must create a Lieutenant commander to act as the supervisor before creating a Lieutenant Junior grade");}		
								}break;
								case (6):{
									if(z.findlist(2.5)!=0){
										a1=new Ensign();
										temp=prompt(a1,z);
										((Officer)a1).putdepartment(temp);
										((Officer)a1).putShID (Integer.parseInt(file));
										Ensignadditions(LcDeptheads,a1,z,z1);

									}
									else {System.out.println("You must create a Lieutenant commander to act as the supervisor before creating a Ensign");}		
								} break;
								case (7):{
									a1=new Personnel();
									temp=prompt(a1,z);
									z.add(a1);
								}break;
								default:{
									while (x>8||x<1){
										System.out.println("Invalid input. Enter again");
										x=in.nextInt();
									}
								}
								}
							}while(x!=8);
						}
						break;
						case(2):{
							do{
								System.out.println("Enter the ID number of the Personnel to edit. If not sure use the Find/Search option to find ID. Enter -1 to retunr");
								temp=in.nextInt();
								while (temp!=-1&&!(z.getallpersonnel().IDchk(temp))){
									System.out.println("Inavlid input enter again");
									temp=in.nextInt();
								}
								if (temp!=-1){
									a1=z.getallpersonnel().findID(temp);
									while (x!=9){
										System.out.println("1: Edit name \n2: Edit age\n3: Edit Height\n4: Edit yrservice\n5: Edit ID\n6: Edit Department\n7: Edit mentor\n8: Edit supervisor\n Enter choice number or 9 to Exit");
										switch (x=in.nextInt()){
										case(1):{
											System.out.println("Previous name: "+a1.getfname()+" "+a1.getlname());
											System.out.print("Enter new lastname: ");a1.putlname(in.next());
											System.out.println("Enter new first name: ");a1.putfname(in.next());
										}break;
										case(2):{
											System.out.println("Previous age: "+a1.getage());
											System.out.print("Enter new age ");a1.putage(in.nextDouble());
										}break;
										case (3):{
											System.out.println("Previously set Height(feet): "+a1.getheight());
											System.out.print("Enter new height (in feet)");a1.putheight(in.nextDouble());
										}break;
										case (4):{
											System.out.println("Current years in service: "+a1.getyrservice());
											System.out.print("Change years in service to: ");a1.putyrservice(in.nextDouble());
										}break;
										case (5):{
											System.out.println("Current ID: "+a1.getID());
											a1.putID(0);
											System.out.print("Enter new ID");
											a1.putID(IDmatchchk(in.nextInt(),z.getallpersonnel()));

										}break;
										case (6):{
											if (a1 instanceof Captain){System.out.println("Capatain can only be from Command Department");}
											if (a1 instanceof Officer){
												if (a1 instanceof Commander ){
													temp=1;
													if( ((Commander)a1).getdepthead()!=0){
														System.out.println("Cannot change department from Medical.\nEnter 1 to remove for department head and change department or Enter any other number to return");		
														if ((temp=in.nextInt())==1){
															deletedept(((Commander)a1).getdepthead(),dept);
														}}
													if (temp==1)
														System.out.println("Previous department: "+Officer.getdepartment(((Officer)a1).getdepartment()));
													System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
													temp=in.nextInt();
													while (temp<1||temp>2){
														System.out.print("Invalid input. Commander can only be from Command (1) or Medical(2) Department. Enter Department num");temp=in.nextInt();
													}
													((Officer)a1).putdepartment(temp);
													((Commander)a1).putdepthead(0);
												}			
												else {
													System.out.println("Previous department: "+Officer.getdepartment(((Officer)a1).getdepartment()));
													System.out.print("Enter department: for Command: 1, Medical: 2, Sciences: 3, Security: 4, Enginnering 5");
													temp=in.nextInt();
													while (temp<1||temp>5){
														System.out.print("Invalid input. Enter Department num");temp=in.nextInt();
													}
													((Officer)a1).putdepartment(temp);
												}
											}
											else {System.out.println("The personnel is not of type Officer");}
										}break;
										case (7):{
											if (a1 instanceof Ensign){
												Commander a2;
												System.out.println("ID of the older Mentor (Commander): "+((Ensign)a1).getmentor()+"\nEnter the ID of the new Mentor. Following are the available Commander ID's");
												z1.getcommanders().printCollection();
												temp=chkID(in.nextInt(),z1.getcommanders());
												a2=(Commander)z.getallpersonnel().findID(temp);
												((Ensign)a1).putmentor(temp);
												a2.putprotege(a1);
											}
											else {System.out.println("The Personnel is not an Ensign. Only Ensigns have a Mentor");}
										}break;
										case (8):{
											LtCommander a2=new LtCommander();
											Commander a3=new Commander(); 
											if (a1 instanceof Ensign||a1 instanceof Lieutenant){
												System.out.print("Previous Supervisor ID");
												if (a1 instanceof Ensign){
													System.out.println(((Ensign)a1).getsupervisor());
													System.out.println("Following are ID's of the available Lt. Commanders for supervising.");
													LcDeptheads.printCollection(); System.out.println("Enter the desiered ID number");
													a2=(LtCommander)LcDeptheads.findID(chkID(in.nextInt(),LcDeptheads));
													((Ensign)a1).putsupervisor(a2.getID());
													a2.putRpOfficers(a1);
												}
												else if(a1 instanceof LtFullgrade||a1 instanceof LtJuniorgrade){
													System.out.println(((Lieutenant)a1).getsupervisor());
													System.out.println("Following are ID's of the available Lt. Commanders for supervising.");
													LcDeptheads.printCollection();System.out.println("Enter the desiered ID number");
													a2=(LtCommander)LcDeptheads.findID(chkID(in.nextInt(),LcDeptheads));
													((Lieutenant)a1).putsupervisor(a2.getID());
													a2.putRpOfficers(a1);
												}
												else{//remove
													System.out.println(((Lieutenant)a1).getsupervisor());
													System.out.println("Following are ID's of the available Commanding officers. Enter the desiered ID number");
													z1.getcommanders().printCollection();
													a3=(Commander)z1.getcommanders().findID(chkID(in.nextInt(),z1.getcommanders()));
													if (((LtCommander)a1).gethead()!=0){
														a3.putLCdeptheads(a1);
													}
													((LtCommander)a1).putsupervisor(a3.getID());
												}
											}
											else {System.out.println("The selected Personnel does not have any supervisors");}
										}break;
										default:{
											while (x<1||x>9){
												System.out.println("Invalid input");
											}
										}

										}

									}


								}}while (temp!=-1);

						}
						break;
						case (3):{
							do {
								System.out.println("Enter the ID number of the Personal to be deleted or -1 to exit");
								temp=in.nextInt();
								while (temp!=-1&&!(z.getallpersonnel().IDchk(temp))){
									System.out.println("Inavlid input enter again or enter -1 to exit");
									temp=in.nextInt();
								}
								if (temp!=-1){
									Personnel y=z.getallpersonnel().findID(temp);
									if (y instanceof Officer){
										if (y instanceof Captain){
											System.out.println("Captain Cannot be deleted");
										}
										else if (y instanceof Commander){
											removeCommander((Commander)y,z1,z,dept);
										}
										else if(y instanceof LtCommander){
											removeLieutant2((LtCommander)y,z1,dept,LcDeptheads,z);
										}
										else if(y instanceof Ensign){
											removeEnsign(z,((Ensign)y));

										}
										else if(y instanceof Executive){
											removeExecutive(z,((Executive)y));
										}
										else {
											removeLieutant1((Lieutenant)y,z);
										}
									}
									else{
										z.getallpersonnel().delete(y);
									}
								}
							}while(temp!=-1);
						}

						break;
						case (4):{
							double newpip;
							do{	System.out.println("Enter the ID number of the personal to be promoted or -1 to exit");
							temp=in.nextInt();
							if (temp!=-1){
								temp=chkID(temp,z.getallpersonnel());
								Personnel temp4=z.getallpersonnel().findID(temp);
								if (temp4 instanceof Officer){
									System.out.print("Previous pips:"+ ((Officer)temp4).getpips()+"\nEnter new number of pips: ");
									newpip=in.nextDouble();
									if (((Officer)temp4).getpips()!=newpip||((Officer)temp4).getpips()==3){
										if (newpip==4){
											System.out.println("In order to promote to Captain must delete previous Captain. Enter 1 to continue or any other number(int) to return");
											if (in.nextInt()==1){
												if (((Officer)temp4).getdepartment()==1){
													copyall(temp4,z1);
													conditions(temp4,z,z1,dept,LcDeptheads);	
													System.out.println("Promotion complete");
												}
												else {System.out.println("Promotion not possible as the Personnel does not belong to department Command");}
											}
										}
										else if (newpip==3){
											do{
												System.out.println("Would you like to create a commander (Enter 1) or Executive Officer (Enter 2");
												temp=in.nextInt();
											}while(temp<1||temp>2);
											if (temp==2){
												if (z.getExo()!=null){
													System.out.println("Only one Executive Officer per ship.\nEnter 1 to continue and delete the previous Executive officer or any other num (int) to continue");
													if (in.nextInt()==1){
														if (((Officer)temp4).getdepartment()==1){
															copyall(temp4,z.getExo()); 	
															conditions(temp4,z,z1,dept,LcDeptheads);
															System.out.println("Promotion complete");
															z.getallpersonnel().delete(temp4);
														}
														else {System.out.println("Promotion not possible as the Personnel does not belong to command department");}

													}
												}
												else{
													if (((Officer)temp4).getdepartment()==1){
														Personnel tempex=new Executive();
														copyall(temp4,tempex);
														conditions(temp4,z,z1,dept,LcDeptheads);
														((Officer)tempex).putdepartment(1);
														z.getallpersonnel().add(tempex);
														z.putExc(tempex);
														System.out.println("Promotion complete");
													}
													else {
														System.out.println("Promotion not possible as the Personnel does not belong to Command department");
													}
												}
											}
											else {
												if (Math.ceil(z.getmaxcapacity(1)/500.0)<=z1.getcommanders().findlist(3)){
													System.out.println("Error.In Relation to maximum ship capacity you can only have "+Math.ceil(z.getmaxcapacity(1)/500.0)+" commanders");
												}
												else{
													if (((Officer)temp4).getdepartment()<=2&&((Officer)temp4).getdepartment()>=1){
														Commander tempcm=new Commander();
														copyall(temp4,tempcm);
														conditions(temp4,z,z1,dept,LcDeptheads);
														Commanderadditions(tempcm,dept);
														z.add(tempcm);
														z1.putcommanders(tempcm);
														System.out.println("Promotion complete");
													}
													else {System.out.println("Promotion not possible as only Personnel's belonging to Command or Medical class can be promoted to Commander");}
												}

											}
										}
										else if (newpip==2.5){
											LtCommander templc=new LtCommander();
											copyall(temp4,templc);
											conditions(temp4,z,z1,dept,LcDeptheads);
											LtCommanderadditions(z1,templc,null,z,dept,LcDeptheads,false);	
											System.out.println("Promotion complete");
										}
										else if (newpip==2||newpip==1.5){
											Personnel templc;
											if (newpip==2){templc=new LtFullgrade();}
											else {templc=new LtJuniorgrade();}
											copyall(temp4,templc);
											conditions(temp4,z,z1,dept,LcDeptheads);
											Ltadditions(LcDeptheads,templc,z);
											System.out.println("Promotion complete");
										}

										else if (newpip==1){
											Ensign tempe= new Ensign();
											copyall(temp4,tempe);
											conditions(temp4,z,z1,dept,LcDeptheads);
											Ensignadditions(LcDeptheads,tempe,z,z1);
											System.out.println("Promotion complete");
										}
										else{
											Personnel tempp=new Personnel();
											copyall(temp4,tempp);
											conditions(temp4,z,z1,dept,LcDeptheads);
											System.out.println("Promotion complete");
										}}}}
							}while (temp!=-1);
						}
						break;
						case (5):{
							do{
								System.out.println("1: Display Personnel by entering ID\t2: Search Personnel\t3: Return");
								temp=in.nextInt();
								switch(temp){
								case (1):{// add do while 
									System.out.println("Enter the ID Number of the personnel to be displayed");
									a1=z.getallpersonnel().findID(chkID(in.nextInt(),z.getallpersonnel()));
									a1.print();
								}
								break;
								case(2):{
									do{
										System.out.println("1: Captain\t2: Executive\t3: Commander\t4: Lt. Commander\t5: Lt.Fullgrade\t6: Lt.Juniorgrade\t7: Ensign\t8: Personnel");
										temp=in.nextInt();
									}while (temp<1||temp>8);
									switch(temp){
									case (1):{
										z1.print();
									}
									break;
									case (2):{
										if (z.getExo()==null){System.out.println("No executive officer has been yet created");}
										else {z.getExo().print();}
									}
									break;
									case (3):{
										if (z1.iscommandersEmpty()){
											System.out.println("Following are the IDs availible");
											z1.getcommanders().printCollection();
											System.out.println("Enter choice"); 
											temp=chkID(in.nextInt(),z1.getcommanders());
											z1.getcommanders().findID(temp).print();
										}
										else{
											System.out.println("No Commanders have been created");
										}
										temp=2;

									}
									break;
									case (4):{
										if (z.getallpersonnel().findlist(2.5)==0){System.out.println("No Lt. Commanders have been yet created"); }
										else{
											System.out.println("Following are the available ID's");
											continuation(2.5,z);
										}
									}
									break;
									case (5):{
										if (z.getallpersonnel().findlist(2)==0){System.out.println("No Lt. Full grades have been yet created"); }
										else{
											System.out.println("Following are the available ID's");
											continuation(2,z);
										}}
									break;
									case (6):{
										if (z.getallpersonnel().findlist(2.5)==0){System.out.println("No Lt. Junior grade have been yet created"); }
										else{
											System.out.println("Following are the available ID's");
											continuation(1.5,z);
										}
									}
									break;
									case(7):{
										if (z.getallpersonnel().findlist(2.5)==0){System.out.println("No Ensigns have been yet created"); }
										else{
											System.out.println("Following are the available ID's");
											continuation(1,z);
										}
									}
									break;
									case (8):{
										if (z.getallpersonnel().findlist(0)==0){System.out.println("No Peronnel (specifically of type Perosoneel have been yet created"); }
										else{
											System.out.println("Following are the available ID's");
											z.getallpersonnel().printlist(0);
											System.out.println("Enter choice");
											temp=chkID(in.nextInt(),z.getallpersonnel());
											z.getallpersonnel().findID(temp).print();
										}}
									defualt:{temp=2;}
									}
								}
								break;
								default:{
									if (temp!=3){
										System.out.println("Invalid input. Enter again");
									}
								}
								}
							}while(temp!=3);
						}
						break;
						case (6):{
							z.print();
							System.out.println("Enter a number (any integer) to return");
							in.nextInt();
						}
						break;
						default:{if (x!=7)System.out.println("Invalid Input. Enter Again");}
						}
					}while(x!=7);
					x=0;
				}
				break;
				case (2):{
					String enter;
					int date;
					do{
						System.out.println("Please choose one of the following options");
						System.out.println("0)\tSave something to your log\n1)\tRead from saved log\n2)\tExit");
						temp=in.nextInt();
						if (temp==0){
							try{
								FileWriter fw = new FileWriter("J:\\logs.txt", true);    
								PrintWriter pw = new PrintWriter (fw);
								do{
									do{
										System.out.println("Enter date (only in #s, ddmmyy): ");
										enter=in.next();
									}while (datechk(enter));
									date=Integer.parseInt(enter);
									System.out.println("Record log: ");
									in.nextLine();
									enter = in.nextLine();							
									pw.println(date+"|"+enter+"|");
									System.out.println("0)\tAdd more to your log\n1)\tGo to previous menu");
									temp=in.nextInt();						
								}while (temp==0);
								pw.close();
							}
							catch (IOException e){}
						}
						else if (temp==1){
							int chk=0;
							try	{
								FileReader fr=new FileReader("J:\\logs.txt");
								BufferedReader br = new BufferedReader(fr);
								while ((enter = br.readLine()) != null) {
									chk=0;
									System.out.print("Date: "+enter.substring(0,chk=(enter.indexOf("|",chk))));  
									System.out.println("-  "+enter.substring(chk+1,enter.indexOf("|",chk+1)));  
								}
								br.close();
								System.out.println("Enter any Integer number to continue to Mneu");
								in.nextInt();
							}
							catch (IOException e){}
							catch (NumberFormatException e){}
						}
					} while (temp!=2);
				}
				break;
				case (3):{
					//displays weapon info, if weapon is unlocked		
					myweapon.printMenu();
					int c;//receives integer input for selection
					do {
						System.out.println("\nEnter choice of weapon (0 to return to main menu):");
						c = in.nextInt();
						myweapon.printNum(c, weaponlock);
					}while(c != 0);
				}
				break;
				case (4):{
					myplanet.printMenu();
					int c2;//receives integer input for planet selection
					do {
						System.out.println("\nEnter choice of planet (0 to return to main menu):");
						c2 = in.nextInt();
						myweapon.unlocked(myplanet.printNum(c2));
						addintarray(weaponlock,c2);
					}while(c2 != 0);
				}
				break;
				case (5):{
					System.out.println("\t\t\t\t\t\tNews Archive");
					System.out.println("\t\t\t\t\t    Live Long and Prosper");
					String [] store=new String[10];
					boolean schk=false;
					String [] temps;
					String read=null;
					int totalnum=0;
					int chk;
					try	{		
						FileReader fr=new FileReader("J:\\"+file+"newsarchive.txt");
						BufferedReader br = new BufferedReader(fr);	
						read=br.readLine();
						if (read==null){System.out.println("No responses\n");}
						while(read!=null){
							totalnum++;
							chk=0;
							System.out.println("#"+totalnum+": "+read.substring(chk,chk=read.indexOf("|",chk+1)));
							System.out.println("Response:  "+read.substring(chk+1,read.indexOf("|",chk+1)));
							store=addpassword(read,store);
							read=br.readLine();
						}
					}catch (IOException e){}
					catch (NumberFormatException e){}
					if (store[0]!=null){
						System.out.println("1: Clear all and exit\t2: Clear\nEnter choice number or anyother integer number to return");
						x=in.nextInt();
						if (x==1){
							store=null;
						}
						else if (x==2){
							int sub=0;
							do{
								System.out.println("Enter the news number to deleted");
								x=in.nextInt();
								while (x>totalnum||x<1){
									System.out.println("Invalid. Enter again");
									x=in.nextInt();
								}
								temps=new String[store.length];
								for (int i=0;i<store.length&&store[i]!=null;i++){
									if (i+1==x){sub=1; 
									if (i==0){schk=true;}
									}
									if (schk){
										temps[i]=store[i+1];
									}
									else {
										temps[i-sub]=store[i];
									}
								}
								store=temps;
								System.out.println("Enter 1 delte more or any other Integer number to exit");
							}while (in.nextInt()==1);
						}
						try {
							FileWriter fw = new FileWriter("J:\\"+file+"newsarchive.txt");
							PrintWriter pw = new PrintWriter (fw);
							if (store==null){pw.print("");}
							else{
								for (int i=0;i<store.length&&store[i]!=null;i++){
									pw.println(store[i]);
								}
								pw.close();     		
							}}
						catch (IOException e){}
					}
					x=0;
				}
				break;
				case (6):{
					String read;
					int count=0;
					String choice="n";
					System.out.println("\t\t\t\t\t\tForums");
					System.out.println("\t\t\tYour questions will be answered as soon as possible");
					try {
						FileWriter fw = new FileWriter("J:\\forum.txt", true); 
						PrintWriter pw = new PrintWriter (fw); 
						do {
							read=file+"|";
							System.out.println("Please enter your question: ");
							if (count==0){
								in.nextLine(); count++;}

							read+=in.nextLine()+"|";
							System.out.println("Do you wish to add more questions: y for yes & n for no");
							choice=in.nextLine();
							pw.println(read);
						}while (choice.equals("y"));
						pw.close();
					}catch (IOException e){} 
				}
				break;
				case (7):{
					z.getallpersonnel().writeship(z,weaponlock,file);
					allfleet.writepassnfleet(passwords);
					Admirals.writeAdmirals();
					System.out.println("All changes saved");
				}
				}
			}

		}
		else{
			x=0;
			while (x!=7){
				System.out.println("1: Planet Management\t2: Fleet info\t3: Add another Fleet\t4: Search/Display ships\t5: Feedback/Question Reply\t6: Saved Replies\t7: Exit\nEnter choice num (1-7)");
				switch (x=in.nextInt()){
				case (1):{ //to add planets	
					int con=0;
					int in1;//receives integer input for menu selection
					String nam = null, atmos = null, life = null, land = null;//String attributes of planets
					myweapon.read();
					myplanet.read();
					int wunl = 0;			
					int idnum = 0;//receives planet ID
					double grav = 0;//receives planet gravity 
					double dist = 0;//receives distance from earth
					int pin;
					System.out.println("1:Add Planet\t2:Edit Planet\t3:View Planets\t4:Delete Planets (Press any other integer number to return to main menu)");
					pin = in.nextInt();//receives integer input for menu selection
					System.out.println(pin);
					switch(pin) {
					case (1): 		{
						temp = 0;
						do {
							if (temp > 0 && myplanet.checkID(idnum, 0)) {
								System.out.println("Planet ID already in use, try different ID");
							}
							else if (temp > 0) {
								System.out.println("Invalid ID");
							}
							System.out.println("Enter Planet ID:");
							idnum = in.nextInt(); 
							temp++;

						}while (myplanet.checkID(idnum, 0) || idnum <= 0);

						System.out.println("Enter Planet Name:");
						in.nextLine();nam = in.nextLine();

						System.out.println("Enter Atmosphere:");
						atmos = in.nextLine();

						do {
							System.out.println("Enter Gravity (m/s^2):");
							grav = in.nextDouble();
						}while (grav <= 0);

						System.out.println("Enter Life Forms:");
						in.nextLine();life = in.nextLine();

						System.out.println("Enter Land Composition:");
						land = in.nextLine();

						do {
							System.out.println("Enter Distance from Earth (light years):");
							dist = in.nextDouble();
						}while (dist <= 0);

						do {
							System.out.println("Enter Weapon ID to Unlock (0 for No Weapon):");
							wunl = in.nextInt();
						}while(!myweapon.checkID(wunl) || wunl < 0);

						do {
							System.out.println("Press 1 to confirm or Press 0 to decline:");
							con = in.nextInt();
							if (con == 1) {
								myplanet.addatEnd(idnum, nam, atmos, grav, life, land, dist, wunl);
								myplanet.write();
								myplanet = new List2();// create a new list
							}
						}while(con != 0 && con != 1);
					}
					break;

					case 2: //to edit planets
						x = 0; temp = 0; //x receives planet id, temp repeats if invalid input
						do {
							if (temp!=0) 
								System.out.println("Invalid Input Try Again");
							System.out.println("Enter Planet ID to be Edited:");
							x = in.nextInt();
							temp++;
						}while (!myplanet.checkID(x, 0) || x <= 0);					
						myplanet.printNum(x);

						int cnum = 0;
						do {
							System.out.println("1:Edit ID\t2:Edit Name\t3:Edit Atmosphere\t4:Edit Gravity:");
							System.out.println("5:Edit Life Forms\t6: Edit Land Composition");
							System.out.println("7:Edit Distance from Earth\t8:Edit Weapon to be Unlocked");
							System.out.println("0: Exit");
							System.out.println("Enter choice:");
							cnum = in.nextInt();
						}while (cnum < 0 || cnum > 8);					

						idnum = x;
						nam = myplanet.getname(idnum);
						atmos = myplanet.getatmos(idnum);
						grav = myplanet.getgrav(idnum);
						life = myplanet.getlife(idnum);
						land = myplanet.getland(idnum);
						dist = myplanet.getdist(idnum);
						wunl = myplanet.getweapon(idnum);

						switch (cnum) {
						case 1:
							do {
								temp = 0;
								if (temp > 0 && myplanet.checkID(idnum, 0)) {
									System.out.println("Planet ID already in use, try different ID");
								}
								else if (temp > 0) {
									System.out.println("Invalid ID");
								}
								System.out.println("Enter New Planet ID:");
								idnum = in.nextInt();
								temp++;
							}while (myplanet.checkID(idnum, x) || idnum <= 0);
							break;
						case 2:
							System.out.println("Enter New Planet Name:");
							in.nextLine();nam = in.nextLine();
							break;
						case 3:
							System.out.println("Enter New Atmosphere:");
							atmos = in.nextLine();
							break;
						case 4:
							do {
								System.out.println("Enter New Gravity (m/s^2):");
								grav = in.nextDouble();
							}while (grav <= 0);
							break;
						case 5:
							System.out.println("Enter New Life Forms:");
							in.nextLine();life = in.nextLine();
							break;
						case 6:
							System.out.println("Enter New Land Composition:");
							land = in.nextLine();
							break;
						case 7:
							do {
								System.out.println("Enter New Distance from Earth (light years):");
								dist = in.nextDouble();
							}while (dist <= 0);
							break;
						case 8:
							if (!myplanet.doubleID(x)){
								temp = 0;
								do {
									if (temp != 0) {
										System.out.println("Invaid Weapon ID or Cannot Change Weapon ID, As the Weapon Will Never be Unlocked");
									}temp++;
									System.out.println("Enter New Weapon ID to Unlock (0 for No Weapon):");
									wunl = in.nextInt();	
								}while(!myweapon.checkID(wunl)|| wunl < 0 || wunl != myplanet.getweapon(x));
							}

							else {
								temp = 0;
								do {
									if (temp != 0) {
										System.out.println("Enter Valid Weapon ID");
									}temp++;
									System.out.println("Enter New Weapon ID to Unlock (0 for No Weapon):");
									wunl = in.nextInt();								
								}while(!myweapon.checkID(wunl)|| wunl < 0);
							}
							break;

						}
						do {
							System.out.println("Press 1 to confirm or Press 0 to decline:");
							con = in.nextInt();
							if (con == 1) {
								myplanet.editPlanet(x, idnum, nam, atmos, grav, life, land, dist, wunl); 
								myplanet.write();
								myplanet = new List2();
							}
						}while(con != 0 && con != 1);
						break;


					case 3: //to view planets by planet ID
						myplanet.printMenu();
						do {
							do {
								System.out.println("Enter Planet ID to be printed:");
								idnum = in.nextInt();
							}while (!myplanet.checkID(idnum, 0) || idnum <= 0);
							myplanet.printNum(idnum);
							System.out.println("Enter 1 to continue or Press any integer number to return to main menu:");
							con = in.nextInt();
						}while (con != 0 && con == 1);
						break;

					case 4: //to delete planets
						temp = 0;
						do {
							if (temp != 0) {
								System.out.println("Invalid Planet ID");
							}temp++;
							System.out.println("Enter Planet ID:");
							idnum = in.nextInt();
							if (!myplanet.doubleID(idnum)) {
								System.out.println("Cannot Delete Planet, As the Weapon Associated Will Never be Unlocked");
							}
						}while(!myplanet.checkID(idnum, 0) || idnum <= 0 ||  !myplanet.doubleID(idnum));

						do {
							System.out.println("Press 1 to confirm or Press 0 to decline:");
							con = in.nextInt();
							if (con == 1) {
								myplanet.delete(idnum);
								myplanet = new List2();
							}
						}while(con != 0 && con != 1);
					}
				}
				break;
				case (2):{
					v=allfleet.getStarfleet(Integer.parseInt(file));
					v.print();
					a1=(Admirals.findFltID(v.getFltID()));
					System.out.println("\t\t\tAdmiral info");
					a1.print();    
					System.out.println("Enter any integer num to return to menu");
					in.nextInt();
				}
				break;
				case (3):{
					int fltid;
					System.out.println("Enter ID for new Fleet");
					v=new Starfleet();
					fltid=in.nextInt();
					while (allfleet.chkfleet(fltid)){
						System.out.println("Invalid Input. Enter Again");
						fltid=in.nextInt();
					}
					v.putFltID(fltid);
					System.out.println("Create Admiral For this Fleet\nEnter first name");
					a1=new Admiral();
					a1.putfname(in.next());
					System.out.println("Enter last name");
					a1.putlname(in.next());
					System.out.println("Enter age");a1.putage(in.nextDouble());
					a1.putSID(SIDchk());
					System.out.println("Enter height:");a1.putheight(in.nextDouble());
					System.out.println("Enter years in service");a1.putyrservice(in.nextDouble());
					System.out.println("Enter ID (must be 7 digits and not match any other Admiral ID): ");
					temp=in.nextInt();
					while(Admirals.IDchk(temp)&&(temp/1000000.0<1||temp/1000000.0>=10)){
						System.out.println("Invalid Input. Enter Again");
						temp=in.nextInt();
					}
					a1.putID(temp);
					((Officer)a1).putdepartment(1);
					((Admiral)a1).putFltID((fltid));
					allfleet.addAtFront(v);
					Admirals.addAtFront(a1);
					System.out.println("Your User ID to login into this account is the same as Fleet ID: "+fltid); 
					temp=0;
					do{
						if (temp!=0){System.out.println("Invalid Input. Enter again");}
						System.out.println("Enter password");
						if (temp==0)
							in.nextLine();
						temp1=in.nextLine();
						System.out.println("Confirm password");
						tempstore=in.nextLine();
						temp++;
					}while (!(temp1.equals(tempstore)));
					temp1=fltid+"|"+tempstore;
					passwords=addpassword(temp1,passwords);
					allfleet.writepassnfleet(passwords);
					Admirals.writeAdmirals();

				}
				break;
				case (4):{
					v=allfleet.getStarfleet(Integer.parseInt(file));
					if (v.getshiplist()[0]!=0){
						v.printships();
						x=0;
						do{
							if (x!=0){System.out.println("Invalid input");} 
							System.out.println("Enter the ID from the Ships connected to view more info");
							temp=in.nextInt();
							x++;
						}while (v.getship(temp));
						readship(z,z1,LcDeptheads,dept,weaponlock,temp+"");
						z.print();
					}
					else {
						System.out.println("No Ships Connected");   
					}
				}
				break;
				case (5):{
					String []store= new String[10];
					String [] storeall=new String[10];
					String[] stores=new String[10];
					int [] answered;
					boolean repeat=true;
					int count=0;
					System.out.println(" \t\t\t\t\tFeedback/ Questions reply ");
					try	{
						FileReader fr=new FileReader("J:\\forum.txt");
						BufferedReader br = new BufferedReader(fr);
						String y;
						while ((y = br.readLine()) != null) {
							count++;
							int chk=0;
							store=addpassword(y,store);
							System.out.println("Question: "+count+" "+y.substring(chk=y.indexOf("|",0)+1,y.indexOf("|",chk+1)));
						}
						br.close();
					}
					catch (IOException e){}
					catch (NumberFormatException e){}
					storeall=store;
					answered=new int[count];
					if (store[0]==null){System.out.println("No Feedback or Questions posted");}
					else{
						do {
							System.out.println("Enter the Question number to reply or enter -1 to exit");
							do {
								repeat=false;
								x=in.nextInt();
								for (int i=0;i<answered.length;i++){
									if (answered[i]==x){
										repeat=true;
										System.out.println("Quesntion already answered. Enter again");
									}
								}
							}while(repeat);
							while (x>count||x<-1){
								System.out.println("Invalid input. Enter again");
								x=in.nextInt();
							}
							if (x!=-1){answered[x-1]=x;}
							int sub=0;
							boolean schk=true;
							String [] temps=new String[storeall.length];

							for (int i=0;i<store.length&&store[i]!=null;i++){
								if (i+1==x){sub=1; 
								if (i==0){schk=true;}
								}
								if (schk){
									temps[i]=store[i+1];
								}
								else {
									temps[i-sub]=store[i];
								}
							}
							storeall=temps;

							int chk=0,chk2=0;
							if (x!=-1){chk=store[x-1].indexOf("|",0);
							System.out.println("Question: "+store[x-1].substring(chk+1,store[x-1].indexOf("|",chk+1)));  
							System.out.println("Enter Response:");
							in.nextLine();
							store[x-1]+=in.nextLine()+"|";
							try{
								FileWriter fw = new FileWriter("J:\\"+store[x-1].substring(0,store[x-1].indexOf("|"))+"newsarchive.txt",true);    // 1) FileWriter 
								PrintWriter pw = new PrintWriter (fw);
								pw.println(store[x-1].substring(store[x-1].indexOf("|")+1));				
								pw.close();
							}
							catch (IOException e){}
							catch (NumberFormatException e){}
							System.out.println("Would you like to save the response.\nEnter 1 to save or any number to skip");
							if (in.nextInt()==1){
								try
								{   

									FileWriter fw = new FileWriter("J:\\savedresponse.txt",true);    // 1) FileWriter 
									PrintWriter pw = new PrintWriter (fw);
									pw.println(store[x-1].substring(store[x-1].indexOf("|")+1));	
									pw.close();
								}
								catch (IOException e){}
								catch (NumberFormatException e){}
							}}
						}while (x!=-1);
						try {
							FileWriter fw = new FileWriter("J:\\forum.txt"); 
							PrintWriter pw = new PrintWriter (fw); 
							for (int i=0;i<storeall.length && storeall[i]!=null;i++){
								pw.println(storeall[i]);
							}
							pw.close();
						}catch (IOException e){}
					}}
				break;
				case (6):{
					try{
						int chk=0;
						FileReader fr=new FileReader("J:\\savedresponse.txt");
						BufferedReader br = new BufferedReader(fr);
						String s=br.readLine();
						if (s==null){System.out.println("No responses saved");}
						else{
							System.out.println("\t\t\t\t\tSaved Responses");
							while ((s!= null)) {				
								System.out.println("Question: "+s.substring(0,chk=s.indexOf("|")));
								System.out.println("Response: "+s.substring(chk+1,s.indexOf("|",chk+1)));
								System.out.println("\n");
								s=br.readLine();
							}
							br.close();

						}}
					catch (IOException e){}
					catch (NumberFormatException e){}
				}
				}
			}}

	}

}
