/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
/**
 *
 * @author frank
 */
    public class GestorPersistencia {

    public static boolean guardarEstado(FileManager fileManager, String filePath) {

        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(fileManager); 
            System.out.println("Estado del simulador guardado en: " + filePath);
            return true;

        } catch (IOException e) {
            System.err.println("Error al guardar el estado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static FileManager cargarEstado(String filePath) {

        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            FileManager fileManager = (FileManager) ois.readObject(); 
            System.out.println("Estado del simulador cargado desde: " + filePath);
            return fileManager;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el estado: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
