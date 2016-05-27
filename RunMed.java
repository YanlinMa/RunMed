/*
Maverick -- Dorothy Ng, Denis Duman, Andrea Ma
APCS2 pd10
HW46 -- Running M[edi]an
2016-05-26
 */
/*****************************************************
 * class RunMed
 * Implements an online algorithm to track the median of a growing dataset
 * Maintains 2 heaps: minheap for upper half of dataset, maxheap for lower half
 * Median will always be one of these:
 *  - max of left heap  (lower range)
 *  - min of right heap (upper range)
 *  - mean of heap roots
 *****************************************************/

public class RunMed {

    //instance vars
    private ALMaxHeap leftHeap;  //for lower range of dataset
    private ALMinHeap rightHeap; //for upper range of dataset


    /*****************************************************
     * default constructor  ---  inits empty heap
     *****************************************************/
    public RunMed() 
    { 
	leftHeap = new ALMaxHeap();
	rightHeap = new ALMinHeap();
    }//O(1)



    /*****************************************************
     * double getMedian()  ---  returns median of dataset
     *****************************************************/
    public double getMedian() 
    {
    	if (leftHeap.isEmpty()) {
	    return (double)(rightHeap.peekMin());
    	}
    	else if (rightHeap.isEmpty()) {
	    return (double)(leftHeap.peekMax());
    	}
	else if (leftHeap.size() > rightHeap.size()){
	    return (double)(leftHeap.peekMax());
	}
	else if (leftHeap.size() < rightHeap.size()){
	    return (double)(rightHeap.peekMin());
	}
	else {
	    return (leftHeap.peekMax()+rightHeap.peekMin())/2.0;
    	}
    }//O(1)



    /*****************************************************
     * insert(int)  ---  adds a new element to the dataset
     * postcondition: dataset is maintained such that 
     *                getMedian() can run in constant time
     *****************************************************/
    public void insert( int addVal )
    {
    	if (isEmpty()) leftHeap.add(addVal);
    	else if (getMedian() > addVal) leftHeap.add(addVal);
    	else rightHeap.add(addVal);
	if (leftHeap.size()-rightHeap.size() > 1) {
	    rightHeap.add(leftHeap.removeMax());
	}
	else if (rightHeap.size()-leftHeap.size() > 1) {
	    leftHeap.add(rightHeap.removeMin());
	}
	//System.out.println();
	//System.out.println("left");
	//System.out.println(leftHeap);
	//System.out.println("right");
	//System.out.println(rightHeap);
    }//O(1)
    


    /*****************************************************
     * boolean isEmpty()  ---  tells whether a median may be calculated
     * postcondition: dataset structure unchanged
     *****************************************************/
    public boolean isEmpty() 
    {
	if (leftHeap.isEmpty()&&rightHeap.isEmpty()) {
	    return true;
	}
	else
	    return false;
    }//O(1)



    //main method for testing
    public static void main( String[] args ) {

	RunMed med = new RunMed();
        med.insert(1);
	System.out.println( med.getMedian() ); //1
        med.insert(3);
	System.out.println( med.getMedian() ); //2
        med.insert(5);
	System.out.println( med.getMedian() ); //3
        med.insert(7);
	System.out.println( med.getMedian() ); //4
        med.insert(9);
	System.out.println( med.getMedian() ); //5
	/*~~~V~~~~~~~~~~~~move~me~down~~~~~~~~~~~~~V~~~
	  ~~~~~|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|~~~*/

    }//end main()

}//end class RunMed
