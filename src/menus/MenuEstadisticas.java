package menus;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import LP.Utilidades;
import jugadoresPujaAlineacion.Jugador;
import ordenacion.Quicksort;
import usuariosAdmins.UsuariosYadmins;

public class MenuEstadisticas 
{
	public static void menuEstadisticas (ArrayList<UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, UsuariosYadmins usuario)
	{
		 System.out.println("Has seleccionado la opcion 'Estadisticas'. ¿Que desea hacer?\n" +
	                "1.- Filtrar jugadores por puntos\n" +
	                "2.- Filtrar jugadores por equipo\n" +
	                "3.- Jugadores ordenados por valor\n" +
	                "4.- Volver al menu principal\n");

	        System.out.println("Seleccione la opcion deseada:");
	        int b = Utilidades.leerEntero();
	        switch (b)
	        {
	            case 1: filtrarPorPuntos(arrayUsuarios, arrayJugadores, usuario);
	                break;
	            case 2: filtrarPorEquipo(arrayUsuarios, arrayJugadores, usuario);
	                break;
	            case 3: filtrarPorValor(arrayUsuarios, arrayJugadores, usuario);
	                break;
	            case 4: MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, usuario);
	            	
	            default: System.out.println("Seleccione una opcion entre 1 y 4 por favor");
	        }
	}
	
	public static void filtrarPorPuntos (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, UsuariosYadmins usuario)
	{
		JFrame framePuntos = new JFrame();
		framePuntos.setVisible(true);
		
		JPanel panel = new JPanel(new FlowLayout());
		//panel.setBounds(400, 250, 400, 300);
		framePuntos.add(panel);
		panel.setVisible(true);
		
		framePuntos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePuntos.setBounds(400, 250, 400, 300);
		framePuntos.setTitle("Estadisticas por puntos");
		
		JLabel label1 = new JLabel("Jugadores con MENOS de ");
		panel.add(label1);
	
		JSpinner control = new JSpinner(new SpinnerNumberModel(50, 1, 500, 10));
		Dimension d = new Dimension(50, 20);
		control.setPreferredSize(d);
		panel.add(control);
		
		JLabel strPuntos = new JLabel(" puntos  ");
		panel.add(strPuntos);
		
		JButton buscar = new JButton("Buscar (-)");
		
		buscar.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int valor = (int) control.getValue();
				arrayJugadores.stream().filter(w -> w.getPuntosTotales() < valor)
									   .sorted(Comparator.comparingInt(Jugador::getPuntosTotales).reversed())
						               .map(a -> a.getNombre() + "   Puntos jornada anterior: " + a.getPoints() + "   Puntos totales: " + a.getPuntosTotales())
									   .forEach(System.out::println);
	
				framePuntos.dispose();
				System.out.println("\nPulsa cualquier boton para volver al menu de estadisticas: ");
				String a = Utilidades.leerTexto();
				MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
			}
		});
		
		panel.add(buscar);
		
		JLabel label2 = new JLabel("Jugadores con MAS de ");
		panel.add(label2);
		
		JSpinner control1 = new JSpinner(new SpinnerNumberModel(20, 0, 500, 5));
		control1.setPreferredSize(d);
		panel.add(control1);
		
		JLabel strPuntoss = new JLabel(" puntos  ");
		panel.add(strPuntoss);
		
		JButton buscar1 = new JButton("Buscar (+)");
		
		buscar1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int valor = (int) control1.getValue();
				arrayJugadores.stream().filter(w -> w.getPuntosTotales() > valor)
								       .sorted(Comparator.comparingInt(Jugador::getPuntosTotales))
						               .map(a -> a.getNombre() + "   Puntos jornada anterior: " + a.getPoints() + "   Puntos totales: " + a.getPuntosTotales())
									   .forEach(System.out::println);
				
				framePuntos.dispose();
				System.out.println("\nPulsa cualquier boton para volver al menu de estadisticas: ");
				String a = Utilidades.leerTexto();
				MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
			}
		});
		
		panel.add(buscar1);
	}

	
	public static void filtrarPorEquipo (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, UsuariosYadmins usuario)
	{
		System.out.println("Introduce el nombre del equipo cuyos jugadores quieras ver: ");
		String ekipo = Utilidades.leerTexto();
		
		List <Jugador> strTeams = arrayJugadores.stream().filter(a -> a.getTeam().equals(ekipo))
														 .collect(Collectors.toList());
		
		if (strTeams.isEmpty())
		{
			System.out.println("No hay jugadores del equipo indicado.\n");
			MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
		}
		
		else
		{
			strTeams.forEach(a -> System.out.println(a));
			//strTeams.forEach(System.out::println);
			
			System.out.println("\nPulsa cualquier boton para volver al menu de estadisticas: ");
			String a = Utilidades.leerTexto();
			MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
		}
	}
	
	public static void filtrarPorValor (ArrayList <UsuariosYadmins> arrayUsuarios, ArrayList<Jugador> arrayJugadores, UsuariosYadmins usuario)
	{
		Quicksort.quicksort(arrayJugadores, 0, arrayJugadores.size() - 1);
		arrayJugadores.stream().map(a -> a.getNombre() + "  VALOR: " + a.getValor()).forEach(b -> System.out.println(b));
		
		System.out.println("\nPulsa cualquier boton para volver al menu de estadisticas: ");
		String a = Utilidades.leerTexto();
		MenuEstadisticas.menuEstadisticas(arrayUsuarios, arrayJugadores, usuario);
	}
}
