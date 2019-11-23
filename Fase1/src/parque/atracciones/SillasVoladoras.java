package parque.atracciones;

import parque.pulseras.ControlPulseras;
import parque.pulseras.Pulsera;


/**  
 * <p>
 * Clase que implementa las interfaces de supervisión ({@link SupervisiónViajes}), control ({@link ControlViajes}) y uso ({@link UsoAtracción}) de la atracción <code>Sillas voladoras</code>.
 * </p>
 * @author DTE-SSOO 2019-20
 */

public class SillasVoladoras
implements UsoAtracción, ControlViajes, SupervisiónViajes
{

	/**
	 * @param maxPlazas número de sillas que posee la atracción
	 * @param cPulseras {@link parque.pulseras.ControlPulseras}
	 */
	public SillasVoladoras ( int maxPlazas, ControlPulseras cPulseras )
	{
	}


	/* (non-Javadoc)
	 * @see parque.atracciones.UsoAtracción#usar(parque.pulseras.Pulsera)
	 */
	@Override
	public void usar ( Pulsera p ) throws InterruptedException
	{
	}


	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#esperarSubida()
	 */
	@Override
	public void esperarSubida () throws InterruptedException
	{
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#esperarBajada()
	 */
	@Override
	public void esperarBajada () throws InterruptedException
	{
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#realizarViaje()
	 */
	@Override
	public void realizarViaje () throws InterruptedException
	{
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#debeCerrar()
	 */
	@Override
	public boolean debeCerrar ()
	{
		return false;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#cerrarAtracción()
	 */
	@Override
	public void cerrarAtracción ()
	{
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#viajesRealizados()
	 */
	@Override
	public int viajesRealizados ()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#tiquesRecaudados()
	 */
	@Override
	public int tiquesRecaudados ()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#clientesActuales()
	 */
	@Override
	public int clientesActuales ()
	{
		return 0;
	}
}
