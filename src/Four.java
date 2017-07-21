import java.util.Collections;
import java.util.Comparator;

//import org.labhc.btreeUtils.Value;

import java.util.ArrayList;
import java.util.BitSet;

public class Four {
	
	@SuppressWarnings("unchecked")
	//****************************************************************************************//
	
	/* Compute the permutation table " permutations"of L2 in L1
	 * 
	 */
	public static void computePermutations(ArrayList<Element4>  L2, ArrayList<Element4> L1, ArrayList<Integer> permutations){
		for (int i=0; i<L2.size(); i++){ //for every element in L2
			permutations.add(L1.indexOf(L2.get(i))); //find the position of that element i in table L1
		}
	}
	
	//****************************************************************************************//
	
	/* Compute the offset table "offset" of L1 w.r.t to L1_dash
	 * bc is the comparator used to sort the elements of L1 and L1_dash
	 * choose True for "strict" to find the strict relative positions of the elements of L1 in L1_dash
	 */
	
	public static void computeOffset(ArrayList<Element4> L1, ArrayList<Element4> L1_dash, ArrayList<Integer> offset, BeanComparator bc, boolean strict){
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
		ArrayList<Element4> table1 = new ArrayList<Element4>(); 
		ArrayList<Element4> table2 = new ArrayList<Element4>();
		table1.add(new Element4(1,1,10,5,1));
		table1.add(new Element4(2,10,50,7,2));
		table1.add(new Element4(3,15,30,3,4));	
		
		table2.add(new Element4(11,2,10,3,1));
		table2.add(new Element4(12,8,10,3,2));
		table2.add(new Element4(13,16,10,3,3));
		table2.add(new Element4(134,16,31,2,0));
		table2.add(new Element4(135,16,32,1,5));
		table2.add(new Element4(14,17,10,1,6));
			
			
		//Create the arrays L1, L1_dash, L2, L2_dash, L3, L3_dash
		ArrayList<Element4> L0 = new ArrayList<Element4>();//L3 is the table1 sorted on the 4th attribute
		ArrayList<Element4> L0_dash = new ArrayList<Element4>();//L3_dash is the table2 sorted on the 4th attribute
		ArrayList<Element4> L1 = new ArrayList<Element4>(); //L1 is the table1 sorted on the third attribute
		ArrayList<Element4> L1_dash = new ArrayList<Element4>();//L1_dash is the table2 sorted on the third attribute
		ArrayList<Element4> L2 = new ArrayList<Element4>();//L2 is the table1 sorted on the  second attribute
		ArrayList<Element4> L2_dash = new ArrayList<Element4>();//L1 is the table2 sorted on the second attribute
		ArrayList<Element4> L3 = new ArrayList<Element4>();//L3 is the table1 sorted on the first attribute
		ArrayList<Element4> L3_dash = new ArrayList<Element4>();//L3_dash is the table2 sorted on the first attribute
		for (int l=0; l<table1.size(); l++){
			L0.add(table1.get(l));
			L1.add(table1.get(l));
			L2.add(table1.get(l));
			L3.add(table1.get(l));
		}
		for (int l=0; l<table2.size(); l++){
			L0_dash.add(table2.get(l));
			L1_dash.add(table2.get(l));
			L2_dash.add(table2.get(l));
			L3_dash.add(table2.get(l));
				}
		
		// Sort the arrays L1, L1_dash, L2 and L2_dash
		BeanComparator bc_price = new BeanComparator(Element4.class, "getPrice"); //create  the ascending comparator of elements of the type element3 based on the Price
		BeanComparator bc_volume = new BeanComparator(Element4.class, "getVolume"); //create  the ascending comparator of elements of the type element3 based on the Volume
		BeanComparator bc_time = new BeanComparator(Element4.class, "getTime"); //create  the ascending comparator of elements of the type element3 based on the Time	
		BeanComparator bc_rate = new BeanComparator(Element4.class, "getRate"); //create  the ascending comparator of elements of the type element3 based on the Time	

		Collections.sort(L0, bc_rate);
		Collections.sort(L0_dash, bc_rate);
		Collections.sort(L1, bc_volume);
		Collections.sort(L1_dash, bc_volume);
		Collections.sort(L2, bc_price);
		Collections.sort(L2_dash, bc_price);
		Collections.sort(L3, bc_time);
		Collections.sort(L3_dash, bc_time);

				
		// Compute the permutation arrays P32 and P_dash32, P31 and P_dash31, P21 and P_dash21
		ArrayList<Integer> P32 = new ArrayList<Integer>(); 
		ArrayList<Integer> P31 = new ArrayList<Integer>();
		ArrayList<Integer> P30 = new ArrayList<Integer>();
		ArrayList<Integer> P_dash32 = new ArrayList<Integer>();
		ArrayList<Integer> P_dash10 = new ArrayList<Integer>();
		ArrayList<Integer> P_dash21 = new ArrayList<Integer>();
		computePermutations(L3, L2, P32); //P32 is the permutation array of L3 in L2
		computePermutations(L3, L1, P31); //P31 is the permutation array of L3 in L1
		computePermutations(L3, L0, P30); //P30 is the permutation array of L3 in L0
		computePermutations(L3_dash, L2_dash, P_dash32); //P_dash32 is the permutation array of L3_dash in L2_dash	 
		computePermutations(L2_dash, L1_dash, P_dash21); //P_dash21 is the permutation array of L2_dash in L1_dash
		computePermutations(L1_dash, L0_dash, P_dash10); //P_dash10 is the permutation array of L1_dash in L0_dash
				
		// Compute the offset arrays O1 and O2
		ArrayList<Integer> O0 = new ArrayList<Integer>();
		ArrayList<Integer> O1 = new ArrayList<Integer>();
		ArrayList<Integer> O2 = new ArrayList<Integer>();
		ArrayList<Integer> O3 = new ArrayList<Integer>();	
		computeOffset(L0, L0_dash, O0, bc_rate, false);	// O0 is the offset array of L1 w.r.t L1_dash without strict relative positions
		computeOffset(L1, L1_dash, O1, bc_volume, false);	// O1 is the offset array of L1 w.r.t L1_dash without strict relative positions
		computeOffset(L2,L2_dash, O2, bc_price, true); // O2 is the offset array of L2 w.r.t L2_dash with strict relative positions
		computeOffset(L3,L3_dash, O3, bc_time, true); // O3 is the offset array of L3 w.r.t L3_dash with strict relative positions

		//Initialize the bit arrays B32 and B21
		BitSet B32= new BitSet(table2.size());
		BitSet B21= new BitSet(table2.size());
		BitSet B10= new BitSet(table2.size());

				
		//Initialize the results ArrayList
		ArrayList<ArrayList<Element4>> results= new ArrayList<ArrayList<Element4>>();
				
		// BEGIN		 
		int i=0,j=0,k=0,l=0, p=0, off1=0, off2=0, off3=0, off0=0;
		long startTime = System.nanoTime();
		int m=table1.size(), n=table2.size();
		for (i=1; i<=m; i++){ //for all elements in L3
			B32.clear();  //clear the bitset B32
			B21.clear(); //clear the bitset B21
			B10.clear();
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
					ArrayList<Element4> result = new ArrayList<Element4>(2);
					B10.set(P_dash10.get(l-1));

				}
			}	
			
			off0= O0.get(P30.get(i-1))+1; //find the relative position of L3[i] in L1_dash
			for (p= 1; p<=Math.min(off0-1, L1_dash.size()); p++){ //for all the elements from L1_dash that respect the third relation (for the third attribute:volume) with L3[i]
				if (B10.get(p-1)){ 
					ArrayList<Element4> result = new ArrayList<Element4>(2);
					result.add(L3.get(i-1));
					result.add(L0_dash.get(p-1)); 
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
		
	}
	
}



		