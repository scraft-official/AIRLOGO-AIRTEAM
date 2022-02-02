package airteam.projects.atarilogo.components.templates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

@SuppressWarnings("serial")
public class ComboBox<E> extends JComboBox<E> {

    public String getTitleText() {
        return labeText;
    }

    public void setTitleText(String labeText) {
        this.labeText = labeText;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private String labeText = "TEXT";
    private Color lineColor = new Color(255,255,255);
    private boolean mouseOver;

    public ComboBox() {
    		setOpaque(false);
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 0, 5, 3));
        setUI(new ComboUI(this));
        setForeground(new Color(255, 255, 255));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                JComponent com = (JComponent) super.getListCellRendererComponent(jlist, o, i, bln, bln1);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                if (bln) {
                    com.setForeground(new Color(77, 209, 115));
                    com.setFont(new Font("Tahoma", Font.BOLD, 12));
                }
                else {
                	com.setForeground(new Color(255,255,255));
                	com.setFont(new Font("Tahoma", Font.BOLD, 12));
                }
                jlist.setSelectionForeground(new Color(255,255,255));
                jlist.setOpaque(false);
                com.setOpaque(false);
                return com;
            }
        });
    }

    private class ComboUI extends BasicComboBoxUI {
        private ComboBox combo;

        public ComboUI(ComboBox combo) {
						this.combo = combo;
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    mouseOver = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    mouseOver = false;
                    repaint();
                }
            });
            addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(200, 200, 200));
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(150, 150, 150));
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(150, 150, 150));
                }
            });
        }

        @Override
        public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {

        }

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup pop = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    list.setFixedCellHeight(30);
                    JScrollPane scroll = new JScrollPane(list);
                    scroll.setBackground(Color.WHITE);
                    JScrollBar sb = new JScrollBar();
                    sb.setUI(new JScrollBarUI());
                    sb.setUnitIncrement(3);
                    sb.setPreferredSize(new Dimension(3, 25));
                    sb.setOpaque(false);
                    sb.setForeground(new Color(207, 62, 101, 255));
                    sb.setBackground(new Color(0, 0, 0, 0));
                    scroll.setVerticalScrollBar(sb);
                    scroll.getViewport().setOpaque(false);
                    scroll.setOpaque(false);
                    return scroll;
                }
                
                public void paintComponent(Graphics g) {
                	Graphics2D g2d = (Graphics2D) g;
                	g2d.setRenderingHint(
              				RenderingHints.KEY_ANTIALIASING,
              				RenderingHints.VALUE_ANTIALIAS_ON);
                	
                	g2d.setColor(new Color(40,40,40));
                	g2d.fillRoundRect(0, -8, getWidth()-2, getHeight()+7, 15, 15);
                	
                	g2d.setColor(new Color(255, 255,255));
                	g2d.setStroke(new BasicStroke(2));
           
                	g2d.drawRoundRect(0, -8, getWidth()-2, getHeight()+7, 15, 15);
                }
                
            };
            pop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pop.setOpaque(false);
            pop.setBorder(new LineBorder(new Color(200, 200, 200), 0));
            return pop;
        }

        @Override
        public void paint(Graphics grphcs, JComponent jc) {
            super.paint(grphcs, jc);
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            int width = getWidth();
            int height = getHeight();
            if (mouseOver) {
                g2.setColor(lineColor);
            } else {
                g2.setColor(new Color(150, 150, 150));
            }
            g2.fillRect(2, height - 1, width - 4, 1);
            createHintText(g2);
            createLineStyle(g2);
            g2.dispose();
        }

        private void createHintText(Graphics2D g2) {
            Insets in = getInsets();
            g2.setColor(new Color(150, 150, 150));
            FontMetrics ft = g2.getFontMetrics();
            Rectangle2D r2 = ft.getStringBounds(combo.getTitleText(), g2);
            double height = getHeight() - in.top - in.bottom;
            double textY = (height - r2.getHeight()) / 2;
            g2.drawString(combo.getTitleText(), in.right, (int) (in.top + textY + ft.getAscent() - 18));
        }

        private void createLineStyle(Graphics2D g2) {
            if (isFocusOwner()) {
                int width = getWidth();
                int height = getHeight();
                g2.setColor(lineColor);
                g2.fillRect(0, height - 2, width-1, 2);
            }
        }

        private class ArrowButton extends JButton {

            public ArrowButton() {
                setContentAreaFilled(false);
                setBorder(new EmptyBorder(5, 5, 5, 5));
                setBackground(new Color(150, 150, 150));
            }

            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                int size = 8;
                int x = (width - size) / 2;
                int y = (height - size) / 2;
                int px[] = {x, x + size, x + size / 2};
                int py[] = {y, y, y + size};
                g2.setColor(getBackground());
                g2.fillPolygon(px, py, px.length);
                g2.dispose();
            }
        }
    }
}