package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class database {
    public static List lista = new ArrayList<String>();

    public static void set_answer(String number, String s) throws Exception {

        if (s.contains("SELECT") && s.contains("FROM") && s.contains("WHERE") && s.contains("ORDER BY")) {
            int tab[] = new int[4];
            String[] table = s.split("\\s+");
            for (int i = 0; i < table.length; i++) {
                if (table[i].contentEquals("SELECT")) {
                    tab[0] = i;
                }

                if (table[i].contentEquals("FROM")) {
                    tab[1] = i;
                }

                if (table[i].contentEquals("WHERE")) {
                    tab[2] = i;
                }

                if (table[i].contentEquals("ORDER") && table[i + 1].contentEquals("BY")) {
                    tab[3] = i;
                }
            }

            if (tab[0] < tab[1] && tab[1] < tab[2] && tab[2] < tab[3]) {
//                if (lista.size() > Integer.parseInt(number) - 1) {
//                    lista.remove(Integer.parseInt(number) - 1);
//                    lista.add(Integer.parseInt(number) - 1, s);
//                } else if (lista.size() == Integer.parseInt(number) - 1) {
//                    lista.add(s);
//                } else {
//                    int counter = lista.size();
//
//                    for (int j = 1; j <= (Integer.parseInt(number) - counter); j++) {
//                        lista.add("");
//                    }
//                    lista.remove(Integer.parseInt(number) - 1);
//                    lista.add(Integer.parseInt(number) - 1, s);
//                }

                if(lista.isEmpty())
                {
                    lista.add(number+"\n"+s);
                }
                else {
                    boolean v=false;
                    int k=lista.size()-1;
                    String nr[];
                    while (k >= 0 && v == false) {
                        nr=(lista.get(k)).toString().split("\n");
                        if (Integer.parseInt(number) > Integer.parseInt(nr[0])) {
                            lista.add(k + 1, number+"\n"+s);
                            v=true;
                        }
                        else if (Integer.parseInt(number) == Integer.parseInt(nr[0])) {
                            lista.remove(k);
                            lista.add(k, number+"\n"+s);
                            v=true;
                        }
                        k--;
                    }
                    if(v==false)
                    {
                        lista.add(0,number+"\n"+s);
                    }
                }

            } else {
                throw new Exception("Wpisano polecenie SQL w błędnej kolejności");
            }

        } else {
            throw new Exception("Brak polecenia SQL");
        }

    }


    public static void savetofile() {

        try {
            FileWriter save = new FileWriter("odp.txt");
            BufferedWriter bu = new BufferedWriter(save);
            for (int i = 0; i < lista.size(); i++) {
                //bu.newLine();
                save.write(lista.get(i) + "\n" );
            }
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void checktime() throws Exception {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);


        switch (hour) {
            case 8:
                if(minute>=15) System.out.println(45 + 60 - minute + "minut do końca zajęć");
                else throw new Exception("Uczelnia nie pracuje");
                break;
            case 9:
                if (minute < 45) System.out.println(45 - minute + " minut do końca zajęć");
                else System.out.println(60 - minute + " minut do końca przerwy");
                break;
            case 10:
                System.out.println(60 - minute + 30 + " minut do końca zajęć");
                break;
            case 11:
                if (minute < 30) System.out.println(30 - minute + " minut do końca zajęć");
                else if (minute >= 30 && minute < 45) System.out.println(45 - minute + " minut do końca przerwy");
                else System.out.println(60 - minute + 75 + " minut do końca zajęć");
                break;
            case 12:
                System.out.println(60 - minute + 15 + " minut do końca zajęć");
                break;
            case 13:
                if (minute < 15) System.out.println(15 - minute + " minut do końca zajęć");
                else if (minute >= 15 && minute < 45) System.out.println(45 - minute + " minut do końca przerwy");
                else System.out.println(60 - minute + 75 + " minut do końca zajęć");
                break;
            case 14:
                System.out.println(60 - minute + 15 + " minut do końca zajęć");
                break;
            case 15:
                if (minute < 15) System.out.println(15 - minute + " minut do końca zajęć");
                else if (minute >= 15 && minute < 30) System.out.println(30 - minute + " minut do końca przerwy");
                else System.out.println(60 - minute + 60 + " minut do końca zajęć");
                break;
            case 16:
                System.out.println(60 - minute + " minut do końca zajęć");
                break;
            case 17:
                if (minute < 15) System.out.println(15 - minute + " minut do końca przerwy");
                else System.out.println(60 - minute + 45 + " minut do końca zajęć");
                break;
            case 18:
                if(minute<45)System.out.println(45 - minute + " minut do końca zajęć");
                else throw new Exception("Uczelnia nie pracuje");
                break;
            default:
                throw new Exception("Uczelnia nie pracuje");

        }
    }
}










