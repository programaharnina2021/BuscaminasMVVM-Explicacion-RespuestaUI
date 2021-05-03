package presentador;

import modelo.Densidad;
import modelo.Dificultad;
import modelo.Tablero;

public class Presentador {
 
	public Tablero tablero;

	public Presentador(Dificultad dificultad,Densidad densidad) {
		super();
		tablero=new Tablero(dificultad.getLongitud(),densidad.getPorcentaje());
	}
	
}
