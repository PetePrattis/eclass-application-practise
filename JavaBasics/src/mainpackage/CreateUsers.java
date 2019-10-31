package mainpackage;

import java.util.Arrays;
import java.util.Scanner;

import mainpackage.Professors;
import mainpackage.Secretaries;
import mainpackage.Students;

import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.charset.Charset;

public class CreateUsers {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner input= new Scanner(System.in);		
		int exit=0;
		String answer1;
		answer1="";
		//we will loop until user want to exit the application
		while(exit==0){
			
			//ask the user if he is a student, professor or a secretary
			System.out.println("Are you a student, a professor or a secretary, or do you want to exit application?");					    			
			answer1 = input.nextLine();			
			//inside the if structures we will loop until the user wishes to exit the application
			if(answer1.equals("student")) {
				//now you go at the Students class' main method
				Students.signin();
				System.out.println("You are now at the main menu");
			}
			else if(answer1.equals("professor")) {
				//now you go to Professors class's main method
				Professors.signin();
				System.out.println("You are now at the main menu");
			}
			else if(answer1.equals("secretary")) {
				//now you go to Secretaries class's main method
				Secretaries.signin();
				System.out.println("You are now at the main menu");
			}
			else if(answer1.equals("exit")) {
				System.out.println("exiting application...");
				//System.exit(1);
				exit=1;
			}
			else
				System.out.println("Please input a valid option, 'student', 'professor', 'secretary' or exit");
		}
	}
	
}
