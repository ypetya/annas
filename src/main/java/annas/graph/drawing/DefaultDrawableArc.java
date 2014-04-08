package annas.graph.drawing;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Default implementation of Arc drawer. Draws a straight line from two points
 * (with no indication of direction).
 * 
 * @author Sam Wilson
 * 
 * @param <A>
 */
public class DefaultDrawableArc<A> implements DrawArc<A> {

	private Color color;

	public DefaultDrawableArc() {
		super();
		this.color = Color.BLACK;
	}

	public DefaultDrawableArc(Color color) {
		super();
		this.color = color;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawArc(Graphics2D graphic, A arc, int x1, int y1, int x2,
			int y2) {
		graphic.setColor(this.color);
		graphic.drawLine(x1, y1, x2, y2);


	}

}
