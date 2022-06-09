///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2370: Introduction to Algorithms and Data Structures
//Project info: Homework 5 - Modified Dijkstra’s Algorithm 
///////////////////////////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class HW5 {

	static int totalWeight;
	
	public static Map<Node, Integer> dijkstra(Node[] g, Node s) {

		Set<Node>  result = new HashSet<Node>();
		Set<Node> visited= new HashSet<Node>();
		MinHeap pq = new MinHeap();
		pq.setSize(g.length);
		s.u=s;
		result.add(s);
		pq.insertNewValue(new AbstractMap.SimpleEntry<Node, Double>(s, s.cost));
		for(Node node : g) {		//setting the distance to all other nodes to the sum of all the weights
			if(node!=s) {
				node.cost=totalWeight;
			}
		}
		while(!pq.isEmpty()) {
			Entry<Node, Double> u = pq.poll();
			Node newSmallest=u.getKey().u;
			if (visited.contains(newSmallest)) {
				continue;
			}
			visited.add(newSmallest);
			if(newSmallest!=null) {	
				for (Entry<Node, Double>  neiNodes : newSmallest.getNeighbours().entrySet()) {
					Node uKey = neiNodes.getKey();
					if(visited.contains(neiNodes.getKey())) {
						continue;
					}
					relaxEdge(newSmallest,uKey);
					Node newNode = new Node(uKey);
					newNode.setMinCost(newSmallest.minCost + neiNodes.getValue());
					newNode.setParent(newSmallest);
					pq.insertNewValue(new AbstractMap.SimpleEntry<Node, Double>(newNode, newNode.minCost));
					result.add(uKey);
				}
			}

		}
		System.out.println();	//printing the results from the collected result of the function instead of returning it
		System.out.println("Distance to ("+s.name+ ") is:"+s.cost+", parent is  null because it's the Chosen source");
		for (Node  r : result) {
			if(r!=s && r!=null) {
				System.out.println("Distance to ("+r.name+ ") is:"+r.cost+", the parent is ("+r.parent.name+")");					
			}
		}
		System.out.println();
		return null;
	}
	public static double relaxEdge(Node a,Node b) {		//comparing and relaxing two edges
		if(a.cost+a.neighbours.get(b)<b.cost) {
			b.cost=a.cost+a.neighbours.get(b);
			b.setParent(a);
			return b.cost;
		}
		return b.cost;
	}
	
	public static StringBuilder getParents(Node pre,StringBuilder str) {	//recursive method that gets the parent until it reaches the source
		if(pre.parent!=null) {												//node, used to get the full path of the chosen node
			str.append(pre.parent.name+" < ");
			getParents(pre.parent,str);	
		}
		return str;
	}
	private static String[] getArrayFromFile(String fileName) {			//gets a string of the file name and makes a string array of all the lines
																		//of that file
		ArrayList<String> al = new ArrayList<String>();
		File file2 = new File(fileName);
		try {
			Scanner fileIn2 = new Scanner(file2);	

			while (fileIn2.hasNextLine()) {
				al.add(fileIn2.nextLine());			
			}
			fileIn2.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		String[] lines= new String[al.size()];
		for(int i=0;i<al.size();i++) {
			System.out.println(al.get(i));
			lines[i] = al.get(i);  
			lines[i] = lines[i].trim().replaceAll(" +", " ");
		}
		return lines;
	}

	public static Node findNodeFromName(Node[] nodes,String n) {	//used to get a node from it's name(string)
		for(int i=0; i<nodes.length;i++) {
			if(nodes[i].name.equalsIgnoreCase(n)) {
				return nodes[i];
			}
		}
		return null;
	}
	public static String[] filePick(String[] allFiles,String pick) {	//loads the selected file
		try {  
			int toInt=Integer.parseInt(pick); 
			switch(toInt) {
			case 0:
				return getArrayFromFile(allFiles[0]);

			case 1:
				return getArrayFromFile(allFiles[1]);

			case 2:
				return getArrayFromFile(allFiles[2]);

			case 3:
				return getArrayFromFile(allFiles[3]);

			case 4:
				return getArrayFromFile(allFiles[4]);

			case 5:
				return getArrayFromFile(allFiles[5]);

			case 6:
				return getArrayFromFile(allFiles[6]);

			case 7:
				return getArrayFromFile(allFiles[7]);

			case 8:
				return getArrayFromFile(allFiles[8]);
			}

			return null;
		} catch(NumberFormatException e){  
			return null;  
		}  

	}
	public static Node[] makeGraph(String[] fileLines) {			//takes the lines of the file and creates a graph out of it
		Node[] gResult = new Node[fileLines.length];
		int[] firstSpaceIndices=new int[fileLines.length];
		for(int i=0; i<fileLines.length;i++) {
			if(fileLines[i].contains(" ")) {						// creates the nodes from first charecter of each line to the space
				firstSpaceIndices[i]=fileLines[i].indexOf(" ");		//and it registers the first space location of each line if the node has neighbors
			}
			else {
				firstSpaceIndices[i]=fileLines[i].length();
			}
			String nodes =  fileLines[i].substring(0,firstSpaceIndices[i] );
			gResult[i]= new Node(nodes);
		}
		for(int i=0; i<fileLines.length;i++) {			
			if(fileLines[i].contains(" ")) { //if it has space/neighbors, Add them.
				String neighborsString=fileLines[i].substring(firstSpaceIndices[i]+1);
				while(neighborsString.contains(" ")) {
					String nodeNM=neighborsString.substring(0,neighborsString.indexOf(" "));
					String nodeV ="";
					Node nodeFromName=findNodeFromName(gResult,nodeNM);
					neighborsString=neighborsString.substring(nodeNM.length()+1);

					if(neighborsString.contains(" ")) {
						nodeV =  neighborsString.substring(0, neighborsString.indexOf(" "));
						neighborsString=neighborsString.substring(nodeV.length()+1);
					}
					else {
						nodeV =  neighborsString;
					}
					gResult[i].addNeighbour( nodeFromName, Double.valueOf(nodeV));				
				}
			}
	
		}
		return gResult;
	}
	public static void startTheProgram() {		//runs the functions and used to start again if the user wants to try a different file
		Scanner myObj = new Scanner(System.in);
		String[] filesArray= {"graph24-6.txt","graph-lotr.txt","WarAndPeace.txt_719_1420_graph.txt","WarAndPeace.txt_1171_2840_graph.txt",
				"WarAndPeace.txt_1906_5680_graph.txt","WarAndPeace.txt_2993_11346_graph.txt","WarAndPeace.txt_4824_22692_graph.txt",
				"WarAndPeace.txt_7708_45377_graph.txt","WarAndPeace.txt_11765_90758_graph.txt"};

		System.out.println("Please pick a file by typing it's number. ");
		for(int i=0; i<filesArray.length;i++) {
			System.out.println(i+ "-"+filesArray[i]);
		}
		String[] fileChoice = filePick(filesArray,myObj.nextLine());
		while(fileChoice==null) {
			System.out.println("Please pick a number from the options above. ");
			fileChoice = filePick(filesArray,myObj.nextLine());
		}
		Node[] graph= makeGraph(fileChoice);	
		System.out.println();
		System.out.println("Pick a starting vertex from the options above. ");
		String firstChoice = myObj.nextLine();
		while(findNodeFromName(graph,firstChoice)==null) {
			System.out.println("Please pick a valid vertex. ");
			firstChoice = myObj.nextLine();
		}
		System.out.println("The optimal distance for all vertices from the source: ");
		dijkstra(graph,findNodeFromName(graph,firstChoice));
		System.out.println("Choose a vertex you want to see the full path of. ");
		String secondChoice=myObj.nextLine();
		while(findNodeFromName(graph,secondChoice)==null) {
			System.out.println("Please pick a valid vertex. ");
			secondChoice = myObj.nextLine();
		}
		StringBuilder fullPathToNode = new StringBuilder(); 
		fullPathToNode.append(secondChoice+" < ");
		fullPathToNode=getParents(findNodeFromName(graph,secondChoice),fullPathToNode);
		System.out.println("The full path between "+firstChoice+" and "+secondChoice+" is:");
		
		System.out.println(fullPathToNode.substring(0, fullPathToNode.length()-2)); 	//printing without the extra "<" character.
//		System.out.println(fullPathToNode);
		System.out.println("Type 'r' if you want to try a different file or anything else to exit");
		if(myObj.nextLine().equalsIgnoreCase("r")) {
			startTheProgram();
		}
	}
	public static void main(String[] args) 
	{		
		startTheProgram();
	}
	
	
	public static class MinHeap{ ///////////////////////////////////////////////////////////////////////////// minHeap inner class
		int size;
		double[] arr;
		Node[] nodes;
		public void insertNewValue(Entry<Node, Double> node) { //the function that adds to the heap
			try {
				arr[size] = node.getValue();
				nodes[size]=node.getKey();
			}
			catch(ArrayIndexOutOfBoundsException exception) {
				size=size-1;
				arr[size] = node.getValue();
				nodes[size]=node.getKey();
			}
			int index = size;
			int parent = (index - 1)/2;
			while(parent >= 0 && arr[parent] > arr[index]) {
				double t = arr[parent];
				Node tn=nodes[parent];
				arr[parent] = arr[index];
				nodes[parent]=nodes[index];
				arr[index] = t;
				nodes[index]=tn;
				t = parent;
				index = parent;
				parent = (int)(t - 1) / 2;
			}
			size++;
		}

		public  Entry<Node, Double> poll(){	//extracting min from the heap
			Double min = arr[0];
			Node mint=nodes[0];
			arr[0]=arr[size-1];
			nodes[0]=nodes[size-1];
			size = size - 1;
			minHeapify(0);
			return new AbstractMap.SimpleEntry<Node, Double>(mint, min);
		}
		public void setSize(int size) {		//setting the size of the heap depending on the file
			this.arr = new double[size];
			this.nodes= new Node[size];

		}
		public void minHeapify(int index) {
			int l= 2*index+1;
			int r= 2*index+2;
			int smallest=index;
			if(l<this.size && arr[l] < arr[smallest]) {
				smallest=l;
			}
			if(r<this.size && arr[r] < arr[smallest]) {
				smallest=r;
			}
			if(smallest != index) {
				Double t=arr[smallest];
				Node tn=nodes[smallest];
				arr[smallest]=arr[index];
				nodes[smallest]=nodes[index];
				arr[index]=t;
				nodes[index]=tn;
				minHeapify(smallest);
			}
		}
		public boolean isEmpty() {
			if(size<1) {
				return true;
			}
			return false;
		}

	}
	static class Node {		///////////////////////////////////////////////////////////////////////////// Node inner class
		private  Map<Node, Double> neighbours;
		private Node parent;
		private double cost;
		private double minCost;
		private Node u;
		private String name;
		public Node(Node node) {
			this.u=node;
		}
		public Node(String name) {
			this.name=name;
			neighbours = new HashMap<>();

		}
		public void addNeighbour(Node node, Double weight) {
			neighbours.put(node, weight);
			totalWeight+=weight;
		}
		public Map<Node, Double> getNeighbours() {
			return this.neighbours;
		}
		public void setMinCost(double c) {
			minCost =c;
		}
		public void setParent(Node pre) {
			this.parent = pre;
		}
	}
}
