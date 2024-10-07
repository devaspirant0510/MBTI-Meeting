import 'package:flutter/material.dart';
import 'package:mbtimeeting/Home.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        initialRoute: "/call", // 처음 보여지는 화면 path 경로
        theme: ThemeData(
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
          useMaterial3: true,
        ),
        routes: {
          "/call": (context) => const Home(),
          /**
           * "/home" :(context) => HomePage(),
           * "/dm" :(context) => DmPage(),
           */
        }
        );
  }
}

