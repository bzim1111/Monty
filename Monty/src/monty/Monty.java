package monty;

import java.util.Random;

public class Monty {

	public static void main(String[] args) {

		Door[] d = new Door[3];
		Random randomGenerator = new Random();
		int random , contestant_pick , monty_reveals=0;
		int noswitch_wins=0, noswitch_losses=0;
		int switch_wins=0, switch_losses=0;
		
		for ( int j=0; j<10000000; j++ ) {
				
			/* set up the doors, default them to "goat" */
			
			for ( int i=0; i<3; i++ ) d[i] = new Door();
			for ( int i=0; i<3; i++ ) d[i].DoorSet(Door.contents.goat);
		
			/* pick a random door to set to "prize* */
			
			random = randomGenerator.nextInt(3);
			d[random].DoorSet(Door.contents.prize);
		
			/* randomly pick one for the contestant */
			
			contestant_pick = randomGenerator.nextInt(3);
		
			/* randomly pick one for Monty to reveal, make sure it's a "goat" door that's revealed */
			
			random = randomGenerator.nextInt(2);
			switch ( contestant_pick ) {

				case 0:
					monty_reveals = random + 1;
					if ( d[monty_reveals].DoorContents() == Door.contents.prize ) {
						if ( monty_reveals == 1 ) monty_reveals = 2;
						else monty_reveals = 1;
					}
					break;
				

				case 1:
					if ( random == 0 ) monty_reveals = 0;
					else monty_reveals = 2;
					if ( d[monty_reveals].DoorContents() == Door.contents.prize ) {
						if ( monty_reveals == 0 ) monty_reveals = 2;
						else monty_reveals = 0;
					}
					break;

				case 2:
					monty_reveals = random;
					if ( d[monty_reveals].DoorContents() == Door.contents.prize ) {
						if ( monty_reveals == 1 ) monty_reveals = 0;
						else monty_reveals = 1;
					}
					break;
			}
		
			/* count the wins/losses without switching */
			
			if ( d[contestant_pick].DoorContents() == Door.contents.prize ) noswitch_wins++;
			else noswitch_losses++;
			
			/* switch the contestant's door - but not to the one Monty revealed */
			
			switch ( contestant_pick ) {
			
				case 0:
					switch ( monty_reveals ) {
						case 1:
							contestant_pick = 2;
							break;
						case 2:
							contestant_pick = 1;
							break;
					}
					break;
					
				case 1:
					switch ( monty_reveals ) {
						case 0:
							contestant_pick = 2;
							break;
						case 2:
							contestant_pick = 0;
							break;
					}
					break;
					
				case 2:
					switch ( monty_reveals ) {
						case 0:
							contestant_pick = 1;
							break;
						case 1:
							contestant_pick = 0;
							break;
					}
					break;

					
				}
			
			/* count the wins/losses with switching */

			if ( d[contestant_pick].DoorContents() == Door.contents.prize ) switch_wins++;
			else switch_losses++;

		}
		
		/* print the results */
		
		System.out.println("NO SWITCH");
		System.out.println("wins   "+noswitch_wins+"  ("+((float) noswitch_wins)/((float) (noswitch_wins+noswitch_losses))*100+"%)");
		System.out.println("loses  "+noswitch_losses+"  ("+((float) noswitch_losses)/((float) (noswitch_wins+noswitch_losses))*100+"%)");
		
		System.out.println();
		
		System.out.println("SWITCH");
		System.out.println("wins   "+switch_wins+"  ("+((float) switch_wins)/((float) (switch_wins+switch_losses))*100+"%)");
		System.out.println("loses  "+switch_losses+"  ("+((float) switch_losses)/((float) (switch_wins+switch_losses))*100+"%)");		
		
	}

}
