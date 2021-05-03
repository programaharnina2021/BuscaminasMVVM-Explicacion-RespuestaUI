package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Densidad;
import modelo.Dificultad;
import presentador.GestionDatos;

public class ParaUI extends UI {
	private GestionDatos gestionDatos;
	
	public ParaUI() {
		super();
		gestionDatos=new GestionDatos(Dificultad.facil.getLongitud(), 5);
		// leyes de demeter
		// para solucionar esto es crear metodos delegados
//		jPanelOpciones.btnIniciar.addActionListener(l);
		getBtnIniciar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println((Densidad) getCmbDensidad().getSelectedItem());
				Dificultad selectedItem = (Dificultad) getCmbDificultad().getSelectedItem();
				System.out.println(selectedItem.getLongitud());
				
			}
		});		
	}

}
