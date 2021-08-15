package com.ssafy.niceage.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ssafy.niceage.Controller.Request.MatchingRequest;
import com.ssafy.niceage.Domain.User.User;
import com.ssafy.niceage.Repository.UserRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
@Scope("prototype")
public class MatchingService {

	private final UserRepository userRepository;
	
	private List<MatchInfo> matchInfoList = new ArrayList<>();
	private List<Boolean> statusList = new ArrayList<>();
	private long[] matchUserNo = new long[10000];
	private boolean[] timeIndex = new boolean[10000];
	
	@Getter
	public class MatchInfo {
		String userId;
		long userNo;
		String userAddress;
		String userGender;
		int firstChoice;
		int secondChoice;
		int thirdChoice;
		
		public MatchInfo(String userId, long userNo, String userAddress, String userGender, int firstChoice,
				int secondChoice, int thirdChoice) {
			this.userId = userId;
			this.userNo = userNo;
			
			// this.userAddres : 서울시 종로구 - 이런식으로 구까지만 저장해서 같은지역인지 비교
			String[] arr = userAddress.split(" ");
			String tempAddress = new String();
			for (int i = 0; i < 2; i++) {
				if (arr[i].equals("서울") || arr[i].equals("서울시")) {
					arr[i] = "서울특별시";
				}
				tempAddress += arr[i] + " ";
			}
			
			this.userAddress = tempAddress;
			this.userGender = userGender;
			this.firstChoice = firstChoice;
			this.secondChoice = secondChoice;
			this.thirdChoice = thirdChoice;
		}
		
	}


	public void addList(MatchingRequest request) {
		User user = userRepository.findByUserId(request.getUserId());
		long userNo = user.getUserNo();
		String userAddress = user.getUserAddress();
		String userGender = user.getUserGender();
		
		MatchInfo object = new MatchInfo(request.getUserId(), userNo, userAddress, userGender, request.getFirstChoice(), request.getSecondChoice(),
				request.getThirdChoice());
		
		matchInfoList.add(object);
		statusList.add(false);

	}

