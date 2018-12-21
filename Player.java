//..........................................................................................
//Assigement 4 - Crazy Nancy’s garden Game
//Written by: Kwok Ho, Chan 40091211
//COMP 248 (Section R) - Fall 2018
//..........................................................................................
public class Player 
{
	private String name;
	private Garden garden; //int []
	
	
	
//public Player(String name)
//{
//	this.name = name;
//	Garden garden = new Garden();
//}

public Player(String name, int size)
	{
	name = name;
	garden = new Garden(size);
	}
public String getName()
	{
	return name;
	}	

public void setName(String name)
	{
	this.name = name;
	}


public int howManyFlowersPossible()
	{
	return garden.countPossibleFlowers();
	}

public int howManyTreesPossible()
	{
	return garden.countPossibleTrees();
	}

public int whatIsPlanted(int r, int c)
	{
	return garden.getInLocation(r, c);
	}

public void plantTreeInGarden(int r, int c)
	{
	garden.plantTree(r, c);
	}


public void plantFlowerInGarden(int r, int c)
	{
	garden.plantFlower(r, c);
	}

public void eatHere(int r, int c)
	{
	garden.removeFlower(r, c);
	}

public boolean isGardenFull()
	{
	return garden.gardenFull();
	}

public String showGarden()
	{
	return garden.toString();
	}

public boolean checkTree(int r, int c, int size) {
	
	if (r < 0 || r > (size-2) || c < 0 || c > (size-2))
		{
		System.out.println("** Sorry that location is out of the board range."); 
		System.out.println("Please enter a new set of coordinates. ");
		 return false;
		}
	 
	else if (whatIsPlanted(r, c) == 't')
		{
		System.out.println("** Sorry that location is already taken up by a t ");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
		}
	
	else if (whatIsPlanted(r+1, c) == 't') 
		{
		System.out.println("** Sorry that location is already taken up by a t ");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
		}
	 
	else if (whatIsPlanted(r, c+1) == 't') 
		{
		System.out.println("** Sorry that location is already taken up by a t ");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
		}
	 
	else if (whatIsPlanted(r+1, c+1) == 't')
		{
		System.out.println("** Sorry that location is already taken up by a t ");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
		}
	
	else if (whatIsPlanted(r, c) == 'f') 
	{
	System.out.println("** Sorry that location is already taken up by a f");
	System.out.println("Please enter a new set of coordinates: ");
	return false;
	}
	
	 else
		 return true;
	 
}


public boolean checkFlower(int r, int c, int size) {
	if (r < 0 || r >= size || c < 0 || c >= size) 
	{
		System.out.println("** Sorry that location is out of the board range."); 
		System.out.println("Please enter a new set of coordinates. ");
		return false;
	}

	else if (whatIsPlanted(r, c) == 't')
	{
		System.out.println("** Sorry that location is already taken up by a t ");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
	}

	else if (whatIsPlanted(r, c) == 'f') 
	{
		System.out.println("** Sorry that location is already taken up by a f");
		System.out.println("Please enter a new set of coordinates: ");
		return false;
	}

	else
		return true;
}

@Override
public String toString()
	{
	return garden.toString();
	}


}
