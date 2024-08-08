package trabajoinventarioletras;

import java.util.Arrays;
import java.util.List;

public class InventarioLetras {

    private String data; 
    private String abcedario = "abcdefghijklmnopqrstuvwxyz"; //est
    private String palabracod;
    private int[] recuentosLetras = new int[26];
    private int numero = 3;
    private int recuentos = 0;

    public InventarioLetras(String data) {
        this.data = data;
        palabracod = encriptarString(data);
        recuentos = data.length();
    }

    public String getPalabracod() {
        if (this.palabracod.length() == this.recuentos) {
            return palabracod;
        } else {
            return getPalabracod2();
        }

    }

    public String getPalabracod2() {
        String letras = "";
        for (int i = 0; i < abcedario.length(); i++) {
            for (int j = 0; j < this.palabracod.length(); j++) {
                if (Character.toString(abcedario.charAt(i)).equals(Character.toString(palabracod.charAt(j)))) {
                    letras = letras + Character.toString(palabracod.charAt(j));
                }

            }
        }
        return letras;
    }

    public String encriptarString(String palabra) {
        String palabracodificada = "";
        String[] arrPal = palabra.split("(?!^)");
        for (String letra : arrPal) {
            char letraC = letra.toLowerCase().charAt(0);
            char letraE = encriptarCesar(letraC);
            encriptarPalabra(letraC, 1);
            palabracodificada = palabracodificada + letraE;
        }

        /* for(int i=0;i<palabra.length();i++){
     char letras=palabra.toLowerCase().charAt(i);
     palabracodificada=palabracodificada+encriptarCesar(letras);
     }
     String[] arrPalCod=palabracodificada.split("(?!^)");
     for(String letra:arrPalCod){
            System.out.println(letra);
        }
     return palabracodificada;*/
        return palabracodificada;
    }

    public String desencriptarString(String palabra) {
        String palabraDescodificada = "";
        String[] arrPal = palabra.split("(?!^)");
        for (String letra : arrPal) {
            char letraC = letra.toLowerCase().charAt(0);
            char letraE = desencriptarCesar(letraC);
            desencriptarPalabra(letraC, 1);
            palabraDescodificada = palabraDescodificada + letraE;
        }
        return palabraDescodificada;

    }

    //Metodo para encriptar la letra
    public char encriptarCesar(char letra) {
        int pos = abcedario.indexOf(letra); 
        if (pos == -1) {
            return letra;
        } else {
            return abcedario.charAt((pos + numero) % abcedario.length());
        }
    }

    //Metodo para desencriptar la letra
    public char desencriptarCesar(char letra) {
        int pos = abcedario.indexOf(Character.toString(letra));
        if (pos == -1) {
            return letra;
        } else {
            if (pos - numero < 0) {
                return abcedario.charAt(abcedario.length() + (pos - 3));
            } else {
                return abcedario.charAt((pos - numero) % abcedario.length());
            }
        }
    }

    public void encriptarPalabra(char letra, int valor) {
        for (int i = 0; i < abcedario.length(); i++) {
            char l = abcedario.charAt(i);
            if (Character.toString(letra).toLowerCase().equals(Character.toString(l))) {
                this.recuentosLetras[i] = recuentosLetras[i] + valor;
            }
        }

    }

    public void desencriptarPalabra(char letra, int valor) {
        for (int i = 0; i < abcedario.length(); i++) {
            char l = abcedario.charAt(i);

            if (Character.toString(letra).toLowerCase().equals(Character.toString(l))) {
                this.recuentosLetras[i] -= valor;
            }
        }

    }

    //Metodo que devuelve cuantos recuentos hay en el invetario
    public int size() {
        return recuentos;
    }

    //Metodo que devuelve true si los recuentos son 0 y falso si es al contrario.
    public boolean isEmpty() {
        ActualizarRecuentos();
        if (recuentos == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int get(char letra) {
        int aux = 0;
        for (int i = 0; i < abcedario.length(); i++) {
            char l = abcedario.charAt(i);
            if (Character.toString(letra).toLowerCase().equals(Character.toString(l))) {
                aux = recuentosLetras[i];
                return aux;
            }
        }
        return aux;
    }

    public void set(char letra, int valor) {
        for (int i = 0; i < abcedario.length(); i++) {
            char l = abcedario.charAt(i);
            if (Character.toString(letra).toLowerCase().equals(Character.toString(l))) {
                this.recuentosLetras[i] = valor;
                ActualizarRecuentos();
            }
        }
    }

    public int getRecuentos() {
        return recuentos;
    }

    public void ActualizarRecuentos() {
        int sum = 0;
        for (int i = 0; i < abcedario.length(); i++) {
            sum = sum + recuentosLetras[i];
            this.recuentos = sum;
        }

    }

    public String getData() {
        return data;
    }

    public InventarioLetras add(InventarioLetras otro) {
        InventarioLetras Ninv = new InventarioLetras(this.data + otro.data);
        for (int i = 0; i < abcedario.length(); i++) {
            Ninv.recuentosLetras[i] = this.recuentosLetras[i] + otro.recuentosLetras[i];
        }
        Ninv.ActualizarRecuentos();
        return Ninv;
    }

    public InventarioLetras amplifies(int n) {
        String dataAux = this.data;
        String datafinal = dataAux;
        for (int i = 1; i < n; i++) {            
            datafinal = datafinal + dataAux;
        }
        InventarioLetras Ninv = new InventarioLetras(datafinal);
        return Ninv;
    }

    public InventarioLetras subtract(InventarioLetras otro) {
        String textfinal = "";
        String a = this.data;
        for (int i = 0; i < abcedario.length(); i++) {
            int total = 0;
            total = recuentosLetras[i] - otro.recuentosLetras[i];
            if (total < 0) {
                InventarioLetras invnull = null;
                return invnull;                         
            }else if(total==0){
                InventarioLetras Ninv = new InventarioLetras(" ");
                return Ninv;            
            
            }
            for (int j = 0; j < this.recuentos; j++) {
                for (int z = 0; z < total; z++) {
                    if (Character.toString(a.charAt(j)).equals(Character.toString(abcedario.charAt(i)))) {
                        textfinal =Character.toString(a.charAt(j))+textfinal;
                    }
                }
            }
        }
        InventarioLetras Ninv = new InventarioLetras(textfinal);
        return Ninv;
    }

    @Override
    public String toString() {
        String invLetras = "";
        for (int i = 0; i < abcedario.length(); i++) {
            if (recuentosLetras[i] != 0) {
                for (int j = 0; j < recuentosLetras[i]; j++) {
                    invLetras = invLetras + Character.toString(abcedario.charAt(i));
                }
            }
        }
        return "[" + invLetras + "]";
    }

}
