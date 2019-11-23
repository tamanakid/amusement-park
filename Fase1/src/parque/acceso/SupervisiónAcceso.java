package parque.acceso;

/**
 * <p>
 * <b>Interfaz de supervisión</b> del <code>Dispositivo de control de acceso</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.Supervisor}
 * </p>
 * @author DTE-SSOO 2019-20
 *  
 */
public interface SupervisiónAcceso
{
	/**
	 * <p>
	 * Permite conocer el número de agentes {@link parque.agentes.Cliente} que un determinado instante se encuentran dentro del parque.
	 * Se entiende que un agente <code>cliente</code> está dentro del parque si ha entrado al parque por el dispositivo de <code>control de acceso</code> y no ha salido
	 * </p>
	 * @return número de agentes {@link parque.agentes.Cliente} que hay en el parque
	 * 
	 * 
	 */
	int clientesDentro ();
	/**
	 * <p>	
	 * Permite conocer el número de agentes {@link parque.agentes.Cliente} que han salido del parque desde el momento de su apertura.
	 * Se considera que un agente <code>cliente</code> ha salido del parque si ha entrado por el dispositivo de <code>control de acceso</code> y ha salido del parque por dicho dispositivo
	 * </p>
	 * @return número de agentes {@link parque.agentes.Cliente} que han salido del parque desde el momento de su apertura
	 */
	int clientesHanSalido ();
}
