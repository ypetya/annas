package annas.graph;

public class ClassBasedNodeFactory<N> implements NodeFactory<N>{

	private Class<N> nodeClass;
	
	public ClassBasedNodeFactory(Class<N> nodeClass){
		super();
		this.nodeClass = nodeClass;
	}

	@Override
	public N createNode() {
		try {
            return this.nodeClass.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Node factory failed", ex);
        }
	}
	
	
 
}
