import javax.swing.JFrame;

public class GUI {
    private static boolean	m_inst = false;
    MainWindow			m_mainWindow;

    public GUI(Controller controller) {
	System.out.print("Une nouvelle instance de la classe GUI a ete creee.\n");
	m_inst = true;
	try {
	    m_mainWindow = new MainWindow(controller);
	}
	catch (NombreInstanceGUIException e) {}
    }
	
}
