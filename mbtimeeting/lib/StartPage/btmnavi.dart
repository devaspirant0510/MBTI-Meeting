import 'package:flutter/material.dart';
import '../../profile/user_profile.dart';
import '../Chatting/chat.dart';
import '../Home/home.dart';
import '../MBTIMeeting/matching.dart';


class BottomNavigationWidget extends StatefulWidget {
  const BottomNavigationWidget({super.key});

  @override
  _BottomNavigationWidgetState createState() => _BottomNavigationWidgetState();
}

class _BottomNavigationWidgetState extends State<BottomNavigationWidget> {
  int _currentIndex = 0;

  // 각 탭에 연결할 페이지 리스트
  final List<Widget> _pages = [
    const Home(),
    const matching(),
    const Home(),
    const Dm(),
    const UserProfile(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        child: AppBar(),
        preferredSize: const Size.fromHeight(0),
      ),
      body: _pages[_currentIndex], // 현재 선택된 페이지를 보여줌
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        showSelectedLabels: false,
        showUnselectedLabels: false,
        currentIndex: _currentIndex,
        onTap: (index) {
          setState(() {
            _currentIndex = index; // 탭을 선택할 때 페이지를 변경
          });
        },
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home_outlined, color: Colors.black,),
            activeIcon: Icon(Icons.home, color: Colors.black,),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.connect_without_contact_outlined, color: Colors.black,),
            activeIcon: Icon(Icons.connect_without_contact, color: Colors.black,),
            label: 'Chat',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.add, color: Colors.black,),
            label: 'Meeting',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.message_outlined, color: Colors.black,),
            activeIcon: Icon(Icons.message, color: Colors.black,),
            label: 'DM',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline, color: Colors.black,),
            activeIcon: Icon(Icons.person, color: Colors.black,),
            label: 'Profile',
          ),
        ],
      ),
    );
  }
}
