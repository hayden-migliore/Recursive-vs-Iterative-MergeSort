//Author: Hayden Migliore
//Program: SortMain
//Date: 4/12/19
//Purpose: Main class, contains warm up for JVM, has try for runSorts()
package cmsc451project1;

public class SortMain {
    public static void main(String[] args) {
        //WarmUp
        long start = System.nanoTime();
        ManualClassLoader.load();
        long end = System.nanoTime();
        System.out.println("Total time taken : " + (end - start));
        
        //Sizes (sizes set 2^n to make iterative merge sort work as it needs to break into blocks of 2
        int[] sizes = {32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384};
        BenchmarkSorts test = new BenchmarkSorts(sizes);
        
        //run sorts and catch unsorted exception
        try{
            test.runSorts();
        }
        catch(UnsortedException e){
            System.out.println("Array not sorted properly.");
        }
    } 
}

//Used to warm up JVM
class ManualClassLoader {
    protected static void load() {
        for (int i = 0; i < 100000; i++) {
            Dummy dummy = new Dummy();
            dummy.m();
        }
    }
}
//Used to warm up JVM
class Dummy {
    public void m() {
    }
}


