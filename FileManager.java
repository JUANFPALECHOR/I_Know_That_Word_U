package IKnowThatWord;

/**
 *Esta clase se utiliza para manejar operaciones de lectura y escritura de archivos.
 * @author Jhon Frank Vasquez: jhon.frank.vasquez@correounivalle.edu.co - 2226510
 * @author Juan Felipe: juan.palechor@correounivalle.edu.co - 22270963
 */
import java.io.*;
import java.util.ArrayList;
public class FileManager {
    public static final String bancoPalabras = "src/IKnowThatWord/files/bancoPalabras.txt";

    public static final String rutaUsuarios = "src/IKnowThatWord/files/Usuarios.txt";

    public String rutaArchivoUsuario() {
        return rutaUsuarios;
    }

    public String rutaArchivoBanco(){
        return bancoPalabras;
    }

    /**
     * Crea un nuevo archivo en la ruta especificada.
     * Y muestra un mensaje indicando si la operación fue exitosa o si se produjo un error.
     */
    public void crearArchivo() {
        try {
            File archivo = new File(rutaUsuarios);
            archivo.createNewFile();
            System.out.println("Archivo creado exitosamente en la ruta: " + rutaUsuarios);
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * lee un archivo de texto línea por línea y devuelve un ArrayList.
     * Que contiene todas las líneas del archivo.
     * @param nombreArchivo
     * @return ArrayList<String> lineas
     */
    public ArrayList<String> leerArchivo(String nombreArchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }

    /**
     * El método leerBancoPalabras lee un archivo de banco de palabras y devuelve un ArrayList que contiene un número específico de líneas del archivo,
     * limitado por el parámetro cantidadPalabras.
     * @param nombreArchivo
     * @param cantidadPalabras
     * @return ArrayList que contiene un número específico de líneas del archivo
     */
    public ArrayList<String> leerBancoPalabras(String nombreArchivo,int cantidadPalabras) {
        ArrayList<String> bancoPalabras = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null && contador < cantidadPalabras) {
                bancoPalabras.add(linea);
                contador++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bancoPalabras;
    }

    /**
     * El método escribirDatosUsuarios permite agregar una línea de datos de usuarios al final de un archivo de texto
     * @param linea
     */
    public void escribirDatosUsuarios(String linea) {
        try {
            FileWriter fileWriter = new FileWriter(rutaUsuarios,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(linea);
            bufferedWriter.newLine();

            bufferedWriter.close();

            System.out.println("Datos de usuarios guardados correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos de usuarios en el archivo: " + e.getMessage());
        }
    }

    /**
     * El método actualizarLineas permite actualizar el contenido de un archivo de texto con un conjunto de líneas proporcionadas,
     * reemplazando el contenido existente en el archivo.
     * @param linea
     */
    public void actualizarLineas(ArrayList<String> linea) {
        try {
            FileWriter fileWriter = new FileWriter(rutaUsuarios);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String espacioEnTxt : linea) {
                bufferedWriter.write(espacioEnTxt);
                bufferedWriter.newLine();
            }
            System.out.println("Nivel actualizado con éxito.");
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error al actualizar el nivel: " + e.getMessage());
        }
    }

}
