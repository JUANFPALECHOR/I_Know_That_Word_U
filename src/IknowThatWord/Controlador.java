package IKnowThatWord;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;

/**
 * La clase Controlador en el código proporcionado se utiliza como parte del patrón de diseño Modelo-Vista-Controlador
 * (MVC) para separar la lógica de control de la aplicación de la representación visual y la manipulación de datos.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 2270963
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void validacionJugador(String userName){

    }
    public void mostrarImagenAyuda() {
        ImageIcon ayudaImagen = new ImageIcon(getClass().getResource("/IKnowThatWord/recursos/imagenAyuda.jpg"));

        // Obtener la imagen redimensionada con el tamaño deseado
        Image imagenRedimensionada = ayudaImagen.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon con la imagen redimensionada
        ImageIcon imagenFinal = new ImageIcon(imagenRedimensionada);

        // Mostrar la imagen en un cuadro de diálogo emergente
        JOptionPane.showMessageDialog(vista, imagenFinal, "Ayuda", JOptionPane.PLAIN_MESSAGE);
    }
}