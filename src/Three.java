import java.util.Collections;
import java.util.Comparator;

//import org.labhc.btreeUtils.Value;

import java.util.ArrayList;
import java.util.BitSet;

public class Three {
	
	@SuppressWarnings("unchecked")
	//****************************************************************************************//
	

	/* Get the elements from table1 (element1) and from table2 (element2) that satisfy the relation element1.methodName op element2.methodName
	 * if op is < choose True for "isAscending"  and False otherwise 
	 */
	public static ArrayList<ArrayList<Element3>> joinArrays (ArrayList<Element3>  table1, ArrayList<Element3> table2, String methodName, boolean isAscending){
		ArrayList<ArrayList<Element3>> results = new ArrayList<ArrayList<Element3>>(); //Create the table of the join results
		BeanComparator bc = new BeanComparator(Element3.class, methodName, isAscending); //create the comparator
		Collections.sort(table1, bc); //sort  table 1
		Collections.sort(table2, bc); //sort table2
		ArrayList<Integer> O = new ArrayList<Integer>(table1.size()-1); //create the offset table (offset of table1 w.r.t table 2)
		computeOffset(table1, table2, O, bc, true); //compute the offset table
		for (int i=0; i<table1.size(); i++){ //for every element in the first table
			if (O.get(i)<table2.size()){ //if there are elements in the table 2 that respect the relation with the element i of the table 1
				for (int j=O.get(i); j<table2.size(); j++){ //for all the elements in table2 hat respect the relation with the element i of table1
					ArrayList<Element3> result = new ArrayList<Element3>(2); //create a new couple for a new join result
					result.add(table1.get(i)); //add the first element to the couple
					result.add(table2.get(j)); //add the second elemnt to the couple
					results.add(result); //add the couple to the join results table
				}
			}
		}
		return results; //return the join results
	}
	
	//****************************************************************************************//
	
	/* Compute the permutation table " permutations"of L2 in L1
	 * 
	 */
	public static void computePermutations(ArrayList<Element3>  L2, ArrayList<Element3> L1, ArrayList<Integer> permutations){
		for (int i=0; i<L2.size(); i++){ //for every element in L2
			permutations.add(L1.indexOf(L2.get(i))); //find the position of that element i in table L1
		}
	}
	
	//****************************************************************************************//
	
	/* Compute the offset table "offset" of L1 w.r.t to L1_dash
	 * bc is the comparator used to sort the elements of L1 and L1_dash
	 * choose True for "strict" to find the strict relative positions of the elements of L1 in L1_dash
	 */
	/*public static void computeOffset(ArrayList<Element3> L1, ArrayList<Element3> L1_dash, ArrayList<Integer> offset, BeanComparator bc, boolean strict){
		boolean found; int j;
		for (int i=0; i<L1.size(); i++){ //for every element in table1
			found=false; j=0;
			while(j<L1_dash.size() & found==false){ // while we haven't found the position and we didn"t check all the elements of L1_dash
				if (strict) {if (bc.compare(L1.get(i), L1_dash.get(j))<0) {offset.add(j); found=true;}}
				else if (bc.compare(L1.get(i), L1_dash.get(j))<=0) {offset.add(j); found=true;}
				j++;
			}
			if (found==false) {offset.add(L1_dash.size());}
			
		}
	}*/
	
	public static void computeOffset(ArrayList<Element3> L1, ArrayList<Element3> L1_dash, ArrayList<Integer> offset, BeanComparator bc, boolean strict){
		for (int i = 0; i < L1.size(); i++) { // for every Value in a
			
			int low = 0;
			int high = L1_dash.size() - 1;
			
			while (low <= high ) { 
				int mid = (low + high) >>> 1;											
				if (strict){
					if (bc.compare(L1.get(i), L1_dash.get(mid)) >= 0)
						low = mid + 1;
					else if (bc.compare(L1.get(i), L1_dash.get(mid)) < 0)
						high = mid - 1;
						else{
							offset.add(mid);
							
						}
				}
				else {
					
					if (bc.compare(L1.get(i), L1_dash.get(mid)) > 0)
						low = mid + 1;
					else if (bc.compare(L1.get(i), L1_dash.get(mid)) < 0)
						high = mid - 1;
						else
							high = mid - 1;
						
				}
		}
				offset.add(low);
	}
}	
	
