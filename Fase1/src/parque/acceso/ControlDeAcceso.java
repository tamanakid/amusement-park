package parque.acceso;

/**  
 * <p>
 * Clase que implementa las interfaces de supervisión ({@link SupervisiónAcceso}) y uso ({@link UsoAcceso})del <code>dispositivo de control de acceso</code>.
 * </p>
 *
 * @author DTE-SSOO 2019-20 
 */
public class ControlDeAcceso
implements SupervisiónAcceso, UsoAcceso
{
	
	private int capacidad;
	private int aforoActual;
	private int aforoHistorico;
	
	/**
	 * @param aforo número máximo de agentes {@link parque.agentes.Cliente} que pueden estar simultáneamente dentro del parque
	 */
	public ControlDeAcceso ( int aforo )
	{
		this.capacidad = aforo;
		this.aforoActual = 0;
		this.aforoHistorico = 0;
	}

	/* (non-Javadoc)
	 * @see parque.acceso.UsoAcceso#entrar()
	 */
	@Override
	public void entrar () throws InterruptedException
	{
		synchronized(this) {
			while (this.aforoActual >= this.capacidad) {
				this.wait();				
			}
			this.aforoActual++;
		}
		System.out.println("Un cliente ha entrado en el parque: " + this.aforoActual);
	}

	/* (non-Javadoc)
	 * @see parque.acceso.UsoAcceso#salir()
	 */
	@Override
	public void salir ()
	{
		synchronized(this) {
			this.aforoActual--;
			this.aforoHistorico++;
			this.notifyAll();
		}
		System.out.println("Un cliente ha salido del parque: " + this.aforoActual);
	}

	/* (non-Javadoc)
	 * @see parque.acceso.SupervisiónAcceso#clientesDentro()
	 */
	@Override
	public int clientesDentro ()
	{
		return this.aforoActual;
	}

	/* (non-Javadoc)
	 * @see parque.acceso.SupervisiónAcceso#clientesHanSalido()
	 */
	@Override
	public int clientesHanSalido ()
	{
		return this.aforoHistorico;
	}
}
