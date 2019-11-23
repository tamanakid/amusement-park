package parque.atracciones;

/**
 * <p>
 * <b>Interfaz de supervisión</b> de <code>atracciones de tipo viaje</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.Supervisor}
 * </p>
 * @author DTE-SSOO 2019-20
 */

public interface SupervisiónViajes
{
	/**
	 * Indica a la atracción que debe cerrar.
	 */
	void cerrarAtracción ();
	
	/**
	 * Permite conocer el número de viajes realizados por la atracción desde la apertura del parque.
	 * @return número de viajes realizados por la atracción desde la apertura del parque.
	 */
	int viajesRealizados ();
	
	/**
	 * Permite conocer el número de tiques recaudados por la atracción desde el momento de apertura del parque.
	 * @return número de tiques recaudados por la atracción desde el momento de apertura del parque.
	 */
	int tiquesRecaudados ();
	
	/**
	 * Permite conocer el número de agentes {@link parque.agentes.Cliente} que un determinado instante se encuentran utilizando la atracción. 
	 * @return número de agentes {@link parque.agentes.Cliente} actuales utilizando la atracción.
	 */
	int clientesActuales ();
}
