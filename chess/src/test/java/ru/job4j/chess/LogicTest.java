package ru.job4j.chess;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Class LogicTest
 * Класс тестирует методы класса Logic
 */
public class LogicTest {

    @Ignore
    @Test
    public void move() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        boolean rsl = logic.move(Cell.C1, Cell.H6);
        assertThat(rsl, is(true));
    }

    /**
     * Метод тестирует работу метода move, когда диагональ по которой движется слон свободная.
     */
    @Test
    public void whenMoveTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.G2));
        boolean rsl = logic.move(Cell.G2, Cell.B7);
        assertThat(rsl, is(true));
    }

    /**
     * Метод тестирует работу метода move, когда диагональ по которой движется слон занята пешкой.
     */
    @Test
    public void whenMoveFalsePawnE3() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new PawnBlack(Cell.E3));
        boolean rsl = logic.move(Cell.C1, Cell.G5);
        assertThat(rsl, is(false));
    }

    /**
     * Метод тестирует работу метода move, когда конечное поле движения слона занята пешкой.
     */
    @Test
    public void whenMoveFalsePawnG5() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new PawnBlack(Cell.G5));
        boolean rsl = logic.move(Cell.C1, Cell.G5);
        assertThat(rsl, is(false));
    }
}