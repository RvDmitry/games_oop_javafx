package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;

/**
 * Class BishopBlack
 * Класс определяет шахматную фигуру слона и ее поведение.
 */
public class BishopBlack implements Figure {
    /**
     * Поле задает позицию фигуры на доске.
     */
    private final Cell position;

    /**
     * Конструктор создает фигуру в заданной позиции на доске.
     * @param position Поле на доске
     */
    public BishopBlack(final Cell position) {
        this.position = position;
    }

    /**
     * Метод возвращает позицию фигуры на доске.
     * @return Поле на доске
     */
    @Override
    public Cell position() {
        return this.position;
    }

    /**
     * Метод строит набор полей по которым будет двигаться фигура.
     * @param source Начальное поле
     * @param dest Конечное поле
     * @return Массив полей движения фигуры
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {

        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        int deltaX = dest.x > source.x ? 1 : -1;
        int deltaY = dest.y > source.y ? 1 : -1;
        for (int index = 0; index < size; index++) {
            int x = source.x + deltaX * (index + 1);
            int y = source.y + deltaY * (index + 1);
            steps[index] = Cell.findBy(x, y);
        }
        return steps;
    }

    /**
     * Метод проверяет лежат ли поля на диагонали.
     * @param source Начальное поле
     * @param dest Конечное поле
     * @return true, если поля лежат на диагонали, иначе false
     */
    public boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) - Math.abs(source.y - dest.y) == 0;
    }

    /**
     * Метод создает новую фигуру взамен старой на новой позиции.
     * @param dest Поле на доске, где нужно создать фигуру
     * @return Новая фигура
     */
    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
