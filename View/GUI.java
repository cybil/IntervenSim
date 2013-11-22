import javax.swing.JFrame;

public class GUI {
	private static boolean	m_inst = false;
	MainWindow 				m_mainWindow;
	
	public GUI() throws NombreInstanceGUIException {
		if (m_inst)
			throw new NombreInstanceGUIException("Il existe deja une instance de la classe GUI.");
		System.out.print("Une nouvelle instance de la classe GUI a ete creee.\n");
		m_inst = true;
		m_mainWindow = new MainWindow();
	}
	
}
