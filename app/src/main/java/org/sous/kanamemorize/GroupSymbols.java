package org.sous.kanamemorize;

import java.util.ArrayList;
import java.util.Random;

class GroupSymbols {
    private ArrayList<Symbol> symbols; //массив символов
    private int current_max = 5; //максимальное значение символов в группе
    private int num_prev = -1; //предыдущий номер символа
    private int num = -1; //текущий номер символа

    GroupSymbols() {
        symbols = new ArrayList<>();
    }

    //добавление символа в группу
    void addSymbol(Symbol symbol) {
        symbols.add(symbol);
    }

    //получение следующего символа в группе
    Symbol getNextSymbol() {
        Random rand = new Random();
        while (num == num_prev) {
            num = rand.nextInt(current_max);
        }

        if (symbols.get(num).isComplete()) {
            if (symbols.size() > current_max) {
                symbols.remove(num);
            }
            else if (symbols.get(0).isComplete() && symbols.get(1).isComplete() && symbols.get(2).isComplete() && symbols.get(3).isComplete() && symbols.get(4).isComplete()) {
                return null;
            }
        }
        num_prev = num;
        return symbols.get(num);
    }
}