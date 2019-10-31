package mainpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Courses {
	static void newcourse() throws IOException, FileNotFoundException{//creating a new course
		File file1 = new File(System.getProperty("user.dir")+"/src/courses.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/src/courses.txt", true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);
		System.out.println("You are about to create a new course, make sure that the course code is unique and its department valid");
		Scanner input= new Scanner(System.in);
		String currentLine1,cc,cd;
		String str="";
		int duplicate =0;//this will help us check if the course code is not unique
		//if code is not unique we will go back to the choice method
		System.out.println("Give course code: ");
		cc = input.nextLine();
		System.out.println("Give course department: ");
		cd = input.nextLine();
		while (true) {
	        if(cd.equals("informatics") || cd.equals("economics") || cd.equals("shipping")){
	            break;
	        }
			System.out.println("Our university has the 3 departments informatics, economics and shipping, insert again one of these: ");
	        cd = input.nextLine();
	    }
		str=cc+","+"none"+","+cd;
		while((currentLine1 = reader1.readLine()) != null) {//check for duplicate course code
			String[] words1=currentLine1.split(",");
			if(words1[0].equals(cc))
					duplicate=1;						
		}
		if(duplicate==1)
			System.out.println("The course code is not unique, you will now go back to secretary menu.");
		else if(duplicate==0) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));
			out.println(str);
			out.close();
			//input.close();
			writer.close(); 
			reader1.close(); 
		}
		
	}
	
	
	static void assigntostudents(int visits) throws IOException, FileNotFoundException{//we will check if there is any student that doesn't have a course and grade assigned inside grades.txt
		File file1 = new File(System.getProperty("user.dir")+"/src/courses.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));		
		String cc,cd,un,rn,d,gun,grn,gd,gcc;
		String currentLine1,currentLine2,currentLine3;
		while((currentLine1 = reader1.readLine()) != null) {//for every course
			String[] words1=currentLine1.split(",");
			cc=words1[0];
			cd=words1[2];
			File file2 = new File(System.getProperty("user.dir")+"/src/studentsfile.txt");
			BufferedReader reader2 = new BufferedReader(new FileReader(file2));
			while((currentLine2 = reader2.readLine()) != null) {//for every student that is in the department that the previous course belongs to
				String[] words2=currentLine2.split(",");
				un=words2[0];
				d=words2[3];
				rn=words2[4];
				if(d.equals(cd)) {//if course department is the same to the student's department
					int exists=-1;//we will check if the student is inside grades.txt already but with not that course assigned to him
					//if one of exists becomes 1, that means that we need to assign the course to that student
					File file3 = new File(System.getProperty("user.dir")+"/src/grades.txt");
					BufferedReader reader3 = new BufferedReader(new FileReader(file3));
					OutputStreamWriter writer1 = new OutputStreamWriter(
			                new FileOutputStream(System.getProperty("user.dir")+"/src/grades.txt", true), "UTF-8");
					BufferedWriter writer = new BufferedWriter(writer1);
					while((currentLine3 = reader3.readLine()) != null) {//we check inside grades.txt if that student has this course assigned to him
						String[] words3=currentLine3.split(",");
						grn=words3[1];
						gun=words3[0];
						gcc=words3[3];
						gd=words3[2];
						if(grn.equals(rn) && gun.equals(un) && gd.equals(d) && !gcc.equals(cc) && exists!=0) {//if student is inside grades.txt but with not that course assigned and we haven't found him already with course assigned
							exists=1;
						}
						else if(grn.equals(rn) && gun.equals(un) && gd.equals(d) && gcc.equals(cc)) {//if student is inside grades.txt and that course is assigned to him
							exists=0;//as soon as we find him assigned the value 0 remains
						}
						else if(!grn.equals(rn) && !gun.equals(un) && !gd.equals(d) && exists!=0) {//if student doesn't exist
							exists=1;
						}
					}
					if(exists==1) {
						String str=un+","+rn+","+d+","+cc+","+"-";
						PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file3, true)));
						out.println(str);
						out.close();
					}
					reader3.close();
					writer.close(); 
				}
			}
			reader2.close();			
		}		
		reader1.close(); 
		if(visits!=3) {
			visits++;
			Courses.assigntostudents(visits);//we revisit the method 3 times in total to make sure that the update takes place completely
		}
	}
	
	
	static void assigncourses() throws IOException, FileNotFoundException{//assign an unassigned course to a professor
		File file1 = new File(System.getProperty("user.dir")+"/src/courses.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		File file2 = new File(System.getProperty("user.dir")+"/src/professorsfile.txt");
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		File file3 = new File(System.getProperty("user.dir")+"/src/coursestemp.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file3));
		Scanner input= new Scanner(System.in);
		System.out.println("Give course id");
		String cid=input.nextLine();
		String currentLine1, currentLine2;
		String cd="";
		int valid=0;
		while((currentLine1 = reader1.readLine()) != null) {//first we check if course is valid and unassigned
			String[] words1=currentLine1.split(",");
			if(words1[0].equals(cid) && words1[1].equals("none")) {
				valid=1;
				cd=words1[2];
			}
		}
		if(valid==0) {
			System.out.println("The course doesn't exist or it is already assigned, you will now go back to secretary menu.");
		}
		else if(valid==1) {
			System.out.println("Give professor registration number");
			String rn=input.nextLine();
			int exists=0;
			while((currentLine2 = reader2.readLine()) != null) {
				String[] words2=currentLine2.split(",");
				if(words2[3].equals(rn)){//if professor exists
					exists=1;
				}
			}
			reader1.close();
			reader2.close();
			if(exists==0)
				System.out.println("The professor's registration number is wrong, you will now go back to secretary menu.");
			else if(exists==1) {
				reader1 = new BufferedReader(new FileReader(file1));
				reader2 = new BufferedReader(new FileReader(file2));
				while((currentLine1 = reader1.readLine()) != null) {
					String[] words1=currentLine1.split(",");
					if(words1[1].equals("none") && words1[0].equals(cid) && words1[2].equals(cd)){						
						String str=cid+","+rn+","+cd;
						writer.write(str + "\n");
					}
					else
						writer.write(currentLine1 + "\n");
				}
			}
					
					
		}
		writer.close();
		//input.close();
		reader1.close();
		reader2.close();
		file1.delete();
		file3.renameTo(file1);
		
		
	}
	
	
	
}
