package annas.graph.generate;

import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.NodeFactory;

/**
 * Generates a linear Graph, a straight line graph
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class LinearGraphGenerator<N, A extends ArcInterface<N>> implements GraphGenerator<N, A> {

	/**
	 * Start node
	 */
	private N start_node;
	
	/**
	 * End node
	 */
	private N end_node;
	
	/**
	 * Number of nodes in the target graph
	 */
	private int length;
	
	public LinearGraphGenerator(int length){
		super();
		if (length < 0) {
			throw new IllegalArgumentException("size must be >= 0");
		}
		this.length = length;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void generate(GraphInterface<N, A> target, NodeFactory<N> factory, Map<String,Object> map) {
		if(map == null){
			map = new HashMap<String, Object>();
		}
		if (target == null)
			return;
		N newnode = factory.createNode();
		N tmp;
		map.put("Start", newnode);
		this.start_node = newnode;
		target.addNode(newnode);
		tmp = newnode;
		for(int i = 0; i< this.length-1; i++){
			newnode = factory.createNode();
			target.addNode(newnode);
			target.addArc(tmp, newnode, new DefaultWeight(1.0));
			tmp = newnode;
		}
		this.end_node = tmp;
		map.put("End", tmp);
	}

	/**
	 * @return the start_node
	 */
	public N getStart_node() {
		return start_node;
	}


	/**
	 * @return the end_node
	 */
	public N getEnd_node() {
		return end_node;
	}


	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}


}
