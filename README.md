# CS361-Group-Project

## Group Members:
Demondre Davis, Arturo Fernandez, Kyle Nguyen, Nathan Thomas

## Problem statement:
Design a logistics system for a warehouse that needs to manage the movement 
of items. The warehouse is represented as a 2D grid where each cell can 
either be empty, contain an obstacle, or hold an item that needs to be 
collected. The goal is to design a program that can find the shortest path 
from a starting point to collect all items using various graph search 
algorithms. The performance of each algorithm will be compared at the end 


## How to run the program
Running the program starts in the main class. To start the program, you must 
past a text tile to main. In the text file, the first line contains the 
width and height of the 2D grid. The lines after represent how the grid actually
looks like. The grid is represented by three cell types, empty('.'), item('I') 
and an obstacle('O'). Once the program finishes reading in the board, the 
program will apply all the search algorithms implemented and will print out the
path that was traversed, the length of the path and how long it took to find the
path in nanoseconds.

Simply give the absolute path for one of the test cases provided to the program
in order to run it.

For example if simpleGrid is in your "TestFiles" folder, pass "TestFiles/simpleGrid.txt"
as the argument to the program. Please reach out to any of the members if there
are any problems running the program or any confusion on how to start it.


## Algorithms implemented

### Breadth-First Search(BFS)

### Depth-First Search(DFS)

### Dijkstra's Algorithm

### A * Search Algorithm

### Prim's Algorithm : 

