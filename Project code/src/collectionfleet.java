import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class collectionfleet{
	starfleet head;
	public void addAtFront(Starfleet x){// adds at front
		head=new starfleet(x,head);
	}
	public void printlist(){// prints the desired Fleets and returns the linked list
		for (starfleet i=head;i!=null;i=i.link){System.out.print(i.x.getFltID()+"\t");}
	}
	public void writepassnfleet(String []passwords){// Reads password list and linked list of fleet
		try {
			int count=0;
			String read;
			boolean done=true;
			while (done){
				if (count==0){read="J:\\passwords.txt";}
				else {read="J:\\allfleet.txt";}
				FileWriter fw = new FileWriter(read);   
				PrintWriter pw = new PrintWriter (fw);  
				if (count==0){
					for (int i=0;done&&i<passwords.length;i++){
						if (passwords[i]!=null){pw.println(passwords[i]);}
						else {done=false;}
					}
					count++;
					done=true;
				}
				else {
					for (starfleet s=head;s!=null;s=s.link){
						read=s.x.getFltID()+"|";
						for (int y=0;y<s.x.getshiplist().length&&done;y++){
							done=false;
							if (s.x.getshiplist()[y]!=0){
								read+=s.x.getshiplist()[y]+"|";
								done=true;
							}
						}
						read=read+"\\\\|";
						pw.println(read);
					}
					done=false;
				}
				pw.close();     
			}
		}
		catch (IOException e){}

	}
	public void print(int z){// prints the the fleet dependfing on ID
		int count=0;
		for (starfleet i=head;i!=null;i=i.link){
			count++;
			System.out.println(count+""+i.x);
			if (i.x.getFltID()==z){
				i.x.print();}}
	}
	public Starfleet getStarfleet(int x){// Returns the stafleet with sent ID
		Starfleet temp=new Starfleet();
		for (starfleet i=head;i!=null;i=i.link){if (i.x.getFltID()==x){temp=i.x;}}
		return temp;
	}
	public boolean shipchk(int x){// Checks to see if ship of given ID exists
		boolean send=false;
		for (starfleet i=head;i!=null;i=i.link){
			for (int d=0;d<i.x.getshiplist().length;d++){
				if (i.x.getshiplist()[d]==x){
					send=true;
				}
			}
		}
		return send;
	}

	public boolean chkfleet(int x){// cheks to see if fleet exists
		boolean temp=false;
		for (starfleet i=head;i!=null&&temp==false;i=i.link){
			if (i.x.getFltID()==x)
				temp=true;
		}
		return temp;
	}

	class starfleet{// Node class
		Starfleet x;
		starfleet link;
		public starfleet(Starfleet z,starfleet y){
			x=z;
			link=y;
		}
	}
}
