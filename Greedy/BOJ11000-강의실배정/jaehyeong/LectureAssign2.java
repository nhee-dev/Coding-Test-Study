package com.greedy.study;

import java.util.ArrayList;
import java.util.Scanner;


public class LectureAssign2 {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		int lectureNums = sc.nextInt(); // 강의 수
		
		ArrayList<Integer> lectureTimes = new ArrayList<Integer>(); // 강의 시간들 
		int roomNeeds = 1;
		
		for(int i = 0; i < lectureNums * 2; i ++) {
			lectureTimes.add(sc.nextInt()); 
		} // 강의 시간 입력
		
		
		for(int k = 0; k < lectureNums; k ++) {
			int sortMin = 2000000000;
			int sortIndex = 0;
			for(int i = 0; i < lectureNums * 2; i += 2) {
				if(lectureTimes.get(i) < sortMin) {
					sortMin = lectureTimes.get(i);
					sortIndex = i;
				} else if(lectureTimes.get(i) == sortMin) {
					if(lectureTimes.get(i) < lectureTimes.get(sortIndex + 1)) {
						sortMin = lectureTimes.get(i);
						sortIndex = i;
					}
				}
			} 
			
			lectureTimes.add(lectureTimes.get(sortIndex)); 
			lectureTimes.add(lectureTimes.get(sortIndex+1));
			lectureTimes.remove(sortIndex+1);
			lectureTimes.remove(sortIndex);
		}
		

		
		
		

		int lastEt = 0;
		int startIndex = 0;
		
		for(int i = 0; i < lectureNums; i ++) { //강의 수만큼 반복
			int fastSt = 2000000000;
			int fastEt = 2000000000;
			int fastIndex = 0;
			boolean isNextLecture = false;
			
			for(int start = startIndex; start < lectureTimes.size(); start += 2) {
				if(isNextLecture == true && lectureTimes.get(start) > fastSt) {
					break;
				}

				if(lectureTimes.get(start) >= lastEt && lectureTimes.get(start) < fastSt) {
					fastSt = lectureTimes.get(start);
					fastEt = lectureTimes.get(start+1);
					fastIndex = start;
					isNextLecture = true;
				} 
			} // 가장 이른 start 강의 찾기
			
			if(isNextLecture == false) {
				roomNeeds++;
				i--;
				lastEt = 0;
				startIndex = 0;
				continue;
				
			}
			
			lectureTimes.remove(fastIndex+1);
			lectureTimes.remove(fastIndex);
			
			lastEt = fastEt; // 

			
		} 
		
		System.out.println(roomNeeds);
		
		
	}

}
