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
/**
 * Clase utilitaria que contiene métodos estáticos para estilizar los
 * componentes gráficos en la interfaz.
 *
 */
public class utilerias {

    /**
     * Aplica un estilo básico a un botón de menú eliminando bordes, fondo y
     * efecto de enfoque, además de cambiar el cursor a mano.
     *
     * @param btn Botón al que se le aplicará el estilo
     */
    public static void estilizarBotonMenu(JButton btn) {
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Configura un botón para mostrar un logo como imagen, eliminando texto,
     * bordes y fondo.
     *
     * @param btn Botón donde se colocará el logo
     */
    public static void colocarLogo(JButton btn) {
        btn.setText(" ");
        escalarImagen(btn, "src/main/java/com/mycompany/recursos/logo3.jpg", 110, 110);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
    }

    /**
     * Escala una imagen desde una ruta específica y la asigna como icono a un
     * botón.
     *
     * @param btn Botón donde se colocará la imagen
     * @param ruta Ruta de la imagen
     * @param ancho Ancho deseado
     * @param alto Alto deseado
     */
    public static void escalarImagen(JButton btn, String ruta, int ancho, int alto) {
        ImageIcon original = new ImageIcon(ruta);
        Image escalada = original.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(escalada));
    }

    /**
     * Aplica un estilo básico a un botón primario, eliminando el enfoque visual
     * y cambiando el cursor.
     *
     * @param btn Botón a estilizar
     */
    public static void estilizarBotonPrimario(JButton btn) {
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());;
    }

    /**
     * Aplica un estilo personalizado a una tabla, incluyendo colores, altura de
     * filas, encabezado y comportamiento visual al seleccionar filas.
     *
     * @param tbl Tabla a estilizar
     */
    public static void estilizarTabla(JTable tbl) {
        // Fondo blanco en la tabla
        tbl.setBackground(Color.WHITE);

        // Color de líneas de la tabla (gris suave)
        tbl.setGridColor(new Color(220, 220, 220));

        // Altura de las filas
        tbl.setRowHeight(30);

        // Estilo del encabezado
        tbl.getTableHeader().setBackground(new Color(18, 44, 79));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Renderer personalizado para filas
        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                // Estilo cuando la fila está seleccionada
                if (isSelected) {
                    c.setBackground(new Color(18, 44, 79));
                    c.setForeground(Color.WHITE);
                } else {
                    // Estilo normal
                    c.setBackground(Color.WHITE);
                    c.setForeground(new Color(18, 44, 79));
                }
                return c;
            }
        });
    }

    /**
     * Aplica un estilo a un botón sin fondo, con colores personalizados y
     * cursor tipo mano.
     *
     * @param btn Botón a estilizar
     */
    public static void estilizarBotonSinFondo(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(18, 44, 79));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