	public Long findList (MatchingRequest request) {
		
		User user = userRepository.findByUserId(request.getUserId());
		long userNo = user.getUserNo();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			User user1 = new User();
			User user2 = new User();
			int i = 1;
			@Override
			public void run() {
				System.out.println("실행중임"+"["+userNo+"]");
				
				// 친구매칭을 클릭한 사람 수 만큼 실시간으로 등록된 매칭정보 객체리스트를 탐색
				// 같은 분류의 매칭을 한 사람을 찾을 시 정보를 저장하고 리스트를 삭제한다.
				for (int i = 0; i < matchInfoList.size(); i++) {
					
					// 탐색중에 본인정보면 continue
					if (request.getUserId().equals(matchInfoList.get(i).userId)) {
						continue;
					}
					
					// 친구매칭 조건에 맞는 사람이 있는 경우
					if (checkMatchingCondition(request, i)) {
						System.out.println("list크기: " + matchInfoList.size() + " i: " + i + " id :" +request.getUserId());
						// 1:1매칭에 성공한 두명의 정보를 각각 저장
						user1 = userRepository.findByUserId(request.getUserId());
						user2 = userRepository.findByUserId(matchInfoList.get(i).userId);

						// Map의 키를 10000명 이하의 유저들의 조합을 구별하기 위해 만든 임의의 계산식
						long key = user1.getUserNo() * (long)Math.pow(10, 4) + user2.getUserNo();
						
						// matchUserNo[] : 매칭된 두 회원의 번호를 짝지어줄 전역변수로 사용 ex) matchUserNo[100] = 50; -> 100번, 50번 회원이 매칭됨을 의미
						// 배열의 인덱스는 int이므로 변환이 필요
						int idx1 = user1.getUserNo().intValue();
						int idx2 = user2.getUserNo().intValue();
						matchUserNo[idx1] = user2.getUserNo();
						matchUserNo[idx2] = user1.getUserNo();
						
						// 이 탐색은 스프링에서 제공하는 멀티스레드로 작업된다.
						// 현재 스레드에서는 한 명의 탐색만 하고 있으므로 매칭이 잡혔다고 바로 리스트에서 삭제하면 상대방의 요청엔 응답을 해줄 수 없다.
						// 따라서 현재 매칭이 잡혔다는 정보를 따로 저장할 필요가 있다.
						
						statusList.set(i, true);						
						break;
					} // if문 종료
					
					
				} // for문 종료
				
				// statuslist가 true (매칭에 성공)이며 matchinfolist 의 id와 request받은 id 혹은 매칭대상의 id와 같은 것을 체크해서 count++을 해준다.
				// 결국 count == 2 이면 매칭에 성공했다는 것을 뜻한다.
				// 이 작업을 하는 이유는 TimerTask객체의 run()메소드에서 사용한 정보들은 이 위치에서 사용할 방법이 없으므로 매칭에 성공한 여부를 다시 한 번 확인하는 것
				int count = 0;
				for (int i = 0; i < statusList.size(); i++) {
					if (statusList.get(i) && matchInfoList.get(i).getUserId().equals(user1.getUserId())) {
						count++;
					}
					if (statusList.get(i) && matchInfoList.get(i).getUserId().equals(user2.getUserId())) {
						count++;
					}
					if (count == 2) {
						break;
					}
				}
				
				// timeindex : 매칭의 성공여부에 상관없이 아래 Thread를 종료시키기 위한 조건
				// 타이머 종료
				if (count == 2) {
					System.out.println("타이머 종료 " + userNo);
					timeIndex[(int) userNo] = true;
					timer.cancel();
				}
				
				// 총 10번의 탐색을 했으나 매칭에 성공하지 못한 경우
				if (i == 10) {
					timeIndex[(int) userNo] = true;
					timer.cancel();
				}
				i++;
			} // @Override method : run() 종료
			
			
			private boolean checkMatchingCondition(MatchingRequest request, int index) {
				User user = userRepository.findByUserId(request.getUserId());
				String userId = user.getUserId();
				long userNo = user.getUserNo();
				
				String userAddress = new String();
				String[] arr = user.getUserAddress().split(" ");
				for (int i = 0; i < 2; i++) {
					if (arr[i].equals("서울") || arr[i].equals("서울시")) {
						arr[i] = "서울특별시";
					}
					userAddress += arr[i] + " ";
				}
				
				String userGender = user.getUserGender();
				int firstChoice = request.getFirstChoice();
				int secondChoice = request.getSecondChoice();
				int thirdChoice = request.getThirdChoice();
				
				boolean firstCheck = false;
				boolean secondCheck = false;
				boolean thirdCheck = false;
				
				/**
				 * firstChoice : (1) 같은 지역 (2) 다른 지역
				 * secondChoice : (1) 동성친구 (2) 이성친구 (3) 상관없음
				 * thirdChoice : (1) 바둑 (2) 장기 (3) 화투 (4) 조깅 (5) 등산 (6) 상관없음
				 */
				
				// 나는 같은동네를 선택했다면 상대방도 같은동네를 선택하고 주소가 같아야만 매칭가능
				if (firstChoice == 1) {
					if (matchInfoList.get(index).firstChoice == 1) {
						if(userAddress.equals(matchInfoList.get(index).userAddress)) {
							firstCheck = true;
						}
					} 
				} else if (firstChoice == 2) {	// 서로 다른동네를 선택하고 주소가 다르면 매칭가능
					if (matchInfoList.get(index).firstChoice == 2) {
						if(!userAddress.equals(matchInfoList.get(index).userAddress)) {
							firstCheck = true;
						}
					}
				}
				
				// 첫번째 체크에서 실패했으면 두세번째 할 필요없이 바로 리턴
				if (!firstCheck) return false;
				
				// 동성친구를 만나고 싶다면 상대방은 동성이나 상관없음을 선택하고 성별이 같아야한다.
				if (secondChoice == 1) {
					if (matchInfoList.get(index).secondChoice == 1 || matchInfoList.get(index).secondChoice == 3) {
						if (userGender.equals(matchInfoList.get(index).userGender)) {
							secondCheck = true;
						}
					}
				} else if (secondChoice == 2) { // 이성친구를 만나고 싶다면 상대방은 이성이나 상관없음을 선택하고 성별이 달라야한다.
					if (matchInfoList.get(index).secondChoice == 2 || matchInfoList.get(index).secondChoice == 3) {
						if (!userGender.equals(matchInfoList.get(index).userGender)) {
							secondCheck = true;
						}
					}
				} else if (secondChoice == 3) { // 상관없음을 선택하면 상대방이랑 성별이 같다면 상대방은 1, 3을 체크 성별이 다르다면 2, 3체크됐어야 한다.
					if (userGender.equals(matchInfoList.get(index).userGender)) {
						if (matchInfoList.get(index).secondChoice == 1 || matchInfoList.get(index).secondChoice == 3) {
							secondCheck = true;
						}
					} else {
						if (matchInfoList.get(index).secondChoice == 2 || matchInfoList.get(index).secondChoice == 3) {
							secondCheck = true;
						}
					}
				}
				// 두 번째 체크에서 실패했으면 세 번째 할 필요없이 바로 리턴
				if (!secondCheck) return false;
				
				// 같은 취미를 선택했거나 상관없음을 선택했으면 매칭이 가능하다.
				if (thirdChoice != 6) {
					if (thirdChoice == matchInfoList.get(index).thirdChoice || matchInfoList.get(index).thirdChoice == 6) {
						thirdCheck = true;
					}
				} else if (thirdChoice == 6){
					thirdCheck = true;
				}
				
				if (!thirdCheck) return false;
				
				return true;
			} // checkMatchingCondition() 종료
			
			
			
			
		}; // TimerTask 종료 시점
		
		// 총 실행 10번 후 작업이 종료된다. 5초에 한 번씩 작업을 진행
		timer.schedule(task, 2000, 2000);

		try {
			while (!timeIndex[(int) userNo]) {
				Thread.sleep(2000);
			}
			// 매칭이 됐다면 반환객체만들고 매칭여부를 알리는 배열 값을 0으로 바꿔주고 타이머 종료후 리턴
			if (matchUserNo[(int) userNo] != 0) {
				long roomNum = 0;
				if (userNo < matchUserNo[(int) userNo]) {
					roomNum = userNo * (long)Math.pow(10, 4) + matchUserNo[(int) userNo];
				} else {
					roomNum = matchUserNo[(int) userNo] * (long)Math.pow(10, 4) + userNo;
				}
				System.out.println("userNo"+userNo);
				matchUserNo[(int) userNo] = 0;
				timeIndex[(int) userNo] = false;
				return roomNum;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 매칭에 실패한 경우 null을 리턴
		long fail = 0;
		return fail;
	}


	/**
	 * 매칭에 성공하든 실패하든 매칭중이 끝났으니 리스트에서 삭제한다.
	 * @param userId
	 */
	public void deleteList(String userId) {

		User user = userRepository.findByUserId(userId);
		long userNo = user.getUserNo();
		
		try {
			Thread.sleep(2000);
			for (int i = 0; i < statusList.size(); i++) {
				if (matchInfoList.get(i).userNo == userNo) {
					statusList.remove(i);
					matchInfoList.remove(i);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
