/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.restaurante.utilerias;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
        escalarImagen(btn, "src/main/java/org/itson/restaurante/recursos/logo3.jpg", 110, 110);
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
    }

    /**
     * Aplica un estilo personalizado a una tabla, incluyendo colores, altura de
     * filas, encabezado y comportamiento visual al seleccionar filas.
     *
     * @param tbl Tabla a estilizar
     */
    public static void estilizarTabla(JTable tbl) {
        tbl.setBackground(Color.WHITE);

        tbl.setGridColor(new Color(220, 220, 220));

        tbl.setRowHeight(30);

        tbl.getTableHeader().setBackground(new Color(18, 44, 79));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

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

    /**
     * Aplica un estilo personalizado a una tabla que contenga imagenes, incluyendo colores, altura de
     * filas, encabezado y comportamiento visual al seleccionar filas.
     *
     * @param tbl Tabla a estilizar
     */
    public static void estilizarTablaImagenes(JTable tbl) {
        
        tbl.setBackground(Color.WHITE);

        tbl.setGridColor(new Color(220, 220, 220));

        tbl.getTableHeader().setBackground(new Color(18, 44, 79));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        //RENDER PARA LAS COLUMNAS TIPO OBJCT
        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(CENTER);//alinea el contenido en el centro 
                setVerticalAlignment(CENTER);
                //CUANDO SE SELECCIONA LA FILA CAMBIA EL COLOR
                if (isSelected) {
                    c.setBackground(new Color(18, 44, 79));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(new Color(18, 44, 79));
                }
                //COLORES PARA LOS STOCK 
                if (column == 3 && value != null) {
                    int stock = Integer.parseInt(value.toString());
                    //Si hay mucho syock se queda normal 
                    if (stock >= 20) {
                        c.setBackground(Color.WHITE);
                        c.setForeground(new Color(18, 44, 79));
                        // si es mayor a 10 menor a 20 se pone azul claro en el fondo 
                    } else if (stock >= 10) {
                        c.setBackground(new Color(224, 235, 255));
                        c.setForeground(new Color(18, 44, 79));
                        // si es mayor a 10 menor a 20 se pone oscuro claro en el fondo 
                    } else {
                        c.setBackground(new Color(91, 136, 178));
                        c.setForeground(Color.WHITE);
                    }
                }
                return c;
            }
        });
        //RENDER PARA LAS COLUMNAS TIPO IMAGE
        tbl.setDefaultRenderer(ImageIcon.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            
            
            
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            label.setOpaque(true);

            if (isSelected) {
                label.setBackground(new Color(18, 44, 79));
            } else {
                label.setBackground(Color.WHITE);
            }

            if (value instanceof ImageIcon) {
                label.setIcon((ImageIcon) value);
            } else {
                label.setIcon(null);
            }

            return label;
        }
    });
    }
    
    public static void estilizarTablaProductos(JTable tbl) {
        
        tbl.setBackground(Color.WHITE);
        tbl.setGridColor(new Color(220, 220, 220));
        tbl.getTableHeader().setBackground(new Color(18, 44, 79));
        tbl.getTableHeader().setForeground(Color.WHITE);
        tbl.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        tbl.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                setHorizontalAlignment(CENTER);
                setVerticalAlignment(CENTER);
                
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
    /**
     * Aplica un ícono personalizado a la ventana principal o secundaria.
     * La imagen se carga desde la ruta de recursos y se escala a 64x64 píxeles.
     *
     * @param frame Ventana a la que se le aplicará el ícono
     */
    public static void aplicarIcono(JFrame frame) {
        ImageIcon icono = new ImageIcon("src/main/java/org/itson/restaurante/recursos/logo3.jpg");
        Image escalada = icono.getImage().getScaledInstance(
                64, 64, Image.SCALE_SMOOTH
        );
        frame.setIconImage(escalada);
    }
}
