package mainpackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class Grades {
	
	public static void printgrade(String rn) throws IOException, FileNotFoundException {//students see their grades
		File file = new File(System.getProperty("user.dir")+"/src/grades.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		String str="No Grades";
		while((currentLine = reader.readLine()) !=null) {
			String[] words=currentLine.split(",");
			if(words[1].equals(rn)) {
				str=currentLine;
				System.out.println(str);
			}
		}reader.close();

		if(str.equals("No Grades"))
			System.out.println(str);
	}
	
	public static void viewgrade(String rn) throws IOException, FileNotFoundException {//professors view their students' grades
		File file1 = new File(System.getProperty("user.dir")+"/src/courses.txt");//we will find the professor's classes and save the class' code
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		File file2 = new File(System.getProperty("user.dir")+"/src/grades.txt");//we will find the student's grades with the help of the class' code
		
		String currentLine1,currentLine2;
		String show="no grades";
		String code ="";
		while((currentLine1 = reader1.readLine()) !=null) {
			String[] words1=currentLine1.split(",");
			if(words1[1].equals(rn)) {
				code=words1[0];
				System.out.println("For the class with code "+ code + " the grades are:");
				BufferedReader reader2 = new BufferedReader(new FileReader(file2));
				while((currentLine2 = reader2.readLine()) !=null) {
					String[] words2=currentLine2.split(",");
					if(words2[3].equals(code)) {
						show=currentLine2;
						System.out.println(show);
					}
					
				}

				reader2.close();
			}
		}					
		reader1.close();
	}
	
	
	public static void changegrade(String rn) throws IOException, FileNotFoundException {//professors change their students' grades
		System.out.println("we are in changegrade");
		File file1 = new File(System.getProperty("user.dir")+"/src/courses.txt");//we will find the professor's courses and save the course code
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));		
		String currentLine1,currentLine2;
		String show="no grades";
		Scanner input = new Scanner(System.in);
		String code ="";
		String grade,str;
		while((currentLine1 = reader1.readLine()) !=null) {
			String[] words1=currentLine1.split(",");
			if(words1[1].equals(rn)) {
				code=words1[0];
				System.out.println("For the class with code "+ code + " procceed with new grades:");
				File file2 = new File(System.getProperty("user.dir")+"/src/grades.txt");//we will find the student's grades with the help of the class' code
				BufferedReader reader2 = new BufferedReader(new FileReader(file2));
				File file3 = new File(System.getProperty("user.dir")+"/src/gradestemp.txt");//this is the temporary grade file
				BufferedWriter writer = new BufferedWriter(new FileWriter(file3));//will use it to write new grade file
				while((currentLine2 = reader2.readLine()) !=null) {
					String[] words2=currentLine2.split(",");
					if(words2[3].equals(code)) {
						System.out.println("Student "+words2[0]+ " with registration code "+words2[1]+" set grade to:");
						grade = input.nextLine();
						str=words2[0]+","+words2[1]+","+words2[2]+","+words2[3]+","+grade;
						writer.write(str + "\n");
					}
					else
						writer.write(currentLine2 + "\n");
				}
				reader2.close();
				writer.close();
				file2.delete();
				file3.renameTo(file2);
			}
		}					
		reader1.close();		
	}
	
}
