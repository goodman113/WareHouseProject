package uz.app.service;

import static uz.app.utils.Utill.intScanner;

public class MainService {
    public void service(){
        while (true){
            System.out.println("""
                    0 exit
                    1 product management
                    2 shop management
                    3 company management
                    4 input output management
                    """);
            switch (intScanner.nextInt()){
                case 0->{}
                case 1->{}
                case 2->{}
                case 3->{}
                case 4->{}
            }
        }
    }
}
