package annas.graph.drawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Creates a graphical representation of a graph.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc types
 */
public class GraphDrawer<N, A extends ArcInterface<N>> {

	/**
	 * Graph to draw
	 */
	protected GraphInterface<N, A> graph;

	/**
	 * Arc Drawer
	 */
	protected DrawArc<A> arcDrawer;

	/**
	 * Node Drawer
	 */
	protected DrawNode<N> nodeDrawer;

	/**
	 * Layout manager
	 */
	protected Placer<N, A> placer;

	/**
	 * Default background colour
	 */
	protected Color defaultBackgroungColour = Color.WHITE;

	/**
	 * X dimension of the image
	 */
	protected int sizeX;

	/**
	 * Y dimension of the image
	 */
	protected int sizeY;

	public GraphDrawer(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
		this.sizeX = 500;
		this.sizeY = 500;
	}

	public GraphDrawer(GraphInterface<N, A> graph, DrawNode<N> nodeDrawer,
			DrawArc<A> arcDrawer,
			Placer<N, A> placer) {
		super();
		this.graph = graph;
		this.sizeX = 500;
		this.sizeY = 500;
		this.arcDrawer = arcDrawer;
		this.nodeDrawer = nodeDrawer;
		this.placer = placer;
	}

	/**
	 * Draws the graph
	 * 
	 * @return graphical representation of the graph
	 */
	public BufferedImage draw() {
		BufferedImage bufimg = new BufferedImage(this.sizeX, this.sizeY,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bufimg.createGraphics();
		g.setColor(this.defaultBackgroungColour);
		g.fillRect(0, 0, this.sizeX, this.sizeY);
		this.draw(g);
		return bufimg;
	}

	/**
	 * Draws the graph
	 * 
	 * @param buffImage
	 *            Image to draw the graph on
	 * @return graphical representation of the graph
	 */
	public BufferedImage draw(BufferedImage bufimg) {

		Graphics2D g = bufimg.createGraphics();
		g.setColor(this.defaultBackgroungColour);
		g.fillRect(0, 0, this.sizeX, this.sizeY);
		this.draw(g);
		return bufimg;
	}

	/**
	 * Draws a graph
	 * 
	 * 
	 * @param graphic
	 */
	public void draw(Graphics2D graphic) {
		Map<N, int[]> map = this.placer.place(this.graph, sizeX, sizeY);

		ArrayList<N> nodeSet = this.graph.getNodeMap();
		ArrayList<A> arcSet;

		for (N node : nodeSet) {
			arcSet = this.graph.getArc(node);

			for (A arc : arcSet) {
				this.arcDrawer.drawArc(graphic, arc, map.get(node)[0], map
						.get(node)[1], map.get(arc.getHead())[0], map.get(arc
						.getHead())[1]);
			}
		}

		for (N node : nodeSet) {
			arcSet = this.graph.getArc(node);
			this.nodeDrawer.drawNode(graphic, node, map.get(node)[0], map
					.get(node)[1]);

		}

	}

	public void getImage(String Name) {
		try {
			File outputfile = new File(Name);
			ImageIO.write(this.draw(), "bmp", outputfile);
		} catch (IOException e) {
		}
	}

	public void setGraph(GraphInterface<N, A> graph) {
		this.graph = graph;
	}
}
