package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
	
    public int[][] buildPyramid(List<Integer> inputNumbers) {
		
    	// We get the number of elements in the list
    	int len = inputNumbers.size();
    	
    	// In order to be able to construct a pyramid with the numbers in the list,
    	// we need to have a specific number of elements (len), a number that is part of the 
    	// triangular sequence: 1, 3, 6, 10, 15, 21...
    	// The numbers in the sequence could be calculated using the following formula:
    	// n*(n+1)/2
    	// The number of elements in the list has to be one of the possible results of that
    	// formula. Solving for n we get:
    	// n = (sqrt(1+8*len)-1)/2
    	// Replacing the len value, if it belongs to the triangular sequence, n has to be an integer.
    	// We get the n value
    	double nsequence = (Math.sqrt(1+8*len) - 1) / 2;
    	
    	// We check if the n value is NaN
    	if (Double.isNaN(nsequence)) {
    		throw new CannotBuildPyramidException();
    	}
    	
		// We get the number of rows and the number of columns in the pyramid
		int nrows = (int)nsequence;
		int ncols = 2*nrows - 1;
				
		// We define the variable that is going to contain the pyramid
		int[][] pyramid = new int[nrows][ncols];	
    	
    	// First of all, we check if the list has any null element
    	if (inputNumbers.contains(null)) {
			throw new CannotBuildPyramidException();
		} else {
	    	// We sort the list in ascending order
	    	Collections.sort(inputNumbers);
			
	    	// We check if it is possible to construct the pyramid, and if so, we construct it.
			if ((nsequence - (int)nsequence) == 0) {
				// We define a list of lists that is going to contain the numbers for each row of the pyramid
				int k = 0; // Aux variable
				
				List<List<Integer>> lists = new ArrayList<>();
				
				// In each i iteration we create a new list/row
				// The k variable indicates the index of the first non zero number in each row
				for (int i = 0; i < nrows; i++) {
					List<Integer> list = new ArrayList<>();
					k = k + i;
					for (int j = k; j <= k+i; j++) {
						list.add(inputNumbers.get(j));
					}
					lists.add(list);
				}
				
				// Now, we fill the spaces between numbers with zeros
				// We create a new list of lists
				List<List<Integer>> zerolists = new ArrayList<>();
				
				// In each i iteration we construct new rows using the old ones
				for (int i = 0; i < nrows; i++) {
					List<Integer> list = new ArrayList<>();
					list = lists.get(i);
					int length = list.size();
					for (int j = 1; j < 2*length-1; j+=2) {
						list.add(j,0);
					}
					zerolists.add(list);
				}
				
				// Finally, we fill the borders with zeros
				// Again a new list of lists
				List<List<Integer>> finallists = new ArrayList<>();
				
				// Each iteration we define the new row based on the old one
				for (int i = 0; i < nrows; i++) {
					List<Integer> list = new ArrayList<>();
					list = zerolists.get(i);
					for (int j = 1; j < nrows-i; j++) {
						list.add(0);
						list.add(0,0);
					}
					finallists.add(list);
				}
				
				// The last step is to construct the pyramid in the corresponding variable
				for (int i = 0; i < nrows; i++) {
					for (int j = 0; j < ncols; j++) {
						pyramid[i][j] = finallists.get(i).get(j);
					}
				}
			} else {
				throw new CannotBuildPyramidException();
			}
		}
    	return pyramid;
    }
}
