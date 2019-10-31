package mainpackage;

import java.util.Scanner;
import java.util.*;

import mainpackage.Users;

import java.util.Arrays;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Students extends Users{
	 
	private final String registrationNumber;
	private String str;
	
	public Students(String un, String n, String sn, String dp,String rn) {
		super(un, n, sn, dp);
		this.registrationNumber = rn;
	}

	public String getregistrationnumber() {
		return registrationNumber;
	}
	
	
	
	public static void signin() throws IOException, FileNotFoundException{//this is signin method, it will check the account's information
		Scanner input1= new Scanner(System.in);		
		File file = new File(System.getProperty("user.dir")+"/src/studentsfile.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		int found=0;
		System.out.println("Give username and registration number to login as student");
		System.out.println("Give username:");			
		String un = input1.nextLine();
		System.out.println("Give registration number:");
		String am = input1.nextLine();
		while((currentLine = reader.readLine()) !=null) {
			String[] words=currentLine.split(",");
				
			if(words[0].equals(un) && words[4].equals(am) && found==0){//if signin is successful the student can choose an action
				System.out.println("User log in successful. Student's information:");
				System.out.println(currentLine);
				found=1;
				Students.choice(am);
			}
			if(found==1)
				break;
			}

			if(found==0) {
				System.out.println("Wrong username or password, or student doesn't exist.");
				Students.signin();
			}
			//input1.close();	
			reader.close();
	}
	
	static void choice(String am) throws IOException, FileNotFoundException{//here the student can choose an action
		Scanner input = new Scanner(System.in);
		System.out.println("Do you want to check your grades or log off? please answer 'a' or 'b'");
		String answer2 = input.nextLine();
		if(answer2.equals("a")) {
			Grades.printgrade(am);
			Students.choice(am);
		}
		else if (!answer2.equals("b")) {
			System.out.println("Wrong input");
			Students.choice(am);
		}
		else
			//input.close();
			System.out.println("Logging off...");
	}
		
}
