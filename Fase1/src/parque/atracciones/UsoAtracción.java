package parque.atracciones;

import parque.pulseras.Pulsera;

/**
 * <p>
 * <b>Interfaz</b> de uso de cualquier tipo de <code>atracción</code>
 * </p>
 * <p>
 * Utilizada por agentes {@link parque.agentes.Cliente}
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public interface UsoAtracción
{
	/**
	 * <p>
	 * Desencadena el conjunto de acciones asociadas al <i>uso</i> de una atracción por parte de un agente {@link parque.agentes.Cliente}.
	 * </p>
	 * @param p {@link parque.pulseras.Pulsera} del cliente sobre la que actuará la <i>atracción</i> tanto para restar tiques como para sumarlos, si procede.
	 * @throws InterruptedException si el thread es interrumpido durante su actividad.
	 */
	void usar ( Pulsera p ) throws InterruptedException;
}
