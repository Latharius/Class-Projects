using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// Problem 1
namespace SecondCS{
    public class Problem1{
        public static IEnumerable<int> myFilter(IEnumerable<int> input){
          var mul5 = input.Where(r => r % 5 != 0 || r <= 50);
          var cub = mul5.Select(n => n * n * n);
          var oddN = cub.Where(r => r % 2 != 0).ToArray();
          
          return oddN;
        }
    }
  
    public class Program{
        public static void Main(string[] args){
            Random rnd = new Random(5); // Important to seed with 5 for repeatability.
            
            var listForProblem = Enumerable.Range(1, 100).Select(i => rnd.Next() % 101);
            var answer = Problem1.myFilter(listForProblem);
            
            foreach (int i in answer){
                Console.WriteLine(i);
            }
        }
    }
}

// Problem 2
namespace SecondCS{
    public class Problem2{
        public static IEnumerable<int> myFilter(IEnumerable<int> input){
            var mul6 = input.Where(r => r % 6 != 0 || r < 42);
            var sq = mul6.Select(n => n * n);
            var evenN = sq.Where(r => r % 2 == 0).ToArray();
            
            return evenN;
        }
    }
  
    public class Program{
        public static void Main(string[] args){
            Random rnd = new Random();
            
            var listForProblem = Enumerable.Range(1, 100).Select(i => rnd.Next() % 101);
            var answer = Problem2.myFilter(listForProblem);
            
            foreach (int i in answer){
                Console.WriteLine(i);
            }
        }
    }
}

// Problem 3
namespace SecondCS{
    public class TestProblem2{
        public static IEnumerable<int> merge(IEnumerable<int> input1, IEnumerable<int> input2, IEnumerable<int> input3, IEnumerable<int> input4){
          //IEnumerable<int> ret, ret2, ret3, ret4, ret5;
          
          ret2 = input1.Where(r => r % 10 == 0);
          ret3 = input2.Where(r => r % 10 == 0);
          ret4 = input3.Where(r => r % 10 == 0);
          ret5 = input4.Where(r => r % 10 == 0);
          ret = input1.Intersect(input2);
          ret = ret.Intersect(input3);
          ret = ret.Intersect(input4);
          
          ret = ret.Union(ret2);
          ret = ret.Union(ret3);
          ret = ret.Union(ret4);
          ret = ret.Union(ret5);
          
          return ret;
        }
    }
  
    public class Program{
        public static void Main(string[] args){
            Random rnd = new Random();
            var list1 = Enumerable.Range(2, 15).Select(i => rnd.Next() % 16);
            var list2 = Enumerable.Range(3, 42).Select(i => rnd.Next() % 42).Where(r => r % 2 == 0);
            var list3 = Enumerable.Range(4, 21).Select(i => rnd.Next() % 22).Where(r => r % 2 != 0);
            var list4 = Enumerable.Range(6, 105).Select(i => rnd.Next() % 106).Where(r => r % 5 == 0);
            var answer = TestProblem2.merge(list1, list2, list3, list4);

            foreach (int i in answer){
                Console.WriteLine(i);
            }
        }
    }
}
