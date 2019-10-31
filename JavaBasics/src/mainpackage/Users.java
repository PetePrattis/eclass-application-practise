package mainpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Users {

	private String username,name,surname,department;
	static int usercounter=0;
	
	public Users(String un,String n,String sn,String dp) {
		usercounter++;
		this.username = un;
		this.name = n;
		this.surname = sn;
		this.department = dp;
	}
	
	public static void UsersCounter() throws IOException, FileNotFoundException{//every time a new student or professor is created the counter adds one to total users
		File file = new File(System.getProperty("user.dir")+"/src/usercounter.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		File file1 = new File(System.getProperty("user.dir")+"/src/usercountertemp.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file1));
		String currentLine ="";
		while((currentLine = reader.readLine()) != null) {
			currentLine = Integer.toString((Integer.parseInt(currentLine))+1);
			writer.write(currentLine + "\n");
			System.out.println("Current number of total users is: "+ currentLine);
		}
		writer.close();
		reader.close();
		file.delete();
		file1.renameTo(file);
		
	}
	public int getcounter() {
		return usercounter;
	}
	
	public String  getusername() {
		return username;
	}
	
	public String getname() {
		return name;
	}
	
	public String getsurname() {
		return surname;
	}
	
	public String getdepartment() {
		return department;
	}
}
