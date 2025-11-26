    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import javax.swing.UIManager;

public class Proyecto2FrancoBarraRogerBalan {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo cargar el LookAndFeel del sistema, usando default.");
        }

        FileManager fileManager = new FileManager();

        java.awt.EventQueue.invokeLater(() -> {
            
            GUI gui = new GUI(fileManager);
            gui.setVisible(true);
            
            HiloSimulador kernel = new HiloSimulador(fileManager, gui);
            kernel.start();
            
            System.out.println("Sistema Operativo Simulado iniciado correctamente.");
        });
    }
}