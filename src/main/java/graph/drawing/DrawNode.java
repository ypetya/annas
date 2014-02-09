package annas.graph.drawing;

import java.awt.Graphics2D;

/**
 * Interface for Node drawing objects
 * 
 * @author Sam Wilson
 * 
 * @param <A>
 *            Arc type
 */
public interface DrawNode<N> {

	/**
	 * Draws a node at a point
	 * 
	 * @param graphic
	 *            Graphic object of the image to draw the node on
	 * @param node
	 *            Node to draw
	 * @param x
	 *            x coordinate of the node
	 * @param y
	 *            y coordinate of the node
	 */
	public void drawNode(Graphics2D graphic, N node, int x, int y);

}
