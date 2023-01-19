package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

import com.tsystems.javaschool.tasks.pyramid.CannotBuildPyramidException;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
	
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
    	// First we check if any of the lists are null
    	if (x == null || y == null) {
    		throw new IllegalArgumentException();
    	}
    	
        // We define the result that is going to be returned at the end
		boolean result = true;
		
		// Now, we define another boolean, an auxiliary variable
		boolean search = true;
		
		// We check if the first list is smaller than the second one
		if (x.size() < y.size()) {
			int i = 0;
			// We check if all elements of the smaller list appear in the bigger one
			// If one element does not appear in the bigger list, we are finish, the result is false
			while (search==true && i < x.size()) {
				boolean ans = y.contains(x.get(i));
				if (!ans) {
					search = false;
					result = false;
				}
				i = i + 1;
			}
			// If all elements are in the bigger list, now we check if they appear in the same order as in the smaller list
			// If not, the final result is false
			if (result==true) {
				for (int j = 0; j < x.size()-1; j++) {
					if (y.indexOf(x.get(j)) > y.indexOf(x.get(j+1))) {
						result = false;
					}
				}	
			}
		// If the second list is smaller, it is not able to contain the first one
		} else {
			result = false;
		}	
		
		if (x.isEmpty() && y.isEmpty()) {
			result = true;
		}
		
        return result;
    }
}
