import 'package:flutter/material.dart';
import 'package:mbtimeeting/DM.dart';
import 'package:mbtimeeting/seung.dart';

class Home extends StatefulWidget {
  const Home({super.key});

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  int _selectedIndex = 0; // 선택된 탭의 인덱스

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
    else if (index == 3) {
      Navigator.push(
          context,
          MaterialPageRoute(builder: (context) => const Dm()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('MBTI Meeting'), // 상단 AppBar 제목
        ),
        body: Center(
          // 선택된 탭에 따라 다른 화면을 보여줌
          child: Text(
            'Selected Tab: $_selectedIndex',
            style: const TextStyle(fontSize: 24),
          ),
        ),
        bottomNavigationBar: BottomNavigationBar(
          type: BottomNavigationBarType.fixed, // 카카오톡처럼 고정된 바
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              icon: Icon(Icons.home),
              label: '홈',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.search),
              label: '검색',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.connect_without_contact),
              //communication 아이콘을 사용하고 싶었으나 어째선지 불러오지못함.
              label: 'MBTI Meeting  ',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.chat),
              label: 'DM',
            ),
            BottomNavigationBarItem(
              icon: Icon(Icons.person),
              label: '프로필',
            ),
          ],
          currentIndex: _selectedIndex, // 현재 선택된 탭 인덱스
          selectedItemColor: Colors.amber[800], // 선택된 아이템 색상
          unselectedItemColor: Colors.black, // 선택되지 않은 아이템 색상
          onTap: _onItemTapped, // 탭 클릭 시 호출
        ),
      ),
    );
  }
}
