/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.gruppoa.servermina.model.util;

/**
 *
 * @author st13299
 */
public class Pair<K, V> {

    private final K left;
    private final V right;

    public static <K, V> Pair<K, V> of(K left, V right) {
        return new Pair<>(left, right);
    }

    public Pair(K left, V right) {
        this.left = left;
        this.right = right;
    }

    public K getLeft() {
        return left;
    }

    public V getRight() {
        return right;
    }

}
