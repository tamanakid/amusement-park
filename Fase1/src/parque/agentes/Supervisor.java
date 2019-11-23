package parque.agentes;

import parque.pulseras.SupervisiónPulseras;
import parque.atracciones.SupervisiónConcurso;
import parque.atracciones.SupervisiónViajes;
import parque.acceso.SupervisiónAcceso;

/**
  * <p>
 * Clase que simula el comportamiento del <b>Supervisor</b> del parque de atracciones.
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public class Supervisor
implements Runnable
{
	
	private SupervisiónPulseras sPulseras;
	private SupervisiónConcurso sConcurso;
	private SupervisiónViajes sViajes;
	private SupervisiónAcceso sAcceso;
	private int numClientes;

	/**
	 * @param sPulseras {@link parque.pulseras.SupervisiónPulseras} interfaz de supervisión del <code>sistema de gestión de pulseras</code>
	 * @param sConcurso {@link parque.atracciones.SupervisiónConcurso} interfaz de supervisión de la atracción <code>Tiro a canasta</code>
	 * @param sViajes {@link parque.atracciones.SupervisiónViajes} interfaz de supervisión de la atracción <code>Sillas voladoras</code>
	 * @param sAcceso {@link parque.acceso.SupervisiónAcceso} interfaz de supervisión del <code>dispositivo de control de acceso</code>
	 * @param numClientes número de clientes que podrán acceder al parque
	 */
	public Supervisor (
		SupervisiónPulseras sPulseras,
		SupervisiónConcurso sConcurso,
		SupervisiónViajes sViajes,
		SupervisiónAcceso sAcceso,
		int numClientes )
	{
		this.sPulseras = sPulseras;
		this.sConcurso = sConcurso;
		this.sViajes = sViajes;
		this.sAcceso = sAcceso;
		this.numClientes = numClientes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run () {
		for (int i = 0; i < 15; i++) {
			System.out.println("@time" + i);
			System.out.println(" - Pulseras vendidas: "	+ sPulseras.pulserasVendidas());
			System.out.println(" - Tiques vendidos: " 	+ sPulseras.tiquesVendidos());
			System.out.println(" - Aforo actual: " 		+ sAcceso.clientesDentro());
			System.out.println(" - Aforo historico: " 	+ sAcceso.clientesHanSalido());
			
			try {
				Thread.sleep(1000);			
			} catch (InterruptedException exception) {
				System.out.println("InterruptedException");
			}
		}
	}
}
