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

            boolean error = false;
            Game game = null;
            do {

                error = false;

                try {

                    game = new Game(sin.nextInt(), sin.nextInt());

                } catch (IllegalArgumentException | InputMismatchException e) {

                    Util.clearBuffer(sin);
                    sout.println("Errore: " + e);
                    error = true;

                }

            } while (error);

            while (game.getGameState() == GameState.RUNNING) {

                sout.println(game);
                sout.println("Inserisci la tua prossima mossa  [x y]");

                do {

                    error = false;

                    try {

                        game.discover(sin.nextInt(), sin.nextInt());

                    } catch (IllegalArgumentException | InputMismatchException e) {

                        Util.clearBuffer(sin);
                        sout.println("Errore: " + e);
                        error = true;

                    }

                } while (error);

            }

            sout.println(game);
            sout.println(game.getGameState().getMessage());

        } catch (IOException e) {

            System.err.println("Errore a: " + this.conn.toString() + " -> " + e);

        }

        System.out.println("Terminata connessione a: " + this.conn);

    }

}
