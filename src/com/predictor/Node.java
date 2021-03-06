package com.predictor;

import java.util.ArrayList;

public class Node {

    protected final ArrayList<FunctionNode> parents = new ArrayList<>();
    protected Vec[] m;

    // Default constructor for a node representing
    // a matrix containing one row and column.
    public Node() {
        m = new Vec[]{new Vec()};
    }

    // Constructor for a node representing a matrix
    // containing some number of rows and columns.
    public Node(int numRows, int numCols) {
        assert numRows > 0: "Nodes must have a positive number of columns.";
        assert numCols > 0: "Nodes must have a positive number of rows.";

        m = new Vec[numRows];

        for (int i = 0; i < numRows; i++) {
            m[i] = new Vec(numCols);
        }
    }

    // Adds a parent node to this node.
    public void addParent(FunctionNode n) {
        if ((ActFn.isValidActFn(n.getFn()) && n.getChildren().size() < 1)
                || (LossFn.isValidLossFn(n.getFn()) && n.getChildren().size() < 2)
                || (n.getFn().equals("dot") && n.getChildren().size() < 2)
                || n.getFn().equals("add")) {
            parents.add(n);
            n.children.add(this);
        }
    }

    // Returns the value at some column and row of this node's matrix.
    public double get(int row, int col) {
        return m[row].get(col);
    }

    // Returns the specified column of this node's matrix.
    public Vec getCol(int col) {
        Vec column = new Vec(numRows());

        for (int i = 0; i < numRows(); i++) {
            column.set(i, m[i].get(col));
        }

        return column;
    }

    // Returns the matrix this node represents.
    public Vec[] getMatrix() {
        Vec[] mCopy = new Vec[numRows()];

        for (int i = 0; i < numRows(); i++) {
            mCopy[i] = new Vec(numCols());

            for (int j = 0; j < numCols(); j++) {
                mCopy[i].set(j, m[i].get(j));
            }
        }

        return mCopy;
    }

    // Returns a copy of the list of parents of this node.
    public ArrayList<FunctionNode> getParents() {
        return new ArrayList<>(parents);
    }

    // Returns the specified row of this node's matrix.
    public Vec getRow(int row) {
        return m[row].copy();
    }

    // Returns whether the node has the same dimensions as this node.
    public boolean hasEqualDims(Node n) {
        return hasEqualNumRows(n) && hasEqualNumCols(n);
    }

    // Returns whether the node has the same number of columns as this node.
    public boolean hasEqualNumCols(Node n) {
        return numCols() == n.numCols();
    }

    // Returns whether the node has the same number of columns as this node.
    public boolean hasEqualNumRows(Node n) {
        return numRows() == n.numRows();
    }

    // Returns whether this node is a child of the node or not.
    public boolean isChildOf(FunctionNode n) {
        return n.getChildren().contains(this);
    }

    // Returns the number of columns of this node's matrix.
    public int numCols() {
        return m[0].length();
    }

    // Returns the number of rows of this node's matrix.
    public int numRows() {
        return m.length;
    }

    // Sets the value at some column and row of this node's matrix.
    public void set(int row, int col, double value) {
        m[row].set(col, value);
    }
}
