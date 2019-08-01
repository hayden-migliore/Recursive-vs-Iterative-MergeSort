//Author: Hayden Migliore
//Program: UnsortedException
//Date: 4/12/19
//Purpose: Error for when arrays unsorted
package cmsc451project1;

public class UnsortedException extends Exception{ 
    UnsortedException(String errorMessage){
        super(errorMessage);
    }
}

