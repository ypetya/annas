package annas.graph.util;

import java.util.ArrayList;

import annas.graph.ArcInterface;
import annas.graph.DefaultWeight;
import annas.graph.GraphInterface;
import annas.graph.GraphPath;

/**
 * Determines all pair shortest paths, as described <a
 * href="http://mathworld.wolfram.com/Floyd-WarshallAlgorithm.html"> here</a>
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node type
 * @param <A>
 *            Arc type
 */
public class Floyd<N, A extends ArcInterface<N>> {

	/**
	 * Graph to perform the algorithm on.
	 */
	private GraphInterface<N, A> g;

	/**
	 * Integer array containing the route from source to destination.
	 */
	private int[][] R;

	/**
	 * Double array containing the distance from source to destination.
	 */
	private double[][] D;

	/**
	 * Default constructor
	 * 
	 * @param g
	 *            Graph o perform algorithm on.
	 */
	public Floyd(GraphInterface<N, A> g) {
		this.g = g;
		makeMatrix();
		run();

	}

	/**
	 * The route matrix for the current graph.
	 * 
	 * @return The route matrix.
	 */
	public int[][] getR() {
		return R;
	}

	/**
	 * The distance matrix for the current graph.
	 * 
	 * @return distance matrix
	 */
	public double[][] getD() {
		return D;
	}

	/**
	 * Finds the distance between the two nodes, by looking in the distance
	 * matrix.
	 * 
	 * @param A
	 *            Source
	 * @param B
	 *            Destination
	 * @return Distance between the nodes.
	 */
	public double getDistance(N A, N B) {
		return D[g.getNodeMap().indexOf(A)][g.getNodeMap().indexOf(B)];
	}

	/**
	 * Find the route through the Graph from the source node to the destination
	 * node, by using the route matrix.
	 * 
	 * @param A
	 *            Source
	 * @param B
	 *            Destination
	 * @return Graph of the route between the two nodes.
	 */
	public GraphPath<N, A> getRoute(N A, N B) {
		GraphPath<N, A> m = new GraphPath<N, A>(A, B);
		N t = A;
		int col = this.g.getNodeMap().indexOf(B);
		int row = this.g.getNodeMap().indexOf(A);
		int temp = row;

		ArrayList<N> fg = this.g.getNodeMap();

		while (temp != col) {
			row = R[col][row] - 1;
			m.add(t, this.g.getArcFactory().create(t, fg.get(row),
					new DefaultWeight(1d)));
			t = fg.get(row);
			temp = row;
		}

		return m;
	}

	/**
	 * Runs the algorithm that finds the shortest path between all pairs.
	 */
	private void run() {
		int loc;
		for (int it = 1; it <= this.D.length; it++) {
			loc = it - 1;

			for (int x = 0; x < this.D.length; x++) {
				for (int y = 0; y < this.D[0].length; y++) {
					if ((D[loc][y] + D[x][loc] < D[x][y])
							&& (x != loc || y != loc)) {
						D[x][y] = D[it - 1][y] + D[x][loc];
						this.R[x][y] = this.R[loc][y];

					}
				}
			}

		}
	}

	/**
	 * Sets up the matrices, and converts from the adjacent list format into
	 * distance matrix.
	 */
	private void makeMatrix() {
		int SIZE = this.g.getNuNodes();
		D = new double[SIZE][SIZE];
		R = new int[SIZE][SIZE];

		for (int i = 0; i < R.length; i++) {
			for (int j = 0; j < R[0].length; j++) {
				R[i][j] = i + 1;
			}
		}

		for (int i = 0; i < D.length; i++) {
			for (int j = 0; j < D[0].length; j++) {
				D[i][j] = Double.MAX_VALUE;
			}
		}

		ArrayList<N> fg = this.g.getNodeMap();
		N current;
		for (int x = 0; x < g.getNodeMap().size(); x++) {
			current = fg.get(x);
			ArrayList<A> arcs = this.g.getArc(current);
			for (int y = 0; y < arcs.size(); y++) {

				D[x][g.getNodeMap().indexOf(arcs.get(y).getHead())] = arcs.get(
						y).getWeight();

			}

		}

	}

}
