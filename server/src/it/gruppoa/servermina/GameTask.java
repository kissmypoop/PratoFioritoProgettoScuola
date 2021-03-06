package it.gruppoa.servermina;

import it.gruppoa.servermina.model.util.Util;
import it.gruppoa.servermina.model.Game;
import it.gruppoa.servermina.model.GameState;
import it.gruppoa.servermina.model.Table;
import it.gruppoa.servermina.model.TableFactory;
import it.gruppoa.servermina.model.util.Pair;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe che fa da ponte tra il client e una istanza del gioco
 * @author alecsferra
 */
public class GameTask extends Thread {

    //Connessione con il client
    private final Socket conn;
    //Tringa di terminazione di messaggio, usata per facilitare la lettura di tutto il buffer su il client
    private static final String MESSAGE_TERMINATOR = "\0";

    /**
     * Crea un istanza della classe GameStask
     * @param conn connessione con il client
     */
    public GameTask(Socket conn) {
        this.conn = conn;
    }

    /**
     * Flusso di gioco
     */
    @SuppressWarnings("ConstantConditions")
    @Override
    public void run() {

        System.out.println("Iniziata connessione a: " + this.conn);

        try (
                Scanner sin = new Scanner(conn.getInputStream());
                PrintWriter sout = new PrintWriter(conn.getOutputStream(), true)) {

            sout.println("Benvenuto a 'SCANSA LA MINA'");
            sout.println("Inserisci le dimensioni della tabella e il numero di bombe (1/5 < r < 4/5) di comparsa delle bombe! [x y n]");
            sout.println(MESSAGE_TERMINATOR);

            boolean error;
            Pair<Table, Table> tables = null;
            do {

                error = false;

                try {

                    tables = TableFactory.createTwinTables(sin.nextInt(), sin.nextInt(), sin.nextInt());

                } catch (IllegalArgumentException e) {

                    sout.println("Errore: " + e.getMessage());
                    sout.println(MESSAGE_TERMINATOR);
                    error = true;

                } catch (InputMismatchException e) {

                    sout.println("Errore: formato di input non valido");
                    sout.println(MESSAGE_TERMINATOR);
                    error = true;

                } finally {
                    Util.clearBuffer(sin);
                }

            } while (error);

            sout.println("Quale tabella vuoi? a[" + tables.getLeft().numberOfBombsLeft() + "] o b[" + tables.getRight().numberOfBombsLeft() + "]");
            sout.println(MESSAGE_TERMINATOR);

            Game game = null;
            do {

                error = false;

                try {

                    String msg = sin.next();

                    if ("a".equals(msg)) {
                        game = new Game(tables.getLeft());
                    } else if ("b".equals(msg)) {
                        game = new Game(tables.getRight());
                    } else {
                        error = true;
                        sout.println("Errore: Input non valido");
                        sout.println(MESSAGE_TERMINATOR);
                    }

                } catch (IllegalArgumentException e) {

                    sout.println("Errore: " + e.getMessage());
                    sout.println(MESSAGE_TERMINATOR);
                    error = true;

                } catch (InputMismatchException e) {

                    sout.println("Errore: formato di input non valido");
                    sout.println(MESSAGE_TERMINATOR);
                    error = true;

                } finally {
                    Util.clearBuffer(sin);
                }

            } while (error);

            while (game.getGameState() == GameState.RUNNING) {
                
                sout.println(game);
                sout.println("Inserisci la tua prossima mossa  [x y]");
                sout.println(MESSAGE_TERMINATOR);

                do {

                    error = false;

                    try {

                        sout.println(game.discover(sin.nextInt(), sin.nextInt()));

                    } catch (IllegalArgumentException e) {

                        sout.println("Errore: " + e.getMessage());
                        sout.println(MESSAGE_TERMINATOR);
                        error = true;

                    } catch (InputMismatchException e) {

                        sout.println("Errore: formato di input non valido");
                        sout.println(MESSAGE_TERMINATOR);
                        error = true;

                    } finally {
                        Util.clearBuffer(sin);
                    }

                } while (error);

            }

            sout.println("Fine partita: " + game.getGameState().getMessage() + "\n");
            sout.println(game);
            sout.println(MESSAGE_TERMINATOR);

            conn.close();

        } catch (IOException e) {

            System.err.println("Errore a: " + this.conn.toString() + " -> " + e);

        }

        System.out.println("Terminata connessione a: " + this.conn);

    }

}