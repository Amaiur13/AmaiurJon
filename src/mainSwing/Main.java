package mainSwing;

import basesDeDatos.SelectData;
import excepciones.UsuarioContrasenaNoExiste;
import ficheros.LecturaEscrituraFichero;
import jugadoresPujaAlineacion.Jugador;
import menus.MenuAdministrador;
import menus.MenuUsuario;
import usuariosAdmins.Administrador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class Main extends JFrame {

	private JPanel contentPane;
	private JLabel labelFondo;
	private Image foto;
	private ArrayList <UsuariosYadmins> arrayUsuarios;
	private ArrayList <Jugador> arrayJugadores;
	private ArrayList<Jugador> arrayMarket;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() 
	{
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0, 500, 300);
		setTitle("LogIn");
		setLocationRelativeTo(null);
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		foto = miPantalla.getImage("./src/mainSwing/comunio.jpeg");
		setIconImage(foto);


		arrayUsuarios = SelectData.selectAllUsers();
		arrayJugadores = SelectData.selectAllPlayers(arrayUsuarios);
		arrayMarket = LecturaEscrituraFichero.leerFicheroMercado(arrayJugadores);
		iniciarComponentes();
		
		
		
		
		
		//labelFondo = new JLabel("");
		//ImageIcon icon = new ImageIcon("./src/swing/comunioLogIn.jpeg");
		//labelFondo.setIcon(icon);
		//labelFondo.setBounds(90, 0, 873, 228);
		//contentPane.add(labelFondo);
		
	}

	/**
	 * Inicia compenente de la ventana
	 */
	private void iniciarComponentes()
	{
		JPanel panel = new JPanel(null);
		panel.setBounds(0, 0, 778, 544);
		
		JLabel bienvenido = new JLabel("Bienvenido a comunio");
		bienvenido.setBounds(70,10,500,50); //primero location luego size (x,y,ancho,alto)
		bienvenido.setFont(new Font("Arial", Font.BOLD, 30));
		bienvenido.setForeground(Color.GREEN);
		panel.add(bienvenido);
		
		JTextField txtUser = new JTextField("User");
		txtUser.setBounds(75, 75, 200,25);
		panel.add(txtUser);
		
		txtUser.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent evt)
			{
				if (txtUser.getText().equals("User"))
				{
					txtUser.setText("");
				}
			}
		});
		
		
		JTextField txtPass = new JTextField("Password");
		txtPass.setBounds(75, 100, 200,25);
		panel.add(txtPass);
		
		txtPass.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent evt)
			{
				if (txtPass.getText().equals("Password"))
				{
					txtPass.setText("");
				}
			}
		});
		
		
		JButton btnLogin = new JButton("Sign in");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(120, 130, 100, 35);
		btnLogin.setBackground(Color.black);
		panel.add(btnLogin);
		
		btnLogin.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String user = txtUser.getText();
				String password = txtPass.getText();
				
				try 
				{
					UsuariosYadmins userOadmin = comprobarLogIn(user,  password, arrayUsuarios);
					
					if (userOadmin instanceof Administrador)
			        { 
						
			            System.out.println("Bienvenido, " + user + "\n");
			            Main.this.dispose();
			            MenuAdministrador.menuAdministrador(arrayUsuarios, arrayJugadores, arrayMarket);
			            
			        }

			        else if (userOadmin instanceof Usuario)
			        {
			           System.out.println("Bienvenido, " + user + "\n");
			           Main.this.dispose();
			           MenuUsuario.menuUsuario(arrayUsuarios, arrayJugadores, userOadmin);        
			        }
				} 
				
				catch (UsuarioContrasenaNoExiste e1) 
				{
					JOptionPane.showMessageDialog(Main.this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		/**labelFondo = new JLabel("");
		ImageIcon icon = new ImageIcon("./src/swing/comunioLogIn.jpeg");
		labelFondo.setIcon(icon);
		labelFondo.setBounds(0, 0, 900, 800);
		panel.add(labelFondo);*/
		
		this.getContentPane().add(panel);
		panel.setVisible(true);
		
	}

	/**
	 * Comprueba si los datos introducidos del LogIn son correctos
	 * @param usuario usuario introducido
	 * @param contra contraseña introducida
	 * @param arrayUsuarios arraylist con usuarios de la bd
	 * @return devuelve el usuario o administrador en cuestion si los datos son correctos y sino null
	 * @throws UsuarioContrasenaNoExiste para el mensaje de error
	 */
	 private UsuariosYadmins comprobarLogIn (String usuario, String contra, ArrayList <UsuariosYadmins> arrayUsuarios) throws UsuarioContrasenaNoExiste
     {
        int aux = 0;
        UsuariosYadmins admin = new Administrador();
        UsuariosYadmins user = new Usuario();

        for (int i = 0; i < arrayUsuarios.size(); i++)
        {
            if (arrayUsuarios.get(i) instanceof Administrador)
            {
                admin = arrayUsuarios.get(i);

                if (usuario.equals(arrayUsuarios.get(i).getUser()))
                {
                    if (contra.equals(arrayUsuarios.get(i).getPassword()))
                    {
                        aux = 2;
                        break;
                    }
                }
            }

            else if (arrayUsuarios.get(i) instanceof Usuario)
            {
                user = arrayUsuarios.get(i);

                if (usuario.equals(arrayUsuarios.get(i).getUser()))
                {
                    if (contra.equals(arrayUsuarios.get(i).getPassword()))
                    {
                        aux = 3;
                        break;
                    }
                }

            }
        }
        if (aux == 2)
        {
            return admin;
        }

        else if (aux == 3)
        {
            return user;
        }

        else
        {
            throw new UsuarioContrasenaNoExiste("Usuario o contrasena incorrecta.");
        }
    }
	
	
}
