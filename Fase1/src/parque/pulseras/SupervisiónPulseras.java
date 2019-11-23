package parque.pulseras;

/**
 * <p>
 * <b>Interfaz de supervisión</b> del <code>sistema de gestión de pulseras</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.Supervisor}
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public interface SupervisiónPulseras
{
	/**
	 * Permite conocer el número de tiques vendidos (cargados en las pulseras adquiridas) desde la apertura del parque.
	 * @return número de tiques vendidos (cargados en las pulseras adquiridas) desde la apertura del parque.
	 */
	int tiquesVendidos ();
	/**
	 * Permite conocer el número de pulseras adquiridas desde la apertura del parque.
	 * @return número de pulseras adquiridas desde la apertura del parque.
	 */
	int pulserasVendidas ();
}
