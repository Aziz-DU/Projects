///////////////////////////////////////////////////
//Abdulaziz alkhelaiwi (Aziz)
//Class: COMP 2691: Computer Organization
//Project info: Homework #2 - Binary Calculator 
///////////////////////////////////////////////////


public class BinaryCalculator
{
	public static BitField add(BitField a, BitField b)
	{
		if(null == a || null == b || a.size() != b.size()){
			throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}

		boolean carry=false;
		BitField result = new BitField (a.size());
		for(int i=0; i<a.size();i++) {
			boolean temp=false;
			if(a.get(i)==false&&b.get(i)==false) {
				if(carry) {
					temp=true;
					carry=false;
				}
				else {
					temp=false;
				}
				
			}
			else if(a.get(i)==true&&b.get(i)==true) {
				if(carry) {
					temp=true;
				}
				else {
					temp=false;
					carry=true;
				}
			}
			else { 
				if(carry) {
					temp=false;
					carry=true;
				}
				else {
					temp=true;
				}
				}
			result.set(i, temp);
		}
		
		return result;
	}
	
	public static BitField subtract(BitField a, BitField b)
	{
		if(null == a || null == b || a.size() != b.size()){
			throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		BitField bnegated = negate(b);
		BitField result = add(a,bnegated);
		return result;
	}
	public static BitField negate(BitField a)
	{
		BitField c = complement(a);
		BitField one = new BitField (a.size());
		BitField result = new BitField (a.size());
		one.set(0, true);
		result=add(c, one);
		return result;
	}
	public static BitField complement(BitField a)
	{
		BitField out = new BitField (a.size());
		for(int i=0; i<a.size();i++) {
			out.set(i, !a.get(i));
		}
		return out;
	}
	public static BitField multiply(BitField a, BitField b)
	{
		if(null == a || null == b || a.size() != b.size()){
			throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		BitField result = new BitField (a.size());
		for(int i=0; i<a.size();i++) {
			if(a.get(0)==true) {
				result=add(b.copy(), result.copy());
			}
			a=BinaryCalculator.shiftRight(a);			
			b=BinaryCalculator.shiftLeft(b);
		}
		return result;
	}

	public static BitField[] divide(BitField a, BitField b)
	{
		if(null == a || null == b || a.size() != b.size()){
			throw new IllegalArgumentException("BinaryCalculator.add(a,b): a and b cannot be null and must be the same length.");
		}
		boolean divisorIsZero=true;
		for(int i=0;i<b.size();i++) {		
			if(b.get(i)==true) {
				divisorIsZero=false;
			}
		}
		if(divisorIsZero) {
			return null;	
		}
		boolean divisorIsNegative=false;
		boolean dividendIsNegative=false;

		if(a.get(a.size()-1)==true) {			
			a= BinaryCalculator.negate(a.copy());
			dividendIsNegative=true;	
		}



		if(b.get(b.size()-1)==true) {			
			b= BinaryCalculator.negate(b.copy());
			divisorIsNegative=true;
		}

		BitField quotient = new BitField (a.size());		
		BitField divisor = new BitField (a.size()*2);
		BitField remainder = new BitField (a.size()*2);  		
		for(int i=0;i<divisor.size();i++) {	
			if(i<b.size()) {
				divisor.set(i, b.get(i));
			}
			else {
				divisor =BinaryCalculator.shiftLeft(divisor);	
			}			
		}
		for(int i=0;i<remainder.size();i++) {		
			if(i<a.size()) {
				remainder.set(i, a.get(i));
			}
		}		
		for(int i=0;i<quotient.size()+1;i++) {											
			remainder =BinaryCalculator.subtract(remainder.copy(), divisor.copy());					
			if(remainder.get(remainder.size()-1)==true){

				remainder =BinaryCalculator.add(remainder.copy(), divisor.copy());
				quotient =BinaryCalculator.shiftLeft(quotient.copy());
			}
			else {
				quotient =BinaryCalculator.shiftLeft(quotient.copy());
				quotient.set(0, true);
			}
			
			divisor =BinaryCalculator.shiftRight(divisor.copy());	
		}
		
		if(divisorIsNegative && !dividendIsNegative) {
			quotient= BinaryCalculator.negate(quotient.copy());
		}
		else if(!divisorIsNegative && dividendIsNegative) {
			quotient= BinaryCalculator.negate(quotient.copy());
			remainder= BinaryCalculator.negate(remainder.copy());
		}
		else if(divisorIsNegative && dividendIsNegative) {
			remainder= BinaryCalculator.negate(remainder.copy());
		}					
		BitField[] out = new BitField [ 2 ];
		out[0] = new BitField(a.size()); 
		out[1] = new BitField(a.size()); 
		for(int i=0;i<out[0].size();i++) {
			out[0].set(i, quotient.get(i));
		}
		for(int i=0;i<out[1].size();i++) {
			out[1].set(i, remainder.get(i));
		}	
		return out;
	}
	public static BitField shiftLeft(BitField bitArray)
	{
		BitField out = new BitField (bitArray.size());
		out.set(0, false);
		for(int i=0; i<bitArray.size()-1;i++) {
			out.set(i+1, bitArray.get(i));	
		}
		return out;
	}
	public static BitField shiftRight(BitField bitArray)
	{
		BitField out = new BitField (bitArray.size());
		for(int i=0; i<bitArray.size()-1;i++) {
			out.set(i, bitArray.get(i+1));	
		}
		return out;
	}
}
