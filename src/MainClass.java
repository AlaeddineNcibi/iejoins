import java.util.Collections;
import java.util.ArrayList;
import java.util.BitSet;

public class MainClass {
	
	
	@SuppressWarnings("unchecked")
	//****************************************************************************************//
	
	// Choose True for "isAscending" if table1.methodName<table2.methodName and False otherwise
	public static ArrayList<ArrayList<Element>> joinArrays (ArrayList<Element>  table1, ArrayList<Element> table2, String methodName, boolean isAscending){
		ArrayList<ArrayList<Element>> results = new ArrayList<ArrayList<Element>>();
		BeanComparator bc = new BeanComparator(Element.class, methodName, isAscending);
		Collections.sort(table1, bc);
		Collections.sort(table2, bc);
		ArrayList<Integer> O = new ArrayList<Integer>(table1.size()-1);
		computeOffset(table1, table2, O, bc, true);
		for (int i=0; i<table1.size(); i++){
			if (O.get(i)<table2.size()){
				for (int j=O.get(i); j<table2.size(); j++){
					ArrayList<Element> result = new ArrayList<Element>(2);
					result.add(table1.get(i));
					result.add(table2.get(j));
					results.add(result);
				}
			}
		}
		return results;
	}
	
	//****************************************************************************************//
	
	public static void computePermutations(ArrayList<Element>  L2, ArrayList<Element> L1, ArrayList<Integer> permutations){
		for (int i=0; i<L2.size(); i++){
			permutations.add(L1.indexOf(L2.get(i)));
		}
	}
	
	//****************************************************************************************//
	
	public static void computeOffset(ArrayList<Element> L1, ArrayList<Element> L1_dash, ArrayList<Integer> offset, BeanComparator bc, boolean strict){
		boolean found; int j;
		for (int i=0; i<L1.size(); i++){
			found=false; j=0;
			while(j<L1_dash.size() & found==false){
				if (strict) {if (bc.compare(L1.get(i), L1_dash.get(j))<0) {offset.add(j); found=true;}}
				else if (bc.compare(L1.get(i), L1_dash.get(j))<=0) {offset.add(j); found=true;}
				j++;
			}
			if (found==false) {offset.add(L1_dash.size());}
			
		}
	}
	
	//****************************************************************************************//
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		
		// Input tables initialization
		ArrayList<Element> table1 = new ArrayList<Element>(); 
		ArrayList<Element> table2 = new ArrayList<Element>( );
		
	/*	table1.add(new Element(1,140,9));
		table1.add(new Element(2,100,12));
		table1.add(new Element(3,90,5));
		table1.add(new Element(4,142,2));
		table1.add(new Element(5,141,12));
		table1.add(new Element(99,21,5));
		table2.add(new Element(10,100,6));
		table2.add(new Element(11,140,11));
		table2.add(new Element(15,90,7));
		table2.add(new Element(16,90,8));
		table2.add(new Element(20,160,15));
		table2.add(new Element(21,160,5));
		table2.add(new Element(22,89,5));
		table2.add(new Element(17,50,6));
		table2.add(new Element(18,21,5));
		table2.add(new Element(19,142,11));*/
		
		table1.add(new Element(1,11,20));
		table1.add(new Element(2,13,30));
		table1.add(new Element(3,15,40));
		table1.add(new Element(4,17,50));
		table1.add(new Element(5,19,60));
		table1.add(new Element(6,21,70));
		table1.add(new Element(7,23,80));
		table1.add(new Element(8,25,40));
		table1.add(new Element(9,27,90));
		table1.add(new Element(10,29,100));
		table1.add(new Element(11,31,40));

		
		table2.add(new Element(1,11,20));
		table2.add(new Element(2,13,30));
		table2.add(new Element(3,15,40));
		table2.add(new Element(4,17,50));
		table2.add(new Element(5,19,60));
		table2.add(new Element(6,21,70));
		table2.add(new Element(7,23,80));
		table2.add(new Element(8,25,40));
		table2.add(new Element(9,27,90));
		table2.add(new Element(10,29,100));
		table2.add(new Element(11,31,40));
		table2.add(new Element(12,32,110));
		
		
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//

		//*** Article's method ***//
		
