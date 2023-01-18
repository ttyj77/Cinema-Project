package viewer;

import Controller.MemberController;
import Model.MemberDto;
import util.ScannerUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmViewer {

    private final Scanner SCANNER;
    private int LEVER_GENERAL = 1;
    private int LEVER_CRITIC = 2;
    private int LEVER_ADMIN = 3;

    private MemberDto logIn;
    private MemberController memberController;

    public FilmViewer(Scanner scanner) {
        memberController = new MemberController();
        this.SCANNER = scanner;

    }

    public void setLogIn(MemberDto logIn) {
        this.logIn = logIn;
    }


    public void showMenu() {
        if (logIn.getRole() == LEVER_ADMIN) {
            showAdminMenu();
        } else {
            showGeneralMenu();
        }
    }


    private void showAdminMenu() {
        String message = "1. 영화 목록 보기 2. 영화 등록하기 3. 뒤로가기";

        int userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 1);
        while (true) {
            if (userChoice == 1) {
//                printFilmList();
            } else if (userChoice == 2) {

            } else if (userChoice == 3) {
                System.out.println("메인화면으로 돌아갑니다.");
                break;
            }
        }
    }

    private void showGeneralMenu() {
        String message = "1. 영화 목록 보기 2. 뒤로가기";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message, 3, 1);
            if (userChoice == 1) {
//                printFilmList();
            } else if (userChoice == 3) {
                System.out.println("메인화면으로 돌아갑니다.");
                break;
            }
        }
    }
}
