import 'package:flutter/material.dart';

import '../Home/home.dart';
import '../MBTIMeeting/seung.dart';


// 채팅방 모델 클래스
class ChatRoom {
  final String name; // 사용자 이름
  final String lastMessage; // 최근 메시지

  ChatRoom({
    required this.name,
    required this.lastMessage,
  });
}

class Dm extends StatefulWidget {
  const Dm({super.key});

  @override
  _DmState createState() => _DmState();
}

class _DmState extends State<Dm> {
  int _selectedIndex = 0; // 선택된 탭의 인덱스
  bool isHovered = false; // 마우스 오버 상태를 나타내는 변수
  List<ChatRoom> _chatRooms = []; // 채팅방 목록

  // 탭 변경 시 호출되는 함수
  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index; // 선택된 인덱스 업데이트
    });

    if (index == 2) { // 인덱스 2가 MBTI Meeting 탭
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => const SeungPage()),
      );
    }
  }

  // 홈 화면으로 이동하는 함수
  void _goToHome() {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (context) => const Home()),
    );
  }

  // 채팅방 생성 함수
  void _createChatRoom() {
    setState(() {
      _chatRooms.add(ChatRoom(
        name: '사용자 ${_chatRooms.length + 1}', // 사용자 이름
        lastMessage: '최근 메시지 내용입니다.', // 최근 메시지
      ));
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Row(
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
                    style: TextStyle(
                      color: isHovered ? Colors.amber : Colors.grey,
                      fontSize: 40.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
              const Padding(
                padding: EdgeInsets.only(left: 15.0),
                child: Text(
                  'MBTI',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 20.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 15.0),
                child: Text(
                  '코틀린과 node',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 28.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              const Spacer(), // 남은 공간을 차지하여 아이콘을 오른쪽으로 이동
              IconButton(
                icon: const Icon(Icons.add), // 채팅방 생성 아이콘
                onPressed: _createChatRoom, // 아이콘 클릭 시 채팅방 생성
              ),
            ],
          ),
        ),
        body: _chatRooms.isEmpty // 채팅방이 비어 있을 경우
            ? Center(child: Text('채팅방이 없습니다. 채팅방을 생성하세요.'))
            : ListView.builder(
          itemCount: _chatRooms.length,
          itemBuilder: (context, index) {
            final chatRoom = _chatRooms[index];
            return ListTile(
              leading: CircleAvatar(
                backgroundColor: Colors.grey[300], // 아이콘 배경 색상
                child: Icon(Icons.person, color: Colors.black), // 사람 아이콘
              ),
              title: Text(chatRoom.name), // 사용자 이름
              subtitle: Text(chatRoom.lastMessage), // 최근 메시지
              onTap: () {
                // 채팅방 클릭 시 동작
              },
            );
          },
        ),
      ),
    );
  }
}
