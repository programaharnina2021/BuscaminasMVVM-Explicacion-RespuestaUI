package modelo;

import java.util.ArrayList;

import utiles.Utiles;

public class TableroAleatorio extends Tablero {
	private boolean terminado = false;

	// Constructor aleatorio
	public TableroAleatorio(int lado, int minas) {
		super(lado);
		ArrayList<Coordenada> posiciones = generaAleatorio(minas, lado);
		disponerTablero(posiciones);
	}

	// constructor no aleatorio
	public TableroAleatorio(int lado, ArrayList<Coordenada> posiciones) {
		super(lado);
		disponerTablero(posiciones);
	}

	private void disponerTablero(ArrayList<Coordenada> posiciones) {
		colocarMinas(posiciones);
		contarMinasAlrededor(posiciones);
	}

	public void contarMinasAlrededor(ArrayList<Coordenada> posiciones) {
		// TODO
	}

	public boolean[][] getCasillasDesveladas() {
		boolean resultados[][] = new boolean[getAlto()][getAncho()];
		for (int i = 0; i < resultados.length; i++) {
			for (int j = 0; j < resultados[i].length; j++) {
				resultados[i][j] = getCasilla(new Coordenada(i, j)).isVelada();
			}
		}
		return resultados;
	}

	public void desvelarContiguas(Coordenada lugar) {
		if (getCasilla(lugar).isVelada()&&!getCasilla(lugar).isMarcada()) {
			getCasilla(lugar).setVelada(false);
			if (getCasilla(lugar).isMina()) {
				this.terminado = true;
			} else {
				if (getCasilla(lugar).getMinasAlrededor() == 0) {
					// proceso recursivo
					int alrededor = 8;
					for (int i = 0; i < alrededor; i++) {
						int[] coordenada = Utiles.damePosicionAlrededor(i);
						Coordenada lugarRelativo = new Coordenada(lugar.getPosX() + coordenada[0],
								lugar.getPosY() + coordenada[1]);
						if (lugarRelativo.isInToLimits(getAncho(),getAlto())) {
							desvelarContiguas(lugarRelativo);
						}
					}
				}
			}
		}else {
			//si alrededor tiene tantas casillas marcadas como minas alrededor
			//tiene la propia casilla
			//si el caso anterior es negativo NADA QUE HACER
			//si es positivo
			//repito el proceso de arriba
			int alrededor = 8;
			for (int i = 0; i < alrededor; i++) {
				int[] coordenada = Utiles.damePosicionAlrededor(i);
				Coordenada lugarRelativo = new Coordenada(lugar.getPosX() + coordenada[0],
						lugar.getPosY() + coordenada[1]);
				if (lugarRelativo.isInToLimits(getAncho(),getAlto())) {
					desvelarContiguas(lugarRelativo);
				}
			}
		}
		getCasilla(lugar).setMina(true);
	}

	private void colocarMinas(ArrayList<Coordenada> posiciones) {
		for (Coordenada coordenada : posiciones) {
			ponerMina(coordenada);
		}
	}

	private void ponerMina(Coordenada coordenada) {
		getCasilla(coordenada).setMina(true);
	}

	public ArrayList<Coordenada> generaAleatorio(int minas, int longitud) {
		assert minas > 0 && longitud > 0;
		assert minas < longitud * longitud;
//		long inicio = System.currentTimeMillis();
		ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
		for (int i = 0; i < minas; i++) {
			Coordenada coord;
			do {
				coord = dameCoordenada(longitud);
			} while (existeCoord(coord, coordenadas));
			coordenadas.add(coord);
		}
//		 long fin = System.currentTimeMillis();
//		 System.out.println("en milis "+(fin-inicio));
		return coordenadas;

	}

	private Coordenada dameCoordenada(int lado) {
		return new Coordenada(Utiles.dameNumero(lado), Utiles.dameNumero(lado));
	}

	private boolean existeCoord(Coordenada coord, ArrayList<Coordenada> coordenadas) {
		for (int i = 0; i < coordenadas.size(); i++) {
			if (coord.equals(coordenadas.get(i))) {
				return true;
			}
		}
		return false;
	}
}
