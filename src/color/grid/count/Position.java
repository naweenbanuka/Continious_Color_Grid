/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package color.grid.count;

/**
 *
 * @author Banuka
 */
class Position {
    private int column;
    private int row;

    public Position(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Position up() {
        return new Position(column, row-1);
    }

    public Position down() {
        return new Position(column, row+1);
    }

    public Position left() {
        return new Position(column-1, row);
    }

    public Position right() {
        return new Position(column+1, row);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position coord = (Position) o;

        if (column != coord.column) return false;
        return row == coord.row;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }

    @Override
    public String toString() {
        return "(" + column + ", " + row + ")";
    }
}
