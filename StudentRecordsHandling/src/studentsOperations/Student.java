package studentsOperations;

public class Student  {
	
	String name ;
	String location;
	String grade;
	String gender;
	String qualification;
	String dept; 
	
	public Student(String name, String location, String gender, String qualification, String dept, String grade) {
		
		this.name = name;
		this.location = location;
		this.gender = gender;
		this.grade = grade ;
		this.qualification = qualification;
		this.dept = dept;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.name = grade;
	}
	@Override
	public String toString() {
		return "Student[ Name : " + this.name + ", Location :"+ this.location +", Gender :"+ this.gender+", Qualification:"+this.qualification
				+", Department :"+this.dept+",Grade : " +this.grade+"]";	 
	}
	 
	
}
