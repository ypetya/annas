package annas.graph.util.color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Node colouring algorithm, Greedily choose a colour to assign to a node
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 * @param <A>
 */
public class GreedyColoring<N, A extends ArcInterface<N>> {

	private GraphInterface<N, A> graph;

	private ArrayList<Integer> c;

	private Map<N, Integer> coloring;

	private int colors;

	/**
	 * 
	 * @param graph
	 *            Graph to colour
	 */
	public GreedyColoring(GraphInterface<N, A> graph) {
		super();
		this.graph = graph;
		this.coloring = new HashMap<N, Integer>();
		this.c = new ArrayList<Integer>();
		this.colors = 0;
	}

	/**
	 * Runs the greedy colouring using an predetermined ordering of nodes.
	 */
	public void run() {
		this.run(this.graph.getNodeMap());
	}

	/**
	 * Runs the greedy colouring of the nodes in the order provided by the list.
	 * 
	 * @param order
	 */
	public void run(ArrayList<N> order) {
		ArrayList<N> nodes = order;
		N node = null;
		for (int i = 0; i < nodes.size(); i++) {
			node = nodes.get(i);
			ArrayList<N> cnodes = this.getConnectedNode(node);
			ArrayList<Integer> colors = this.getUnusedColor(cnodes);
			if (colors.size() == 0) {
				this.c.add(this.colors);
				this.coloring.put(node, this.colors);
				this.colors++;
			} else {
				this.coloring.put(node, colors.get(0));
			}
		}

	}

	private ArrayList<Integer> getUnusedColor(ArrayList<N> nodes) {
		ArrayList<Integer> ins = new ArrayList<Integer>(this.c);

		for (int i = 0; i < nodes.size(); i++) {
			if (ins.contains(this.coloring.get(nodes.get(i)))) {
				ins.remove(this.coloring.get(nodes.get(i)));
			}
		}
		return ins;
	}

	private ArrayList<N> getConnectedNode(N node) {

		ArrayList<A> arcs = this.graph.getArc(node);
		ArrayList<N> nodes = new ArrayList<N>();

		for (int i = 0; i < arcs.size(); i++) {
			nodes.add(arcs.get(i).getHead());
		}
		return nodes;
	}

}
