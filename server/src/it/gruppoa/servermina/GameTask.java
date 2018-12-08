/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina;

import it.gruppoa.servermina.model.Game;
import it.gruppoa.servermina.model.GameState;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author st13299
 */
public class GameTask extends Thread {

    private final Socket conn;
    private static final String messageTerminator = "\n\0\n";

    public GameTask(Socket conn) {
        this.conn = conn;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void run() {

        System.out.println("Iniziata connessione a: " + this.conn);

        try (
                Scanner sin = new Scanner(conn.getInputStream());
                PrintWriter sout = new PrintWriter(conn.getOutputStream(), true)) {

            sout.println("Benvenuto a 'SCANSA LA MINA'");
            sout.println("Inserisci le dimensioni della tabella! [x y]");
            sout.println(messageTerminator);

            boolean error = false;
            Game game = null;
            do {

                error = false;

                try {

                    game = new Game(sin.nextInt(), sin.nextInt());

                } catch (IllegalArgumentException e) {

                    sout.println("Errore: " + e);
                    sout.println(messageTerminator);
                    error = true;

                } catch (InputMismatchException e) {

                    sout.println("Errore: formato di input non valido");
                    sout.println(messageTerminator);
                    error = true;

                } finally {
                    Util.clearBuffer(sin);
                }

            } while (error);

            System.out.println("Pre game loop");

            while (game.getGameState() == GameState.RUNNING) {

                System.out.println("Game running");
                sout.println(game);
                sout.println("Inserisci la tua prossima mossa  [x y]");
                sout.println(messageTerminator);

                do {

                    error = false;

                    try {

                        sout.println(game.discover(sin.nextInt(), sin.nextInt()) + "\n");

                    } catch (IllegalArgumentException e) {

                        sout.println("Errore: " + e);
                        sout.println(messageTerminator);
                        error = true;

                    } catch (InputMismatchException e) {

                        sout.println("Errore: formato di input non valido");
                        sout.println(messageTerminator);
                        error = true;

                    } finally {
                        Util.clearBuffer(sin);
                    }

                } while (error);

            }

            sout.println("Fine partita: " + game.getGameState().getMessage() + "\n");
            sout.println(game);
            sout.println(messageTerminator);

            conn.close();

        } catch (IOException e) {

            System.err.println("Errore a: " + this.conn.toString() + " -> " + e);

        }

        System.out.println("Terminata connessione a: " + this.conn);

    }

}
