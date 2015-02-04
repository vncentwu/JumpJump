import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class OutlineLabel extends JButton 
{

    private Color outlineColor = Color.WHITE;
    private boolean isPaintingOutline = false;
    private boolean forceTransparent = false;
	int width;

	/*	
	 *	Contructs an outline label
	 */
    public OutlineLabel() {
        super();
    }

	/*
	 *	Contructs an outline label
	 *
	 *	@param text adds a name to the label
	 */
    public OutlineLabel(String text) {
        super(text);
        width = 2;
    }
    
	/*
	 *	Gets the color of the outline
	 *
	 *	@return the color of the outline
	 */
    public Color getOutlineColor() {
        return outlineColor;
    }
	
	/*
	 *	Sets the color of the outline
	 *
	 *	@param outlineColor color the outline will be set to
	 */
    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
        this.invalidate();
    }

	/*
	 *	Gets the color of the foreground
	 *
	 *	@return color of the foreground
	 */
    @Override
    public Color getForeground() {
        if ( isPaintingOutline ) {
            return outlineColor;
        } else {
            return super.getForeground();
        }
    }
	
	/*
	 *	Returns if it is transparent or not
	 *
	 *	@return transparency 
	 */
    @Override
    public boolean isOpaque() {
        if ( forceTransparent ) {
            return false;
        } else {
            return super.isOpaque();
        }
    }

	/*
	 *	Paint method 
	 *
	 *	@param g graphics of paint
	 */
    @Override
    public void paint(Graphics g) {

        String text = getText();
        if ( text == null || text.length() == 0 ) {
            super.paint(g);
            return;
        }

        // 1 2 3
        // 8 9 4
        // 7 6 5

        if ( isOpaque() )
            super.paint(g);

        forceTransparent = true;
        isPaintingOutline = true;
        g.translate(-1, -1); super.paint(g); // 1 
        g.translate( 1,  0); super.paint(g); // 2 
        g.translate( 1,  0); super.paint(g); // 3 
        g.translate( 0,  1); super.paint(g); // 4
        g.translate( 0,  1); super.paint(g); // 5
        g.translate(-1,  0); super.paint(g); // 6
        g.translate(-1,  0); super.paint(g); // 7
        g.translate( 0, -1); super.paint(g); // 8
        g.translate( 1,  0); // 9
        isPaintingOutline = false;

        super.paint(g);
        forceTransparent = false;

    }

    public static void main(String[] args) {
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OutlineLabel label = new OutlineLabel("Test");
        label.setOpaque(true);
        w.setContentPane(new JPanel(new BorderLayout()));
        w.add(label, BorderLayout.CENTER);
        w.pack();
        w.setVisible(true);
    }
}