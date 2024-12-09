package model;
public class Personal {
	 private int id;
	    private String name;  private String address;	
	    private String gender;	    private String knowledge;   private String subject;
	    public Personal(int id, String name, String address, String gender, String knowledge, String subject) {
	        this.id = id;
	        this.name = name;
	        this.address = address;
	        this.gender = gender;
	        this.knowledge = knowledge;
	        this.subject = subject;
	    }
	    public int getId() {      return id;	    }
	    public void setId(int id) {   this.id = id;    }
	    public String getName() {    return name;	    }
	    public void setName(String name) {   this.name = name;	    }
	    public String getAddress() {	        return address;	    }
	    public void setAddress(String address) {   this.address = address;    }
	    public String getGender() {	        return gender;	    }
	    public void setGender(String gender) {	        this.gender = gender;	    }
	    public String getKnowledge() {	        return knowledge;	    }
	    public void setKnowledge(String knowledge) {     this.knowledge = knowledge;	    }
	    public String getSubject() {	        return subject;	    }
	    public void setSubject(String subject) {	        this.subject = subject;	    }
}
