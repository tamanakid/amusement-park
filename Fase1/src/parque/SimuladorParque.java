package parque;

import parque.agentes.Supervisor;
import parque.agentes.Cliente;
// import parque.agentes.ControladorSillasVoladoras;

import parque.acceso.ControlDeAcceso;

import parque.pulseras.GestorPulseras;
import parque.pulseras.Pulsera;

import parque.atracciones.TiroACanasta;
import parque.atracciones.SillasVoladoras;
import parque.atracciones.UsoAtracción;

/**  
 * <p>
 * Clase que implementa al simulador.
 * </p>
 * <pre>
 * 	<b>uso</b>: java SimuladorParque &lt;numClientes&gt; &lt;maxTiquesPulsera&gt; &lt;aforo&gt; &lt;plazasSillas&gt; &lt;numCanastas&gt;
 * 	 <b>&lt;numClientes&gt;</b> número de clientes que se intentarán acceder al parque este día
 * 	 <b>&lt;maxTiquesPulsera&gt;</b> número máximo de tiques que se podrán cargar en cada pulsera en el momento de su expedición
 * 	 <b>&lt;aforo&gt;</b> aforo máximo del parque
 * 	 <b>&lt;plazasSillas&gt;</b> número de canastas disponibles en la atracción de tiro a canasta
 * 	 <b>&lt;numCanastas&gt;</b> número de sillas del carrusel de las sillas voladoras
 * </pre>
 * 
 * @author DTE-SSOO 2019-20
 */
public class SimuladorParque
{
	/**
	 * @param args argumentos que espera la aplicación.
	 *  
	 */
	public static void main ( String[] args ) {
		
		// Argumentos del CLI
		int clientesTotal = Integer.parseInt(args[0]);
		int maxTiquesPulsera = Integer.parseInt(args[1]);
		int aforo = Integer.parseInt(args[2]);
		int canastas = Integer.parseInt(args[3]);
		int sillas = Integer.parseInt(args[4]);
		
		
		// Instanciacion de elementos de gestión de Pulseras
		GestorPulseras gestorPulseras = new GestorPulseras(maxTiquesPulsera);
		Pulsera[] pulseras = new Pulsera[100]; // pendiente
		
		// Instanciacion de elementos de gestión de Acceso
		ControlDeAcceso controlDeAcceso = new ControlDeAcceso(aforo);
		
		// Instanciacion de atracciones
		TiroACanasta tiroACanasta = new TiroACanasta(sillas, gestorPulseras); // SupervisiónPulseras
		SillasVoladoras sillasVoladoras = new SillasVoladoras(canastas, gestorPulseras);
		UsoAtracción[] usoAtracciones = { tiroACanasta, sillasVoladoras };
		
		
		// Instanciacion de Agentes (Threads)
		Thread supervisor;
		// Thread controladorSillasVoladoras;
		Thread[] clientes = new Thread[100];
		
		supervisor = new Thread(new Supervisor(gestorPulseras, tiroACanasta, sillasVoladoras, controlDeAcceso, clientesTotal)); // etc
		
		supervisor.start();
		
		for (int i = 0; i < clientesTotal; i++) {
			clientes[i] = new Thread(new Cliente(gestorPulseras, usoAtracciones, controlDeAcceso));
			clientes[i].start();
		}
		
		for (int i = 0; i < clientesTotal; i++) {
			try {
				clientes[i].join();
			} catch (InterruptedException exception) {
				System.out.println("InterruptedException");
			}
		}
		
		try {
			supervisor.join();			
		} catch (InterruptedException exception) {
			System.out.println("InterruptedException");
		}
		
		
	}
}
