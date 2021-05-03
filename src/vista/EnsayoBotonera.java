package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Coordenada;
import modelo.Dificultad;
import presentador.GestionDatos;
import presentador.RespuestaDesvelo;

public class EnsayoBotonera extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Botonera botonera;
	private GestionDatos gestionDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnsayoBotonera frame = new EnsayoBotonera();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EnsayoBotonera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		textField = new JTextField();
		textField.setColumns(10);
		gestionDatos=new GestionDatos(Dificultad.facil.getLongitud(), 5);
		botonera = new Botonera(Dificultad.facil.getLongitud());
		contentPane.add(botonera, BorderLayout.CENTER);
		asociarBotones();
	}

	public void pintaBotones(RespuestaDesvelo respuestaDesvelo) {
		for (int i = 0; i < this.botonera.getAlto(); i++) {
			for (int j = 0; j < this.botonera.getAncho(); j++) {
				Coordenada coordenada = new Coordenada(i, j);
				boolean veloPosicion = respuestaDesvelo.getVeloPosicion(new Coordenada(i, j));
				//Y aqui desvelas o no el boton
//				botonera.getButton(coordenada).setText(veloPosicion);
			}
		}
	}

	private void asociarBotones() {
		for (int i = 0; i < this.botonera.getAlto(); i++) {
			for (int j = 0; j < this.botonera.getAncho(); j++) {
				Coordenada coordenada = new Coordenada(i, j);
				botonera.getButton(coordenada).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton boton = (JButton) e.getSource();
						Coordenada coordenada2 = botonera.getCoordenada(boton);
						gestionDatos.desvelarCasilla(coordenada2);
//						boton.setText(String.valueOf(gestionDatos.tablero.getCasilla(coordenada2).getMinasAlrededor()));
						pintaBotones(gestionDatos.getRespuestaDesvelo());
					}
				});
				;
			}
		}
	}
}
