package parque.atracciones;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

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

	private Random randomGenerator;
	private int maxCanastas;
	private Semaphore canastas;
	private ControlPulseras controlPulseras;
	private AtomicInteger recaudados;
	private AtomicInteger regalados;
	
	/**
	 * @param maxPuestos número de canastas que posee la atracción
	 * @param cPulseras {@link parque.pulseras.ControlPulseras}
	 */
	public TiroACanasta ( int maxPuestos, ControlPulseras cPulseras )
	{
		this.randomGenerator = new Random();
		this.maxCanastas = maxPuestos;
		this.canastas = new Semaphore(maxPuestos);
		this.controlPulseras = cPulseras;
		this.recaudados = new AtomicInteger(0);
		this.regalados = new AtomicInteger(0);
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.UsoAtracción#usar(parque.pulseras.Pulsera)
	 */
	@Override
	public void usar ( Pulsera p ) throws InterruptedException
	{
		this.canastas.acquire();
		System.out.println("Jugador entra en TiroACanasta");
		this.controlPulseras.restarTique(p);
		this.recaudados.addAndGet(1);
		
		boolean fallo = encestarCanastas();
		if (!fallo) {
			this.controlPulseras.sumarTique(p);
			this.controlPulseras.sumarTique(p);
			this.regalados.addAndGet(2);
		}


		this.canastas.release();
	}

	
	// Returns true if there is a miss
	private boolean encestarCanastas() throws InterruptedException
	{
		int intentos = 5;
		boolean fallo = false;
		while ((intentos > 0) && !fallo) {
			Thread.sleep(400);
			int precision = randomGenerator.nextInt(10);
			fallo = (precision < 3) ? true : false;
			intentos--;
		}
		return fallo;
	}

	
	
	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónConcurso#tiquesRecaudados()
	 */
	@Override
	public int tiquesRecaudados ()
	{
		return this.recaudados.get();
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónConcurso#tiquesRegalados()
	 */
	@Override
	public int tiquesRegalados ()
	{
		return this.regalados.get();
	}

	/* (non-Javadoc)
	 * @see parque.atracciones.SupervisiónConcurso#clientesActuales()
	 */
	@Override
	public int clientesActuales ()
	{
		return (this.maxCanastas - this.canastas.availablePermits());
	}
}
