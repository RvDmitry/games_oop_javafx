package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Optional;

/**
 * //TODO add comments.
 * Class Logic
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    /**
     * Массив содержит набор фигур на доске.
     */
    private final Figure[] figures = new Figure[32];
    /**
     * Индекс характеризует количество фигур на доске.
     */
    private int index = 0;

    /**
     * Метод добавляет фигуру на доску.
     * @param figure Фигура
     */
    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * Метод определяет может ли фигура сделать ход.
     * @param source Начальное поле
     * @param dest Конечно поле
     * @return true, если ход возможен, иначе false
     */
    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        int index = this.findBy(source);
        if (index != -1) {
            try {
                Cell[] steps = this.figures[index].way(source, dest);
                if (movement(steps) && steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        return rst;
    }

    /**
     * Метод определяет, есть ли на пути движения фигуры другие фигуры, которые перегрождают ей путь.
     * @param steps Набор полей по которым возможно движение фигуры
     * @return true, если путь свободен, иначе false
     */
    private boolean movement(Cell[] steps) {
        boolean free = true;
        for (Cell step : steps) {
            if (this.findBy(step) != -1) {
                free = false;
                break;
            }
        }
        return free;
    }

    /**
     * Метод очищает доску от фигур.
     */
    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * Метод определяет находится ли на заданном поле фигура.
     * Если находится возвращает ее индекс в массиве фигур. Иначе вовзращает -1.
     * @param cell Поле в котором нужно проверить, есть ли там фигура
     * @return Индекс фигуры в массиве
     */
    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * Метод переопределяет вывод массива фигур в виде строки.
     * @return Строка
     */
    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }
}
