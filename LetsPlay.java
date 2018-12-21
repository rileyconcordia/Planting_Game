//..........................................................................................
//Assigement 4 - Crazy Nancy’s garden Game
//Written by: Kwok Ho, Chan 40091211
//COMP 248 (Section R) - Fall 2018
//..........................................................................................
import java.util.Random;
import java.util.Scanner;
public class LetsPlay 
{
	public static void welcome()
	{
		//Welcome message
		System.out.println("-------****-------****-------****-------****-----****-----");
		System.out.println("       Welcome to Crazy Nancy's Garden Game!");
		System.out.println("-------****-------****-------****-------****-----****-----");

		//Rules
		System.out.println("To win this game you need some luck with the dice and a bit of strategy.");
		System.out.println("Your garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A tree takes 4 spots (2x2).");
		System.out.println("You roll the dice and based on the outcome you get to plant a pre-set number of trees and flowers.");
		System.out.println(""); //spacing
		System.out.println("Rolls and their outcome:");
		System.out.println("---------------------");
		System.out.println("3: plan a tree (2x2) and a flower (1x1)");
		System.out.println("6: plant 2 flowers (2 times 1x1)");
		System.out.println("12: plant 2 trees (2 times 2x2)");
		System.out.println("5 or 10: the rabbit will eat something that you have planted - might be a flower or part of a tree(1x1)");
		System.out.println("Any other EVEN rolls: plant a tree (2x2)");
		System.out.println("Any other ODD rolls: plant a flower (1x1)");
		System.out.println(""); //spacing
		System.out.println("Minimum number of players: 2.");
		System.out.println("Minimum garden size: 3x3.");
		System.out.println("You can only plant in empty locations. To plant a tree, you give the top left coordinates of the 2x2 space");
		System.out.println("and I will check to make sure all 4 locations are free.");
		System.out.println("Okay ... Let's start the game! May the best gardener win!!!");
		System.out.println(""); //spacing
		System.out.println("The default garden size is a 3x3 square. You can use this default board size or change the size");
		System.out.println(""); //spacing
	}

