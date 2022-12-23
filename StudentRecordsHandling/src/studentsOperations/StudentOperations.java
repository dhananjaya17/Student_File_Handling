package studentsOperations;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class StudentOperations {

	String[]  data =null;
	Student Student = null;
	public static final String studentsFilePath="D:/Dhananjaya/Studentdetails1.txt";
	List<String> female = Arrays.asList("female","FEMALE","Female","f","F");
	List<String> male = Arrays.asList("Male","male","MALE","m","M");
	List<Student> stdList = null;

	public List<Student> getAllStudentDetails(String gender) throws IOException {
		List<Student> stdList =  new ArrayList<Student>();
		try {
			Scanner sc = new Scanner(Paths.get(StudentOperations.studentsFilePath));
			while (sc.hasNext()) {
				String StudentDetails = sc.nextLine();
				data = StudentDetails.split(",");
				if (gender != null && data[2].toLowerCase().equals(gender.toLowerCase())) {
					Student Student = new Student(data[0], data[1], data[2], data[3], data[4],data[5]);
					stdList.add(Student);
				} else if (gender == null) {
					Student Student = new Student(data[0], data[1], data[2], data[3], data[4],data[5]);
					stdList.add(Student);
				}   
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return stdList;
	}

	public List<Student> getStudentsByGender(String gender) throws IOException{
		stdList = null;
		if(stdList == null ) {
			stdList = getAllStudentDetails(gender);
		}
		return stdList;
	}

	public void insertStudentDetails(String Student) throws IOException {
		File file = new File(StudentOperations.studentsFilePath);
		if(!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file,true);
		fileWriter.write(Student);
		fileWriter.close();
	}


	public  List<Student> getStudentsByAlphabeticalOrder(String filterOption,String gender) throws IOException{
		stdList = null;
		if(stdList == null) {
			stdList = getAllStudentDetails(gender);
			Collections.sort(stdList,new Comparator<Student>() {
				String temp = null;
				String temp2 = null;
				int result ;
				public int compare(Student a,Student b) {
					if(filterOption.toLowerCase().equals("name")){
						temp= a.getName();
						temp2=b.getName();		    				
					}else if(filterOption.toLowerCase().equals("location")) {
						temp=a.getLocation();
						temp2=b.getLocation();			    				
					}else if(filterOption.toLowerCase().equals("gender")) {
						temp= a.getGender();
						temp2=b.getGender();			    				
					}else if(filterOption.toLowerCase().equals("qualification")) {
						temp= a.getQualification();
						temp2=b.getQualification();			    				
					}else if(filterOption.toLowerCase().equals("department")) {
						temp= a.getDept();
						temp2=b.getDept();			    				
					}else if(filterOption.toLowerCase().equals("grade")) {
						temp= a.getGrade();
						temp2=b.getGrade();	
					}

					result = temp.toLowerCase().compareTo(temp2.toLowerCase());

					return result;
				}
			});	
		}
		return stdList;
	}
	public List<Student> getStudentsWhoPassed(String gender) throws IOException{
		String tempGrade = null;
		stdList = getAllStudentDetails(gender);
		for(int i =0; i<stdList.size();i++) {
			Student = stdList.get(i);
			tempGrade = Student.getGrade();
			if(tempGrade.equals("F")) {
				stdList.remove(Student);
			}
		}
		return stdList;
	}

	public  float  getTotalPassPercentageOfStudents(String gender) throws IOException{
		float percentage = 0 ;
		List<Student> passStdList =null;
		float totalStudents = 0;
		float passStudents = 0;
		if(stdList == null && passStdList == null) {
			stdList = getAllStudentDetails(gender);
			totalStudents =stdList.size();
			System.out.println(" TOTAL NUMBER OF STUDENTS : "+totalStudents);
			passStdList = getStudentsWhoPassed(gender);
			passStudents = passStdList.size();
			System.out.println(" TOTAL NUMBER OF STUDENTS PASSED : "+passStudents);
			percentage  = (passStudents/totalStudents)*100;
		}

		return percentage;	
	}
	

	public static void main(String[] args) throws IOException {
		StudentOperations studentData = new StudentOperations();
		String genderChoice = null;
		List<Student> stdList = null;
		try (Scanner intScanner = new Scanner(System.in)) {
			try (Scanner stringScanner = new Scanner(System.in)) {
				int userChoice = -1;
				do {
					System.out.println("************************************************************************************************************************");
					System.out.println("                                                                                                                          ");
					
					System.out.println("                                #####    SELECT BELOW    #####                       ");
					System.out.println("-------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Select which operation to be perfomed : ");
					System.out.println("                                                                                                                          ");
					System.out.println(" 1:       Insert Student or Students");
					System.out.println(" 2:       Display Students          ");
					System.out.println(" 3:       Student By Gender         ");
					System.out.println(" 4:       Passed Students           ");
					System.out.println(" 5:       PassPercentage of Students");
					System.out.println(" 6:       Sort By UserChoice        ");
					System.out.println(" 0:       Exit                      ");
					System.out.println("--------------------------------------------------------------------------------------------------------------------------");
					
					System.out.println("ENTER VALUE + ENTER KEY : ");
					userChoice  = intScanner.nextInt();
					System.out.println("----------------------------------------------------------------------------------------------------------------------------");

					switch(userChoice){
					case 1:
						System.out.println("                                #####    INSERT STUDENT    #####                       ");
						System.out.println("                                                                                   ");
						System.out.println("                                                                                   ");
						System.out.println("HOW MANY STUDENT U WANTED TO INSERT?");
						System.out.println("ENTER VALUE + ENTER KEY : ");
						int numberOfStudents = intScanner.nextInt();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println("                      ########     INSERT STUDENT DETAILS ONE BY ONE    ######                                                       ");
						for(int i=0; i<numberOfStudents ;i++) {
							System.out.println("                                                                                   ");
							System.out.println("                                                                                   ");
							System.out.println("   ENTER NAME            :        ");
							String name = stringScanner.nextLine();
							System.out.println("   ENTER LOCATION        :        ");
							String location = stringScanner.nextLine();
							System.out.println("   ENTER GENDER          :        ");
							String gender =stringScanner.nextLine();
							System.out.println("   ENTER QUALIFICATION   :        ");
							String qualification = stringScanner.nextLine();
							System.out.println("   ENTER DEPARTMENT NAME :        ");
							String dept = stringScanner.nextLine();
							System.out.println("   ENTER GRADE           :        ");
							String grade =stringScanner.nextLine();
							String newStudentDetails = "\r\n"+ name+","+location+","+gender+","+qualification+","+dept+","+grade;
							studentData.insertStudentDetails(newStudentDetails);
						}
						System.out.println("                                                                                   ");
						System.out.println("                                                                                   ");
						System.out.println("                               !!  UPDATED STUDENT !!                               ");
						System.out.println("                                                                                   ");
						System.out.println("                                                                                   ");
						break; 

					case 2:
						System.out.println("                                                                                   ");
						System.out.println("                                #####    STUDENTS    #####                       ");
						System.out.println("                                                                                   ");
						System.out.println("                                                                                   ");
						stdList = studentData.getAllStudentDetails(genderChoice);
						for(int i = 0 ;i<stdList.size();i++) {
							System.out.println(stdList.get(i).toString());
						}
						System.out.println("                                                                                   ");
						break;

					case 3:
						System.out.println("                                #####    STUDENTS BY GENDER   #####                  ");
						System.out.println("                                                                                     ");
						System.out.println("      SELECT GENDER  :   ");
						System.out.println("                                                                                   ");
						System.out.println("  1:   MALE      ");
						System.out.println("  2:   FEMALE    ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println("ENTER VALUE + ENTER KEY : ");
						userChoice  = intScanner.nextInt();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						switch(userChoice) {
						case 1:
							System.out.println("   MALE STUDENTS   :: ");
							System.out.println("                                                                                   ");
							System.out.println("                                                                                   ");
							genderChoice = "male";
							break;
						case 2:
							System.out.println("   FEMALE STUDENTS :: ");
							System.out.println("                                                                                   ");
							System.out.println("                                                                                   ");
							genderChoice = "female";
							break;
						}
						stdList = studentData.getStudentsByGender(genderChoice);
						for(int i = 0 ;i<stdList.size();i++) {
							System.out.println(stdList.get(i).toString());
						}
						System.out.println("                                                                                   ");
						break;

					case 4:
						System.out.println("                                                                                   ");
						System.out.println("                     #####   PASSED STUDENTS  ####                                 ");
						System.out.println("                                                                                   ");
						System.out.println("   SELECT GENDER : ");
						System.out.println("                                                                                   ");
						System.out.println(" 1:    All Students");
						System.out.println(" 2:    Male        ");
						System.out.println(" 3:    Female      ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println("ENTER VALUE + ENTER KEY : ");
						userChoice  = intScanner.nextInt();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						switch(userChoice) {
						case 1:
							System.out.println(" PASSED STUDENTS        :: ");
							System.out.println("                                                                                   ");
							genderChoice = null;
							break;
						case 2:
							System.out.println(" PASSED MALE STUDENTS   :: ");
							System.out.println("                                                                                   ");
							genderChoice = "male";
							break;
						case 3:
							System.out.println(" PASSED FEMALE STUDENTS :: ");
							System.out.println("                                                                                   ");
							genderChoice = "female";
							break;
						}
						stdList = studentData.getStudentsWhoPassed(genderChoice);
						for(int i = 0 ;i<stdList.size();i++) {
							System.out.println(stdList.get(i).toString());
						}
						System.out.println("                                                                                   ");
						System.out.println("                                                                                   ");
						break;

					case 5:
						System.out.println("                                                                                   ");
						System.out.println("                     #####   PASS PERCENTAGE OF STUDENTS  ####                                 ");
						System.out.println("                                                                                   ");
						float  passPercentage = 0 ;
						System.out.println(" SELECT GENDER : ");
						System.out.println("                                                                                   ");
						System.out.println(" 1: ALL STUDENTS");
						System.out.println(" 2: MALE        ");
						System.out.println(" 3: FEMALE      ");
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println("ENTER VALUE + ENTER KEY : ");
						userChoice  = intScanner.nextInt();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						switch(userChoice) {
						case 1:
							System.out.println("                                                                                   ");
							System.out.println(" TOTAL PASS PERCENTAGE :: ");
							System.out.println("                                                                                   ");
							genderChoice = null;
							break;
						case 2:
							System.out.println("                                                                                   ");
							System.out.println("MALE STUDENTS PASS% :: ");
							System.out.println("                                                                                   ");
							genderChoice = "male";
							break;
						case 3:
							System.out.println("                                                                                   ");
							System.out.println("FEMALE STUDENTS PASS% :: ");
							System.out.println("                                                                                   ");
							genderChoice = "female";
							break;
						}
						passPercentage = studentData.getTotalPassPercentageOfStudents(genderChoice);
						System.out.println("PASS PERCENTAGE OF STUDENTS ::"+ passPercentage +"%");
						break;

					case 6:
						System.out.println("                                                                                   ");
						System.out.println("                     #####    SORTING  STUDENTS  ####                               ");
						System.out.println("                                                                                   ");
						String userFilterChoice = null;
						System.out.println("   SELCT GENDER: ");
						System.out.println("                                                                                   ");
						System.out.println(" 1: All Students");
						System.out.println(" 2: Male        ");
						System.out.println(" 3: Female      ");
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println("ENTER VALUE + ENTER KEY : ");
						userChoice  = intScanner.nextInt();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						System.out.println(" SELECT SORTING ORDER: ");
						System.out.println("                                                                                   ");
						System.out.println("  1: Name          ");
						System.out.println("  2: Location      ");
						System.out.println("  3: Gender        ");
						System.out.println("  4: Qualification ");
						System.out.println("  5: Department    ");
						System.out.println("  6: Grade         ");
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");

						System.out.println("ENTER VALUE + ENTER KEY : ");
						userFilterChoice  = stringScanner.nextLine();
						System.out.println("                                                                                   ");
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");

						switch(userChoice) {
						case 1:
							System.out.println("                                                                                   ");
							System.out.println(" ALL STUDENTS :: ");
							System.out.println("                                                                                   ");
							genderChoice = null;
							break;
						case 2:
							System.out.println("                                                                                   ");
							System.out.println(" MALE STUDENTS :: ");
							System.out.println("                                                                                   ");
							genderChoice = "male";
							break;
						case 3:
							System.out.println("                                                                                   ");
							System.out.println(" FEMALE STUDENTS :: ");
							System.out.println("                                                                                   ");
							genderChoice = "female";
							break;
						}
						System.out.println("---------------------------------------------------------------------------------------------------------------------------");
						switch(userChoice) {
						case 1:
							System.out.println("                                                                                   ");
							userFilterChoice ="name";
							System.out.println("                                                                                   ");
						break;
						case 2:
							System.out.println("                                                                                   ");
							userFilterChoice ="location";
							System.out.println("                                                                                   ");
						break;
						case 3:
							System.out.println("                                                                                   ");
							userFilterChoice ="gender";
							System.out.println("                                                                                   ");
						break;
						case 4: 
							System.out.println("                                                                                   ");
							userFilterChoice ="qualification";
							System.out.println("                                                                                   ");
						break;
						case 5:
							System.out.println("                                                                                   ");
							userFilterChoice ="dept";
							System.out.println("                                                                                   ");
						break;
						case 6:
							System.out.println("                                                                                   ");
							userFilterChoice ="grade";
							System.out.println("                                                                                   ");
						break;
						}
						
						stdList = studentData.getStudentsByAlphabeticalOrder(userFilterChoice,genderChoice );
						for(int i = 0 ;i<stdList.size();i++) {
							System.out.println(stdList.get(i).toString());
						}   
						System.out.println("                                                                                   ");
						break;

					}
				}while(userChoice!=0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

