/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author dev
 */
public class FormataData {
    
    
    public String dataFormatada(Date data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String d = formatador.format(data);
   
        return d;
    }
    //sobrecarga de metodo
    public String dataFormatada(Calendar data){
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String d = formatador.format(data);
   
        return d;
    }
        
    public String dataAtual(){
        Date cal = Calendar.getInstance().getTime();    
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        String data = formatador.format(cal);
        return data;
    }
    
      public String horaAtual(){
        Date cal = Calendar.getInstance().getTime();    
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
        String hora = formatador.format(cal);
        return hora;
    }

 
    
}
