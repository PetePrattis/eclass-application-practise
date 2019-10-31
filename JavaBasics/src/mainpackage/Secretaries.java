package mainpackage;

import java.util.Scanner;
import java.io.*;

import mainpackage.Users;

public class Secretaries extends Users{
	private final String regNumber;
	private String strre;

	public Secretaries(String un, String n, String sn, String dp, String rn2) {
		super(un, n, sn, dp);
		this.regNumber = rn2;
	}
	public String getregistrationnumber() {
		return regNumber;
	}
	
	
	public static void signin() throws IOException, FileNotFoundException{//signin method checks if the account information is correct
		Scanner input1= new Scanner(System.in);		
		File file = new File(System.getProperty("user.dir")+"/src/secretariesfile.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String currentLine;
		int found=0;
		System.out.println("Give username and registration number to login as secretary");
		System.out.println("Give username:");
			
		String un = input1.nextLine();

		System.out.println("Give registration number:");

		String am = input1.nextLine();		
		while((currentLine = reader.readLine()) !=null) {
			String[] words=currentLine.split(",");
				
			if(words[0].equals(un) && words[3].equals(am) && found==0){//if signin is successful you proceed with choosing an action
				System.out.println("User log in successful. Secretary's information:");
				System.out.println(currentLine);
				found=1;
				Secretaries.choice();
			}
			if(found==1)
				break;
			}

			if(found==0) {
				System.out.println("Wrong username or password, or secretary doesn't exist.");
				Secretaries.signin();
			}
			//input1.close();	
			reader.close();
	}
	
	
	static void choice() throws IOException, FileNotFoundException{//here you chose an action
		Scanner input1= new Scanner(System.in);	
		System.out.println("Do you want to create a new student, create a new professor, create a new course, assign course to professor or update grades file or log off? please answer 'a' or 'b' or 'c' or 'd' or 'e' or 'f'");   
		String answer2 = input1.nextLine();
		if(answer2.equals("a")) {
			Secretaries.newstudent();
			Secretaries.choice();
		}
		else if (answer2.equals("b")) {
			Secretaries.newprofessor();
			Secretaries.choice();
		}
		else if (answer2.equals("c")) {
			Courses.newcourse();
			Secretaries.choice();
		}
		else if (answer2.equals("d")) {
			Courses.assigncourses();
			Secretaries.choice();
		}
		else if (answer2.equals("e")) {
			int visits=0;
			Courses.assigntostudents(visits);
			Secretaries.choice();
		}
		else if(answer2.equals("f"))
			//input1.close();
			System.out.println("Logging off...");
		else {
			System.out.println("Wrong input");
			Secretaries.choice();
		}
			
	}

	static boolean number(String input) throws IOException{//this method will check if the input for registration number is number
		try{
			Integer.parseInt(input);
	    }catch(NumberFormatException ex){return false;}
	    return true;
	}

	static void newstudent() throws IOException, FileNotFoundException{//creating a new student
		File file1 = new File(System.getProperty("user.dir")+"/src/studentsfile.txt");
		File file2 = new File(System.getProperty("user.dir")+"/src/professorsfile.txt");
		File file3 = new File(System.getProperty("user.dir")+"/src/secretariesfile.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/src/studentsfile.txt", true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		BufferedReader reader3 = new BufferedReader(new FileReader(file3));
		System.out.println("You are about to create a new student, make sure his registration number is unique among all other registration numbers (students, professors and secretaries)");
		Scanner input= new Scanner(System.in);
		String currentLine1,currentLine2,currentLine3,un,n,sn,dp,rn;
		String str="";
		int duplicate =0;//this will help us check if the registration number is not unique
		//first we ask for the secretary to give the new info, if they are not valid he will go back to the choice method
		System.out.println("Give username: ");
		un = input.nextLine();
		System.out.println("Give name: ");
		n = input.nextLine();
		System.out.println("Give surname: ");
		sn = input.nextLine();
		System.out.println("Give department: ");
		dp = input.nextLine();
		while (true) {
	        if(dp.equals("informatics") || dp.equals("economics") || dp.equals("shipping")){
	            break;
	        }
			System.out.println("Our university has the 3 departments informatics, economics and shipping, insert again one of these: ");
	        dp = input.nextLine();
	    }
		System.out.println("Give registration number: ");
		rn = input.nextLine();
		while(rn.length()!=5 || !Secretaries.number(rn)) {
			System.out.println("Registration number must be a 5 digit number, insert again: ");
			rn = input.nextLine();
		}		
		str=un+","+n+","+sn+","+dp+","+rn;
		while((currentLine3 = reader3.readLine()) != null) {
			String[] words3=currentLine3.split(",");
			if(words3[3].equals(rn))
					duplicate=1;									
		}
		while((currentLine2 = reader2.readLine()) != null) {
			String[] words2=currentLine2.split(",");
			if(words2[3].equals(rn))
					duplicate=1;						
		}
		while((currentLine1 = reader1.readLine()) != null) {
			String[] words1=currentLine1.split(",");
			if(words1[4].equals(rn))
					duplicate=1;						
		}
		if(duplicate==1) {
			System.out.println("The registration number is not unique, you will now go back to secretary menu.");
		}
		else if(duplicate==0) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));
			out.println(str);
			out.close();
			//input.close();
			writer.close(); 
			reader1.close(); 
			reader2.close();
			reader3.close(); 
			System.out.println("Student created.");
			Users.UsersCounter();
		}
		

	}
	
	static void newprofessor() throws IOException, FileNotFoundException{//creating a new professor
		File file2 = new File(System.getProperty("user.dir")+"/src/studentsfile.txt");
		File file1 = new File(System.getProperty("user.dir")+"/src/professorsfile.txt");
		File file3 = new File(System.getProperty("user.dir")+"/src/secretariesfile.txt");
		BufferedReader reader1 = new BufferedReader(new FileReader(file1));
		//BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
		OutputStreamWriter writer1 = new OutputStreamWriter(
                new FileOutputStream(System.getProperty("user.dir")+"/src/professorsfile.txt", true), "UTF-8");
		BufferedWriter writer = new BufferedWriter(writer1);
		BufferedReader reader2 = new BufferedReader(new FileReader(file2));
		BufferedReader reader3 = new BufferedReader(new FileReader(file3));
		System.out.println("You are about to create a new professor, make sure his registration number is unique among all other registration numbers (students, professors and secretaries)");
		Scanner input= new Scanner(System.in);
		String currentLine1,currentLine2,currentLine3,un,n,sn,rn;
		String str="";
		int duplicate =0;//this will help us check if the registration number is not unique
		//first we ask for the secretary to give the new info, if they are not valid he will go back to the choice method
		System.out.println("Give username: ");
		un = input.nextLine();
		System.out.println("Give name: ");
		n = input.nextLine();
		System.out.println("Give surname: ");
		sn = input.nextLine();
		System.out.println("Give registration number: ");
		rn = input.nextLine();
		while(rn.length()!=5 || !Secretaries.number(rn)) {
			System.out.println("Registration number must be a 5 digit number, insert again: ");
			rn = input.nextLine();
		}
		str=un+","+n+","+sn+","+rn;
		while((currentLine3 = reader3.readLine()) != null) {
			String[] words3=currentLine3.split(",");
			if(words3[3].equals(rn))
					duplicate=1;									
		}
		while((currentLine2 = reader2.readLine()) != null) {
			String[] words2=currentLine2.split(",");
			if(words2[4].equals(rn))
					duplicate=1;						
		}
		while((currentLine1 = reader1.readLine()) != null) {
			String[] words1=currentLine1.split(",");
			if(words1[3].equals(rn))
					duplicate=1;						
		}
		if(duplicate==1)
			System.out.println("The registration number is not unique, you will now go back to secretary menu.");
		else if(duplicate==0) {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file1, true)));
			out.println(str);
			out.close();
			//input.close();
			writer.close(); 
			reader1.close(); 
			reader2.close();
			reader3.close(); 
			System.out.println("Professor created.");
			Users.UsersCounter();

		}
		

	}
	
}
