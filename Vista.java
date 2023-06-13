package IKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;

/**
 *Esta clase extiende un JDialog y representa la interfaz gráfica del juego.
 * Incluye varios componentes como botones, campos de texto, etiquetas y paneles.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 22270963
 */

public class Vista extends JDialog {
    private JButton botonAyuda,botonJugar,botonIniciar,botonContinuar,botonSi,botonNo, botonRepetir,botonNexNivel,botonCerrar;
    private JTextField userInput;
    private JPasswordField passwordField;
    private Controlador controlador;
    private JPanel panelPassword,panelInicioJuego;
    private Escucha escucha;
    private Header headerProject;
    private JLabel passwordLabel, incorrectPassword, usuarioExistente,usuarioNoExistente,labelPalabras,nivelLabel,nombreLabel,tiempoLabel;
    private String userName, password,passwordHash,palabras;
    private int nivelActual,partesDelJuego,contadorTiempo,nivel,repetir,contadorPalabras;
    private JDialog dialog;
    private GridBagConstraints restricion;
    private Timer tiempo;
    /**
     *Constructor de la clase vista
     */
    public Vista() {
        initVista();
        //Configuracion predeterminada de la ventana
        setTitle("...I KNOW THAT WORD...");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // La ventana se cerrará y liberará los recursos asociados cuando se solicite su cierre
        setResizable(false);//Evista que la ventana se redimencione
        setLocationRelativeTo(null);//Centra la ventana
        pack();
    }

    /**
     * Este metodo se utiliza para configurar los compoenentes.
     * como un botón de ayuda, un encabezado de proyecto, un campo de entrada de usuario y un botón de jugar.
     * También establece el diseño de la Vista utilizando GridBagLayout.
     * Los objetos de escucha escucha son utilizados para manejar los eventos de acción y enfoque en los componentes.
     * Al final, se llama a revalidate() y repaint() para actualizar la Vista.
     */
    private void initVista(){

        setLayout(new GridBagLayout());//Establece el diseño del contendor.
        //Configura el diseño del contenedor
        restricion = new GridBagConstraints();
        //Crea los objetos de escucha y control
        escucha = new Escucha();

        botonAyuda = new JButton("?");
        botonAyuda.setBorderPainted(true);//Transparente le border del boton
        botonAyuda.setContentAreaFilled(false);
        botonAyuda.setForeground(Color.RED);//Cambia el color del titulo
        botonAyuda.setPreferredSize(new Dimension(30,30));//Establece el tamaño del boton
        botonAyuda.setFont(new Font("Arial", Font.BOLD, 20)); // Cambiar el tipo y tamaño de texto del botón
        botonAyuda.setMargin(new Insets(-10, -10, -10, -10));// Quitar el margen interno del texto del botón
        botonAyuda.addActionListener(escucha);
        restricion.insets = new Insets(1, 1, 100, 60);
        restricion.gridx=0;
        restricion.gridy=0;
        add(botonAyuda,restricion);

        //Titulo del Juego
        headerProject = new Header("I KNOW THAT WORD", Color.WHITE);
        restricion.gridx=1;
        restricion.gridy=1;
        restricion.gridwidth=2;
        add(headerProject, restricion);

        userInput = new JTextField();
        userInput.setText("USERNAME");
        userInput.addFocusListener(escucha);
        userInput.setPreferredSize(new Dimension(200, 30));
        userInput.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        restricion.gridx = 1;
        restricion.gridy = 1;
        restricion.insets = new Insets(0, 100, 0, 150);
        restricion.fill = GridBagConstraints.NONE;
        restricion.anchor = GridBagConstraints.CENTER;
        add(userInput, restricion);


        botonJugar = new JButton("Jugar");
        botonJugar.addActionListener(escucha);
        botonJugar.setBackground(Color.PINK);
        botonJugar.setForeground(Color.BLACK);//Cambia el color del titulo
        botonJugar.setPreferredSize(new Dimension(90,30));//Establece el tamaño del boton
        botonJugar.setFont(new Font("Arial", Font.BOLD, 15)); // Cambiar el tipo y tamaño de texto del botón
        restricion.gridx = 1; // Establece la posición x del botón en el diseño.
        restricion.gridy = 3; // Establece la posición y del botón en el diseño.
        restricion.gridwidth = 1; // Establece el ancho del botón en el diseño.
        restricion.insets = new Insets(0, 150, 50, 50); // Establece las distancias internas del botón.
        add(botonJugar, restricion); // Agrega el botón al contenedor.
        revalidate();
        repaint();

    }

