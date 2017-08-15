package Pi;
/**
 * Calculate pi to the best of our ability using different series expansions.
 * The Gregory-Leibniz series which converges slowly, the divisor is approximately 2n.
 * The Nilakantha series which converges faster, it uses divisors approximately 2n cubed.
 * The Bailey-Borwein-Plouffe which converges even faster, it uses divisors 16^n,
 * each iteration had 4 sums.
 * The Cheat is a single division 355/113
 * 
 */
import java.util.Scanner;

public class CalcPi {

	public static void main(String[] args) {
		StringBuilder instructions = new StringBuilder("Usage:\njava -jar CalcPi.jar nGL nN nBBP [YA]\n");
		instructions.append("nGL is the number of iterations (in Millions) for the Gregory-Leibniz Series.\n");
		instructions.append("nN  is the number of iterations (in Millions) for the Nilakantha Series.\n");
		instructions.append("nGL is the number of iterations for the Bailey-Borwein-Plouffe Series.\n");
		instructions.append("Add a Y to the end if the valid digits are to be printed.\n");
		instructions.append("Or an A to the end if all digits are to be printed.\n");
		
		if(args.length < 3) {
			System.out.println(instructions);
			System.exit(0);
		}
// Instantiate the director and start the calculations
		Scanner s = new Scanner(args[0]);
		int argsGL = 0;
		int argsN = 0;
		int argsBBP = 0;
		if (s.hasNextInt()) {
			argsGL = s.nextInt();
		} else {
			System.out.println("Incorrect parameter #1: \"" + args[0] + "\"");
			System.out.println(instructions);
			System.exit(0);
		}
		s = new Scanner(args[1]);
		if (s.hasNextInt()) {
			argsN = s.nextInt();
		} else {
			System.out.println("Incorrect parameter #2: \"" + args[1] + "\"");
			System.out.println(instructions);
			System.exit(0);
		}
		s = new Scanner(args[2]);
		if (s.hasNextInt()) {
			argsBBP = s.nextInt();
		} else {
			System.out.println("Incorrect parameter #3: \"" + args[2] + "\"");
			System.out.println(instructions);
			System.exit(0);
		}
		int print = 0;
		int all = 0;
		if((args.length == 4) && ((args[3].contains("Y")) || (args[3].contains("y")))) {
			print = 1;
			all = 0;
	}
		if((args.length == 4) && ((args[3].contains("A")) || (args[3].contains("a")))) {
			print = 0;
			all = 1;
	}
		Thread t0 = null;
		Thread t1 = null;
		Thread t2 = null;
		PiDirector pi0 = null;
		PiDirector pi1 = null;
		PiDirector pi2 = null;
		if(argsGL != 0) {
			pi0 = new PiDirector(0, argsGL);	// Gregory-Leibniz Series
			t0 = new Thread(pi0);
		}
		if(argsN != 0) {
			pi1 = new PiDirector(1, argsN);	// Nilakantha Series
			t1 = new Thread(pi1);
		}
		if(argsBBP != 0) {
			pi2 = new PiDirector(2, argsBBP);	// Bailey-Borwein-Plouffe Series
			t2 = new Thread(pi2);
		}
		PiDirector pi3 = new PiDirector(3, 1);	// Cheat
		Thread t3 = new Thread(pi3);
		if(t0 != null) t0.start();
		if(t1 != null) t1.start();
		if(t2 != null) t2.start();
		t3.start();
		try {
			if(t0 != null) t0.join();
			if(t1 != null) t1.join();
			if(t2 != null) t2.join();
			t3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n*10000 is the max that can be checked");

		if(print == 1) {
			pi3.print("\nCheat (355/113)");
			if(pi0 != null) pi0.print("Gregory-Leibniz Series");
			if(pi1 != null) pi1.print("Nilakantha Series");
			if(pi2 != null) pi2.print("Bailey-Borwein-Plouffe Series");
		}
		if(all == 1) {
			pi3.print("\nCheat (355/113)");
			if(pi0 != null) pi0.printAll("Gregory-Leibniz Series*");
			if(pi1 != null) pi1.printAll("Nilakantha Series*");
			if(pi2 != null) pi2.printAll("Bailey-Borwein-Plouffe Series*");
			System.out.println("\n*Not all numbers printed are valid.");
		}
	}
}
