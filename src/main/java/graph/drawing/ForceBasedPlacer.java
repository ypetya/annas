package annas.graph.drawing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import annas.graph.ArcInterface;
import annas.graph.GraphInterface;

/**
 * Uses a Forced based iterative algorithm to find the optimal location of each
 * node.
 * 
 * @author Sam Wilson
 * 
 * @param <N>
 *            Node Type
 * @param <A>
 *            Arc type
 */
public class ForceBasedPlacer<N, A extends ArcInterface<N>> implements
		Placer<N, A> {

	private Force f;
	private GraphInterface<N, A> g;
	private ArrayList<Vertex<N>> verticies = new ArrayList<Vertex<N>>();
	public static boolean debug = false;
	public static boolean random = false;


	public ForceBasedPlacer() {
		super();
		f = new Force(0.092, 75, 50, 50, 1);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<N, int[]> place(GraphInterface<N, A> graph, int sizeX, int sizeY) {
		this.g = graph;
		init();
		for (int i = 0; i < 100; i++) {
			this.iterateForce();
		}
		Map<N, int[]> map = new HashMap<N, int[]>();
		for (Vertex v : this.verticies) {
			map.put((N) v.node, new int[] { v.getXloc(), v.getYloc() });
		}

		return map;

	}

	private void init() {
		/**
		 * Places Nodes on a grid.
		 */

		ArrayList<N> fg = this.g.getNodeMap();
		if (!random) {
			for (int i = 0; i < fg.size(); i++) {
				this.verticies.add(new Vertex(fg.get(i), (((i) % 4) + 1) * 100,
						(((i + 1) / 4) + 1) * 100));

			}
		} else {
			/**
			 * Randomly Places Nodes near the Centre of the Picture.
			 */

			for (int i = 0; i < fg.size(); i++) {

				Vertex v = new Vertex(fg.get(i), (int) (Math.random() * 500),
						(int) (Math.random() * 500));
				this.verticies.add(v);
				/*
				 * System.out.println(v.getClass()); this.g.getNodeMap().set(i,
				 * v);
				 */

			}
		}

	}

	public void iterateForce() {

		for (int i = 0; i < this.g.getNodeMap().size(); i++) {
			this.calculateForces(i);
		}
		this.calculateChange();

	}

	public void calculateForces(int i) {

		Vertex V = (Vertex) this.verticies.get(i);

		Vertex U;
		Vertex X;

		if (debug) {
			System.out
					.println("-------------------------------------------------------");
			System.out.println("Node: " + V.getNode());
			System.out.println("Node X Location: " + V.getXloc());
			System.out.println("Node Y Location: " + V.getYloc());
			System.out
					.println("-------------------------------------------------------");
		}

		for (int j = 0; j < this.verticies.size(); j++) {
			U = this.verticies.get(j);
			if (!(V.equals(U))) {
				f.setPoint(V, U);
				if (debug) {
					System.out.println("Distance between: " + V.getNode()
							+ " and " + U.getNode() + " = " + f.getDistance());
					System.out.println("Charge Force between: " + V.getNode()
							+ " and " + U.getNode() + " = " + f.charge());
					System.out.println("Angle between: " + V.getNode()
							+ " and " + U.getNode() + " = " + f.getAngle(V, U));
				}
				V.setHforce(V.getHforce()
						+ ((f.charge() * Math.sin(f.getAngle(V, U))) * xcoef(V,
								U)));
				V.setVforce(V.getVforce()
						+ ((f.charge() * Math.cos(f.getAngle(V, U))) * ycoef(V,
								U)));

			}
		}

		for (int k = 0; k < this.g.getArc((N) V.getNode()).size(); k++) {

			int loc = this.getVertexByNode((this.g.getArc((N) V.getNode()).get(
					k).getHead()));
			X = this.verticies.get(loc);
			if (!(V.equals(X))) {
				f.setPoint(V, X);

				// testing

				if (debug) {
					System.out.println("Distance between: " + V.getNode()
							+ " and " + X.getNode() + " = " + f.getDistance());
					System.out.println("Spring Force between: " + V.getNode()
							+ " and " + X.getNode() + " = " + f.spring());
					System.out.println("Angle between: " + V.getNode()
							+ " and " + X.getNode() + " = " + f.getAngle(V, X));
				}
				// Experimental
				V
						.setHforce(V.getHforce()
								+ ((f.springRepel() * Math
										.sin(f.getAngle(X, V))) * xcoef(V, X)));
				V
						.setVforce(V.getVforce()
								+ ((f.springRepel() * Math
										.cos(f.getAngle(X, V))) * ycoef(V, X)));
				V
						.setHforce(V.getHforce()
								+ ((f.springAttract() * Math.sin(f.getAngle(X,
										V))) * xcoef(X, V)));
				V
						.setVforce(V.getVforce()
								+ ((f.springAttract() * Math.cos(f.getAngle(X,
										V))) * ycoef(X, V)));

				X
						.setHforce(X.getHforce()
								+ ((f.springRepel() * Math
										.sin(f.getAngle(X, V))) * xcoef(X, V)));
				X
						.setVforce(X.getVforce()
								+ ((f.springRepel() * Math
										.cos(f.getAngle(X, V))) * ycoef(X, V)));
				X
						.setHforce(X.getHforce()
								+ ((f.springAttract() * Math.sin(f.getAngle(X,
										V))) * xcoef(V, X)));
				X
						.setVforce(X.getVforce()
								+ ((f.springAttract() * Math.cos(f.getAngle(X,
										V))) * ycoef(V, X)));

			}

		}

	}

	public void calculateChange() {
		Vertex V;
		ArrayList<N> gc = this.g.getNodeMap();
		for (int i = 0; i < gc.size(); i++) {
			V = this.verticies.get(this.getVertexByNode(gc.get(i)));
			if (debug) {
				System.out.println("VForce CALC: " + V.getNode() + " "
						+ Math.round(V.getVforce()));
				System.out.println("HForce CALC : " + V.getNode() + " "
						+ Math.round(V.getHforce()));
			}
			V.setXloc((int) (V.getXloc() + Math.round((V.getHforce() * f
					.getTimeInterval()))));
			V.setYloc((int) (V.getYloc() + Math.round(V.getVforce()
					* f.getTimeInterval())));
			V.setHforce(0);
			V.setVforce(0);
		}
	}

	private int xcoef(Vertex V, Vertex U) {
		if (V.getXloc() < U.getXloc()) {
			return -1;
		} else {
			return 1;
		}
	}

	private int ycoef(Vertex V, Vertex U) {
		if (V.getYloc() > U.getYloc()) {
			return 1;
		} else {
			return -1;
		}
	}

	private int getVertexByNode(N node) {
		for (Vertex v : this.verticies) {
			if (v.getNode().equals(node)) {
				return this.verticies.indexOf(v);
			}
		}
		return -1;
	}

	class Vertex<N> {

		private N node;
		private int xloc;
		private int yloc;
		private double vforce;
		private double hforce;

		public Vertex() {
			super();
		}

		public Vertex(N node, int i, int j) {
			super();
			this.node = node;
			this.xloc = i;
			this.yloc = j;

		}

		public N getNode() {
			return node;
		}

		public void setNode(N node) {
			this.node = node;
		}

		public int getXloc() {
			return xloc;
		}

		public void setXloc(int xloc) {
			this.xloc = xloc;
		}

		public int getYloc() {
			return yloc;
		}

		public void setYloc(int yloc) {
			this.yloc = yloc;
		}

		public double getVforce() {
			return vforce;
		}

		public void setVforce(double vforce) {
			vforce = vforce;
		}

		public double getHforce() {
			return hforce;
		}

		public void setHforce(double hforce) {
			hforce = hforce;
		}
	}

	class Force {

		/**
		 * Value of K
		 */
		private double springK;

		/**
		 * Length on an unextended spring.
		 */
		private double unextended;

		/**
		 * Distance between two particles.
		 */
		private double distance;

		/**
		 * Charge on particle A
		 */
		private double chargeA;

		/**
		 * Charge on particle B
		 */
		private double chargeB;

		/**
		 * Time interval to work out the displacement.
		 */
		private double timeInterval;

		/**
		 * Parameterized Constructor.
		 * 
		 * @param springK
		 *            Spring Constant.
		 * @param unextended
		 *            Length of a relaxed Spring.
		 * @param chargeB
		 *            Charge on particle B.
		 * @param chargeA
		 *            Charge on particle A.
		 */
		public Force(double springK, double unextended, double chargeB,
				double chargeA, double timeInterval) {
			this.springK = springK;
			this.unextended = unextended;
			this.chargeB = chargeB;
			this.chargeA = chargeA;
			this.timeInterval = timeInterval;
		}

		/**
		 * Default constructor.
		 */
		public Force() {
			super();
		}

		/**
		 * Gets over all force exerted by spring.
		 * 
		 * @return Over all Force exerted by the spring.
		 */
		public double spring() {
			return springAttract() + springRepel();
		}

		/**
		 * Gets the Attraction caused by the spring.
		 * 
		 * @return Attractive force exerted by the spring.
		 */
		public double springAttract() {
			double difference = unextended - distance;
			if (difference < 0) {
				return ((difference) * springK * -1);
			} else {
				return 0;
			}
		}

		/**
		 * Gets the Repulsion caused by the spring.
		 * 
		 * @return Repulsive force exerted by the spring.
		 */
		public double springRepel() {
			double difference = unextended - distance;
			if (difference > 0) {
				// System.out.println("REPELED");
				return ((difference) * springK);
			} else {
				return 0;
			}

		}

		/**
		 * Gets the Force exerted by the charges.
		 * 
		 * @return Force exerted by the charges.
		 */
		public double charge() {
			return (chargeA * chargeB) / (distance * distance);
		}

		/**
		 * Gets the angle between two particles.
		 * 
		 * @param X
		 *            x position of the First particle.
		 * @param Y
		 *            y position of the First particle.
		 * @param X1
		 *            x position of the second particle.
		 * @param Y1
		 *            y position of the second particle.
		 * @return Angle in radians between the two particles.
		 */
		public double getAngle(int X, int Y, int X1, int Y1) {
			double vert = Math.sqrt((Y - Y1));
			double hori = Math.sqrt((X - X1));
			return (Math.atan((vert / hori)));
		}

		/**
		 * Gets the angle between two particles.
		 * 
		 * @param v
		 *            Position of first particle.
		 * @param u
		 *            Position of second particle.
		 * @return Angle in radians between the two particles.
		 */
		public double getAngle(Vertex v, Vertex u) {
			int X = v.getXloc();
			int Y = v.getYloc();
			int X1 = u.getXloc();
			int Y1 = u.getYloc();
			double vert = Math.sqrt((Y - Y1) * (Y - Y1));
			double hori = Math.sqrt((X - X1) * (X - X1));
			return Math.atan((hori / vert));
		}

		/**
		 * Sets the locations of the two particles.
		 * 
		 * @param v
		 *            First Vertex.
		 * @param u
		 *            Second Vertex.
		 */
		public void setPoint(Vertex v, Vertex u) {
			int x = v.getXloc();
			int y = v.getYloc();
			int x1 = u.getXloc();
			int y1 = u.getYloc();
			this.distance = Math
					.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
		}

		/**
		 * Gets the time interval.
		 * 
		 * @return Time interval.
		 */
		public double getTimeInterval() {
			return timeInterval;
		}

		/**
		 * Sets the time interval.
		 * 
		 * @param timeInterval
		 *            New time interval.
		 */
		public void setTimeInterval(double timeInterval) {
			this.timeInterval = timeInterval;
		}

		/**
		 * Gets the Value of the Spring Constant.
		 * 
		 * @return The spring constant.
		 */
		public double getSpringK() {
			return springK;
		}

		/**
		 * Sets the Spring constant.
		 * 
		 * @param springK
		 *            New Spring constant.
		 */
		public void setSpringK(double springK) {
			this.springK = springK;
		}

		/**
		 * Gets the unextended length of the spring.
		 * 
		 * @return The unextended
		 */
		public double getUnextended() {
			return unextended;
		}

		/**
		 * Sets the unextended length of the Spring.
		 * 
		 * @param unextended
		 *            New unextended length.
		 */
		public void setUnextended(double unextended) {
			this.unextended = unextended;
		}

		/**
		 * Gets the Distance between the current two particles.
		 * 
		 * @return Distance
		 */
		public double getDistance() {
			return distance;
		}

		/**
		 * Sets the Distance between two particles.
		 * 
		 * @param distance
		 *            New Distance.
		 */
		public void setDistance(double distance) {
			this.distance = distance;
		}

		/**
		 * Gets the value of Charge B
		 * 
		 * @return Value of Charge B
		 */
		public double getChargeB() {
			return chargeB;
		}

		/**
		 * Sets the Value of Charge B
		 * 
		 * @param chargeB
		 *            New Value of Charge B
		 */
		public void setChargeB(double chargeB) {
			this.chargeB = chargeB;
		}

		/**
		 * Gets the Value of Charge A
		 * 
		 * @return Value of Charge A
		 */
		public double getChargeA() {
			return chargeA;
		}

		/**
		 * Sets the Value of Charge A
		 * 
		 * @param chargeA
		 *            New Value of Charge A
		 */
		public void setChargeA(double chargeA) {
			this.chargeA = chargeA;
		}

	}

}
