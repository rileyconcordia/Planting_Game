//..........................................................................................
//Assigement 4 - Crazy Nancy’s garden Game
//Written by: Kwok Ho, Chan 40091211
//COMP 248 (Section R) - Fall 2018
//..........................................................................................
import java.util.Random;
import java.util.Scanner;
public class Garden 
{
	private char garden[][];

	//default
	public Garden()
	{
		this.garden = new char [3] [3];	
		initializeGarden();
	}	

	public Garden(int a)
	{
		this.garden = new char [a][a];
		initializeGarden();
	}

	private void initializeGarden()
	{
		for (int i=0; i<garden.length; i++)
		{
			for (int j=0; j<garden.length;j++)
			{
				garden[i][j] = '-';
			}
		}
	}

	public char getInLocation(int r,int c)
	{
		return garden[r][c];
	}

	public void plantFlower(int r,int c)
	{
		garden[r][c]= 'f';
	}

	public void plantTree(int r,int c)
	{
		garden[r][c]=  't';
		garden[r][c+1]=  't';
		garden[r+1][c]=  't';
		garden[r+1][c+1]=  't';
	}

	public void removeFlower(int r,int c)
	{
		garden[r][c] = '-';
	}

	public int countPossibleTrees()
	{
		int count = 0;
		for(int i = 0; i < garden.length - 1;i++)
		{
			for (int j = 0; j<garden[i].length - 1; j++)
			{
				if (garden[i][j] == '-' && garden[i][j+1] =='-' && garden[i+1][j]=='-' && garden[i+1][j+1]=='-')
				{count = count + 1;}
			}
		}
		return count;
	}

	public int countPossibleFlowers()
	{
		int count = 0;
		for(int i = 0; i < garden.length;i++)
		{
			for (int j = 0; j<garden.length; j++)
			{
				if (garden[i][j] == '-')
				{count = count + 1;}
			}
		}
		return count;
	}

	public boolean gardenFull()
	{
		if (countPossibleFlowers() == 0)
		{return true;}
		else
		{return false;}

	}

	@Override
	public String toString()
	{
		String z = "   |";

		for (int k=0; k<garden.length; k++)
		{
			z = z + "  ";
			z = z + k;

		}

		for (int m=0; m<garden.length; m++)
		{
			z = z + "\n\n";
			z = z + m;

			if (m > 10)
			{z = z + " |";}

			else
			{z = z + "  |";}


			for (int n = 0; n < garden.length; n++)
			{
				if (n >= 10)
				{
					z = z + ("   " + garden[m][n]);
				}

				else
				{
					z = z + ("  " +  garden[m][n]);
				}
			}

		}
		return z;





	}

}