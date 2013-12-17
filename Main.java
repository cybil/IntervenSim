import java.util.Scanner;

public class Main
{
    public static void	main(String[] args)
    {
	Controller		controller = new Controller();
	controller.eventPutNode(800, 600);
	controller.eventPutNode(500, 500);
	controller.eventPutNode(200, 200);
	// controller.eventPutNode(236, 152);
	// controller.eventPutNode(84, 212);
	// controller.eventPutNode(413, 85);
 
	int[]		c1 = {800, 600};
	int[]		c2 = {500, 500};
	int[]		c3 = {200, 200};
	controller.eventAddNodeUrgency(800, 600, Urgency.EUrgencyState.SLEEPING, 0f, 5f, 0);

	controller.eventAddRoad(c1, c2);
	controller.eventAddRoad(c2, c3);
	controller.eventEditAttachPoint(200, 200);
	controller.eventCreatVehicule(200, 200);

	// controller.displayMap();

	// controller.eventPlay();
	// while (true)
	//     {
	// 	Scanner sc = new Scanner(System.in);
	// 	System.out.println("Veuillez saisir un mot de 1 caractere pour le Play/Pause:");
	// 	String str = sc.nextLine();
	// 	if (str.length() == 1)
	// 	    {
	// 		controller.eventPause();
	// 	    }
	// 	if (str.length() == 2)
	// 	    {
	// 		System.out.println("Stop ?");
	// 		controller.eventStop();
	// 	    }
	//     }
    }
}