package IKnowThatWord;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
/**
 * La clase Controlador se encarga de coordinar las acciones entre el modelo y la vista, manejar la lógica del juego y gestionar la interacción con el usuario.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 22270963
 */
public class Controlador {
    private Modelo modelo;
    private Vista vista;
    private ArrayList<String> datosUsuario,listaBancoPalabras,listaPalabrasCorrectas,listaPalabrasInorrectas,listaPalabrasRepetir;
    private String usuario,password,mostrarPalabras,texto;
    private int nivelGuardado,cantidadPalabras,mitadBanco,contadorAciertos;
    private double porcentajePorNivel;
    private boolean boleanoExistencia,boleanoNoExistencia;

    /**
     * Constructor de lac lase controlador
     * @param modelo
     * @param vista
     */
    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        datosUsuario=new ArrayList<>();
        listaBancoPalabras=new ArrayList<>();
        listaPalabrasCorrectas=new ArrayList<>();
        listaPalabrasInorrectas=new ArrayList<>();
        listaPalabrasRepetir=new ArrayList<>();
        this.vista.setControlador(this);
    }

    /**
     * El método tablaPuntuacion() verifica si el contador de aciertos alcanza el porcentaje requerido para pasar al siguiente nivel.
     * @return un mensaje en formato HTML indicando los aciertos y el porcentaje alcanzado.
     */
    public String tablaPuntuacion(){
        if(contadorAciertos>=(cantidadPalabras*porcentajePorNivel)){//Pasa al siguiente nivel
            texto="<html>Pasaste *_*<br>Aciertos: " + contadorAciertos + "<br>Porcentaje: " + String.format("%.2f", ((double) contadorAciertos / cantidadPalabras) * 100) + "%</html>";
            nivelGuardado++;
            if (nivelGuardado>10){
                nivelGuardado=10;
            }
            modelo.actualizarNivelJugador(usuario,nivelGuardado);
            return texto;
        }else{//No pasa al siguiente nivel
            texto="<html>No pasaste °_°<br>Aciertos: " + contadorAciertos + "<br>Porcentaje: " + String.format("%.2f", ((double) contadorAciertos / cantidadPalabras) * 100) + "%</html>";
            return texto;
        }
    }

    /**
     * El método validarPalabras() se utiliza para verificar si una palabra adivinada por el usuario se encuentra en la lista de palabras correctas o incorrectas.
     * Si es así, incrementa el contador de aciertos y devuelve un código correspondiente.
     * @param validor
     * @param palabraMostrada
     * @return numero
     */
    public int validarPalabras(int validor,String palabraMostrada){
        if (validor==1){
            //si adivina que la palabra estaba en la lista de memorizar, aumenenta contadorAciertos
            for (String palabra:listaPalabrasCorrectas){
                if(palabra.equals(palabraMostrada)){
                    contadorAciertos++;
                    return 0;
                }
            }
        } else if (validor==2) {
            //si adivina que la palabra no estaba en la lista de memorizar, aumenenta contadorAciertos
            for (String palabra:listaPalabrasInorrectas){
                if(palabra.equals(palabraMostrada)){
                    contadorAciertos++;
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * El método bancoPalabrasCompletas() devuelve una palabra del banco de palabras en función de un contador de palabras dado.
     * Esto se utiliza para mostrar las palabras completas que deben memorizarse.
     * @param contadorPalabras
     * @return una palabra del banco de palabras
     */
    public String bancoPalabrasCompletas(int contadorPalabras){
        listaPalabrasRepetir.clear();
        System.out.println(listaBancoPalabras);
        mostrarPalabras="";
        if(contadorPalabras<listaBancoPalabras.size()){
            mostrarPalabras = listaBancoPalabras.get(contadorPalabras);
            contadorPalabras++;
        }
        return mostrarPalabras;
    }

    /**
     * El método guardarDatosUsuario() se utiliza para almacenar los datos del usuario en el modelo, incluyendo el nombre de usuario, la contraseña hasheada y el nivel actual.
     * @param userName
     * @param passwordHash
     * @param nivelActual
     */
    public void guardarDatosUsuario(String userName,String passwordHash,int nivelActual){
        usuario=userName;
        password=passwordHash;
        nivelGuardado=nivelActual;

        modelo.guardarDatosUsuario(userName, passwordHash, nivelActual);
    }

    /**
     * El método palabrasAMemorizar() se utiliza para obtener las palabras que el usuario debe memorizar en función de un indicador de repetición
     * y un contador de palabras. Las palabras se obtienen de las listas de palabras correctas,
     * y se agregan a la lista listaPalabrasRepetir en caso de que el usuario solicite repetirlas
     * @param repetir
     * @param contadorPalabras
     * @return las palabras que el usuario debe memorizar
     */
    public String palabrasAMemorizar(int repetir, int contadorPalabras){
        palabrasDelNivel();
        System.out.println(listaPalabrasCorrectas);
        System.out.println(listaPalabrasInorrectas);
        mostrarPalabras="";
        if(repetir==1){
            if(contadorPalabras<listaPalabrasRepetir.size()){
                mostrarPalabras = listaPalabrasRepetir.get(contadorPalabras);
                contadorPalabras++;
            }
        }else if (repetir != 1) {
            if(contadorPalabras<listaPalabrasCorrectas.size()){
                mostrarPalabras = listaPalabrasCorrectas.get(contadorPalabras);
                listaPalabrasRepetir.add(mostrarPalabras);
                contadorPalabras++;
            }
        }
        return mostrarPalabras;
    }

    /**
     * El método palabrasDelNivel() se utiliza para obtener las palabras del banco de palabras correspondientes al nivel actual.
     * Se divide el banco en dos mitades, una para palabras correctas y otra para palabras incorrectas.
     * Además, se desordena el banco utilizando Collections.shuffle().
     */
    public void palabrasDelNivel(){
        listaBancoPalabras =modelo.bancoPalabras(palabrasPorNivel());

        mitadBanco = listaBancoPalabras.size()/2;

        listaPalabrasCorrectas = new ArrayList<>(listaBancoPalabras.subList(0,mitadBanco));
        listaPalabrasInorrectas =new ArrayList<>(listaBancoPalabras.subList(mitadBanco,listaBancoPalabras.size()));

        Collections.shuffle(listaBancoPalabras);// Desordenar el ArrayList
    }

    /**
     * El método palabrasPorNivel() devuelve la cantidad de palabras y el porcentaje requerido de aciertos para el nivel actual.
     * @return la cantidad de palabras y el porcentaje requerido de aciertos para el nivel actual.
     */
    public int palabrasPorNivel(){
        switch (nivelGuardado) {
          case 1:
              porcentajePorNivel=0.7;
              return cantidadPalabras = 20;
          case 2:
              porcentajePorNivel=0.7;
              return cantidadPalabras = 40;
          case 3:
              porcentajePorNivel=0.75;
              return cantidadPalabras = 50;
          case 4:
              porcentajePorNivel=0.8;
              return cantidadPalabras = 60;
          case 5:
              porcentajePorNivel=0.8;
              return cantidadPalabras = 70;
          case 6:
              porcentajePorNivel=0.85;
              return cantidadPalabras = 80;
          case 7:
              porcentajePorNivel=0.9;
              return cantidadPalabras = 100;
          case 8:
              porcentajePorNivel=0.9;
              return cantidadPalabras = 120;
          case 9:
              porcentajePorNivel=0.95;
              return cantidadPalabras = 140;
          case 10:
              porcentajePorNivel=1;
              return cantidadPalabras = 200;
          default:
              return 0;
        }
    }

    public String generarHash(String password){
        return modelo.generarHash(password);
    }

    /**
     * El método valorDeLaLista() verifica si hay datos de usuario disponibles y establece los valores de usuario, contraseña y nivel guardado.
     * @return la cantidad de datos disponibles del arreglo.
     */
    public int valorDeLaLista(){
        if (datosUsuario != null) {
            usuario=datosUsuario.get(0);
            password=datosUsuario.get(1);
            nivelGuardado=Integer.parseInt(datosUsuario.get(2));
            return datosUsuario.size();
        } else {
            return 0;
        }
    }
    public int getNivelGuardado(){
        return nivelGuardado;
    }

    public String getNombreUsuario(){
        return usuario;
    }

    /**
     * El método coincidenciaPassword() verifica si una contraseña dada coincide con la contraseña almacenada.
     * @param passwordHash
     * @return true si la cntraseña coincide y false en el caso de que no.
     */
    public boolean coincidenciaPassword(String passwordHash){
        System.out.println(password);
        System.out.println(passwordHash);
        if (passwordHash.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * El método validacionJugador() verifica si el archivo de usuarios existe y comprueba si un usuario dado existe en él.
     * Dependiendo del resultado, se actualizan las variables boleanoExistencia y boleanoNoExistencia, y se invoca un método en la vista para mostrar los componentes correspondientes.
     * @param userName
     */
    public void validacionJugador(String userName){
        if (modelo.existeArchivoUsuarios()){
            datosUsuario =modelo.existeUsuario(userName);
            if(datosUsuario != null){
                boleanoExistencia = true;
                boleanoNoExistencia = false;
                vista.componentesPanelPassword(boleanoExistencia, boleanoNoExistencia);
                //System.out.println("Usuario existe");
            }else{
                boleanoExistencia= false;
                boleanoNoExistencia = true;
                vista.componentesPanelPassword(boleanoExistencia, boleanoNoExistencia);
                //System.out.println("Usuario no existe");
            }
        }else{
            modelo.crearArchivoUsuarios();
        }
    }

    /**
     * El método mostrarImagenAyuda() muestra una imagen de ayuda en un cuadro de diálogo emergente utilizando la clase JOptionPane.
     */
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