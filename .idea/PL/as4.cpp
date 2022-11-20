#include <iostream>
using namespace std;

int problem1()
{
    ofstream of("NOTES.TXT", ios::trunc);

    for(int i = 1; i <= 100; i++){
        of<<i<<endl;
    }
    of.close();
}

int problem2(){
    ofstream of("OUT.TXT", ios::trunc);
    string text = "Time is a great teacher but unfortunately it kills all its pupils. Berlioz";

    ifs>>text;

    of.close();
}

int problem3(){
    ifstream ifs("OUT.TXT");
    int count = 0;
    int i;

    while(ifs.eof()){
        if()
    }
}