	//****************************************************************************************//
	@SuppressWarnings("unchecked")
	
	
	public static void main(String[] args) {
		
		//Input tables initialization
		ArrayList<Element3> table1 = new ArrayList<Element3>(); 
		ArrayList<Element3> table2 = new ArrayList<Element3>();
		table1.add(new Element3(1,1,10,5));
		table1.add(new Element3(2,10,50,7));
		table1.add(new Element3(3,15,30,3));				
		table2.add(new Element3(11,2,10,3));
		table2.add(new Element3(12,8,10,3));
		table2.add(new Element3(13,16,10,3));
		table2.add(new Element3(134,16,31,2));
		table2.add(new Element3(135,16,32,1));
		table2.add(new Element3(14,17,10,1));
			
			
		//Create the arrays L1, L1_dash, L2, L2_dash, L3, L3_dash
		ArrayList<Element3> L1 = new ArrayList<Element3>(); //L1 is the table1 sorted on the third attribute
		ArrayList<Element3> L1_dash = new ArrayList<Element3>();//L1_dash is the table2 sorted on the third attribute
		ArrayList<Element3> L2 = new ArrayList<Element3>();//L2 is the table1 sorted on the  second attribute
		ArrayList<Element3> L2_dash = new ArrayList<Element3>();//L1 is the table2 sorted on the second attribute
		ArrayList<Element3> L3 = new ArrayList<Element3>();//L3 is the table1 sorted on the first attribute
		ArrayList<Element3> L3_dash = new ArrayList<Element3>();//L3_dash is the table2 sorted on the first attribute
		for (int l=0; l<table1.size(); l++){
			L1.add(table1.get(l));
			L2.add(table1.get(l));
			L3.add(table1.get(l));
		}
		for (int l=0; l<table2.size(); l++){
			L1_dash.add(table2.get(l));
			L2_dash.add(table2.get(l));
			L3_dash.add(table2.get(l));
				}
		
		// Sort the arrays L1, L1_dash, L2 and L2_dash
		BeanComparator bc_price = new BeanComparator(Element3.class, "getPrice"); //create  the ascending comparator of elements of the type element3 based on the Price
		BeanComparator bc_volume = new BeanComparator(Element3.class, "getVolume"); //create  the ascending comparator of elements of the type element3 based on the Volume
		BeanComparator bc_time = new BeanComparator(Element3.class, "getTime"); //create  the ascending comparator of elements of the type element3 based on the Time	
		Collections.sort(L1, bc_volume);
		Collections.sort(L1_dash, bc_volume);
		Collections.sort(L2, bc_price);
		Collections.sort(L2_dash, bc_price);
		Collections.sort(L3, bc_time);
		Collections.sort(L3_dash, bc_time);

				
		// Compute the permutation arrays P32 and P_dash32, P31 and P_dash31, P21 and P_dash21
		ArrayList<Integer> P32 = new ArrayList<Integer>(L3.size()-1); 
		ArrayList<Integer> P_dash32 = new ArrayList<Integer>(L3_dash.size()-1);
		ArrayList<Integer> P31 = new ArrayList<Integer>(L3.size()-1);
		ArrayList<Integer> P_dash31 = new ArrayList<Integer>(L3_dash.size()-1);
		ArrayList<Integer> P21 = new ArrayList<Integer>(L2.size()-1);
		ArrayList<Integer> P_dash21 = new ArrayList<Integer>(L2_dash.size()-1);
		computePermutations(L3, L1, P31); //P31 is the permutation array of L3 in L1
		computePermutations(L3_dash, L1_dash, P_dash31); //P_dash31 is the permutation array of L3_dash in L1_dash	 
		computePermutations(L2, L1, P21); //P21 is the permutation array of L2 in L1
		computePermutations(L2_dash, L1_dash, P_dash21); //P_dash21 is the permutation array of L2_dash in L1_dash
		computePermutations(L3, L2, P32); //P32 is the permutation array of L3 in L2
		computePermutations(L3_dash, L2_dash, P_dash32); //P_dash32 is the permutation array of L3_dash in L2_dash
				
		// Compute the offset arrays O1 and O2
		ArrayList<Integer> O1 = new ArrayList<Integer>();
		ArrayList<Integer> O2 = new ArrayList<Integer>();
		ArrayList<Integer> O3 = new ArrayList<Integer>();	
		computeOffset(L1, L1_dash, O1, bc_volume, false);	// O1 is the offset array of L1 w.r.t L1_dash without strict relative positions
		computeOffset(L2,L2_dash, O2, bc_price, true); // O2 is the offset array of L2 w.r.t L2_dash with strict relative positions
		computeOffset(L3,L3_dash, O3, bc_time, true); // O3 is the offset array of L3 w.r.t L3_dash with strict relative positions
/*
		//Initialize the bit arrays B32 and B21
		BitSet B32= new BitSet(table2.size());
		BitSet B21= new BitSet(table2.size());
				
		//Initialize the results ArrayList
		ArrayList<ArrayList<Element3>> results= new ArrayList<ArrayList<Element3>>();
				
		// BEGIN		 
		int i=0,j=0,k=0,l=0, off1=0, off2=0, off3=0;
		long startTime = System.nanoTime();
		int m=table1.size(), n=table2.size();
		for (i=1; i<=m; i++){ //for all elements in L3
			B32.clear();  //clear the bitset B32
			B21.clear(); //clear the bitset B21
			off3=O3.get(i-1)+1; //find the relative position of L3[i] in L3_dash		
			for (j=off3; j<=n; j++){ //for all the elements from L3_dash that respect the first relation (for the first attribute:time) with L3[i]
				B32.set(P_dash32.get(j-1));
			}	
			off2= O2.get(P32.get(i-1))+1; //find the relative position of L3[i] in L2_dash
			for (k=off2; k<=n; k++){ //for all the elements from L2_dash that respect the second relation (for the second attribute:price) with L3[i]
				if (B32.get(k-1)) {
					B21.set(P_dash21.get(k-1));
				}
			}	
			off1= O1.get(P31.get(i-1))+1; //find the relative position of L3[i] in L1_dash
			for (l= 1; l<=Math.min(off1-1, L1_dash.size()); l++){ //for all the elements from L1_dash that respect the third relation (for the third attribute:volume) with L3[i]
				if (B21.get(l-1)){ 
					ArrayList<Element3> result = new ArrayList<Element3>(2);
					result.add(L3.get(i-1));
					result.add(L1_dash.get(l-1)); 
					results.add(result);
				}
			}	
		}		
			
		//Show the results
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("**Execution time: "+ totalTime +" ns");
		System.out.println("**Results**");
		for ( l=0; l<results.size(); l++){
			System.out.println("("+results.get(l).get(0).getId()+","+results.get(l).get(1).getId()+")"); 
		}
		*/		
	}
	
}



		