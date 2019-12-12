package parque.atracciones;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

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
	private boolean cerrar;
	private boolean barrera;
	private int maxPlazas;
	private int viajes;
	private AtomicInteger sillasDisponibles;
	private AtomicInteger recaudados;
	private ControlPulseras controlPulseras;
	private Lock cerrojo;
	private Condition puedeSubir;
	private Condition puedeBajar;
	private Condition empezarViaje;
	private Condition permitirEntrada;

	/**
	 * @param maxPlazas número de sillas que posee la atracción
	 * @param cPulseras {@link parque.pulseras.ControlPulseras}
	 */
	public SillasVoladoras ( int maxPlazas, ControlPulseras cPulseras )
	{
		this.cerrar = false;
		this.barrera = false;
		this.maxPlazas = maxPlazas;
		this.viajes = 0;
		this.sillasDisponibles = new AtomicInteger(this.maxPlazas);
		this.recaudados = new AtomicInteger(0);
		this.controlPulseras = cPulseras;

		this.cerrojo = new ReentrantLock();
		this.puedeSubir = this.cerrojo.newCondition();
		this.puedeBajar = this.cerrojo.newCondition();
		this.empezarViaje = this.cerrojo.newCondition();
		this.permitirEntrada = this.cerrojo.newCondition();
	}


	/* (non-Javadoc)
	 * @see parque.atracciones.UsoAtracción#usar(parque.pulseras.Pulsera)
	 */
	@Override
	public void usar ( Pulsera p ) throws InterruptedException
	{		
		this.cerrojo.lock();
		try {
			while (this.sillasDisponibles.get() == 0 || this.barrera == true) {
				this.puedeSubir.await();
			}
			System.out.println("Jugador sube a SillasVoladoras");
			
			this.recaudados.addAndGet(1);
			this.controlPulseras.restarTique(p);
			
			this.sillasDisponibles.decrementAndGet();
			this.empezarViaje.signal();
			
			// Espera a que termine el viaje
			this.puedeBajar.await();
			
			this.sillasDisponibles.incrementAndGet();
			this.permitirEntrada.signal();
			System.out.println("Jugador baja de SillasVoladoras");
		} finally {
			this.cerrojo.unlock();
		}
	}


	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#esperarSubida()
	 */
	@Override
	public void esperarSubida () throws InterruptedException
	{
		boolean isClientEntered = true;
		
		this.barrera = false;
		this.cerrojo.lock();
		this.puedeSubir.signalAll();
		try {
			while((!this.cerrar) && (this.sillasDisponibles.get() > 0) && (isClientEntered || (this.sillasDisponibles.get() == this.maxPlazas))) {
				isClientEntered = this.empezarViaje.await(5000, TimeUnit.MILLISECONDS);
			}
			this.barrera = true;
		} finally {
			this.cerrojo.unlock();
		}
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#esperarBajada()
	 */
	@Override
	public void esperarBajada () throws InterruptedException
	{
		this.barrera = false;
		this.cerrojo.lock();
		this.puedeBajar.signalAll();
		try {
			while(this.sillasDisponibles.get() < this.maxPlazas) {
				this.permitirEntrada.await();
			}
		} finally {
			this.cerrojo.unlock();
		}
		
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#realizarViaje()
	 */
	@Override
	public void realizarViaje () throws InterruptedException
	{
		this.viajes++;
		System.out.println("Viaje de SillasVoladoras");
		Thread.sleep(7500);
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.ControlViajes#debeCerrar()
	 */
	@Override
	public boolean debeCerrar ()
	{
		return this.cerrar;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#cerrarAtracción()
	 */
	@Override
	public void cerrarAtracción ()
	{
		this.cerrar = true;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#viajesRealizados()
	 */
	@Override
	public int viajesRealizados ()
	{
		return this.viajes;
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#tiquesRecaudados()
	 */
	@Override
	public int tiquesRecaudados ()
	{
		return this.recaudados.get();
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónViajes#clientesActuales()
	 */
	@Override
	public int clientesActuales ()
	{
		return this.maxPlazas - this.sillasDisponibles.get();
	}
}
