package Models;

import Interfaces.MemberInterface;

public class Member implements MemberInterface {
    private int id;
    private String name;
    private String email;

    public int getId() {
        int tempId = id;
        return tempId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Member(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.email = data[2];
    }

    public Member(Member member) {
        this.id = member.id;
        this.name = member.name;
        this.email = member.email;
    }

    @Override
    public String toString() {
        return "Member{" + "id=" + id + ",name='" + name + '\'' + ",email='" + email + '\'' + '}';
    }
}
