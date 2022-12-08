using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace problems1234{
    class student{ //problem 1
        private int admno;
        private char[] sname = new char[20];
        private float eng, math, science, total;

        private static float cTotal(){
            float total = eng + math + science;

            return total;
        }

        public static void Takedata(int admno, char[] sname, float math, float eng, float science){
            this.admno = admno;
            this.sname = sname;
            this.math = math;
            this.eng = eng;
            this.science = science;

            total = cTotal(math, eng, science);
        }

        public static void Showdata(){
            Console.WriteLine("admno: " + admno);
            Console.WriteLine("sname: " + sname);
            Console.WriteLine("math: " + math);
            Console.WriteLine("eng: " + eng);
            Console.WriteLine("science: " + science);
            Console.WriteLine("total: " + total);
        }

        public static void Main (string[] args){
            Takedata();
            Showdata();
        }
    }

    class batsman{ //problem 2
        private int[] bcode = new int[4];
        private char[] bname = new char[20];
        private int innings, notout, runs;
        private float batavg;

        private float calcavg(){
            batavg = runs / (innings - notout);

            return batavg;
        }

        public static void readdata(int[] bcode, char[] bname, int innings, int notout, int runs, float batavg){
            this.bcode = bcode;
            this.bname = bname;
            this.innings = innings;
            this.notout = notout;
            this.runs = runs;

            this.batavg = calcavg();
        }

        public static void displaydata(){
            Console.WriteLine("bcode: " + bcode);
            Console.WriteLine("bname: " + bname);
            Console.WriteLine("innings: " + innings);
            Console.WriteLine("notout: " + notout);
            Console.WriteLine("runs: " + runs);
            Console.WriteLine("batavg: " + batavg);
        }

        public static void Main (string[] args){
            readdata();
            displaydata();
        }

    }

    public static problem3() {
        using(StreamReader file = new StreamReader(textFile)) {
            int count = 0;
            string line;

            while ((line = file.ReadLine()) != null) {
                if((line[0] != 'A') || (line[0] != 'a'))
                    count++;
            }
            Console.Writeline(count);
        }
    }

    public class problem4 {
        public static void Main (string[] args){

        Dog dog = new Dog();
        Turtle turtle = new Turtle();
        Bird bird = new Bird();

        dog.breath();
        turtle.breath();
        bird.breath();

        dog.bark();
        turtle.retract();
        bird.fly();
        }
    }

    public class Animal {
        public void breath() {
        Console.WriteLine("Breathing");
        }

        public void sleep() {
        Console.WriteLine("Sleeping");
        }

        public void eat() {
        Console.WriteLine("Eating");
        }
    }

    class Bird : Animal {
        public void fly () {
        Console.WriteLine("Bird is flying");
        }
    }

    class Turtle : Animal {
        public void retract () {
        Console.WriteLine("Turtle is retracting");
        }
    }

    class Dog : Animal {
        public void bark () {
        Console.WriteLine("Dog is barking");
        }
    }


    class problems567{
        static int[] numbers1 = { 1,3,5,6,7,8,9,10,11,12,13,29,30,31,32,33 };
        static int[] numbers2 = { 30,31,40,41,50,51,60,61,70,71,72,74,75,100,101,101,200 };
        static int[] numbers3 = { -5, -6, -7, -8, 47, 50, 60, 1000, 2000, 3000 };
        static string[] names = { "Sam", "Joe", "Suzy", "Sara", "Jim Bob", "Billy Bob", "George","Jane", "Emily" };

        public static IEnumerable<int> problem5(IEnumerable<int> numbers1, IEnumerable<int> numbers2){
            IEnumerable<int> list = numbers1.intersect(numbers2).where( x => x % 2 != 0 && x % 5 == 0);

            return list
        }

        public static IEnumerable<int> problem6(IEnumerable<int> numbers3){
            IEnumerable<int> sq = numbers3.Select( x => x * x);
            IEnumerable<int> oddsq = sq.where( x => x % 2 != 0);

            return oddsq;
        }

        public static IEnumerable<int> problem6(IEnumerable<int> numbers2){
            IEnumerable<int> prime = numbers2.where(x => isPrime(x) == true);

            return prime;
        }

        public static boolean I isPrime(x){
            bool isPrime = true;

            for (int i = 2; i <= Math.Sqrt(x); i++){
                if (x % i == 0){
                    isPrime = false;
                    break;
                }
            }
        }
    }
}

