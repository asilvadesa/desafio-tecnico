package br.com.sicredi.simulacao.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GeradorCpfDataFactory {
    public static String geradorCpf(){
        return pegaCpf();
    }

    private static String pegaCpf(){

     List<String> list = Arrays.asList(
             "72645646069",
             "03141457026",
             "93368148001",
             "38560716017",
             "76106054096"
             );
     Random random = new Random();
     return list.get(random.nextInt(list.size()));

    }
}
