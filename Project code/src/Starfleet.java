
class Starfleet{
	//Feilds
	private int FltID;
	private int [] shiplist=new int[10];
	public int getFltID(){return FltID;}// returns Fleet ID
	public void putFltID(int x){ FltID=x;}// Mutates FleetID
	public int[] getshiplist(){return shiplist;}// Returns shiplist (ID's of all ships connected)
	public void putShip(int x){// adds a ship
		boolean added=true;
		for (int i=0;added;i++){
			if (i+1==shiplist.length){
				int [] shiplist1=new int[shiplist.length+10];
				for (int z=0;z<shiplist.length;z++){
					shiplist1[z]=shiplist[z];
				}
				shiplist=shiplist1;
			}
			if (shiplist[i]==0){shiplist[i]=x;added=false;}
		}
	}
	public void printships(){// prints the ship
		for (int i=0;shiplist[i]!=0;i++){
			System.out.print(1+i+": "+shiplist[i]+"\t");
		}
		System.out.println(" ");
	}
	public void print(){// prints the Star fleet
		System.out.println("Fleet ID:"+FltID+"\nShips Connected (ID numbers");
		printships();
	}
	public boolean getship(int z){// checks to see if ships ID exists
		boolean send=true;
		for (int i=0;shiplist[i]!=0&& i<shiplist.length;i++){
			if (z==shiplist[i]){send =false;}
		}
		return send;
	}
}
