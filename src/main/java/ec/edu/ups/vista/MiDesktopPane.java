package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiDesktopPane extends JDesktopPane {

    public MiDesktopPane() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo blanco
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Fuente principal
        Font fuente = new Font("Arial Black", Font.BOLD, 48);
        g2.setFont(fuente);
        FontMetrics metrics = g2.getFontMetrics();

        String textoSinC = "HIQUI PEDRO";
        int anchoC = metrics.charWidth('C');
        int anchoTextoSinC = metrics.stringWidth(textoSinC);
        int anchoTotal = anchoC + anchoTextoSinC + 8;
        int altoTexto = metrics.getHeight();

        int xInicio = (getWidth() - anchoTotal) / 2;
        int yTexto = (getHeight() + metrics.getAscent()) / 2;

        // Posición exacta donde se dibuja la C
        int xC = xInicio;
        int yC = yTexto;

        // Centro del rombo ajustado respecto a la posición real de la C
        int romboSize = altoTexto + 10;
        int centroRomboX = xC + anchoC / 2;
        int centroRomboY = yTexto - metrics.getAscent() / 2 + 5; // ajuste fino +5

        // Dibuja rombo detrás de la C
        Polygon rombo = new Polygon();
        rombo.addPoint(centroRomboX, centroRomboY - romboSize / 2);
        rombo.addPoint(centroRomboX + romboSize / 2, centroRomboY);
        rombo.addPoint(centroRomboX, centroRomboY + romboSize / 2);
        rombo.addPoint(centroRomboX - romboSize / 2, centroRomboY);

        g2.setColor(Color.decode("#C8965C")); // dorado
        g2.fillPolygon(rombo);

        // Letra C en blanco sobre el rombo
        g2.setColor(Color.WHITE);
        g2.drawString("C", xC, yC);

        // Resto del texto
        g2.setColor(Color.decode("#80322B")); // marrón
        g2.drawString(textoSinC, xC + anchoC + 8, yC);

        // Línea decorativa
        g2.setColor(Color.decode("#C8965C"));
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(xInicio+20, yC + 15, xInicio + anchoTotal, yC + 15);
    }
}
