package trabajoinventarioletras;

import java.util.Scanner;

public class TrabajoInventarioLetras {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); //input para recibir
        Scanner input2 = new Scanner(System.in); //segundo input creado, esto debido a que en la linea 61 me imprimia otras cosas usando el input1

        InventarioLetras Inventarios[] = new InventarioLetras[80];
        /*Aca se crea un array de 80 espacios de objetos de la clase InventarioLetras,
        *nota* Son 80 arrays creados para completar, estos al momento de crearse son null(que los atributos que necesitan deben darselos)*/

        int invs = 0;
        /*invs son el contador de inventarios que han sido creado totalmente, esto es para que al momento de crear un inventario se
        guarde en el primer contenedor no ocupado del array Inventarios[]  */

        int aux = 1; //Esto simplemente es un auxiliar para el do while a continuacion, si es 1 se volvera a repetir, pero si es 0, saldra.
        do {
            System.out.println("--------------------------Menú--------------------------");
            System.out.println("Ingrese 1 para crear un inventario");
            System.out.println("Ingrese 2 para desencriptar un inventario");
            System.out.println("Ingrese 3 para saber la cantidad de una letra que hay en un inventario");
            System.out.println("Ingrese 4 para darle un valor fijo a una letra de un inventario");
            System.out.println("Ingrese 5 para ver el total de recuentos de un inventario");
            System.out.println("Ingrese 6 para saber si un inventario esta vacio ");
            System.out.println("Ingrese 7 para imprimir las letras del inventario");
            System.out.println("Ingrese 8 para sumar 2 inventarios");
            System.out.println("Ingrese 9 para amplificar un inventario");
            System.out.println("Ingrese 10 para restar 2 inventarios");
            System.out.println("Ingrese 0 para salir");
            System.out.println("--------------------------------------------------------");
            int num = 0; // variable num para guardar el numero que eliga el usuario
            int auxi = 0; //variable auxiliar para que al momento de elegir una opcion, se vuelva a repetir el try catch

            int auxM = 0; //variable auxiliar para recibir el numero del menu, si es 1 saldra para el while a continuacion
            while (auxM == 0) {
                System.out.println("Ingrese un numero: ");
                String numtext = input.next();
                if (!EsNumero(numtext)) {
                    System.out.println("numero invalido, porfavor escoga del 0 al 10");
                } else if (Integer.parseInt(numtext) < 0 || Integer.parseInt(numtext) > 10) {
                    System.out.println("numero invalido, porfavor escoga del 0 al 10");
                } else {
                    num = Integer.parseInt(numtext);
                    auxM = 1;
                }

            }

            if (num == 0) {
                aux = 0; //con esto podra salir del while del menu.
                System.out.println("Ha salido del menu.");

            } else if (num == 1) {
                System.out.println("Crear un inventario");
                String palabra = ""; // variable donde se guardara la palabra ingreasda por el usuario

                while (auxi == 0) {
                    try {
                        System.out.println("Inrese palabra a encriptar: ");
                        palabra = input2.nextLine();
                        String[] palabras = palabra.split("(?!^)");
                        /*aca la plabra del usuario se desglosa por letras, si fuera play 
                        se convierte en un array de string de cada letra, osea quedaria [p,l,a,y]*/

                        for (String e : palabras) { // Recorre cada string que hay en el array de string palabras, llamandose el string tomado "e"
                            if (Character.isDigit(e.charAt(0))) {
                                /* char at convierte en caracter la primera letra de un string, y character is digit
                                es un metodo de java que comprueba si un caracter es numero*/
                                throw new IllegalArgumentException("Ingrese una letra del alfabeto valida"); //es un numero,manda una excepcion q la captura el catch
                            } else if (e.equals(" ")) {
                                /*equals es un metodo para comprar strings es mejor que string==string ya que este no suele
                                funcionar, en cambio este si, la idea es que si el elemento tomado es un espacio, si es asi no se hace nada*/

                            } else if (!Character.isAlphabetic(e.charAt(0))) {
                                /* char at convierte en caracter la primera letra de un string, y character is
                                alphabetic es un metodo de java que comprueba si el elemento es una letra del alfabeto ingles(sin ñ) esto es por caba en el
                                ultimo else if, ya que si hubiera sido un espacio , hubiera mandado false es por eso del segundo else if para que entre ahi
                                y no aqui*/
                                throw new IllegalArgumentException("Ingrese una letra del alfabeto valida");  //es la ñ o acento, manda excepcion
                            }
                        }
                        auxi = 1; //permite salir del while del try
                    } catch (Exception e) {
                        e.printStackTrace(); // imprime el error de illegalargumentexception
                    }
                }

                Inventarios[invs] = new InventarioLetras(palabra); // crea un inventarioletras con el string ingresado por el usuario
                invs++; //aumenta unp el invs, esto recuerda que es para que al momento de crear un inventario, se guarde en el primer espacio del array disponible
            } else if (num == 2) {
                if (invs > 0) {
                    ImprimirInventariosEncriptados(Inventarios);
                    System.out.println("Ingrese el numero del que desea desencriptar");
                    int numfinal = VerificarNumero(invs);

                    System.out.println(Inventarios[numfinal - 1].desencriptarString(Inventarios[numfinal - 1].getPalabracod()));

                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            } else if (num == 3) {

                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el numero de inventario que quiera saber el recuento de una letra");
                    int numfinal = VerificarNumero(invs);
                    System.out.println("Ingrese la letra que quiera saber sus recuentos");
                    String letra=VerificarLetra();
                     int rec = Inventarios[numfinal - 1].get(letra.toLowerCase().charAt(0));
                     System.out.println("Hay un total de " + rec + " de letra " + letra);
                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            } else if (num == 4) {
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el inventario que quiera darle a una letra un valor fijo");
                    int numfinal = VerificarNumero(invs);
                    System.out.println("Ingrese la letra que quiera fijar un valor");
                    String letra = VerificarLetra();
                    System.out.println("Ingrese el valor a fijar");
                    while (auxi == 0) {
                        String ind = input.next();
                        if (!EsNumero(ind)) {
                            System.out.println("Ingrese un numero");
                        } else {
                            int numvalor = Integer.parseInt(ind);
                            Inventarios[numfinal - 1].set(letra.charAt(0), numvalor);
                            auxi = 1;
                        }
                    }
                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            } else if (num == 5) {
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el numero del inventario que quiera ver los recuentos");
                    int numfinal = VerificarNumero(invs);
                    System.out.println("Hay un recuento de: " + Integer.toString(Inventarios[numfinal - 1].getRecuentos()) + " letras");

                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }

            } else if (num == 6) {
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el numero del inventario que quiere sabe si esta vacio");
                    int numfinal = VerificarNumero(invs);
                    if (Inventarios[numfinal - 1].isEmpty()) {
                        System.out.println("El inventario esta vacio");
                    } else {
                        System.out.println("El inventario contiene letras");
                    }
                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }

            } else if (num == 7) {
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el numero del inventario que quieras imprimir");
                    int numfinal = VerificarNumero(invs);
                    System.out.println(Inventarios[numfinal - 1].toString());

                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            } else if (num == 8) {
                int ind = 0;
                int ind2 = 0;
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    while (auxi == 0) {
                        System.out.println("Ingrese el numero del inventario que quiera sumarle otro");
                        String numtext = input.next();
                        System.out.println("Ingrese el otro inventario");
                        String numtext2 = input.next();
                        if (!EsNumero(numtext) || !EsNumero(numtext2)) {
                            System.out.println("Ingrese nuevamente los numeros");
                        } else if ((Integer.parseInt(numtext) > invs || Integer.parseInt(numtext) < 0) || (Integer.parseInt(numtext2) > invs || Integer.parseInt(numtext2) < 0)) {
                            System.out.println("numeros de inventario invalidos, intentelo nuevamente");
                        } else {
                            ind = Integer.parseInt(numtext);
                            ind2 = Integer.parseInt(numtext2);
                            auxi = 1;
                        }
                    }

                    Inventarios[invs] = Inventarios[ind - 1].add(Inventarios[ind2 - 1]);
                    invs++;

                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            } else if (num == 9) {
                int ind = 0;
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    System.out.println("Ingrese el numero del inventario que quiera amplificar");
                    int numfinal = VerificarNumero(invs);
                    System.out.println("Ingrese cuanto lo quiere multiplicar");
                    while (auxi == 0) {
                        String numtext = input.next();
                        if (!EsNumero(numtext)) {
                            System.out.println("Ingrese nuevamente el numero");
                        } else {
                            ind = Integer.parseInt(numtext);;
                            auxi = 1;
                        }
                    }
                    Inventarios[invs] = Inventarios[numfinal - 1].amplifies(ind);
                    invs++;
                }
            } else if (num == 10) {
                int ind = 0;
                int ind2 = 0;
                if (invs > 0) {
                    ImprimirInventarios(Inventarios);
                    while (auxi == 0) {
                        System.out.println("Ingrese el numero del inventario que quiera restarle otro");
                        String numtext = input.next();
                        System.out.println("Ingrese el otro inventario");
                        String numtext2 = input.next();
                        if (!EsNumero(numtext) || !EsNumero(numtext2)) {
                            System.out.println("Ingrese nuevamente los numeros");
                        } else if ((Integer.parseInt(numtext) > invs || Integer.parseInt(numtext) < 0) || (Integer.parseInt(numtext2) > invs || Integer.parseInt(numtext2) < 0)) {
                            System.out.println("numeros de inventario invalido, intentelo nuevamente");
                        } else {
                            ind = Integer.parseInt(numtext);
                            ind2 = Integer.parseInt(numtext2);
                            auxi = 1;
                        }
                    }
                    if(Inventarios[ind - 1].subtract(Inventarios[ind2 - 1])==null){
                        System.out.println("El recuento de alguna letra ha termiando en negativo, asi que el inventario quedo null y no se creo");
                    
                    }else{
                        Inventarios[invs]=Inventarios[ind - 1].subtract(Inventarios[ind2 - 1]);
                        invs++;
                    }
                 
                } else {
                    System.out.println("No hay ningun inventario creado, porfavor cree uno. ");
                }
            }
        } while (aux == 1);

    }

    public static boolean EsNumero(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static void ImprimirInventariosEncriptados(InventarioLetras[] Invs) {
        int m = 0;
        for (InventarioLetras G : Invs) {
            if (G != null && G.size() > 0) {
                System.out.println("Inventario " + Integer.toString(m + 1) + ": ref " + G.getPalabracod());

            }
            m++;
        }
    }

    static void ImprimirInventarios(InventarioLetras[] Invs) {
        int m = 0;
        for (InventarioLetras G : Invs) {
            if (G != null) {
                System.out.println("Inventario " + Integer.toString(m + 1) + ": ref " + G.getData());

            }
            m++;
        }
    }

    public static int VerificarNumero(int contadorinvs) {
        Scanner input = new Scanner(System.in);
        int auxn = 0;
        int numinput = 0;
        while (auxn == 0) {
            String ind = input.next();
            if (!EsNumero(ind)) {
                System.out.println("Ingrese un numero");
            } else if (Integer.parseInt(ind) > contadorinvs || Integer.parseInt(ind) < 1) {
                System.out.println("numero de inventario invalido, intentelo nuevamente");
            } else {
                numinput = Integer.parseInt(ind);
                auxn = 1;
            }
        }
        return numinput;

    }

    public static String VerificarLetra() {
        Scanner input = new Scanner(System.in);
        int auxi = 0;
        String letra = "";
        while (auxi == 0) {
            try {
                letra = input.next().toLowerCase();
                if (letra.length() > 1) {
                    System.out.println("Ingreso más de un caracter, vuelva a intentarlo");

                } else if (Character.isDigit(letra.charAt(0))) {
                    throw new IllegalArgumentException("Ingrese una letra del alfabeto valida");
                } else if (!Character.isAlphabetic(letra.charAt(0))) {
                    throw new IllegalArgumentException("Ingrese una letra del alfabeto valida");
                }
                auxi = 1;
                return letra;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return letra;
    }

}
