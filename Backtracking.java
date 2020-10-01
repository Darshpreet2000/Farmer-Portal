package Backtracking;

public class Backtracking {

	/**
	 * @param args
	 */
	static int count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QueenPermutations(new boolean[4], 0, 2, "");
        //QueenCombination(new boolean[4],0,2,"",-1);
        CoinChangeP(new int[] {2,3,4,5,6},10,"");	
	}
	
	public static void QueenPermutations(boolean[] boxes,int qpsf,int tq,String ans){
		if(qpsf==tq){
			count++;
			System.out.println(count+" . "+ans);
			return;
		}
		
		for(int i=0;i<boxes.length;i++){
			if(boxes[i]==false){
			boxes[i]=true;
			QueenPermutations(boxes, qpsf+1, tq, ans+"q"+qpsf+"b"+i+" ");
		    boxes[i]=false;
			}
		
	}

}

	public static void QueenCombination(boolean[] boxes,int qpsf,int tq,String ans,int lastBoxUsed){
		if(qpsf==tq){
			count++;
			System.out.println(count+" . "+ans);
			return;
		}
		
		for(int i=lastBoxUsed+1;i<boxes.length;i++){
			if(boxes[i]==false){
			boxes[i]=true;
			QueenCombination(boxes, qpsf+1, tq, ans+"q"+qpsf+"b"+i+" ",i);
		    boxes[i]=false;
			}
		
	}

}

    public static void CoinChange(int[] denom,int amount,String ans,int lastDenomIndex){
    	if(amount==0){
    		System.out.println(ans);
    		return; 
    	}
    	for(int i=lastDenomIndex;i<denom.length;i++){
    		if(amount>=denom[i])
    		CoinChange(denom, amount-denom[i], ans+denom[i], i);
    		
    	}
    }
    public static void CoinChangeP(int[] denom,int amount,String ans){
    	if(amount==0){
    		count++;
    		System.out.println(count+"."+ans);
    		return; 
    	}
    	if(amount<0){
    		return;
    	}
    	for(int i=0;i<denom.length;i++){
    		if(amount>=denom[i])
    		CoinChangeP(denom, amount-denom[i], ans+denom[i]);
    		
    	}
    }
}
