package parque.agentes;

import java.util.Random;

import parque.acceso.UsoAcceso;

import parque.atracciones.UsoAtracción;

import parque.pulseras.Pulsera;
import parque.pulseras.UsoPulseras;

/**
 * <p>
 * Clase que simula el comportamiento de un <b>cliente</b> del parque de atracciones.
 * </p>
 * @author DTE-SSOO 2019-20 
 */
public class Cliente
implements Runnable
{
	private UsoPulseras usoPulseras;
	private UsoAtracción[] usoAtracciones;
	private UsoAcceso usoAcceso;
	private Pulsera pulsera;
	private Random randomGenerator;
	
	/**
	 * @param uPulseras {@link parque.pulseras.UsoPulseras} interfaz de uso del sistema de gestión de pulseras.
	 * @param uAtracciones {@link parque.atracciones.UsoAtracción} interfaz de uso de cada una de las atracciones disponibles en el parque en el momento de apertura.
	 * @param uAcceso {@link parque.acceso.UsoAcceso} interfaz de uso del dispositivo de control de acceso al parque.
	 */
	public Cliente ( UsoPulseras uPulseras, UsoAtracción[] uAtracciones, UsoAcceso uAcceso )
	{
		this.usoPulseras = uPulseras;
		this.usoAtracciones = uAtracciones;
		this.usoAcceso = uAcceso;
		this.randomGenerator = new Random();
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run ()
	{
		int walkToRide;
		int nextRide;
		
		try {
			this.pulsera = this.usoPulseras.obtenerPulsera();
			
			this.usoAcceso.entrar();
			
			while(this.usoPulseras.quedanTiques(this.pulsera)) {
				walkToRide = this.randomGenerator.nextInt(2000) + 2000;
				Thread.sleep(walkToRide);
				nextRide = randomGenerator.nextInt(2);
				this.usoAtracciones[nextRide].usar(this.pulsera);
			}
			
			this.usoAcceso.salir();

		} catch (InterruptedException exception) {
			System.out.println("InterruptedException");
		}
	}
}
