public class Owners {

    public Owners(int id, String fname, String lname, int age, String sex) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.sex = sex;
    }

    private int id;
    private String fname;
    private String lname;
    private int age;
    private String sex;

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Owner [id=" + id + ", fname=" + fname + ", lname=" + lname +
                ",  age=" + age + ", sex = " + sex + "]";

    }
}
