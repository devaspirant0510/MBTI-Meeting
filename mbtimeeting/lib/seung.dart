import 'dart:async';
import 'package:flutter/material.dart';
import 'package:mbtimeeting/Home.dart';

class SeungPage extends StatefulWidget {
  const SeungPage({super.key});

  @override
  _SeungPageState createState() => _SeungPageState();
}

class _SeungPageState extends State<SeungPage> {
  // 음성 인식 상태를 나타내는 변수
  bool isSpeaking = false; // 초기값 false로 설정
  bool isHovered = false; // 마우스 오버 상태를 나타내는 변수
  bool isInCall = false; // 통화 중인지 여부
  bool isUser2Joined = false; //User2가 입장했는지 여부
  bool isCallEnded = false; // 통화 종료 상태를 나타내는 변수


  // 음성 인식 상태를 토글하는 함수
  void _toggleSpeakingState() {
    setState(() {
      isSpeaking = !isSpeaking; // 음성 인식 상태를 반전시킴 (true <-> false)
    });
  }

  void _goToHome() {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (context) => const Home()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        // 상단 AppBar 설정
        appBar: AppBar(
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              MouseRegion(
                onEnter: (_) {
                  setState(() {
                    isHovered = true; // 마우스가 올려졌을 때 상태 변경
                  });
                },
                onExit: (_) {
                  setState(() {
                    isHovered = false; // 마우스가 나갔을 때 상태 변경
                  });
                },
                child: GestureDetector(
                  onTap: _goToHome,
                  child: Text(
                    '<',
                    // 왼쪽에 '<' 표시
                    style: TextStyle(
                      color: isHovered ? Colors.amber : Colors.grey,
                      fontSize: 40.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
              const Padding(
                padding: EdgeInsets.only(bottom: 25.0),
                child: Text(
                  '…',
                  // 오른쪽에 '…' 표시
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 40.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ],
          ),
        ),
        // Body 부분 설정
        body: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // 첫 번째 텍스트, "상대방 응답 기다리는 중..."
            Container(
              height: 100,
              child: Padding(
                padding: EdgeInsets.only(top: 25.0, left: 25.0),
                child: Text(
                  isInCall
                      ? '통화 중...' // User2가 통화를 시작했을 때 상태
                      : isCallEnded
                      ? '통화 종료' // 통화가 종료된 상태
                      : isUser2Joined
                      ? '상대방 응답 기다리는 중...' // User2가 입장했을 때 상태
                      : 'MBTI 매칭 중...', // 기본 상태
                  style: TextStyle(fontSize: 30.0),
                ),
              ),
            ),
            // User1의 상태를 표시하는 Row
            Container(
              height: 150,
              child: Padding(
                padding: EdgeInsets.only(top: 0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    // User1의 아이콘 표시, 음성 상태에 따라 변함
                    _UserIcon(isSpeaking: isSpeaking), // 음성 인식 상태에 따라 아이콘 변화
                    const Text(
                      "User1",
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 20.0,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            // User2의 상태를 표시하는 Row
            Container(
              height: 50,
              alignment: Alignment.center,
              child: Padding(
                padding: EdgeInsets.only(bottom: 25.0),
                child: Text("...",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 20.0,
                  ),
                ),
              )
            ),
            Container(
              height: 150,
              child: Padding(
                padding: EdgeInsets.only(top: 30.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    // User2 이름 텍스트
                    const Padding(
                      padding: EdgeInsets.only(right: 0),
                      child: Text(
                        "User2",
                        style: TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 20.0,
                        ),
                      ),
                    ),
                    // User2의 아이콘 표시, 음성 상태에 따라 변함
                    _UserIcon(isSpeaking: isSpeaking), // 음성 인식 상태에 따라 아이콘 변화
                  ],
                ),
              ),
            ),
            //통화 종료 버튼
            Padding(
              padding: const EdgeInsets.only(top: 100.0), // 원하는 패딩 값을 설정
              child: Container(
                height: 50.0,
                alignment: Alignment.center,
                decoration: BoxDecoration(
                  color: Colors.red,
                  shape: BoxShape.circle,
                ),
                child: IconButton(
                  icon: Icon(Icons.phone_disabled, color: Colors.white),
                  onPressed: () {
                    setState(() {
                      isInCall = false; // 통화 중 상태를 false로 변경
                      isCallEnded = true; // 통화 종료 상태를 true로 변경
                    });
                  },
                ),
              ),
            ),
            //테스트용 User2 입장버튼
            Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () {
                    setState(() {
                      isUser2Joined = true; // User2가 입장했을 때 상태 변경
                    });
                  },
                  child: const Text('User2 입장'),
                ),
                // User2가 통화를 시작하는 버튼 (테스트용)
                ElevatedButton(
                  onPressed: () {
                    setState(() {
                      if (isUser2Joined) {
                        isInCall = true; // User2가 통화를 시작했을 때 상태 변경
                      }
                    });
                  },
                  child: const Text('User2 통화 시작'),
                ),
                // 음성 상태를 토글하는 버튼
                ElevatedButton(
                  onPressed: _toggleSpeakingState, // 버튼 클릭 시 음성 상태 전환
                  child:
                      Text(isSpeaking ? '말하기 종료' : '말하기 시작'), // 상태에 따라 텍스트 변화
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

// 사용자 아이콘을 나타내는 위젯
class _UserIcon extends StatelessWidget {
  final bool isSpeaking; // 음성 인식 중인지 여부

  const _UserIcon({required this.isSpeaking});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: 100.0,
      height: 100.0,
      decoration: BoxDecoration(
        color: Colors.grey[300], // 기본 배경 색
        shape: BoxShape.circle, // 원형 모양
        border: Border.all(
          // 음성 인식 중이면 테두리를 초록색으로 표시
          color: isSpeaking ? Colors.green : Colors.transparent,
          width: 4.0,
        ),
      ),
      // 기본 아이콘은 회색 사람 모양
      child: const Icon(
        Icons.person,
        size: 50.0,
        color: Colors.grey,
      ),
    );
  }
}
