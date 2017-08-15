/**
 * 
 * DoDiv does the actual work of dividing a numerator by a divisor. Each base 10 digit is its own class.
 * The numerators and divisors are passed as BigIntegers. The SUM is kept as a BigInteger. The REMAINDER*10
 * is passed to the next divider as its numerator. Any carrys are passed to the preceding divider.
 *  
 */
package Pi;

import java.math.BigInteger;

public class DoDiv {
	private DoDiv nextDivisor;
	private BigInteger sum;
	private int doDivNumber;
	private int maxPlaces;

	// Constructor to initialize the sum to 0 (not needed) and store what digit object this is.
	public DoDiv(int a, int max) {
		maxPlaces = max;
		sum = new BigInteger("0");
		doDivNumber = a;
		if(a < maxPlaces)
			nextDivisor = new DoDiv(a+1, maxPlaces);
//		System.out.println(a + ",\t" + sum);
	}
	public void setSum(int x) {
		sum = new BigInteger(String.format("%d", x));
	}
// Do this digit division using remainder from last object, the divisor, and the flag telling either addition or subtraction
	public BigInteger myDiv(BigInteger remainder, BigInteger divisor, String flag) {
// If the remainder is less than 0 we got an error
		if(remainder.compareTo(BigInteger.ZERO) < 0) System.out.println("Error in processing.");
// the sum (for now) is changed by the long division of the remainder by the divisor times the flag
		sum = sum.add((new BigInteger(flag).multiply(remainder.divide(divisor))));
// The new remainder is what is left times 10 for the next digit
		BigInteger newRemainder = remainder.remainder(divisor).multiply(BigInteger.TEN);
		BigInteger temp;
// If this is the last digit or the new remainder is 0 then stop
		if ((doDivNumber == (maxPlaces)) || (newRemainder.equals(BigInteger.ZERO))) {
// recalculate the sum as modulo 10 and pass anything greater than 10 to the previous digit
			temp = sum.divide(BigInteger.TEN);
			sum = sum.remainder(BigInteger.TEN);
// if sum is negative, then borrow one (10) from the previous digit
			if (sum.compareTo(BigInteger.ZERO) < 0) {
				sum = sum.add(BigInteger.TEN);
				temp = temp.subtract(BigInteger.ONE);
			}
// This returns to the previous digit
			return temp;
		} else {
// If this is not the last digit, then call the next digit and recalculate sum as before with the result
			sum = sum.add(nextDivisor.myDiv(newRemainder, divisor, flag));
			temp = sum.divide(BigInteger.TEN);
			sum = sum.remainder(BigInteger.TEN);
			if (sum.compareTo(BigInteger.ZERO) < 0) {
				sum = sum.add(BigInteger.TEN);
				temp = temp.subtract(BigInteger.ONE);
			}
			return temp;
		}
	}
/**
 * 
 * PrintSum can be used to print all digits of a calculation.
 * 
 */
// Print this digit
	public void printSum() {
		System.out.print(sum);
		if (doDivNumber == 0)
			System.out.print(".");
		if (doDivNumber < maxPlaces)
			nextDivisor.printSum();
	}
/**
 * 
 * getPi builds a StringBuilder String of the finished calculation. After appending the SUM to the String,
 * the next divider is called to repeat with its sum. This StringBuilder String is used to check against the 
 * reference pi String.
 * 
 */
	public void getPi(StringBuilder result) {
		result.append(sum.toString());
		if (doDivNumber == 0)
			result.append(".");
		if (doDivNumber < maxPlaces)
			nextDivisor.getPi(result);
	}
}
