package IKnowThatWord;

/**
 * Esta clase es usada como punto de entrada para el programa.
 * Donde se crea una instancia del modelo, la vista y el controlador, y se establece la conexión entre ellos.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 22270963
 */

public class Main {

    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
        vista.mostrar();
    }
}