		// Create the arrays L1, L1_dash, L2 and L2_dash
		ArrayList<Element> L1 = new ArrayList<Element>();
		ArrayList<Element> L1_dash = new ArrayList<Element>();
		ArrayList<Element> L2 = new ArrayList<Element>();
		ArrayList<Element> L2_dash = new ArrayList<Element>();
		for (int l=0; l<table1.size(); l++){
			L1.add(table1.get(l));
			L2.add(table1.get(l));
		}
		for (int l=0; l<table2.size(); l++){
			L1_dash.add(table2.get(l));
			L2_dash.add(table2.get(l));
		}
		// Sort the arrays L1, L1_dash, L2 and L2_dash
		BeanComparator bc_price_descend = new BeanComparator(Element.class, "getPrice", false);
		BeanComparator bc_volume_descend = new BeanComparator(Element.class, "getVolume", false);
		BeanComparator bc_price = new BeanComparator(Element.class, "getPrice");
		Collections.sort(L1, bc_price);
		Collections.sort(L1_dash, bc_price);
		System.out.println();
		System.out.println("*****");
		System.out.println("L1");
		for (int o=0; o<L1.size(); o++){
			System.out.print(L1.get(o).getPrice()+" ");
		}
		System.out.println();
		System.out.println("*****");
		System.out.println("L1_dash");
		for (int o=0; o<L1_dash.size(); o++){
			System.out.print(L1_dash.get(o).getPrice()+" ");
		}
		
		BeanComparator bc_volume = new BeanComparator(Element.class, "getVolume");
		Collections.sort(L2, bc_volume);
		Collections.sort(L2_dash, bc_volume);
		System.out.println();
		System.out.println("*****");
		System.out.println("L2");
		for (int o=0; o<L2.size(); o++){
			System.out.print(L2.get(o).getVolume()+" ");
		}
		System.out.println();
		System.out.println("*****");
		System.out.println("L2_dash");
		for (int o=0; o<L2_dash.size(); o++){
			System.out.print(L2_dash.get(o).getVolume()+" ");
		}
		
		// Compute the permutation arrays P and P_dash
		ArrayList<Integer> P = new ArrayList<Integer>(L1.size()-1);
		ArrayList<Integer> P_dash = new ArrayList<Integer>(L1_dash.size()-1);
		computePermutations(L2, L1, P);
		computePermutations(L2_dash, L1_dash, P_dash);	
		System.out.println();
		System.out.println("*****");
		System.out.println("P");
		for (int o=0; o<P.size(); o++){
			System.out.print(P.get(o)+" ");
		}
		System.out.println();
		System.out.println("*****");
		System.out.println("P_dash");
		for (int o=0; o<P_dash.size(); o++){
			System.out.print(P_dash.get(o)+" ");
		}
		
		// Compute the offset arrays O1 and O2
		ArrayList<Integer> O1 = new ArrayList<Integer>();
		ArrayList<Integer> O2 = new ArrayList<Integer>();
		computeOffset(L1, L1_dash, O1, bc_price, true);
		computeOffset(L2,L2_dash, O2, bc_volume, false);
		System.out.println();
		System.out.println("*****");
		System.out.println("O1");
		for (int o=0; o<O1.size(); o++){
			System.out.print(O1.get(o)+" ");
		}
		System.out.println();
		System.out.println("*****");
		System.out.println("O2");
		for (int o=0; o<O2.size(); o++){
			System.out.print(O2.get(o)+" ");
		}
		//Initialize the bit array B_dash
		BitSet B_dash= new BitSet(table2.size());
		
		//Initialize the results ArrayList
		ArrayList<ArrayList<Element>> results= new ArrayList<ArrayList<Element>>();
		int eqOff=1; 
		int i=0,j=0,k=0, off1=0, off2=0;
		long startTime = System.nanoTime();
		int m=table1.size(), n=table2.size();
		for (i=1; i<=m; i++){
			//BitSet B_dash= new BitSet(table2.size());
			off2=O2.get(i-1)+1; //+1 because décalage indices
			System.out.println(i + " " +off2 );
			for (j= 1; j<=Math.min(off2-1, L2_dash.size()); j++){  ///mistake table2 ou L2_dashinstead of L2 + mistake off2-1
				B_dash.set(P_dash.get(j-1));
				System.out.println("yes"+i);
			}
			off1= O1.get(P.get(i-1))+1;
			
			for (k=off1; k<=n; k++){
				
				if (B_dash.get(k-1)){ 
					

					ArrayList<Element> result = new ArrayList<Element>(2);
					result.add(L2.get(i-1));
					result.add(L1_dash.get(k-1)); //it was a mistake here
					results.add(result);
				}
			}
		}
		
