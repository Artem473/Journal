package sample.model;

public class Person {
    private int id;
    private String fullName;
    private String date1;
    private String date2;
    private String date3;
    private String date4;
    private String course;

    public Person(int id,String fullName,String date1,String date2,String date3,String date4,String course ){
        this.id = id;
        this.fullName=fullName;
        this.date1=date1;
        this.date2=date2;
        this.date3=date3;
        this.date4=date4;
        this.course = course;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public String getDate1(){
        return date1;
    }
    public void setDate1(String date1){
        this.date1=date1;
    }
    public String getDate2(){
        return date2;
    }
    public void setDate2(String date2){
        this.date2=date2;
    }
    public String getDate3(){
        return date3;
    }
    public void setDate3(String date3){
        this.date3=date3;
    }
    public String getDate4(){
        return date4;
    }
    public void setDate4(String date4){
        this.date4=date4;
    }
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString(){
        return String.format("%s; %s; %s; %s; %s \n",getFullName(),getDate1(),getDate2(),getDate3(),getDate4());
    }
}
