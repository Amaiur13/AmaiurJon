package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import basesDeDatos.SelectData;
import excepciones.UsuarioContrasenaNoExiste;
import ficherosYbd.LecturaEscrituraFichero;
import jugadores.Jugador;
import menus.MenuAdministrador;
import menus.MenuUsuario;
import usuariosAdmins.Administrador;
import usuariosAdmins.Usuario;
import usuariosAdmins.UsuariosYadmins;

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
		setBounds(0,0, 800, 600);
		setTitle("LogIn");
		setLocationRelativeTo(null);
		
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		foto = miPantalla.getImage("./src/swing/comunio.jpeg");
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
	
	private void iniciarComponentes()
	{
		JPanel panel = new JPanel(null);
		panel.setBounds(0, 0, 778, 544);
		
		JLabel bienvenido = new JLabel("Bienvenido a comunio");
		bienvenido.setBounds(200,10,500,50); //primero location luego size (x,y,ancho,alto)
		bienvenido.setFont(new Font("Arial", Font.PLAIN, 30));
		bienvenido.setForeground(Color.GRAY);
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
            throw new UsuarioContrasenaNoExiste("Usuario o contrase�a incorrecta.");
        }
    }
	
	
}
