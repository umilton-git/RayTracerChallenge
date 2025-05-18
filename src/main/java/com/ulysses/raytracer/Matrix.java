package com.ulysses.raytracer;

public class Matrix {
    private static final double EPS = 1e-9;   // tolerance for element comparison

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

    @Override
    public boolean equals(Object comp){
        if (this == comp) return true;
        if(!(comp instanceof Matrix other)) return false;

        if(matrix.length != other.matrix.length || matrix[0].length != other.matrix[0].length){
            return false;
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(Math.abs(matrix[i][j] - other.matrix[i][j]) > EPS){
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix matMultiply(Matrix m){
        if(matrix.length != 4 || matrix[0].length != 4 || m.matrix.length != 4 || m.matrix[0].length != 4) {
            throw new IllegalArgumentException("Error: This project only works with 4x4 matrix multiplication.");
        }

        Matrix result = new Matrix(4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                result.matrix[i][j] = matrix[i][0] * m.matrix[0][j] +
                                      matrix[i][1] * m.matrix[1][j] +
                                      matrix[i][2] * m.matrix[2][j] +
                                      matrix[i][3] * m.matrix[3][j];
            }
        }
        return result;
    }
}
