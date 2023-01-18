package Model;

public class MemberDto {
    private int id; //회원번호 1, 2 ---
    private String username; //아이디 user1
    private String nickname;
    private String password;
    private int role;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (o instanceof MemberDto) {
            MemberDto b = (MemberDto) o;
            return id == b.id;
        }
        return false;
    }

    public MemberDto(int id) {
        this.id = id;
    }

    public MemberDto() {
        //새로운 객체를 생성할 수도 있으니까 기본생성자 만든것!
    }

    public MemberDto(MemberDto origin) {
        id = origin.id;
        username = origin.username;
        nickname = origin.nickname;
        password = origin.password;
        role = origin.role;
    }





}
