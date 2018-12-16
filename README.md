[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8bacc2714a1c458ab26503f7aa763ebf)](https://www.codacy.com/app/AlecsFerra/PratoFioritoProgettoScuola?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=AlecsFerra/PratoFioritoProgettoScuola&amp;utm_campaign=Badge_Grade) [![CodeFactor](https://www.codefactor.io/repository/github/alecsferra/pratofioritoprogettoscuola/badge)](https://www.codefactor.io/repository/github/alecsferra/pratofioritoprogettoscuola)

# SCANSA LA MINA
Gioco online che utilizza socket per lo scambio di dati tra client e server.

Gruppo 4: Ferrarini Alessio, Riccardo Gini e Michele dalle Vedove

## Consegna
Realizzare una Java App implementata tramite socket e thread per far giocare un utente a “Campo Minato”. 
Il Server distribuisce su una scacchiera di dimensioni m x n un numero di mine rappresentate da un carattere e nelle restanti celle viene raffigurato il terreno non minato tramite un carattere differente. 
Una volta distribuite le mine sulla scacchiera parte il gioco: il giocatore (client) indica su quale cella vuole andare; una volta identificata dal server, questo comunica al client se si trattava di terreno minato o meno.  
La mina a questo punto potrebbe esplodere (utilizzare un meccanismo random) ed il gioco terminare, oppure non esplodere ed il gioco prosegue. Sulla scacchiera vengono distribuite un numero random di mine, compreso fra 1/5 e 4/5 delle celle totali (se la scacchiera è 5x5 avrà 25 celle totali, sulla quale vengono distribuite casualmente fra le 5 e le 20 mine).

Specifica speciale del gruppo:
 - Devono essere costruite 2 scacchiere. Il client invia il numero di mine da distribuire sul campo nelle due scacchiere (controllare che il numero sia compreso tra 1/5 e 4/5), il server organizza le scacchiere e quindi, dopo aver contato il numero di mine distribuite sia su uno che sull’altro campo, chiede al giocatore se vuole giocare sulla scacchiera con più mine o su quella con meno

## Descrizione del progetto
### Suddivisione
Il progetto si suddivide in due app principali:
 - [Client](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/tree/master/client/src/it/gruppoa/clientmina)
 - [Server](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/tree/master/server/src/it/gruppoa/servermina)

Per quanto riguarda il client, l'intento è di renderlo più semplice e "stupido" possibile, cosicché si occupi solo di leggere ed inviare stringhe al server.
Tutto questo per i seguenti motivi:
 - Facilità di aggiornamento dell' app che richiederà solo modifiche sull' app server
 - Sicurezza: Rendere impossibile il barare nel gioco tramite manipolazione del client

### Il Server
#### Classi
Diagramma uml delle classi:
<p align="center">
    <img src="https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/assets/UMLServer.PNG?raw=true"/>
</p>

Il Package Model contiene tutto ciò che riguarda la Business Logic dell' applicazione e le classi di utility.
Nel package principale si trovano la classe [Main](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/Main.java) usata solo per validare il numero di porta ed accettare i client, la classe [GameTask](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/GameTask.java) invece è usata per interagire con il client.

#### La struttura del Model
La classe fondamentale è [Cell](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/model/Cell.java) che rappresenta la singola cella ed essa ha [Tipo](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/c935da4c94b1c359b247b3fa72d5b50320777270/server/src/it/gruppoa/servermina/model/Cell.java#L69): BOMB o FLOWER.
Un array bidimensionale di celle è conservato all' interno della classe [Table](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/model/Table.java).
La classe [Game](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/model/Game.java) invece conterrà un' istanza di Table e un [GameState](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/model/GameState.java) che terrà traccia dello stato della partità, inoltre essa fornirà i metodi necessari a [GameTask](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/GameTask.java) per interagire con l'utente.
Per vedere più dettagliatamente consultare le [JavaDocs](https://alecsferra.github.io/PratoFioritoProgettoScuola/javadoc-server/) di tutto il package model;

#### Utilizzo dei Thread
I thread sono stati usati all' interno della classe Table rispettivamente nei metodi [isAnyFlowerLeft()](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/ccbbfbb6914d38f67458a5054fa55e3d8791ac2a/server/src/it/gruppoa/servermina/model/Table.java#L109) e [numberOfBombsLeft()](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/ccbbfbb6914d38f67458a5054fa55e3d8791ac2a/server/src/it/gruppoa/servermina/model/Table.java#L95) per velocizzare la ricerca.
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
Inoltre, i Thread sono anche stati usati per rendere l'applicazione utilizzabile da più client connessi allo stesso server, facendo estendere a [GameTask](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/c51b393ca0a58af7df9feee7bcd1f4be757419b4/server/src/it/gruppoa/servermina/GameTask.java#L19) (che si occupa di interagire con il client) la classe Thread.
Esempio di accettazione (preso dalla classe [Main](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/master/server/src/it/gruppoa/servermina/Main.java)):
```java
try(ServerSocket server = new ServerSocket(numeroPorta)){
            
    while(true){
                
        new GameTask(server.accept()).start();
                
    }
            
} catch (IOException ex) {
    //Gestione Errori
}  
```
Per ulteriori informazioni sulle interazioni delle varie classi consultare le [javadocs](https://alecsferra.github.io/PratoFioritoProgettoScuola/javadoc-server/).

### Il Client
Come già spiegato in precedenza, è stata adoperata la filosofia di rendere il client più passivo possibile, inoltre anche per questa app sono disponibli le [javadocs](https://alecsferra.github.io/PratoFioritoProgettoScuola/javadoc-client/).

### Flow chart che rappresenta una parita

<p align="center">
    <img src="https://raw.githubusercontent.com/AlecsFerra/PratoFioritoProgettoScuola/master/assets/AppFlow.png"/>
</p>

#### Scelte di progettazione
La probabilità di esplosione delle [bombe](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/blob/c51b393ca0a58af7df9feee7bcd1f4be757419b4/server/src/it/gruppoa/servermina/model/Cell.java#L42) è stata impostata al 66% (la quale corrisponde ai 2/3 dei casi).
```java
return WorldRandom.getBoolFromPerc(66)
        ? CellDiscoverResult.BOMB_EXPLODED
        : CellDiscoverResult.BOMB_NOT_EXPLODED;
```
Quando verrà proposto all'utente quale tabella scegliere non verrà mostrato "Tabella più facile" o "Tabella più difficile" però non determinabile in caso il numero di bombe sia uguale su entrambe le tabelle.
```console
Quale tabella vuoi a[50] o b[50]?
```
Come già spiegato prima, il server permette a più host di giocare contemporaneamente.

## Come utilizzare il gioco
Passo 1: Scaricare i jar dalla sezione [release](https://github.com/AlecsFerra/PratoFioritoProgettoScuola/releases/tag/Official).

Passo 2: Lanciare il server.
```console
user@machine:~$ java -jar ServerMina.jar 1555
```
NB: Ovviamente sostituisci la porta con quella che vuoi usare, non sei obbligato ad usare la stessa.

Passo 3: Lanciare il client.
```console
user@machine:~$ java -jar ClientMina.jar 127.0.0.1 1555
```
nb: Inserisci l'indirizzo ip della macchina su cui è hostato il server e mi raccomando non sbagliare porta!
## Suddivisione del lavoro
| Membro               | Compiti svolti                                                                                                                                                                                                    |
|----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Alessio Ferrarini    | <ul> <li>Progettazione logica dell'applicativo</li> <li>Coding dell' applicativo</li> <li>Gestione connessione</li> <li>Documentazione progetto</li> <li>Crazione JavaDocs</li> <li>Creazione relazione</li> </ul> |
| Michele Dalle Vedove | <ul> <li> Prova dell' applicazione</li> <li>Tentato aiuto nella relazione</li></ul>                                                                                                                                                                   |
| Riccardo Gini        | <ul> <li>Aiuto nell'implementazione delle probabilità</li> <li>Stesura di alcune parti della relazione </li> <li>Revisione finale della relazione</li> <li>Realizzazione flow-chart</li></ul>                                                                                                                                                                                  |
