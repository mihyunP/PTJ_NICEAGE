package com.ssafy.niceage.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private List<MatchStatus> statusList = new ArrayList<>();
	private Map<Long, MatchSuccess> result = new HashMap<>();
	private long[] matchUserNo = new long[10000];

	public class ObjectForReturn {
		long roomNum;
		MatchSuccess matchSuccess;
		
		public ObjectForReturn(long roomNum, MatchSuccess matchSuccess) {
			this.roomNum = roomNum;
			this.matchSuccess = matchSuccess;
		}				
	}
	
	@Getter
	public class MatchInfo {
		String userId;
		int firstChoice;
		int secondChoice;
		int thirdChoice;

		public MatchInfo(String userId, int firstChoice, int secondChoice, int thirdChoice) {
			this.userId = userId;
			this.firstChoice = firstChoice;
			this.secondChoice = secondChoice;
			this.thirdChoice = thirdChoice;
		}
	}
	
	@Getter
	public class MatchStatus {
		String userId;
		boolean status;
		
		public MatchStatus(String userId, boolean status) {
			this.userId = userId;
			this.status = status;
		}
				
	}

	@Getter
	public class MatchSuccess {
		User user1;
		User user2;
		boolean matching;

		public MatchSuccess(User user1, User user2, boolean matching) {
			this.user1 = user1;
			this.user2 = user2;
			this.matching = matching;
		}

	}

	public void addList(MatchingRequest request) {
		MatchInfo object = new MatchInfo(request.getUserId(), request.getFirstChoice(), request.getSecondChoice(),
				request.getThirdChoice());
		matchInfoList.add(object);
		System.out.println(matchInfoList.get(matchInfoList.size() - 1).firstChoice + " " + matchInfoList.get(matchInfoList.size() - 1).secondChoice + " "
				+ matchInfoList.get(matchInfoList.size() - 1).thirdChoice);
		statusList.add(new MatchStatus(request.getUserId(), false));

	}

	public ObjectForReturn findList (MatchingRequest request) {
		
		User user = userRepository.findByUserId(request.getUserId());
		long userNo = user.getUserNo();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			User user1;
			User user2;
			@Override
			public void run() {
				System.out.println("실행중임");
				
				// 친구매칭을 클릭한 사람 수 만큼 실시간으로 등록된 매칭정보 객체리스트를 탐색
				// 같은 분류의 매칭을 한 사람을 찾을 시 정보를 저장하고 리스트를 삭제한다.
				L:for (int i = 0; i < matchInfoList.size(); i++) {
					if (request.getUserId().equals(matchInfoList.get(i).userId)) {
						continue;
					}
					// 친구매칭 조건이 같은 사람이 있는 경우
					if (request.getFirstChoice() == matchInfoList.get(i).firstChoice
							&& request.getSecondChoice() == matchInfoList.get(i).secondChoice
							&& request.getThirdChoice() == matchInfoList.get(i).thirdChoice
							&& !statusList.get(i).status) {

						// 1:1매칭에 성공한 두명의 정보를 각각 저장
						user1 = userRepository.findByUserId(request.getUserId());
						user2 = userRepository.findByUserId(matchInfoList.get(i).userId);

						// Map의 키를 10000명 이하의 유저들의 조합을 구별하기 위해 만든 임의의 계산식
						long key = user1.getUserNo() * (long)Math.pow(10, 4) + user2.getUserNo();
						
						// response에 담을 객체, result : 전역변수 (key, 객체)를 담은 Map
						result.put(key, new MatchSuccess(user1, user2, true));
						
						// matchUserNo[] : 매칭된 두 회원의 번호를 짝지어줄 전역변수로 사용 ex) matchUserNo[100] = 50; -> 100번, 50번 회원이 매칭됨을 의미
						// 배열의 인덱스는 int이므로 변환이 필요
						int idx1 = user1.getUserNo().intValue();
						int idx2 = user2.getUserNo().intValue();
						matchUserNo[idx1] = user2.getUserNo();
						matchUserNo[idx2] = user1.getUserNo();
						
						// 이 탐색은 스프링에서 제공하는 멀티스레드로 작업된다.
						// 현재 스레드에서는 한 명의 탐색만 하고 있으므로 매칭이 잡혔다고 바로 리스트에서 삭제하면 상대방의 요청엔 응답을 해줄 수 없다.
						// 따라서 현재 매칭이 잡혔다는 정보를 따로 저장할 필요가 있다.
						MatchStatus matchStatus1 = new MatchStatus(user1.getUserId(), true);
						MatchStatus matchStatus2 = new MatchStatus(user2.getUserId(), true);
						
						statusList.set(i, matchStatus1);						
						for (int j = 0; j < matchInfoList.size(); j++) {
							if (matchInfoList.get(j).userId.equals(user1.getUserId())) {
								statusList.set(j, matchStatus2);
								System.out.println(user1.getUserId() + " " + user2.getUserId());
								break L;
							}
						}
						

					} // if문 종료
					
					
				} // for문 종료
				
				// 전역리스트 원소를 2개지워야하므로 인덱스를 따로 저장해놓고  한번에 지워준다.
				int index1 = 0;
				int index2 = 0;
				int count = 0;
				for (int i = 0; i < statusList.size(); i++) {
					if (statusList.get(i).status && matchInfoList.get(i).getUserId().equals(user1.getUserId())) {
						index1 = i;
						count++;
						if (count == 2) {
							break;
						}
					}
					if (statusList.get(i).status && matchInfoList.get(i).getUserId().equals(user2.getUserId())) {
						index2 = i;
						count++;
						if (count == 2) {
							break;
						}
					}
				}
				
				if (count == 2) {
					statusList.remove(index1);
					statusList.remove(index2);
					matchInfoList.remove(index1);
					matchInfoList.remove(index2);					
				}
				
				System.out.println("size"+matchInfoList.size());
			} // @Override method : run() 종료
		}; // TimerTask 종료 시점
		
		// 총 실행 10번 후 작업이 종료된다. 5초에 한 번씩 작업을 진행
		for (int i = 0; i < 10; i++) {
			timer.schedule(task, 5000);
			// 매칭이 됐다면 반환객체만들고 매칭여부를 알리는 배열 값을 0으로 바꿔주고 타이머 종료후 리턴
			if (matchUserNo[(int) userNo] != 0) {
				long key = userNo * (long)Math.pow(10, 4) + matchUserNo[(int) userNo];
				ObjectForReturn returnObject = new ObjectForReturn(key, result.get(key));
				System.out.println("userNo"+userNo);
				matchUserNo[(int) userNo] = 0;
				timer.cancel();
				return returnObject;
			}
			timer.cancel();
		}

		return null;
	}

}
