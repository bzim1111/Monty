package monty;

public class Door {

	enum contents { prize , goat }
	
	contents c;
	
	
	public void Door () {
		/* constructor */
	}
	
	
	public Door.contents DoorContents() {
		return ( c );
	}
	
	
	public void DoorSet ( Door.contents cont ) {
		c = cont;
	}
	
	
	public void DoorPrint() {
		switch ( c ) {
			case prize:
				System.out.println("Prize");
				break;
			case goat:
				System.out.println("Goat");
		}
			
	}

	
}
