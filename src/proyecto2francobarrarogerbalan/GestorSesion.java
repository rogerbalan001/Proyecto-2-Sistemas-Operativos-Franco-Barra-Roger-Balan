/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2francobarrarogerbalan;

import java.io.Serializable;

public class GestorSesion implements Serializable { 
    
    private static final long serialVersionUID = 1L;
    
    private ModoUsuario modoActual;

    public GestorSesion() {
        this.modoActual = ModoUsuario.USUARIO;
    }

    public void setModoActual(ModoUsuario nuevoModo) {
        this.modoActual = nuevoModo;
        System.out.println("Modo cambiado a: " + nuevoModo);
    }

    public ModoUsuario getModoActual() {
        return modoActual;
    }

    public boolean isAdmin() {
        return this.modoActual == ModoUsuario.ADMINISTRADOR;
    }
}
