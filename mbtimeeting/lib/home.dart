import 'package:flutter/material.dart';
import 'alarm.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Color(0xFFE4D9D),
          title: Text('MBTI Meeting'),
          elevation: 0,
          actions: [
            IconButton(onPressed: (){}, icon: Icon(Icons.search)),
            IconButton(onPressed: (){
              Navigator.push(
                  context,
                  MaterialPageRoute(
                      builder: (context) => const Alarm()
                  )
              );
            }, icon: Icon(Icons.notifications))
          ],
        ),
        body: Center(

        ),
      ),
    );  // 바텀 네비게이션 위젯을 호출
  }
}
