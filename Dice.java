//..........................................................................................
//Assigement 4 - Crazy Nancy’s garden Game
//Written by: Kwok Ho, Chan 40091211
//COMP 248 (Section R) - Fall 2018
//..........................................................................................
import java.util.Random;
public class Dice 
{
	private int die_1;
	private int die_2;

public Dice()
	{	
	this.die_1 = 0;
	this.die_2 = 0;
	}

public int getDie_1()
	{
	return die_1;
	}

public int getDie_2()
	{
	return die_2;
	}

public int getDice()
	{
	return (die_1+die_2);
	}


public int rollDice()
	{
	final int MAX = 6;
	final int MIN = 1;
	Random rd = new Random();
	die_1 = rd.nextInt(MAX - MIN)+1 + MIN;
	die_2 = rd.nextInt(MAX - MIN)+1 + MIN;
	return die_1 + die_2;
	}

@Override
public String toString()
	{
	return 	"(Die 1: " + die_1 + " Die 2: " + die_2 + ")";
	}

}
