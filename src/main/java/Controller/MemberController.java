package Controller;

import Model.MemberDto;

import java.util.ArrayList;

public class MemberController {
    private ArrayList<MemberDto> list;
    private int nextId;
    private int LEVER_GENERAL = 1;
    private int LEVER_CRITIC = 2;
    private int LEVER_ADMIN = 3;

    public MemberController() {
        list = new ArrayList<>();
        nextId = 1;

        MemberDto memberDto = new MemberDto();
        memberDto.setId(nextId++);
        memberDto.setPassword("9");
        memberDto.setRole(LEVER_ADMIN);
        memberDto.setNickname("관리자닉");
        memberDto.setUsername("9");
        list.add(memberDto);

        addSampleData();

    }

    private void addSampleData(){
        //평론가
        for (int i=0; i<=3; i++){
            MemberDto m = new MemberDto();
            m.setId(nextId++);
            m.setUsername("C" + i);
            m.setPassword("1");
            m.setNickname("평론가" + i);
            m.setRole(LEVER_CRITIC);
            list.add(m);
        }

        //일반 사용자
        for (int i =0; i<=3; i++){
            MemberDto m = new MemberDto();
            m.setId(nextId++);
            m.setUsername("G" + i);
            m.setPassword("1");
            m.setNickname("일반사용자" + i);
            m.setRole(LEVER_CRITIC);
            list.add(m);
        }


    }




    public void add(MemberDto memberDto) {
        memberDto.setId(nextId++);
        memberDto.setRole(LEVER_GENERAL);
        list.add(memberDto);
    }

    public boolean validateUsername(String username) {
        if (username.equalsIgnoreCase("X")) {
            return false;
        }

        for (MemberDto u : list) {
            if (username.equalsIgnoreCase(u.getUsername())) {
                return false;
            }
        }

        return true;
    }

    public MemberDto auth(String username, String password) {
        for (MemberDto u : list) {
            if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {

                return new MemberDto(u);
            }
        }

        return null;
    }

    public void update(MemberDto memberDto) {
        list.set(list.indexOf(memberDto), memberDto);
    }

    public void delete(int id) {
        list.remove(new MemberDto(id));
//        managerList.remove(new MemberDto(id));
//        reviewerList.remove(new MemberDto(id));
    }

    public void rankUp(int id, int level) {
        MemberDto m = new MemberDto(id);
        list.get(list.indexOf(m)).setRole(level);
    }

}
