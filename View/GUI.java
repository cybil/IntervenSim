import javax.swing.JFrame;

public class GUI {
    private static boolean	m_inst = false;
    MainWindow 				m_mainWindow;
    private Map				map;

    public GUI(Map map) {
	System.out.print("Une nouvelle instance de la classe GUI a ete creee.\n");
	m_inst = true;
	this.map = map;
	try {
	    m_mainWindow = new MainWindow(map);
	}
	catch (NombreInstanceGUIException e) {}
    }
	
}
