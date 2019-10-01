// C343 Summer 2019
//
// a simple implementation for graphs with adjacency lists

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {
    private boolean digraph;
    private int totalNode;
    private Vector<String> nodeList;
    private int totalEdge;
    private Vector<LinkedList<Integer>>  adjList;     // adjacency list
    private Vector<Boolean> visited;
    private Vector<Integer> nodeEnum;                 // list of nodes pre visit

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
        visited = new Vector<Boolean>();
        nodeEnum = new Vector<Integer>();
        totalNode = totalEdge = 0;
        digraph = false;
    }

    // set vertices:
    public void setVertices(String[] nodes) {
        for(int i = 0; i < nodes.length; i ++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            visited.add(false);
            totalNode ++;
        }
    }

    // add a vertex:
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        totalNode ++;
    }

    public int getNode(String node) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.elementAt(i).equals(node)) return i;
        }
        return -1;
    }

    // return the number of vertices:
    public int length() {
        return nodeList.size();
    }

    // add edge from v1 to v2:
    public void setEdge(int v1, int v2, int weight) {
        LinkedList<Integer> tmp = adjList.elementAt(v1);
        if(adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1,  tmp);
            totalEdge ++;
        }
    }

    public void setEdge(String v1, String v2, int weight) {
        if((getNode(v1) != -1) && (getNode(v2) != -1)) {
            // add edge from v1 to v2
            setEdge(getNode(v1), getNode(v2), weight);
            // for undirected graphs, add edge from v2 to v1 as well
            if (digraph == false) setEdge(getNode(v2), getNode(v1), weight);
        }
    }

    // keep track if a vertex is visited or not, used e.g. for traversal:
    public void setVisited(int v) {
        visited.set(v, true);
        nodeEnum.add(v);
    }

    public boolean ifVisited(int v) {
        return visited.get(v);
    }

    public void clearWalk() {
        // clean up before traversing:
        nodeEnum.clear();
        for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph 
        for(int i = 0; i < nodeList.size(); i ++) {
            if(ifVisited(i) == false) {
                if(method.equals("BFS")) BFS(i);      // i is the start node
                else if(method.equals("DFS")) DFS(i); // i is the start node
                else {
                    System.out.println("unrecognized traversal order: " + method);
                    System.exit(0);
                }
            }
        }
        System.out.println(method + ":");
        displayEnum();
    }

    // Lab 15 TODO:
    //
    // write your componentsAndSizes() method here.
    //
    public void componentsAndSizes(){
        this.walk("BFS");

        ArrayList<Integer> componentsSize = new ArrayList<Integer>();
        int size = 1;
        for(int i = 1; i < nodeEnum.size(); i++){
            if(adjList.get(nodeEnum.get(i)).contains(nodeEnum.get(i - 1))){
                size++;
            }
            else{
                componentsSize.add(size);
                size = 1;
            }
        }
        componentsSize.add(size);
        for(int i = 0; i < componentsSize.size(); i++){
            System.out.printf("Component %d  has a size of %d\n", i, componentsSize.get(i));
        }
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
        System.out.println("total nodes: " + totalNode);
        System.out.println("total edges: " + totalEdge);
    }
    public void displayEnum() {
        for(int i = 0; i < nodeEnum.size(); i ++) {
            System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
        }
        System.out.println();
    }

    public void numberOfEdges(){
        int[][] vertMatrix = new int[totalNode][totalNode];

        for(int i = 0; i < totalNode; i++){
            for(int j = 0; j < totalNode; j++){
                if(j == i){
                    vertMatrix[i][j] = 0;
                }
                else{
                    vertMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        //System.out.println(adjList);
        for(int i = 0; i < vertMatrix.length; i++) {
            System.out.println(Arrays.toString(vertMatrix[i]));
        }

        System.out.println(adjList);
        System.out.println("\n\n");
        for(int i = 0; i < adjList.size(); i++){
            for(int j = 0; j < adjList.get(i).size(); j++){
                vertMatrix[i][adjList.get(i).get(j)] = 1;
            }
        }

        for(int i = 0; i < vertMatrix.length; i++) {
            System.out.println(Arrays.toString(vertMatrix[i]));
        }
        for(int i = 0; i < totalNode; i++){
            for(int j = 0; j < totalNode; j++){
                for(int k = 0; k < totalNode; k++){
                    if(vertMatrix[i][k] != Integer.MAX_VALUE && vertMatrix[k][j] != Integer.MAX_VALUE && vertMatrix[i][j] > vertMatrix[j][k] + vertMatrix[k][j]){
                        vertMatrix[i][j] = vertMatrix[i][k] + vertMatrix[k][j];
                    }
                }
            }
        }
        for(int i = 0; i < vertMatrix.length; i++) {
            System.out.println(Arrays.toString(vertMatrix[i]));
        }
    }

    // Lab 15 TODO:

    // write a main() method here:

    public static void main(String[] args){
        AdjGraph graph = new AdjGraph();
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("z");
        graph.setEdge("b", "c", 0);
        graph.setEdge("d", "e", 0);
        graph.setEdge("b", "e", 0);
        /*graph.addVertex("g");
        graph.addVertex("o");
        graph.addVertex("i");
        graph.addVertex("h");
        graph.addVertex("t");
        graph.addVertex("r");*/
        /*graph.setEdge("e", "d", 0);
        graph.setEdge("b", "c", 0);
        graph.setEdge("r", "i", 0);*/
        graph.display();
        graph.componentsAndSizes();
        graph.numberOfEdges();


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
        graph1.setEdge("e", "d", 0);
        graph1.setEdge("b", "c", 0);
        graph1.setEdge("e", "z", 0);
        graph1.setEdge("s", "g", 0);
        graph1.display();
        graph1.componentsAndSizes();
        graph1.numberOfEdges();


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
        graph2.setEdge("e", "d", 0);
        graph2.setEdge("b", "c", 0);
        graph2.setEdge("e", "z", 0);
        graph2.setEdge("s", "g", 0);
        graph2.setEdge("r", "t", 0);
        graph2.setEdge("o", "t", 0);
        graph2.setEdge("h", "g", 0);
        graph2.setEdge("c", "g", 0);
        graph2.display();
        graph2.componentsAndSizes();
        graph2.numberOfEdges();




        //11.4 TEST!!!!!!!!!!! FOO U SEE THIS
        AdjGraph graph3 = new AdjGraph(true);
        graph3.addVertex("J1");
        graph3.addVertex("J2");
        graph3.addVertex("J3");
        graph3.addVertex("J4");
        graph3.addVertex("J5");
        graph3.addVertex("J6");
        graph3.addVertex("J7");
        graph3.setEdge("J1", "J3", 0);
        graph3.setEdge("J1", "J2", 0);
        graph3.setEdge("J2", "J6", 0);
        graph3.setEdge("J2", "J4", 0);
        graph3.setEdge("J5", "J7", 0);
        graph3.setEdge("J4", "J5", 0);
        graph3.setEdge("J3", "J4", 0);
        graph3.setEdge("J2", "J5", 0);
        graph3.numberOfEdges();

    }

    // 1) instantiate a new graph,
    // 2) assign2 vertices and edges to the graph,
    // 3) then display2 the graph's content (use display() )
    // 4) finally call your componentsAndSizes() method to provide
    //    output results as from Lab 15 instructions

    // provide 3 different examples,
    //   with at least 10 nodes for each different graph

} // end of class AdjGraph