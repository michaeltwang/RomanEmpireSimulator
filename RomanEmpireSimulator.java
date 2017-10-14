package game;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class RomanEmpireSimulator {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		Random rand = new Random();

		String[] hazards = { "Volcanic Eruption", "Hun raiding party", "Excessive wine party", "Plague" };
		int economicGrowth = 20;
		int numSpaceshipParts = 0;
		int larsFee = 30;
		int spaceshipPartsDropChance = 70; // Percentage
		int economicStrength = 0;
		int goldCoins = 100;
		int tax = 0;
		int progress = 0;
		boolean running = true;

		System.out.println("You are emperor/empress of the Roman Empire, but your lifelong dream is to go to outerspace."
				+ "\nLuckily, Lars the Builder from the year 2117 can build it for you, if he has the necessary spaceship "
				+ "\nparts. However, the Huns have stolen his parts, and even when he gets the parts, Lars requries payment."
				+ "\nIt's your job to get the necessary funds and parts, all while dealing with the hazards a large empire"
				+ "\nfaces year-to-year. Good luck!");
		System.out.println("\nAll hail the new Roman emperor/empress!");
		System.out.println("\n# GAME START # ");
		
		GAME:
		while (running) {
			System.out.println("----------------------------------------------------------------------------");

			int hazardCost = ThreadLocalRandom.current().nextInt(15, 35);
			String hazard = hazards[rand.nextInt(hazards.length)];
			System.out.println("\n\t# " + hazard + " has occurred! #\n");

			while (hazardCost > 0) {
				System.out.println("\tYour goldCoins: " + goldCoins);
				System.out.println("\t" + hazard + "'s Estimated Cost: " + hazardCost);
				System.out.println("\n\tWhat would you like to do?");
				System.out.println("\t1. Deal with hazard");
				System.out.println("\t2. Fund building of Spaceship");
				System.out.println("\t3. Ignore hazard");

				String input = in.nextLine();
				
				if (input.equals("1")) {
					int spendingCost = ThreadLocalRandom.current().nextInt(Math.abs(hazardCost-10), hazardCost + 10);

					hazardCost -= spendingCost;
					goldCoins -= spendingCost;

					System.out.println("\t> You help deal with the " + hazard + " by paying " + spendingCost + " gold coins");

					if (goldCoins < 1) {
						System.out.println("\t You have no more gold coins! Your empire is bankrupt!");
						break;
					}
				} else if (input.equals("2")) {

					if (numSpaceshipParts > 0) {
						goldCoins -= larsFee;
						progress += larsFee;
						numSpaceshipParts--;
						System.out.println("\t>    You paied Lars the Builder to work on the spaceship for: " + larsFee + "."
								+ "\n\t>    You now have" + goldCoins + " gold coins."
								+ "\n\t>    You now have" + numSpaceshipParts + " Spaceshhip parts left.\n");
						
					} else {
						System.out.println("\t> You have no Spaceship parts, fight the Huns for a chance to get one");
					}

				} else if (input.equals("3")) {
					System.out.println("\t> You ignore the problem and let local leaders deal with it");
					economicStrength = economicStrength / 2; 
					tax = economicStrength / 3;
					
					System.out.println("\t> The empire's economic strength is weakened to " + economicStrength);
					System.out.println("\t> You only collect " + tax + " in taxes");
					System.out.println("\t> You have " + (goldCoins + tax) + " gold coins left");
					if (goldCoins < 1) {
						System.out.println("\t You have no more gold coins! Your empire is bankrupt!");
						break;
					}
					continue GAME;
				} else {
					System.out.println("\tInvalid command");
				}
			}
			if (goldCoins < 1) {
				System.out.println("\nThe end is near. Enemy troops of your rival kingdom, taking "
						        + "\nadvantage of your weakness, have stormed the castle, the ");
				System.out.println("treasures you and your long and proud lineage accumulated ");
				System.out.println("are being looted mercilessly. You and the last of the loyal");
				System.out.println("royal guards are holed up in the throne room, waiting for the ");
				System.out.println("inevitable. Because of your poor leadership and decisions, your");
				System.out.println("people will be subjugated and your once great empire will be \n"
						+ "merely a footnote in the history books. Game over.");
				break;
			}
			if (progress >= 300 && economicStrength >= 50) {
				System.out.println("\nThe spaceship is complete. With a few feeble steps you walk ");
				System.out.println("toward the magnificent machine, awed by its towering size, it's ");
				System.out.println("shiny metal skin and the mighty engines below. You look toward ");
				System.out.println("Lars the Builder, who looks back at you with eyes of hope and ");
				System.out.println("satisfaction. You step into the door, looking back one last time ");
				System.out.println("at the empire you have ruled for the past few decades.\n"
						+ "But adventure beyond the Earth awaits as you look toward the stars!");
				break;
			}
			System.out.println("----------------------------------------------------------------------------");
			switch (hazard){
			case "Volcanic Eruption":
			case "Plague":
				System.out.println(" # Your spending on relief after the " + hazard + " has returned normalcy to the lives of the affected citizens! # ");
				break;
			case "Hun raiding party":
				System.out.println(" # The mighty Roman army has successfully driven off the Huns, thanks to your monetary support! # ");
				if (rand.nextInt(100) < spaceshipPartsDropChance) {
					numSpaceshipParts++;
					System.out.println(" # The Huns dropped a Spaceship part. # ");
					System.out.println(" # You now have " + numSpaceshipParts + " Spaceship part(s). # ");
				}
				break;
			case "Excessive wine party":
				System.out.println(" # Let the wine flow on the streets of Rome! You stock the party with the finest wine and join in on the fun # ");
				break;
			}
			economicStrength += rand.nextInt(economicGrowth);
			tax = economicStrength / 2;
			goldCoins += tax;
			System.out.println(" # The empire's economic strength is strengthened to " + economicStrength + " # ");
			System.out.println(" # You collect " + tax + " in taxes # ");
			System.out.println(" # You have " + goldCoins + " gold coins left # ");
			
			
			System.out.println("----------------------------------------------------------------------------");
			System.out.println("What would you like to do now?");
			System.out.println("1. Continue reign");
			System.out.println("2. Abdicate throne");
			String input = in.nextLine();

			while (!input.equals("1") && !input.equals("2")) {
				System.out.println("invalid command");
				input = in.nextLine();

			}
			if (input.equals("1")) {
				System.out.println("You continue to reign.");
			} else if (input.equals("2")) {
				System.out.println("You give up the throne to your oldest heir and start a successful career in comp sci.");
				break;
			}
		}
		System.out.println("######################");
		System.out.println("# THANKS FOR PLAYING #");
		System.out.println("######################");
	}
}