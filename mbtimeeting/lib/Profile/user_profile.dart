import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

import 'edit_profile.dart';

class UserProfile extends StatefulWidget {
  const UserProfile({super.key});

  @override
  State<UserProfile> createState() => _UserProfileState();
}

class _UserProfileState extends State<UserProfile> {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: const Color(0xFFE4D9D),
          title: const Text('프로필'),
        ),
        body: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(20.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text.rich(
                        TextSpan(
                          text: 'INTP\n', // 일반 텍 스트
                          style: TextStyle(fontSize: 16, color: Colors.grey), // 기본 스타일
                          children: <TextSpan>[
                            TextSpan(
                              text: '코틀린과 Node', // 굵게 만들 부분
                              style: TextStyle(fontWeight: FontWeight.bold, color: Colors.black), // 글자 굵게
                            ),
                          ],
                        ),
                      ),
                      CircleAvatar(
                        backgroundImage: AssetImage(""),
                      ),
                    ],
                  ),
                  const SizedBox(height: 10),
                  const Text('팔로원 3.4M\n팔로잉 1', style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
                  const SizedBox(height: 10,),
                  Row(
                    children: [
                      Expanded(child: ElevatedButton(onPressed: (){
                        Navigator.push(
                            context,
                            MaterialPageRoute(
                                builder: (context) => const EditProfile()
                            )
                        );
                      }, child: const Text("프로필 편집", style: TextStyle(color: Color(0xFF4B4B4B)),), style:
                      ElevatedButton.styleFrom(backgroundColor: Color(0xFFF2F2F2)),)),
                      SizedBox(width: 20,),
                      Expanded(child: ElevatedButton(onPressed: (){
                        showModalBottomSheet(
                          enableDrag: true,
                          context: context,
                          builder: (context) {
                            return Container(
                              height: 150,
                              child: Row(

                              ),
                            );
                          },
                        );
                      }, child: const Text("프로필 공유", style: TextStyle(color: Color(0xFF4B4B4B)),), style:
                      ElevatedButton.styleFrom(backgroundColor: Color(0xFFF2F2F2)),))
                    ],
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
