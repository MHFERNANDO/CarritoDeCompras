package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

public class MiDesktopPane extends JDesktopPane {

    public MiDesktopPane() { super(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* ─────────────────  COLORES CORPORATIVOS  ───────────────── */
        Color doradoClaro = Color.decode("#C8965C");  // rombo y línea
        Color doradoOscuro = Color.decode("#C88039"); // degradé
        Color marron       = Color.decode("#80322B"); // texto principal
        Color sombra       = new Color(0, 0, 0, 60);  // sombra suave

        /* ─────────────────  FONDO CON SOMBRA SUAVE  ───────────────── */
        int width  = getWidth();
        int height = getHeight();

        // Rectángulo sutil sombreado detrás del logo
        RoundRectangle2D fondo = new RoundRectangle2D.Double(
                width * 0.15, height * 0.35,
                width * 0.70, height * 0.25,
                30, 30
        );
        g2.setPaint(new GradientPaint(0, 0, doradoClaro.brighter(),
                0, height, doradoOscuro.darker()));
        g2.fill(fondo);

        // Sombra inferior suave
        g2.setColor(sombra);
        g2.fillRoundRect((int)(width*0.15)+4, (int)(height*0.35)+8,
                (int)(width*0.70), (int)(height*0.25), 30,30);

        /* ─────────────────  TEXTO Y ROMBO  ───────────────── */
        Font fuente = new Font("Arial Black", Font.BOLD, 60);
        g2.setFont(fuente);
        FontMetrics fm = g2.getFontMetrics();

        String textoCompleto = "CHIQUI CENTRO";
        String textoSinC     = textoCompleto.substring(1); // "HIQUI PEDRO"
        int anchoC           = fm.charWidth('C');
        int anchoRestante    = fm.stringWidth(textoSinC) + 15; // espacio
        int anchoTotal       = anchoC + anchoRestante;

        // Coordenadas centradas
        int x0 = (width  - anchoTotal) / 2;
        int y0 = (height + fm.getAscent()) / 2 - 10;

        // --- ROMBO DETRÁS DE LA C ---
        int sizeRombo   = fm.getAscent() + 20;
        int centroX     = x0 + anchoC / 2;
        int centroY     = y0 - fm.getAscent()/2;

        Polygon rombo = new Polygon();
        rombo.addPoint(centroX,               centroY - sizeRombo/2);
        rombo.addPoint(centroX + sizeRombo/2, centroY);
        rombo.addPoint(centroX,               centroY + sizeRombo/2);
        rombo.addPoint(centroX - sizeRombo/2, centroY);

        // Borde dorado oscuro
        g2.setColor(doradoOscuro.darker());
        g2.setStroke(new BasicStroke(4));
        g2.drawPolygon(rombo);
        // Relleno dorado degradado
        g2.setPaint(new GradientPaint(centroX, centroY-sizeRombo/2,
                doradoClaro, centroX, centroY+sizeRombo/2,
                doradoOscuro));
        g2.fillPolygon(rombo);

        // Letra C en blanco, centrada
        g2.setColor(Color.WHITE);
        // Corrección fina para centrar horizontalmente la C
        int xC = x0 + (anchoC - fm.charWidth('C'))/2;
        g2.drawString("C", xC, y0-10);

        // --- RESTO DEL TEXTO ---
        g2.setColor(marron);
        g2.drawString(textoSinC, x0 + anchoC + 15, y0-10);

        /* ─────────────────  LÍNEA DECORATIVA  ───────────────── */
        g2.setStroke(new BasicStroke(5));
        g2.setColor(doradoClaro);
        g2.drawLine(x0, y0 + 22, x0 + anchoTotal, y0 + 22);


        /* ─────────────────  PEQUEÑA SOMBRA AL TEXTO  ───────────────── */
        // (opcional) crear una sombra ligera desplazada
        AffineTransform orig = g2.getTransform();
        g2.translate(2, 2);
        g2.setColor(new Color(0,0,0,40));
        g2.setFont(fuente);
        g2.drawString("C", xC, y0-10);
        g2.drawString(textoSinC, x0 + anchoC + 15, y0-10);
        g2.setTransform(orig);
    }
}
