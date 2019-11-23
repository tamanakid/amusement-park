package parque.atracciones;

/**  
 * <p>
 * <b>Interfaz de control</b> de <code>atracciones de tipo viaje</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.ControladorSillasVoladoras}
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public interface ControlViajes
{
	/**
	 * <p>
	 * Bloquea al thread que lo invoca hasta que no queden plazas libres en la atracción asociada o 
	 * haya transcurrido un cierto tiempo desde que subió el último pasajero.
	 * </p>
	 * <p>
	 * El objetivo es que la atracción no realice viajes sin <code>clientes</code>, pero que los <code>clientes</code> no tengan por qué esperar
	 * a que no queden plazas libres para poder disfrutar de la atracción.
	 * </p>
	 * @throws InterruptedException si el thread es desbloqueado por una causa diferente a las esperadas.
	 */
	void esperarSubida () throws InterruptedException;
	
	/**
	 * <p>
	 * Bloquea al thread que lo invoca hasta que no queden <code>clientes</code> en la atracción.
	 * </p>
	 * <p>
	 * El objetivo es que no se mezclen los <code>clientes</code> que <i>suben</i> con los que <i>bajan</i>.
	 * </p>
	 * @throws InterruptedException si el thread es desbloqueado por una causa diferente a las esperadas.
	 */
	void esperarBajada () throws InterruptedException;
	
	/**
	 *<p>
	 * Simula el viaje de la atracción.
	 *</p>
	 *<p>
	 * Se impide la <i>subida</i> de nuevos <code>clientes</code> y se inicia el viaje.
	 * </p>
	 * <p>
	 * La duración del viaje se simula mediante una espera de un tiempo determinado (por ejemplo 1 segundo).<br>
	 * Mientras dure el viaje no es posible subir ni bajar de la atracción. <br>
	 * Sólo se dará inicio al viaje si la barrera de acceso a la atracción está cerrada y no hay algún {@link parque.agentes.Cliente} subido.
	 * <p>
	 * @throws InterruptedException si el thread es desbloqueado por una causa diferente a las esperadas.
	 */
	void realizarViaje () throws InterruptedException;
	
	/**
	 * <p>
	 * Permite conocer si el agente {@link parque.agentes.Supervisor} ha indicado que la atracción <i>debe cerrar</i>.
	 * </p>
	 * @return <code>true</code> si el agente {@link parque.agentes.Supervisor} ha indicado que la atracción <i>debe cerrar</i>; <code>false</code> en caso contrario.
	 */
	boolean debeCerrar ();
}
