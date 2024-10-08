import 'package:flutter/material.dart';
import 'alarm.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'MBTI Chat',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Home(),
    );
  }
}

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: const Color(0xFFE4D9D),
        title: const Text('MBTI Meeting'),
        elevation: 0,
        actions: [
          IconButton(
              onPressed: () {},
              icon: const Icon(Icons.search)),
          IconButton(
              onPressed: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(
                    builder: (context) => const Alarm(),
                  ),
                );
              },
              icon: const Icon(Icons.notifications))
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.all(20.0),
        // 높이를 강제하지 않고 내용에 맞게 설정
        child: IntrinsicHeight( // 자식 요소의 높이에 맞게 부모 높이 설정
          child: Card(
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
            ),
            elevation: 0.0,
            child: SingleChildScrollView(
              scrollDirection: Axis.horizontal, // 가로 스크롤을 허용
              child: Row(
                children: [
                  _buildProfile('assets/images/god.jpg', '대상혁'),
                  _buildProfile('assets/images/god.jpg', '이상혁'),
                  _buildProfile('assets/images/god.jpg', '김철수'),
                  _buildProfile('assets/images/god.jpg', '박영희'),
                  _buildProfile('assets/images/god.jpg', '박영희'),
                  _buildProfile('assets/images/god.jpg', '박영희'),
                  _buildProfile('assets/images/god.jpg', '박영희'),
                  _buildProfile('assets/images/god.jpg', '박영희'),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  // 프로필을 빌드하는 함수
  Widget _buildProfile(String imagePath, String name) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 10.0),
      child: Column(
        mainAxisSize: MainAxisSize.min, // 세로 공간을 내용 크기에 맞게 조정
        children: [
          CircleAvatar(
            backgroundImage: AssetImage(imagePath),
            radius: 30, // 아바타 크기 설정
          ),
          const SizedBox(height: 8),
          Text(name),
        ],
      ),
    );
  }
}
