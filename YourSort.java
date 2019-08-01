//Author: Hayden Migliore
//Program: YourSort
//Date: 4/12/19
//Purpose: Run Iterative mergeSort and Recursive mergeSort
package cmsc451project1;
import java.util.Arrays;

public class YourSort{
    long startTime = 0;
    long endTime = 0;
    int count = 0;
    
    void merge(int[] array, int left, int middle, int right){
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
        
        //Fill temp arrays
        for(int i = 0; i < leftSize; i++){
            leftArray[i] = array[left + i];
            count++;//for loop
        }
        for(int j = 0; j < rightSize; j++){
            rightArray[j] = array[middle + 1 + j]; //was array[middle + 1 + j]
            count++;//for loop;
        }
        
        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = left;
        
        //Sort using temp arrays
        while (leftIndex < leftSize && rightIndex  < rightSize){
            if (leftArray[leftIndex] <= rightArray[rightIndex]){
                array[mergedIndex] = leftArray[leftIndex];
                leftIndex++;
            }
            else{
                array[mergedIndex] = rightArray[rightIndex ];
                rightIndex++;
            }
            mergedIndex++;
            count++;//one for while loop
            count++;//one for comparison
        }//end while
        
        //Check for leftovers
        while(leftIndex < leftSize){
            array[mergedIndex] = leftArray[leftIndex];
            leftIndex++;
            mergedIndex++;
            count++;//while loop
        }//end remaing leftArray
        
        //Check for leftovers        
        while(rightIndex  < rightSize){
            array[mergedIndex] = rightArray[rightIndex];
            rightIndex++;
            mergedIndex++;
            count++;
        }//end remaing rightArray  
    }//end merge
    
    public void mergeSortR(int array[], int left, int right){        
        startTime = System.nanoTime();
        count = 0;
        if (left < right){
            int middle = (left + right) / 2;
            
            //Sort the halves
            mergeSortR(array, left, middle);
            count++;//recusion 
            mergeSortR(array, middle + 1, right);
            count++;//recursion
            
            //Merge the halves
            merge(array, left, middle, right);
            count++;//merge
        }//end if left < right
        endTime = System.nanoTime();
    }//end recusrive merge sort
    
    public void mergeSortI(int array[]){
        startTime = System.nanoTime();
        count = 0;
        int low = 0;
        int high = array.length - 1;
        for (int m = 1; m <= high - low; m = 2*m){
            count++;
            for (int i = low; i < high; i += 2*m){
                count++;
                int left = i;
                int mid = i + m - 1;
                int right = Integer.min(i + 2 * m - 1, high);
                
                //Merge the halves
                merge(array, left, mid, right);
                count++;
            }
        }
        endTime = System.nanoTime();  
    }//end iterative merge sort
    
    public long getTime(){
        return endTime - startTime;
    }//end getTime
    
    public int getCount(){
        return count;
    }//end getCount   
}//end Your Sort

