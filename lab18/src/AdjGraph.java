// C343 Summer 2019

// a simple implementation for graphs with adjacency lists

// lab 18 starter file


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.*;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>>  adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;

    // every visited node:
    private Vector<Boolean> visited;
    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

    int[] dist;

    public AdjGraph() {
        init();
    }

    public AdjGraph(boolean ifdigraph) {
        init();
        digraph = ifdigraph;
    }

    public void init() {
        nodeList = new Vector<String>();
        adjList = new Vector<LinkedList<Integer>>();
        adjWeight = new Vector<LinkedList<Integer>>();
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNodes = totalEdges = 0;
        digraph = false;
    }

    // set vertices
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);
            totalNodes ++;
        }
    }

    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
        totalNodes ++;
    }
    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdges ++;
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1,  tmp2);
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if ((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2:
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well:
            if (digraph == false) {
                setEdge(getNode(v2), getNode(v1), weight);
            }
        }
    }

    // keep track whether a vertex has been visited or not,
    //    for graph traversal purposes:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }


    // new for Lab 18:
    public LinkedList<Integer> getNeighbors(int v) {
        return adjList.get(v);
    }

    public int getWeight(int v, int u) {
        LinkedList<Integer> tmp = getNeighbors(v);
        LinkedList<Integer> weight = adjWeight.get(v);
        if (tmp.contains(u)) {
            return weight.get(tmp.indexOf(u));
        } else {
            return Integer.MAX_VALUE;
        }
    }



    public void clearWalk() {
        nodeEnum.clear();
        for (int i = 0; i < nodeList.size(); i ++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i ++) {
            if (ifVisited(i) == false) {
                if (method.equals("BFS")) BFS(i);      // i is the start node
                else if (method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // Lab 18 TODO:
    //
    // write your methods here.
    //

    public void relax(int u, int v){
        //System.out.println(adjWeight);
        if(dist[u] > dist[v] + getWeight(v, u)){
            dist[u] = dist[v] + getWeight(v, u);
        }
        //nodeList.get(v);
    }

    public int minVertex(){
        int v = 0;
        for(int i = 0; i < nodeList.size(); i++){
            if(!ifVisited(i)){
                v = i;
                break;
            }
        }
        for(int i = 0; i < nodeList.size(); i++){
            if(!ifVisited(i) && dist[i] < dist[v]){
                v = i;
            }
        }
        return v;
    }

    public void Dijkstra(int s){
        dist = new int[totalNodes];
        for(int i = 0; i < nodeList.size(); i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        System.out.println(nodeList.size());
        for(int k = 0; k<nodeList.size(); k++){
            int v = minVertex();
            System.out.println(v);
            setVisited(v);
            if(dist[v] == Integer.MAX_VALUE) break;
            for(int j = 0; j < adjList.get(v).size(); j++){
                relax(adjList.get(v).get(j), v);
            }
        }

        System.out.println(Arrays.toString(dist));

    }



    public void DFS(int v) {
        setVisited(v);
        LinkedList<Integer> neighbors = adjList.elementAt(v);
        for(int i = 0; i < neighbors.size(); i ++) {
            int v1 = neighbors.get(i);
            if(ifVisited(v1) == false) DFS(v1);
        }
    }
    public void BFS(int s) {
        ArrayList<Integer> toVisit = new ArrayList<Integer>();
        toVisit.add(s);
        while(toVisit.size() > 0) {
            int v = toVisit.remove(0); // first-in, first-visit
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for(int i = 0; i < neighbors.size(); i ++) {
                int v1 = neighbors.get(i);
                if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                    toVisit.add(v1);
                }
            }
        }
    }
    public void display() {
        System.out.println("total nodes: " + totalNodes);
        System.out.println("total edges: " + totalEdges);
    }
    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }
    public static void main(String argv[]) {
        AdjGraph g1 = new AdjGraph(true);
        String[] nodes1 = {"A", "B", "C", "D", "E"};
        int weight = 1;
        g1.setVertices(nodes1);
        g1.setEdge("A", "B", weight);
        g1.setEdge("B", "C", weight);
        g1.setEdge("C", "D", weight);
        g1.setEdge("A", "C", weight);
        //g1.relax("A", "B", "C");
        g1.Dijkstra(0);

        //second example: g2 
        AdjGraph g2 = new AdjGraph(true);
        String[] nodes2 = {"a", "b", "c", "d", "e", "f"};
        g2.setVertices(nodes2);
        g2.setEdge("a", "b", 9);
        g2.setEdge("a", "f", 5);
        g2.setEdge("a", "e", 3);
        g2.setEdge("b", "c", 5);
        g2.setEdge("b", "f", 4);
        g2.setEdge("c", "d", 2);
        g2.setEdge("c", "f", 8);
        g2.setEdge("d", "f", 7);
        g2.setEdge("d", "e", 1);
        g2.setEdge("e", "f", 5);
        g2.Dijkstra(0);

        AdjGraph graph1 = new AdjGraph();
        graph1.addVertex("b");
        graph1.addVertex("c");
        graph1.addVertex("d");
        graph1.addVertex("e");
        graph1.addVertex("z");
        graph1.addVertex("g");
        graph1.addVertex("o");
        graph1.addVertex("i");
        graph1.addVertex("h");
        graph1.addVertex("t");
        graph1.addVertex("r");
        graph1.setEdge("e", "d", 2);
        graph1.setEdge("b", "c", 1);
        graph1.setEdge("e", "z", 3);
        graph1.setEdge("s", "g", 6);
        graph1.display();
        graph1.Dijkstra(0);

        AdjGraph graph2 = new AdjGraph();
        graph2.addVertex("b");
        graph2.addVertex("c");
        graph2.addVertex("d");
        graph2.addVertex("e");
        graph2.addVertex("z");
        graph2.addVertex("g");
        graph2.addVertex("o");
        graph2.addVertex("i");
        graph2.addVertex("h");
        graph2.addVertex("t");
        graph2.addVertex("r");
        graph2.setEdge("e", "d", 1);
        graph2.setEdge("b", "c", 5);
        graph2.setEdge("e", "z", 2);
        graph2.setEdge("s", "g", 3);
        graph2.setEdge("r", "t", 1);
        graph2.setEdge("o", "t", 1);
        graph2.setEdge("h", "g", 6);
        graph2.setEdge("c", "g", 9);
        graph2.Dijkstra(0);

        AdjGraph graph3 = new AdjGraph(true);
        graph3.addVertex("J1");
        graph3.addVertex("J2");
        graph3.addVertex("J3");
        graph3.addVertex("J4");
        graph3.addVertex("J5");
        graph3.addVertex("J6");
        graph3.addVertex("J7");
        graph3.setEdge("J1", "J3", 1);
        graph3.setEdge("J1", "J2", 2);
        graph3.setEdge("J2", "J6", 5);
        graph3.setEdge("J2", "J4", 1);
        graph3.setEdge("J5", "J7", 2);
        graph3.setEdge("J4", "J5", 2);
        graph3.setEdge("J3", "J4", 7);
        graph3.setEdge("J2", "J5", 9);
        graph3.Dijkstra(0);
    }


    // Lab 18 TODO:

    // write your new main() method here:

    // provide 3 different examples, using the two different versions of Dijkstra's algorithm
    //   with at least 10 nodes for each different graph





}