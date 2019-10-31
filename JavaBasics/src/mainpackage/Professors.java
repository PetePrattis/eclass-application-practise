package mainpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import mainpackage.Users;

public class Professors extends Users{
	private final String nameoflesson;
	private final String kodikos;
	private String strp;

	public Professors(String un, String n, String sn, String dp, String nol, String kod) {
		super(un, n, sn, dp);
		this.nameoflesson = nol;
		this.kodikos = kod;
	}
	public String getnameoflesson() {
		return nameoflesson;
	}
	public String getkodiko() {
		return kodikos;
	}
	
	public static void signin() throws IOException, FileNotFoundException{//this is signin method, it will check the account's information
		Scanner input1= new Scanner(System.in);		
		File file = new File(System.getProperty("user.dir")+"/src/professorsfile.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		int found=0;
		System.out.println("Give username and registration number to login as professor");
		System.out.println("Give username:");			
		String un = input1.nextLine();
		System.out.println("Give registration number:");
		String am = input1.nextLine();
		while((currentLine = reader.readLine()) !=null) {
			String[] words=currentLine.split(",");
				
			if(words[0].equals(un) && words[3].equals(am) && found==0){//if signin is successful 
				System.out.println("User log in successful. Professor's information:");
				System.out.println(currentLine);
				found=1;
				Professors.choice(am);
			}
			if(found==1)
				break;
			}

			if(found==0) {
				System.out.println("Wrong username or password, or professor doesn't exist.");
				Professors.signin();
			}
			//input1.close();	
			reader.close();
	}
	
	static void choice(String am) throws IOException, FileNotFoundException{//here the professor can choose an action
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to view the grades of your students, change their grades or log off? please answer 'a' or 'b' or 'c'");
		String answer2 = input.nextLine();
		if(answer2.equals("a")) {
			Grades.viewgrade(am);
			Professors.choice(am);
		}
		else if (answer2.equals("b")) {
			Grades.changegrade(am);
			Professors.choice(am);
		}
		else if (answer2.equals("c"))
			//input.close();
			System.out.println("Logging off...");
		else{
			System.out.println("Wrong input");
			Professors.choice(am);
		}
			
	}

}
