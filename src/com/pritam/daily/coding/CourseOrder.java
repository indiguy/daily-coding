/**
 *
 */
package com.pritam.daily.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

/**
 * We're given a hashmap associating each courseId key with a list of courseIds
 * values, which represents that the prerequisites of courseId are courseIds.
 * Return a sorted ordering of courses such that we can finish all courses.
 *
 * Return null if there is no such ordering.
 *
 * For example, given {'CSC300': ['CSC100', 'CSC200'], 'CSC200': ['CSC100'],
 * 'CSC100': []}, should return ['CSC100', 'CSC200', 'CSCS300'].
 *
 * @author pribiswas
 *
 */
public class CourseOrder {

	private static class Node<T> {
		private final T data;
		private List<Node<T>> neighbours = new ArrayList<>();
		private int incomingEdges;

		/**
		 * @param data
		 */
		public Node(T data) {
			this.data = data;
		}

		public void addEdge(Node<T> neighbour) {
			neighbours.add(neighbour);
			neighbour.addAnIncomingEdge();
		}

		public void removeEdges() {
			for (Node<T> neighbour : neighbours) {
				neighbour.removeIncomingEdge();
			}
		}

		private void addAnIncomingEdge() {
			incomingEdges++;
		}

		private void removeIncomingEdge() {
			incomingEdges--;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * @return the incomingEdges
		 */
		public int getIncomingEdges() {
			return incomingEdges;
		}

		/**
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			return result;
		}

		/**
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Node other = (Node) obj;
			if (data == null) {
				if (other.data != null) {
					return false;
				}
			} else if (!data.equals(other.data)) {
				return false;
			}
			return true;
		}

	}

	private static class Graph<T> {
		private Set<Node<T>> vertices = new HashSet<>();

		public void add(Node<T> vertex) {
			vertices.add(vertex);
		}

		public void remove(Node<T> vertex) {
			vertices.remove(vertex);
		}

		/**
		 * @return the vertices
		 */
		public Set<Node<T>> getVertices() {
			return vertices;
		}

		public int size() {
			return vertices.size();
		}

	}

	private final Graph<String> graph;

	/**
	 * @param courses
	 */
	public CourseOrder(Map<String, List<String>> courses) {
		this.graph = new Graph<>();
		for (Entry<String, List<String>> entry : courses.entrySet()) {
			Node<String> course = new Node<>(entry.getKey());
			for (String value : entry.getValue()) {
				Node<String> prerequisite = new Node<>(value);
				graph.add(prerequisite);
				prerequisite.addEdge(course);
			}
			graph.add(course);
		}
	}

	public List<String> findOrder() {
		List<String> ordering = new LinkedList<>();
		int noOfVertices = graph.size();
		while (ordering.size() != noOfVertices) {
			Optional<Node<String>> noDependency = graph.getVertices().stream().filter(v -> v.getIncomingEdges() == 0)
					.findFirst();
			if (!noDependency.isPresent()) {
				return null;
			}
			Node<String> nonDependent = noDependency.get();
			ordering.add(nonDependent.getData());
			nonDependent.removeEdges();
			graph.remove(nonDependent);
		}
		return ordering;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, List<String>> courses = new HashMap<>();
		courses.put("CSC300", Arrays.asList("CSC100", "CSC200"));
		courses.put("CSC200", Arrays.asList("CSC100"));

		CourseOrder solution = new CourseOrder(courses);
		System.out.println(solution.findOrder());
	}

}
