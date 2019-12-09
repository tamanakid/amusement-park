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
			while(!this.control.debeCerrar()) {
				this.control.esperarSubida();
				this.control.realizarViaje();
				this.control.esperarBajada();
			}
			System.out.println("Sillas Voladoras cerradas");
		} catch (InterruptedException exception) {
			System.out.println("Controlador Sillas Voladoras interrumpido");
		}
	}
}
