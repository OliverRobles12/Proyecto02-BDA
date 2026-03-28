
package org.itson.restaurante.utilerias;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PanelBordeRedondo extends javax.swing.JPanel {
    
    private int radio;
    private Color colorFondo;
    
    public PanelBordeRedondo(int radio, Color colorFondo) {
        this.radio = radio;
        this.colorFondo = colorFondo;
        setOpaque(false); 
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        g2.dispose();
        super.paintComponent(g);
    }
}