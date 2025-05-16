package com.ulysses.raytracer;

public class Matrix {
    double[][] matrix;

    public Matrix(int rows, int cols, double... values)
    {
        if(values.length != rows * cols) {
            throw new IllegalArgumentException("Error: Number of values not appropriate for given rows and columns.");
        }
        this.matrix = new double[rows][cols];
        for(int i = 0, k = 0; i < rows; i++){
            for(int j = 0; j < cols; j++, k++){
                matrix[i][j] = values[k];
            }
        }
    }

    public double at(int row, int col) { return matrix[row][col]; }
}
