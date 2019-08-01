//Author: Hayden Migliore
//Program: BenchmarkSorts
//Date: 4/12/19
//Purpose: Run Sorts, Create Int Arrays, Create JFrame 
package cmsc451project1;
import java.util.*;
import javax.swing.*;

public class BenchmarkSorts {
    int[] sizes;
    JFrame jf = new JFrame();
    Object[][] data = new String[10][9];
    int i = 0;
    
    //Constructor
    BenchmarkSorts(int[] sizes){
        this.sizes = sizes;
    }//end constructor
    
    public void runSorts() throws UnsortedException{
        //Titles for display
        String[] columnNames = {"Data Set Size N", "Average Critical Operation Count Iterative", 
            "Coefficient of Variance of Count Iterative", "Average Execution Time Iterative", 
            "Coefficient of Variance of Time Iterative", "Average Critical Operation Count Recursive", 
            "Coefficient of Variance of Count Recursive", "Average Execution Time Recursive", 
            "Coefficient of Variance of Time Recursive"};
        
        //Initalize variables
        double acoci = 0;
        double cvci = 0;
        double aeti = 0;
        double cvti = 0;
        double acocr = 0;
        double cvcr = 0;
        double aetr = 0;
        double cvtr = 0;
        ArrayList<Long> timesi = new ArrayList<Long>();
        ArrayList<Long> timesr = new ArrayList<Long>();
        ArrayList<Integer> countsi = new ArrayList<Integer>();
        ArrayList<Integer> countsr = new ArrayList<Integer>();
        double sdci;
        double sdcr;
        double sdti;
        double sdtr;
        int sum;
        YourSort ys = new YourSort();
        
        for(int size : sizes){
            for(int i = 0; i < 50; i++){
                int[] arrayi = new int[size];
                int[] arrayr = new int[size];
                
                //populate array
                for (int j = 0; j < size; j++){
                    int rand = new Random().nextInt(1000);
                    arrayi[j] = rand;
                    arrayr[j] = rand;
                }
                //Recursive merge sort for array
                int left = 0;
                int right = size - 1;
                ys.mergeSortR(arrayr, left, right);
                countsr.add(ys.getCount());
                timesr.add(ys.getTime());
                if (checkSorted(arrayr, arrayr.length) == 0)
                    throw new UnsortedException("Array not sorted");
                
                //Iterative merge sort for array
                ys.mergeSortI(arrayi);
                countsi.add(ys.getCount());
                timesi.add(ys.getTime());
                if (checkSorted(arrayi, arrayi.length) == 0)
                    throw new UnsortedException("Array not sorted");
            }
            
            //Calculations:
            //Recursive:
            //Average Critical Operation Count
            for (int count : countsr)
                acocr += count;
            acocr = acocr / countsr.size();
            //Coefficient of Variance Count
            sum = 0;
            for (int count : countsr)
                sum += Math.pow(count - acocr, 2);
            sdcr = Math.sqrt(sum / (countsr.size() - 1));
            cvcr = (sdcr / acocr) * 100;
            //Average Execution Time
            for (long time : timesr)
                aetr += time;
            aetr = aetr / timesr.size();
            //Coefficient of Variance Time
            sum = 0;
            for (long time : timesr)
                sum += Math.pow(time - aetr, 2);
            sdtr = Math.sqrt(sum / (timesr.size() - 1));
            cvtr = (sdtr / aetr) * 100;
            
            //Iterative:
            //Average Critical Operation Count
            for (int count : countsi)
                acoci += count;
            acoci = acoci / countsi.size();
            //Coefficient of Variance Count
            sum = 0;
            for (int count : countsi)
                sum += Math.pow(count - acoci, 2);
            sdci = Math.sqrt(sum / (countsi.size() - 1));
            cvci = (sdci / acoci) * 100;
            //Average Execution Time
            for (long time : timesi)
                aeti += time;
            aeti = aeti / timesi.size();
            //Coefficient of Variance Time
            sum = 0;
            for (long time : timesi)
                sum += Math.pow(time - aeti, 2);
            sdti = Math.sqrt(sum / (timesi.size() - 1));
            cvti = (sdti / aeti) * 100;
            
            //Collect data for display
            displayReport(size, acoci, cvci, aeti, cvti, acocr, cvcr, aetr, cvtr);
        }
        //Set up display
        JTable table = new JTable(data, columnNames);
        JScrollPane js = new JScrollPane(table);
        jf.add(js);
        jf.setTitle("Project 1");
        jf.setSize(2000, 400); 
        jf.setVisible(true);
    }
    
    void displayReport(int size, double acoci, double cvci, double aeti, double cvti,
            double acocr, double cvcr, double aetr, double cvtr){
        
        //Collect data for JTable
        String[] data1 = {String.valueOf(size),String.valueOf(acoci),String.valueOf(cvci),
            String.valueOf(aeti),String.valueOf(cvti),String.valueOf(acocr),String.valueOf(cvcr),
            String.valueOf(aetr),String.valueOf(cvtr)};
        data[i] = data1;
        i++;
    }
    
    //Check if array is properly sorted
    int checkSorted(int[] array, int n){
        if (n == 1 || n == 0)
            return 1;
        if(array[n - 1] < array[n - 2])
            return 0;
        return checkSorted(array, n - 1);
    }
}

