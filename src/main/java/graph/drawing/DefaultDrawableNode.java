package annas.graph.drawing;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Default implementation of Node Drawer. Draws a Circle of the specified
 * colour.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 */
public class DefaultDrawableNode<N> implements DrawNode<N> {

	private int size;

	private Color color;

	public DefaultDrawableNode() {
		super();
		this.size = 10;
		this.color = Color.RED;
	}

	public DefaultDrawableNode(Color color, int size) {
		super();
		this.size = size;
		this.color = color;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void drawNode(Graphics2D graphic, N node, int x, int y) {
		graphic.setColor(this.color);
		graphic.drawOval(x - this.size / 2, y - this.size / 2, this.size,
				this.size);
	}

}
