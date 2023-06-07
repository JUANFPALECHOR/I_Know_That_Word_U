package IKnowThatWord;


/**
 *La clase Vista es una clase que representa la interfaz gráfica de la aplicación "I Know That Word"
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 2270963
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

// Declaración de variables y componentes de la interfaz gráfica
public class Vista extends JDialog {
    private JButton botonAyuda, botonJugar, botonRepetir, botonListo, botonSi,botonNo,botonPasarNivel,botonListo2;
    private JTextField userInput;
    private Controlador controlador;
    private Escucha escucha;
    private Header headerProject;
    private String userName;
    private JPanel panel;

    private List<String> palabrasMostradas,palabrasMostradas2;

    private List<String> palabrasNoMostradas,palabrasNoMostradas2;
    private int indiceActual;

    private int indiceActual2;
    private Timer timer;


    private List<String> palabrasTotales;

    private List<String> palabrasTotales2;

    private int contador;

    private JLabel contadorLabel;

    private JPanel panelContador;

    // Constructor de la clase Vista. Inicializa los componentes de la interfaz gráfica y configura la ventana.
    public Vista() {
        palabrasMostradas = new ArrayList<>();
        palabrasNoMostradas = new ArrayList<>();

        palabrasMostradas2 = new ArrayList<>();
        palabrasNoMostradas2 = new ArrayList<>();

        indiceActual = 0;
        indiceActual2 = 0;


        palabrasTotales= new ArrayList<>();
        palabrasTotales2= new ArrayList<>();

        panelContador = new JPanel();
        contador = 0;
        initVista();
        setTitle("...I KNOW THAT WORD...");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();

    }
    // Método que inicializa los componentes de la interfaz gráfica y los agrega al contenedor.
    private void initVista() {
        setLayout(new GridBagLayout());
        GridBagConstraints restricion = new GridBagConstraints();
        escucha = new Escucha();

        botonAyuda = new JButton("?");
        botonAyuda.setBorderPainted(true);
        botonAyuda.setContentAreaFilled(false);
        botonAyuda.setForeground(Color.RED);
        botonAyuda.setPreferredSize(new Dimension(30, 30));
        botonAyuda.setFont(new Font("Arial", Font.BOLD, 20));
        botonAyuda.setMargin(new Insets(-10, -10, -10, -10));
        botonAyuda.addActionListener(escucha);
        restricion.insets = new Insets(1, 1, 100, 60);
        restricion.gridx = 0;
        restricion.gridy = 0;
        add(botonAyuda, restricion);

        headerProject = new Header("I KNOW THAT WORD", Color.WHITE);
        restricion.gridx = 1;
        restricion.gridy = 1;
        restricion.gridwidth = 2;
        add(headerProject, restricion);

        userInput = new JTextField();
        userInput.setText("Username");
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
        botonJugar.setForeground(Color.BLACK);
        botonJugar.setPreferredSize(new Dimension(90, 30));
        botonJugar.setFont(new Font("Arial", Font.BOLD, 15));
        restricion.gridx = 1;
        restricion.gridy = 3;
        restricion.gridwidth = 1;
        restricion.insets = new Insets(0, 150, 50, 50);
        add(botonJugar, restricion);


        botonRepetir = new JButton("Repetir");
        botonRepetir.addActionListener(escucha);
        botonRepetir.setBackground(Color.GREEN);
        botonRepetir.setForeground(Color.BLACK);
        botonRepetir.setPreferredSize(new Dimension(90, 30));
        botonRepetir.setFont(new Font("Arial", Font.BOLD, 15));
        botonRepetir.setVisible(false);
        restricion.gridx = 1;
        restricion.gridy = 4;
        restricion.insets = new Insets(0, 150, 10, 50);
        add(botonRepetir, restricion);


        botonListo = new JButton("Listo");
        botonListo.addActionListener(escucha);
        botonListo.setBackground(Color.ORANGE);
        botonListo.setForeground(Color.BLACK);
        botonListo.setPreferredSize(new Dimension(90, 30));
        botonListo.setFont(new Font("Arial", Font.BOLD, 15));
        botonListo.setVisible(false);
        restricion.gridx = 1;
        restricion.gridy = 5;
        restricion.insets = new Insets(0, 150, 10, 50);
        add(botonListo, restricion);

        botonListo2 = new JButton("Listo");
        botonListo2.addActionListener(escucha);
        botonListo2.setBackground(Color.ORANGE);
        botonListo2.setForeground(Color.BLACK);
        botonListo2.setPreferredSize(new Dimension(90, 30));
        botonListo2.setFont(new Font("Arial", Font.BOLD, 15));
        botonListo2.setVisible(false);
        restricion.gridx = 1;
        restricion.gridy = 5;
        restricion.insets = new Insets(0, 150, 10, 50);
        add(botonListo2, restricion);

        // Agregar botón "Sí"
        botonSi = new JButton("Sí");
        botonSi.addActionListener(escucha);
        botonSi.setPreferredSize(new Dimension(90, 30));
        botonSi.setFont(new Font("Arial", Font.BOLD, 15));
        botonSi.setVisible(false);
        restricion.gridx = 0;
        restricion.gridy = 6;
        restricion.insets = new Insets(0, 50, 10, 150);
        add(botonSi, restricion);

        // Agregar botón "No"
        botonNo = new JButton("No");
        botonNo.addActionListener(escucha);
        botonNo.setPreferredSize(new Dimension(90, 30));
        botonNo.setFont(new Font("Arial", Font.BOLD, 15));
        botonNo.setVisible(false);
        restricion.gridx = 2;
        restricion.gridy = 6;
        restricion.insets = new Insets(0, 150, 10, 50);
        add(botonNo, restricion);

        panelContador = new JPanel();
        panelContador.setVisible(false);
        contadorLabel = new JLabel("Puntuación: " + contador, SwingConstants.LEFT);
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 11));
        panelContador.add(contadorLabel);
        restricion.gridx = 0;
        restricion.gridy = 7;
        restricion.gridwidth = 3;
        add(panelContador, restricion);


        botonPasarNivel = new JButton("Pasar ");
        botonPasarNivel.addActionListener(escucha);
        botonPasarNivel.setBackground(Color.BLUE);
        botonPasarNivel.setForeground(Color.WHITE);
        botonPasarNivel.setPreferredSize(new Dimension(90, 30));
        botonPasarNivel.setFont(new Font("Arial", Font.BOLD, 15));
        botonPasarNivel.setVisible(false);
        restricion.gridx = 1;
        restricion.gridy = 7;
        restricion.insets = new Insets(10, 20, 10, 0);
        add(botonPasarNivel, restricion);




        revalidate();
        repaint();
    }
    // Método que se ejecuta al inicio del juego. Muestra las palabras en el panel y realiza algunas configuraciones.
    public void inicioDelJuego() {
        panel = new JPanel();

        panel.setLayout(new FlowLayout()); // Establecer el diseño del panel
        GridBagConstraints restricion = new GridBagConstraints();
        restricion.gridx = 0;
        restricion.gridy = 2;
        restricion.gridwidth = 3; // Establecer el ancho del panel para que ocupe toda la anchura
        restricion.fill = GridBagConstraints.HORIZONTAL;






        add(panel,restricion);
        //palabrasMostradas = PalabrasReader.mostrarPalabrasEnPanel(panel, 10);
        palabrasMostradas.addAll(PalabrasReader.mostrarPalabrasEnPanel(panel, 10));
        Collections.shuffle(palabrasMostradas);
        indiceActual = 0;


        palabrasNoMostradas = new ArrayList<>(PalabrasReader.getPalabras());
        Collections.shuffle(palabrasNoMostradas);
        palabrasNoMostradas = palabrasNoMostradas.subList(0, Math.min(10, palabrasNoMostradas.size()));
        indiceActual = 0;

        botonJugar.setVisible(false);
        botonRepetir.setVisible(true);
        botonListo.setVisible(true);




        // Imprimir PALABRAS NO MOSTRADAS EN CONSOLA
        System.out.println("palabras No mostradas");// no borrar
        System.out.println(palabrasNoMostradas);// no borrar


    }
    // Método que se ejecuta al iniciar el segundo nivel del juego. Muestra las palabras en el panel y realiza algunas configuraciones específicas del segundo nivel.
    public void inicioDelJuego2(){



        palabrasMostradas2.addAll(PalabrasReader.mostrarPalabrasEnPanel2(panel, 20));
        Collections.shuffle(palabrasMostradas2);
        indiceActual2 = 0;

        palabrasNoMostradas2 = new ArrayList<>(PalabrasReader.getPalabras2());
        Collections.shuffle(palabrasNoMostradas2);
        palabrasNoMostradas2 = palabrasNoMostradas2.subList(0, Math.min(20, palabrasNoMostradas2.size()));
        indiceActual2 = 0;

        botonRepetir.setVisible(true);
        botonListo.setVisible(true);

        System.out.println("palabras No mostradas");// no borrar
        System.out.println(palabrasNoMostradas2);

    }
    // Método que maneja los eventos de acción (por ejemplo, cuando se hace clic en un botón).
    private class Escucha implements ActionListener, FocusListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == botonAyuda) {
                controlador.mostrarImagenAyuda();
            } else if (e.getSource() == botonJugar) {
                userName = userInput.getText();
                if (userName.equals("Username")) {
                    userName = "";
                }

                if (userName.isEmpty()) {
                    userInput.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    userInput.setVisible(false);
                    headerProject.setVisible(false);
                    botonJugar.setVisible(false);
                    controlador.validacionJugador(userName);

                    inicioDelJuego();
                    revalidate();
                    repaint();
                }
            } else if (e.getSource() == botonRepetir) {
                mostrarPalabrasSecuencialmente();
            } else if (e.getSource() == botonListo) {
                panelContador.setVisible(true);
                botonPasarNivel.setVisible(true);

                palabrasTotales.addAll(palabrasMostradas);
                palabrasTotales.addAll(palabrasNoMostradas);



                PalabrasReader.mostrarPalabrasTotales(panel, palabrasTotales);

                botonSi.setVisible(true);
                botonNo.setVisible(true);

                botonRepetir.setVisible(false);
                botonListo.setVisible(false);

                revalidate();
                repaint();

                System.out.println("Palabras Mostradas y No Mostradas:");
                System.out.println(palabrasTotales);
            } else if (e.getSource() == botonSi) {
                String palabraActual = palabrasTotales.get(indiceActual);

                if (palabrasMostradas.contains(palabraActual)) {
                    contador += 10;
                    actualizarContador();
                    System.out.println("Palabra adivinada. Puntuación: " + contador);
                    System.out.println("La palabra está en palabrasMostradas");
                    botonSi.setBackground(Color.GREEN);
                } else {
                    botonSi.setBackground(Color.RED);
                    System.out.println("La palabra NO está en palabrasMostradas");
                }
            } else if (e.getSource() == botonNo) {
                String palabraActual = palabrasTotales.get(indiceActual);
                if (palabrasMostradas.contains(palabraActual)) {
                    System.out.println("La palabra está en palabrasMostradas");
                    botonNo.setBackground(Color.RED);

                } else {
                    contador += 10;
                    actualizarContador();
                    System.out.println("Palabra adivinada. Puntuación: " + contador);
                    botonNo.setBackground(Color.GREEN);
                    System.out.println("La palabra NO está en palabrasMostradas");
                }
            } else if (e.getSource() == botonPasarNivel) {


                if (contador > 100) {

                    inicioDelJuego2();
                    revalidate();
                    repaint();
                } else if (e.getSource() == botonRepetir) {
                    mostrarPalabrasSecuencialmente2();
                    panelContador.setVisible(false);
                } else if (e.getSource() == botonListo2) {
                    contador=0;
                    panelContador.setVisible(true);
                    botonPasarNivel.setVisible(false);

                    palabrasTotales2.addAll(palabrasMostradas2);
                    palabrasTotales2.addAll(palabrasNoMostradas2);

                    PalabrasReader.mostrarPalabrasTotales2(panel, palabrasTotales2);

                    botonSi.setVisible(true);
                    botonNo.setVisible(true);

                    botonRepetir.setVisible(false);
                    botonListo.setVisible(false);

                    System.out.println("Palabras Mostradas y No Mostradas:");
                    System.out.println(palabrasTotales2);

                    revalidate();
                    repaint();


                } else if (e.getSource() == botonSi) {
                    String palabraActual2 = palabrasTotales2.get(indiceActual2);

                    if (palabrasMostradas2.contains(palabraActual2)) {
                        contador += 10;
                        actualizarContador();
                        System.out.println("Palabra adivinada. Puntuación: " + contador);
                        System.out.println("La palabra está en palabrasMostradas");
                        botonSi.setBackground(Color.GREEN);
                    } else {
                        botonSi.setBackground(Color.RED);
                        System.out.println("La palabra NO está en palabrasMostradas");
                    }
                } else if (e.getSource() == botonNo) {
                    String palabraActual = palabrasTotales2.get(indiceActual2);
                    if (palabrasMostradas2.contains(palabraActual)) {
                        System.out.println("La palabra está en palabrasMostradas");
                        botonNo.setBackground(Color.RED);
                    } else {
                        botonNo.setBackground(Color.GREEN);
                        System.out.println("La palabra NO está en palabrasMostradas");
                    }
                }
            }

            indiceActual++;
            indiceActual2++;
        }
        // Método que se ejecuta cuando un componente obtiene el foco
        @Override
        public void focusGained(FocusEvent e) {
            if (userInput.getText().equals("Username")) {
                userInput.setText("");
            }
        }
        // Método que se ejecuta cuando un componente pierde el foco
        @Override
        public void focusLost(FocusEvent e) {
            if (userInput.getText().isEmpty()) {
                userInput.setText("Username");
            }
        }
    }
    // Método que actualiza el valor del contador en la interfaz gráfica.
    private void actualizarContador() {
        contadorLabel.setText("Puntuación: " + contador);
        panelContador.setVisible(true);
        panel.repaint();
    }
    // Método que muestra las palabras secuencialmente en la interfaz gráfica.
    private void mostrarPalabrasSecuencialmente() {
        if (indiceActual >= palabrasMostradas.size()) {
            indiceActual = 0;
        }

        if (timer != null) {
            timer.cancel();
            System.out.println(palabrasMostradas);


        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (indiceActual < palabrasMostradas.size()) {
                    String palabra = palabrasMostradas.get(indiceActual);

                    panel.removeAll();
                    JLabel label = new JLabel(palabra);
                    panel.add(label);
                    revalidate();
                    repaint();
                    indiceActual++;



                } else {
                    timer.cancel();
                    timer = null;
                }
            }
        }, 0, 7000);
    }

    private void mostrarPalabrasSecuencialmente2() {
        if (indiceActual2 >= palabrasMostradas2.size()) {
            indiceActual2 = 0;
        }

        if (timer != null) {
            timer.cancel();
            System.out.println(palabrasMostradas2);
            timer = null;

        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (indiceActual2 < palabrasMostradas2.size()) {
                    String palabra2 = palabrasMostradas2.get(indiceActual2);

                    panel.removeAll();
                    JLabel label = new JLabel(palabra2);
                    panel.add(label);
                    revalidate();
                    repaint();
                    indiceActual++;


                } else {
                    timer.cancel();
                    timer = null;
                }
            }
        }, 0, 7000);
    }




    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void mostrar() {
        setVisible(true);
    }
}