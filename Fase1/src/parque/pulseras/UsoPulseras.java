package parque.pulseras;

/**
 * <p>
 * <b>Interfaz de uso</b> del <code>sistema de gestión de pulseras</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.Cliente}
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public interface UsoPulseras
{
	/**
	 * Permite conocer si quedan tiques en una {@link Pulsera}
	 * @param p {@link Pulsera}
	 * @return <code>true</code> si el número de tiques que quedan en <code>p</code> es mayor que <code>0</code>; <code>false</code> en caso contrario.
	 */
	boolean quedanTiques ( Pulsera p );
	/**
	 * Posibilita a un agente {@link parque.agentes.Cliente} la obtención de una {@link Pulsera} antes de entrar en el parque.  
	 * @return una nueva {@link Pulsera}
	 */
	Pulsera obtenerPulsera ();
}
