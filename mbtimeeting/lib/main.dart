import 'package:flutter/material.dart';
import 'package:mbtimeeting/StartPage/login_screen.dart';
import 'package:supabase_flutter/supabase_flutter.dart';
import 'MBTIMeeting/seung.dart';
import 'StartPage/btmnavi.dart';

void main() async{
  await Supabase.initialize(
    url: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRpY3p5c3hpbnBweWF4YndjeG5rIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjc3ODk3OTksImV4cCI6MjA0MzM2NTc5OX0.iOmIdU_rY5m0I1ZGxuUePNwABqf2_Xlm1zYtiJ-1GgY',
    anonKey: 'YOUR_ANON_KEY',
  );
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        title: 'Flutter Demo',
        initialRoute: "/call", // 처음 보여지는 화면 path 경로
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        routes: {
          "/call": (context) => const BottomNavigationWidget(),
          /**
           * "/home" :(context) => HomePage(),
           * "/dm" :(context) => DmPage(),
           */
        }
        );
  }
}

