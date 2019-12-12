package parque.agentes;


import parque.atracciones.ControlViajes;

/**  
 * <p>
 * Clase que simula el comportamiento del <b>controlador</b> de la atracción <code>Sillas voladoras</code>.
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public class ControladorSillasVoladoras
implements Runnable
{
	private ControlViajes control;

	/**
	 * @param cViajes {@link parque.atracciones.ControlViajes} interfaz de gestión la atracción <code>Sillas voladoras</code>.
	 */
	public ControladorSillasVoladoras ( ControlViajes cViajes ) {
		this.control = cViajes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run ()
	{
		try {
			this.control.esperarSubida();
			while(!this.control.debeCerrar()) {
				this.control.realizarViaje();
				this.control.esperarBajada();
				this.control.esperarSubida();
			}

		} catch (InterruptedException exception) {
			System.out.println("InterruptedException: Controlador Sillas Voladoras");
		}
		
		System.out.println("Finalizacion de ejecución del controlador de sillas voladoras.");
	}
}
