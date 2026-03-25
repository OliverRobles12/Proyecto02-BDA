/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utilerias;

import java.awt.Cursor;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author joset
 */
public class utilerias {
    public static void estilizarBotonMenu(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    public static void colocarLogo(JButton btn){
        btn.setText(" ");
        escalarImagen(btn, "src/main/java/com/mycompany/recursos/logo3.jpg", 110, 110);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
    }
    public static void escalarImagen(JButton btn, String ruta, int ancho, int alto) {
        ImageIcon original = new ImageIcon(ruta);
        Image escalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(escalada));
    }
    public static void estilizarBotonPrimario(JButton btn) {
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
    }
    
}