    /**
     * crea una ventana de diálogo personalizada que contiene un campo de contraseña,
     * una etiqueta de usuario existente o no existente, una etiqueta para ingresar la contraseña, una etiqueta para mostrar un mensaje de contraseña incorrecta
     * y un botón para iniciar una acción.
     * La visibilidad de las etiquetas "usuarioNoExistente" y "usuarioExistente" depende de los valores booleanos pasados como parámetros al método.
     * @param boleanoExistencia
     * @param boleanoNoExistencia
     */
    public void componentesPanelPassword(boolean boleanoExistencia, boolean boleanoNoExistencia) {
        JFrame frame = new JFrame();

        // Crear un campo de texto y personalizar su aspecto
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));

        // Crear un botón personalizado
        botonIniciar = new JButton("Iniciar");
        botonIniciar.setBackground(Color.PINK);
        botonIniciar.setForeground(Color.BLACK);
        botonIniciar.setPreferredSize(new Dimension(90,30));//Establece el tamaño del boton
        botonIniciar.setFont(new Font("Arial", Font.BOLD, 17));
        botonIniciar.addActionListener(escucha);

        // Crear el panel que contendrá los componentes con GridBagLayout
        panelPassword = new JPanel(new GridBagLayout());
        restricion.gridx = 0;
        restricion.gridy = 0;
        restricion.anchor = GridBagConstraints.CENTER;
        restricion.insets = new Insets(10, 10, 10, 10);

        // Añadir la etiqueta de usuario no existente
        usuarioNoExistente = new JLabel("Este usuario no existe");
        usuarioNoExistente.setForeground(Color.BLACK);
        usuarioNoExistente.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        usuarioNoExistente.setVisible(boleanoNoExistencia);
        restricion.gridy = 0;
        restricion.anchor = GridBagConstraints.WEST; // Alineación a la izquierda
        panelPassword.add(usuarioNoExistente, restricion);

        // Añadir la etiqueta de usuario existente
        usuarioExistente = new JLabel("Este usuario existe");
        usuarioExistente.setForeground(Color.RED);
        usuarioExistente.setFont(new Font("Arial", Font.BOLD, 12));
        usuarioExistente.setVisible(boleanoExistencia);
        restricion.gridy = 0;
        restricion.anchor = GridBagConstraints.WEST; // Alineación a la izquierda
        panelPassword.add(usuarioExistente, restricion);

        // Añadir la etiqueta de la contraseña
        passwordLabel = new JLabel("Digite Contraseña");
        passwordLabel.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        restricion.gridy = 2;
        restricion.anchor = GridBagConstraints.CENTER; // Alineación al centro
        panelPassword.add(passwordLabel, restricion);

        //Añador texto "Contraseña erronea"
        incorrectPassword = new JLabel("Contraseña errónea");
        incorrectPassword.setForeground(Color.BLUE);
        incorrectPassword.setFont(new Font(Font.DIALOG, Font.BOLD, 12));

        incorrectPassword.setVisible(false);
        restricion.gridy = 3;
        panelPassword.add(incorrectPassword, restricion);

        restricion.gridy = 4;
        panelPassword.add(passwordField, restricion);

        restricion.gridy = 5;
        panelPassword.add(botonIniciar, restricion);

        // Crear el cuadro de diálogo personalizado
        dialog = new JDialog(frame, "...PASSWORD...", true); //modal T: No interactuar con otra ventana.
        dialog.getContentPane().add(panelPassword);
        dialog.setSize(410, 250);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    /**
     * Este metodo se encarga de configurar y agregar varios componentes (etiquetas, botones) al panel panelInicioJuego,
     * que representa la interfaz de inicio del juego.
     */
    public void inicioDelJuego() {
        // Crear el panel para el inicio del juego
        panelInicioJuego = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Texto "nivel:"
        nivel= controlador.getNivelGuardado();
        nivelLabel = new JLabel("Nivel: " + nivel);
        nivelLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelInicioJuego.add(nivelLabel, gbc);

        // JText "00:00"
        tiempoLabel = new JLabel("00:00");
        tiempoLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelInicioJuego.add(tiempoLabel, gbc);

        // Texto "nombre"
        nombreLabel = new JLabel("Nombre: " + controlador.getNombreUsuario());
        nombreLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        panelInicioJuego.add(nombreLabel, gbc);

        // JLabel de texto centrado
        labelPalabras = new JLabel("¡En 5 comenzamos!");
        labelPalabras.setFont(new Font("Impact", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panelInicioJuego.add(labelPalabras, gbc);
        tiempo = new Timer(1000, escucha);

        // Botón "Continuar"
        botonContinuar = new JButton("Continuar");
        botonContinuar.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 5);
        botonContinuar.addActionListener(escucha);
        panelInicioJuego.add(botonContinuar, gbc);

        // Crear el botón "Repetir"
        botonRepetir = new JButton("Repetir");
        botonRepetir.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 5);
        botonRepetir.addActionListener(escucha);
        panelInicioJuego.add(botonRepetir, gbc);

        // Botón "Continuar Siguiente Nivel"
        botonNexNivel = new JButton("Continuar");
        botonNexNivel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 5);
        botonNexNivel.addActionListener(escucha);
        panelInicioJuego.add(botonNexNivel, gbc);

        // Botón "Cerrar"
        botonCerrar = new JButton("Salir");
        botonCerrar.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 10, 10);
        botonCerrar.addActionListener(escucha);
        panelInicioJuego.add(botonCerrar, gbc);

        // Botón "Si"
        botonSi = new JButton("Si");
        botonSi.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 5);
        botonSi.addActionListener(escucha);
        panelInicioJuego.add(botonSi, gbc);

        // Botón "No"
        botonNo = new JButton("No");
        botonNo.setVisible(false);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 5, 10, 5);
        botonNo.addActionListener(escucha);
        panelInicioJuego.add(botonNo, gbc);

        // Agregar el panel al contenedor principal
        restricion.gridx = 0;
        restricion.gridy = 6;
        restricion.gridwidth = 3;
        add(panelInicioJuego, restricion);

        // Actualizar la interfaz gráfica
        revalidate();
        repaint();
    }

    /**
     * Se define una clase interna llamada Escucha que implementa las interfaces ActionListener y FocusListener.
     * Estas interfaces se utilizan para manejar eventos de acción (como hacer clic en un botón) y eventos de enfoque (cuando un componente gana o pierde el enfoque).
     */
    private class Escucha implements ActionListener,FocusListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonAyuda){
                controlador.mostrarImagenAyuda();
            }
            else if (e.getSource()==botonJugar){
                userName = userInput.getText();
                if (userName.equals("USERNAME")) {
                    // La caja de texto contiene el marcador de posición, asignar una cadena vacía
                    userName = "";
                }
                if (userName.isEmpty()) {
                    // Si la caja de texto está vacía, se coloca un borde rojo
                    userInput.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    controlador.validacionJugador(userName);
                    revalidate();
                    repaint();
                }
            } else if (e.getSource()==botonIniciar){
                char[] passwordChars = passwordField.getPassword();
                password = new String(passwordChars);

                if (password.isEmpty()) {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.RED));
                }else if (controlador.valorDeLaLista() == 0) {
                    passwordHash= controlador.generarHash(password);
                    nivelActual=1;
                    controlador.guardarDatosUsuario(userName,passwordHash,nivelActual);
                    userInput.setVisible(false); // Ocultar la caja de texto
                    headerProject.setVisible(false); // Ocultar el header
                    botonJugar.setVisible(false); // Ocultar el botón de jugar
                    dialog.setVisible(false);
                    revalidate();
                    repaint();
                    inicioDelJuego();
                    contadorPalabras=0;
                    partesDelJuego=1;
                    contadorTiempo=0;
                    tiempo.start();
                }else{
                    passwordHash= controlador.generarHash(password);
                    if (controlador.coincidenciaPassword(passwordHash)){
                        System.out.println("Ingreso");
                        userInput.setVisible(false); // Ocultar la caja de texto
                        headerProject.setVisible(false); // Ocultar el header
                        botonJugar.setVisible(false); // Ocultar el botón de jugar
                        dialog.setVisible(false);
                        revalidate();
                        repaint();
                        inicioDelJuego();
                        contadorPalabras=0;
                        partesDelJuego=1;
                        contadorTiempo=0;
                        tiempo.start();
                    }else{
                        incorrectPassword.setVisible(true);
                        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    }
                }
            }else if (e.getSource()==tiempo){
                tiempoLabel.setText("00:0" + contadorTiempo);
                contadorTiempo++;
                if (partesDelJuego==1){
                    if (contadorTiempo > 5){ //Tiempo en 5sg
                        palabras=controlador.palabrasAMemorizar(repetir,contadorPalabras);
                        contadorPalabras++;
                        if(palabras==""){
                            System.out.println("detener tiempo");
                            tiempo.stop();
                            tiempoLabel.setText("00:00");
                            if(repetir==1){
                                botonContinuar.setVisible(true);
                            }else{
                                botonContinuar.setVisible(true);
                                botonRepetir.setVisible(true);
                            }
                        }
                        labelPalabras.setText(palabras);
                        contadorTiempo=1;
                        System.out.println(palabras);
                    }
                } else if (partesDelJuego==2) {
                    if (contadorTiempo > 7){ //Tiempo en 7sg
                        botonSi.setVisible(true);
                        botonNo.setVisible(true);
                        botonSi.setBackground(Color.white);
                        botonNo.setBackground(Color.white);
                        botonSi.setEnabled(true);// habilitar el botón
                        botonNo.setEnabled(true);// habilitar el botón
                        palabras=controlador.bancoPalabrasCompletas(contadorPalabras);
                        contadorPalabras++;
                        if(palabras==""){
                            System.out.println("detener tiempo");
                            tiempo.stop();
                            revalidate();
                            repaint();
                            tiempoLabel.setVisible(false);
                            botonSi.setVisible(false);
                            botonNo.setVisible(false);
                            botonCerrar.setVisible(true);
                            botonNexNivel.setVisible(true);
                            nivel= controlador.getNivelGuardado();
                            System.out.println(controlador.getNivelGuardado());
                            palabras=controlador.tablaPuntuacion();
                            labelPalabras.setText(palabras);
                            nivelLabel.setText("Nivel: " + controlador.getNivelGuardado());
                        }
                        labelPalabras.setText(palabras);
                        contadorTiempo=1;
                        System.out.println(palabras);
                    }
                }
            } else if (e.getSource()==botonRepetir) {
                System.out.println("Repetir");
                remove(panelInicioJuego);// Eliminar el panel actual
                inicioDelJuego();
                partesDelJuego=1;
                contadorTiempo=0;
                contadorPalabras=0;
                repetir=1;
                tiempo.start();
                // Actualizar la interfaz gráfica
                revalidate();
                repaint();
            } else if (e.getSource()==botonContinuar) {
                remove(panelInicioJuego);// Eliminar el panel actual
                inicioDelJuego();
                labelPalabras.setText("¡En 7 comenzamos!");
                partesDelJuego=2;
                contadorTiempo=0;
                contadorPalabras=0;
                tiempo.start();
                // Actualizar la interfaz gráfica
                revalidate();
                repaint();
            } else if (e.getSource()==botonSi) {
                botonNo.setVisible(false);
                botonSi.setEnabled(false);// Deshabilitar el botón
                if(controlador.validarPalabras(1,palabras)==0){//palabra correcta
                    botonSi.setBackground(Color.green);
                }else {//palabra incorrecta
                    botonSi.setBackground(Color.red);
                    revalidate();
                    repaint();
                }
            } else if (e.getSource()==botonNo) {
                botonSi.setVisible(false);
                botonNo.setEnabled(false);// Deshabilitar el botón
                if(controlador.validarPalabras(2,palabras)==0){//palabra correcta
                    botonNo.setBackground(Color.green);
                }else {//palabra incorrecta
                    botonNo.setBackground(Color.red);
                    revalidate();
                    repaint();
                }
            } else if (e.getSource()==botonCerrar) {
                // Finalizar el proceso y cerrar la ventana
                System.exit(0);
            } else if (e.getSource()==botonNexNivel) {
                remove(panelInicioJuego);
                revalidate();
                repaint();
                inicioDelJuego();
                contadorPalabras=0;
                partesDelJuego=1;
                contadorTiempo=0;
                tiempo.start();
            }
        }

        @Override
        public void focusGained(FocusEvent e) {
            // Se borra el texto del marcador de posición cuando se gana el enfoque
            if (userInput.getText().equals("USERNAME")) {
                userInput.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            // Se restaura el texto del marcador de posición si se pierde el enfoque sin haber ingresado texto
            if (userInput.getText().isEmpty()) {
                userInput.setText("USERNAME");
            }
        }
    }
    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }
    public void mostrar() {
        setVisible(true);
    }
}