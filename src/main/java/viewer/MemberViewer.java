package viewer;

import Controller.MemberController;
import Model.MemberDto;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberViewer {

    private Scanner SCANNER;
    private MemberDto login = null;
    private MemberController memberController;

    private ArrayList<MemberDto> criticList;
    private ArrayList<MemberDto> adminList;

    private FilmViewer filmViewer;

    private int LEVER_GENERAL = 1;
    private int LEVER_CRITIC = 2;
    private int LEVER_ADMIN = 3;

    public MemberViewer(Scanner scanner) {
        SCANNER = scanner;
        memberController = new MemberController();
        adminList = new ArrayList<>();
        criticList = new ArrayList<>();
    }

    public void setFilmViewer(FilmViewer filmViewer) {
        this.filmViewer = filmViewer;
    }

    public void showIndex() {
        System.out.println("영화 관리 프로그램에 접속하신걸 환영합니다 ! ");
        String message = "1. 로그인 2. 회원가입 3. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (login != null) {
                    filmViewer.setLogIn(login);
//                    reviewViewer.setLogIn(login);
//                    theaterViewer.setLogIn(login);
//                    runningTimeViewer.setLogIn(login);

                    showMenu();
                }

            } else if (userChoice == 2) {
                register();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private void auth() {
        //객체지향프로그랭은 자식이 필요한 결과만 알면된다.
        String message;
        message = "아이디를 입력해주세요";
        String username = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력해주세요";
        String password = ScannerUtil.nextLine(SCANNER, message);

        login = memberController.auth(username, password);

        if (login == null) {
            System.out.println("로그인정보가 정확하지 않습니다.");
        }
    }

    private void register() {
        String message;
        message = "사용하실 아이디를 입려해주세요";
        String username = ScannerUtil.nextLine(SCANNER, message);

        while (!memberController.validateUsername(username)) { //username 이미 존재
            System.out.println("해당 아이디는 사용이 불가능합니다.");
            message = "사용하실 아이디나 뒤로 가실려면 \"X\"를 입력해주세요";
            username = ScannerUtil.nextLine(SCANNER, message);

            if (username.equalsIgnoreCase("X")) {
                break;
            }
        }
        if (!username.equalsIgnoreCase("X")) {
            MemberDto u = new MemberDto();
            u.setUsername(username);

            message = "사용하실 비밀번호를 입력해주세요";
            u.setPassword(ScannerUtil.nextLine(SCANNER, message));

            message = "사용하실 닉네임을 입력해주세요";
            u.setNickname(ScannerUtil.nextLine(SCANNER, message));

            memberController.add(u);
        }
    }

    public void showMenu() {
        String message = "1. 영화정보 2.극장정보 3.상영정보 4.회원정보 5.로그아웃";
        while (login != null) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
//                movieViewer.showMenu();
                filmViewer.showMenu();
            } else if (userChoice == 2) {
//                movieViewer.selectMovie();
            } else if (userChoice == 3) {
//                theaterViewer.printTheaterList();
            } else if (userChoice == 4) {
                printUserOne();
            } else if (userChoice == 5) {
                login = null;
                System.out.println("정상적으로 로그아웃 되었습니다.");
                showIndex();
            }
        }
    }

    private void printUserOne() {
        System.out.println("회원번호 :  " + login.getId());
        System.out.println("회원 닉네임 : " + login.getNickname());

        if (login.getRole() == LEVER_GENERAL) {
            System.out.println("회원 등급 : 일반회원");
        } else if (login.getRole() == LEVER_CRITIC) {
            System.out.println("회원 등급 : 전문 평론가");
        } else {
            System.out.println("회원 등급 : 매니저");
        }
        System.out.println("=====================================");

        String message = "1. 닉네임수정 2. 탈퇴";
        System.out.print(message);
        if (login.getRole() == LEVER_ADMIN) {
            message = "3. 등업신청 현황";
        } else {
            message = "3. 등업신청 하기";
        }
        message += "4.뒤로가기";

        int userChoice = ScannerUtil.nextInt(SCANNER, message, 4, 1);
        if (userChoice == 1) {
            update();
        } else if (userChoice == 2) {
            delete();
        } else if (userChoice == 3) {
            if (login.getRole() == LEVER_ADMIN) {
                //등업신청 현황 보기
                approveRequest();
            } else {
                //등업신청하기 메소드 실행
                promote();
            }

        } else if (userChoice == 4) {

        }

    }

    private void update() {

        String message;
        message = "새로운 닉네임을 입력해주세요";
        String newNickname = ScannerUtil.nextLine(SCANNER, message);

        message = "기존 비밀번호 입력해주세요";
        String oldPassword = ScannerUtil.nextLine(SCANNER, message);

        if (oldPassword.equals(login.getPassword())) {
            login.setNickname(newNickname);
            memberController.update(login);
        }
        printUserOne();

    }

    private void delete() {
        if (login.getId() == 1) {
            System.out.println("기본관리자는 탈퇴하실 수 없습니다.");
        } else {
            String message = "정말로 삭제하시겠습니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);
            if (yesNo.equalsIgnoreCase("Y")) {

                criticList.remove(login);
                adminList.remove(login);

                memberController.delete(login.getId());
                login = null;
//                showIndex();
            }
        }

    }

    //등업요청
    private void promote() {
        String message = "1.관리자 2. 평론가 0.뒤로";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 2, 0);
        if (userChoice == login.getRole()) {
            System.out.println("현재 등급으로는 변경하실 수 없습니다.");
        } else if (userChoice == 2) {
            MemberDto memberDto = new MemberDto(login.getId());
            memberDto.setUsername(login.getUsername());
            criticList.add(memberDto);
        } else if (userChoice == 3) {
            MemberDto memberDto = new MemberDto(login.getId());
            memberDto.setUsername(login.getUsername());
            adminList.add(memberDto);
        }
    }

    //관리자 승인
    private void approveRequest() {
        String message = "2. 평론가 신청목록  3. 매니저신청목록 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 1);
        if (userChoice == 1) {
            printRequest(criticList, "평론가");
        } else if (userChoice == 2) {
            printRequest(adminList, "관리자");
        } else if (userChoice == 3) {

        }

    }

    private void printRequest(ArrayList<MemberDto> list, String level) {
        System.out.println(level + "등급의 신청목록");
        for (MemberDto m : list) {
            System.out.println("%d. %s");
        }

        String message = "등업을 승인할 회원의 번호나 뒤로가시려면 0을 눌러주세요";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);

        while (userChoice != 0 && !list.contains(new MemberDto(userChoice))) {
            System.out.println("유효하지 않은 번호입니다.");
            userChoice = ScannerUtil.nextInt(SCANNER, message);
        }

        if (userChoice != 0) {
            if (level.equalsIgnoreCase("평론가")) {
                memberController.rankUp(userChoice, LEVER_CRITIC);
                criticList.remove(new MemberDto(userChoice));
            } else {
                memberController.rankUp(userChoice, LEVER_ADMIN);
                adminList.remove(new MemberDto(userChoice));
            }
            printRequest(list, level);
        } else {
            approveRequest();
        }

    }


}
