package by;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InetAddress adress = InetAddress.getLocalHost();
        System.out.println(adress);
        URL hp = new URL("https://google.com");
        HttpURLConnection ru =(HttpURLConnection)hp.openConnection();
        System.out.println(ru.getRequestMethod());
        System.out.println(ru.getResponseCode());
        System.out.println(ru.getResponseMessage());

        Map<String, List<String>> drMap = ru.getHeaderFields();
        Set<String> field = drMap.keySet();
        for (String f: field){
            System.out.println("ключ: "+ f+" значение: "+drMap.get(f));
        }
        int c;
        System.out.println("=====Содержимое======");
        InputStream in =ru.getInputStream();
        while ((c=in.read())!=-1){
            System.out.print((char) c);
        }
        in.close();

        List<Integer>vals = new ArrayList<>();
        vals.add(1);
        vals.add(2);
        vals.add(3);
        vals.add(4);
        vals.add(5);
        System.out.print("содержимое листа"+": ");
        for (int i : vals){
            System.out.print(i+" ");
        }
        System.out.println();
        int sum=0;
        for (int v: vals){
            sum+=v;
        }
        System.out.print("сумма чисел: "+sum);
    }
}