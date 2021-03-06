package com.javaex.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javaex.ex01.Person;

public class PhoneManager {

	private List<Person> pList;
	private Scanner sc;

	public PhoneManager() throws IOException {

		sc = new Scanner(System.in);
		pList = new ArrayList<Person>();

		pList = getList();
	}

	// 시작준비 (시작화면 출려과 리스트 가져온다)
	public void showTitle() {
		System.out.println("*************************************************");
		System.out.println("*			전화번호 관리 프로그램			*");
		System.out.println("*************************************************");

	}

	// 메뉴 출력과 입력을 받는다.
	public int showMenu() {
		System.out.println(" ");
		System.out.println("1. 리스트   2. 등록   3.삭제   4. 검색   5. 종료");
		System.out.println("----------------------------------");
		System.out.print(">메뉴번호: ");
		int num = sc.nextInt();
		return num;
	}

	InputStream is = new FileInputStream("./PhoneDB.txt");
	InputStreamReader ir = new InputStreamReader(is, "UTF-8");
	BufferedReader br = new BufferedReader(ir);

	// 1.리스트선택시
	public void showList() throws IOException {
		System.out.println("<1.리스트>");

		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}

			String[] arr = str.split(",");

			Person p = new Person(arr[0], arr[1], arr[2]);

			pList.add(p);
		}
		for (int i = 0; i < pList.size(); i++) {
			pList.get(i).showInfo();
		}
	}

	// 2.등록선택시
	public void showAdd() throws IOException {
		OutputStream os = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		System.out.println("<2.등록>");
		sc.nextLine();
		System.out.print(">이름 : ");
		String text = sc.nextLine();
		System.out.print(">휴대전화 : ");
		String text01 = sc.nextLine();
		System.out.print(">회사전화 : ");
		String text02 = sc.nextLine();

		Person pp = new Person(text, text01, text02);
		pList.add(pp);
		for (int i = 0; i < pList.size(); i++) {
			bw.write(pList.get(i).writeDB());
			bw.newLine();
		}
		bw.close();

	}

	// 3.삭제선택시
	public void showRemove() throws IOException {
		OutputStream os1 = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter osw1 = new OutputStreamWriter(os1, "UTF-8");
		BufferedWriter bw1 = new BufferedWriter(osw1);
		System.out.println("<3.삭제>");
		System.out.print(">번호 : ");

		int num = sc.nextInt();
		pList.remove(num - 1);
		for (int i = 0; i < pList.size(); i++) {
			bw1.write(pList.get(i).writeDB());
			bw1.newLine();
		}
		bw1.close();
		System.out.println("[삭제되었습니다.]");
	}

	// 4.검색선택시
	public void showSearch()  {
		
		System.out.println("<4.검색>");
		System.out.print(">이름 : ");
		sc.nextLine();
		String src = sc.nextLine();
		
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getName().contains(src)) {
				pList.get(i).showInfo();

			}
		}

	}

	// 5.종료시
	public void showEnd() {
		System.out.println("<5.종료>");
		System.out.println("*************************************************");
		System.out.println("*				감사합니다				*");
		System.out.println("*************************************************");
	}

	// 메뉴번호를 잘못 입력시 안내문구를 출력하는 메소드
	public void showEtc() {
		System.out.println("[다시 입력해 주세요.]");
	}

	// 파일을 읽어 리스트에 담아 전달한다.
	private List<Person> getList() {
		return pList;

	}

	// 리스트를 파일에 저장한다.
	private void saveList() {

	}

	// 전체 리스트를 출력한다
	private void printList() {
		printList("");// 아래 메소드에 키워드값을 비워서 호출
	}

	// 키워드로 검색한 리스트를 출력한다
	private void printList(String keyword) {

	}

}
