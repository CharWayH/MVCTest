package entity;

public class Login {
    private int id;
    private String uname;
    private String pwd;

    public Login(String uname, String pwd) {
        this.uname = uname;
        this.pwd = pwd;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
