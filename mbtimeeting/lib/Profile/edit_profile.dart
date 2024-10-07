import 'package:flutter/material.dart';

class EditProfile extends StatefulWidget {
  const EditProfile({super.key});

  @override
  State<EditProfile> createState() => _EditProfileState();
}

class _EditProfileState extends State<EditProfile> {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Color(0xFFE4D9D),
          title: Text('프로필 편집'),
          actions: [
            TextButton(onPressed: (){}, child: Text('완료')),
          ],
        ),
        body: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Card(
            child: Container(
              width: MediaQuery.of(context).size.width,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text('이름'),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
