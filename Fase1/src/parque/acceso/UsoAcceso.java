package parque.acceso;


/** 
 * <p>
 * <b>Interfaz de uso</b> del <code>Dispositivo de control de acceso</code>
 * </p>
 * <p>
 * Utilizada por el agente {@link parque.agentes.Cliente}
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public interface UsoAcceso
{
	/**
	 * <p>
	 * Permite simular la entrada al parque por parte de un {@link parque.agentes.Cliente}.<br>
	 * Bloquea al thread que lo invoca hasta que pueda acceder al parque.
	 * </p>
	 * @throws InterruptedException si el thread es desbloqueado por una causa diferente a las esperadas.
	 */
	void entrar () throws InterruptedException;
	
	/**
	 *  Permite simular la salida del parque por parte de un {@link parque.agentes.Cliente}
	 */
	void salir ();
}
