# Graphs

***Authors:** Noa Aizer & Lior Samuel-Levy*

In this project we implemented algorithms for developing a data structure into a directional weighted graph.<br />
The graph contains vertices and edges between each 2 vertices.<br />

In the project we have 5 packages:


## Data Structures:

**Node:**<br />
*The node_data interface is implemented in Node class:*<br />
This class represents the set of operations applicable on a  node (vertex) in a (directional) weighted graph.<br />
Each node has a unique id number and 3D location in the graph. <br />
There are 3 more node fields (weight, info and tag) that were used only during the algorithms. <br />

**Edge:**<br />
*The edge_data interface is implemented in Edge class:*<br />
This class represents the set of operations applicable on a directional edge with source and destination nodes in a (directional) weighted graph.<br />
Each edge has a source and destenation nodes and weight.<br />
The edge weight represents the cost of arrival from the source vertex to the destination vertex.<br />
There are 2 more node fields (info and tag) that were used only during the algorithms. <br />
*There is no option to have a negative edge weight and an edge from a vertex to itself.*


**DGraph:**<br />
*The graph interface is implemented in Dgraph:*<br />
 This class represents a directional weighted graph.<br />
 The class has a road-system or communication network in mind - and support a large number of nodes (over 100,000).<br />

 The nodes and edges are implemented in a data structure - HashMap.<br />
 There are functions for adding / removing nodes and edges, obtaining lists of nodes and edges, <br />
 obtaining the amount of nodes/edges that are in the diagram and obtaining the amount of actions done on the graph (saved as MC).
      
 ## Algorithms:
 
 *The graph_algorihms interface is implemented in Graph_Algo class:*<br />
 The Graph_Algo object contains a graph to activate the algorithms on.
 
 This class represents the Graph Theory algorithms including:
 1. **copy**- Deep copy of the graph.<br />
 2. **init**- Initializes the graph from a file or init this set of algorithms on a given graph .<br />
 3. **save**- Saves the graph to a file.<br />
 4. **isConnected** -Checks whether the graph is strongly connected.<br />
 5. **shortestPathDist**- Calculates the shortest path distance between 2 given nodes. <br />
 6. **shortestPath** - Finds the shortest path (at what edges should we use the path) between 2 given nodes in the graph. <br />
 7. **TSP**- Computes a relatively short path which visit each node in the targets List.

      
 ## Utils:
 Tools to use and create the GUI (Point3D, Range, stdDraw).<br />
 
 **Point3D**- This class represents a 3D point in space, with several methods for 2D, including Point-Line test.<br />
 **Range**- This class represents a simple 1D range of shape [min,max].<br />
 **StdDraw**- This class provides a basic capability for creating drawings in the programs.<br />
 *We used stdDraw class made by Princeton University.*
 
## Tests: 
We have created 2 JUNIT test (using JUNIT 5 version) : DGraph Test and Graph Algo Tests that try to build a graph and test some algorithms on it.


 ## GUI:
 
In our display the vertices are marked as blue dots with the identification numbers next to them, the edge direction is marked as yellow dot and their weights are marked in red.

The graph GUI supports:<br />
**File:** Saveing graph as a picture, loading a graph from a file and saving the graph to a file.<br />
**Algo:** Runs some algorithms on the graph. <br />
*such as- is  the graph connected? , finding the shortest path between 2 vertices and their distance and finding the shortest path between a set of vertices in the graph (TSP).*

**An example for displaying a grpah in the project:**
 
 ![An Example:](https://github.com/NoaAizer/Graphs/blob/master/graph%20example.png)




