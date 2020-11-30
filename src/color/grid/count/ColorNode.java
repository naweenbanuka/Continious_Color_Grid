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
class ColorNode {
    private Position position;
    private char color;
    private int id;

    public ColorNode(int id, int column, int row, char color) {
        this.color = color;
        this.position = new Position(column, row);
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public char getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColorNode node = (ColorNode) o;

        if (color != node.color) return false;
        return position.equals(node.position);
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + (int) color;
        return result;
    }

    @Override
    public String toString() {
        return " [" + position.toString() + ", " + color + "] ";
    }
}
