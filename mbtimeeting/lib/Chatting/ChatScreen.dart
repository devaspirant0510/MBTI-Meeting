import 'package:flutter/material.dart';
import 'package:mbtimeeting/Chatting/chat_room.dart';

// Message 클래스 정의
class Message {
  final String content;  // 메시지 내용
  final bool isSentByMe; // 내가 보낸 메시지인지 여부

  Message({required this.content, required this.isSentByMe});
}

class ChatScreen extends StatefulWidget {
  final ChatRoom chatRoom;

  const ChatScreen({Key? key, required this.chatRoom}) : super(key: key);

  @override
  _ChatScreenState createState() => _ChatScreenState();
}

class _ChatScreenState extends State<ChatScreen> {
  final TextEditingController _messageController = TextEditingController();
  List<Message> _messages = [];

  // 메시지 전송 함수
  void _sendMessage() {
    if (_messageController.text.isNotEmpty) {
      setState(() {
        _messages.add(Message(content: _messageController.text, isSentByMe: true));
        _messageController.clear(); // 메시지 전송 후 입력란 초기화
      });
    }
  }

  // 무작위 메시지 수신 함수
  void _receiveRandomMessage() {
    setState(() {
      _messages.add(Message(content: '상대방의 무작위 메시지입니다.', isSentByMe: false));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.chatRoom.name),
        actions: [
          IconButton(
            icon: const Icon(Icons.message),
            onPressed: _receiveRandomMessage, // 무작위 메시지 수신 버튼
          ),
        ],
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: _messages.length,
              itemBuilder: (context, index) {
                final message = _messages[index];
                return Align(
                  alignment: message.isSentByMe ? Alignment.centerRight : Alignment.centerLeft,
                  child: Container(
                    margin: const EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                    padding: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: message.isSentByMe ? Colors.blue[100] : Colors.grey[300],
                      borderRadius: BorderRadius.circular(10),
                    ),
                    child: Text(message.content),
                  ),
                );
              },
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _messageController,
                    decoration: const InputDecoration(
                      labelText: '메시지 입력',
                    ),
                  ),
                ),
                IconButton(
                  icon: const Icon(Icons.send),
                  onPressed: _sendMessage, // 메시지 전송
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
