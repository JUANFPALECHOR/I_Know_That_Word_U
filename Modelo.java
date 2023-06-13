package IKnowThatWord;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es usada para manejar la lógica de negocio y el acceso a datos.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 22270963
 */
public class Modelo {
    private FileManager fileManager;
    private int mitadBanco;
    private String archivoUsuarios, linea,archivoBancoPalabras;
    private boolean verdadOFalso;
    public Modelo() {
        fileManager = new FileManager();
        archivoUsuarios = fileManager.rutaArchivoUsuario();
        archivoBancoPalabras=fileManager.rutaArchivoBanco();
    }

    /**
     * verifica si el archivo de usuarios existe en la ubicación indicada
     * y devuelve un valor booleano que indica su existencia.
     * @return (True or False) valor booleano que indica su existencia.
     */
    public boolean existeArchivoUsuarios(){
       File archivo = new File(FileManager.rutaUsuarios);
       boolean existe = archivo.exists() && archivo.isFile();
       return existe;
    }

    public void crearArchivoUsuarios(){
        fileManager.crearArchivo();
    }

    /**
     * Busca un usuario específico en el archivo de usuarios.
     * @param userName
     * @return una lista con los datos del usuario si se encuentra, o null si el usuario no existe.
     */
    public ArrayList<String> existeUsuario(String userName){
        ArrayList<String> lineas = fileManager.leerArchivo(archivoUsuarios);
        ArrayList<String> datosUsuario = new ArrayList<>();
        for (String lineaActual:lineas){
            String[] partes = lineaActual.split(","); //Suponiendo que los campos esten separador por comas
            if(partes.length >=3){ //Aseguro de que la linea tenga al menos tres campos
                String usuario = partes[0];
                if (usuario.equals(userName)){
                    datosUsuario.add(partes[0]);//username
                    datosUsuario.add(partes[1]);//password
                    datosUsuario.add(partes[2]);//nivel actual
                    return datosUsuario; //Usuario existe
                }
            }
        }
        return null; // usuario no existe
    }

    public ArrayList<String> bancoPalabras(int cantidadPalabras){
        ArrayList<String> listaBancoPalabras = fileManager.leerBancoPalabras(archivoBancoPalabras,cantidadPalabras);
        return  listaBancoPalabras;
    }

    /**
     * Toma una contraseña como entrada y devuelve su hash en forma de cadena hexadecimal.
     * @param password
     * @return hash en forma de cadena hexadecimal
     */
    public String generarHash(String password){
        try {
            //MessageDigest lo utilizamos para general el hash y mas a delante se devuelve como una cadena hexadecimal.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convierte los bytes del hash en una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void guardarDatosUsuario(String userName,String passwordHash,int nivelActual){
        linea = userName + "," + passwordHash + "," + nivelActual;
        fileManager.escribirDatosUsuarios(linea);
    }

    /**
     * Se encarga de actualizar el nivel de un jugador en el archivo de usuarios.
     * @param usuario
     * @param nivelGuardado
     */
    public void actualizarNivelJugador(String usuario,int nivelGuardado){
        ArrayList<String> lineas = fileManager.leerArchivo(archivoUsuarios);
        // Buscar la línea correspondiente al nombre de usuario y modificar el nivel
        for (int i = 0; i < lineas.size(); i++) {
            String linea = lineas.get(i);
            String[] partes = linea.split(",");
            if (partes.length == 3 && partes[0].equals(usuario)) {
                // Modificar el nivel en la línea actual
                partes[2] = String.valueOf(nivelGuardado);//actualizamos el nivel actual como string
                //combinamos los datos del usuario y encontramos la linea que se tiene que modificar.
                lineas.set(i, String.join(",", partes));
                fileManager.actualizarLineas(lineas);
                break;
            }
        }
    }
}