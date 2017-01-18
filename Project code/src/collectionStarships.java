
class collectionStarships{// Linked list for Starships
	starship head;
	public void addAtFront(Starship z){
		head=new starship(z,head);
	}
	class starship{// Node class for linked list
		Starship x;
		starship link;
		public starship(Starship z, starship y){
			x=z;
			link=y;
		}
	}
}
