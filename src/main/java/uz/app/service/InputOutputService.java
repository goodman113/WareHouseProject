package uz.app.service;

import static uz.app.utils.Utill.*;

public class InputOutputService {
    public void service(){
        while (true){
            System.out.println("""
                    0 exit
                    1 input
                    2 output
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
