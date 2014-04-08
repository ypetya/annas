package annas.graph;

import annas.graph.NodeFactory;

public class DefaultNodeFactory implements NodeFactory<String> {

	private int count;
	
	public DefaultNodeFactory() {
		super();
		this.count = -1;
	}

	@Override
	public String createNode() {
		this.count++;
		return Integer.valueOf(count).toString();
		
	}

}
