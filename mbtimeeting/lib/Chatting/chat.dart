import 'package:flutter/material.dart';
import 'package:mbtimeeting/Chatting/ChatScreen.dart';
import 'package:mbtimeeting/Chatting/chat_room.dart';
import '../StartPage/btmnavi.dart'; // ChatRoom 클래스 가져오기

class Dm extends StatefulWidget {
  const Dm({super.key});

  @override
  _DmState createState() => _DmState();
}

class _DmState extends State<Dm> {
  bool isHovered = false;
  List<ChatRoom> _chatRooms = []; // chat_room.dart에서 가져온 ChatRoom 클래스 사용

  void _goToHome() {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(builder: (context) => const BottomNavigationWidget()),
    );
  }

  void _createChatRoom() {
    setState(() {
      _chatRooms.add(ChatRoom(
        name: '사용자 ${_chatRooms.length + 1}',
        lastMessage: '최근 메시지 내용입니다.',
      ));
    });
  }

  void _enterChatRoom(ChatRoom chatRoom) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => ChatScreen(chatRoom: chatRoom), // chat_screen.dart에서 불러옴
      ),
    );
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
                    isHovered = true;
                  });
                },
                onExit: (_) {
                  setState(() {
                    isHovered = false;
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
              const Spacer(),
              IconButton(
                icon: const Icon(Icons.add),
                onPressed: _createChatRoom,
              ),
            ],
          ),
        ),
        body: _chatRooms.isEmpty
            ? const Center(child: Text('채팅방이 없습니다. 채팅방을 생성하세요.'))
            : ListView.builder(
          itemCount: _chatRooms.length,
          itemBuilder: (context, index) {
            final chatRoom = _chatRooms[index];
            return ListTile(
              leading: CircleAvatar(
                backgroundColor: Colors.grey[300],
                child: const Icon(Icons.person, color: Colors.black),
              ),
              title: Text(chatRoom.name),
              subtitle: Text(chatRoom.lastMessage),
              onTap: () => _enterChatRoom(chatRoom),
            );
          },
        ),
      ),
    );
  }
}
