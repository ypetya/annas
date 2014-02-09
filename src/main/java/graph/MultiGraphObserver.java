/**
 * 
 */
package annas.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * GraphObserver which supports multiple graph observers
 * 
 * @author sam
 * 
 */
public class MultiGraphObserver implements GraphObserver {

	private boolean async = false;

	private ArrayList<GraphObserver> observers;

	/**
	 * Raises the event on all graph observers registered with this graph
	 * observer.
	 * 
	 * if async is set to false, the event is raised in the order which the item
	 * where added to the ArrayList, the order can be assumed to run from index
	 * 0...N.
	 * 
	 * if async is set to true, the each event is executed in its own thread and
	 * therefore is under the control of the scheduler, no order can be assumed
	 * for the execution sequence.
	 */
	@Override
	public void update(GraphEvent e) {
		if (!async) {

			for (GraphObserver gs : this.observers) {
				gs.update(e);
			}

		} else {

			for (GraphObserver gs : this.observers) {
				new Thread(new runner(gs, e)).start();
			}
		}

	}

	class runner implements Runnable {

		private GraphObserver gs;

		private GraphEvent e;

		public runner(GraphObserver gs, GraphEvent e) {
			super();
			this.gs = gs;
			this.e = e;

		}

		@Override
		public void run() {
			this.gs.update(e);

		}

	}

	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean add(GraphObserver e) {
		return observers.add(e);
	}

	/**
	 * @param index
	 * @param element
	 * @see java.util.ArrayList#add(int, java.lang.Object)
	 */
	public void add(int index, GraphObserver element) {
		observers.add(index, element);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.ArrayList#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends GraphObserver> c) {
		return observers.addAll(c);
	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.ArrayList#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends GraphObserver> c) {
		return observers.addAll(index, c);
	}

	/**
	 * 
	 * @see java.util.ArrayList#clear()
	 */
	public void clear() {
		observers.clear();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return observers.contains(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Collection<?> c) {
		return observers.containsAll(c);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#get(int)
	 */
	public GraphObserver get(int index) {
		return observers.get(index);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#indexOf(java.lang.Object)
	 */
	public int indexOf(Object o) {
		return observers.indexOf(o);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#isEmpty()
	 */
	public boolean isEmpty() {
		return observers.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.AbstractList#iterator()
	 */
	public Iterator<GraphObserver> iterator() {
		return observers.iterator();
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf(Object o) {
		return observers.lastIndexOf(o);
	}

	/**
	 * @return
	 * @see java.util.AbstractList#listIterator()
	 */
	public ListIterator<GraphObserver> listIterator() {
		return observers.listIterator();
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.AbstractList#listIterator(int)
	 */
	public ListIterator<GraphObserver> listIterator(int index) {
		return observers.listIterator(index);
	}

	/**
	 * @param index
	 * @return
	 * @see java.util.ArrayList#remove(int)
	 */
	public GraphObserver remove(int index) {
		return observers.remove(index);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Object o) {
		return observers.remove(o);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Collection<?> c) {
		return observers.removeAll(c);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.AbstractCollection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Collection<?> c) {
		return observers.retainAll(c);
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.ArrayList#set(int, java.lang.Object)
	 */
	public GraphObserver set(int index, GraphObserver element) {
		return observers.set(index, element);
	}

	/**
	 * @return
	 * @see java.util.ArrayList#size()
	 */
	public int size() {
		return observers.size();
	}

	/**
	 * @return
	 * @see java.util.ArrayList#toArray()
	 */
	public Object[] toArray() {
		return observers.toArray();
	}

	/**
	 * @param <T>
	 * @param a
	 * @return
	 * @see java.util.ArrayList#toArray(T[])
	 */
	public <T> T[] toArray(T[] a) {
		return observers.toArray(a);
	}
}
