import java.util.*;


public class Main {

    public static void main(String[] args) {
	int[][] sudoku={
            {7,8,4, 1,5,9, 3,2,6},
            {5,3,9, 6,7,2, 8,4,1},
            {6,1,2, 4,3,8, 7,5,9},

            {9,2,8, 7,1,5, 4,6,3},
            {3,5,7, 8,4,6, 1,9,2},
            {4,6,1, 9,2,3, 5,8,7},

            {8,7,6, 3,9,4, 2,1,5},
            {2,4,3, 5,6,1, 9,7,8},
            {1,9,5, 2,8,7, 6,3,4},
            };

	int[][] sudoku2={{3,4, 2,1},
                     {2,1, 4,3},

                     {4,3, 1,2},
                     {1,2, 3,4},};
        printFunction(sudoku);
        System.out.println(isValid(sudoku));

        System.out.println(isValid(sudoku2));
    }


    public static void printFunction(int[][] sudoku){
        //Determining width and height
        int noRows    =sudoku.length;
        int noColumns=sudoku[0].length;


        //Auxiliary function to print a 2D matrix in a sudoku style
        for(int i = 0; i<noRows; i++)
        {
            for(int j = 0; j<noColumns; j++)
            {
                System.out.print(sudoku[i][j]);
                if(j==2 || j==5 || j==8)
                    System.out.print("    ");
            }

            if(i==2 || i==5 || i==8)
                System.out.println("\n");
            else
                System.out.println();
        }


    }

    public static boolean isValid(int[][] sudoku) {

        //Verifying if matrix is null
        if (sudoku == null) {
            System.out.println("Sudoku is null!");
            return false;
        }

        int noRows = sudoku.length;
        int noColumns = sudoku[0].length;


        //Verifying if it is NxN
        if (noRows != noColumns) {
            System.out.println("Is not NxN");
            return false;
        }

        //Verifying if sqrt(N) is an integer
        int x = (int) Math.sqrt(noColumns);
        if (noColumns != Math.pow(x, 2)) {
            System.out.println("Is not perfect square");
            return false;
        }

        //Wasn't really necessary but it is easier to understand the logic with it
        int N = noRows;

        // The set in which we put every different value
        Set<Integer> mySet = new HashSet<>();

        //Validate every column
        //for every iteration of j, I traverse the matrix by columns, after I reached the end,
        //j++ and we get to the next column and so on
        int j=0;
        while(j<N){
        for (int i = 0; i < N; i++) {
            int value = sudoku[i][j];
            //System.out.println(value);
            if (value <= 0 || value > N || mySet.contains(value)) {
                System.out.println("Incorect on column  here: "+i+" "+j);
                return false;
            } else {
                mySet.add(value);
            }
        }
            mySet.clear();
            j++;
            //System.out.println(" ");
        }

        //Validate every row
        //for every iteration of j, I traverse the matrix by rows, after I reached the end
        //j++ and we get to the next row(the code is the same as in the column validation but I switched the j and i)
        j=0;
        while(j<N){
            for (int i = 0; i < N; i++) {
                int value = sudoku[j][i];
                //System.out.println(value);
                if (value <= 0 || value > N || mySet.contains(value)) {
                    System.out.println("Incorect on row here: "+j+" "+i);
                    return false;
                } else {
                    mySet.add(value);
                }
            }
            mySet.clear();
            j++;
            //System.out.println(" ");
        }

        //Validate every box
        //first I get the start of each box by iterating at a distance of sqrtN (x was previously calculated as being sqrtN)
        for(int i=0;i<=N-x;i+=x){
            for(int k=0;k<=N-x;k+=x){
                //System.out.print(sudoku[i][k]);
                //every box starts at a distance of sqrtN of the previous point
                //using the stack I check every box of sqrtN x sqrtN

                //System.out.println(i+" "+k+"\n");

                for(int w=i;w<i+x;w++){
                    for(int z=k;z<k+x;z++){
                        //System.out.print(sudoku[w][z]);
                        int value = sudoku[w][z];
                        if (value <= 0 || value > N || mySet.contains(value)) {
                            System.out.println("Incorect box on here: " +w+ " "+z);
                            return false;
                        } else {
                            mySet.add(value);
                        }
                    }
                    //System.out.println(" ");
                }
                mySet.clear();
            }
        }

        // I will try to check the time complexity for the above algorithm:
        // looking at the box validation we see that we have 2 foor loops with a logN time,
        // another 2 lops with linear time
        //O(n^2*(logn)^2)
        return true;
    }


}
