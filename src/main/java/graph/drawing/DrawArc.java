package annas.graph.drawing;

import java.awt.Graphics2D;

/**
 * Interface for arc drawing objects
 * 
 * @author Sam Wilson
 * 
 * @param <A>
 *            Arc type
 */
public interface DrawArc<A> {

	/**
	 * Draws an arc at a given point.
	 * 
	 * @param graphic
	 *            Graphic object from the image the arc is to be drawn on
	 * @param arc
	 *            arc to draw
	 * @param x1
	 *            x coordinate of the tail
	 * @param y1
	 *            y coordinate of the tail
	 * @param x2
	 *            x coordinate of the head
	 * @param y2
	 *            y coordinate of the head
	 */
	public void drawArc(Graphics2D graphic, A arc, int x1, int y1, int x2,
			int y2);

}
