package edu.pucmm.mt;

import com.sparkpost.Client;
import com.sparkpost.exception.SparkPostException;

public class Main {

    /**
     * Ejemplo de uso de correo transaccionales
     * https://github.com/SparkPost/java-sparkpost
     * @param args
     * @throws SparkPostException
     */
    public static void main(String[] args) throws SparkPostException {

        String API_KEY = "";
        Client client = new Client(API_KEY);

        client.sendMessage(
                "",
                "",
                "Probando",
                "Hola Mundo desde SparkPost",
                "<b>Hola Mundo desde SparkPost</b>");

    }
}
