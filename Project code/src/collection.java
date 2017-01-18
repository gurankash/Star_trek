import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class collection {// creates a linked list of Personnel
	private Node lower; 
	public void addAtFront(Personnel x){
		lower=new Node(x,lower);}
	public void delete(){
	}
	public void transfer(collection x){// Copies the list to another list
		for (Node i=x.lower;i!=null;i=i.link){
			this.add(i.x);
		}
	}
	public void connections(Captain z1){// Arranges connections such as proteges and Commanders connection
		for (Node i=lower;i!=null;i=i.link){
			if (i.x instanceof Ensign ){
				((Commander)findID(((Ensign)i.x).getmentor())).putprotege(i.x);
				((LtCommander)findID(((Ensign)i.x).getsupervisor())).putRpOfficers(i.x);
			}
			else if(i.x instanceof LtJuniorgrade||i.x instanceof LtFullgrade){
				((LtCommander)findID(((Lieutenant)i.x).getsupervisor())).putRpOfficers(i.x);	
			}
			else if(i.x instanceof LtCommander && ((LtCommander)i.x).gethead()!=0){
				((Commander)findID(((LtCommander)i.x).getsupervisor())).putLCdeptheads(i.x);
			}
			if (i.x instanceof Commander){
				((Captain)z1).putcommanders(i.x);
			}
		}
	}
	public boolean chk(){if (lower==null){return false;}else {return true;}}//chks to see if the list is empty 
	public collection printlist(double y){
		collection send=new collection();
		for (Node i=lower;i!=null;i=i.link){
			if (!(i.x instanceof Officer) && y==0){
				System.out.println("ID: "+i.x.getID()+"\t");
				send.add(i.x);
			}
			if((i.x instanceof Officer && ((Officer)i.x).getpips()==y)){
				System.out.println("ID: "+i.x.getID()+"\t");
				send.add(i.x);
			}	
		}
		if (send.lower==null){System.out.println("None of the type Exist");}
		return send;
	}
	public Personnel findID(int x){// returns the personnel with given ID
		Personnel temp=null;
		for (Node i=lower;i!=null && temp==null;i=i.link){
			if (i.x.getID()==x)
				temp=i.x;			 
		}
		return temp;
	}
	public Personnel findFltID(int x){// Returns admiral depending on ID sent
		Personnel temp=null;
		for (Node i=lower;i!=null && temp==null;i=i.link){
			if (((Admiral)i.x).getFltID()==x)
				temp=i.x;			 
		}
		return temp;
	}	
	public collection option(double x, int y){// Displays different personnel options available
		int count=1;
		collection send=new collection();
		for (Node i=lower;i!=null;i=i.link,count++){
			if (((Officer)i.x).getpips()==x&&i.x.getID()!=y){
				System.out.println(count+": "+i.x.getID());
				send.add(i.x);
			}
		}
		return send;
	}
	public void delete(Personnel y){// deltes Personnel
		if (lower==null){System.out.println("No personnel present in the list");}
		else if(y.getID()==lower.x.getID()){lower=lower.link;}
		else{
			lower.delete(y);
		}
	}
	public boolean IDchk(int x){// Checks if ID exits
		boolean y=false;
		for (Node i=lower;i!=null&&y==false;i=i.link){if (i.x.getID()==x){y=true;}}
		return y;
	}
	public void add(Personnel y){// Adds a personnel to a linked list
		if ((!(y instanceof Officer))||lower==null){
			lower=new Node(y,lower);
		}
		else{
			Personnel temp=lower.x;
			if (lower.x instanceof Officer && ((Officer)temp).getpips()>=((Officer)y).getpips()){lower=new Node(y,lower);}
			else{lower.add(y);}
		}
	}
	public int findlist(double x){//returns the number of Personnel available depending on the number of pips
		int count=0;
		for (Node i=lower;i!=null;i=i.link){
			if (x==0 && (!(i.x instanceof Officer))){
				count++;
			}
			else{
				Personnel temp=new Officer();
				temp=i.x;
				if (i.x instanceof Officer && ((Officer)temp).getpips()==x){count++;}
			}}

		return count;
	}
	public void printCollection(){// prints all the ID's present in the linked list
		int count=1;
		for (Node i=lower;i!=null;i=i.link,count++){System.out.print(count+": "+i.x.getID()+"\t");}
		System.out.println("");
	}
	public static String generalprint(Personnel x){// Prints general Feilds of a Personnel (all Personnel class fields)
		return x.getfname()+"|"+x.getlname()+"|"+x.getage()+"|"+x.getSID()+"|"+x.getheight()+"|"+x.getyrservice()+"|"+x.getID()+"|";

	}
	public void readAdmirals(){// reads different admirals from a file
		try
		{  Personnel a1=new Admiral();
		String x;		
		String temp;
		int chk=0;
		int chk2=0;
		FileReader fr=new FileReader("J:\\admiral.txt");
		BufferedReader br = new BufferedReader(fr);
		while ((x=br.readLine())!=null){
			a1.putlname(x.substring(chk,chk2=x.indexOf("|",chk+1)));
			a1.putfname(x.substring(chk2+1,chk=x.indexOf("|",chk2+1)));
			a1.putage(Double.parseDouble(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
			a1.putheight(Double.parseDouble(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
			a1.putSID(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
			a1.putID(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
			a1.putyrservice(Double.parseDouble(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
			((Officer)a1).putdepartment(Integer.parseInt(x.substring(chk2+1,chk=x.indexOf("|",chk2+1))));
			((Admiral)a1).putFltID(Integer.parseInt(x.substring(chk+1,chk2=x.indexOf("|",chk+1))));
			while (!(temp=x.substring(chk2+1,chk=x.indexOf("|",chk2+1))).equals("\\\\")){
				chk2=chk;
				((Admiral)a1).putcaptains(Integer.parseInt(temp));
			}
			addAtFront(a1);

		}

		br.close();

		}
		catch (IOException e){}
		catch (NumberFormatException e){}
	}
	public void writeAdmirals(){// writes the Admirals to a file
		try
		{   String x;		
		String temp;
		int chk=0;
		int chk2=0;

		FileWriter fw = new FileWriter("J:\\admiral.txt");    // 1) FileWriter 
		PrintWriter pw = new PrintWriter (fw);
		for (Node i=lower;i!=null;i=i.link){
			x=i.x.getlname()+"|";
			x+=i.x.getfname()+"|";
			x+=i.x.getage()+"|";
			x+=i.x.getheight()+"|";
			x+=i.x.getSID()+"|";
			x+=i.x.getID()+"|";
			x+=i.x.getyrservice()+"|";
			x+=(((Officer)i.x).getdepartment()+"|");
			x+=(((Admiral)i.x).getFltID()+"|");
			for (int z=0;z<((Admiral)i.x).getcaptains().length&&((Admiral)i.x).getcaptains()[z]!=0;z++){
				x+=((Admiral)i.x).getcaptains()[z]+"|";
			}
			x+="\\\\|";
			pw.println(x);
		}
		pw.close();

		}
		catch (IOException e){}
		catch (NumberFormatException e){}
	}
	public void writeship(Starship z,int[] weaponlock,String file){// writes all the attributes of the ship to a file
		try {
			String x;
			boolean c=true;
			FileWriter fw = new FileWriter("J:\\"+file+".txt");    // 1) FileWriter 
			PrintWriter pw = new PrintWriter (fw);     // 2) PrintWriter
			x=z.getregistrynum()+"|"+z.getFleetID()+"|"+z.getPtropedoes()+"|"+z.getFshields()+"|"+z.getAshilds()+"|"+z.getSclass();
			pw.println(x);
			for (Node i=lower;i!=null;i=i.link){
				if (i.x instanceof Personnel){
					x=0.0+"|"+generalprint(i.x);
				}
				if (i.x instanceof Ensign){
					x=1.0+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID()+"|"+((Ensign)i.x).getmentor()+"|"+((Ensign)i.x).getsupervisor();
				}
				if(i.x instanceof LtJuniorgrade){
					x=1.5+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID()+"|"+((LtJuniorgrade)i.x).getsupervisor();
				}
				if(i.x instanceof LtFullgrade){
					x=2.0+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID()+"|"+((LtFullgrade)i.x).getsupervisor();
				}
				if(i.x instanceof LtCommander){
					x=2.5+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID()+"|"+((LtCommander)i.x).getsupervisor()+"|"+((LtCommander)i.x).gethead();
				}
				if(i.x instanceof Commander){
					x=3.0+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID()+"|"+((Commander)i.x).getdepthead();
				}
				if (i.x instanceof Executive){
					x=3.1+"|"+generalprint(i.x)+"|"+((Officer)i.x).getdepartment();
				}
				if (i.x instanceof Captain){
					x=4.0+"|"+generalprint(i.x)+((Officer)i.x).getdepartment()+"|"+((Officer)i.x).getShID();
				}
				pw.println(x);
			}
			pw.println("\\\\");
			x="";
			for (int i=0;c;i++){
				if (weaponlock[i]!=0){
					x=weaponlock[i]+"|";
				}
				else{c=false;}
			}
			x=x+"\\\\|";
			pw.println(x);
			pw.close();
		}
		catch (IOException e){}
	}
	class Node{
		Personnel x;
		Node link;
		public Node(Personnel z,Node y){
			x=z;
			link=y;
		}
		public void delete(Personnel y){// deletes a Personnel
			if (link==null){System.out.println("Personnel not present in the list");}
			else if(link.x.getID()==y.getID()){
				link=link.link;
			}
			else {link.delete(y);}
		}
		public void add(Personnel y){// adds a personnel to a list
			if (link==null){link=new Node(y,null);}
			else if (link.x instanceof Officer){
				Personnel temp=link.x;
				if (((Officer)temp).getpips()>=((Officer)y).getpips()){link=new Node(y,link);}
			}
			else{link.add(y);}
		}

	}
}