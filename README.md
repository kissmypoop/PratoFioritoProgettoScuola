[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8bacc2714a1c458ab26503f7aa763ebf)](https://www.codacy.com/app/AlecsFerra/PratoFioritoProgettoScuola?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AlecsFerra/PratoFioritoProgettoScuola&amp;utm_campaign=Badge_Grade) [![CodeFactor](https://www.codefactor.io/repository/github/alecsferra/pratofioritoprogettoscuola/badge)](https://www.codefactor.io/repository/github/alecsferra/pratofioritoprogettoscuola)

# SCANSA LA MINA
Gioco online che utilizza socket per lo scambio di dati tra client e server.

## Consegna
Realizzare una Java App implementata tramite socket e thread per far giocare un utente a “Campo Minato”. 
Il Server distribuisce su una scacchiera di dimensioni m x n un numero di mine rappresentate da un carattere e nelle restanti celle viene raffigurato il terreno non minato tramite un carattere differente. 
Una volta distribuite le mine sulla scacchiera parte il gioco: il giocatore (client) indica su quale cella vuole andare; una volta identificata dal server, questo comunica al client se si trattava di terreno minato o meno.  
La mina a questo punto potrebbe esplodere (utilizzare un meccanismo random) ed il gioco terminare, oppure non esplodere ed il gioco prosegue. Sulla scacchiera vengono distribuite un numero random di mine, compreso fra 1/5 e 4/5 delle celle totali (se la scacchiera è 5x5 avrà 25 celle totali, sulla quale vengono distribuite casualmente fra le 5 e le 20 mine).

Specifica speciale del gruppo:
 - Devono essere costruite 2 scacchiere. Il client invia il numero di mine da distribuire sul campo nelle due scacchiere (controllare che il numero sia compreso tra 1/5 e 4/5), il server organizza le scacchiere e quindi, dopo aver contato il numero di mine distribuite sia su uno che sull’altro campo, chiede al giocatore se vuole giocare sulla scacchiera con più mine o su quella con meno

## Descrizione del progetto
### Suddivisione
Il progetto si suddivide in 2 app principali:
 - [Client](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/tree/master/client/src/it/gruppoa/clientmina)
 - [Server](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/tree/master/server/src/it/gruppoa/servermina)

La filosofia alla base di tutto il progetto è stata rendere il client più semplice e "stupido" possibile e si occuprà solo di leggere ed inviare stringhe al server.
Per i seguenti motivi:
 - Facilità di aggiornamento dell' app che richiederà solo modifiche sull' app server
 - Sicurezza: Rendere impossibile il barare nel gioco tramite manipolazione del client

### Il Server
#### Classi
Diagramma uml delle classi:
<p align="center">
    <img src="https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/assets/UMLServer.PNG?raw=true"/>
</p>

Il packag model contiene tutto ciò che riguarda la business logic dell' applicazione e le classi di utility.
Nel package principale si trovano il Main usato solo per accettare i client e GameTask usato per interagire con il client.

#### Utilizzo dei Thread
I thread sono stati usati all' interno della classe Table rispettivamenti nei metodi [isAnyFlowerLeft](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/7b6dbd2724049ac2dbab0b7db905308eabeb47ab/server/src/it/gruppoa/servermina/model/Table.java#L109) e [numberOfBombsLeft](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/7b6dbd2724049ac2dbab0b7db905308eabeb47ab/server/src/it/gruppoa/servermina/model/Table.java#L97) per velocizzare la ricerca.
Esempio:
```java
IntStream.range(0, 10)
         .parallel() //Rende l'esecuzione parallela
         .forEach(System.out::println);
        /*
         OUTPUT
         6
         5
         2
         8
         4
         9
         3
         7
         1
         0
        */
```
Infine i Thread sono anche stati usati per rendere l' applicazione utilizzabile da più client sullo stesso server facendo estendere ad [GameTask](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/c51b393ca0a58af7df9feee7bcd1f4be757419b4/server/src/it/gruppoa/servermina/GameTask.java#L19), che si occupa di interagire con il client, la classe Thread.
Esempio di accettazione (Preso dal [Main](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/Main.java)):
```java
try(ServerSocket server = new ServerSocket(numeroPorta)){
            
    while(true){
                
        new GameTask(server.accept()).start();
                
    }
            
} catch (IOException ex) {
    //Gestione Errori
}  
```
Per ultriori informazioni sull' interazione delle varie classi consultare le [javadocs](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/dist/javadoc/).

### Il Client
Come già spiegato in precedenza è stata usata la filosofia dl rendere il client più stupido possibile.
Comunque anche per questa app sono dispnibli le [javadocs](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/client/dist/javadoc/).

#### Scelte di progettazione
La probabilità di esplosione delle [bombe](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/c51b393ca0a58af7df9feee7bcd1f4be757419b4/server/src/it/gruppoa/servermina/model/Cell.java#L42) è stata impostata al 66%.
```java
return WorldRandom.getBoolFromPerc(66)
        ? CellDiscoverResult.BOMB_EXPLODED
        : CellDiscoverResult.BOMB_NOT_EXPLODED;
```
Quando verràproposto all'utente quale tabella scegliere non verrà mostrato "Tabella più facile" o "Tabella più difficile" perè non determinable in caso il numero di bombe sia uguale su entrambe le tabelle.
```console
Quale tabella vuoi a[50] o b[50]?
```
Come già spiegato prima il server permette a più host di giocare contemporaneamente.

## Come utilizzare il gioco
Passo 1: Scaricare i jar dalla sezione [release](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/releases/tag/Official).
Passo 2: Lanciare il server.
```console
user@machine:~$ java -jar ServerMina.jar 1555
```
nb: Ovviamente sostituisci la porta con quella che vuoi usare non sei obbligato ad usare la stessa.
Passo 3: Lanciare il client.
```console
user@machine:~$ java -jar ClientMina.jar 127.0.0.1 1555
```
nb: Inserisci l'indirizzo ip della macchina su cui è hostato il server e mi raccomando non sbagliare porta!
## Suddivisione del lavoro
| Membro               | Compiti svolti                                                                                                                                                                                                    |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alessio Ferrarini    | <ul> <li>Progettazione logica dell'applicativo</li> <li>Coding dell' applicativo</li> <li>Gestione connessione</li> <li>Documentazione progetto</li> <li>Crazione JavaDocs</li> <li>Crazione relazione</li> </ul> |
| Michele Dalle Vedove | <ul> <li>Non saper creare un metodo</li> </ul>                                                                                                                                                                    |
| Riccardo Gini        | <ul> <li>stare a casa</li> </ul>                                                                                                                                                                                  |
