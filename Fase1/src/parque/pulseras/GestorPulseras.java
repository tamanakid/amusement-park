package parque.pulseras;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Clase que implementa las interfaces de supervisión ({@link SupervisiónPulseras}), control ({@link ControlPulseras}) y uso ({@link UsoPulseras}) sobre el repositorio de <i><b>pulseras activas</b></i>.
 * </p>
 * <p>
 * El repositorio de <i><b>pulseras activas</b></i> almacena información, en tiempo real, sobre el número de tiques asociados a cada {@link Pulsera}.  
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public class GestorPulseras
implements UsoPulseras, ControlPulseras, SupervisiónPulseras
{
	
	private int maxTiquesPulsera;
	private AtomicInteger totalTiquesVendidos;
	private AtomicInteger totalPulserasVendidas;
	private HashMap<Pulsera, Integer> pulseras;
	private Random randomGenerator;

	/**
	 * @param maxTiquesPulsera número máximo de tiques con los que se puede obtener ( {@link #obtenerPulsera()}) una {@link Pulsera}
	 */
	public GestorPulseras ( int maxTiquesPulsera )
	{
		this.randomGenerator = new Random();
		this.maxTiquesPulsera = maxTiquesPulsera;
		this.totalTiquesVendidos = new AtomicInteger(0);
		this.totalPulserasVendidas = new AtomicInteger(0);
		this.pulseras = new HashMap<Pulsera, Integer>();
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.UsoPulseras#obtenerPulsera()
	 */
	@Override
	public Pulsera obtenerPulsera ()
	{
		int tiques = randomGenerator.nextInt(this.maxTiquesPulsera) + 1;
		System.out.println("GestorPulseras: Pulsera generada con " + tiques + " tiques");
		Pulsera newPulsera = new Pulsera();
		
		this.totalTiquesVendidos.addAndGet(tiques);
		this.totalPulserasVendidas.addAndGet(1);
		synchronized(this.pulseras) {
			this.pulseras.put(newPulsera, tiques);			
		}
		
		return newPulsera;
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.ControlPulseras#restarTique(parque.pulseras.Pulsera)
	 */
	@Override
	public void restarTique ( Pulsera p )
	{
		synchronized(this.pulseras) {
			int valor = this.pulseras.get(p);
			this.pulseras.put(p, valor - 1);			
		}
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.ControlPulseras#sumarTique(parque.pulseras.Pulsera)
	 */
	@Override
	public void sumarTique ( Pulsera p )
	{
		synchronized(this.pulseras) {
			int valor = this.pulseras.get(p);
			this.pulseras.put(p, valor + 1);			
		}
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.SupervisiónPulseras#tiquesVendidos()
	 */
	@Override
	public int tiquesVendidos ()
	{
		return this.totalTiquesVendidos.get();
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.SupervisiónPulseras#pulserasVendidas()
	 */
	@Override
	public int pulserasVendidas ()
	{
		return this.totalPulserasVendidas.get();
	}

	/* (non-Javadoc)
	 * @see parque.pulseras.UsoPulseras#quedanTiques(parque.pulseras.Pulsera)
	 */
	@Override
	public boolean quedanTiques ( Pulsera p )
	{
		return (this.pulseras.get(p) > 0) ? true : false;
	}
}
