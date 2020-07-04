package edu.pucmm.mt;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static j2html.TagCreator.*;

public class Main {

    /**
     * Ejemplo de uso de correo transaccionales.
     * Pueden usar: https://generator.email/ o https://emailfake.com/ para email falsos.
     */
    public static void main(String[] args) throws IOException {
        //Configurando el servidor.
        Mailer mailer = MailerBuilder
                .withSMTPServer("host.correo", 587, "", "")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();

        //Cargando la información
        byte[] imagen = Main.class.getResourceAsStream("/logoPucmm.png").readAllBytes();
        byte[] anexo = Main.class.getResourceAsStream("/isc1b.pdf").readAllBytes();

        //Creando mensaje HTML.
        String html = body(
                h1("ISC-517 - Programación Web Avanzada"),
                h2("Hola Mundo Mensaje Transaccional"),
                img().withSrc("cid:logo")
        ).render();

        //Configurando el Correo para ser enviado.
        //https://generator.email/qkamal@alpicley.gq
        Email email = EmailBuilder.startingBlank()
                .from("noreply@micorre.com")
                .to("Otra Dirección", "qkamal@alpicley.gq")
                .to("Otra Dirección Random", "dissuadaient@voicememe.com")
                .withReplyTo("Soporte", "soporte@micorre.com")
                .withSubject("Mensaje de Correo Transaccional - Usando Simple Java Mail - "+ new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date()))
                .withHTMLText(html)
                .withPlainText("No visualiza la información en formato html")
                .withEmbeddedImage("logo", imagen, "image/png")
                .withAttachment("Programa ISC", anexo, "application/pdf")
                .withReturnReceiptTo()
                .withBounceTo("bounce@micorre.com")
                .buildEmail();

        //Enviando el mensaje:
        mailer.sendMail(email);

    }
}
