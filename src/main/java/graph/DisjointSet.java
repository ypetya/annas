package annas.graph;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DisjointSet<T> {

	private Map<T, T> parent = new TreeMap<T, T>();
	
	private final Lock lock = new ReentrantLock();

	public void makeSet(T x) {
		lock.lock();
		try {
			parent.put(x, x);
		} finally {
			lock.unlock();
		}
	}

	public void union(T x, T y) {
		lock.lock();
		try {
			for (T z : parent.keySet()) {
				if (findSet(z).equals(findSet(y))) {
					parent.put(z, findSet(x));
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public T findSet(T x) {
		if (parent.get(x).equals(x)) {
			return x;
		} else {
			return findSet(parent.get(x));
		}
	}

	public Map<T, T> getMap() {
		return parent;
	}

}