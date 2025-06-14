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

    public Matrix(int rows, int cols) {
        this(rows, cols, new double[rows * cols]);   // all zeros
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
            throw new IllegalArgumentException("Error: This function only works with 4x4 matrix multiplication.");
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

    public Tuple matMultTuple(Tuple t){
     return new Tuple(matrix[0][0] * t.x + matrix[0][1] * t.y + matrix[0][2] * t.z + matrix [0][3] * t.w,
             matrix[1][0] * t.x + matrix[1][1] * t.y + matrix[1][2] * t.z + matrix [1][3] * t.w,
             matrix[2][0] * t.x + matrix[2][1] * t.y + matrix[2][2] * t.z + matrix [2][3] * t.w,
             matrix[3][0] * t.x + matrix[3][1] * t.y + matrix[3][2] * t.z + matrix [3][3] * t.w);
    }

    public static Matrix matTranspose(Matrix m)
    {
        return new Matrix(m.matrix[0].length, m.matrix.length, m.matrix[0][0], m.matrix[1][0], m.matrix[2][0], m.matrix[3][0],
                                                               m.matrix[0][1], m.matrix[1][1], m.matrix[2][1], m.matrix[3][1],
                                                               m.matrix[0][2], m.matrix[1][2], m.matrix[2][2], m.matrix[3][2],
                                                               m.matrix[0][3], m.matrix[1][3], m.matrix[2][3], m.matrix[3][3]);
    }

    public double determinant()
    {
        if(matrix.length == 3 && matrix[0].length == 3) {
            double cofactor1 = Matrix.cofactor(this, 0, 0);
            double cofactor2 = Matrix.cofactor(this, 0, 1);
            double cofactor3 = Matrix.cofactor(this, 0, 2);

            return(matrix[0][0] * cofactor1 + matrix[0][1] * cofactor2 + matrix[0][2] * cofactor3);
        }
        else if(matrix.length == 4 && matrix[0].length == 4) {
            double cofactor1 = Matrix.cofactor(this, 0, 0);
            double cofactor2 = Matrix.cofactor(this, 0, 1);
            double cofactor3 = Matrix.cofactor(this, 0, 2);
            double cofactor4 = Matrix.cofactor(this, 0, 3);

            return(matrix[0][0] * cofactor1 + matrix[0][1] * cofactor2 + matrix[0][2] * cofactor3 + matrix[0][3] * cofactor4);
        }
        return((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
    }

    public static Matrix submatrix(Matrix A, int row, int col){
        Matrix newMatrix = new Matrix(A.matrix.length - 1, A.matrix[0].length - 1);
        for(int i = 0; i < A.matrix.length; i++){
            if(i == row) continue;
            for(int j = 0; j < A.matrix[0].length; j++){
                if(j == col) continue;
                int newRow = (i < row) ? i : i - 1;
                int newCol = (j < col) ? j : j - 1;
                newMatrix.matrix[newRow][newCol] = A.matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static double minor(Matrix A, int row, int col){
     Matrix m = submatrix(A, row, col);
     double minor = m.determinant();
     return minor;
    }

    public static double cofactor(Matrix A, int row, int col){
        int check = row + col;
        double cofactor = (check % 2 == 0) ? Matrix.minor(A, row, col) : -Matrix.minor(A, row, col);
        return cofactor;
    }

    public static Matrix Inverse(Matrix m) {
        double det = m.determinant();
        if (Math.abs(det) < 1e-9) {
            throw new IllegalArgumentException("Matrix is not invertible (det ≈ 0).");
        }

        int n = m.matrix.length;
        Matrix inv = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double cof = Matrix.cofactor(m, i, j);
                inv.matrix[j][i] = cof / det;
            }
        }
        return inv;
    }

    // Transformations

    public static Tuple Translate(Tuple t, double translateX, double translateY, double translateZ) {
        if(t.w == 0) {
            return t;
        }
        Matrix translate = new Matrix(4, 4, 1, 0, 0, translateX,
                0, 1, 0, translateY,
                0, 0, 1, translateZ,
                0, 0, 0, 1);

        return translate.matMultTuple(t);
    }

    public static Tuple TranslateInv(Tuple t, double translateX, double translateY, double translateZ) {
        if(t.w == 0) {
            return t;
        }
        Matrix translate = new Matrix(4, 4, 1, 0, 0, translateX,
                0, 1, 0, translateY,
                0, 0, 1, translateZ,
                0, 0, 0, 1);

        translate = Matrix.Inverse(translate);

        return translate.matMultTuple(t);
    }

    public static Tuple Scale(Tuple t, double scaleX, double scaleY, double scaleZ) {
        Matrix scale = new Matrix(4, 4, scaleX, 0, 0, 0,
                                                                0, scaleY, 0, 0,
                                                                0, 0, scaleZ, 0,
                                                                0, 0, 0, 1);

        return scale.matMultTuple(t);
    }

    public static Tuple ScaleInv(Tuple t, double scaleX, double scaleY, double scaleZ) {
        Matrix scale = new Matrix(4, 4, scaleX, 0, 0, 0,
                                                            0, scaleY, 0, 0,
                                                            0, 0, scaleZ, 0,
                                                            0, 0, 0, 1);
        scale = Matrix.Inverse(scale);

        return scale.matMultTuple(t);
    }

    public static Tuple RotationX(Tuple t, double radians) {
        Matrix rotation = new Matrix(4, 4, 1, 0, 0, 0,
                                                                0, Math.cos(radians), -Math.sin(radians), 0,
                                                                0, Math.sin(radians), Math.cos(radians), 0,
                                                                0, 0, 0, 1);
        return rotation.matMultTuple(t);
    }

    public static Tuple RotationY(Tuple t, double radians) {
        Matrix rotation = new Matrix(4, 4, Math.cos(radians), 0, Math.sin(radians), 0,
                                                                0, 1, 0, 0,
                                                                -Math.sin(radians), 0, Math.cos(radians), 0,
                                                                0, 0, 0, 1);
        return rotation.matMultTuple(t);
    }

    public static Tuple RotationZ(Tuple t, double radians) {
        Matrix rotation = new Matrix(4, 4, Math.cos(radians), -Math.sin(radians), 0, 0,
                                                                Math.sin(radians), Math.cos(radians), 0, 0,
                                                                0, 0, 1, 0,
                                                                0, 0, 0, 1);
        return rotation.matMultTuple(t);
    }
}

