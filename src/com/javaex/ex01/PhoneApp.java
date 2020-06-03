
package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

	public static void main(String[] args) throws IOException {

		Person person = new Person();
		InputStream is = new FileInputStream("./PhoneDB.txt");
		InputStreamReader ir = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(ir);

		List<Person> list = new ArrayList<Person>();

		Scanner sc = new Scanner(System.in);

		System.out.println("*************************************************");
		System.out.println("*			전화번호 관리 프로그램			*");
		System.out.println("*************************************************");

		while (true) {
			String str = br.readLine();
			if (str == null) {
				break;
			}

			String[] arr = str.split(",");

			Person p = new Person(arr[0], arr[1], arr[2]);
			
			
			list.add(p);
		}

		while (true) {
			System.out.println(" ");
			System.out.println("1. 리스트   2. 등록   3.삭제   4. 검색   5. 종료");
			System.out.println("----------------------------------");

			System.out.print(">메뉴번호: ");

			int num = sc.nextInt();
			if (num == 5) {
				System.out.println("<5.종료>");
				System.out.println("*************************************************");
				System.out.println("*				감사합니다				*");
				System.out.println("*************************************************");
				break;
			}

			switch (num) {
			case 1:
				System.out.println("<1.리스트>");

				for (int i = 0; i < list.size(); i++) {
					list.get(i).showInfo();
				}

				break;
			case 2:
				OutputStream os = new FileOutputStream("./PhoneDB.txt");
				OutputStreamWriter ow = new OutputStreamWriter(os, "UTF-8");
				BufferedWriter bw = new BufferedWriter(ow);

				System.out.println("<2.등록>");
				sc.nextLine();
				System.out.print(">이름 : ");
				String text = sc.nextLine();
				System.out.print(">휴대전화 : ");
				String text01 = sc.nextLine();
				System.out.print(">회사전화 : ");
				String text02 = sc.nextLine();

				Person p5 = new Person(text, text01, text02);
				list.add(p5);

				for (int j = 0; j < list.size(); j++) {
					bw.write(list.get(j).writeDB());
					bw.newLine();
				}
				bw.close();
				break;

			case 3:
				OutputStream os1 = new FileOutputStream("./PhoneDB.txt");
				OutputStreamWriter ow1 = new OutputStreamWriter(os1, "UTF-8");
				BufferedWriter bw1 = new BufferedWriter(ow1);

				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
					int inum = sc.nextInt();
					list.remove(inum-1);
					for (int i = 0; i < list.size(); i++) {
						bw1.write(list.get(i).writeDB());
						bw1.newLine();
					}
					bw1.close();
				System.out.println("[삭제되었습니다.]");
			
				
				break;

			case 4:
				System.out.println("<4.검색>");
				System.out.print(">이름 : ");
				String search = sc.nextLine();
				for (int i = 0; i<list.size(); i++) {
					if (list.get(i).getName().contains(search)) {
						list.get(i).showInfo();
					}
				}
				
				
				break;

			default:
				System.out.println("[다시 입력해 주세요.]");
				break;

			}

		}
	}

}