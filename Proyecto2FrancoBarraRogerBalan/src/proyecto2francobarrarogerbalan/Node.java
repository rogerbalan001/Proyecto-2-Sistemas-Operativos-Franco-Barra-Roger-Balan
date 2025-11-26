/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable; // Vital para Guardar/Cargar

/**
 * Clase Base para Archivos y Directorios.
 * Implementa Serializable para persistencia.
 * @author frank
 */
public abstract class Node implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    protected String name;
    protected NodeDirectory parent; // Referencia al padre (null si es root)

    public Node(String name, NodeDirectory parent) {
        this.name = name;
        this.parent = parent;
    }
    
    // --- Getters y Setters ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeDirectory getParent() {
        return parent;
    }

    public void setParent(NodeDirectory parent) {
        this.parent = parent;
    }
    
    /**
     * Construye la ruta completa del archivo/carpeta.
     * Ejemplo: /root/documentos/tarea.pdf
     */
    public String getPath() {
        if (parent == null) { // Es el directorio raíz
            return "/"; 
        }
        String parentPath = parent.getPath();
        if (parentPath.equals("/")) {
            return "/" + name;
        }
        return parent.getPath() + "/" + name;
    }
    
    /**
     * Método abstracto para obtener el tamaño.
     * - Archivos: Su tamaño en bloques.
     * - Directorios: Usualmente 0 o 1.
     */
    public abstract int getSizeInBlocks();

    /**
     * --- ¡EL CAMBIO MÁGICO! ---
     * Sobrescribimos toString para que el JTree sepa qué texto mostrar.
     * Sin esto, el árbol mostraría códigos de memoria raros.
     */
    @Override
    public String toString() {
        return name; 
    }
}