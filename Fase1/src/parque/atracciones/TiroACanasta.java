package parque.atracciones;

import parque.pulseras.ControlPulseras;
import parque.pulseras.Pulsera;

/**
 * <p>
 * Clase que implementa las interfaces de supervisión ({@link SupervisiónConcurso}) y uso ({@link UsoAtracción})de las <code>atracciones de tipo concurso</code>.
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public class TiroACanasta
implements UsoAtracción, SupervisiónConcurso
{

	/**
	 * @param maxPuestos número de canastas que posee la atracción
	 * @param cPulseras {@link parque.pulseras.ControlPulseras}
	 */
	public TiroACanasta ( int maxPuestos, ControlPulseras cPulseras )
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
	 * @see parque.atracciones.SupervisiónConcurso#tiquesRecaudados()
	 */
	@Override
	public int tiquesRecaudados ()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónConcurso#tiquesRegalados()
	 */
	@Override
	public int tiquesRegalados ()
	{
		return 0;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónConcurso#clientesActuales()
	 */
	@Override
	public int clientesActuales ()
	{
		return 0;
	}
}
