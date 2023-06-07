package IKnowThatWord;

/**
 *esta clase parece estar diseñada para mostrar palabras en un panel de forma secuencial y controlada por un temporizador.
 *Utiliza un archivo de texto como fuente de palabras y proporciona métodos para mostrar diferentes conjuntos de palabras en un panel.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 2270963
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PalabrasReader {
    private static final int DURACION_PALABRA = 7000; // Duración en milisegundos (1 segundo)

    private static List<String> palabras,palabras2;
    private static int indiceActual,indiceActual2;
    private static Timer timer;



    //Este método muestra una cantidad específica de palabras en un panel
    public static List<String> mostrarPalabrasEnPanel(JPanel panel, int cantidadPalabras) {
        palabras = leerPalabrasDesdeArchivo("C:\\Users\\CASA\\IdeaProjects\\I_know_that_word\\src\\IKnowThatWord\\recursos\\palabras.txt");

        List<String> palabrasMostradas = new ArrayList<>();


        if (palabras.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "No se encontraron palabras en el archivo.");
            return palabrasMostradas;
        }

        panel.setLayout(new GridLayout(1, 1));
        panel.removeAll();

        palabrasMostradas = palabras.subList(0, Math.min(cantidadPalabras, palabras.size()));
        mostrarPalabras(panel, palabrasMostradas);
        System.out.println("" + palabras);// NO BORRAR
        System.out.println("" + palabrasMostradas);// NO BORRAR



        return palabrasMostradas;


    }


    //Este método muestra todas las palabras en un panel.

    public static void mostrarPalabrasTotales(JPanel panel, List<String> palabrasTotales) {
        indiceActual = 0;

        mostrarSiguientePalabraTotales(panel, palabrasTotales);

        timer = new Timer(DURACION_PALABRA, e -> {
            ocultarPalabra(panel);
            if (indiceActual < palabrasTotales.size()) {
                mostrarSiguientePalabraTotales(panel, palabrasTotales);
                indiceActual++;
            } else {
                timer.stop();

            }
        });
        timer.start();



    }

    //Este método lee las palabras desde un archivo
    private static void mostrarPalabras(JPanel panel, List<String> palabrasMostradas) {
        indiceActual = 0;
        mostrarSiguientePalabra(panel, palabrasMostradas);



        timer = new Timer(DURACION_PALABRA, e -> {
            ocultarPalabra(panel);
            if (indiceActual < palabrasMostradas.size()) {
                mostrarSiguientePalabra(panel, palabrasMostradas);
                indiceActual++;
            } else {
                timer.stop();
            }
        });
        timer.start();
    }




    //Estos métodos muestran la siguiente palabra en el panel.
    private static void mostrarSiguientePalabra(JPanel panel, List<String> palabrasMostradas) {
        if (indiceActual >= palabrasMostradas.size()) {
            return;
        }


        JLabel label = new JLabel(palabrasMostradas.get(indiceActual), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(label);
        panel.revalidate();
        panel.repaint();


    }

    private static void mostrarSiguientePalabraTotales(JPanel panel, List<String> palabrasTotales) {


        if (indiceActual >= palabrasTotales.size()) {
            timer.stop();
            return;
        }



        JLabel label = new JLabel(palabrasTotales.get(indiceActual), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(label);
        panel.revalidate();
        panel.repaint();




    }
    //Estos métodos ocultan la palabra actualmente mostrada en el panel
    private static void ocultarPalabra(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }


    private static List<String> leerPalabrasDesdeArchivo(String rutaArchivo) {
        List<String> palabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(palabras);
        return palabras;
    }

    //Estos métodos devuelven las listas de palabras almacenadas en la clase.
    public static List<String> getPalabras() {
        return palabras;
    }

    ////////////////////////NIVEL 2
    public static List<String> mostrarPalabrasEnPanel2(JPanel panel, int cantidadPalabras2) {
        palabras2 = leerPalabrasDesdeArchivo2("C:\\Users\\CASA\\IdeaProjects\\I_know_that_word\\src\\IKnowThatWord\\recursos\\palabras.txt");

        List<String> palabrasMostradas2 = new ArrayList<>();


        if (palabras2.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "No se encontraron palabras en el archivo.");
            return palabrasMostradas2;
        }

        panel.setLayout(new GridLayout(1, 1));
        panel.removeAll();

        palabrasMostradas2 = palabras2.subList(0, Math.min(cantidadPalabras2, palabras2.size()));
        mostrarPalabras2(panel, palabrasMostradas2);
        System.out.println("" + palabras2);// NO BORRAR
        System.out.println("" + palabrasMostradas2);// NO BORRAR



        return palabrasMostradas2;


    }



    private static void mostrarSiguientePalabra2(JPanel panel, List<String> palabrasMostradas2) {
        if (indiceActual2 >= palabrasMostradas2.size()) {
            return;
        }


        JLabel label = new JLabel(palabrasMostradas2.get(indiceActual2), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panel.add(label);
        panel.revalidate();
        panel.repaint();


    }


    private static void mostrarPalabras2(JPanel panel, List<String> palabrasMostradas2) {
        indiceActual2 = 0;
        mostrarSiguientePalabra2(panel, palabrasMostradas2);



        timer = new Timer(DURACION_PALABRA, e -> {
            ocultarPalabra2(panel);
            if (indiceActual2 < palabrasMostradas2.size()) {
                mostrarSiguientePalabra2(panel, palabrasMostradas2);
                indiceActual2++;
            } else {
                timer.stop();
            }
        });
        timer.start();
    }

    public static void mostrarPalabrasTotales2(JPanel panel, List<String> palabrasTotales2) {
        indiceActual2 = 0;

        mostrarSiguientePalabraTotales2(panel, palabrasTotales2);
        ocultarPalabra2(panel);
        timer = new Timer(DURACION_PALABRA, e -> {

            if (indiceActual2 < palabrasTotales2.size()) {
                mostrarSiguientePalabraTotales2(panel, palabrasTotales2);
                indiceActual2++;
            } else {
                timer.stop();

            }
        });
        timer.start();
        mostrarSiguientePalabraTotales2(panel, palabrasTotales2);
        timer.start();



    }


    private static void mostrarSiguientePalabraTotales2(JPanel panel, List<String> palabrasTotales2) {


        if (indiceActual2 >= palabrasTotales2.size()) {
            timer.stop();
            return;
        }



        JLabel label = new JLabel(palabrasTotales2.get(indiceActual2), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(label);
        panel.revalidate();
        panel.repaint();




    }
    private static void ocultarPalabra2(JPanel panel) {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }


    private static List<String> leerPalabrasDesdeArchivo2(String rutaArchivo) {
        List<String> palabras2 = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                palabras2.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(palabras2);
        return palabras2;
    }


    public static List<String> getPalabras2() {
        return palabras2;
    }



}