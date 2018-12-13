package it.gruppoa.servermina.model.util;

/**
 * Classe di utility che rappresenta una coppia di valori
 * @author alecsferra
 * @param <K> tipo del valor di sinistra
 * @param <V> tipo del valore di destra
 */
public class Pair<K, V> {

    //valore di sinistra
    private final K left;
    //valor di destra
    private final V right;

    /**
     * Metodo di creazione di una copia per evitare la ridondaza del costruttore
     * @param <K> tipo del valore di sinsitra
     * @param <V> tipo del valore di destra
     * @param left valore di sinistra
     * @param right valore di destra 
     * @return la coppia
     */
    public static <K, V> Pair<K, V> of(K left, V right) {
        return new Pair<>(left, right);
    }

    /**
     * Costruttore di una coppia
     * @param left valore di sinistra
     * @param right valore di destra
     */
    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Ottieni il valore di sinistra
     * @return il valore di sinistra
     */
    public K getLeft() {
        return left;
    }

    /**
     * Ottieni il valore di destra
     * @return il valore di destra
     */
    public V getRight() {
        return right;
    }

}
