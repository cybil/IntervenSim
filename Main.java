import java.util.Scanner;

public class Main
{
    public static void	main(String[] args)
    {
	Controller		controller = new Controller();
	controller.eventPutNode(10, 2);
	controller.eventPutNode(75, 84);
	controller.eventPutNode(63, 10);
	controller.eventPutNode(35, 31);
	controller.eventPutNode(2, 9);
	controller.eventPutNode(54, 5);

	int[]		c1 = {10, 2};
	int[]		c2 = {75, 84};
	controller.eventAddNodeUrgency(c1, Urgency.EUrgencyState.SLEEPING, 10);

	controller.eventAddRoad(c1, c2);

	controller.displayMap();

	controller.eventPlay();
	while (true)
	    {
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir un mot de 1 caractere pour le Play/Pause:");
		String str = sc.nextLine();
		if (str.length() == 1)
		    {
			controller.eventPause();
		    }
		if (str.length() == 2)
		    {
			System.out.println("Stop ?");
			controller.eventStop();
		    }
	    }
    }
}