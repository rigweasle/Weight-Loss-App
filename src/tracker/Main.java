package tracker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
	

	public static void main(String[] args) {
		


	
	// Creating date variable
    LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
	String cd = today.format(formatter);
	Scanner in1 = new Scanner(System.in);
	
	// First question input
	System.out.println("Lets get started!");
	
	System.out.println("What is your name?");
		String username;
			username = in1.nextLine();

	// Second question input		
	System.out.println("What is your age?");
		while (!in1.hasNextInt())
		{
			System.out.println("Please use numbers");
			in1.next();
		}
		int currentAge = in1.nextInt();
	
	// Third question input
	System.out.println("What is your current weight?");
		int currentWeight = in1.nextInt();
	
	// Determining if you're losing or gaining weight. If within and If testing if first, the goal isn't equal and next, if it's positive or negative
	System.out.println("What is your goal weight?");
		int targetWeight = in1.nextInt();

		
		int goalDirection = (currentWeight - targetWeight);

			int wktgmod = 0;
			char goal = 'x';
			
			if (goalDirection == 0) {
				System.out.println("Looks like you've either met your goal or have not set an appropriate one.");
			}
			else if (goalDirection > 1) {
				 System.out.println("Looks like you're trying to lose weight.");
					goal = 'p';
					wktgmod = -1;			//Modifies what direction this goal will take.
			}
			else {
				System.out.println("Looks like you're trying to gain weight.\n");
					goal = 'n';
					wktgmod = 1;
			}
		
	// Determining the weekly target
	double weeklyTarget;
	weeklyTarget = 1.5;	
	System.out.println("The default weekly goal is a " + weeklyTarget + "lbs change per week.\nWould you like to adjust it?\n(type y or n)");
		String q2 = in1.next();
		char a1 = q2.charAt(0);
			if (a1 == 'n') {
				System.out.println("Good, let's proceed.\n");
			}
			else {
				System.out.println("What would you like to change it to?");
					Scanner q3 = new Scanner (System.in);
					double q4 = q3.nextDouble();
					weeklyTarget = q4;
					q3.close();
			}
		
		System.out.println("The weekly target has been set to:\n" + weeklyTarget + "lbs per week.\n");
		double finalMod;
		finalMod = weeklyTarget * wktgmod;
		String fnmd = String.valueOf(finalMod);

		//Calculating how many weeks will be necessary to achieve the goal
		double wkcalc = Math.abs((currentWeight - targetWeight) / finalMod);
		int wknd = (int) Math.ceil(wkcalc);
		String wkndst = String.valueOf(wknd);
		
		//Determining what date you should expect to reach your goal
	    LocalDate gd = today.plus(wknd, ChronoUnit.WEEKS);
		String goalDate = gd.format(formatter);

	System.out.println("To reach your goal of " + targetWeight + "lbs, then you will need " + wknd + " weeks to achive this goal.\n");
	
	System.out.println("The current date is:\n" + cd + "\n");
	
	System.out.println("If all goes well, then you should expect to meet your goal by:\n" + goalDate);

		
	String fileoutln1 = (username + ',' + currentAge + ',' + currentWeight + ',' + targetWeight);
	String fileoutln3 = (fnmd + ',' + wkndst + ',' + goal +  "," + goalDate);

	int dtcount = 0;
	
		//Writing all the collected values for the previous questions to a file
		try {
		PrintWriter writer;
		try {
			writer = new PrintWriter("goal.txt", "UTF-8");
			writer.println(fileoutln1);
			writer.println("");
			writer.println(fileoutln3);
			writer.println("date setup");
			writer.println(cd);
			writer.println("");
			while (dtcount <= wknd) {
				LocalDate gd1 = today.plus(dtcount, ChronoUnit.WEEKS);
				String gdate = gd1.format(formatter);
				double rcdt = (currentWeight + (finalMod * dtcount));
				dtcount++;
				writer.println(gdate + ',' + " " + rcdt + "lbs");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in1.close();
		
	}
}
