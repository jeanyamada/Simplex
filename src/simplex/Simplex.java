/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplex;

/**
 *
 * @author jean
 */
public class Simplex {

    /**
     * @param args the command line arguments
     */
    public static Simplex simplex;

    public static void main(String[] args) {
        // TODO code application logic here
        double[][] matrix1 = {{1.0, -4.0, -3.0, 0.0, 0.0, 0.0},
        {0.0, -1.0, 1.0, 1.0, 0.0, 3.0},
        {0.0, 4.0, 1.0, 0.0, 1.0, 8.0}};

        double[][] matrix3 = {{1.0, -6.0, -3.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 2.0, 4.0, 1.0, 0.0, 0.0, 720.0},
        {0.0, 4.0, 4.0, 0.0, 1.0, 0.0, 880.0},
        {0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 160.0}};

        double[][] matrix4 = {{1.0, -2.0, -3.0, 0.0, 0.0, 0.0},
        {0.0, -4.0, 2.0, 1.0, 0.0, 1.0},
        {0.0, -1.0, 2.0, 0.0, 1.0, 6.0}};

        double[][] matrix2 = {{1.0, -4.0, 1.0, 30.0, -11.0, -2.0, 3.0, 0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, -2.0, 0.0, 6.0, 2.0, 0.0, -3.0, 1.0, 1.0, 0.0, 0.0, 20.0},
        {0.0, -4.0, 1.0, 7.0, 1.0, 0.0, -1.0, 0.0, 0.0, 1.0, 0.0, 10.0},
        {0.0, 0.0, 0.0, -5.0, 3.0, 1.0, -1.0, 0.0, 0.0, 0.0, 1.0, 60.0}};

        double[][] matrix5 = {
            {1, -8, -10, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 2, -1, 0, 0, 0, 1, 0, 2},
            {0, 1, 0, 0, -1, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 3},
            {0, 4, 5, 0, 0, 0, 1, 0, 0, 20},
            {1, -2, -2, 1, 1, 0, 0, 0, 0, -3}};

        double[][] matrixDual = {{1, 0, 0, -0.5, -3, 0, 0, 13},
        {0, 0, 1, -0.5, 0.5, 0, 0, 0.5},
        {0, 1, 0, 0, -1, 0, 0, 1},
        {0, 0, 0, 0, 1, 1, 0, 2},
        {0, 0, 0, 2.5, 1.5, 0, 1, 13.5}};
        simplex = new Simplex();
        simplex.printMatrix(matrix5);
        simplex.simplex(matrixDual, 0);

    }

    public int linhaSai(double[][] matrix, int indexEntra, int indexPrincipal) {
        double menor = Double.MAX_VALUE;
        int indexSai = -1;

        for (int i = 0; i < matrix.length; i++) {
            if (indexPrincipal != i) {
                int indexBase = matrix[i].length - 1;
                double value;
                if (matrix[i][indexEntra] != 0) {
                    value = matrix[i][indexBase] / matrix[i][indexEntra];
                    if (value > 0) {
                        if (menor > value) {
                            menor = value;
                            indexSai = i;
                        }
                    }
                }
            }

        }
        return indexSai;
    }

    public void simplex(double[][] matrix, int indexPrincipal) {
        int indexEntra;

        while ((indexEntra = colunaEntra(matrix, indexPrincipal)) >= 0) {

            int indexSai = linhaSai(matrix, indexEntra, indexPrincipal);

            if (indexSai == -1) {
                return;
            }

            double[] vetor = matrix[indexSai];

            double value = vetor[indexEntra];
            for (int i = 0; i < vetor.length; i++) {
                vetor[i] = vetor[i] / value;
                System.out.print("| " + vetor[i] + " | ");
            }

            System.out.println();

            System.out.println("coluna entra: " + indexEntra + " linha sai: " + indexSai);

            for (int i = 0; i < matrix.length; i++) {
                value = -matrix[i][indexEntra];
                for (int j = 0; j < matrix[i].length; j++) {
                    if (i != indexSai) {

                        matrix[i][j] = value * vetor[j] + matrix[i][j];
                    }
                }
            }
            printMatrix(matrix);
        }
    }

    public int colunaEntra(double[][] matrix, int index) {
        double menor = Double.MAX_VALUE;
        int indexEntra = -1;
        for (int i = 1; i < matrix[index].length - 1; i++) {
            if (matrix[index][i] < 0) {
                if (matrix[index][i] < menor) {
                    menor = matrix[index][i];
                    indexEntra = i;
                }
            }
        }

        return indexEntra;

    }

    public void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("| " + matrix[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }
}
