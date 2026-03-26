/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.utilerias;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

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
    
    public static void estilizarTabla(JTable tbl){
        // Fondo blanco en la tabla
        tbl.setBackground(Color.WHITE);
        tbl.setGridColor(new Color(220, 220, 220)); // líneas grises suaves
        tbl.setRowHeight(30); // filas más altas

        // Encabezado de la tabla estilo oscuro como tus otros botones
        tbl.getTableHeader().setBackground(new Color(18, 44, 79));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        
        @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);
                    if (isSelected) {
                        c.setBackground(new Color(18, 44, 79));
                        c.setForeground(Color.WHITE);
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(new Color(18, 44, 79));
                    }
                    return c;
                }
            });
    }
    
    public static void estilizarBotonSinFondo(JButton btn){
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(18, 44, 79));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}

