//"C343 / Summer 2019", "Lab 19",
// Ahmed Shahzad ahshahz


import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Scanner;
import java.io.FileReader;

public class AdjGraph implements Graph {

    // is it a directed graph (true or false) :
    private boolean digraph;

    private int totalNodes;
    // all the nodes in the graph:
    private Vector<String> nodeList;

    private int totalEdges;
    // all the adjacency lists, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjList;

    // all the weights of the edges, one for each node in the graph:
    private Vector<LinkedList<Integer>> adjWeight;

    // every visited node:
    private Vector<Boolean> visited;
    // list of nodes pre-visit:
    private Vector<Integer> nodeEnum;

    private int[] distance;

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
        digraph = true;
    }

    // set vertices
    public void setVertices(String[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            nodeList.add(nodes[i]);
            adjList.add(new LinkedList<Integer>());
            adjWeight.add(new LinkedList<Integer>());
            visited.add(false);

            totalNodes++;
        }
    }

    // add a vertex
    public void addVertex(String label) {
        nodeList.add(label);
        visited.add(false);
        adjList.add(new LinkedList<Integer>());
        adjWeight.add(new LinkedList<Integer>());
        totalNodes++;
    }

    public int getNode(String node) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.elementAt(i).equals(node)) return i;
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
        if (adjList.elementAt(v1).contains(v2) == false) {
            tmp.add(v2);
            adjList.set(v1, tmp);
            totalEdges++;
            LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
            tmp2.add(weight);
            adjWeight.set(v1, tmp2);
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
        for (int i = 0; i < nodeList.size(); i++)
            visited.set(i, false);
    }

    public void walk(String method) {
        clearWalk();
        // traverse the graph:
        for (int i = 0; i < nodeList.size(); i++) {
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

    public void relax(int u, int v) {

        if (distance[u] > distance[v] + getWeight(v, u)) {
            distance[u] = distance[v] + getWeight(v, u);
        }
    }

    public int minVertex() {
        int v = 0;
        for (int i = 0; i < totalNodes; i++) {
            if (!ifVisited(i)) {
                v = i;
                break;
            }
        }
        for (int i = 0; i < totalNodes; i++)
            if (!ifVisited(i) && (distance[i] < distance[v]))
                v = i;

        return v;

    }

    public void topological(){
        ArrayList<Integer> sortedArray = new ArrayList<>();
        int[] indegrees = new int[totalNodes];
        LinkedList<Integer> Q = new LinkedList<>();


        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {

                indegrees[adjList.get(i).get(j)]++;

            }
        }

        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                Q.add(i);
            }
        }
        int element = 0;
        while (!Q.isEmpty()) {
            element = Q.removeFirst();
            sortedArray.add(element);
            for (int i = 0; i < adjList.get(element).size(); i++) {
                indegrees[adjList.get(element).get(i)]--;
                if (indegrees[adjList.get(element).get(i)] == 0) {
                    Q.add(adjList.get(element).get(i));
                }
            }

        }
        for (int i = 0; i < sortedArray.size(); i++) {
            System.out.print(" " + nodeList.get(sortedArray.get(i)));
        }

    }


    public int longestPath() {

        //initialize array of sorted
        ArrayList<Integer> sortedArray = new ArrayList<>();

        //indegrees
        int[] indegrees = new int[totalNodes];
        System.out.println(nodeList);
        LinkedList<Integer> Q = new LinkedList<>();

        //every node
        // for all the negibhors node
        // go to indegress for that negibhor and increment one


        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {

                indegrees[adjList.get(i).get(j)]++;

            }
        }

        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                Q.add(i);
            }
        }
        int element = 0;
        System.out.println("length of indegrees" + indegrees.length);
        while (!Q.isEmpty()) {
            element = Q.removeFirst();
            sortedArray.add(element);
            for (int i = 0; i < adjList.get(element).size(); i++) {
                System.out.println(adjList.get(element).get(i));
                System.out.println(Arrays.toString(indegrees));
                indegrees[adjList.get(element).get(i)]--; //outofboundserror
                if (indegrees[adjList.get(element).get(i)] == 0) {
                    Q.add(adjList.get(element).get(i));
                }
            }

        }
        System.out.println("Topological");
        System.out.println();
        for (int i = 0; i < sortedArray.size(); i++) {
            System.out.print(" " + nodeList.get(sortedArray.get(i)));
        }
        System.out.println();
       // int v;
        int weight = 0;
        int maxWeight = 0;
        String node = " ";


        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(i  + "  " + adjList.get(i));
        }

        Vector<LinkedList<Integer>> adjListN = new Vector<LinkedList<Integer>>();
        for (int i = 0; i < adjList.size(); i++) {
            for(int j = 0; j < adjList.get(i).size(); j++) {

            }
        }


            System.out.println("maxweight: " + maxWeight);
            return maxWeight;
        }

        public void DFS ( int v){
            setVisited(v);
            LinkedList<Integer> neighbors = adjList.elementAt(v);
            for (int i = 0; i < neighbors.size(); i++) {
                int v1 = neighbors.get(i);
                if (ifVisited(v1) == false) DFS(v1);
            }
        }

        public void BFS ( int s){
            ArrayList<Integer> toVisit = new ArrayList<Integer>();
            toVisit.add(s);
            while (toVisit.size() > 0) {
                int v = toVisit.remove(0); // first-in, first-visit
                setVisited(v);
                LinkedList<Integer> neighbors = adjList.elementAt(v);
                for (int i = 0; i < neighbors.size(); i++) {
                    int v1 = neighbors.get(i);
                    if ((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
                        toVisit.add(v1);
                    }
                }
            }
        }

        public void display () {
            System.out.println("total nodes: " + totalNodes);
            System.out.println("total edges: " + totalEdges);
        }

        public void displayEnum () {
            for (int i = 0; i < nodeEnum.size(); i++) {
                System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
            }
            System.out.println();
        }

        public static void main (String[]args) throws FileNotFoundException {
            int weight = 1;
            int cost = 0;

            AdjGraph graphOne = new AdjGraph(true);

            FileReader fileOne = new FileReader("src/Locations.txt.txt");

            Scanner fileReadOne = new Scanner(fileOne);


            boolean onFirstLine = true;
            while (fileReadOne.hasNext()) {
                String input = fileReadOne.nextLine();
                //System.out.println(input+ "\n");
                if (onFirstLine) {
                    //param -> split input into string
                    String[] param = input.split(" ");
                    System.out.println(Arrays.toString(param));

                    // n -> number of edges
                    int n = Integer.parseInt(param[0]);

                    String[] nodes = new String[n];

                    for (int i = 0; i < n; i++) {
                        nodes[i] = (i + 1) + "";
                    }

                    graphOne.setVertices(nodes);
                    onFirstLine = false;
                } else {
                    String[] param = input.split(" ");
                    graphOne.setEdge(param[0], param[1], Integer.parseInt(param[2]));
                }
            }
            //int longpath = graphOne.longestPath(); not working
            System.out.println("Toplogical sort");
            graphOne.topological();
           // System.out.println(" " + longpath);
        }


    }