		//Show the results
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println("Execution time for the articles method: "+ totalTime +"ms");
		System.out.println("Results of the article's method");
		for (int l=0; l<results.size(); l++){
			System.out.println("("+results.get(l).get(0).getId()+","+results.get(l).get(1).getId()+")"); 
		}
		
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//
		
		//*** IESELF_JOIN ***//
		System.out.println("*******************");
		// Input table initialization (table)
		ArrayList<Element> table = new ArrayList<Element>(); 
		
		
		table.add(new Element(1,12,4));
		table.add(new Element(2,15,3));
		table.add(new Element(3,12,5));
		table.add(new Element(4,7,5));
		// Create the arrays L_1 and L_2 
		ArrayList<Element> L_1 = new ArrayList<Element>();
		ArrayList<Element> L_2 = new ArrayList<Element>();
		for (int l=0; l<table.size(); l++){
			L_1.add(table.get(l));
			L_2.add(table.get(l));
		}
		// Sort the arrays L_1 and L_2 
		
		Collections.sort(L_1, bc_price_descend);
		for ( i=0; i<L_1.size(); i++){
			System.out.println("L_1["+i+"].price= "+ L_1.get(i).getPrice());
		}
		
		Collections.sort(L_2, bc_volume_descend);
		for ( i=0; i<L_2.size(); i++){
			System.out.println("L_2["+i+"].volume= "+ L_2.get(i).getVolume());
		}
		// Compute the permutation arrays P_ of L_1 in L_2
		ArrayList<Integer> P_ = new ArrayList<Integer>(L_1.size()-1);
		computePermutations(L_2, L_1, P_);
		for ( i=0; i<P_.size(); i++){
			System.out.println("P["+i+"]= "+ P_.get(i));
		}
		//Initialize the bit array B_dash
		BitSet B= new BitSet(table.size());
		//Initialize the results ArrayList
		ArrayList<ArrayList<Element>> results_self= new ArrayList<ArrayList<Element>>();
		//Start the algorithm
		eqOff=1; int pos=0;
		startTime = System.nanoTime();
		for (i=0; i<table.size(); i++){
			pos=P_.get(i);
			for (j= pos+eqOff; j<table.size(); j++){
				if (B.get(j)){
					ArrayList<Element> result_self = new ArrayList<Element>(2);
					result_self.add(L_1.get(j));
					result_self.add(L_1.get(P_.get(i))); 
					results_self.add(result_self);
				}
			}
			B.set(pos);
		};		
		//Show the results
		endTime   = System.nanoTime();
		totalTime = endTime - startTime;
		System.out.println("Execution time for SelfJoin: "+ totalTime +"ms");
		System.out.println("Results of SelJoin method");
		for (int l=0; l<results_self.size(); l++){
			System.out.println("("+results_self.get(l).get(0).getId()+","+results_self.get(l).get(1).getId()+")"); 
		}				
		System.out.println("***********************");
		
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//
//********************************************************************************************************************//
		
		//*** Simple method ***//
		
		// Initialize the ArrayLists for the price's condition and the volume's condition
		ArrayList<ArrayList<Element>> join_results_price = new ArrayList<ArrayList<Element>>();
		ArrayList<ArrayList<Element>> join_results_volume = new ArrayList<ArrayList<Element>>();
		// Initialize the ArrayList for the results
		ArrayList<ArrayList<Element>> final_results = new ArrayList<ArrayList<Element>>();
		// Compute these two ArrayLists
		 startTime = System.nanoTime();
		join_results_price=joinArrays (table1, table2, "getPrice", true);
		
		join_results_volume=joinArrays (table1, table2, "getVolume", false);
		
		// Start Algorithm
		int index;
		for (int l=0; l<join_results_price.size(); l++){
			index= join_results_volume.indexOf(join_results_price.get(l));
			if (index>=0) {
				final_results.add(join_results_price.get(l));
			}
		}
		
		//Show the results
		 endTime   = System.nanoTime();
		 totalTime = endTime - startTime;
		System.out.println("Execution time for the simple method: "+ totalTime + "ms");
		System.out.println("Results of the simple method");
		for (int l=0; l<final_results.size(); l++){
			System.out.println("("+final_results.get(l).get(0).getId()+","+final_results.get(l).get(1).getId()+")"); 
		}
		
	}

	//****************************************************************************************//
	
}