	public static void game()
	{
		Scanner sc = new Scanner(System.in);
		int k = 0;
		int temp = 0;

		boolean valid; // use to verify user input


		//1st question
		int q1;
		do 
		{
			System.out.print("Enter 0 to use the default garden size or -1 to enter your own size: ");
			q1 = sc.nextInt();
			valid = (q1 == 0) || (q1==-1);

			if(!valid)
			{
				System.out.println("Sorry but " + q1 + " is not a legal choice. ");
				//q1 = sc.nextInt();
			}
		}
		while (!valid);
		//end of 1st question


		//customized size of garden
		int customized_plane=0;
		if (q1 == 0)
		{
			customized_plane = 3;
			Garden plane = new Garden(customized_plane);
		}

		else if (q1 == -1)//come back later
		{
			do
			{
				System.out.print("What size board would you like? (minimum size 3) ");
				customized_plane = sc.nextInt();
				Garden plane = new Garden(customized_plane);
			}
			while(customized_plane < 3);

		}
		//end of customized size of garden


		//2nd question
		int q2;
		do 
		{
			System.out.print("How many gardeners will there be (minimum 2 required to play, max allowed 10)? ");
			q2 = sc.nextInt();
			valid = (q2 >= 2) && (q2 <= 10);

			if(!valid)
			{
				System.out.println("** Sorry but " + q2 + " is not a legal number of players.");
				//q1 = sc.nextInt();
			}
		}
		while (!valid);
		//end of 2nd question


		String [] players_name = new String[q2];
		for (int i = 0; i < q2; i++)
		{
			System.out.print("--> Name of player " + (i+1) + "(no spaces allowed): ");
			players_name[i] = sc.next();
		}


		Player arrPlayer [] = new Player [q2];
		Player arrSortedPlayer [] = new Player [q2];
		for (int i = 0; i < q2;i++)
		{
			arrPlayer [i] = new Player(players_name[i], customized_plane);
			arrPlayer [i].setName(players_name[i]);
			//System.out.println(players_name[i]); //to test info store inside only
		}


		//to see who go first

		System.out.println("Let's see who goes first ...");
		System.out.println("If the same numbers are rolled, the process will be started over. ");

		//decide who go first
		Dice roll = new Dice();
		int arr_Dice [] = new int [q2];
		int arr_Dice2 [] = new int [q2];

		//roll dice and see if the same dice are shown
		arr_Dice2 = arr_Dice;
		do 
		{
			//roll dice
			for (int i = 0; i < q2; i++)
			{
				arr_Dice[i] = roll.rollDice(); 
				System.out.println(arrPlayer[i].getName() + " rolled a " + arr_Dice[i]);
			}

			//check if same numbers are shown
			valid = true;
			for (int i = 0; i < q2; i++)
			{
				for (int j = 0; j < q2; j++)
				{
					if (arr_Dice[i] == arr_Dice2[j] && i != j)
					{
						arr_Dice[i] = roll.rollDice();
						valid = false;
					}
				}
			}
			System.out.println("");
		}while(!valid);


		//sort the players according to the result above
		for(int i = 12; i >= 2; i--)
		{
			for(int j = 0; j<q2;j++)
			{
				if (arr_Dice[j] == i)
				{
					arrSortedPlayer[k] = arrPlayer[j];
					k++;
				}
			}
		}





		//show who play first
		System.out.println("The order will be: ");
		for(int i = 0; i<q2; i++)
		{
			System.out.println((i+1) + ": " + (arrSortedPlayer[i]).getName());
		}
		System.out.println("");


		//to play
		System.out.println("Time to play!!!!");
		System.out.println("------------------ \n");


		//initialize garden
		int r;
		int c;
		boolean check = false;
		String winner = "";
		int playerRolls;

		//to check 
		//long while-loop
		while (winner == "")
		{
			for(int i = 0; i < arrSortedPlayer.length; i++) 
			{
				playerRolls = roll.rollDice();
				System.out.println("");
				System.out.println(arrSortedPlayer[i].getName() + " you rolled " + playerRolls + " " + roll.toString());

				if (winner != "")  // to check whether someone wins
				{break;}  // break out of rounds

				//start playing
				//rolls 3
				if (playerRolls == 3)
				{
					System.out.println("You must plant a tree (2x2) and a flower (1x1) ");
					System.out.println(arrSortedPlayer[i].toString());
					//plant tree
					if (arrSortedPlayer[i].howManyTreesPossible() == 0)
					{
						System.out.println("** Sorry no room left to plant a tree - You miss a turn");
						break;
					}

					//check if user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}

					else 
					{
						System.out.print("");
						System.out.println("You have " + arrSortedPlayer[i].howManyTreesPossible() + " places to plant a tree.");
						System.out.print("Enter coordinates as row column: ");


						do
						{
							r = sc.nextInt();
							c = sc.nextInt();

							check = arrSortedPlayer[i].checkTree(r, c, customized_plane);
						}
						while(check == false);

						arrSortedPlayer[i].plantTreeInGarden(r, c);
					}
					//check if user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}

					//plant flowers
					else 
					{
						System.out.print("");
						System.out.println("Now you must plant a flower (1x1)");
						System.out.println(arrSortedPlayer[i].toString());
						System.out.println("You have " + arrSortedPlayer[i].howManyFlowersPossible() + " places to plant a flower.");
						System.out.println("Enter coordinates as row column: ");

						do
						{
							r = sc.nextInt();
							c = sc.nextInt();

							check = arrSortedPlayer[i].checkFlower(r, c, customized_plane);
						}
						while(check == false);

						arrSortedPlayer[i].plantFlowerInGarden(r, c);

						//check if the user has won
						if (arrSortedPlayer[i].isGardenFull() == true)
						{
							winner = arrSortedPlayer[i].getName();
							System.out.println("The winner is " + winner);
							System.out.println(arrSortedPlayer[i].toString());
							break;
						}
					}
				}
				//end of rolls 3




				//rolls 6
				else if (playerRolls == 6)
				{
					System.out.println("You must plant 2 flowers (2 times 1x1) ");
					System.out.println(arrSortedPlayer[i].toString());
					System.out.println("You have " + arrSortedPlayer[i].howManyFlowersPossible() + " places to plant a flower.");
					System.out.println("Enter coordinates as row column: ");


					//plant 1st flower
					do
					{
						r = sc.nextInt();
						c = sc.nextInt();

						check = arrSortedPlayer[i].checkFlower(r, c, customized_plane);
					}
					while(check == false);

					arrSortedPlayer[i].plantFlowerInGarden(r, c);

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}


					//plant 2nd flower
					System.out.println("Please plant the second flower (1x1)");
					System.out.println(arrSortedPlayer[i].toString());
					System.out.println("You have " + arrSortedPlayer[i].howManyFlowersPossible() + " places to plant a flower.");
					System.out.println("Enter coordinates as row column: ");


					do
					{
						r = sc.nextInt();
						c = sc.nextInt();

						check = arrSortedPlayer[i].checkFlower(r, c, customized_plane);
					}
					while(check == false);

					arrSortedPlayer[i].plantFlowerInGarden(r, c);

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}
				}
				//end of rolls 6




				//rolls 12
				else if (playerRolls == 12)
				{
					System.out.println("You must plant 2 trees (2 times 2x2)");
					System.out.println("Plant the trees (2 times 2x2)");
					System.out.println(arrSortedPlayer[i].toString());
					if (arrSortedPlayer[i].howManyTreesPossible() == 0)
					{
						System.out.println("** Sorry no room left to plant a tree - You miss a turn ");
					}


					else 
					{
						System.out.println("You have " + arrSortedPlayer[i].howManyTreesPossible() + " places to plant a tree.");
						System.out.print("Enter coordinates as row column: ");

						//plant 1st tree
						do
						{
							r = sc.nextInt();
							c = sc.nextInt();

							check = arrSortedPlayer[i].checkTree(r, c, customized_plane);
						}
						while(check == false);

						arrSortedPlayer[i].plantTreeInGarden(r, c);
					}

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}

					//plant 2nd tree
					System.out.println("Please plant the second flower (1x1) ");
					System.out.println(arrSortedPlayer[i].toString());
					if (arrSortedPlayer[i].howManyTreesPossible() == 0)
					{
						System.out.println("** Sorry no room left to plant a tree - You miss a turn ");
					}

					else 
					{
						System.out.println("You have " + arrSortedPlayer[i].howManyTreesPossible() + " places to plant a tree.");
						System.out.print("Enter coordinates as row column: ");

						if (arrSortedPlayer[i].howManyTreesPossible()== 0)
						{
							System.out.println("** Sorry no room left to plant a tree - You miss a turn ");
							break;
						}

						do
						{
							r = sc.nextInt();
							c = sc.nextInt();

							check = arrSortedPlayer[i].checkTree(r, c, customized_plane);
						}
						while(check == false);

						arrSortedPlayer[i].plantTreeInGarden(r, c);
					}

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}
				}
				//end of roll 12




				//rolls 2, 4 or 8
				else if (playerRolls == 2 || playerRolls == 4 || playerRolls == 8)
				{
					System.out.println("You must plant a tree (2x2)");
					System.out.println(arrSortedPlayer[i].toString());
					if (arrSortedPlayer[i].howManyTreesPossible() == 0)
					{
						System.out.println("** Sorry no room left to plant a tree - You miss a turn");
					}


					else 
					{
						System.out.println("You have " + arrSortedPlayer[i].howManyTreesPossible() + " places to plant a tree.");
						System.out.println("Enter coordinates as row column: ");


						//plant 1st tree
						do
						{
							r = sc.nextInt();
							c = sc.nextInt();

							check = arrSortedPlayer[i].checkTree(r, c, customized_plane);
						}
						while(check == false);

						arrSortedPlayer[i].plantTreeInGarden(r, c);
					}

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}
				}
				//end of rolls 2, 4 or 8



				//rolls 7, 9 or 11
				else if (playerRolls == 7 || playerRolls == 9 || playerRolls == 11)
				{
					System.out.println("You must plant a flower (1x1). ");
					System.out.println(arrSortedPlayer[i].toString());
					System.out.println("Enter coordinates as row column: ");

					//plant the flower
					do
					{
						r = sc.nextInt();
						c = sc.nextInt();

						check = arrSortedPlayer[i].checkFlower(r, c, customized_plane);
					}
					while(check == false);

					arrSortedPlayer[i].plantFlowerInGarden(r, c);

					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}
				}
				//end of rolls 7, 9 or 11



				//rolls 5 or 10
				else if (playerRolls == 5 || playerRolls == 10)
				{
					System.out.println("");
					//to check if there are somethings for the rabbit to eat
					int checkEmpty = 0;
					for (int m=0; m < customized_plane; m++)
					{
						for (int n=0; n < customized_plane; n++)
						{
							if (arrSortedPlayer[i].whatIsPlanted(m, n) == '-')
							{checkEmpty = checkEmpty + 1;}
						}
					}

					if (checkEmpty == Math.pow(customized_plane, 2))
					{
						System.out.println("The rabbit ate nothing since there is nothing in the garden. ");
						System.out.println(arrSortedPlayer[i].toString());
					}

					else
					{
						System.out.println(arrSortedPlayer[i].toString());
						do
						{
							r = (int)(Math.random()*(customized_plane));
							c = (int)(Math.random()*(customized_plane));
						}
						while(arrSortedPlayer[i].whatIsPlanted(r, c) == '-');

						arrSortedPlayer[i].eatHere(r, c);

						System.out.println("The rabbit ate whatever was planted in location (" + r + "," + c + ") ");

						System.out.println(arrSortedPlayer[i].toString());

					}
					//check if the user has won
					if (arrSortedPlayer[i].isGardenFull() == true)
					{
						winner = arrSortedPlayer[i].getName();
						System.out.println("The winner is " + winner);
						System.out.println(arrSortedPlayer[i].toString());
						break;
					}
				}
				//end of rolls 5 or 10
			}//end of very long for-loop
		}//end of very long while-loop
		sc.close();
	}

	public static void main(String[] args) 
	{
		welcome();

		game();

	}


}
