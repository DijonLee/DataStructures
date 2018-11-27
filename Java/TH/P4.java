import java.util.Scanner;

public class P4 {
static int c1;
static int c2;
static int c3;

	public static void main(String[] args) {

		// Input number of cities
		// number of roads
		System.out.println("Enter Number of cities");
		Scanner sc = new Scanner(System.in);
		int numCities = sc.nextInt();
		System.out.println("Okay, there are" +numCities);

		System.out.println("Enter Number of roads");
		Scanner sc2 = new Scanner(System.in);
		int numRoads = sc2.nextInt();
		System.out.println("Okay, there are" +numRoads);
		sc2.close();
		
		
		c1 = 7;
		c2 = 7;
		c3 = 10;
		// c1 c2 t
		// City 1 - City 2-
		// Time to gone from C1 to C2
		// 7 7 10
		//


	}

}
