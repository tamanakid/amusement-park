package parque.pulseras;

/**  
 * <p>
 * <b>Interfaz de control</b> del <code>sistema de gestión de pulseras</code>
 * </p>
 * <p>
 * Utilizada por implementaciones de la interfaz de uso de las atracciones ({@link parque.atracciones.UsoAtracción})
 * </p>
 * @author DTE-SSOO 2019-20
 */
public interface ControlPulseras
{
	/**
	 * <p>
	 * Decrementa en <i>uno</i> del número de tiques asociados a <code>p</code>
	 * </p>  
	 * @param p {@link Pulsera}
	 */
	void restarTique ( Pulsera p );
	/**
	 * <p>
	 * Incrementa en <i>uno</i> del número de tiques asociados a <code>p</code>
	 * </p>  
	 * @param p {@link Pulsera}
	 */
	void sumarTique ( Pulsera p );
}
