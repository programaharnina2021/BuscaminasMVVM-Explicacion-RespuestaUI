package presentador;

import modelo.Coordenada;
import modelo.TableroAleatorio;

public class GestionDatos {
	public TableroAleatorio tablero;

	public GestionDatos(int lado,int minas) {
		super();
		tablero=new TableroAleatorio(lado, minas);
	}
	
	public RespuestaDesvelo getRespuestaDesvelo() {
		RespuestaDesvelo respuesta=new RespuestaDesvelo(tablero.getAlto());
		for (int i = 0; i < tablero.getAlto(); i++) {
			for (int j = 0; j < tablero.getAlto(); j++) {
				Coordenada coord = new Coordenada(i, j);
				respuesta.setVeloPosicion(coord, tablero.getCasilla(coord).isVelada());
			}
		}
		return respuesta;
	}

	public void desvelarCasilla(Coordenada coordenada2) {
		tablero.desvelarContiguas(coordenada2);
		
	}
}
