import 'package:flutter/material.dart';
import 'package:supabase_flutter/supabase_flutter.dart';
import 'login_screen.dart';

void main() async {
  /// TODO: update Supabase credentials with your own
  await Supabase.initialize(
    url: 'https://diczysxinppyaxbwcxnk.supabase.co',
    anonKey: 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRpY3p5c3hpbnBweWF4YndjeG5rIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjc3ODk3OTksImV4cCI6MjA0MzM2NTc5OX0.iOmIdU_rY5m0I1ZGxuUePNwABqf2_Xlm1zYtiJ-1GgY',
  );
  runApp(const MyApp());
}

final supabase = Supabase.instance.client;

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Auth',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const LoginScreen(),
    );
  }
}