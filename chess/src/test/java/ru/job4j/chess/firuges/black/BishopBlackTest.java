package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class BishopBlackTest
 * Класс тестирует работу методов класса BishopBlack.
 * @author Dmitry Razumov
 * @version 1
 */
public class BishopBlackTest {
    /**
     * Метод проверяет верно ли определяется позиция слона на доске.
     */
    @Test
    public void whenPosition() {
        Cell expected = Cell.C4;
        BishopBlack bishop = new BishopBlack(expected);
        Cell rsl = bishop.position();
        assertThat(rsl, is(expected));
    }

    /**
     * Метод проверяет правильно ли создается новая позиция слона на доске
     */
    @Test
    public void whenCopy() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell expected = Cell.F4;
        bishop = (BishopBlack) bishop.copy(expected);
        Cell rsl = bishop.position();
        assertThat(rsl, is(expected));
    }

    /**
     * Метод проверяет правильность нахождения полей по которым движется слон с одного поля до другого.
     */
    @Test
    public void whenC1G5WayThenD2E3F4G5() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] rsl = bishop.way(Cell.C1, Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(rsl, is(expected));
    }

    /**
     * Метод проверяет правильность нахождения полей по которым движется слон с одного поля до другого.
     */
    @Test
    public void whenG3E5WayThenD2E3F4G5() {
        BishopBlack bishop = new BishopBlack(Cell.G3);
        Cell[] rsl = bishop.way(Cell.G3, Cell.E5);
        Cell[] expected = {Cell.F4, Cell.E5};
        assertThat(rsl, is(expected));
    }

    /**
     * Метод проверяет правильность определения, движется ли слон по диагонали.
     */
    @Test
    public void whenE3G5IsDiagonalThenTrue() {
        Cell source = Cell.E3;
        Cell dest = Cell.G5;
        BishopBlack bishop = new BishopBlack(source);
        assertThat(bishop.isDiagonal(source, dest), is(true));
    }

    /**
     * Метод проверяет правильность определения, движется ли слон по диагонали.
     */
    @Test
    public void whenB3F5IsDiagonalThenFalse() {
        Cell source = Cell.B3;
        Cell dest = Cell.F5;
        BishopBlack bishop = new BishopBlack(source);
        assertThat(bishop.isDiagonal(source, dest), is(false));
    }

    /**
     * Метод проверяет срабатывание исключения, когда слон движется не по диагонали.
     */
    @Test(expected = IllegalStateException.class)
    public void whenWayException() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        bishop.way(Cell.C1, Cell.E4);
    }
}