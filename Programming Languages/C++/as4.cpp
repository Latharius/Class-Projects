#include <iostream>
#include <fstream>
#include <cstdio>
using namespace std;

int problem1()
{
    ofstream of("NOTES.TXT", ios::trunc);

    for(int i = 1; i <= 100; i++){
        of<<i<<endl;
    }
    of.close();

    return 0;
}

int problem2(){
    ofstream of;
    of.open("OUT.TXT");
    string text = "Time is a great teacher but unfortunately it kills all its pupils. Berlioz";

    ifs<<text;
    of.close();

    return 0;
}

void problem3(){
    ifstream ifs;
    ifs.open("OUT.TXT");
    char c;
    int count = 0;

    while(!ifs.eof()){
        ifs.get(c);

        if(isalpha(c)){
            count++;
        }
    }
    cout<<count<<endl;
}

void problem4(){
    ifstream ifs;
    ifs.open("OUT.TXT");
    char c;
    int count = 0;

    while(!ifs.eof()){
        ifs.get(c);

        if(c == ' '){
            count++;
        }
    }
    cout<<count<<endl;

    ifs.close();
}

void problem5(){
    ifstream ifs;
    ifs.open("OUT.TXT");
    char word[20];
    int count = 0;

    while(!ifs.eof()){
        ifs>>word>>endl;
        count++;
    }
    cout<<count<<endl;

    ifs.close();
}

void problem6(){
    ifstream ifs;
    ifs.open("STORY.TXT");
    char word[20];
    int count = 0;

    while(!ifs.eof()){
        ifs>>word;

        if(strcmpi(word, "the") == 0){
            count++;
        }
    }
    cout<<count<<endl;

    ifs.close();
}

void problem7(){
    ifstream ifs;
    ifs.open("STORY.TXT");
    char story[100];
    int count = 0;

    while(!ifs.eof()){
        ifs.getline(story, 100);

        if(story[0] != 'A'){
            count++;
        }
    }
    cout<<count<<endl;

    ifs.close();
}

void copyupper(){ //Problem 8
    ifstream ifs;
    ifs.open("FIRST.TXT");

    ofstream of;
    of.open("SECOND.TXT");
    char c;

    while(!ifs.eof()){
        ifs.get(c);
        c = toupper(c);
        of<<c;
    }
    ifs.close();
    of.close();
}

void vowelwords(){ //Problem 9
    ifstream ifs;
    ifs.open("FIRST.TXT");

    ofstream of;
    of.open("SECOND.TXT");
    char word[20];

    while(!ifs.eof){
        ifs>>word;

        if(word[0] == 'a' || word[0] == 'e' || word[0] == 'i' || word[0] == 'o' || word[0] == 'u'){
            of<<word<<" "<<endl;
        }
    }
    ifs.close();
    of.close();
}

void problem10(){
  ofstream file("problem10.bin", ios::binary);
  EMPLOYEE s; 
  s.GETIT();
  file.write((char ) &s, sizeof(s));
  file.close();
  ifstream fileR("problem10.bin", ios::binary);
  fileR.read((char) &s, sizeof(s));
  s.SHOWIT();
  fileR.close();
}

void problem12(){
  ifstream file("SHIP.DAT", ios::binary);
  computer obj; 
  file.read((char*) &obj, sizeof(obj));
  cout << sizeof(file) << endl;
  file.close();
}

void problem13(){
    ifstream ifs;
    ifs.open("STUDENT.DAT", ios::in | ios::out | ios::binary);

    Student cur;

    while(!ifs.eof()){
        ifs.read((char*) & cur, sizeof(cur));

        if(cur.ReturnPercentage() > 75){
            cur.DisplayData();
        }
    }
    ifs.close();
}

int Item::Countrec() //Problem 14
{
            fstream File;
            File.open("EMP.DAT", ios::binary | ios::in);
            File.seekg(0, ios::end); //Statement 1
            int Bytes = File.tellg(); //Statement 2
            int Count = Bytes / sizeof(Item);
            File.close();
            return Count;
}

class STUD{ //Problem 15
    int Rno;
    char Name[20];

    public:
        void Enter()
        {cin >> Rno; gets(Name); }

        void Display()
        {cout << Rno << Name << endl;}

        void Add(string name){
            std::ofstream of;
            of.open(name, std::ios_base::app);

            of<<Rno<<","<<Name<<endl;

            of.close();
        }
}

//Problem 16
 void Item::Search(int RecNo)
{
    fstream File;
    File.open( “STOCK.DAT”, ios::binary | ios::in);
    file.seekg(RecNo, ios::beg); //Statement 1
    File.read((char*)this, sizeof(Item));
    cout << Ino << ”==> ” << Item << endl;
    File.close();
}

void Item::Modify(int RecNo)
{
    fstream File;
    File.open( “STOCK.DAT”, ios::binary | ios::in | ios::out);
    cin>>Ino;
    cin.getline(Item, 20);
    File.seekp(RecNo, ios::beg); //Statement 2
    File.write( (char*)this, sizeof(Item));
    File.close();
}