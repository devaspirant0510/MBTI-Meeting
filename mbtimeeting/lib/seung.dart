import 'package:flutter/material.dart';

class SeungPage extends StatelessWidget {
  const SeungPage({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          //앱 상단
          title: const Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween, // 좌우로 배치
            children: [
              //상단에 <부분 코딩
              Align(
                alignment: Alignment.centerLeft,
                child: Text('<' ,
                  style: TextStyle(
                    color: Colors.grey,
                    fontSize: 40.0,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              //상단에 …부분 코딩
              Align(
                alignment: Alignment.centerRight,
                child: Padding(
                  padding: EdgeInsets.only(bottom: 25.0),
                  child: Text('…',
                    style: TextStyle(
                      color: Colors.grey,
                      fontSize: 40.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
        body: Container(
          color: Colors.white70,
        ),
      )
    );
  }
}

